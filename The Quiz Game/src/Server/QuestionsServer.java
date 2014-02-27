package Server;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;


public class QuestionsServer{
	
	private ArrayList<ArrayList<String>> originalList;
	private ArrayList<ArrayList<String>> newList;
	private IO io;
	
	public QuestionsServer()
	{
	    io = new IO();												//Creates a new instance of IO.
		originalList = io.getArray();								//Copies the original list from the IO class to a local list

	}
	
	public void writeQuestion(ArrayList<String> inQuestion) {
		try {														//If the array is = null then this catches it
		if(!inQuestion.isEmpty()) {									//Testing if the array is partially empty
			io.newQuestion(inQuestion);
		}
		else {
			//System.out.println("Did not write");
		}
		}
		catch(NullPointerException e) {
			//System.out.println("Did not write, catched error");
		}
	}
	
	public ArrayList<ArrayList<String>> getQuestions(int y){
		
		newList = new ArrayList<ArrayList<String>>();
		shuffle(originalList);	

		for(int x = 0; x < y; x++){
			newList.add(originalList.get(x));
		}	
		return newList;
	}
	/**
	 * Shuffles Arraylist as parameter with system time as seed.
	 */
	private void shuffle(ArrayList<?> list)
	{
		long seed = System.nanoTime();				//New seed for randomizer.
		Collections.shuffle(list, new Random(seed));	
	}
}
