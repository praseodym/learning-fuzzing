package net.praseodym.activelearner;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * afl native binding
 */
@Component
@Lazy
public class AFL {
    static {
        System.loadLibrary("afl");
    }

    public native void hello();

    public native void pre(String inputDirectory, String outputDirectory, String target);

    @Cacheable("runs")
    public native byte[] run(byte[] testcase);

    public native void post();

    public native int getQueuedDiscovered();

    public native byte[] getTraceBitmap();

    public static void main(String[] args) {
        AFL AFL = new AFL();
        AFL.hello();

        AFL.pre("afl_in", "afl_out", "/home/mark/target/simpletarget");

        AFL.debugRun("1\0");
        AFL.debugRun("42\0");

        AFL.post();
    }

    private void debugRun(String testcase) {
        byte[] testOutput = run(testcase.getBytes());

        System.out.println("Testcase output: [" + new String(testOutput) + "]");

        byte[] traceBitmap = getTraceBitmap();

        for (int i = 0, traceBitmapLength = traceBitmap.length; i < traceBitmapLength; i++) {
            byte b = traceBitmap[i];
            if (b != 0)
                System.out.println("  Trace byte " + i + ": " + b);
        }
    }
}
