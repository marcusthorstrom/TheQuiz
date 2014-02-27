import java.io.*;
import java.util.*;


public class IO {
	
	private static final String STANDARDFRAGOR = "fragor.txt"; 
	private ArrayList<String> questions;
	private ArrayList<ArrayList<String>> allQuestions;
	private BufferedReader br;
	public IO() {
	}
	private void readFile() {
		questions = new ArrayList<String>();
		allQuestions = new ArrayList<ArrayList<String>>();
		try {
			
			if (OSDetector.isMac()){
				//br = new BufferedReader(new FileReader(System.getProperty ("user.home") + "/Desktop/" + STANDARDFRAGOR));
				br = new BufferedReader(new FileReader(STANDARDFRAGOR));
			}
			else if (OSDetector.isWindows())
			{
				br = new BufferedReader(new FileReader(STANDARDFRAGOR));
			}
			
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
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		
		catch (IOException e) {
			System.out.println("ERROR! Error occurd when file was read.");
		}
	}
	public ArrayList<ArrayList<String>> getArray(){
		readFile();
		return allQuestions;
	}
	
	/**
	 * Method for adding questions to fragor.txt
	 * 
	 */
	public void newQuestion(ArrayList<String> question){
		try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(STANDARDFRAGOR,true));	
	        
	        Iterator<String> it = question.iterator(); //Sets an iterator to the arrayList in order iterate through it.
	        while(it.hasNext()){
	        	bw.write(it.next()+"\n");		
	        }
	        bw.write("\n");
	        bw.close();
	        
	        } 
		catch (IOException e) {
			System.out.println("ERROR! Error occurd when write");
		}
	} 
}