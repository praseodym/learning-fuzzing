package net.praseodym.activelearner;

/**
 * Fork server native binding
 */
public class ForkServer {
    public native void hello();

    public native void pre();

    public native void post();

    public static void main(String[] args) {
        ForkServer forkServer = new ForkServer();
        forkServer.hello();
        long l = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            forkServer.hello();
        }
        System.out.println("");
        System.out.println(System.nanoTime() - l);
    }

    static {
        System.loadLibrary("forkserver");
    }
}
