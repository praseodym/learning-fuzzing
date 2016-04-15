package net.praseodym.activelearner;

import de.learnlib.api.EquivalenceOracle;
import de.learnlib.oracles.DefaultQuery;
import net.automatalib.automata.UniversalDeterministicAutomaton;
import net.automatalib.automata.concepts.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Delegating equivalence oracle: allows use of multiple equivalence oracles
 */
@Component("delegatingEquivalenceOracle")
public class DelegatingEquivalenceOracle<A extends UniversalDeterministicAutomaton<?, I, ?, ?, ?> & Output<I, D>, I, D>
        implements EquivalenceOracle<A, I, D> {

    private final Logger log = LoggerFactory.getLogger(DelegatingEquivalenceOracle.class);

    private EquivalenceOracle<A, I, D>[] equivalenceOracles;

    @Autowired
    @SafeVarargs
    public DelegatingEquivalenceOracle(EquivalenceOracle<A, I, D>... equivalenceOracles) {
        log.info("Configuring delegating equivalence oracle with {}",
                Arrays.stream(equivalenceOracles).map(eq -> eq.getClass().getSimpleName())
                        .collect(Collectors.joining(", ")));
        this.equivalenceOracles = equivalenceOracles;
    }

    @Nullable
    @Override
    public DefaultQuery<I, D> findCounterExample(A hypothesis, Collection<? extends I> inputs) {
        for (EquivalenceOracle<A, I, D> equivalenceOracle : equivalenceOracles) {
            log.debug("Finding counterexample with {}", equivalenceOracle.getClass().getSimpleName());
            DefaultQuery<I, D> counterExample = equivalenceOracle.findCounterExample(hypothesis, inputs);
            if (counterExample != null) {
                log.info("Counterexample found with {}", equivalenceOracle.getClass().getSimpleName());
                return counterExample;
            }
        }
        return null;
    }
}
