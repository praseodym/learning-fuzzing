
file(GLOB_RECURSE sources "*.cpp")
file(GLOB_RECURSE headers "*.hpp")

set(libs)

add_library(common ${headers} ${sources})
target_link_libraries(common ${libs})
target_include_directories(common PUBLIC ".")
