package net.praseodym.activelearner;

import de.learnlib.api.MembershipOracle;
import de.learnlib.api.SUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableCaching
public class ActivelearnerApplication {

    private final Logger logger = LoggerFactory.getLogger(ActivelearnerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ActivelearnerApplication.class, args);
    }

    @Bean
    @Profile("forkserver")
    public MembershipOracle.MealyMembershipOracle<String, String> forkserverMealyOracle(SUL<String, String> sul) {
        // dumb method
//        return new LoggingSULOracle<String, String>(sul);
        return new ForkServerMealyOracle();
    }

    @Bean
    @Profile("forkserver")
    public SUL<String, String> forkserverSul() {
        return new ForkServerSUL();
    }

    @Bean
    @Profile("!forkserver")
    public MembershipOracle.MealyMembershipOracle<String, String> processMealyOracle(SUL<String, String> sul) {
        return new LoggingSULOracle<String, String>(sul);
    }

    @Bean
    @Profile("!forkserver")
    public SUL<String, String> testSul() {
        return new ProcessSUL();
    }
}