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
		int noQuestions;
		while (true) {
		    try {
		    	noQuestions = (int)inStream.readObject();     
			} catch (Exception e) {
				try {
					inStream.close();
					s.close();
				} catch (IOException e1) { e1.printStackTrace();}
				return;
			}
		    try {
		    	ArrayList<ArrayList<String>> qList = q.getQuestions(noQuestions);
				outStream.writeObject(qList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
