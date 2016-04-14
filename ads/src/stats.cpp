#include <adaptive_distinguishing_sequence.hpp>
#include <mealy.hpp>
#include <reachability.hpp>
#include <read_mealy.hpp>
#include <separating_family.hpp>
#include <splitting_tree.hpp>
#include <transfer_sequences.hpp>

#include <algorithm>
#include <fstream>
#include <future>
#include <iostream>
#include <numeric>
#include <random>

using namespace std;

template <typename C, typename S>
void print_quantiles(C const & container, S && selector, ostream & out) {
	const auto index_weight = [&](double p) -> pair<size_t, double> {
		auto index = (p * (container.size() - 1));
		return {floor(index), 1 - fmod(index, 1)};
	};

	auto sorted_container = container;
	sort(sorted_container.begin(), sorted_container.end(),
	     [&](auto const & l, auto const & r) { return selector(l) < selector(r); });
	out << "min/Q1/Q2/Q3/max ";
	out << selector(sorted_container.front()) << '/';

	const auto i25 = index_weight(0.25);
	out << i25.second * selector(sorted_container[i25.first])
	           + (1 - i25.second) * selector(sorted_container[i25.first + 1])
	    << '/';

	const auto i50 = index_weight(0.50);
	out << i50.second * selector(sorted_container[i50.first])
	           + (1 - i50.second) * selector(sorted_container[i50.first + 1])
	    << '/';

	const auto i75 = index_weight(0.75);
	out << i75.second * selector(sorted_container[i75.first])
	           + (1 - i75.second) * selector(sorted_container[i75.first + 1])
	    << '/';

	out << selector(sorted_container.back());
}

static auto count_self_loops(mealy const & m) {
	vector<long> ret(m.graph_size);
	for (state s = 0; s < m.graph_size; ++s) {
		ret[s] = count_if(m.graph[s].begin(), m.graph[s].end(), [=](auto e) { return e.to == s; });
	}
	return ret;
}

static void print_stats_for_machine(string filename) {
	const auto machine = [&] {
		if (filename.find(".txt") != string::npos) {
			return read_mealy_from_txt(filename);
		} else if (filename.find(".dot") != string::npos) {
			return read_mealy_from_dot(filename).first;
		}

		clog << "warning: unrecognized file format, assuming dot";
		return read_mealy_from_dot(filename).first;
	}();

	cout << "machine " << filename << " has\n";
	cout << '\t' << machine.graph_size << " states\n";
	cout << '\t' << machine.input_size << " inputs\n";
	cout << '\t' << machine.output_size << " outputs" << endl;

	const auto reachable_machine = reachable_submachine(machine, 0);
	cout << '\t' << reachable_machine.graph_size << " reachable states" << endl;

	const auto prefixes = create_transfer_sequences(canonical_transfer_sequences, reachable_machine, 0, 0);
	cout << "prefixes ";
	print_quantiles(prefixes, [](auto const & l) { return l.size(); }, cout);
	cout << endl;

	const auto self_loop_counts = count_self_loops(reachable_machine);
	cout << "self loops ";
	print_quantiles(self_loop_counts, [](auto const & l) { return l; }, cout);
	cout << endl;

	const auto counted_outputs = [&] {
		vector<unsigned> counts(reachable_machine.input_size, 0);
		for (auto && r : reachable_machine.graph)
			for (auto && e : r) counts[e.out]++;
		return counts;
	}();
	cout << "output usage ";
	print_quantiles(counted_outputs, [](auto const & l) { return l; }, cout);
	cout << endl;
	{
		ofstream extended_log("extended_log.txt");
		for(auto && x : counted_outputs) extended_log << x << '\n';
	}

	const auto counted_states = [&] {
		vector<unsigned> counts(reachable_machine.graph_size, 0);
		for (auto && r : reachable_machine.graph)
			for (auto && e : r) counts[e.to]++;
		return counts;
	}();
	cout << "state usage ";
	print_quantiles(counted_states, [](auto const & l) { return l; }, cout);
	cout << endl;
	{
		ofstream extended_log("extended_log2.txt");
		for(auto && x : counted_states) extended_log << x << '\n';
	}

	const auto unique_transitions = [&] {
		vector<unsigned> ret(reachable_machine.input_size+1, 0);
		for (state s = 0; s < reachable_machine.graph_size; ++s) {
			unsigned count = 0;
			vector<bool> c(reachable_machine.graph_size, false);
			for (auto && x : reachable_machine.graph[s]) {
				if(!c[x.to]) {
					c[x.to] = true;
					count++;
				}
			}
			ret[count]++;
		}
		return ret;
	}();
	cout << "transition usage ";
	print_quantiles(unique_transitions, [](auto const & l) { return l; }, cout);
	cout << endl;
	{
		ofstream extended_log("extended_log3.txt");
		for (auto && x : unique_transitions) extended_log << x << '\n';
	}

	return;

	random_device rd;
	uint_fast32_t seeds[] = {rd(), rd()};
	auto sequence_fut = async([&] {
		const auto tree = create_splitting_tree(machine, randomized_lee_yannakakis_style, seeds[0]);
		return create_adaptive_distinguishing_sequence(tree);
	});

	auto pairs_fut = async([&] {
		const auto tree = create_splitting_tree(machine, randomized_min_hopcroft_style, seeds[1]);
		return tree.root;
	});

	const auto suffixes = create_separating_family(sequence_fut.get(), pairs_fut.get());

	cout << "number of suffixes (randomized) ";
	print_quantiles(suffixes, [](auto const & l) { return l.local_suffixes.size(); }, cout);
	cout << endl;

	vector<word> all_suffixes;
	for (auto const & s : suffixes)
		for (auto const & t : s.local_suffixes) all_suffixes.push_back(t);

	cout << "length of all suffixes (randomized) ";
	print_quantiles(all_suffixes, [](auto const & l) { return l.size(); }, cout);
	cout << endl;
}

int main(int argc, char * argv[]) {
	if (argc != 2) {
		cerr << "usages: stats <filename>" << endl;
		return 1;
	}

	const string filename = argv[1];
	print_stats_for_machine(filename);
}
