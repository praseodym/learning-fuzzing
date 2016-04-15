package learner;

import de.learnlib.api.SUL;
import de.learnlib.api.SULException;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * External process System under Learning (SUL) wrapper
 */
public class ProcessSUL implements SUL<String, String> {

    public static final byte[] SEPARATOR = System.lineSeparator().getBytes();

    private Process p;
    private OutputStream stdin;
    private BufferedReader stdout;
    private int execs;

    private String target;

    public ProcessSUL(String target) {
        this.target = target;
    }

    @Override
    public void pre() {
        ProcessBuilder pb =  new ProcessBuilder(target);
        try {
            p = pb.start();
            execs++;
            stdin = p.getOutputStream();
            stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void post() {
        p.destroyForcibly();
    }

    @Nullable
    @Override
    public String step(@Nullable String in) throws SULException {
        if (in == null)
            return null;

        if (!p.isAlive())
            return null;

        String out;
        try {
            stdin.write(in.getBytes());
            stdin.write(SEPARATOR);
            stdin.flush();

            // FIXME: ugly hack to fix multi-line output in RERS SULs
            out = stdout.readLine();
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (stdout.ready()) {
                out += "_" + stdout.readLine();
            }
        } catch (IOException e) {
            if ("Broken pipe".equals(e.getMessage()) || "Stream closed".equals(e.getMessage())) {
                return null;
            } else
                throw new SULException(e);
        }

        return out;
    }

}

