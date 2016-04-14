#include <docopt.h>

#include <boost/range/algorithm/sort.hpp>
#include <boost/range/algorithm/unique.hpp>

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
      learning_graph [options] <file> ...

    Options:
      --testing_only   Only count the figures for testing
      --learning_only  Only count the figures for learning
      --accumulate     Accumulates the data
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

template <typename C, typename S>
void print_quantiles(C const & container, S && selector, ostream & out) {
	const auto index_weight = [&](double p) -> pair<size_t, double> {
		auto index = (p * (container.size() - 1));
		return {floor(index), 1 - fmod(index, 1)};
	};

	auto sorted_container = container;
	sort(sorted_container.begin(), sorted_container.end(),
	     [&](auto const & l, auto const & r) { return selector(l) < selector(r); });
	out << selector(sorted_container.front()) << '\t';

	const auto i25 = index_weight(0.25);
	out << i25.second * selector(sorted_container[i25.first])
	           + (1 - i25.second) * selector(sorted_container[i25.first + 1])
	    << '\t';

	const auto i50 = index_weight(0.50);
	out << i50.second * selector(sorted_container[i50.first])
	           + (1 - i50.second) * selector(sorted_container[i50.first + 1])
	    << '\t';

	const auto i75 = index_weight(0.75);
	out << i75.second * selector(sorted_container[i75.first])
	           + (1 - i75.second) * selector(sorted_container[i75.first + 1])
	    << '\t';

	out << selector(sorted_container.back());
}

auto all(datapoint const & p) {
	return p.learning_queries + p.learning_inputs + p.testing_queries + p.testing_inputs;
}
auto testing(datapoint const & p) {
	return p.testing_queries + p.testing_inputs;
}
auto learning(datapoint const & p) {
	return p.learning_queries + p.learning_inputs;
}

int main(int argc, char * argv[]) {
	const auto args = docopt::docopt(USAGE, {argv + 1, argv + argc}, true, __DATE__ __TIME__);

	const auto field = args.at("--testing_only").asBool() ? &testing : args.at("--learning_only").asBool() ? &learning : &all;

	vector<future<dataset>> dataset_futures;
	for (auto const & filename : args.at("<file>").asStringList()) {
		dataset_futures.emplace_back(async([filename, &args] {
			fstream file(filename);
			if (!file) throw runtime_error("Could not open file " + filename);

			dataset s;
			datapoint p;
			while (file >> p.states >> p.learning_queries >> p.learning_inputs >> p.testing_queries
			       >> p.testing_inputs) {
				s.push_back(p);
			}

			if (args.at("--accumulate").asBool())
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

	vector<size_t> state_values;
	state_values.reserve(datasets[0].size());

	// lazy way of doing things
	for (auto && ds : datasets)
		for (auto && x : ds) state_values.push_back(x.states);

	sort(state_values.begin(), state_values.end());
	state_values.erase(unique(state_values.begin(), state_values.end()), state_values.end());

	// id(state_value) -> [total query size]
	vector<vector<double>> data;
	data.reserve(state_values.size());

	// we keep track of the current timestamp for the different datasets
	struct it_pair {
		dataset::const_iterator current, next, end;
	};
	vector<it_pair> iterators(datasets.size());
	for (size_t i = 0; i < datasets.size(); ++i)
		iterators[i] = {datasets[i].begin(), datasets[i].begin(), datasets[i].end()};

	for (auto const & state : state_values) {
		data.push_back({});
		for (auto & it : iterators) {
			while (it.next != it.end && it.next->states < state) {
				it.current = it.next;
				it.next++;
			}

			// one run stopped prior to the others, we can skip it
			if (it.next == it.end) continue;

			// one run started earlier, we can skip it
			if (it.current->states > state) continue;

			// if we're spot on, update current
			if (it.next->states == state) it.current = it.next;

			const auto v2 = field(*it.next);
			const auto v1 = field(*it.current);
			const auto ratio
			    = it.next->states == state
			          ? 1.0
			          : (state - it.current->states) / double(it.next->states - it.current->states);
			const auto v = ratio * v2 + (1.0 - ratio) * v1;
			data.back().push_back(v);
		}
	}

	for (auto & v : data) {
		sort(v.begin(), v.end());
	}

	cout << "s\tmin\tQ1\t\tQ2\tQ3\tmax" << endl;
	for (size_t i = 0; i < state_values.size(); ++i) {
		auto v = data[i];
		if (v.empty()) continue;
		cout << state_values[i] << '\t';
		print_quantiles(v, [](auto const & x) { return x; }, cout);
		cout << endl;
	}
}
