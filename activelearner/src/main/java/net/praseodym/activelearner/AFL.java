package net.praseodym.activelearner;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Bridge to our AFL fuzzer library (libafl), using JNI.
 */
@Component
@Lazy
public class AFL {
    /*
      Load the native AFL library.
     */
    static {
        System.loadLibrary("afl");
    }

    /**
     * "Hello World" test method, prints to stdout.
     */
    public native void hello();

    /**
     * Initialise AFL library with an input and output directory as well as the location of the target. Also executes
     * a number of tests on the target (e.g. whether the fork server and coverage bitmap work as expected) as well
     * as some calibration steps to determine the variation in execution time of the target (used to detect hangs).
     *
     * @param inputDirectory  AFL input directory, must contain at least a single valid test case used for
     *                        initialisation tests and calibration
     * @param outputDirectory AFL output directory, where AFL will save test cases it deems "interesting"
     * @param target          location of the target binary, compiled with AFL instrumentation code, and
     *                        optional program arguments; use '@@' as a placeholder for target file
     */
    public native void pre(String inputDirectory, String outputDirectory, String... target);

    /**
     * Shut down AFL, including the forkserver.
     */
    public native void post();

    /**
     * Run the target with our given test case as input, and return the output.
     *
     * @param testcase the test case to be used as an input to the target program
     * @return output produced by the target program
     */
    @Cacheable("runs")
    public native byte[] run(byte[] testcase);

    /**
     * Get the number of "interesting" test cases discovered in this run (called queued_discovered by AFL internally).
     *
     * @return the number of test cases discovered in this run
     */
    public native int getQueuedDiscovered();

    /**
     * Get the coverage bitmap from the previous run, which is essentially a trace of all edges (code branches)
     * covered by the test case we just ran.
     *
     * @return the coverage (trace) bitmap for the previous run.
     */
    public native byte[] getTraceBitmap();

    public static List<String> friendlyBitmap(byte[] traceBitmap) {
        List<String> out = new ArrayList<>();
        for (int i = 0, traceBitmapLength = traceBitmap.length; i < traceBitmapLength; i++) {
            byte b = traceBitmap[i];
            if (b != 0)
                out.add(String.format("%06d", i));
//                out.add(String.format("%06d:%d", i, b));
        }
        return out;
    }

    public static byte[] getNewTraceBits(byte[] prev, byte[] cur) {
        assert prev.length == cur.length;

        int traceBitmapLength = prev.length;
        byte[] out = new byte[traceBitmapLength];

        for (int i = 0; i < traceBitmapLength; i++) {
//            out[i] = prev[i] == cur[i] ? 0 : cur[i];
//            out[i] = (byte) (cur[i] - prev[i]);
            out[i] = prev[i] > 0 ? 0 : cur[i];
        }

        return out;
    }
}
