package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import Server.SingleQuestion;

public class ConnectionToServer {
	private static Socket socket;
	private InetAddress ip;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ConnectionToServer() throws IOException {
		InetAddress ip = null;
		ip = InetAddress.getByName("localHost");
		socket = new Socket(ip, 13337);

		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());

	}

	/*
	 * public void writeQuestion(SingleQuestion qu) { try {
	 * 
	 * out.write(serialize(qu)); } cat(SingleQuestion) o.readObject()ch
	 * (IOException e) { System.out.println("Not able to serialize the object");
	 * e.printStackTrace(); }
	 * 
	 * }
	 */


	public ArrayList<SingleQuestion> getQuestions(int gameRounds){
		ArrayList<SingleQuestion> sqList = new ArrayList<SingleQuestion>();
		ArrayList<ArrayList<String>> aal = new ArrayList<ArrayList<String>>();
		
		try{
			out.writeObject(gameRounds);
			try{
				aal=(ArrayList<ArrayList<String>>)in.readObject();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}catch(SocketException e){
			System.out.println(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqList;
	}
}
