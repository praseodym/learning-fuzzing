#pragma once

#include "types.hpp"

#include <map>
#include <string>
#include <vector>

/*
 * Everything is indexed by size_t's, so that we can index vectors
 * in constant time. Can only represent deterministic machines,
 * but partiality still can occur.
 *
 * Note that graph_size == graph.size() and that input_size equals the size
 * of the biggest row in graph. Finally output_size bounds the number of
 * outputs. These values are redundant, but nice to have here.
 */
struct mealy {
	struct edge {
		edge() = default;
		edge(state t, output o) : to(t), out(o) {}
		state to = state(-1);
		output out = output(-1);
	};

	// state -> input -> (output, state)
	std::vector<std::vector<edge>> graph;

	size_t graph_size = 0;
	size_t input_size = 0;
	size_t output_size = 0;
};

inline bool is_complete(const mealy & m){
	for(state n = 0; n < m.graph_size; ++n){
		if(m.graph[n].size() != m.input_size) return false;
		for(auto && e : m.graph[n]) if(e.to == state(-1) || e.out == output(-1)) return false;
	}
	return true;
}

inline bool defined(mealy const & m, state s, input i) {
	if (s >= m.graph.size()) return false;
	if (i >= m.graph[s].size()) return false;
	if (m.graph[s][i].to == state(-1) || m.graph[s][i].out == output(-1)) return false;
	return true;
}

inline mealy::edge apply(mealy const & m, state state, input input){
	return m.graph[state][input];
}

template <typename Iterator>
mealy::edge apply(mealy const & m, state state, Iterator b, Iterator e){
	mealy::edge ret;
	ret.to = state;
	while(b != e){
		ret = apply(m, ret.to, *b++);
	}
	return ret;
}
