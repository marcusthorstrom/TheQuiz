package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
	/**
	 * This Class is the servers runnable
	 *  object, and creates a new every time
	 *  a client has connected to the server
	 * @author Marcus
	 *
	 */
public class ActiveObj implements Runnable{
	private Socket s;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	private QuestionsServer q;

	public ActiveObj(Socket klientS) {
		s = klientS;
	}

	@Override
	public void run() {
		System.out.println(s.getLocalAddress()+" har anslutit till servern");
		q = new QuestionsServer();
		try {
			inStream = new ObjectInputStream(s.getInputStream());
			outStream = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		requestQuestion();				
	}

	@SuppressWarnings("unchecked")
	private void requestQuestion() {
		Object incoming;					//The incoming Object
		while (true) {
			try {
				incoming = inStream.readObject();
				try {
					if(incoming instanceof Integer) {		//If the incoming object is a Integer that means the client has ordered questions
						int noQuestions = (int)incoming;  
						try {
							ArrayList<ArrayList<String>> qList = q.getQuestions(noQuestions);
							outStream.writeObject(qList);
							System.out.println(s.getLocalAddress()+" har hamtat "+noQuestions+" st fragor");

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if(incoming instanceof ArrayList<?>) {		//If the incoming object is a ArrayList then the client wants to write a question
						ArrayList<String> question = new ArrayList<String>();
						question = (ArrayList<String>) incoming;
						question.trimToSize();
						q.writeQuestion(question);
						System.out.println("Skrev frågan: \n" +question.get(0)+"\n"+question.get(1)+"\n"+question.get(2)+"\n"+question.get(3)+"\n"+question.get(4));						
					}
					System.out.println(s.getLocalAddress()+" has disconected.");	//Closing the connection after delivering
					inStream.close();
					s.close();
					break;
				} catch (Exception e) {
				}
			} catch (ClassNotFoundException | IOException e2) {
				e2.printStackTrace();
			}
		}

	}

}
