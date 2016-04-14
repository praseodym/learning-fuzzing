#include <adaptive_distinguishing_sequence.hpp>
#include <logging.hpp>
#include <mealy.hpp>
#include <reachability.hpp>
#include <read_mealy.hpp>
#include <separating_family.hpp>
#include <splitting_tree.hpp>
#include <test_suite.hpp>
#include <transfer_sequences.hpp>

#include <docopt.h>

#include <algorithm>
#include <future>
#include <iomanip>
#include <numeric>
#include <random>

using namespace std;

static const char USAGE[] =
R"(Generate a test suite. Use '=' as filename for stdin.

    Usage:
      main [options] <filename> (all|fixed|random) <max k> <rnd length>

    Options:
      -h, --help       Show this screen
      --version        Show version
      --seed <seed>    32 bits seeds for deterministic execution
      --no-ds          Only use the classical algorithm (hsi)
      --random-ds      Choose randomly between the ds method or hsi method
      --no-suffix      Dont calculate anything smart, just do the random stuff
      --suffix-based   Only applies in random mode. Chooses suffix first, and not prefix first
      --prefix <type>  Chooses the kind of prefix: canonical, minimal, buggy, longest
)";

using time_logger = silent_timer;

int main(int argc, char *argv[]) try {
	const auto args = docopt::docopt(USAGE, {argv + 1, argv + argc}, true, "25 Nov 11:50");

	const string filename = args.at("<filename>").asString();
	const bool use_stdio = filename == "=";

	const auto k_max = args.at("<max k>").asLong();
	const auto rnd_length = args.at("<rnd length>").asLong();

	const bool streaming = args.at("all").asBool() || args.at("fixed").asBool();
	const bool random_part = args.at("all").asBool() || args.at("random").asBool();
	const bool no_suffix = args.at("--no-suffix").asBool();
	const bool suffix_based = args.at("--suffix-based").asBool();

	const bool seed_provided = bool(args.at("--seed"));
	const uint_fast32_t seed = seed_provided ? args.at("--seed").asLong() : 0;

	const bool use_distinguishing_sequence = [&]{
		if(args.at("--random-ds").asBool()) {
			random_device rd;
			return rd() - rd.min() < (rd.max() - rd.min()) / 2;
		}
		return !args.at("--no-ds").asBool();
	}();
	const string prefix_type = args.at("--prefix") ? args.at("--prefix").asString() : "minimal";
	const bool randomize_hopcroft = true;
	const bool randomize_lee_yannakakis = true;

	const auto machine_and_translation = [&]{
		time_logger t_("reading file " + filename);
		if(use_stdio){
			return read_mealy_from_dot(cin);
		}
		if(filename.find(".txt") != string::npos) {
			const auto m = read_mealy_from_txt(filename);
			const auto t = create_translation_for_mealy(m);
			return make_pair(move(m), move(t));
		} else if (filename.find(".dot") != string::npos) {
			return read_mealy_from_dot(filename);
		}

		clog << "warning: unrecognized file format, assuming .dot\n";
		return read_mealy_from_dot(filename);
	}();

	const auto & machine = reachable_submachine(move(machine_and_translation.first), 0);
	const auto & translation = machine_and_translation.second;

	// every thread gets its own seed
	const auto random_seeds = [&] {
		vector<uint_fast32_t> seeds(4);
		if (seed_provided) {
			seed_seq s{seed};
			s.generate(seeds.begin(), seeds.end());
		} else {
			random_device rd;
			generate(seeds.begin(), seeds.end(), ref(rd));
		}
		return seeds;
	}();

	auto all_pair_separating_sequences = [&]{
		if(no_suffix) return splitting_tree(0, 0);

		const auto splitting_tree_hopcroft = [&]{
			time_logger t("creating hopcroft splitting tree");
			return create_splitting_tree(machine, randomize_hopcroft ? randomized_hopcroft_style : hopcroft_style, random_seeds[0]);
		}();

		return splitting_tree_hopcroft.root;
	}();

	auto sequence = [&]{
		if(no_suffix) return adaptive_distinguishing_sequence(0, 0);

		const auto tree = [&]{
			time_logger t("Lee & Yannakakis I");
			if(use_distinguishing_sequence)
				return create_splitting_tree(machine, randomize_lee_yannakakis ? randomized_lee_yannakakis_style : lee_yannakakis_style, random_seeds[1]);
			else
				return result(machine.graph_size);
		}();

		const auto sequence_ = [&]{
			time_logger t("Lee & Yannakakis II");
			return create_adaptive_distinguishing_sequence(tree);
		}();

		return sequence_;
	}();

	auto transfer_sequences = [&] {
		time_logger t("determining transfer sequences");
		if(prefix_type == "canonical") return create_transfer_sequences(canonical_transfer_sequences, machine, 0, random_seeds[2]);
		if(prefix_type == "minimal") return create_transfer_sequences(minimal_transfer_sequences, machine, 0, random_seeds[2]);
		if(prefix_type == "buggy") return create_transfer_sequences(buggy_transfer_sequences, machine, 0, random_seeds[2]);
		if(prefix_type == "longest") return create_transfer_sequences(longest_transfer_sequences, machine, 0, random_seeds[2]);

		cerr << "Warning: no valid prefix type specified. Assuming minimal.\n";
		return create_transfer_sequences(minimal_transfer_sequences, machine, 0, random_seeds[2]);
	}();

	auto inputs = create_reverse_map(translation.input_indices);

	const auto separating_family = [&]{
		if(no_suffix) {
			separating_set s{{word{}}};
			vector<separating_set> suffixes(machine.graph_size, s);
			return suffixes;
		}

		time_logger t("making seperating family");
		return create_separating_family(sequence, all_pair_separating_sequences);
	}();

	if(streaming){
		time_logger t("outputting all preset tests");
		test(machine, transfer_sequences, separating_family, k_max, default_writer(inputs));
	}

	if(random_part){
		time_logger t("outputting all random tests");
		const auto k_max_ = streaming ? k_max + 1 : 0;

		if (suffix_based) {
			randomized_test_suffix(machine, transfer_sequences, separating_family, k_max_,
			                       rnd_length, default_writer(inputs), random_seeds[3]);
		} else {
			randomized_test(machine, transfer_sequences, separating_family, k_max_, rnd_length,
			                default_writer(inputs), random_seeds[3]);
		}
	}

} catch (exception const & e) {
	cerr << "Exception thrown: " << e.what() << endl;
	return 1;
}
