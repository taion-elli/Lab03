import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
	}

	
	public void run (String serverAddress, int port, String commandString) throws IOException {
		try(Socket s = new Socket(serverAddress,port)){
			InputStream instream= s.getInputStream();
			OutputStream outstream = s.getOutputStream();
			
			Scanner in = new Scanner(instream);
			PrintWriter out = new PrintWriter(outstream);
			
			
			String command = commandString;
			System.out.println("Sending: "+command);
			out.print(command);
			out.flush();
			
			while (in.hasNext()) {
				String input=in.nextLine();
				System.out.println(input);
				
			}
		}
		
	}
	
	
}
