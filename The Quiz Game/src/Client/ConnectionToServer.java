package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Server.SingleQuestion;

public class ConnectionToServer {
	private static Socket socket;
	private boolean connection;
	private InetAddress ip = null;
	
	public ConnectionToServer() throws IOException {
		InetAddress ip;
		ip = InetAddress.getByName("localHost");
		try {
			socket = new Socket(ip, 13337);
		} catch (IOException e) {
			connection = false;
			e.printStackTrace();
		}

		DataInputStream out = new DataInputStream(socket.getInputStream());
		DataOutputStream in = new DataOutputStream(socket.getOutputStream());


	}
	public boolean conectionIsActive() {
		return false;
	}
	public void writeQuestion(SingleQuestion qu) {
		
		
	}
	public void getQuestions(int gameRounds) {
		
	}

}
