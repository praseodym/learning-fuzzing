cmake_minimum_required(VERSION 3.3)
project(RERS2015)

# SET(CMAKE_C_COMPILER "afl-clang-fast")
SET(CMAKE_C_FLAGS "-Ofast -Wno-parentheses-equality")

add_executable(Problem1 Problem1/Problem1.c)
add_executable(Problem2 Problem2/Problem2.c)
add_executable(Problem3 Problem3/Problem3.c)
add_executable(Problem4 Problem4/Problem4.c Problem4/Problem4External.c)
add_executable(Problem5 Problem5/Problem5.c Problem5/Problem5External.c)
add_executable(Problem6 Problem6/Problem6.c Problem6/Problem6External.c)
