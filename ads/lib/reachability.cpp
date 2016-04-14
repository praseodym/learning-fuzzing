#include "reachability.hpp"
#include "mealy.hpp"

#include <map>
#include <queue>
#include <vector>

using namespace std;

mealy reachable_submachine(const mealy& in, state start) {
	using state_out = state;
	state_out max_state = 0;
	map<state, state_out> new_state;
	vector<bool> visited(in.graph_size, false);

	mealy out;

	queue<state> work;
	work.push(start);
	while (!work.empty()) {
		state s = work.front();
		work.pop();

		if (visited[s]) continue;
		visited[s] = true;

		if (!new_state.count(s)) new_state[s] = max_state++;
		state_out s2 = new_state[s];

		for (input i = 0; i < in.input_size; ++i) {
			const auto ret = apply(in, s, i);
			const output o = ret.out;
			const state t = ret.to;

			if (!new_state.count(t)) new_state[t] = max_state++;
			state_out t2 = new_state[t];

			if (out.graph.size() < max_state) out.graph.resize(max_state);
			if (out.graph[s2].size() < in.input_size) out.graph[s2].resize(in.input_size);
			out.graph[s2][i] = mealy::edge(t2, o);

			if (!visited[t]) work.push(t);
		}
	}

	out.graph_size = max_state;
	out.input_size = in.input_size;
	out.output_size = in.output_size;

	if(out.graph_size == 0) throw runtime_error("Empty state set");
	if(out.input_size == 0) throw runtime_error("Empty input set");
	if(out.output_size == 0) throw runtime_error("Empty output set");
	if(!is_complete(out)) throw runtime_error("Partial machine");

	return out;
}
