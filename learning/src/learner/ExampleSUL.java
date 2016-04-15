package learner;

import de.learnlib.api.SUL;
import de.learnlib.api.SULException;

/**
 * Example of a three-state system, hard-coded.
 * 
 * @author Ramon Janssen
 */
public class ExampleSUL implements SUL<String, String> {
	private enum State{s0,s1,s2};
	private State currentState;
	private static boolean VERBOSE = false;
	
	@Override
	public void pre() {
		// add any code here that should be run at the beginning of every 'session',
		// i.e. put the system in its initial state
		if (VERBOSE) {
			System.out.println("Starting SUL");
		}
		currentState = State.s0;
	}
	
	@Override
	public void post() {
		// add any code here that should be run at the end of every 'session'
		if (VERBOSE) {
			System.out.println("Shutting down SUL");
		}
	}

	@Override
	public String step(String input) throws SULException {
		State previousState = this.currentState;
		String output = makeTransition(input);
		State nextState = this.currentState; 
		if (VERBOSE) {
			System.out.println(previousState + " --" + input + "/" + output + "-> " + nextState);
		}
		return output;
	}
	
	/**
	 * The behaviour of the SUL. It takes one input, and returns an output. It now
	 * contains a hardcoded state-machine (so the result is easy to check). To learn
	 * an external program/system, connect this method to the SUL (e.g. via sockets
	 * or stdin/stdout) and make it perform an actual input, and retrieve an actual
	 * output.
	 * @param input
	 * @return
	 */
	public String makeTransition(String input) {
		switch (currentState) {
		case s0:
			switch(input) {
			case "a":
				currentState = State.s1;
				return "x";
			case "b":
				currentState = State.s2;
				return "y";
			case "c":
				return "z";
			}
		case s1:
			switch(input) {
			case "a":
				return "z";
			case "b":
				currentState = State.s2;
				return "y";
			case "c":
				return "z";
			}
		case s2:
			switch(input) {
			case "a":
				return "z";
			case "b":
				currentState = State.s0;
				return "y";
			case "c":
				return "z";
			}
		}
		throw new SULException(new IllegalArgumentException("Argument '" + input + "' was not handled"));
	}
}
