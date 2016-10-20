package net.praseodym.activelearner;

import com.google.common.hash.HashCode;
import com.google.common.primitives.Bytes;
import de.learnlib.api.MembershipOracle;
import de.learnlib.api.Query;
import net.automatalib.words.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Collection;

/**
 * AFL Forkserver Mealy Membership Oracle
 * <p>
 * This is an optimisation to the normal SULOracle: instead of replaying the prefix and suffix as single steps,
 * we execute the entire prefix/suffix in one step.
 */
@ParametersAreNonnullByDefault
public class AFLMealyOracle implements MembershipOracle.MealyMembershipOracle<Byte, String> {

    private final Logger log = LoggerFactory.getLogger(AFLMealyOracle.class);

    private AFLSUL aflSUL;

    public AFLMealyOracle(AFLSUL aflSUL) {
        this.aflSUL = aflSUL;
    }

    @Override
    public void processQueries(Collection<? extends Query<Byte, Word<String>>> queries) {
        for (Query<Byte, Word<String>> q : queries) {
            Word<String> output = answerQuery(q.getPrefix(), q.getSuffix());
            q.answer(output);
        }
    }

    @Override
    public Word<String> answerQuery(Word<Byte> prefix, Word<Byte> suffix) {
        if (log.isTraceEnabled()) {
            log.debug("Answering query, prefix: [{}] suffix: [{}]", toHexString(prefix), toHexString(suffix));
        }

        byte[] rawOutput = aflSUL.run(concatenateWord(prefix, suffix));

        String output = new String(rawOutput);
        Word<String> word = buildWord(output, suffix.length());

        if (log.isDebugEnabled()) {
            log.debug("Answered query prefix: [{}] suffix: [{}] => answer: [{}]", toHexString(prefix), toHexString
                    (suffix), output);
        }

        assert suffix.length() == word.length() : "Invalid answer length";

        return word;
    }

    private static String toHexString(Word<Byte> word) {
        return word.size() < 1 ? "ε" : HashCode.fromBytes(Bytes.toArray(word.asList())).toString();
    }

    private byte[] concatenateWord(Word<Byte> prefix, Word<Byte> suffix) {
        return Bytes.concat(Bytes.toArray(prefix.asList()), Bytes.toArray(suffix.asList()));
    }

    /**
     * Build a Word from a String. We need the output Word to be same length as the suffix, otherwise counterexample
     * finding will end up in an infinite loop because the hypothesis will never match the actual answer we give.
     */
    private Word<String> buildWord(@Nonnull String output, int suffixLength) {
        assert "valid".equals(output) || "error".equals(output) : "Unexpected output: " + output;

        String[] symbols = new String[suffixLength];
        Arrays.fill(symbols, output);
        return Word.fromSymbols(symbols);
    }

}
