#include "transfer_sequences.hpp"

#include "mealy.hpp"

#include <algorithm>
#include <numeric>
#include <deque>
#include <random>
#include <tuple>

using namespace std;

namespace {
static size_t clamp_to_size_t(double x, size_t min, size_t max) {
	if (x >= max) return max;
	if (x <= min) return min;
	return x;
}
}

transfer_sequences create_transfer_sequences(transfer_options const & opt, const mealy & machine,
                                             state s, uint_fast32_t random_seed) {
	mt19937 generator(random_seed);
	uniform_real_distribution<double> dist(opt.q_min, opt.q_max);

	vector<bool> added(machine.graph_size, false);
	vector<word> words(machine.graph_size);
	vector<input> all_inputs(machine.input_size);
	iota(begin(all_inputs), end(all_inputs), input(0));

	deque<state> work(1, s);
	added[s] = true;
	while (!work.empty()) {
		const auto u = [&] {
			// get the place in the array to pop a state
			const auto sample = dist(generator);
			const auto scaled_sample = clamp_to_size_t(floor(work.size() * sample), 0, work.size()-1);
			const auto it = work.begin() + scaled_sample;
			// get the element
			const auto ret = *it;
			// pop it
			work.erase(it);
			// return it
			return ret;
		}();

		// NOTE: we could also shuffle work, but we would need to do this per distance
		// the current shuffle is an approximation of real randomization, but easier to implement.
		if (opt.randomized) shuffle(begin(all_inputs), end(all_inputs), generator);
		for (input i : all_inputs) {
			const auto v = apply(machine, u, i).to;
			if (added[v]) continue;

			work.push_back(v);
			added[v] = true;
			words[v] = words[u];
			words[v].push_back(i);
		}
	}

	return words;
}
