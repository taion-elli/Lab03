import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException{
		
		
		final int serverPort=8889;
		ServerSocket server= new ServerSocket(serverPort);
		System.out.println("Waiting for client to connect");
	
	while(true) {
		try (Socket s =server.accept()){
			System.out.println("Client connected");

			
			Scanner in = new Scanner(s.getInputStream());
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
			while(true) {
				if(!in.hasNext()) {return; }
				String command= in.next();
				System.out.println(command);
				out.print(command);
				out.flush();
				if(command.equals("QUIT")) {server.close();return;}
			}
				
		}
		
		
	}
	
	}

	}
