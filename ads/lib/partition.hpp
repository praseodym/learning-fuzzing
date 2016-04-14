#pragma once

#include <cassert>
#include <list>
#include <numeric>
#include <stdexcept>
#include <type_traits>
#include <vector>

template <typename Iterator, typename Fun>
std::list<std::list<typename Iterator::value_type>>
partition_(Iterator b, Iterator e, Fun&& function, size_t output_size) {
	using namespace std;
	using T = typename decay<decltype(*b)>::type;

	list<T> elements(b, e);

	list<list<T>> blocks;
	using ref = typename list<list<T>>::iterator;
	vector<ref> A(output_size);

	auto it = begin(elements);
	auto ed = end(elements);
	while (it != ed) {
		const auto current = it++;
		const auto y = function(*current);
		if (y >= output_size) throw runtime_error("Output is too big");

		auto& ar = A[y];
		if (ar == ref{}) {
			ar = blocks.insert(blocks.end(), list<T>{});
		}
		ar->splice(ar->end(), elements, current);
	}

	return blocks;
}
