package Client;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

/**
 * This class is for getting the arraylist from the IO class and
 * putting it into the SingleQuestion Class and exporting the 
 * number of questions the user asks for.
 * The class also is responsible for converting the incoming 
 * Question to an ArrayList.
 */

public class QuestionsClient{

	private ArrayList<ArrayList<String>> originalList;
	private ArrayList<SingleQuestionClient> newList;
	private ArrayList<SingleQuestionClient> objectList;
	private IOClient io;
	
	
	/**
	 * Constructor creates a new IOClient and asks for an array to be converted into SingleQuestions'.
	 * @throws IOException 
	 */
	public QuestionsClient()
	{
	    io = new IOClient();												//Creates a new instance of IO.
	    objectList = new ArrayList<SingleQuestionClient>();				//List for storing questionClasses

	}
	
	public void writeQuestion(SingleQuestionClient inQuestion) {
		try {														//If the array is = null then this catches it
		if(!inQuestion.isEmpty()) {									//Testing if the array is partially empty
			ArrayList<String> tempQuestions = new ArrayList<String>();
			io.newQuestion(tempQuestions);
		}
		else {
			//System.out.println("Did not write");
		}
		}
		catch(NullPointerException e) {
			//System.out.println("Did not write, caught error");
		}
	}
	
	/**
	 * Creates a new temporary array list in which a number of singleQuestions are stored. 
	 * The full list of SingleQuestions are shuffled before they are added to the new list.
	 * @param y - the number of questions asked for.
	 * @return An array containing y-number of SingleQuestions.
	 * @throws IOException 
	 */
	public ArrayList<SingleQuestionClient> getQuestions(int y) throws IOException, java.io.FileNotFoundException {
		originalList = io.getArray();								//Copies the original list from the IO class to a local list
		shuffle(originalList);
		for(int i = 0; i < y; i++){								//Loops through the list to create a new list of Objects instead of ArrayList<String> which is returned from the IO class
			objectList.add(new SingleQuestionClient(originalList.get(i)));	
		}
		newList = new ArrayList<SingleQuestionClient>();
		return objectList;
	}
	
	/**
	 * Shuffles Array List as parameter with system time as seed.
	 */
	private void shuffle(ArrayList<?> list)
	{
		long seed = System.nanoTime();				//New seed for randomizer.
		Collections.shuffle(list, new Random(seed));	
	}
}
