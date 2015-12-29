package net.praseodym.activelearner;

import de.learnlib.api.SUL;
import de.learnlib.api.SULException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Forkserver System under Learning (SUL)
 *
 * We do not implement SUL#fork() because we are not a true single-step SUL.
 */
public class ForkServerSUL implements SUL<String, String>, InitializingBean, DisposableBean {

    private final Logger logger = LoggerFactory.getLogger(ForkServerSUL.class);

    @Autowired
    private ForkServer forkServer;

    private List<String> previousSteps;

    public ForkServerSUL() {
        System.loadLibrary("forkserver");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        forkServer.pre("test");
    }

    @Override
    public void destroy() throws Exception {
        forkServer.post();
    }

    @Override
    public void pre() {
        logger.trace("pre");
        previousSteps = new ArrayList<>();
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
            return null;

        // TODO: support for multiple steps (replay previous steps)
        if (!previousSteps.isEmpty())
            return null;

        byte[] output = forkServer.run("1\0".getBytes());
        String out = new String(output);

        previousSteps.add(in);

        logger.trace("stdout: {}", out);

        return out;
    }
}