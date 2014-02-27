package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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

	private void requestQuestion() {
		Object incoming;
		while (true) {
			try {
				incoming = inStream.readObject();
				try {
					if(incoming instanceof Integer) {
						int noQuestions = (int)incoming;  
						try {
							ArrayList<ArrayList<String>> qList = q.getQuestions(noQuestions);
							outStream.writeObject(qList);
							System.out.println(s.getLocalAddress()+" har hämtat "+noQuestions+" st frågor");

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if(incoming instanceof ArrayList<?>) {
						ArrayList<String> question = new ArrayList<String>();
						question = (ArrayList<String>) incoming;
						question.trimToSize();
						q.writeQuestion(question);
						System.out.println("Fråga skriven.");						
					}
					inStream.close();
					System.out.println(s.getLocalAddress()+" has disconected.");
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
