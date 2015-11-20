package net.praseodym.activelearner;

import de.learnlib.algorithms.lstargeneric.ce.ObservationTableCEXHandlers;
import de.learnlib.algorithms.lstargeneric.closing.ClosingStrategies;
import de.learnlib.algorithms.lstargeneric.mealy.ClassicLStarMealy;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.LearningAlgorithm;
import de.learnlib.api.SUL;
import de.learnlib.eqtests.basic.WMethodEQOracle;
import de.learnlib.experiments.Experiment;
import de.learnlib.oracles.SULOracle;
import net.automatalib.commons.util.mappings.MapMapping;
import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;
import net.automatalib.words.impl.SimpleAlphabet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ActivelearnerApplication {

    private final Logger logger = LoggerFactory.getLogger(ActivelearnerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ActivelearnerApplication.class, args);
    }
}
