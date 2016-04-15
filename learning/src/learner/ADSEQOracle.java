package learner;

import com.google.common.collect.Lists;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.MembershipOracle;
import de.learnlib.oracles.DefaultQuery;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.util.graphs.dot.GraphDOT;
import net.automatalib.words.Word;
import net.automatalib.words.WordBuilder;

import javax.annotation.Nullable;
import java.io.*;
import java.util.*;

/**
 * Implements the Lee & Yannakakis suffixes by invoking an external program. Because of this indirection to an external
 * program, the findCounterexample method might throw a RuntimeException.
 *
 * @author Joshua Moerman
 *
 * @param <O> is the output alphabet. (So a query will have type Word<String, Word<O>>.)
 */
public class ADSEQOracle<O> implements EquivalenceOracle.MealyEquivalenceOracle<String, O> {
    private final MembershipOracle<String, Word<O>> sulOracle;
    private final ProcessBuilder pb;

    // We buffer queries, in order to allow for parallel membership queries.
    private final int bufferSize;
    private final ArrayList<DefaultQuery<String, Word<O>>> buffer;

    // We might want an upper limit on the number of test queries (only in random mode)
    private final int maxTests;
    private int testCount; // is reset after a counterexample
    private boolean randomMode;


    private Process process;
    private Writer processInput;
    private BufferedReader processOutput;
    private StreamGobbler errorGobbler;

    /**
     * @param sulOracle The membership oracle of the SUL, we need this to check the output on the test suite
     */
    ADSEQOracle(String path, MembershipOracle<String, Word<O>> sulOracle, int bufferSize, int maxTests, String... extraArguments) {
        this.sulOracle = sulOracle;
        List<String> command = Lists.newArrayList(path, "=");
        command.addAll(Arrays.asList(extraArguments));
        if(extraArguments[0] == "random") {
            this.randomMode = true;
        } else {
            this.randomMode = false;
        }
        this.pb = new ProcessBuilder(command);
        this.bufferSize = bufferSize;
        this.maxTests = maxTests;
        this.buffer = new ArrayList<>(bufferSize);
    }

    /**
     * A small class to print all stuff to stderr. Useful as I do not want stderr and stdout of the external program to
     * be merged, but still want to redirect stderr to java's stderr.
     */
    class StreamGobbler extends Thread {
        private final InputStream stream;
        private final String prefix;

        StreamGobbler(InputStream stream, String prefix) {
            this.stream = stream;
            this.prefix = prefix;
        }

        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line;
                while ((line = reader.readLine()) != null)
                    System.err.println(prefix + "> " + line);
            } catch (IOException e) {
                // It's fine if this thread crashes, nothing depends on it
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts the process and creates buffered/whatnot streams for stdin stderr or the external program
     * @throws IOException if the process could not be started (see ProcessBuilder.start for details).
     */
    private void setupProcess() throws IOException {
        process = pb.start();
        processInput = new OutputStreamWriter(process.getOutputStream());
        processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR> main");
        errorGobbler.start();
    }

    /**
     * I thought this might be a good idea, but I'm not a native Java speaker, so maybe it's not needed.
     */
    private void closeAll() {
        // Since we're closing, I think it's ok to continue in case of an exception
        try {
            processInput.close();
            processOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            errorGobbler.join(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        process.destroyForcibly();
    }

    /**
     * Uses an external program to find counterexamples. The hypothesis will be written to stdin. Then the external
     * program might do some calculations and write its test suite to stdout. This is in turn read here and fed
     * to the SUL. If a discrepancy occurs, an counterexample is returned. If the external program exits (with value
     * 0), then no counterexample is found, and the hypothesis is correct.
     *
     * This method might throw a RuntimeException if the external program crashes (which it shouldn't of course), or if
     * the communication went wrong (for whatever IO reason).
     */
    @Nullable
    @Override
    public DefaultQuery<String, Word<O>> findCounterExample(MealyMachine<?, String, ?, O> machine, Collection<? extends String> inputs) {
        return findCounterExampleImpl(machine, inputs);
    }

    private DefaultQuery<String, Word<O>> findCounterExampleImpl(MealyMachine<?, String, ?, O> machine, Collection<? extends String> inputs) {
        try {
            setupProcess();
        } catch (IOException e) {
            throw new RuntimeException("Unable to start the external program: " + e);
        }

        try {
            // Write the hypothesis to stdin of the external program
            GraphDOT.write(machine, inputs, processInput);
            processInput.flush();

            testCount = 0;

            // Read every line outputted on stdout.
            // We buffer the queries, so that a parallel membership query can be applied
            String line;
            while ((line = processOutput.readLine()) != null) {
                // Read every string of the line, this will be a symbol of the input sequence.
                WordBuilder<String> wb = new WordBuilder<>();
                Scanner s = new Scanner(line);
                while(s.hasNext()) {
                    wb.add(s.next());
                }

                // Convert to a word and test on the SUL
                Word<String> test = wb.toWord();
                DefaultQuery<String, Word<O>> query = new DefaultQuery<>(test);
                buffer.add(query);

                testCount++;

                // If the buffer is filled, we can perform the checks (possibly in parallel)
                if(buffer.size() >= bufferSize || (randomMode && testCount >= maxTests)){
                    DefaultQuery<String, Word<O>> r = checkAndEmptyBuffer(machine);
                    if(r != null){
                        closeAll();
                        return r;
                    }
                }

                // If we have reached the maximum number of tests, close the test process and break
                if(randomMode && testCount >= maxTests) {
                    closeAll();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to communicate with the external program: " + e);
        }

        // At this point, the external program closed its stream, so it should have exited.
        if(process.isAlive()){
            System.err.println("ERROR> log> No counterexample but process stream still active!");
            closeAll();
            throw new RuntimeException("No counterexample but process stream still active!");
        }

        // If the program exited with a non-zero value, something went wrong (for example a segfault)!
        // return value 141 means it was stopped by us, so this is ok.
        int ret = process.exitValue();
        if(ret != 0 && ret != 141){
            System.err.println("ERROR> log> Something went wrong with the process: return value = " + ret);
            closeAll();
            throw new RuntimeException("Something went wrong with the process: return value = " + ret);
        }

        // Here, the program exited normally, without counterexample, so we may return null.
        return null;
    }

    private DefaultQuery<String, Word<O>> checkAndEmptyBuffer(MealyMachine<?, String, ?, O> machine){
        sulOracle.processQueries(buffer);
        DefaultQuery<String, Word<O>> r = inspectBuffer(machine);
        buffer.clear();
        return r;
    }

    private DefaultQuery<String, Word<O>> inspectBuffer(MealyMachine<?, String, ?, O> machine){
        for(DefaultQuery<String, Word<O>> query : buffer){
            Word<O> o1 = machine.computeOutput(query.getInput());
            Word<O> o2 = query.getOutput();

            assert o1 != null;
            assert o2 != null;

            // If equal => no counterexample :(
            if(!o1.equals(o2)) return query;
        }
        return null;
    }
}