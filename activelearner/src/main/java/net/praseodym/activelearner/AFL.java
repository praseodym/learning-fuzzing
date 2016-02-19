package net.praseodym.activelearner;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
