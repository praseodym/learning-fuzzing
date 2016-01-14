package net.praseodym.activelearner;

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
 */
public class AFLEQOracle<A extends UniversalDeterministicAutomaton<?, I, ?, ?, ?> & Output<I, D>, I, D>
        implements EquivalenceOracle<A, I, D> {

    private final Logger log = LoggerFactory.getLogger(AFLEQOracle.class);

    private final Alphabet<I> inputAlphabet;
    private final MembershipOracle<I, D> sulOracle;
    private final WordBuilder<I> wb = new WordBuilder<>();
    private final String directory;

    /**
     * Constructor.
     *
     * @param sulOracle interface to the system under learning
     * @param directory
     */
    public AFLEQOracle(Alphabet<I> inputAlphabet, MembershipOracle<I, D> sulOracle, String directory) {
        this.inputAlphabet = inputAlphabet;
        this.sulOracle = sulOracle;
        this.directory = directory;
    }

    /*
     * (non-Javadoc)
     * @see de.learnlib.api.EquivalenceOracle#findCounterExample(java.lang.Object, java.util.Collection)
     */
    @Override
    public DefaultQuery<I, D> findCounterExample(A hypothesis,
                                                 Collection<? extends I> inputs) {

//        List<Word<I>> transCover = Automata.transitionCover(hypothesis, inputs);
//        List<Word<I>> charSuffixes = Automata.characterizingSet(hypothesis, inputs);

        // Special case: List of characterizing suffixes may be empty,
        // but in this case we still need to test!
//        if (charSuffixes.isEmpty())
//            charSuffixes = Collections.singletonList(Word.<I>epsilon());

        // /tmp/learner_afl5862107233829149040


        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
            for (Path path : directoryStream) {
                if (log.isDebugEnabled()) {
                    log.debug("Equivalence for {}", path.getFileName());
                }
                Scanner s = new Scanner(path);
                while (s.hasNext()) {
                    I token = (I) s.next();
                    try {
                        // Check if symbol is in alphabet, otherwise the hypothesis.computeOutput will throw an error
                        inputAlphabet.getSymbolIndex(token);
                        wb.append(token);
                    } catch (NullPointerException ignored) {
                    }
                }
                Word<I> queryWord = wb.toWord();
                wb.clear();
                DefaultQuery<I, D> query = new DefaultQuery<>(queryWord);
                D hypOutput = hypothesis.computeOutput(queryWord);
                sulOracle.processQueries(Collections.singleton(query));
                if (!Objects.equals(hypOutput, query.getOutput()))
                    return query;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /*for (List<? extends I> middle : CollectionsUtil.allTuples(inputs, 1, maxDepth)) {
            for (Word<I> trans : transCover) {
                for (Word<I> suffix : charSuffixes) {
                    wb.append(trans).append(middle).append(suffix);
                    Word<I> queryWord = wb.toWord();
                    wb.clear();
                    DefaultQuery<I, D> query = new DefaultQuery<>(queryWord);
                    D hypOutput = hypothesis.computeOutput(queryWord);
                    sulOracle.processQueries(Collections.singleton(query));
                    if (!Objects.equals(hypOutput, query.getOutput()))
                        return query;
                }
            }
        }*/

        return null;
    }
}