#include <adaptive_distinguishing_sequence.hpp>
#include <read_mealy.hpp>
#include <splitting_tree.hpp>
#include <write_tree_to_dot.hpp>

#include <iostream>
#include <random>
#include <string>

using namespace std;

int main(int argc, char * argv[]) {
	if (argc != 3) return 1;
	const string filename = argv[1];

	const string randomized_str = argv[2];
	const bool randomized = randomized_str == "randomized";

	const auto machine_and_translation = read_mealy_from_dot(filename);
	const auto & machine = machine_and_translation.first;
	const auto & translation = machine_and_translation.second;

	const auto options = randomized ? randomized_lee_yannakakis_style : lee_yannakakis_style;

	random_device rd;
	const auto tree = create_splitting_tree(machine, options, rd());
	const auto sequence = create_adaptive_distinguishing_sequence(tree);

	write_splitting_tree_to_dot(tree.root, filename + ".tree");
	write_adaptive_distinguishing_sequence_to_dot(sequence, translation, filename + ".seq");
}
