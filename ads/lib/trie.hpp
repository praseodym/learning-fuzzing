#pragma once

#include <boost/optional.hpp>

#include <stack>
#include <stdexcept>
#include <utility>
#include <vector>

///
/// \brief A Trie datastructure used to remove prefixes in a set of words
///
/// The datastructure only works for words over integral unsigned types. In principle the symbols
/// can be unbounded, however having very large symbols degrades the performance a lot. Some random
/// testing shows that for symbols <= 50 the performance is similar to std::set (which is solving a
/// different problem).
///
/// Tests : 1M words, avg words length 4 (geometric dist.), alphabet 50 symbols
/// trie reduction 58% in 1.15s
/// set  reduction 49% in 0.92s
///
/// I did not implement any iterators, as those are quite hard to get right.
/// There are, however, "internal iterators" exposed as a for_each() member
/// function (if only we had coroutines already...)
///
template <typename T> struct trie {
	static_assert(std::is_integral<T>::value && std::is_unsigned<T>::value, "");

	/// \brief Inserts a word (given by iterators \p begin and \p end)
	/// \returns true if the element was inserted, false if already there
	template <typename Iterator> bool insert(Iterator && begin, Iterator && end) {
		if (begin == end) return false;

		size_t i = *begin++;
		if (i >= branches.size()) branches.resize(i + 1);

		auto & b = branches[i];
		if (b) return b->insert(begin, end);

		b = trie();
		b->insert(begin, end);
		return true;
	}

	/// \brief Inserts a word given as range \p r
	/// \returns true if the element was inserted, false if already there
	template <typename Range> bool insert(Range const & r) { return insert(begin(r), end(r)); }

	/// \brief Applies \p function to all word (not to the prefixes)
	template <typename Fun> void for_each(Fun && function) const {
		std::vector<T> word;
		return for_each_impl(std::forward<Fun>(function), word);
	}

	/// \brief Empties the complete set
	void clear() { branches.clear(); }

  private:
	template <typename Fun> void for_each_impl(Fun && function, std::vector<T> & word) const {
		size_t count = 0;
		for (T i = 0; i < branches.size(); ++i) {
			auto const & b = branches[i];
			if (b) {
				++count;
				word.push_back(i);
				b->for_each_impl(function, word);
				word.resize(word.size() - 1);
			}
		}

		if (count == 0) {
			const auto & cword = word;
			function(cword); // we don't want function to modify word
			return;
		}
	}

	std::vector<boost::optional<trie>> branches;
};

/// \brief Flattens a trie \p t
/// \returns an array of words (without the prefixes)
template <typename T> std::vector<std::vector<T>> flatten(trie<T> const & t) {
	std::vector<std::vector<T>> ret;
	t.for_each([&ret](std::vector<T> const & w) { ret.push_back(w); });
	return ret;
}

/// \brief Returns size and total sum of symbols
template <typename T> std::pair<size_t, size_t> total_size(trie<T> const & t) {
	size_t count = 0;
	size_t total_count = 0;
	t.for_each([&count, &total_count](std::vector<T> const &  w) {
		++count;
		total_count += w.size();
	});
	return {count, total_count};
}
