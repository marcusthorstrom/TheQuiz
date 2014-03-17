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

	private IOClient io;
	private ArrayList <ArrayList<String>> originalList;
	private ArrayList<SingleQuestion> objectList;
	
	/*
	 * Constructor creates a new IOClient and asks 
	 * for an array to be converted into SingleQuestions'. 
	 */
	public QuestionsClient() throws Throwable
	{
		io = new IOClient();	
		
		originalList = new  ArrayList <ArrayList<String>>();
	    originalList = io.getArray();
	    shuffle(originalList);
	}
	
	public void writeQuestion(SingleQuestion inQuestion) throws UnsupportedEncodingException, FileNotFoundException {
		try {														//If the array is = null then this catches it
			if(!inQuestion.isEmpty()) {									//Testing if the array is partially empty
				ArrayList<String> tempQuestions = new ArrayList<String>();
				tempQuestions = inQuestion.printArray();
				io.newQuestion(tempQuestions);
			}
		}
		catch(NullPointerException e) {}
	}
	
	/*
	 * Creates a new temporary array list in which a number of singleQuestions are stored. 
	 * The full list of SingleQuestions are shuffled before they are added to the new list.
	 */
	public ArrayList<SingleQuestion> getQuestions(int y) throws Throwable {
		shuffle(originalList);
		 objectList = new ArrayList<SingleQuestion>();			//List for storing questionClasses
		for(int i = 0; i < y; i++){								//Loops through the list to create a new list of Objects instead of ArrayList<String> which is returned from the IO class
			objectList.add(new SingleQuestion(originalList.get(i)));	
		}
		return objectList;
	}
	
	/*
	 * Shuffles Array List as parameter with system time as seed, this for a more randomized shuffle.
	 */
	private void shuffle(ArrayList<?> list)
	{
		long seed = System.nanoTime();				//New seed for randomizer.
		Collections.shuffle(list, new Random(seed));	
	}
}
