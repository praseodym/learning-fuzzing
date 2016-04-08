package net.praseodym.activelearner;

import com.google.common.io.BaseEncoding;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.MembershipOracle;
import de.learnlib.oracles.DefaultQuery;
import net.automatalib.automata.UniversalDeterministicAutomaton;
import net.automatalib.automata.concepts.Output;
import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;
import net.automatalib.words.WordBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

/**
 * AFL equivalence oracle.
 * <p>
 * Uses existing AFL discovered (interesting) test cases as a way to find counterexamples.
 * <p>
 * For TTT (and maybe other algorithms) a single test case can be a counterexample more than once, and even
 * turn up as a counterexample after having "passed" before. This is why we always re-test using all test cases.
 */
public class AFLEQOracle<A extends UniversalDeterministicAutomaton<?, I, ?, ?, ?> & Output<I, D>, I, D>
        implements EquivalenceOracle<A, I, D> {

    private final Logger log = LoggerFactory.getLogger(AFLEQOracle.class);

    private final Alphabet<I> inputAlphabet;
    private final MembershipOracle<I, D> sulOracle;
    private final WordBuilder<I> wb = new WordBuilder<>();
    private final Path directory;

    /**
     * Constructor.
     *
     * @param sulOracle interface to the system under learning
     * @param directory directory with test cases
     */
    public AFLEQOracle(Alphabet<I> inputAlphabet, MembershipOracle<I, D> sulOracle, String directory) throws
            IOException {
        this.inputAlphabet = inputAlphabet;
        this.sulOracle = sulOracle;
        this.directory = Paths.get(directory);

        log.info("Using AFL equivalence test cases from {}", directory);
    }

    /*
     * (non-Javadoc)
     * @see de.learnlib.api.EquivalenceOracle#findCounterExample(java.lang.Object, java.util.Collection)
     */
    @Override
    public DefaultQuery<I, D> findCounterExample(A hypothesis,
                                                 Collection<? extends I> inputs) {
        try (DirectoryStream<Path> testcases = Files.newDirectoryStream(directory)) {
            for (Path testcase : testcases) {
                log.debug("Test case {}", testcase.getFileName());

                // Match whitespace, ASCII control chars, high UTF-8 chars, + chars, and any leading zeroes as delimiter
                Scanner s = new Scanner(testcase).useDelimiter("(\\s|[\\x00-\\x1F]|[\\x7F-\\uFFFF]|\\+)+0*");
                while (s.hasNext()) {
                    String token = s.next();

                    try {
                        // Check if symbol is in alphabet, otherwise the hypothesis.computeOutput will throw an error
                        @SuppressWarnings("unchecked") I symbol = (I) token;
                        inputAlphabet.getSymbolIndex(symbol);
                        wb.append(symbol);
                    } catch (NullPointerException e) {
                        // Not a valid symbol
                        if (log.isDebugEnabled()) {
                            String hex = BaseEncoding.base16().encode(token.getBytes());
                            log.debug("Unknown symbol: {} hex: {} length: {}", token, hex, token.length());
                            log.debug("Current sequence: {}", wb);
                        }
                    }
                }
                if (wb.size() == 0)
                    continue;
                Word<I> queryWord = wb.toWord();
                wb.clear();
                DefaultQuery<I, D> query = new DefaultQuery<>(queryWord);
                D hypOutput = hypothesis.computeOutput(queryWord);
                sulOracle.processQueries(Collections.singleton(query));
                if (!Objects.equals(hypOutput, query.getOutput())) {
                    log.info("Test case {} is counterexample", testcase.getFileName());
                    return query;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
