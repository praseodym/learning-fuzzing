#include "adaptive_distinguishing_sequence.hpp"

#include <algorithm>
#include <cassert>
#include <functional>
#include <queue>

using namespace std;

adaptive_distinguishing_sequence::adaptive_distinguishing_sequence(size_t N, size_t d)
: CI(N), depth(d) {
	for (size_t i = 0; i < N; ++i) CI[i] = {i, i};
}

adaptive_distinguishing_sequence create_adaptive_distinguishing_sequence(const result & splitting_tree){
	const auto & root = splitting_tree.root;
	const auto & succession = splitting_tree.successor_cache;
	const auto N = root.states.size();

	adaptive_distinguishing_sequence sequence(N, 0);

	queue<reference_wrapper<adaptive_distinguishing_sequence>> work;
	work.push(sequence);

	while(!work.empty()){
		adaptive_distinguishing_sequence & node = work.front();
		work.pop();

		if(node.CI.size() < 2) continue;

		vector<bool> states(N, false);
		for(auto && state : node.CI){
			states[state.first] = true;
		}

		const auto & oboom = lca(root, [&states](state state) -> bool{
			return states[state];
		});

		if(oboom.children.empty()) continue;

		node.w = oboom.separator;
		for(auto && c : oboom.children){
			adaptive_distinguishing_sequence new_c(0, node.depth + 1);

			size_t i = 0;
			size_t j = 0;

			while(i < node.CI.size() && j < c.states.size()){
				if(node.CI[i].first < c.states[j]) {
					i++;
				} else if(node.CI[i].first > c.states[j]) {
					j++;
				} else {
					const auto curr = succession[oboom.depth][node.CI[i].first];
					const auto init = node.CI[i].second;
					new_c.CI.push_back({curr, init});
					i++;
					j++;
				}
			}

			// FIXME: this should/could be done without sorting...
			sort(begin(new_c.CI), end(new_c.CI));

			if(!new_c.CI.empty()){
				node.children.push_back(move(new_c));
			}
		}

		assert(node.children.size() > 1);

		for(auto & c : node.children) {
			work.push(c);
		}
	}

	return sequence;
}
