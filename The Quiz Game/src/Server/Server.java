package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket s;
	private ActiveObj a;
	
	public Server() throws IOException {

		s = new ServerSocket(13337);
		System.out.println("Waiting for clients...");
		
		while(true) {
			Socket klientS = s.accept();
			a = new ActiveObj(klientS);
			a.run();
		}
		
	}
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
