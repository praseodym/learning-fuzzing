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
    R"(FSM-based test methods

    Usage:
      methods (hsi|ads) [options] <file>

    Options:
      -h, --help               Show current help
      --version                Show version
      -s <seed>, --seed <seed> Specify a seed
      --non-random             Iterate inputs in specified order (as occurring in input file)
      -k <states>              Testing extra states [default: 1]
      --print-suite            Prints the whole test suite
)";

int main(int argc, char * argv[]) {
	const auto args = docopt::docopt(USAGE, {argv + 1, argv + argc}, true, __DATE__ __TIME__);

	const string filename = args.at("<file>").asString();
	const size_t k_max = args.at("-k").asLong();

	const auto machine = [&] {
		if (filename.find(".txt") != string::npos) {
			return read_mealy_from_txt(filename);
		} else if (filename.find(".dot") != string::npos) {
			return read_mealy_from_dot(filename).first;
		}

		clog << "warning: unrecognized file format, assuming dot";
		return read_mealy_from_dot(filename).first;
	}();

	const auto random_seeds = [&] {
		vector<uint_fast32_t> seeds(3);
		if (args.at("--seed")) {
			seed_seq s{args.at("--seed").asLong()};
			s.generate(seeds.begin(), seeds.end());
		} else {
			random_device rd;
			generate(seeds.begin(), seeds.end(), ref(rd));
		}
		return seeds;
	}();

	auto sequence_fut = async([&] {
		if (args.at("hsi").asBool()) {
			return create_adaptive_distinguishing_sequence(result(machine.graph_size));
		}
		const auto tree = create_splitting_tree(machine, args.at("--non-random").asBool()
		                                                     ? lee_yannakakis_style
		                                                     : randomized_lee_yannakakis_style,
		                                        random_seeds[0]);
		return create_adaptive_distinguishing_sequence(tree);
	});

	auto pairs_fut = async([&] {
		const auto tree = create_splitting_tree(machine, args.at("--non-random").asBool()
		                                                     ? min_hopcroft_style
		                                                     : randomized_min_hopcroft_style,
		                                        random_seeds[1]);
		return tree.root;
	});

	auto prefixes_fut = async([&] {
		return create_transfer_sequences(args.at("--non-random").asBool()
		                                     ? canonical_transfer_sequences
		                                     : minimal_transfer_sequences,
		                                 machine, 0, random_seeds[2]);
	});

	auto suffixes_fut
	    = async([&] { return create_separating_family(sequence_fut.get(), pairs_fut.get()); });

	trie<input> test_suite;
	word buffer;

	const auto suffixes = suffixes_fut.get();
	for(state s = 0; s < suffixes.size(); ++s){
		clog << "suffixes for " << s << endl;
		for(auto s2 : suffixes[s].local_suffixes) {
			for(auto x : s2){
				clog << x;
			}
			clog << endl;
		}
	}

	const auto prefixes = prefixes_fut.get();
	for(state s = 0; s < prefixes.size(); ++s) {
		clog << "prefix for " << s << endl;
		for(auto x : prefixes[s]) {
			clog << x;
		}
		clog << endl;
	}

	test(machine, prefixes, suffixes, k_max,
	     {[&buffer](auto const & w) { buffer.insert(buffer.end(), w.begin(), w.end()); },
	      [&buffer, &test_suite]() {
		      test_suite.insert(buffer);
		      buffer.clear();
			  return true;
		  }});

	const auto p = total_size(test_suite);
	cout << p.first << '\t' << p.second << '\t' << p.first + p.second << endl;

	if(args.at("--print-suite").asBool()){
		test_suite.for_each([](const auto & w){
			for(const auto & x : w) {
				cout << x << ' ';
			}
			cout << endl;
		});
	}
}
