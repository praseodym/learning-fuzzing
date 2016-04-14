#pragma once

#include "types.hpp"
#include "splitting_tree.hpp"

#include <utility>

/*
 * The adaptive distinguishing sequence as described in Lee & Yannakakis. This
 * is not a sequence, but a decision tree! It can be constructed from the Lee
 * & Yannakakis-style splitting tree. We also need some other data produced
 * by the splitting tree algorithm.
 */

struct adaptive_distinguishing_sequence {
	adaptive_distinguishing_sequence(size_t N, size_t depth);

	// current, initial
	std::vector<std::pair<state, state>> CI;
	std::vector<adaptive_distinguishing_sequence> children;
	word w;
	size_t depth;
};

adaptive_distinguishing_sequence create_adaptive_distinguishing_sequence(result const & splitting_tree);
