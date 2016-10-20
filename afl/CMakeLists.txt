cmake_minimum_required(VERSION 3.3)
project(afl)

set(VERSION 2.35b)
set(PREFIX /usr/local)
set(JAVA_HOME $ENV{JAVA_HOME})
set(BIN_PATH $(PREFIX)/bin)
set(HELPER_PATH $(PREFIX)/lib/afl)
set(DOC_PATH $(PREFIX)/share/doc/afl)
set(MISC_PATH $(PREFIX)/share/afl)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O3 -funroll-loops -D_FORTIFY_SOURCE=2 -g -Wno-pointer-sign -ldl -z execstack \
    -DVERSION=\\\"${VERSION}\\\" -DAFL_PATH=\\\"$(HELPER_PATH)\\\" \
    -DDOC_PATH=\\\"$(DOC_PATH)\\\" -DBIN_PATH=\\\"$(BIN_PATH)\\\"")
set(CMAKE_MODULE_LINKER_FLAGS "-z noexecstack ${CMAKE_MODULE_LINKER_FLAGS}")

include_directories(${JAVA_HOME}/include ${JAVA_HOME}/include/linux)

add_executable(afl-analyze afl-analyze.c)
add_executable(afl-as afl-as.c)
add_executable(afl-fuzz afl-fuzz.c)
add_executable(afl-gcc afl-gcc.c)
add_executable(afl-gotcpu afl-gotcpu.c)
add_executable(afl-showmap afl-showmap.c)
add_executable(afl-tmin afl-tmin.c)
add_executable(test-instr test-instr.c)

set_source_files_properties(afl.c PROPERTIES COMPILE_FLAGS -DAFL_LIB)
add_library(afl MODULE afl.c)