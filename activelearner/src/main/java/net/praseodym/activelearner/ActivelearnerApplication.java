package net.praseodym.activelearner;

import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.MembershipOracle;
import de.learnlib.api.SUL;
import de.learnlib.cache.mealy.MealyCacheOracle;
import de.learnlib.eqtests.basic.WMethodEQOracle;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.commons.util.mappings.MapMapping;
import net.automatalib.words.Word;
import net.automatalib.words.impl.SimpleAlphabet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@SpringBootApplication
//@EnableCaching
public class ActivelearnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivelearnerApplication.class, args);
    }

    @Bean(name = "mealyOracle")
    @Profile("afl")
    public MembershipOracle.MealyMembershipOracle<String, String> aflMealyOracle(SUL<String, String> sul) {
        // dumb method
//        return new LoggingSULOracle<String, String>(sul);
        return new AFLMealyOracle();
    }

    @Bean
    @Profile("afl")
    public SUL<String, String> aflSul() {
        return new AFLSUL();
    }

    @Bean(name = "mealyOracle")
    @Profile("!afl")
    public MembershipOracle.MealyMembershipOracle<String, String> processMealyOracle(SUL<String, String> sul) {
        return new LoggingSULOracle<>(sul);
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
    public MembershipOracle<String, Word<String>> mealyMembershipOracle(MembershipOracle<String, Word<String>> mealyOracle) {
        MapMapping<String, String> errorMapping = new MapMapping<>();
        errorMapping.put("invalid_state", "invalid_state");
        for (int i = 0; i <= 26; i++) {
            for (int j = 1; j <= 99; j++) {
                errorMapping.put(String.format("%d_assert:!error_%d", i, j), "invalid_state");
            }
        }

//        return new CounterOracle.MealyCounterOracle<>(mealyMembershipOracle, "membership queries");
        return MealyCacheOracle.createDAGCacheOracle(alphabet(), errorMapping, mealyOracle);
    }

    @Bean
    @Profile("afleq")
    public EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> aflEquivalence(@Value("${learner.afleq.directory}") String equivalenceTestFiles, MembershipOracle<String, Word<String>> membershipOracle) {
        return new AFLEQOracle<>(alphabet(), membershipOracle, equivalenceTestFiles);
    }

    @Bean
    @Profile("wmethodeq")
    public EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> wmethodEquivalence(@Value("${learner.wmethodeq.maxdepth}") int wmethodMaxDepth, MembershipOracle<String, Word<String>> membershipOracle) {
        assert wmethodMaxDepth > 0;

        return new WMethodEQOracle.MealyWMethodEQOracle<>(wmethodMaxDepth, membershipOracle);
    }

    @Bean
    @Profile("!afl")
    public SUL<String, String> testSul() {
        return new ProcessSUL();
    }
}