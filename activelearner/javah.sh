#!/bin/zsh
BUILD_DIR=build/classes/main

set -ex
./gradlew build
pushd $BUILD_DIR
javah net.praseodym.activelearner.ForkServer
popd
mv $BUILD_DIR/net_praseodym_activelearner_ForkServer.h ../afl/