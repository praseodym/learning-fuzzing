package net.praseodym.activelearner;

import de.learnlib.api.MembershipOracle;
import de.learnlib.api.Query;
import net.automatalib.words.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * AFL Forkserver Mealy Membership Oracle
 * <p>
 * This is an optimisation to the normal SULOracle: instead of replaying the prefix and suffix as single steps,
 * we execute the entire prefix/suffix in one step.
 */
@ParametersAreNonnullByDefault
public class AFLMealyOracle implements MembershipOracle.MealyMembershipOracle<String, String> {

    private final Logger log = LoggerFactory.getLogger(AFLMealyOracle.class);

    private AFLSUL aflSUL;

    public AFLMealyOracle(AFLSUL aflSUL) {
        this.aflSUL = aflSUL;
    }

    @Override
    public void processQueries(Collection<? extends Query<String, Word<String>>> queries) {
        for (Query<String, Word<String>> q : queries) {
            Word<String> output = answerQuery(q.getPrefix(), q.getSuffix());
            q.answer(output);
        }
    }

    @Override
    public Word<String> answerQuery(Word<String> prefix, Word<String> suffix) {
        if (log.isTraceEnabled()) {
            log.debug("Answering query, prefix: [{}] suffix: [{}]", prefix, suffix);
        }

        byte[] rawOutput = aflSUL.run(concatenateWord(prefix, suffix).getBytes());

        String output = new String(rawOutput);
        Word<String> word = buildWord(output, prefix.length(), suffix.length());

        if (log.isDebugEnabled()) {
            log.debug("Answered query prefix: [{}] suffix: [{}] => answer: [{}]", prefix, suffix, word);
        }

        assert suffix.length() == word.length() : "Invalid answer length";

        return word;
    }

    private String concatenateWord(Word<String> prefix, Word<String> suffix) {
        return Stream.concat(prefix.stream(), suffix.stream()).collect(Collectors.joining(AFLSUL.SEPARATOR));
    }

    /**
     * Build a Word from a String. We need the output Word to be same length as the suffix, otherwise counterexample
     * finding will end up in an infinite loop because the hypothesis will never match the actual answer we give.
     */
    private Word<String> buildWord(@Nonnull String in, int prefixLength, int suffixLength) {
        ArrayDeque<String> symbols = new ArrayDeque<>(prefixLength + suffixLength);
        for (String s : in.split(AFLSUL.SEPARATOR)) {
            // assert symbols occurs after real symbols, we need to merge this into a single symbol
            if (s.startsWith("assert")) {
                s = symbols.removeLast() + "_" + s;
            }
            symbols.addLast(s);
        }

        // Remove all elements up to prefix
        for (int i = Math.min(prefixLength, symbols.size()) - 1; i >= 0; i--) {
            symbols.removeFirst();
        }

        assert symbols.size() <= suffixLength;
        while (symbols.size() != suffixLength) {
            symbols.addLast(AFLSUL.PADDING);
        }

        return Word.fromSymbols(symbols.toArray(new String[symbols.size()]));
    }

}
