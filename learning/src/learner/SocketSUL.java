package learner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import de.learnlib.api.SUL;
import de.learnlib.api.SULException;

/**
 * Socket interface to connect to an SUT/test adapter over TCP.
 * 
 * As an example, type into a unix terminal "nc -vl {ip} {port}" (where {ip} and
 * {port} are the chosen values), and run this socketSUL. You can now control the
 * SUL through the terminal.
 * @author Ramon Janssen
 */
public class SocketSUL implements SUL<String, String>, AutoCloseable {
	private final BufferedReader SULoutput;
	private final PrintWriter SULinput;
	private final Socket socket;
	private final boolean extraNewLine;
	private final String resetCmd;
	
	/**
	 * Socket-interface for SUTs. Connects to a SUT (or test-adapter)
	 * @param ip the ip-address
	 * @param port the tcp-port
	 * @param extraNewLine whether to print a newline after every input to the SUT
	 * @param resetCmd the command to send for resetting the SUT
	 * @throws UnknownHostException 
	 * @throws IOException
	 */
	public SocketSUL(InetAddress ip, int port, boolean extraNewLine, String resetCmd) throws UnknownHostException, IOException {
		this.socket = new Socket(ip, port);
		this.SULoutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.SULinput = new PrintWriter(socket.getOutputStream(), true);
		this.extraNewLine = extraNewLine;
		this.resetCmd = resetCmd;
	}
	
	@Override
	public void post() {
		if (extraNewLine) {
			this.SULinput.write(this.resetCmd + System.lineSeparator());
		} else {
			this.SULinput.write(this.resetCmd);
		}
		this.SULinput.flush();
	}

	@Override
	public void pre() {
		
	}

	@Override
	public String step(String input) throws SULException {
		if (extraNewLine) {
			this.SULinput.write(input + System.lineSeparator());
		} else {
			this.SULinput.write(input);
		}
		this.SULinput.flush();
		try {
			return this.SULoutput.readLine();
		} catch (IOException e) {
			throw new SULException(e);
		}
	}

	@Override
	public void close() throws Exception {
		this.socket.close();
	}
}
