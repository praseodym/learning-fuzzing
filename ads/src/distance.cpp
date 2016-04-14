#include <mealy.hpp>
#include <read_mealy.hpp>

#include <iostream>
#include <fstream>
#include <sstream>
#include <queue>
#include <set>

using namespace std;

int main(int argc, char * argv[]) {
	if (argc != 4) {
		cerr << "usage: distance <file1> <file2> <log>" << endl;
		return 1;
	}

	translation t;
	const auto m1 = read_mealy_from_dot(argv[1], t);
	const auto m2 = read_mealy_from_dot(argv[2], t);

	if (m1.input_size != m2.input_size) throw runtime_error("different alphabets!");

	const auto log = [&] {
		vector<word> log_;
		string line;
		ifstream log_file(argv[3]);
		while (std::getline(log_file, line)) {
			log_.emplace_back();
			word & w = log_.back();
			stringstream ss(line);
			string symbol;
			while (ss >> symbol) {
				w.push_back(t.input_indices.at(symbol));
			}
		}
		return log_;
	}();

	// can be vector of bool, but meh
	set<pair<state, state>> visited;
	queue<pair<state, state>> work;

	size_t current_length_work = 0;
	size_t next_length_work = 0;
	size_t current_length = 0;
	size_t current_counterexamples = 0;

	const auto push = [&](auto s1, auto s2) {
		next_length_work++;
		work.push({s1, s2});
	};

	const auto pop = [&]() {
		const auto p = work.front();
		work.pop();
		current_length_work--;
		return p;
	};

	const auto check_length = [&]() {
		if (current_length_work == 0) {
			if (current_counterexamples != 0) {
				cout << "(-" << current_length << ", " << current_counterexamples << ")" << endl;
				exit(0);
			}

			current_length_work = next_length_work;
			current_length++;
		}
	};

	push(0, 0);
	for(auto const & l : log){
		state s1 = 0;
		state s2 = 0;
		for(auto const & i : l){
			s1 = apply(m1, s1, i).to;
			s2 = apply(m2, s2, i).to;
			push(s1, s2);
		}
	}

	while (!work.empty()) {
		check_length();

		const auto p = pop();
		const auto s1 = p.first;
		const auto s2 = p.second;

		if (visited.count(p)) continue;
		visited.insert(p);

		for (input i = 0; i < m1.input_size; ++i) {
			auto q1 = apply(m1, s1, i);
			auto q2 = apply(m2, s2, i);

			if (q1.out != q2.out) {
				current_counterexamples++;
			}

			auto new_p = make_pair(q1.to, q2.to);
			if (visited.count(new_p)) continue;
			push(q1.to, q2.to);
		}
	}

	cout << "distance = 0" << endl;
}
