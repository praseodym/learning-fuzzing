#pragma once

#include "types.hpp"

struct mealy;

// state -> sequence going to that state
using transfer_sequences = std::vector<word>;

struct transfer_options {
	// range used to sample the work-queue. [0,0] is a bfs (minimal), [1,1] is a dfs (dumb).
	// q_min should be smaller than q_max.
	double q_min;
	double q_max;

	// indicates whether to randomly iterate over the alphabet
	// and to randomly select in [q_min, q_max] instead of taking the center
	bool randomized;
};

const transfer_options canonical_transfer_sequences{0.0, 0.0, false};
const transfer_options minimal_transfer_sequences{0.0, 0.0, true};
const transfer_options buggy_transfer_sequences{0.0, 1.0, true};
const transfer_options longest_transfer_sequences{1.0, 1.0, true}; // longest, forming a tree

transfer_sequences create_transfer_sequences(transfer_options const & opt, mealy const & machine,
                                             state s, uint_fast32_t random_seed);
