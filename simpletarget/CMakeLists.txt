cmake_minimum_required(VERSION 3.2)
project(simpletarget)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
SET(CMAKE_C_COMPILER "afl-clang-fast")
SET(CMAKE_C_FLAGS "-Ofast")

add_executable(simpletarget simpletarget.c)
add_executable(statemachine statemachine.c)
#file(COPY statemachine.c)

add_executable(statemachine_obfuscated statemachine_obfuscated.c)
set(TIGRESS_HOME /home/mark/apps/tigress-2.0)

macro(tigress input)
    ADD_CUSTOM_COMMAND(TARGET ${input}_obfuscated
            MAIN_DEPENDENCY ${input}.c
            COMMAND ${CMAKE_COMMAND} -E env "TIGRESS_HOME=${TIGRESS_HOME}" "PATH=/bin:/usr/bin:${TIGRESS_HOME}"
            ${TIGRESS_HOME}/tigress
            --Transform=InitEntropy --Functions=main
            --Transform=InitOpaque --Functions=main
            --Transform=EncodeLiterals --Functions=*
            --Transform=EncodeArithmetic --Functions=*
            --Transform=AddOpaque --Functions=*
            --Transform=AntiTaintAnalysis --Functions=*
            --Transform=Flatten --Functions=*
            --Transform=CleanUp
            --out=${input}_obfuscated.c ${CMAKE_CURRENT_SOURCE_DIR}/${input}.c)
endmacro()