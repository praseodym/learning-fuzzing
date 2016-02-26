package net.praseodym.activelearner;

import de.learnlib.experiments.Experiment;
import de.learnlib.statistics.SimpleProfiler;
import de.learnlib.statistics.StatisticOracle;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.util.graphs.dot.GraphDOT;
import net.automatalib.words.Alphabet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mealy Machine Learner class
 */
@Component
public class MealyMachineLearner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MealyMachineLearner.class);

    @Resource
    private Alphabet<String> alphabet;

    @Autowired
    private Experiment.MealyExperiment<String, String> experiment;

    @Autowired
    private StatisticOracle[] statisticOracles;

    private MealyMachine learn() {
        log.info("Starting learning");

        experiment.setProfile(true);
        experiment.setLogModels(true);

        long start = System.currentTimeMillis();
        experiment.run();
        long end = System.currentTimeMillis();

        MealyMachine result = experiment.getFinalHypothesis();

        // report results
        log.info("-------------------------------------------------------");
        logSummary(SimpleProfiler.getResults());
        log.info("Total time: " + (end - start) + "ms (" + ((end - start) / 1000) + " s)");
        logSummary(experiment.getRounds().getSummary());
        for (StatisticOracle statisticOracle : statisticOracles) {
            logSummary(statisticOracle.getStatisticalData().getSummary());
        }
        log.info("States in final hypothesis: " + result.size());
        log.info("-------------------------------------------------------");

        return result;
    }

    private void logSummary(String summary) {
        Arrays.stream(summary.split(System.lineSeparator())).forEach(log::info);
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
        List<String> lines = Files.readAllLines(Paths.get(outputFilename)).stream()
                .filter(s -> !s.contains("invalid_state")).collect(Collectors.toList());
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
