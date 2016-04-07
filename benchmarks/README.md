# Benchmarks

Uses `cgenerate` tool to build C programs that simulate an input dot file. That C program is obfuscated using Tigress and built using the `afl-clang-fast` compiler that adds AFL instrumentation.

Requirements:
- CMake, Ninja (`apt-get install cmake ninja-build`)
- [Tigress](http://tigress.cs.arizona.edu) on PATH
- `cgenerate` on PATH
- `afl-clang-fast` on PATH

To build: `mkdir -p build && pushd build && cmake -GNinja .. && cmake --build . && popd`

