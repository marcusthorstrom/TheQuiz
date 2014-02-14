import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IO {
	private ArrayList<String> questions;
	private ArrayList<ArrayList<String>> allQuestions;
	private BufferedReader br;
	public IO() {
		
	}
	public ArrayList<ArrayList<String>> readFile() {
		questions = new ArrayList<String>();
		allQuestions = new ArrayList<ArrayList<String>>();
		new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader("\\fragor.txt"));
			String line;
			while((line = br.readLine()) != null) {
				if(line.equals("")){
					@SuppressWarnings("unchecked")
					ArrayList<String> tempQuest = ((ArrayList<String>) questions.clone());
					allQuestions.add(tempQuest);
					questions.clear();
				}
				else {
					questions.add(line);
				}
			}
		}catch (IOException e) {
			System.out.println("ERROR! Error occurd when file was read.");
		}
		return allQuestions;
		//
	} 
}

