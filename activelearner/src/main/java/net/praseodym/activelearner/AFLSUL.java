package net.praseodym.activelearner;

import com.google.common.hash.HashCode;
import com.google.common.primitives.Bytes;
import de.learnlib.api.SUL;
import de.learnlib.api.SULException;
import net.automatalib.words.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * AFL System under Learning (SUL)
 * <p>
 * This class is optimised to be used with the AFLMealyOracle, which uses the run call.
 * We do not implement SUL#fork() because we are not a true single-step SUL.
 */
public class AFLSUL implements SUL<Byte, String>, InitializingBean, DisposableBean {

    private final Logger log = LoggerFactory.getLogger(AFLSUL.class);

    public static final String SEPARATOR = System.lineSeparator();
    public static final byte[] SEPARATOR_BYTE = SEPARATOR.getBytes();
    public static final String PADDING = "invalid_state";

    @Autowired
    protected AFL afl;

    protected byte[] previousInput;
    protected byte[] previousOutput;

    private int queuedDiscovered;
    protected int execs;

    @Value("${learner.target}")
    private String target;

    @Value("${learner.testinput}")
    private String testinput;

    @Value("${learner.outdir:}")
    private String outdir;

    @Value("${learner.aflout:}")
    private String aflOut;

    @Override
    public void afterPropertiesSet() throws Exception {
        Path tempPath = Files.createTempDirectory("learner_afl_");

        String aflIn;
        Path outPath;
        Path aflOutPath;
        if (aflOut.equals("")) {
            if (outdir.equals("")) {
                outPath = tempPath;
            } else {
                outPath = Paths.get(outdir);
                assert Files.exists(outPath) : "learner.outdir directory does not exist";
            }
            aflOutPath = Files.createDirectory(outPath.resolve("afl_out"));
            aflOut = aflOutPath.toAbsolutePath().toString();
        } else {
            aflOutPath = Paths.get(aflOut);
            if (!Files.exists(aflOutPath)) {
                Files.createDirectory(aflOutPath);
            }
        }

        if (!Files.exists(aflOutPath.resolve("queue"))) {
            Path aflInPath = Files.createDirectory(tempPath.resolve("afl_in"));
            Files.write(aflInPath.resolve("testcase"), testinput.getBytes());
            aflIn = aflInPath.toAbsolutePath().toString();
        } else {
            aflIn = "-";
        }

        log.info("AFL input directory: {}", aflIn);
        log.info("AFL output directory: {}", aflOut);

        log.info("Starting forkserver");
        String[] argv = target.split(" ");
        afl.pre(aflIn, aflOut, argv);
    }

    @Override
    public void destroy() throws Exception {
        log.info("Stopping forkserver: {} run calls, {} discovered test cases", execs++, afl.getQueuedDiscovered());
        afl.post();
    }

    @Override
    public void pre() {
        previousInput = null;
        previousOutput = null;
    }

    @Override
    public void post() {
    }

    /**
     * Simulate single-step by keeping previous input and output
     */
    @Nullable
    @Override
    public String step(@Nullable Byte in) throws SULException {
        if (in == null)
            in = 0x00;

        byte[] input = new byte[]{in};
        byte[] output = run(input);

        byte[] shortOutput = calculateNewOutput(previousOutput, output);

        previousInput = input;
        previousOutput = output;

        return new String(shortOutput);
    }

    /**
     * Run and return output
     */
    @Nonnull
    public byte[] run(@Nonnull byte[] input) {
        // Run target
        byte[] output = afl.run(input);
        execs++;

        if (log.isTraceEnabled()) {
            log.trace("Run with stdin [{}] and stdout [{}]", toHexString(input),
                    new String(output).replace("\n", " ").trim());
        }

        // Check forkserver for new edges
        if (log.isDebugEnabled()) {
            int newQueuedDiscovered = afl.getQueuedDiscovered();
            if (queuedDiscovered != newQueuedDiscovered) {
                log.debug("Discovered new interesting testcase {} - stdin: [{}], stdout: [{}]",
                        newQueuedDiscovered,
                        toHexString(input),
                        new String(output).replace("\n", " ").trim());
                queuedDiscovered = newQueuedDiscovered;
            }
        }

        return output;
    }

    /**
     * New input is calculated by given input prefixed by previous input.
     *
     * @param previousInput
     * @param input
     * @return
     */
    protected byte[] calculateNewInput(@Nullable byte[] previousInput, @Nonnull byte[] input) {
        if (previousInput == null || previousInput.length == 0) {
            input = Bytes.concat(input, SEPARATOR_BYTE);
        } else {
            input = Bytes.concat(previousInput, SEPARATOR_BYTE, input, SEPARATOR_BYTE);
        }
        return input;
    }

    /**
     * New output is the difference between the previous and current output.
     *
     * @param previousOutput
     * @param output
     * @return
     */
    protected byte[] calculateNewOutput(@Nullable byte[] previousOutput, byte[] output) {
        if (previousOutput != null && previousOutput.length == output.length) {
            output = new byte[0];
        } else if (previousOutput != null && previousOutput.length > 0) {
            assert previousOutput.length <= output.length;
            output = Arrays.copyOfRange(output, previousOutput.length, output.length);
        }
        return output;
    }

    private static String toHexString(byte[] bytes) {
        return bytes.length < 1 ? "ε" : HashCode.fromBytes(bytes).toString();
    }
}
