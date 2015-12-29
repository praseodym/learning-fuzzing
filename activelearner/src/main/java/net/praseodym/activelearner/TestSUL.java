package net.praseodym.activelearner;

import de.learnlib.api.SUL;
import de.learnlib.api.SULException;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Test System under Learning (SUL)
 */
public class TestSUL implements SUL<String, String> {
    List<String> previousSteps;

    @Override
    public void pre() {
        previousSteps = new ArrayList<>();
    }

    @Override
    public void post() {
        // TODO: shut down
    }

    @Nullable
    @Override
    public String step(@Nullable String in) throws SULException {
        System.err.println("in: " + in);

        if (in == null)
            return null;

        // TODO: support for multiple steps (i.e. keep process alive)
        if (!previousSteps.isEmpty())
            return null;

        ProcessBuilder pb = new ProcessBuilder("/home/mark/target/simpletarget");
        String out;
        try {
            Process p = pb.start();
            OutputStream stdin = p.getOutputStream();
            BufferedReader stdout =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            stdin.write(in.getBytes());
            stdin.write(System.lineSeparator().getBytes());
            stdin.flush();
            out = stdout.readLine();
        } catch (IOException e) {
            throw new SULException(e);
        }

        previousSteps.add(in);

        System.err.println("out: " + out);

        return out;
    }
}
