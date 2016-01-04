package net.praseodym.activelearner;

import de.learnlib.api.MembershipOracle;
import de.learnlib.api.Query;
import de.learnlib.api.SUL;
import de.learnlib.api.SULException;
import net.automatalib.words.Word;
import net.automatalib.words.WordBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Logging SUL Oracle that logs prefix and suffix. Verbatim copy of SULOracle, which is not extensible.
 */
public class LoggingSULOracle<I, O> implements MembershipOracle.MealyMembershipOracle<I, O> {

    private static final Logger log = LoggerFactory.getLogger(LoggingSULOracle.class);

    private final SUL<I, O> sul;
    private final ThreadLocal<SUL<I, O>> localSul;

    public LoggingSULOracle(SUL<I, O> sul) {
        this.sul = sul;
        if (sul.canFork()) {
            this.localSul = new ThreadLocal<SUL<I, O>>() {
                @Override
                protected SUL<I, O> initialValue() {
                    return sul.fork();
                }
            };
        } else {
            this.localSul = null;
        }
    }

    @Override
    public void processQueries(Collection<? extends Query<I, Word<O>>> queries) {
        if (localSul != null) {
            processQueries(localSul.get(), queries);
        } else {
            synchronized (sul) {
                processQueries(sul, queries);
            }
        }
    }

    private static <I, O> void processQueries(SUL<I, O> sul, Collection<? extends Query<I, Word<O>>> queries) {
        for (Query<I, Word<O>> q : queries) {
            Word<O> output = answerQuery(sul, q.getPrefix(), q.getSuffix());
            q.answer(output);
        }
    }

    @Nonnull
    private static <I, O> Word<O> answerQuery(SUL<I, O> sul, Word<I> prefix, Word<I> suffix) throws SULException {
        sul.pre();
        try {
            log.debug("Answering query with prefix [{}] and suffix [{}]", prefix, suffix);
            // Prefix: Execute symbols, don't record output
            for (I sym : prefix) {
                sul.step(sym);
            }

            // Suffix: Execute symbols, outputs constitute output word
            WordBuilder<O> wb = new WordBuilder<>(suffix.length());
            for (I sym : suffix) {
                wb.add(sul.step(sym));
            }

            return wb.toWord();
        } finally {
            sul.post();
        }
    }

}
