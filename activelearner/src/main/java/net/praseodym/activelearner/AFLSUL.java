package net.praseodym.activelearner;

import com.google.common.primitives.Bytes;
import de.learnlib.api.SUL;
import de.learnlib.api.SULException;
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
 * We do not implement SUL#fork() because we are not a true single-step SUL.
 */
public class AFLSUL implements SUL<String, String>, InitializingBean, DisposableBean {

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

    @Value("${learner.outdir:#{null}}")
    private String outdir;

    @Override
    public void afterPropertiesSet() throws Exception {
        Path aflDir;
        if (outdir == null) {
            aflDir = Files.createTempDirectory("learner_afl_");
        } else {
            aflDir = Paths.get(outdir);
            assert Files.exists(aflDir) : "Output directory does not exist";
        }
        Path aflIn = aflDir.resolve("afl_in");
        Path aflOut = aflDir.resolve("afl_out");
        Files.createDirectory(aflIn);
        Files.createDirectory(aflOut);
        Files.write(aflIn.resolve("a"), testinput.getBytes());

        log.info("AFL directory: {}", aflDir.toAbsolutePath());

        log.info("Starting forkserver");
        afl.pre(aflIn.toString(), aflOut.toString(), target);
    }

    @Override
    public void destroy() throws Exception {
        log.info("Stopping forkserver, total run calls: {}", execs++);
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
    public String step(@Nullable String in) throws SULException {
        if (in == null)
            in = "";

        byte[] input = in.getBytes();
        byte[] output = run(previousOutput, previousInput, input);

        previousInput = input;
        previousOutput = output; // TODO: this saves the shortened output value, not the original one

        return new String(output);
    }

    /**
     * Run output and return difference between previous output (from "prefix") and new output (from "suffix")
     */
    @Nonnull
    public byte[] run(@Nullable byte[] previousInput, @Nullable byte[] previousOutput, @Nonnull byte[] input) {
        input = calculateNewInput(previousInput, input);

        // Run target
        byte[] output = afl.run(input);
        execs++;

        if (log.isTraceEnabled()) {
            log.trace("Run with stdin [{}] and stdout [{}]",
                    new String(input).replace("\n", " ").trim(),
                    new String(output).replace("\n", " ").trim());
        }

        output = calculateNewOutput(previousOutput, output);

        // Check forkserver for new edges
        int newQueuedDiscovered = afl.getQueuedDiscovered();
        if (queuedDiscovered != newQueuedDiscovered) {
            log.info("Discovered new interesting testcase {} - stdin: [{}], stdout: [{}]",
                    newQueuedDiscovered,
                    new String(input).replace("\n", " ").trim(),
                    new String(output).replace("\n", " ").trim());
            queuedDiscovered = newQueuedDiscovered;
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
}