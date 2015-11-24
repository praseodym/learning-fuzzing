package net.praseodym.activelearner;

import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealy;
import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealyBuilder;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.MembershipOracle;
import de.learnlib.api.SUL;
import de.learnlib.eqtests.basic.WMethodEQOracle;
import de.learnlib.experiments.Experiment;
import de.learnlib.oracles.CounterOracle;
import de.learnlib.statistics.SimpleProfiler;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.commons.util.mappings.MapMapping;
import net.automatalib.util.graphs.dot.GraphDOT;
import net.automatalib.words.Word;
import net.automatalib.words.impl.SimpleAlphabet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Mealy Machine Learner class
 */
@Component
public class MealyMachineLearner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MealyMachineLearner.class);

    @Autowired
    SUL<String, String> sul;

    private SimpleAlphabet<String> alphabet;

    private Experiment.MealyExperiment<String, String> experiment() {
        alphabet = new SimpleAlphabet<>();
        Stream.of("1", "2", "3", "4", "5", "42").forEach(alphabet::add);

        MapMapping<String, String> errorMapping = new MapMapping<>();

        // FIXME: BasicMembershipOracle is never called?
        MembershipOracle<String, Word<String>> membershipOracle = new CounterOracle.MealyCounterOracle<>(new BasicMembershipOracle(sul), "membership queries");

        // TODO: caching and other stuff

        EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> equivalenceOracle = new WMethodEQOracle.MealyWMethodEQOracle<>(3, new BasicEquivalenceOracle(sul));

        ExtensibleLStarMealy<String, String> learningAlgorithm = new ExtensibleLStarMealyBuilder<String, String>().withAlphabet(alphabet).withOracle(membershipOracle).create();

        return new Experiment.MealyExperiment<>(learningAlgorithm, equivalenceOracle, alphabet);
    }

    private MealyMachine learn() {
        Experiment.MealyExperiment<String, String> experiment = experiment();

        log.info("Starting learning");

        experiment.setProfile(true);
        experiment.setLogModels(true);

        long start = System.currentTimeMillis();
        experiment.run();
        long end = System.currentTimeMillis();

        MealyMachine result = experiment.getFinalHypothesis();

        // report results
        log.info("-------------------------------------------------------");
        // profiling
        log.info(SimpleProfiler.getResults());
        log.info("Total time: " + (end - start) + "ms (" + ((end - start) / 1000) + " s)");
        // learning statistics
        log.info(experiment.getRounds().getSummary());

//        log.info(statsMemOracle.getStatisticalData().getSummary());
//        log.info(statsEQOracle.getStatisticalData().getSummary());
//        log.info(statsCacheEQOracle.getStatisticalData().getSummary());
        log.info("States in final hypothesis: " + result.size());

        return result;
    }

    private void writeResult(MealyMachine mealyMachine) {
        // Copy configuration to output file
        // Files.copy(Paths.get(configFile), Paths.get(learner.config.output_dir + "/config.properties"), StandardCopyOption.REPLACE_EXISTING);

        // Write output to file
//        String outputFilename = learner.config.output_dir + "/learnedModel.dot";
        String outputFilename = "learnedModel.dot";
        String outputFilenamePdf = outputFilename.replace(".dot", ".pdf");
        File dotFile = new File(outputFilename);
        PrintStream psDotFile = null;
        try {
            psDotFile = new PrintStream(dotFile);
            GraphDOT.write(mealyMachine, alphabet, psDotFile);

            // Convert .dot to .pdf
            Runtime.getRuntime().exec("dot -Tpdf -o " + outputFilenamePdf + " " + outputFilename);
        } catch (java.io.IOException e) {
            log.error("Could not write dot file", e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        MealyMachine mealyMachine = learn();
        writeResult(mealyMachine);
    }
}
