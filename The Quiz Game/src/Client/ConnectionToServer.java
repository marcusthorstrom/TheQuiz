package Client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Server.SingleQuestion;

public class ConnectionToServer {
	private static Socket socket;
	private boolean connection;
	private InetAddress ip = null;
	private DataInputStream in;
	private DataOutputStream out;
	private byte byteS;
	private int serviceWrite = 1; //For writing to the server
	private int serviceRead = 0;  //For reading to the server
	
	public ConnectionToServer() throws IOException {
		InetAddress ip;
		ip = InetAddress.getByName("localHost");
			socket = new Socket(ip, 13337);
			connection = false;
			
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		
	}
	public void writeQuestion(SingleQuestion qu) {
		try {
			
			out.write(serialize(qu));
		} catch (IOException e) {
			System.out.println("Not able to serialize the object");
			e.printStackTrace();
		}
		
	}
	public void getQuestions(int gameRounds) throws IOException {
		out.writeInt(gameRounds);
		byteS = in.readByte();
	}

	public static byte[] serialize(SingleQuestion obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
}
