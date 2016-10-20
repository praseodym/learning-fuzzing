package net.praseodym.activelearner;

import de.learnlib.api.SULException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.stream.Collectors;

/**
 * AFL SUL that uses trace bitmap as output (instead of target's stdout)
 */
public class AFLTraceBitmapSUL extends AFLSUL {

    private final Logger log = LoggerFactory.getLogger(AFLTraceBitmapSUL.class);
    private byte[] previousTraceBitmap;

    @Override
    public void pre() {
        super.pre();
        previousTraceBitmap = null;
    }

    @Nullable
    @Override
    public String step(@Nullable Byte in) throws SULException {
        if (in == null)
            in = 0x00;

        //log.trace("in: [{}] [{}]", previousInput == null ? "null" : new String(previousInput), in);

        byte[] input = calculateNewInput(previousInput, new byte[]{in});

        byte[] output = afl.run(input);
        execs++;

        byte[] newOutput = calculateNewOutput(previousOutput, output);
        byte[] traceBitmap = afl.getTraceBitmap();

//        byte[] newTraceBits;
//        if (previousOutput == null) {
//            newTraceBits = traceBitmap;
//        } else {
//            newTraceBits = AFL.getNewTraceBits(previousTraceBitmap, output);
//        }

        previousInput = input;
        previousOutput = output;
        previousTraceBitmap = traceBitmap;

        return new String(newOutput) + '_' + AFL.friendlyBitmap(traceBitmap).stream().collect(Collectors.joining("#"));
    }
}
