package Server;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;


public class QuestionsServer{
	/**
	 * This class is for getting the arraylist from the IO class and
	 * putting it into the SingleQuestion Class and exporting the 
	 * number of questions the user asks for.
	 * The class also is responsible for converting the incoming 
	 * Question to an ArrayList.
	 */
	private ArrayList<ArrayList<String>> originalList;
	private ArrayList<ArrayList<String>> newList;
	private IOServer io;

	public QuestionsServer()
	{
		io = new IOServer();												//Creates a new instance of IO.
		originalList = io.getArray();								//Copies the original list from the IO class to a local list

	}

	public void writeQuestion(ArrayList<String> inQuestion) {
		try {														//If the array is = null then this catches it
			if(!inQuestion.isEmpty()) {									//Testing if the array is partially empty
				io.newQuestion(inQuestion);
			}
			else {
				System.out.println("Did not write");
			}
		}
		catch(NullPointerException e) {
			//System.out.println("Did not write, catched error");
		}

	}
	/*
	 * Creates a new temporary array list in which a number of singleQuestions are stored. 
	 * The full list of SingleQuestions are shuffled before they are added to the new list.
	 */
	public ArrayList<ArrayList<String>> getQuestions(int y){

		newList = new ArrayList<ArrayList<String>>();
		shuffle(originalList);	

		for(int x = 0; x < y; x++){
			newList.add(originalList.get(x));
		}	
		return newList;
	}
	/*
	 * Shuffles Arraylist as parameter with system time as seed.
	 */
	private void shuffle(ArrayList<?> list)
	{
		long seed = System.nanoTime();				//New seed for randomizer.
		Collections.shuffle(list, new Random(seed));	
	}
}
