#include "separating_family.hpp"
#include "adaptive_distinguishing_sequence.hpp"
#include "splitting_tree.hpp"
#include "trie.hpp"

#include <functional>
#include <stack>
#include <utility>

using namespace std;

separating_family create_separating_family(const adaptive_distinguishing_sequence & sequence,
                                           const splitting_tree & separating_sequences) {
	const auto N = sequence.CI.size();

	vector<trie<input>> suffixes(N);
	separating_family ret(N);

	// First we accumulate the kind-of-UIOs and the separating words we need. We will do this with a
	// breath first search. If we encouter a set of states which is not a singleton, we add
	// sequences from the matrix, locally and globally.
	stack<pair<word, reference_wrapper<const adaptive_distinguishing_sequence>>> work;
	work.push({{}, sequence});
	while (!work.empty()) {
		auto uio = work.top().first;
		const adaptive_distinguishing_sequence & node = work.top().second;
		work.pop();

		// On a leaf, we need to add the accumulated word as suffix (this is more or less a UIO).
		// And, if needed, we also need to augment the set of suffixes (for all pairs).
		if (node.children.empty()) {
			for (auto && p : node.CI) {
				const auto state = p.second;
				suffixes[state].insert(uio);
			}

			vector<bool> states(N, false);
			for (auto && p : node.CI) {
				const auto s = p.second;
				states[s] = true;
			}
			const auto roots
			    = multi_lca(separating_sequences, [&states](state z) -> bool { return states[z]; });

			// NOTE: this is slightly less efficient than doing the same thing inline in lca(...)
			// but I was to lazy to write a dfs again
			for (const splitting_tree & n : roots) {
				for (state s : n.states) {
					if (states[s]) {
						suffixes[s].insert(n.separator);
					}
				}
			}

			// Finalize the suffixes
			for (auto && p : node.CI) {
				const auto s = p.second;
				auto & current_suffixes = suffixes[s];

				ret[s].local_suffixes = flatten(current_suffixes);
				current_suffixes.clear();
			}

			continue;
		}

		// add some work
		for (auto && i : node.w) uio.push_back(i);        // extend the word
		for (auto && c : node.children) work.push({uio, c}); // and visit the children with word
	}

	return ret;
}
