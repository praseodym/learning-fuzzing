import net.praseodym.activelearner.AFL;

import java.util.List;
import java.util.stream.Collectors;

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
        test("A A");
        test("A B");
        previousTraceBitmap = test("A C");
        test("A C C");
        test("A C C C");
        previousTraceBitmap = test("A C A");
        previousTraceBitmap = test("A C A B");
        previousTraceBitmap = test("A C A B B");
        previousTraceBitmap = test("A C A B B B");
        previousTraceBitmap = test("A C A B B B B");
        previousTraceBitmap = test("A C A B B B B B");
        previousTraceBitmap = test("A C A B B B B B B");
        previousTraceBitmap = test("A C A B B B B B B B");
        previousTraceBitmap = test("A C A B B B B B B B B");
        previousTraceBitmap = test("A B A B B B B B B B B");
        previousTraceBitmap = test("A A B B B B B B B B");

        previousTraceBitmap = null;
        previousTraceBitmap = test("C");
        previousTraceBitmap = test("B C");
        previousTraceBitmap = test("A A C");

//        previousTraceBitmap = test("C");
//        previousTraceBitmap = test("C C");
//        previousTraceBitmap = test("C C C");
//        previousTraceBitmap = test("C C C C");
//        previousTraceBitmap = test("C C C C C");
//        previousTraceBitmap = test("C C C C C C");
//        previousTraceBitmap = test("C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C C C C C");
//        previousTraceBitmap = test("C C C C C C C C C C C C C");

        AFL.post();
    }

    private static byte[] test(String testcase) {
        byte[] testOutput = afl.run(testcase.getBytes());

        System.out.println("Testcase: [" + testcase.replace('\n', ' ')
                + "] -> [" + new String(testOutput).replace('\n', ' ') + "]");

        byte[] traceBitmap = afl.getTraceBitmap();
        System.out.println(AFL.friendlyBitmap(traceBitmap).stream().collect(Collectors.joining("#")));

        if (previousTraceBitmap != null) {
            byte[] newTraceBits = AFL.getNewTraceBits(previousTraceBitmap, traceBitmap);
            List<String> friendlyBitmap = AFL.friendlyBitmap(newTraceBits);
            System.out.format("New trace bits: %d\n", friendlyBitmap.size());
            friendlyBitmap.forEach((x) -> System.out.println("- Trace byte " + x));
        }

        return traceBitmap;
    }
}
