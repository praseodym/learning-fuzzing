cmake_minimum_required(VERSION 3.2)
project(simpletarget)

SET(CMAKE_C_COMPILER "afl-clang-fast")
SET(CMAKE_C_FLAGS "-Ofast")

add_executable(simpletarget simpletarget.c)
add_executable(simplestatemachine simplestatemachine.c)

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
            --out=${input}_obfuscated.c ${CMAKE_CURRENT_SOURCE_DIR}/${input}.c
    )
endmacro()

tigress(simplestatemachine)
add_executable(simplestatemachine_obfuscated simplestatemachine_obfuscated.c)
