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
    public native void hello();

    public native void pre(String arguments);

    @Cacheable("runs")
    public native byte[] run(byte[] testcase);

    public native void post();

    public native int getQueuedDiscovered();

    public static void main(String[] args) {
        AFL AFL = new AFL();
        AFL.hello();

        AFL.pre("test");

        byte[] testOutput = AFL.run("1\0".getBytes());
        System.out.println("Testcase 1 output: [" + new String(testOutput) + "]");

        testOutput = AFL.run("42\0".getBytes());

        System.out.println("Testcase 2 output: [" + new String(testOutput) + "]");

        AFL.post();
    }

    static {
        System.loadLibrary("afl");
    }
}
