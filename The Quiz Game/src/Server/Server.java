package Server;

import java.awt.Container;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Server {
	private ServerSocket s;
	private ActiveObj a;
	private static JFrame frame;
	private JLabel l;
	public Server() throws IOException {
		
		s = new ServerSocket(13337);
		System.out.println("Waiting for clients...");
		Container c = frame.getContentPane();
		c.add(l = new JLabel("Server Is Running...", JLabel.CENTER));
		
		
		
		
		
		while(true) {
			Socket klientS = s.accept();
			a = new ActiveObj(klientS);
			a.run();
		}
		
	}
	public static void main(String[] args) {
		try {
			frame = new JFrame("The server");
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300, 200);
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
