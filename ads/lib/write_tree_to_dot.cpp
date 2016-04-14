#include "adaptive_distinguishing_sequence.hpp"
#include "read_mealy.hpp"
#include "splitting_tree.hpp"
#include "write_tree_to_dot.hpp"

#include <algorithm>
#include <fstream>
#include <iostream>

using namespace std;

template <typename T, typename Fun>
void print_vec(ostream & out, const vector<T> & x, const string & d, Fun && f) {
	if (x.empty()) return;

	auto it = begin(x);
	out << f(*it++);
	while (it != end(x)) out << d << f(*it++);
}

struct id_functor {
	id_functor(){}
	template <typename T>
	T operator()(T const & x) const { return x; }
};

static const id_functor id;

void write_splitting_tree_to_dot(const splitting_tree & root, ostream & out_) {
	write_tree_to_dot(root, [](const splitting_tree & node, ostream & out) {
		print_vec(out, node.states, " ", id);
		if (!node.separator.empty()) {
			out << "\\n";
			print_vec(out, node.separator, " ", id);
		}
	}, out_);
}

void write_splitting_tree_to_dot(const splitting_tree & root, const string & filename) {
	ofstream file(filename);
	write_splitting_tree_to_dot(root, file);
}


void write_adaptive_distinguishing_sequence_to_dot(const adaptive_distinguishing_sequence & root, const translation & t, ostream & out_) {
	const auto symbols = create_reverse_map(t.input_indices);
	size_t overflows = 0;
	write_tree_to_dot(root, [&symbols, &overflows](const adaptive_distinguishing_sequence & node, ostream & out) {
		if (!node.w.empty()) {
			print_vec(out, node.w, " ", [&symbols](input x){ return "I" + symbols[x]; });
		} else {
			vector<state> I(node.CI.size());
			transform(begin(node.CI), end(node.CI), begin(I), [](pair<state, state> p){ return p.second; });
			if (I.size() < 7) {
				out << '{';
				print_vec(out, I, ", ", id);
				out << '}';
			} else {
				overflows++;
				out << I.size() << " states\\n{" << I[0] << ", ...}";
			}
		}
	}, out_);
	clog << overflows << " overflows" << endl;
}

void write_adaptive_distinguishing_sequence_to_dot(const adaptive_distinguishing_sequence & root, const translation & t, const string & filename) {
	ofstream file(filename);
	write_adaptive_distinguishing_sequence_to_dot(root, t, file);
}
