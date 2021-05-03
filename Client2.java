import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client2 {

	String host="2001:16b8:45b6:f00:91f9:3143:2bcd:d7ca";
	final static int port= 8051;
	
	
	public static void main(String[] args) throws IOException {

		Client2 client = new Client2();

		String host;
		String resource;
		if (args.length == 2) {
			host = args[0];
			resource = args[1];
		} else {
			host = "2001:16b8:45b6:f00:91f9:3143:2bcd:d7ca";
			resource = "/";
		}


		String commandString = "hi";

		// Scanner reader =new Scanner()
		

		try (Socket s = new Socket(host, port)) {

			// bis hier wird der code aufgerufen
			// inputstream, outputstream
			client.waitForInput(s);

		}
	}

	public void waitForInput(Socket socket) {
		System.out.println("Use the console to send messages.");
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				String line = scanner.nextLine();
				try {
					write(socket, line);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (IllegalStateException e) {
			// System.in has been closed
			e.printStackTrace();
		}
	}

	Runnable readMessageRunnable = new Runnable() {
		@Override
		public void run() {
			try (Socket s = new Socket(host, 8051)) {
			read(s);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}}
	};

	// constuctor der nichts macht
	public Client2() throws UnknownHostException, IOException {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.scheduleAtFixedRate(readMessageRunnable, 0, 200, TimeUnit.MILLISECONDS);
	}

	// public void startCient

	public void read(Socket s) throws IOException {
		InputStream instream = s.getInputStream();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(instream);
		while (in.hasNext()) {
			String input = in.nextLine();
			System.out.println(input);
		}
	}

	public void write(Socket s, String commandString) throws IOException {
		OutputStream outstream = s.getOutputStream();
		PrintWriter out = new PrintWriter(outstream);
		String command = commandString;
		System.out.println("Sending: " + command);
		out.print(command);
		out.flush();

	}
}
