package Server;

import java.awt.Container;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * This is the main class for the server, this checks for incoming 
 * connections on port 13337 and accepts them creating a new 
 * thread for every incoming connections.
 */
public class Server {
	private ServerSocket s;
	private ActiveObj a;
	private static JFrame frame;
	public Server() throws IOException {
		s = new ServerSocket(13337);
		System.out.println("Waiting for clients...");
		Container c = frame.getContentPane();
		c.add(new JLabel("Server Is Running...", JLabel.CENTER));

		while(true) {
			Socket klientS = s.accept();
			a = new ActiveObj(klientS);								//This is created for every Connection to the server
			a.run();
		}
	}
	public static void main(String[] args) {
		try {
			frame = new JFrame("The server");						//The frame is used to easier manage opening and closing of the server
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300, 200);
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
