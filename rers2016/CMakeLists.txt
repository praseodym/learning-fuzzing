cmake_minimum_required(VERSION 3.3)
project(RERS2016)

SET(CMAKE_C_COMPILER "afl-clang-fast")
SET(CMAKE_C_FLAGS "-Ofast -Wno-parentheses-equality")

add_executable(Problem11 Problem11/Problem11.c)
# add_executable(Problem17 Problem17/Problem17.c)