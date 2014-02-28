package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ConnectionToServer {
	private static Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	/**
	 * The Class responsible for the serverCommunication
	 * @throws IOException
	 */
	public ConnectionToServer() throws IOException {
		InetAddress ip = null;
		ip = InetAddress.getByName("localhost");
		socket = new Socket(ip, 13337);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	/**
	 * Method for writing Questions over the server
	 * @param The Question as a SingleQuestionClient object
	 * @throws IOException
	 */
	public void writeQuestion(SingleQuestionClient qu) throws IOException {

		try {
			if (!qu.isEmpty()) {
				ArrayList<String> tempQuestions = new ArrayList<String>();
				tempQuestions = qu.printArrayList(tempQuestions);
				out.writeObject(tempQuestions);
			} else {
				System.out.println("Did not write");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}

	/**
	 * Asks for questions from server Returns questions from sever to GModel
	 * 
	 * @param gameRounds
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SingleQuestionClient> getQuestions(int gameRounds)
			throws IOException {
		ArrayList<SingleQuestionClient> sqList = new ArrayList<SingleQuestionClient>();
		ArrayList<ArrayList<String>> aal = new ArrayList<ArrayList<String>>();

		/**
		 * Tries to ask server for x numbers of questions
		 */
		try {
			out.writeObject(gameRounds);
			/**
			 * If succeeded, tires to read from server
			 */
			try {
				aal = (ArrayList<ArrayList<String>>) in.readObject();

				/**
				 * trims all Null elements out of ArrayLists
				 */
				aal.trimToSize();
				for (ArrayList<String> q : aal) {
					q.trimToSize();
					sqList.add(new SingleQuestionClient(q));
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
