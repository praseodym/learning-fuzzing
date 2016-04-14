#pragma once

#include "mealy.hpp"
#include "separating_family.hpp"
#include "transfer_sequences.hpp"
#include "types.hpp"

#include <functional>

struct writer {
	std::function<void(word)> apply; // store a part of a word
	std::function<bool(void)> reset; // flush, if flase is returned, testing is stopped
};

/// \brief Performs exhaustive tests with \p k_max extra states (harmonized, e.g. HSI / DS)
void test(mealy const & specification, transfer_sequences const & prefixes,
          separating_family const & separating_family, size_t k_max, writer const & output);

/// \brief Performs random non-exhaustive tests for more states (harmonized, e.g. HSI / DS)
void randomized_test(mealy const & specification, transfer_sequences const & prefixes,
                     separating_family const & separating_family, size_t min_k, size_t rnd_length,
                     writer const & output, uint_fast32_t random_seed);

void randomized_test_suffix(mealy const & specification, transfer_sequences const & prefixes,
                            separating_family const & separating_family, size_t min_k,
                            size_t rnd_length, writer const & output, uint_fast32_t random_seed);

/// \brief returns a writer which simply writes everything to cout (via inputs)
writer default_writer(const std::vector<std::string> & inputs);
