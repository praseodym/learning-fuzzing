#include <trie.hpp>

#include <cstdint>
#include <iostream>
#include <sstream>
#include <string>
#include <unordered_map>

using namespace std;

template <typename T>
int func(std::istream & in, std::ostream & out) {
	unordered_map<string, T> translation;
	trie<T> unique_traces;

	string line;
	vector<T> current_word;
	while (getline(in, line)) {
		current_word.clear();
		// TODO: this can be done more efficiently, I guess
		stringstream ss(line);
		string symbol;
		while (ss >> symbol) {
			if (symbol.empty()) continue;
			const auto id = translation.insert(make_pair(symbol, translation.size())).first->second;
			current_word.push_back(id);
		}
		unique_traces.insert(current_word);
	}

	const auto p = total_size(unique_traces);
	out << p.first << '\t' << p.second << '\t' << p.first + p.second << endl;

	return 0;
}

int main(int argc, char * argv[]) {
	// default is an alphabet is maximal 2^32 = 4'294'967'296 symbols
	// this bound does not really matter for speed or space
	return func<uint32_t>(cin, cout);
}
