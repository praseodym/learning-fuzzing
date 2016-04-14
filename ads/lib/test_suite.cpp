#include "test_suite.hpp"

#include <iostream>
#include <random>


using namespace std;

void test(const mealy & specification, const transfer_sequences & prefixes,
          const separating_family & separating_family, size_t k_max, const writer & output) {
	vector<word> all_sequences(1);

	for (size_t k = 0; k <= k_max; ++k) {
		// clog << "*** K = " << k << endl;

		for (state s = 0; s < specification.graph_size; ++s) {
			const auto prefix = prefixes[s];

			for (auto && middle : all_sequences) {
				const auto t = apply(specification, s, middle.begin(), middle.end()).to;

				for (auto && suffix : separating_family[t].local_suffixes) {
					output.apply(prefix);
					output.apply(middle);
					output.apply(suffix);
					if(!output.reset()) return;
				}
			}
		}

		all_sequences = all_seqs(0, specification.input_size, all_sequences);
	}
}

void randomized_test(const mealy & specification, const transfer_sequences & prefixes,
                     const separating_family & separating_family, size_t min_k, size_t rnd_length,
                     const writer & output, uint_fast32_t random_seed) {
	// clog << "*** K >= " << min_k << endl;

	std::mt19937 generator(random_seed);

	// https://en.wikipedia.org/wiki/Geometric_distribution we have the random variable Y here
	uniform_int_distribution<> unfair_coin(0, rnd_length);
	uniform_int_distribution<state> prefix_selection(0, prefixes.size() - 1);
	uniform_int_distribution<size_t> suffix_selection;
	uniform_int_distribution<input> input_selection(0, specification.input_size - 1);

	while (true) {
		state current_state = 0;

		const auto & prefix = prefixes[prefix_selection(generator)];
		current_state = apply(specification, current_state, begin(prefix), end(prefix)).to;

		word middle;
		middle.reserve(min_k + 1);
		size_t minimal_size = min_k;
		while (minimal_size || unfair_coin(generator)) {
			input i = input_selection(generator);
			middle.push_back(i);
			current_state = apply(specification, current_state, i).to;
			if (minimal_size) minimal_size--;
		}

		using params = decltype(suffix_selection)::param_type;
		const auto & suffixes = separating_family[current_state].local_suffixes;
		const auto & suffix = suffixes[suffix_selection(generator, params{0, suffixes.size() - 1})];

		output.apply(prefix);
		output.apply(middle);
		output.apply(suffix);
		if(!output.reset()) return;
	}
}

void randomized_test_suffix(const mealy & specification, const transfer_sequences & prefixes,
                            const separating_family & separating_family, size_t min_k,
                            size_t rnd_length, const writer & output, uint_fast32_t random_seed) {
	vector<pair<state, word>> all_suffixes;
	for (state s = 0; s < separating_family.size(); ++s) {
		for (auto const & w : separating_family[s].local_suffixes) {
			all_suffixes.emplace_back(s, w);
		}
	}

	// state -> [(input, state)]
	vector<vector<pair<input, state>>> reverse_machine(specification.graph_size);
	for (state s = 0; s < specification.graph_size; ++s) {
		for (input i = 0; i < specification.input_size; ++i) {
			const auto t = apply(specification, s, i).to;
			reverse_machine[t].emplace_back(i, s);
		}
	}

	std::mt19937 generator(random_seed);

	// https://en.wikipedia.org/wiki/Geometric_distribution we have the random variable Y here
	uniform_int_distribution<> unfair_coin(0, rnd_length);
	uniform_int_distribution<state> suffix_selection(0, all_suffixes.size() - 1);
	uniform_int_distribution<size_t> input_selection;

	while (true) {
		const auto & state_suffix = all_suffixes[suffix_selection(generator)];
		const auto & suffix = state_suffix.second;
		state current_state = state_suffix.first;

		word middle;
		middle.reserve(min_k + 1);
		size_t minimal_size = min_k;
		while (minimal_size || unfair_coin(generator)) {
			const auto & preds = reverse_machine[current_state];
			if(preds.empty()) {
				cerr << "ERROR: no predecessors for this state!\n";
				break;
			}

			using params = decltype(input_selection)::param_type;
			const auto & input_state
			    = preds[input_selection(generator, params{0, preds.size() - 1})];

			current_state = input_state.second;
			middle.insert(middle.begin(), input_state.first);

			if (minimal_size) minimal_size--;
		}

		const auto & prefix = prefixes[current_state];

		output.apply(prefix);
		output.apply(middle);
		output.apply(suffix);
		if(!output.reset()) return;
	}
}

writer default_writer(std::vector<std::string> const & inputs) {
	static const auto print_word = [&](word w) {
		for (auto && x : w) cout << inputs[x] << ' ';
	};
	static const auto reset = [&] {
		cout << endl;
		return bool(cout);
	};
	return {print_word, reset};
}
