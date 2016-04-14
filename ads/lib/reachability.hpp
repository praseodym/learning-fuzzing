#pragma once

#include "types.hpp"

struct mealy;

mealy reachable_submachine(const mealy& in, state start);
