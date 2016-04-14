#pragma once

#include "types.hpp"

struct adaptive_distinguishing_sequence;
struct splitting_tree;

/// \brief From the LY algorithm we generate a separating family
/// If the adaptive distinguihsing sequence is complete, then we do not need to augment the LY
/// result. If it is not complete, we augment it with sequences from the HSI-method. In both cases
/// the result is a separating family (as defined in LY).

/// \brief A set (belonging to some state) of separating sequences
/// It only has local_suffixes, as all suffixes are "harmonized", meaning that sequences share
/// prefixes among the family. With this structure we can define the HSI-method and DS-method. Our
/// method is a hybrid one. Families are always indexed by state.
struct separating_set {
	std::vector<word> local_suffixes;
};

using separating_family = std::vector<separating_set>;

/// \brief Creates the separating family from the results of the LY algorithm
/// If the sequence is complete, we do not need the sequences in the splitting tree.
separating_family create_separating_family(const adaptive_distinguishing_sequence & sequence,
                                           const splitting_tree & separating_sequences);
