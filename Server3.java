import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server3 {

	public static void main(String[] args) throws IOException{
		Server3 server3=new Server3();
		
		
	}
	
	public Server3() throws IOException {
		final int serverPort=8051;
		ServerSocket server= new ServerSocket(serverPort);
		System.out.println("Waiting for client to connect");
	
	while(true) {
		try (Socket s =server.accept()){
			System.out.println("Client connected");
			
			Scanner in = new Scanner(s.getInputStream());
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
			
			while(in.hasNext()) {
				String command= in.nextLine();
				System.out.println(command);
				out.print(command+"\r\n");
				out.flush();
				if(command.equals("QUIT")) {server.close();return;}

			}
				
		}
		
		
	}
	
	}

}
