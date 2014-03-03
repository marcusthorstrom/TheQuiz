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

/**
 * Responsible for reading and writing questions to .txt file
 * @author Emil
 *
 */
public class IOServer {

	private static final String STANDARDFRAGOR = "fragor.txt";
	private ArrayList<String> questions;
	private ArrayList<ArrayList<String>> allQuestions;
	private BufferedReader br;

	public IOServer() {
	}

	/**
	 * Method for reading questions from .txt file.
	 */
	private void readFile() {
		questions = new ArrayList<String>();
		allQuestions = new ArrayList<ArrayList<String>>();
		try {
			/**
			 * Checks what operating system the computer is running, then open
			 * the file in different ways to get Swedish characters
			 */
			if (OSDetectorServer.isMac()) {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(STANDARDFRAGOR), "ISO-8859-1"));
			} else if (OSDetectorServer.isWindows()) {
				br = new BufferedReader(new FileReader(STANDARDFRAGOR));

				//
			} else if (OSDetectorServer.isLinux()) {
				String file = (System.getProperty("user.home") + "/Desktop/" + STANDARDFRAGOR);
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file), "ISO-8859-1"));

			}
			/**
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
			System.exit(1);
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
				bw.write(it.next());
				bw.newLine();
			}
			bw.newLine();
			bw.close();
			
		} catch (IOException e) {
			System.out.println("ERROR! Error occurd when write");
		}
		
	}
}