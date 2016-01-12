package net.praseodym.activelearner;

import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealy;
import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealyBuilder;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.MembershipOracle;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Mealy Machine Learner class
 */
@Component
public class MealyMachineLearner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MealyMachineLearner.class);


    @Autowired
    private MembershipOracle.MealyMembershipOracle<String, String> mealyMembershipOracle;

    @Value("${learner.wmethod.maxdepth}")
    private int wmethodMaxDepth;

    @Value("${learner.alphabet}")
    private String alphabetRaw;

    private SimpleAlphabet<String> alphabet;

    private Experiment.MealyExperiment<String, String> experiment() {
        assert wmethodMaxDepth > 0;

        alphabet = new SimpleAlphabet<>();
        Arrays.stream(alphabetRaw.split(",")).forEach(alphabet::add);

        MapMapping<String, String> errorMapping = new MapMapping<>();

        MembershipOracle<String, Word<String>> membershipOracle = new CounterOracle.MealyCounterOracle<>(mealyMembershipOracle, "membership queries");

        // TODO: caching and other stuff
        EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> equivalenceOracle = new WMethodEQOracle.MealyWMethodEQOracle<>(wmethodMaxDepth, mealyMembershipOracle);

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

    private void writeResult(MealyMachine mealyMachine) throws IOException {
        // Copy configuration to output file
        // Files.copy(Paths.get(configFile), Paths.get(learner.config.output_dir + "/config.properties"), StandardCopyOption.REPLACE_EXISTING);

        // Write output to file and convert to pdf
//        String outputFilename = learner.config.output_dir + "/learnedModel.dot";
        String outputFilename = "learnedModel.dot";
        String outputFilenamePdf = outputFilename.replace(".dot", ".pdf");
        File dotFile = new File(outputFilename);
        PrintStream psDotFile = new PrintStream(dotFile);
        GraphDOT.write(mealyMachine, alphabet, psDotFile);
        Runtime.getRuntime().exec("dot -Tpdf -o " + outputFilenamePdf + " " + outputFilename);

        // Simplify .dot file and convert to pdf
        List<String> lines = Files.readAllLines(Paths.get(outputFilename));
        List<String> simpified = SimplifyDot.simplifyDot(lines);
        String simplifiedOutputFilename = outputFilename.replace(".dot", "_simple.dot");
        Files.write(Paths.get(simplifiedOutputFilename), simpified, Charset.defaultCharset());
        String simplifiedOutputFilenamePdf = outputFilenamePdf.replace(".pdf", "_simple.pdf");
        Runtime.getRuntime().exec("dot -Tpdf -o " + simplifiedOutputFilenamePdf + " " + simplifiedOutputFilename);
    }

    @Override
    public void run(String... args) throws Exception {
        MealyMachine mealyMachine = learn();
        writeResult(mealyMachine);
    }
}
