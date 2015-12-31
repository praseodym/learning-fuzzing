package net.praseodym.activelearner;

import com.google.common.primitives.Bytes;
import de.learnlib.api.SUL;
import de.learnlib.api.SULException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Forkserver System under Learning (SUL)
 * <p>
 * We do not implement SUL#fork() because we are not a true single-step SUL.
 */
public class ForkServerSUL implements SUL<String, String>, InitializingBean, DisposableBean {

    private final Logger logger = LoggerFactory.getLogger(ForkServerSUL.class);

    public static final byte[] SEPARATOR = System.lineSeparator().getBytes();

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
        logger.info("Starting forkserver");
        // TODO: configurable initial input
        forkServer.pre("test");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("Stopping forkserver with {} total target executions", execs++);
        forkServer.post();
    }

    @Override
    public void pre() {
        logger.trace("pre");
        previousInput = null;
        previousOutput = null;
    }

    @Override
    public void post() {
        logger.trace("post");
    }

    @Nullable
    @Override
    public String step(@Nullable String in) throws SULException {
        logger.trace("stdin: {}", in);

        if (in == null)
            in = "";

        byte[] input = in.getBytes();
        if (previousInput == null) {
            input = Bytes.concat(input, SEPARATOR);
        } else {
            input = Bytes.concat(previousInput, input, SEPARATOR);
        }

        execs++;
        byte[] output = forkServer.run(input);
        String out;
        if (previousOutput != null && previousOutput.length > 0) {
            byte[] newOutput = Arrays.copyOfRange(output, previousOutput.length, output.length);
            out = new String(newOutput);
        } else {
            out = new String(output);
        }

        int newQueuedDiscovered = forkServer.getQueuedDiscovered();
        if (queuedDiscovered != newQueuedDiscovered) {
            logger.info("Discovered new testcase ({} -> {}) - stdin: [{}], stdout: [{}]",
                    queuedDiscovered, newQueuedDiscovered, in, out);
            queuedDiscovered = newQueuedDiscovered;
        }

        previousInput = input;
        previousOutput = output;

        logger.trace("stdout: {}", out);

        return out;
    }
}