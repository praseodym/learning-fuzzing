#pragma once

#include <functional>
#include <ostream>
#include <queue>
#include <utility>

// Generic printer for tree
template <typename T, typename NodeString>
void write_tree_to_dot(const T & tree, NodeString && node_string, std::ostream & out) {
	using namespace std;
	out << "digraph {\n";

	// breadth first
	int global_id = 0;
	queue<pair<int, reference_wrapper<const T>>> work;
	work.push({global_id++, tree});
	while (!work.empty()) {
		const auto id = work.front().first;
		const T & node = work.front().second;
		work.pop();

		out << "\n\ts" << id << " [label=\"";
		node_string(node, out);
		out << "\"];\n";

		for (auto && c : node.children) {
			int new_id = global_id++;
			out << "\ts" << id << " -> "
			    << "s" << new_id << ";\n";
			work.push({new_id, c});
		}
	}

	out << "}" << endl;
}


// Specialized printing for splitting trees and dist seqs
struct splitting_tree;
void write_splitting_tree_to_dot(const splitting_tree & root, std::ostream & out);
void write_splitting_tree_to_dot(const splitting_tree & root, const std::string & filename);

struct adaptive_distinguishing_sequence;
struct translation;
void write_adaptive_distinguishing_sequence_to_dot(const adaptive_distinguishing_sequence & root, const translation & t, std::ostream & out);
void write_adaptive_distinguishing_sequence_to_dot(const adaptive_distinguishing_sequence & root, const translation & t, const std::string & filename);
