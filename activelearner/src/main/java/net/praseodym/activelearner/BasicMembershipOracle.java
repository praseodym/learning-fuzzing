package net.praseodym.activelearner;

import de.learnlib.api.SUL;
import de.learnlib.oracles.SULOracle;
import net.automatalib.words.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Basic membership oracle
 */
public class BasicMembershipOracle extends SULOracle<String, String> {

    private static final Logger log = LoggerFactory.getLogger(BasicMembershipOracle.class);

    int nrQueries = 0;

    public BasicMembershipOracle(SUL<String, String> sul) {
        super(sul);
    }

    @Override
    public Word<String> answerQuery(Word<String> prefix, Word<String> suffix) {
        nrQueries++;
        log.info("Membership query {}: {} | {}", nrQueries,  prefix, suffix);
        Word<String> answer = super.answerQuery(prefix, suffix);
        log.info("Answer: {}", answer);
        return answer;
    }
}

