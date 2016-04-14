#include "splitting_tree.hpp"
#include "partition.hpp"

#include <algorithm>
#include <cassert>
#include <functional>
#include <numeric>
#include <queue>
#include <random>
#include <utility>

using namespace std;

splitting_tree::splitting_tree(size_t N, size_t d) : states(N), depth(d) {
	iota(begin(states), end(states), 0);
}

result create_splitting_tree(const mealy & g, options opt, uint_fast32_t random_seed) {
	const auto N = g.graph_size;
	const auto P = g.input_size;
	const auto Q = g.output_size;

	result ret(N);
	auto & root = ret.root;
	auto & succession = ret.successor_cache;

	// We'll use a queue to keep track of leaves we have to investigate;
	// In some cases we cannot split, and have to wait for other parts of the
	// tree. We keep track of how many times we did no work. If this is too
	// much, there is no complete splitting tree.
	queue<reference_wrapper<splitting_tree>> work;
	size_t days_without_progress = 0;

	// List of inputs, will be shuffled in case of randomizations
	vector<input> all_inputs(P);
	iota(begin(all_inputs), end(all_inputs), 0);
	mt19937 generator(random_seed);

	size_t current_order = 0;
	bool split_in_current_order = false;

	// Some lambda functions capturing some state, makes the code a bit easier :)
	const auto add_push_new_block = [&work](list<list<state>> const & new_blocks, splitting_tree& boom) {
		boom.children.assign(new_blocks.size(), splitting_tree(0, boom.depth + 1));

		size_t i = 0;
		for (auto && b : new_blocks) {
			boom.children[i++].states.assign(begin(b), end(b));
		}

		for (auto && c : boom.children) {
			work.push(c);
		}

		assert(boom.states.size() == accumulate(begin(boom.children), end(boom.children), 0ul,
		                                        [](size_t l, const splitting_tree & r) {
		                                        	return l + r.states.size();
		                                        }));
	};
	const auto is_valid = [N, opt, &g](list<list<state>> const & blocks, input symbol) {
		for (auto && block : blocks) {
			const auto new_blocks = partition_(begin(block), end(block), [symbol, &g](state state) {
				return apply(g, state, symbol).to;
			}, N);
			for (auto && new_block : new_blocks) {
				if (new_block.size() != 1) return false;
			}
		}
		return true;
	};
	const auto update_succession = [N, &succession](state s, state t, size_t depth) {
		if (succession.size() < depth + 1)
			succession.resize(depth + 1, vector<state>(N, state(-1)));
		succession[depth][s] = t;
	};

	// We'll start with the root, obviously
	work.push(root);
	while (!work.empty()) {
		splitting_tree & boom = work.front();
		work.pop();
		const size_t depth = boom.depth;

		if (boom.states.size() == 1) continue;

		if (opt.randomized) shuffle(begin(all_inputs), end(all_inputs), generator);

		if (!opt.assert_minimal_order || current_order == 0) {
			// First try to split on output
			for (input symbol : all_inputs) {
				const auto new_blocks = partition_(
				    begin(boom.states),
				    end(boom.states), [symbol, depth, &g, &update_succession](state state) {
				    	const auto r = apply(g, state, symbol);
				    	update_succession(state, r.to, depth);
				    	return r.out;
				    }, Q);

				// no split -> continue with other input symbols
				if (new_blocks.size() == 1) continue;

				// not a valid split -> continue
				if (opt.check_validity && !is_valid(new_blocks, symbol)) continue;

				// a succesful split, update partition and add the children
				boom.separator = {symbol};
				add_push_new_block(new_blocks, boom);

				goto has_split;
			}
		}

		if (!opt.assert_minimal_order || current_order > 0) {
			// Then try to split on state
			for (input symbol : all_inputs) {
				vector<bool> successor_states(N, false);
				for (auto && state : boom.states) {
					successor_states[apply(g, state, symbol).to] = true;
				}

				const auto & oboom = lca(root, [&successor_states](state state) -> bool {
					return successor_states[state];
				});

				// a leaf, hence not a split -> try other symbols
				if (oboom.children.empty()) continue;

				// If we want to enforce the right order, we should :D
				if (opt.assert_minimal_order && oboom.separator.size() != current_order) continue;

				// possibly a succesful split, construct the children
				const vector<input> word = concat(vector<input>(1, symbol), oboom.separator);
				const auto new_blocks = partition_(
				    begin(boom.states),
				    end(boom.states), [word, depth, &g, &update_succession](state state) {
				    	const mealy::edge r = apply(g, state, word.begin(), word.end());
				    	update_succession(state, r.to, depth);
				    	return r.out;
				    }, Q);

				// not a valid split -> continue
				if (opt.check_validity && !is_valid(new_blocks, symbol)) continue;

				assert(new_blocks.size() > 1);

				// update partition and add the children
				boom.separator = word;
				add_push_new_block(new_blocks, boom);

				goto has_split;
			}
		}

		// We tried all we could, but did not succeed => declare incompleteness.
		if (days_without_progress++ >= work.size()) {
			if (!split_in_current_order || !opt.assert_minimal_order) {
				ret.is_complete = false;
				return ret;
			}

			current_order++;
			split_in_current_order = false;
		}

		work.push(boom);
		continue;

		has_split:
		split_in_current_order = true;
		days_without_progress = 0;
	}

	return ret;
}
