#include <mealy.hpp>
#include <read_mealy.hpp>

#include <fstream>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int main(int argc, char *argv[]){
	if(argc != 3) return 1;

	const string filename = argv[1];
	const string machince_positions_filename = argv[2];

	// Read machine and its positions
	const auto machine_translation = read_mealy_from_dot(filename);
	const auto & machine = machine_translation.first;
	const auto & translation = machine_translation.second;

	vector<pair<double, double>> positions(machine.graph_size);
	ifstream position_file(machince_positions_filename);
	for(auto & p : positions){
		position_file >> p.first >> p.second;
	}

	// read subalphabet
	cout << "Machine is read, please provide a subalphabet" << endl;
	vector<input> subalphabet;
	string in;
	while(cin >> in){
		const input x = translation.input_indices.at(in);
		subalphabet.push_back(x);
	}

	// visit with subalphabet
	vector<bool> visited(machine.graph_size, false);
	queue<state> work;
	work.push(0);
	while(!work.empty()){
		const state s = work.front();
		work.pop();

		if(visited[s]) continue;
		visited[s] = true;

		for(auto x : subalphabet){
			const state t = apply(machine, s, x).to;
			if(!visited[t]) work.push(t);
		}
	}

	// write to dot
	ofstream out(filename + ".reachable.dot");
	out << "digraph {\n";

	for(state s = 0; s < machine.graph_size; ++s){
		bool is_visited = visited[s];
		out << "\t" << "s" << s << " [";
		out << "color=\"" << (is_visited ? "green" : "red") << "\"" << ", ";
		out << "pos=\"" << positions[s].first << "," << positions[s].second << "\"";
		out << "]\n";
	}

	for(state s = 0; s < machine.graph_size; ++s){
		vector<bool> should_ignore(machine.graph_size, false);
		should_ignore[s] = true;
		for(input i = 0; i < machine.input_size; ++i){
			const auto t = apply(machine, s, i).to;
			if(should_ignore[t]) continue;
			out << "\t" << "s" << s << " -> " << "s" << t << "\n";
			should_ignore[t] = true;
		}
	}

	out << "}" << endl;
}

