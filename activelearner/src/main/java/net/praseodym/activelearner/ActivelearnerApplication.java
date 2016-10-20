package net.praseodym.activelearner;

import com.google.common.collect.Lists;
import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealyBuilder;
import de.learnlib.algorithms.ttt.mealy.TTTLearnerMealyBuilder;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.LearningAlgorithm;
import de.learnlib.api.MembershipOracle;
import de.learnlib.api.SUL;
import de.learnlib.cache.mealy.MealyCacheOracle;
import de.learnlib.eqtests.basic.RandomWordsEQOracle;
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
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

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
    public SUL<Byte, String> aflTraceBitmapSul() {
        return new AFLTraceBitmapSUL();
    }

    @Bean
    @Profile("process")
    public SUL<String, String> testSul() {
        return new ProcessSUL();
    }

    @Bean(name = "sul")
    @Profile({"afl", "afltracebitmap"})
    public MembershipOracle.MealyMembershipOracle<Byte, String> aflMealyOracle(AFLSUL sul) {
        return new CounterOracle.MealyCounterOracle<>(new AFLMealyOracle(sul), "AFL SUL queries");
    }

    @Bean(name = "sul")
    @Profile("process")
    public MembershipOracle.MealyMembershipOracle<Byte, String> processMealyOracle(SUL<Byte, String> sul) {
        return new CounterOracle.MealyCounterOracle<>(new LoggingSULOracle<>(sul), "Process SUL queries");
    }

    @Bean(name = "alphabet")
    public SimpleAlphabet<Byte> alphabet() {
        SimpleAlphabet<Byte> alphabet = new SimpleAlphabet<>();
        for (int i = 0; i < 256; i++) {
            alphabet.add((byte) i);
        }
        if (log.isTraceEnabled()) {
            log.trace("Alphabet: {}", Lists.newArrayList(alphabet));
        }
        return alphabet;
    }

    @Profile("mealycache")
    @Bean(name = "learning")
    public CounterOracle.MealyCounterOracle<Byte, String> mealyCacheMembershipOracle(
            @Qualifier("sul") MembershipOracle<Byte, Word<String>> membershipOracle) {
        MapMapping<String, String> errorMapping = new MapMapping<>();
        errorMapping.put("invalid_state", "invalid_state");
        for (int i = 0; i <= 26; i++) {
            for (int j = 1; j <= 99; j++) {
                errorMapping.put(String.format("%d_assert:!error_%d", i, j), "invalid_state");
            }
        }
        // DAG cache throws ConflictExceptions (in some cases), tree cache uses more memory
        log.info("Configuring tree cache Mealy membership oracle");
        MealyCacheOracle<Byte, String> cacheOracle = MealyCacheOracle.createTreeCacheOracle(alphabet(),
                errorMapping, membershipOracle);
        return new CounterOracle.MealyCounterOracle<>(cacheOracle, "Learning membership queries to cache");
    }

    @Bean(name = "learning")
    @Profile("!mealycache")
    public CounterOracle.MealyCounterOracle<Byte, String> learningMembershipOracle(
            @Qualifier("sul") MembershipOracle<Byte, Word<String>> membershipOracle) {
        log.info("Configuring uncached Mealy membership oracle");
        return new CounterOracle.MealyCounterOracle<>(membershipOracle, "Learning membership queries");
    }

    @Bean(name = "testing")
    public CounterOracle.MealyCounterOracle<Byte, String> testingMembershipOracle(
            @Qualifier("sul") MembershipOracle<Byte, Word<String>> membershipOracle) {
        return new CounterOracle.MealyCounterOracle<>(membershipOracle, "Testing membership queries");
    }

    @Bean
    @Profile("lstar")
    public LearningAlgorithm.MealyLearner<Byte, String> lstarLearningAlgorithm(
            @Qualifier("learning") MembershipOracle<Byte, Word<String>> membershipOracle) {
        return new ExtensibleLStarMealyBuilder<Byte, String>().withAlphabet(alphabet()).withOracle
                (membershipOracle).create();
    }

    @Bean
    @Profile("ttt")
    public LearningAlgorithm.MealyLearner<Byte, String> tttLearningAlgorithm(
            @Qualifier("learning") MembershipOracle<Byte, Word<String>> membershipOracle) {
        return new TTTLearnerMealyBuilder<Byte, String>().withAlphabet(alphabet()).withOracle(membershipOracle)
                .create();
    }

    @Bean
    @Profile("afleq")
    @Order(value = 1)
    public EquivalenceOracle<MealyMachine<?, Byte, ?, String>, Byte, Word<String>> aflEquivalence(
            @Value("${learner.afleq.directory}") String equivalenceTestFiles,
            @Qualifier("testing") MembershipOracle<Byte, Word<String>> membershipOracle) throws IOException {
        log.info("Configuring AFL equivalence oracle");
        return new AFLEQOracle<>(alphabet(), membershipOracle, equivalenceTestFiles);
    }

    @Bean
    @Profile("adseq")
    @Order(value = 3)
    public EquivalenceOracle<MealyMachine<?, Byte, ?, String>, Byte, Word<String>> adsEquivalence(
            @Value("${learner.adseq.adsbin}") String adsBinary,
            @Value("${learner.adseq.maxk}") String kMax,
            @Value("${learner.adseq.rndlength}") String rndLength,
            @Value("${learner.adseq.maxtests}") int maxTests,
            @Qualifier("testing") MembershipOracle<Byte, Word<String>> membershipOracle) {
        log.info("Configuring ADS equivalence oracle with max. K {}, rnd length {}, max. tests {}",
                maxTests, kMax, rndLength);
        return new ADSEQOracle<>(adsBinary, membershipOracle, 1, maxTests, "--prefix", "buggy", "=", "random", kMax,
                rndLength);
    }

    @Bean
    @Profile("randomeq")
    @Order(value = 3)
    public EquivalenceOracle<MealyMachine<?, Byte, ?, String>, Byte, Word<String>> randomEquivalence(
            @Value("${learner.randomeq.minlength}") int minLength,
            @Value("${learner.randomeq.maxlength}") int maxLength,
            @Value("${learner.randomeq.maxtests}") int maxTests,
            @Qualifier("testing") MembershipOracle<Byte, Word<String>> membershipOracle) {
        log.info("Configuring random words equivalence oracle with min. length {}, max. length {}, max. tests {}",
                minLength, maxLength, maxTests);
        return new RandomWordsEQOracle.MealyRandomWordsEQOracle<>(membershipOracle, minLength, maxLength, maxTests,
                new Random());
    }

    @Bean
    @Profile("wmethodeq")
    @Order(value = 4)
    public EquivalenceOracle<MealyMachine<?, Byte, ?, String>, Byte, Word<String>> wmethodEquivalence(
            @Value("${learner.wmethodeq.maxdepth}") int wmethodMaxDepth,
            @Qualifier("testing") MembershipOracle<Byte, Word<String>> membershipOracle) {
        assert wmethodMaxDepth > 0;
        log.info("Configuring W-method equivalence oracle with max. depth {}", wmethodMaxDepth);
        return new WMethodEQOracle.MealyWMethodEQOracle<>(wmethodMaxDepth, membershipOracle);
    }

    @Bean
    public Experiment.MealyExperiment<Byte, String> experiment(
            LearningAlgorithm.MealyLearner<Byte, String> learningAlgorithm,
            @Qualifier("delegatingEquivalenceOracle")
                    EquivalenceOracle<MealyMachine<?, Byte, ?, String>, Byte, Word<String>> equivalenceOracle) {
        return new Experiment.MealyExperiment<>(learningAlgorithm, equivalenceOracle, alphabet());
    }
}
