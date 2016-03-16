cmake_minimum_required(VERSION 3.3)
project(afl)

set(VERSION 2.00b)
set(PREFIX /usr/local)
set(JAVA_HOME /Library/Java/JavaVirtualMachines/jdk1.8.0_74.jdk/Contents/Home)
set(BIN_PATH $(PREFIX)/bin)
set(HELPER_PATH $(PREFIX)/lib/afl)
set(DOC_PATH $(PREFIX)/share/doc/afl)
set(MISC_PATH $(PREFIX)/share/afl)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O3 -funroll-loops -D_FORTIFY_SOURCE=2 -g -Wno-pointer-sign -ldl -z execstack \
    -DVERSION=\\\"${VERSION}\\\" -DAFL_PATH=\\\"$(HELPER_PATH)\\\" \
    -DDOC_PATH=\\\"$(DOC_PATH)\\\" -DBIN_PATH=\\\"$(BIN_PATH)\\\"")
set(CMAKE_MODULE_LINKER_FLAGS "-z noexecstack ${CMAKE_MODULE_LINKER_FLAGS}")

include_directories(${JAVA_HOME}/include ${JAVA_HOME}/include/linux)

set(COMM_HDR
    alloc-inl.h
    config.h
    debug.h
    types.h)

add_executable(afl-gcc afl-gcc.c ${COMM_HDR})
add_executable(afl-as afl-as.c afl-as.h ${COMM_HDR})
add_executable(afl-fuzz afl-fuzz.c ${COMM_HDR})
add_executable(afl-showmap afl-showmap.c ${COMM_HDR})
add_executable(afl-tmin afl-tmin.c ${COMM_HDR})
add_executable(afl-gotcpu afl-gotcpu.c ${COMM_HDR})
add_executable(afl-analyze afl-analyze.c ${COMM_HDR})
#add_executable(test-instr test-instr.c)

add_library(afl MODULE afl.c net_praseodym_activelearner_AFL.h ${COMM_HDR})
add_executable(afl_test afl.c net_praseodym_activelearner_AFL.h ${COMM_HDR})

#set_target_properties(libforkserver PROPERTIES SUFFIX ".so")