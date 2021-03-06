package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
/**
 * The Class responsible for the serverCommunication
 * @throws IOException
 */
public class ConnectionToServer {
	private static Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ConnectionToServer() throws IOException {
		InetAddress ip = InetAddress.getLocalHost();
		socket = new Socket();
		socket.connect(new InetSocketAddress(ip, 13337), 1000);
		socket.setSoTimeout(1000);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	/*
	 * Method for writing Questions over the server
	 */
	public void writeQuestion(SingleQuestion qu) throws IOException {

		try {
			if (!qu.isEmpty()) {
				ArrayList<String> tempQuestions = new ArrayList<String>();
				tempQuestions = qu.printArray();
				out.writeObject(tempQuestions);
			} else {
				System.out.println("Did not write");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}

	/*
	 * Asks for questions from server Returns questions from sever to GModel
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SingleQuestion> getQuestions(int gameRounds)
			throws IOException {
		ArrayList<SingleQuestion> sqList = new ArrayList<SingleQuestion>();
		ArrayList<ArrayList<String>> aal = new ArrayList<ArrayList<String>>();

		/*
		 * Tries to ask server for x numbers of questions
		 */
		try {
			out.writeObject(gameRounds);
			/*
			 * If succeeded, tires to read from server
			 */
			try {
				aal = (ArrayList<ArrayList<String>>) in.readObject();

				/*
				 * trims all Null elements out of ArrayLists
				 */
				aal.trimToSize();
				for (ArrayList<String> q : aal) {
					q.trimToSize();
					if(!q.isEmpty()) {
						sqList.add(new SingleQuestion(q));
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SocketException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
		return sqList;
	}
}
