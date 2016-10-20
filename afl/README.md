# AFL with libafl

Dependencies (Debian):

    apt-get install cmake build-essential

Build with CMake:

```bash
mkdir build
cd build
cmake ..
cmake --build .
```

Upgrading to new versions of AFL:

```bash
diff -u afl-fuzz.c afl.c > libafl.patch
# extract http://lcamtuf.coredump.cx/afl/releases/afl-latest.tgz in this directory
cp afl-fuzz.c afl.c
patch afl.c libafl.patch
# update afl-jni.c with updates made in afl-fuzz.c main function
# update CMakeLists.txt with new version
````