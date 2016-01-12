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

    @Autowired
    private AFL afl;

    private byte[] previousInput;
    private byte[] previousOutput;

    private int queuedDiscovered;
    private int execs;

    @Value("${learner.target}")
    private String target;

    @Value("${learner.testinput}")
    private String testinput;

    @Override
    public void afterPropertiesSet() throws Exception {
        Path tempDir = Files.createTempDirectory("learner_afl");
        Path aflIn = tempDir.resolve("afl_in");
        Path aflOut = tempDir.resolve("afl_out");
        Files.createDirectory(aflIn);
        Files.createDirectory(aflOut);
        Files.write(aflIn.resolve("a"), testinput.getBytes());

        log.info("AFL directory: {}", tempDir);

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
        previousOutput = output;

        return new String(output);
    }

    /**
     * Run output and return difference between previous output (from "prefix") and new output (from "suffix")
     */
    @Nonnull
    public byte[] run(@Nullable byte[] previousInput, @Nullable byte[] previousOutput, @Nonnull byte[] input) {
        // Calculate new input
        if (previousInput == null || previousInput.length == 0) {
            input = Bytes.concat(input, SEPARATOR_BYTE);
        } else {
            input = Bytes.concat(previousInput, SEPARATOR_BYTE, input, SEPARATOR_BYTE);
        }

        // Run target
        byte[] output = afl.run(input);
        execs++;

        if (log.isTraceEnabled()) {
            log.trace("Run with stdin [{}] and stdout [{}]",
                    new String(input).replace("\n", " ").trim(),
                    new String(output).replace("\n", " ").trim());
        }

        // FIXME: sometimes we get no output, bug in libafl? -> probably caused by hang, fix this
//        assert output.length > 0 : "no output";
        if (output.length == 0) {
            log.error("No output, target hang? Retrying.");
            // retry
            return run(previousInput, previousOutput, input);
        }

        // Calculate difference between previous and new output
        if (previousOutput != null && previousOutput.length == output.length) {
            return new byte[0];
        } else if (previousOutput != null && previousOutput.length > 0) {
            assert previousOutput.length <= output.length;
            output = Arrays.copyOfRange(output, previousOutput.length, output.length);
        }

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
}