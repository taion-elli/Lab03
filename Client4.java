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

public class Client4 {

	Socket s;
	String host="localhost";//2001:16b8:45b6:f00:91f9:3143:2bcd:d7ca
	final int port= 8051;
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Client4 client = new Client4();
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
			
			try {
				read(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
	};

	// constuctor der nichts macht
	public Client4() throws UnknownHostException, IOException, InterruptedException {
		try (Socket s = new Socket(host, port)) {
			// bis hier wird der code aufgerufen
			// inputstream, outputstream
			this.s=s;
			waitForInput(this.s);
			
			
			ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
			executor.scheduleAtFixedRate(readMessageRunnable, 0, 200, TimeUnit.MILLISECONDS);
			
		}
		
		
		
	
		
	}

	// public void startCient

	public void read(Socket s) throws IOException {
		InputStream instream = s.getInputStream();
		
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
		out.print(command  +"\r\n");
		out.flush();
	}
}
