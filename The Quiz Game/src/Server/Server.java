package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Server {
	private ServerSocket s;

	public Server() throws IOException {
		s = new ServerSocket(13337);
		JFrame frame = new JFrame();
		System.out.println("Waiting for clients...");
		
		while(true) {
			Socket klientS = s.accept();
			new ActiveObj(klientS);
		}
		
	}
	
}
