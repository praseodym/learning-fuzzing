#include "read_mealy.hpp"
#include "mealy.hpp"

#include <boost/algorithm/string/trim.hpp>

#include <cassert>
#include <fstream>
#include <sstream>
#include <stdexcept>
#include <string>

using namespace std;

static string easy_substr(string const & s, size_t begin, size_t end){
	return s.substr(begin, end - begin);
}

mealy read_mealy_from_txt(std::istream & in, bool check) {
	mealy m;

	state max_state = 0;
	input max_input = 0;
	output max_output = 0;

	string line;
	while (getline(in, line)) {
		state from, to;
		input i;
		output o;
		string separator;

		stringstream ss(line);
		ss >> from >> separator >> i >> separator >> o >> separator >> to;

		if (from >= max_state) max_state = from + 1;
		if (to >= max_state) max_state = to + 1;
		if (i >= max_input) max_input = i + 1;
		if (o >= max_output) max_output = o + 1;

		if (defined(m, from, i)) throw runtime_error("Nondeterministic machine");

		m.graph.resize(max_state);
		auto & v = m.graph[from];
		v.resize(max_input);
		v[i] = mealy::edge(to, o);

		assert(defined(m, from, i));
	}

	m.graph_size = max_state;
	m.input_size = max_input;
	m.output_size = max_output;

	if (m.graph_size == 0) throw runtime_error("Empty state set");
	if (m.input_size == 0) throw runtime_error("Empty input set");
	if (m.output_size == 0) throw runtime_error("Empty output set");
	if (check && !is_complete(m)) throw runtime_error("Partial machine");
	return m;
}

mealy read_mealy_from_txt(const std::string & filename, bool check) {
	std::ifstream file(filename);
	return read_mealy_from_txt(file, check);
}

mealy read_mealy_from_dot(std::istream & in, translation & t, bool check){
	mealy m;

	std::unordered_map<std::string, state> state_indices;
	state max_state = 0;

	string line;
	while(getline(in, line)){
		using boost::algorithm::trim_copy;
		const auto npos = std::string::npos;

		if(line.find("}") != string::npos) break;

		// parse states
		const auto arrow_pos = line.find("->");
		const auto bracket_pos = line.find('[');
		if(arrow_pos == npos || bracket_pos == npos) continue;

		const auto lh = trim_copy(easy_substr(line, 0, arrow_pos));
		const auto rh = trim_copy(easy_substr(line, arrow_pos+2, bracket_pos));

		// parse input/output
		const auto quote1_pos = line.find('\"', bracket_pos);
		const auto slash_pos = line.find('/', quote1_pos);
		const auto quote2_pos = line.find('\"', slash_pos);
		if(quote1_pos == npos || slash_pos == npos || quote2_pos == npos) continue;

		const auto input = trim_copy(easy_substr(line, quote1_pos+1, slash_pos));
		const auto output = trim_copy(easy_substr(line, slash_pos+1, quote2_pos));

		// make fresh indices, if needed
		if(state_indices.count(lh) < 1) state_indices[lh] = max_state++;
		if(state_indices.count(rh) < 1) state_indices[rh] = max_state++;
		if(t.input_indices.count(input) < 1) t.input_indices[input] = t.max_input++;
		if(t.output_indices.count(output) < 1) t.output_indices[output] = t.max_output++;

		if(defined(m, state_indices[lh], t.input_indices[input]))
			throw runtime_error("Nondeterministic machine");

		// add edge
		m.graph.resize(max_state);
		auto & v = m.graph[state_indices[lh]];
		v.resize(t.max_input);
		v[t.input_indices[input]] = mealy::edge(state_indices[rh], t.output_indices[output]);
	}

	m.graph_size = max_state;
	m.input_size = t.max_input;
	m.output_size = t.max_output;

	if(m.graph_size == 0) throw runtime_error("Empty state set");
	if(m.input_size == 0) throw runtime_error("Empty input set");
	if(m.output_size == 0) throw runtime_error("Empty output set");
	if(check && !is_complete(m)) throw runtime_error("Partial machine");
	return m;
}


mealy read_mealy_from_dot(const string & filename, translation & t, bool check){
	ifstream file(filename);
	return read_mealy_from_dot(file, t, check);
}


std::pair<mealy, translation> read_mealy_from_dot(istream & in, bool check){
	translation t;
	const auto m = read_mealy_from_dot(in, t, check);
	return {move(m), move(t)};
}


std::pair<mealy, translation> read_mealy_from_dot(const string & filename, bool check){
	translation t;
	const auto m = read_mealy_from_dot(filename, t, check);
	return {move(m), move(t)};
}


template <typename T>
std::vector<std::string> create_reverse_map_impl(std::unordered_map<std::string, T> const & indices) {
	std::vector<std::string> ret(indices.size());
	for (auto && p : indices) {
		ret[p.second] = p.first;
	}
	return ret;
}

std::vector<string> create_reverse_map(const std::unordered_map<string, input> & indices) {
	return create_reverse_map_impl(indices);
}

#if 0 // Note: input and output are equal types, so this would be a redecl
std::vector<string> create_reverse_map(const std::map<string, output> & indices) {
	return create_reverse_map_impl(indices);
}
#endif

translation create_translation_for_mealy(const mealy & m) {
	translation t;
	t.max_input = m.input_size;
	t.max_output = m.output_size;

	for (input i = 0; i < t.max_input; ++i) {
		t.input_indices[to_string(i)] = i;
	}

	for (output o = 0; o < t.max_output; ++o) {
		t.output_indices[to_string(o)] = o;
	}

	return t;
}
