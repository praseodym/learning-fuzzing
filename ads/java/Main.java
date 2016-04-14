package yannakakis;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import de.learnlib.acex.analyzers.AcexAnalyzers;
import de.learnlib.algorithms.kv.mealy.KearnsVaziraniMealy;
import de.learnlib.algorithms.lstargeneric.ce.ObservationTableCEXHandlers;
import de.learnlib.algorithms.lstargeneric.closing.ClosingStrategies;
import de.learnlib.algorithms.lstargeneric.mealy.ExtensibleLStarMealy;
import de.learnlib.algorithms.ttt.mealy.TTTLearnerMealy;
import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.LearningAlgorithm;
import de.learnlib.api.MembershipOracle;
import de.learnlib.api.Query;
import de.learnlib.counterexamples.LocalSuffixFinders;
import de.learnlib.eqtests.basic.WMethodEQOracle;
import de.learnlib.eqtests.basic.WpMethodEQOracle;
import de.learnlib.oracles.DefaultQuery;
import de.learnlib.oracles.SimulatorOracle;
import de.learnlib.parallelism.ParallelOracle;
import de.learnlib.parallelism.ParallelOracleBuilders;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.automata.transout.impl.compact.CompactMealy;
import net.automatalib.util.graphs.dot.GraphDOT;
import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;
import net.automatalib.words.impl.Alphabets;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Test for the Lee and Yannakakis implementation.
 */
public class Main {

	public static enum Method { LeeYannakakis, WMethod, WpMethod }
	public static enum Learner { LStar, RivestSchapire, TTT, KearnsVazirani }

	public static void main(String[] args) throws IOException {
		// Some settings
		boolean learnRealESM = false;
		boolean saveAllHypotheses = false;
		int threadsForMembershipQueries = 4;

		Method testMethod = Method.LeeYannakakis;
		int maxDepthForWMethod = 2;

		Learner learnAlgorithm = Learner.LStar;

		System.out.println("Setting up SUL");
		Supplier<MembershipOracle.MealyMembershipOracle<String, String>> supplier = null;
		Alphabet<String> alphabet = null;
		List<Alphabet<String>> alphabets = null;

		if(learnRealESM){
			// Real ESM
			// I did not have this class, so I commented this out.
//		    long seed = 78465782;
//		    Random random = new Random(seed);
//		    supplier = new ESMOracleSupplier(random);
//
//		    alphabet = esmSupplier.getInputAlphabet();
//		    Alphabet<String> realSubAlphabet = esmSupplier.getSubAlphabet();
//		    alphabets = Arrays.asList(realSubAlphabet, realAlphabet);
		} else {
			// A simulated ESM (by means of a mealy machine)
			String filename = "esm-manual-controller.dot";
			System.out.println(" Reading dot file: " + filename);
			GraphvizParser p = new GraphvizParser(Paths.get(filename));
			CompactMealy<String, String> fm = p.createMachine();

			alphabet = fm.getInputAlphabet();
			Alphabet<String> simSubAlphabet = Alphabets.fromArray("21.1", "21.0", "22", "53.4", "52.5");
			alphabets = Arrays.asList(simSubAlphabet, alphabet);

			System.out.println(" created machine with " + fm.size() + " states and " + alphabet.size() + " inputs");

			supplier = () -> {
				return new SimulatorOracle.MealySimulatorOracle<>(fm);
			};
		}
		assert supplier != null;
		assert alphabet != null;
		assert alphabets != null;

		System.out.println("Setting up membership oracle");
		MembershipOracle<String, Word<String>> mParallelOracle = ParallelOracleBuilders
				.newDynamicParallelOracle(supplier)
				.withBatchSize(5)
				.withPoolSize(threadsForMembershipQueries)
				.withPoolPolicy(ParallelOracle.PoolPolicy.FIXED)
				.create();


		// We will have the simulating membership oracle. Since the equivalence oracles use a membership oracle to
		// test equality, we make membership oracle which maintain a count. E.g. This way we can count the number of
		// queries needed to find a counterexample.
		CountingMembershipOracle<String, Word<String>> mOracleForLearning = new CountingMembershipOracle<>(mParallelOracle, "learning");
		CountingMembershipOracle<String, Word<String>> mOracleForTesting = new CountingMembershipOracle<>(mParallelOracle, "testing");


		System.out.println("Setting up equivalence oracle");
		EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> eqOracle = null;
		switch (testMethod){
			case LeeYannakakis: eqOracle = new YannakakisEQOracle<>(alphabets, mOracleForTesting); break;
			case WMethod: eqOracle = new WMethodEQOracle.MealyWMethodEQOracle<>(maxDepthForWMethod, mOracleForTesting); break;
			case WpMethod: eqOracle = new WpMethodEQOracle.MealyWpMethodEQOracle<>(maxDepthForWMethod, mOracleForTesting); break;
		}
		assert eqOracle != null;


		System.out.println("Setting up learner");
		LearningAlgorithm<MealyMachine<?, String, ?, String>, String, Word<String>> learner = null;
		switch (learnAlgorithm){
			case LStar:
				learner = new ExtensibleLStarMealy<>(alphabet, mOracleForLearning, Lists.<Word<String>>newArrayList(), ObservationTableCEXHandlers.CLASSIC_LSTAR, ClosingStrategies.CLOSE_SHORTEST);
				break;
			case RivestSchapire:
				learner = new ExtensibleLStarMealy<>(alphabet, mOracleForLearning, Lists.<Word<String>>newArrayList(), ObservationTableCEXHandlers.RIVEST_SCHAPIRE, ClosingStrategies.CLOSE_SHORTEST);
				break;
			case TTT:
				learner = new TTTLearnerMealy<>(alphabet, mOracleForLearning, LocalSuffixFinders.FIND_LINEAR);
				break;
			case KearnsVazirani:
				learner = new KearnsVaziraniMealy<>(alphabet, mOracleForLearning, false, AcexAnalyzers.BINARY_SEARCH);
				break;
		}
		assert learner != null;


		// Here we will perform our experiment. I did not use the Experiment class from LearnLib, as I wanted some
		// more control (for example, I want to reset the counters in the membership oracles). This control flow
		// is suggested by LearnLib (on their wiki).
		System.out.println("Starting experiment\n");
		int stage = 0;
		learner.startLearning();

		while(true) {
			if(saveAllHypotheses) {
				String dir = "/Users/joshua/Documents/PhD/Machines/Mealy/esms3/";
				String filename = dir + "hyp." + stage + ".obf.dot";
				PrintWriter output = new PrintWriter(filename);
				GraphDOT.write(learner.getHypothesisModel(), alphabet, output);
				output.close();
			}

			System.out.println(stage++ + ": " + Calendar.getInstance().getTime());
			System.out.println("Hypothesis: " + learner.getHypothesisModel().getStates().size());
			mOracleForLearning.logAndReset(System.out);
			mOracleForTesting.logAndReset(System.out);
			System.out.println();

			DefaultQuery<String, Word<String>> ce = eqOracle.findCounterExample(learner.getHypothesisModel(), alphabet);
			if(ce == null) break;

			learner.refineHypothesis(ce);
		}

		System.out.println("Done with learning, no counter example found after:");
		mOracleForLearning.logAndReset(System.out);
		mOracleForTesting.logAndReset(System.out);
		System.out.println();

		PrintWriter output = new PrintWriter("last_hypothesis.dot");
		GraphDOT.write(learner.getHypothesisModel(), alphabet, output);
		output.close();
	}

	/**
	 * An membership oracle which maintains a count of the number of queries (and symbols). Usefull for testing
	 * performance of equivalence oracles (which use membership oracles). It simply delegates the queries to
	 * a delegate.
	 * @param <I> Input alphabet
	 * @param <D> Output domain (should be Word<O> for mealy machines)
	 */
	static public class CountingMembershipOracle<I, D> implements MembershipOracle<I, D> {
		private final MembershipOracle<I, D> delegate;
		private final String name;
		private long queries = 0;
		private long symbols = 0;

		public CountingMembershipOracle(MembershipOracle<I, D> delegate, String name){
			this.delegate = delegate;
			this.name = name;
		}

		public void reset(){
			queries = 0;
			symbols = 0;
		}

		public void log(PrintStream output){
			output.println(name + ": queries: " + queries);
			output.println(name + ": symbols: " + symbols);
		}

		public void logAndReset(PrintStream output){
			log(output);
			reset();
		}

		@Override
		public void processQueries(Collection<? extends Query<I, D>> collection) {
			queries += collection.size();
			collection.parallelStream().forEach((Query<I, D> idQuery) -> {
				symbols += idQuery.getInput().size();
			});

			delegate.processQueries(collection);
		}
	}
}
