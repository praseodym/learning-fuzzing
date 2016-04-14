#include <docopt.h>

#include <cstdint>
#include <cmath>
#include <fstream>
#include <future>
#include <iostream>
#include <stdexcept>
#include <vector>

using namespace std;

static const char USAGE[] =
    R"(Generate a statistical learning graph from multiple runs

    Usage:
      learning_graph <file> ...

    Options:
      -h, --help       Show this screen
      --version        Show version
)";

struct datapoint {
	uint64_t states;
	uint64_t learning_queries;
	uint64_t learning_inputs;
	uint64_t testing_queries;
	uint64_t testing_inputs;
};

using dataset = vector<datapoint>;

static void accumulate_dataset(dataset & ds) {
	for (size_t i = 0; i < ds.size() - 1; ++i) {
		ds[i + 1].learning_queries += ds[i].learning_queries;
		ds[i + 1].learning_inputs += ds[i].learning_inputs;
		ds[i + 1].testing_queries += ds[i].testing_queries;
		ds[i + 1].testing_inputs += ds[i].testing_inputs;
	}
}

int main(int argc, char * argv[]) {
	const auto args = docopt::docopt(USAGE, {argv + 1, argv + argc}, true, __DATE__ __TIME__);

	vector<future<dataset>> dataset_futures;
	for (auto const & filename : args.at("<file>").asStringList()) {
		dataset_futures.emplace_back(async([filename] {
			fstream file(filename);
			if (!file) throw runtime_error("Could not open file " + filename);

			dataset s;
			datapoint p;
			while (file >> p.states >> p.learning_queries >> p.learning_inputs >> p.testing_queries
			       >> p.testing_inputs) {
				s.push_back(p);
			}

			accumulate_dataset(s);

			return s;
		}));
	}

	vector<dataset> datasets;
	clog << "datasets";
	for (auto & f : dataset_futures) {
		datasets.emplace_back(f.get());
		clog << ' ' << datasets.back().size();
		if (datasets.back().size() == 0) throw runtime_error("empty dataset");
	}
	clog << endl;

	for (auto const & set : datasets) {
		for (auto const & p : set) {
			const auto v
			    = p.learning_queries + p.learning_inputs + p.testing_queries + p.testing_inputs;
			cout << p.states << '\t' << v << endl;
		}
	}
}
