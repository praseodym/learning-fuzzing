#include <trie.hpp>

#include <algorithm>
#include <chrono>
#include <iostream>
#include <random>
#include <set>
#include <stdexcept>
#include <vector>

using namespace std;

using word = vector<size_t>;

static void check(bool r) {
	if (!r) throw runtime_error("error in trie");
}

static void test() {
	word w1 = {1, 2, 3};
	word w2 = {2, 3};
	word w3 = {1, 2};
	word w4 = {5, 5, 5};
	word w5 = {5, 5, 3};
	word w6 = {5, 5, 3, 1};

	trie<unsigned> t;
	check(t.insert(w1));
	check(!t.insert(w1));
	check(t.insert(w2));
	check(!t.insert(w3));
	check(t.insert(w4));
	check(t.insert(w5));
	check(t.insert(w6));

	check(flatten(t).size() == 4);

	t.for_each([](auto&& w) {
		for (auto&& i : w) cout << i << ' ';
		cout << '\n';
	});
	cout << endl;
}

static void performance() {
	vector<word> corpus(1000000);

	std::random_device rd;
	std::mt19937 generator(rd());
	uniform_int_distribution<int> unfair_coin(0, 3);
	uniform_int_distribution<size_t> symbol(0, 50 - 1);

	generate(begin(corpus),
	         end(corpus),
	         [&] {
		         word w;
		         while (unfair_coin(generator) || w.empty()) {
			         w.push_back(symbol(generator));
		         }
		         return w;
		     });

	size_t size = corpus.size();
	size_t total_size
	    = accumulate(begin(corpus),
	                 end(corpus),
	                 0ul,
	                 [](auto l, auto&& r) { return l + r.size(); });

	cout << size << " words\n";
	cout << total_size << " symbols\n";
	cout << total_size / double(size) << " average word length\n";
	cout << endl;

	using clock = std::chrono::high_resolution_clock;
	using time = std::chrono::time_point<clock>;
	using seconds = std::chrono::duration<double>;

	auto t_start = clock::now();
	trie<unsigned> t;
	for (auto&& w : corpus) t.insert(w);
	auto t_end = clock::now();

	auto s_start = clock::now();
	set<word> s;
	for (auto&& w : corpus) s.insert(w);
	auto s_end = clock::now();

	size_t trie_size = flatten(t).size();
	size_t set_size = s.size();
	cout << trie_size << " words in the trie\n";
	cout << trie_size / double(size) << " ratio\n";
	cout << seconds(t_end - t_start).count() << " seconds\n";
	cout << endl;

	cout << set_size << " words in the set\n";
	cout << set_size / double(size) << " ratio\n";
	cout << seconds(s_end - s_start).count() << " seconds\n";
	cout << endl;
}

int main() {
	test();
	performance();
}
