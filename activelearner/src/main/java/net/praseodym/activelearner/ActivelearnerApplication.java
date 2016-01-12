package net.praseodym.activelearner;

import de.learnlib.api.MembershipOracle;
import de.learnlib.api.SUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@EnableCaching
public class ActivelearnerApplication {

    private final Logger log = LoggerFactory.getLogger(ActivelearnerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ActivelearnerApplication.class, args);
    }

    @Bean
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

    @Bean
    @Profile("!afl")
    public MembershipOracle.MealyMembershipOracle<String, String> processMealyOracle(SUL<String, String> sul) {
        return new LoggingSULOracle<>(sul);
    }

    @Bean
    @Profile("!afl")
    public SUL<String, String> testSul() {
        return new ProcessSUL();
    }
}