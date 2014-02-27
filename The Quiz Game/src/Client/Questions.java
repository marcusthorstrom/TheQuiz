package Client;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;


public class Questions{
	
	private ArrayList<ArrayList<String>> originalList;
	private ArrayList<SingleQuestion> newList;
	private ArrayList<SingleQuestion> objectList;
	private IO io;
	
	public Questions()
	{
	    io = new IO();												//Creates a new instance of IO.
	    objectList = new ArrayList<SingleQuestion>();				//List for storing questionClasses
		originalList = io.getArray();								//Copies the original list from the IO class to a local list
		for(int i = 0; i < originalList.size(); i++)				//Loopes through the list to create a new list of Objects instead of ArrayList<String> which is returned from the IO class
		{
			objectList.add(new SingleQuestion(originalList.get(i)));	
		}
	}
	
	public void writeQuestion(SingleQuestion inQuestion) {
		try {														//If the array is = null then this catches it
		if(!inQuestion.isEmpty()) {									//Testing if the array is partially empty
			ArrayList<String> tempQuestions = new ArrayList<String>();
			tempQuestions = inQuestion.printArrayList(tempQuestions);
			io.newQuestion(tempQuestions);
		}
		else {
			//System.out.println("Did not write");
		}
		}
		catch(NullPointerException e) {
			//System.out.println("Did not write, catched error");
		}
	}
	
	public ArrayList<SingleQuestion> getQuestions(int y){
		
		newList = new ArrayList<SingleQuestion>();
		shuffle(objectList);	

		for(int x = 0; x < y; x++){
			newList.add(objectList.get(x));
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