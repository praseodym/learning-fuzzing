#pragma once

#include "mealy.hpp"

/// \brief A splitting tree as defined in Lee & Yannakakis.
/// This is also known as a derivation tree (Knuutila). Both the Gill/Moore/Hopcroft-style and the
/// Lee&Yannakakis-style trees are splitting trees.
struct splitting_tree {
	splitting_tree(size_t N, size_t depth);

	std::vector<state> states;
	std::vector<splitting_tree> children;
	word separator;
	size_t depth = 0;
};

/// \brief the generic lca implementation.
/// It uses \p store to store the relevant nodes (in some bottom up order), the last store is the
/// actual lowest common ancestor (but the other might be relevant as well). The function \p f is
/// the predicate on the states (returns true for the states we want to compute the lca of).
template <typename Fun, typename Store>
size_t lca_impl(splitting_tree const & node, Fun && f, Store && store) {
	static_assert(std::is_same<decltype(f(state(0))), bool>::value, "f should return a bool");
	if (node.children.empty()) {
		// if it is a leaf, we search for the states
		// if it contains a state, return this leaf
		for (auto s : node.states) {
			if (f(s)) {
				store(node);
				return 1;
			}
		}
		// did not contain the leaf => nullptr
		return 0;
	} else {
		// otherwise, check our children. If there is a single one giving a node
		// we return this (it's the lca), if more children return a non-nil
		// node, then we are the lca
		size_t count = 0;
		for (auto & c : node.children) {
			auto inner_count = lca_impl(c, f, store);
			if (inner_count > 0) count++;
		}

		if (count >= 2) {
			store(node);
		}

		return count;
	}
	throw std::logic_error("unreachable code");
}

/// \brief Find the lowest common ancestor of elements on which \p f returns true.
template <typename Fun> splitting_tree & lca(splitting_tree & root, Fun && f) {
	splitting_tree const * store = nullptr;
	lca_impl(root, f, [&store](splitting_tree const & node) { store = &node; });
	return const_cast<splitting_tree &>(*store); // NOTE: this const_cast is safe
}

template <typename Fun> const splitting_tree & lca(const splitting_tree & root, Fun && f) {
	splitting_tree const * store = nullptr;
	lca_impl(root, f, [&store](splitting_tree const & node) { store = &node; });
	return *store;
}

/// \brief Find "all" lca's of elements on which \p f returns true.
/// This can be used to collect all the separating sequences for the subset of states.
template <typename Fun>
std::vector<std::reference_wrapper<const splitting_tree>> multi_lca(const splitting_tree & root,
                                                                    Fun && f) {
	std::vector<std::reference_wrapper<const splitting_tree>> ret;
	lca_impl(root, f, [&ret](splitting_tree const & node) { ret.emplace_back(node); });
	return ret;
}


/// \brief Structure contains options to alter the splitting tree creation.
/// \p check_validity checks whether the transition/output map is injective on the current set of
/// nodes which is being split. Setting this false degenerates to generating pairwise separating
/// sequences. \p assert_minimal_order is used to produce minimal (pairwise) separating sequences.
/// \p cach_succesors is needed by the second step in the LY algorithm and \p randomized randomizes
/// the loops over the alphabet.
struct options {
	bool check_validity;
	bool assert_minimal_order;
	bool cache_succesors;
	bool randomized;
};

const options lee_yannakakis_style = {true, false, true, false};
const options hopcroft_style = {false, false, false, false};
const options min_hopcroft_style = {false, true, false, false};
const options randomized_lee_yannakakis_style = {true, false, true, true};
const options randomized_hopcroft_style = {false, false, false, true};
const options randomized_min_hopcroft_style = {false, true, false, true};

/// \brief The algorithm produces more than just a splitting tree, all results are put here.
struct result {
	result(size_t N) : root(N, 0), successor_cache(), is_complete(N <= 1) {}

	// The splitting tree as described in Lee & Yannakakis
	splitting_tree root;

	// Encodes f_u : depth -> state -> state, where only the depth of u is of importance
	std::vector<std::vector<state>> successor_cache;

	// false <-> no adaptive distinguishing sequence
	bool is_complete;
};

/// \brief Creates a splitting tree by partition refinement.
/// \returns a splitting tree and other calculated structures.
result create_splitting_tree(mealy const & m, options opt, uint_fast32_t random_seed);
