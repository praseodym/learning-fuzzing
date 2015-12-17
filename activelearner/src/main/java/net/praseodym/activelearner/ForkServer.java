package net.praseodym.activelearner;

/**
 * Fork server native binding
 */
public class ForkServer {
    public native void hello();

    public native void pre(String arguments);

    public native byte[] run(byte[] testcase);

    public native void post();

    public static void main(String[] args) {
        ForkServer forkServer = new ForkServer();
        forkServer.hello();
        //long l = System.nanoTime();
        //for (int i = 0; i < 100; i++) {
        //    forkServer.hello();
        //}
        //System.out.println("");
        //System.out.println(System.nanoTime() - l);

        forkServer.pre("test");

        byte[] testOutput = forkServer.run("1\0".getBytes());
        System.out.println("Testcase 1 output: [" + new String(testOutput) + "]");

        testOutput = forkServer.run("42\0".getBytes());

        System.out.println("Testcase 2 output: [" + new String(testOutput) + "]");

        forkServer.post();
    }

    static {
        System.loadLibrary("forkserver");
    }
}
