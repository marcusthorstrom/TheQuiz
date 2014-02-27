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
							System.out.println(s.getLocalAddress()+" vill h�mta "+noQuestions+" st fr�gor");
							ArrayList<ArrayList<String>> qList = q.getQuestions(noQuestions);
							outStream.writeObject(qList);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(incoming instanceof ArrayList<?>) {

						System.out.println("FRÅGA SKA SKRIVAS");
						ArrayList<String> question = new ArrayList<String>(); //inStream.readObject();
						
						question = (ArrayList<String>) incoming;
						
						System.out.println("Efter lagt i array");
						question.trimToSize();
						System.out.println("Efter trim");
						q.writeQuestion(question);
						System.out.println("Fr�gan skriven.");

					}
				} catch (Exception e) {
					try {
						inStream.close();
						s.close();
					} catch (IOException e1) { e1.printStackTrace();}
					return;
				}
			} catch (ClassNotFoundException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

	}

}
