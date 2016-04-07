cmake_minimum_required(VERSION 3.5)
project(benchmarks)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
SET(CMAKE_C_COMPILER "afl-clang-fast")
SET(CMAKE_C_FLAGS "-Ofast")

macro(cgenerate input)
    add_custom_command(
        OUTPUT ${input}.c
        DEPENDS ${input}.dot
        COMMAND
            cgenerate < ${CMAKE_CURRENT_SOURCE_DIR}/${input}.dot > ${input}.c
    )
endmacro()

macro(tigress input)
    add_custom_command(
        OUTPUT ${input}_obfuscated.c
        DEPENDS ${input}.c
        COMMAND
        tigress
            --Transform=InitEntropy --Functions=main
            --Transform=InitOpaque --Functions=main
            --Transform=EncodeLiterals --Functions=*
            --Transform=EncodeArithmetic --Functions=*
            --Transform=AddOpaque --Functions=*
            --Transform=AntiTaintAnalysis --Functions=*
            --Transform=Flatten --Functions=*
            --Transform=CleanUp
            --out=${input}_obfuscated.c ${input}.c
    )
endmacro()

macro(build_benchmark input)
    cgenerate(${input})
    tigress(${input})
    add_executable(${input}_plain ${input}.c)
    add_executable(${input}_obfuscated ${input}_obfuscated.c)
endmacro(build_benchmark)

build_benchmark(simpletarget)
build_benchmark(esm)