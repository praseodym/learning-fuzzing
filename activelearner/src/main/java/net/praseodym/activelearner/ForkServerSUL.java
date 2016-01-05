package net.praseodym.activelearner;

import com.google.common.primitives.Bytes;
import de.learnlib.api.SUL;
import de.learnlib.api.SULException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Forkserver System under Learning (SUL)
 * <p>
 * We do not implement SUL#fork() because we are not a true single-step SUL.
 */
public class ForkServerSUL implements SUL<String, String>, InitializingBean, DisposableBean {

    private final Logger log = LoggerFactory.getLogger(ForkServerSUL.class);

    public static final String SEPARATOR = System.lineSeparator();
    public static final byte[] SEPARATOR_BYTE = SEPARATOR.getBytes();

    @Autowired
    private ForkServer forkServer;

    private byte[] previousInput;
    private byte[] previousOutput;

    private int queuedDiscovered;
    private int execs;

    public ForkServerSUL() {
        System.loadLibrary("forkserver");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Starting forkserver");
        // TODO: configurable initial input
        forkServer.pre("test");
    }

    @Override
    public void destroy() throws Exception {
        log.info("Stopping forkserver with {} total target executions", execs++);
        forkServer.post();
    }

    @Override
    public void pre() {
        log.trace("pre");
        previousInput = null;
        previousOutput = null;
    }

    @Override
    public void post() {
        log.trace("post");
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
        byte[] output = forkServer.run(input);
        execs++;

        if (log.isTraceEnabled()) {
            log.trace("Run with stdin [{}] and stdout [{}]", new String(input), new String(output));
        }

        // Calculate difference between previous and new output
        if (previousOutput != null && previousOutput.length == output.length) {
            return new byte[0];
        } else if (previousOutput != null && previousOutput.length > 0) {
            output = Arrays.copyOfRange(output, previousOutput.length, output.length);
        }

        // Check forkserver for new edges
        int newQueuedDiscovered = forkServer.getQueuedDiscovered();
        if (queuedDiscovered != newQueuedDiscovered) {
            log.info("Discovered new interesting testcase ({} -> {}) - stdin: [{}], stdout: [{}]",
                    queuedDiscovered, newQueuedDiscovered, new String(input), new String(output));
            queuedDiscovered = newQueuedDiscovered;
        }

        return output;
    }
}