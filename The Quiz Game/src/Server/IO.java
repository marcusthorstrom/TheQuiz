package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class IO {

	private static final String STANDARDFRAGOR = "fragor.txt";
	private ArrayList<String> questions;
	private ArrayList<ArrayList<String>> allQuestions;
	private BufferedReader br;

	public IO() {
	}

	/**
	 * Method for reading questions from .txt file.
	 */
	private void readFile() {
		questions = new ArrayList<String>();
		allQuestions = new ArrayList<ArrayList<String>>();
		try {
			/*
			 * Checks what operating system the computer is running, then open
			 * the file in different ways to get Swedish characters
			 */
			if (OSDetector.isMac()) {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(STANDARDFRAGOR), "ISO-8859-1"));
			} else if (OSDetector.isWindows()) {
				br = new BufferedReader(new FileReader(STANDARDFRAGOR));

				//
			} else if (OSDetector.isLinux()) {
				String file = (System.getProperty("user.home") + "/Desktop/" + STANDARDFRAGOR);
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file), "ISO-8859-1"));

			}
			/*
			 * reads line by line and add them to an ArrayList of strings, if
			 * new row without signs the ArrayList will be added to a bigger
			 * ArrrayList
			 */
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals("")) {
					@SuppressWarnings("unchecked")
					ArrayList<String> tempQuest = ((ArrayList<String>) questions
							.clone());
					allQuestions.add(tempQuest);
					questions.clear();
				} else {
					questions.add(line);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}

		catch (IOException e) {
			System.out.println("ERROR! Error occurd when file was read.");
		}
	}

	/**
	 * Method for returning all the questions read from file
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<String>> getArray() {
		readFile();
		return allQuestions;
	}

	/**
	 * Method for adding questions to fragor.txt
	 * 
	 */
	public void newQuestion(ArrayList<String> question) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					STANDARDFRAGOR, true));

			Iterator<String> it = question.iterator(); // Sets an iterator to
														// the arrayList in
														// order iterate through
														// it.
			while (it.hasNext()) {
				bw.write(it.next() + "\n");
			}
			bw.write("\n");
			bw.close();
/*
			for(String line:question){
				bw.write(line + "\n");
			}
			bw.write("\n");
*/			
		} catch (IOException e) {
			System.out.println("ERROR! Error occurd when write");
		}
		
	}
}