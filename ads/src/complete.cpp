#include <adaptive_distinguishing_sequence.hpp>
#include <read_mealy.hpp>
#include <separating_family.hpp>
#include <splitting_tree.hpp>
#include <test_suite.hpp>
#include <transfer_sequences.hpp>
#include <trie.hpp>

#include <docopt.h>

#include <future>
#include <iostream>
#include <random>
#include <string>

using namespace std;

static const char USAGE[] =
    R"(FSM-completer (only dot), also renames the state names

    Usage:
      methods [options] <file>

    Options:
      -h, --help               Show current help
      --version                Show version
      --sink                   Completion with sink
      --loop                   Completion with self loops (default)
      --output <out>           Output for new transitions (leave empty for fresh output)
)";

void write_mealy_to_dot(const mealy & m, const translation & translation, std::ostream & out) {
	const auto inputs = create_reverse_map(translation.input_indices);
	const auto output = create_reverse_map(translation.output_indices);

	out << "digraph {\n";

	for (state s = 0; s < m.graph_size; ++s) {
		for (input i = 0; i < m.input_size; ++i) {
			if (!defined(m, s, i)) continue;
			const auto ret = apply(m, s, i);
			out << s << " -> " << ret.to << " [label=\"" << inputs[i] << " / " << output[ret.out]
			    << "\"]\n";
		}
	}

	out << "}\n";
}

int main(int argc, char * argv[]) {
	const auto args = docopt::docopt(USAGE, {argv + 1, argv + argc}, true, __DATE__ __TIME__);

	const string filename = args.at("<file>").asString();

	auto mt = read_mealy_from_dot(filename, false);
	auto & machine = mt.first;
	auto & translation = mt.second;

	if (!is_complete(machine)) {
		const auto out = [&]() -> output {
			if (args.at("--output")) {
				const string out_str = args.at("--output").asString();
				if (translation.output_indices.count(out_str)) {
					// reuse old output
					return translation.output_indices[out_str];
				}
			}
			// else: grab a new one
			string newo = "SILENT";
			while(translation.output_indices.count(newo)) newo += '0';
			return translation.output_indices[newo] = machine.output_size++;
		}();


		if (args.at("--sink").asBool()) {
			// add sink
			const auto new_state = machine.graph_size++;
			machine.graph.resize(machine.graph_size);

			for (state s = 0; s < machine.graph_size; ++s) {
				machine.graph[s].resize(machine.input_size);
				for (input i = 0; i < machine.input_size; ++i) {
					if (defined(machine, s, i)) continue;
					machine.graph[s][i] = mealy::edge(new_state, out);
				}
			}
		} else {
			// add self loops
			for (state s = 0; s < machine.graph_size; ++s) {
				machine.graph[s].resize(machine.input_size);
				for (input i = 0; i < machine.input_size; ++i) {
					if (defined(machine, s, i)) continue;
					machine.graph[s][i] = mealy::edge(s, out);
				}
			}
		}
	}

	write_mealy_to_dot(machine, translation, cout);
}
