package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Server {
	private ServerSocket s;
	private ActiveObj a;
	
	public Server() throws IOException {
		s = new ServerSocket(13337);
		JFrame window = new JFrame("Server");
		window.setVisible(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
