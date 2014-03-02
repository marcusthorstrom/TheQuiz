package Client;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	private ArrayList<SingleQuestion> objectList;
	private IOClient io;
	
	
	/**
	 * Constructor creates a new IOClient and asks for an array to be converted into SingleQuestions'.
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public QuestionsClient() throws UnsupportedEncodingException, FileNotFoundException
	{
	    io = new IOClient();												//Creates a new instance of IO.
	    objectList = new ArrayList<SingleQuestion>();				//List for storing questionClasses

	}
	
	public void writeQuestion(SingleQuestion inQuestion) {
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
	 * @throws Throwable 
	 */
	public ArrayList<SingleQuestion> getQuestions(int y) throws Throwable {
		originalList = io.getArray();								//Copies the original list from the IO class to a local list
		originalList.trimToSize();
		shuffle(originalList);
		for(int i = 0; i < y; i++){								//Loops through the list to create a new list of Objects instead of ArrayList<String> which is returned from the IO class
			objectList.add(new SingleQuestion(originalList.get(i)));	
		}
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
