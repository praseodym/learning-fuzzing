import net.praseodym.activelearner.AFL;

import java.util.List;

/**
 * AFL native library test code (not an actual unit test, but well)
 */
public class AFLTest {
    private static AFL afl;
    private static byte[] previousTraceBitmap;

    public static void main(String... args) {
        afl = new AFL();
        AFL AFL = afl;
        AFL.hello();

        AFL.pre("afl_in", "afl_out", args[0]);

        previousTraceBitmap = test("A");
        test("A\nA");
        test("A\nB");
        previousTraceBitmap = test("A\nC");
        test("A\nC\nC");
        test("A\nC\nC\nC");
        previousTraceBitmap = test("A\nC\nA");
        previousTraceBitmap = test("A\nC\nA\nB");
        test("A\nC\nA\nB\nB");

        AFL.post();
    }

    private static byte[] test(String testcase) {
        byte[] testOutput = afl.run(testcase.getBytes());

        System.out.println("Testcase: [" + testcase.replace('\n', ' ')
                + "] -> [" + new String(testOutput).replace('\n', ' ') + "]");

        byte[] traceBitmap = afl.getTraceBitmap();

        //friendlyBitmap(traceBitmap).forEach(System.out::println);

        if (previousTraceBitmap != null) {
            byte[] newTraceBits = AFL.getNewTraceBits(previousTraceBitmap, traceBitmap);
            List<String> friendlyBitmap = AFL.friendlyBitmap(newTraceBits);
            System.out.format("New trace bits: %d\n", friendlyBitmap.size());
            friendlyBitmap.forEach((x) -> System.out.println("- Trace byte " + x));
        }

        return traceBitmap;
    }
}
