#include <mealy.hpp>
#include <reachability.hpp>
#include <splitting_tree.hpp>

#include <docopt.h>
#include <boost/lexical_cast.hpp>

#include <iostream>
#include <random>
#include <fstream>

using namespace std;

static const char USAGE[] =
R"(Random Mealy machine generator

    Usage:
      generator random [options] <states> <inputs> <outputs> <machines> [<seed>]
      generator hopcroft a <states>
      generator hopcroft b <states>

    Options:
      -h, --help                    Show this screen
      --version                     Show version
      -m, --minimal                 Only generate minimal machines
      -c, --connected               Only generate reachable machines
      --output-cluster <factor>     How clustered should the outputs be
      --state-cluster <factor>      And what about states
      --single-output-boost <fctr>  Boost for a single output (e.g. quiescence)
      --permute-alphabets <n>       Makes n copies with permuted input/output
)";

static size_t number_of_leaves(splitting_tree const & root) {
	if (root.children.empty()) return 1;

	return accumulate(root.children.begin(), root.children.end(), 0ul,
	                  [](auto const & l, auto const & r) { return l + number_of_leaves(r); });
}

struct random_options {
	double output_spread = 0;
	double state_spread = 0;
	double single_output_boost = 1;
};

static mealy permute_alphabets(mealy const & m){
	mt19937 gen(random_device{}());
	const auto create_permutation = [&gen](size_t n){
		vector<size_t> p(n);
		iota(p.begin(), p.end(), 0);
		shuffle(p.begin(), p.end(), gen);
		return p;
	};

	const auto ip = create_permutation(m.input_size);
	const auto op = create_permutation(m.output_size);

	mealy ret = m;
	for(state s = 0; s < m.graph_size; ++s){
		for(input i = 0; i < m.input_size; ++i) {
			ret.graph[s][i] = m.graph[s][ip[i]];
			ret.graph[s][i].out = op[ret.graph[s][i].out];
		}
	}
	return ret;
}

static mealy generate_random_machine(size_t N, size_t P, size_t Q, random_options opts, mt19937 & gen) {
	mealy m;

	m.graph_size = N;
	m.input_size = P;
	m.output_size = Q;

	m.graph.assign(m.graph_size, vector<mealy::edge>(m.input_size));

	auto o_dist = [&] {
		const auto factor = opts.output_spread;
		vector<double> probs(m.output_size);
		for (output o = 0; o < m.output_size; ++o)
			probs[o] = exp(factor * o / double(m.output_size - 1));
		probs[0] *= opts.single_output_boost;
		discrete_distribution<output> dist(probs.begin(), probs.end());
		return dist;
	}();

	auto s_dist = [&] {
		const auto factor = opts.state_spread;
		vector<double> probs(m.graph_size);
		for (output o = 0; o < m.graph_size; ++o)
			probs[o] = exp(factor * o / double(m.graph_size - 1));
		discrete_distribution<state> dist(probs.begin(), probs.end());
		return dist;
	}();

	vector<state> states(m.graph_size);
	iota(states.begin(), states.end(), 0);

	for (state s = 0; s < m.graph_size; ++s) {
		shuffle(states.begin(), states.end(), gen);
		for (input i = 0; i < m.input_size; ++i) {
			m.graph[s][i] = {states[s_dist(gen)], o_dist(gen)};
		}
	}

	return m;
}

static void print_machine(string const & prefix, mealy const & m, size_t count) {
	ofstream file(prefix + "_" + to_string(m.graph_size) + "_" + to_string(m.input_size) + "_"
	              + to_string(m.output_size) + "_" + to_string(count) + ".txt");
	for (state s = 0; s < m.graph_size; ++s) {
		for (input i = 0; i < m.input_size; ++i) {
			auto e = m.graph[s][i];
			file << s << " -- " << i << " / " << e.out << " -> " << e.to << endl;
		}
	}
}

int main(int argc, char * argv[]) {
	const auto args = docopt::docopt(USAGE, {argv + 1, argv + argc}, true, __DATE__ __TIME__);

	if (args.at("random").asBool()) {
		random_options opts;
		if (args.at("--output-cluster"))
			opts.output_spread = -boost::lexical_cast<double>(args.at("--output-cluster").asString());
		if (args.at("--state-cluster"))
			opts.state_spread = -boost::lexical_cast<double>(args.at("--state-cluster").asString());
		if (args.at("--single-output-boost"))
			opts.single_output_boost = boost::lexical_cast<double>(args.at("--single-output-boost").asString());

		auto gen = [&] {
			if (args.at("<seed>")) {
				auto seed = args.at("<seed>").asLong();
				return mt19937(seed);
			}
			random_device rd;
			return mt19937(rd());
		}();

		size_t number_of_machines = args.at("<machines>").asLong();
		size_t constructed = 0;

		while (constructed < number_of_machines) {
			auto const m = generate_random_machine(args.at("<states>").asLong(),
			                                       args.at("<inputs>").asLong(),
			                                       args.at("<outputs>").asLong(), opts, gen);

			if (args.at("--connected").asBool()) {
				auto const m2 = reachable_submachine(m, 0);
				if (m2.graph_size != m.graph_size) continue;
			}

			if (args.at("--minimal").asBool()) {
				auto const tree = create_splitting_tree(m, min_hopcroft_style, 0).root;
				if (number_of_leaves(tree) != m.graph_size) continue;
			}

			if (args.at("--permute-alphabets")) {
				auto permuted_copies = args.at("--permute-alphabets").asLong();
				while (permuted_copies--) {
					constructed++;
					auto copy = permute_alphabets(m);
					print_machine("machine", copy, constructed);
				}
			} else {
				constructed++;
				print_machine("machine", m, constructed);
			}
		}
	} else if (args.at("hopcroft").asBool() && args.at("a").asBool()) {
		mealy m;

		m.graph_size = args.at("<states>").asLong();
		m.input_size = m.output_size = 2;
		m.graph.assign(m.graph_size, vector<mealy::edge>(m.input_size));

		for (state s = 0; s < m.graph_size; ++s) {
			m.graph[s][0] = mealy::edge(s + 1, 0);
			m.graph[s][1] = mealy::edge(s, 0);
		}

		// "accepting state"
		m.graph[m.graph_size - 1][0] = mealy::edge(m.graph_size - 1, 1);
		m.graph[m.graph_size - 1][1] = mealy::edge(m.graph_size - 1, 1);

		print_machine("hopcroft_a", m, 1);
	} else if (args.at("hopcroft").asBool() && args.at("b").asBool()) {
		// In the original paper, the machine is not well defined...
		// So I don't know what Hopcroft had in mind exactly...
		mealy m;

		auto n = m.graph_size = args.at("<states>").asLong();
		m.input_size = m.output_size = 2;
		m.graph.assign(m.graph_size, vector<mealy::edge>(m.input_size));

		for (state s = 0; s < n; ++s) {
			m.graph[s][0] = mealy::edge(s ? s - 1 : 0, s < n / 2 ? 0 : 1);
			if (s < n / 2) {
				m.graph[s][1] = mealy::edge(2 * s + 1, 0);
			} else {
				m.graph[s][1] = mealy::edge(s + (n - s) / 2, 0);
			}
		}

		print_machine("hopcroft_b", m, 1);
	}
}
