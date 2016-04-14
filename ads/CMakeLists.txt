project(ads)
cmake_minimum_required(VERSION 2.8)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y")

find_package(Boost REQUIRED COMPONENTS)
include_directories(SYSTEM ${Boost_INCLUDE_DIRS})
include_directories(SYSTEM "${PROJECT_SOURCE_DIR}/contrib/docopt.cpp")
set(libs ${libs} ${Boost_LIBRARIES} docopt_s)

add_subdirectory("contrib/docopt.cpp")
add_subdirectory("lib")
add_subdirectory("src")

# file(GLOB resources "resources/*")
# file(COPY ${resources} DESTINATION ${CMAKE_BINARY_DIR})
