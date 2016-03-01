package net.praseodym.activelearner;

import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealy;
import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealyBuilder;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.MembershipOracle;
import de.learnlib.api.SUL;
import de.learnlib.cache.mealy.MealyCacheOracle;
import de.learnlib.eqtests.basic.WMethodEQOracle;
import de.learnlib.experiments.Experiment;
import de.learnlib.oracles.CounterOracle;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.commons.util.mappings.MapMapping;
import net.automatalib.words.Word;
import net.automatalib.words.impl.SimpleAlphabet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@SpringBootApplication
//@EnableCaching
public class ActivelearnerApplication {

    private static final Logger log = LoggerFactory.getLogger(ActivelearnerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ActivelearnerApplication.class, args);
    }

    @Bean
    @Profile("afl")
    public AFLSUL aflSul() {
        return new AFLSUL();
    }

    @Bean
    @Profile("afltracebitmap")
    public SUL<String, String> aflTraceBitmapSul() {
        return new AFLTraceBitmapSUL();
    }

    @Bean
    @Profile("process")
    public SUL<String, String> testSul() {
        return new ProcessSUL();
    }

    @Bean(name = "mealyOracle")
    @Profile({"afl", "afltracebitmap"})
    public CounterOracle.MealyCounterOracle<String, String> aflMealyOracle(AFLSUL sul) {
        return new CounterOracle.MealyCounterOracle<>(new AFLMealyOracle(sul), "AFL SUL queries");
    }

    @Bean(name = "mealyOracle")
    @Profile("process")
    public CounterOracle.MealyCounterOracle<String, String> processMealyOracle(SUL<String, String> sul) {
        return new CounterOracle.MealyCounterOracle<>(new LoggingSULOracle<>(sul), "Process SUL queries");
    }

    @Value("${learner.alphabet}")
    String alphabetRaw;

    @Bean(name = "alphabet")
    public SimpleAlphabet<String> alphabet() {
        SimpleAlphabet<String> alphabet = new SimpleAlphabet<>();
        Arrays.stream(alphabetRaw.split(",")).forEach(alphabet::add);
        return alphabet;
    }

    @Bean(name = "membershipOracle")
    @Profile("mealycache")
    public CounterOracle.MealyCounterOracle<String, String> mealyCacheMembershipOracle(
            MembershipOracle<String, Word<String>> mealyOracle) {
        // Mealy cache is faster but has bugs (ConflictExceptions) in some cases.
        MapMapping<String, String> errorMapping = new MapMapping<>();
        errorMapping.put("invalid_state", "invalid_state");
        for (int i = 0; i <= 26; i++) {
            for (int j = 1; j <= 99; j++) {
                errorMapping.put(String.format("%d_assert:!error_%d", i, j), "invalid_state");
            }
        }
        // DAG cache throws ConflictExceptions (in some cases), tree cache uses more memory
        log.info("Configuring Mealy tree cache membership oracle");
        mealyOracle = MealyCacheOracle.createTreeCacheOracle(alphabet(), errorMapping, mealyOracle);
        return new CounterOracle.MealyCounterOracle<>(mealyOracle, "Membership queries to cache");
    }

    @Bean(name = "membershipOracle")
    @Profile("!mealycache")
    public CounterOracle.MealyCounterOracle<String, String> mealyMembershipOracle(
            @Qualifier("mealyOracle") CounterOracle.MealyCounterOracle<String, String> mealyOracle) {
        log.info("Configuring uncached Mealy membership oracle");
        return new CounterOracle.MealyCounterOracle<>(mealyOracle, "Membership queries");
    }

    @Bean(name = "eqOracle")
    public CounterOracle.MealyCounterOracle<String, String> eqOracle(
            @Qualifier("mealyOracle") CounterOracle.MealyCounterOracle<String, String> mealyOracle) {
        return new CounterOracle.MealyCounterOracle<>(mealyOracle, "Equivalence queries");
    }

    @Bean
    @Profile("afleq")
    public EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> aflEquivalence(
            @Value("${learner.afleq.directory}") String equivalenceTestFiles,
            CounterOracle.MealyCounterOracle<String, String> eqOracle) {
        log.info("Configuring AFL equivalence");
        return new AFLEQOracle<>(alphabet(), eqOracle, equivalenceTestFiles);
    }

    @Bean
    @Profile("wmethodeq")
    public EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> wmethodEquivalence(
            @Value("${learner.wmethodeq.maxdepth}") int wmethodMaxDepth,
            CounterOracle.MealyCounterOracle<String, String> eqOracle) {
        assert wmethodMaxDepth > 0;
        log.info("Configuring W-method equivalence with max. depth {}", wmethodMaxDepth);
        return new WMethodEQOracle.MealyWMethodEQOracle<>(wmethodMaxDepth, eqOracle);
    }

    @Bean
    public Experiment.MealyExperiment<String, String> experiment(
            MembershipOracle<String, Word<String>> membershipOracle,
            EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> equivalenceOracle) {
        ExtensibleLStarMealy<String, String> learningAlgorithm = new ExtensibleLStarMealyBuilder<String, String>().withAlphabet(alphabet()).withOracle(membershipOracle).create();
        return new Experiment.MealyExperiment<>(learningAlgorithm, equivalenceOracle, alphabet());
    }
}