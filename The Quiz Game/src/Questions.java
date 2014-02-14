import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;


public class Questions{
	
	private ArrayList<ArrayList<String>> originalList;
	private ArrayList<ArrayList<String>> newList;
	IO io;
	
	
	
	public Questions()
	{
	    io = new IO();	//Creates a new instance of IO.
	}
	
	
	/**
	 * Get an array from IO, calls shuffle-method. Takes x lists and puts them in a new list to be returned. 
	 */
	public ArrayList<ArrayList<String>> getQuestions(int x)
	{
		newList = new ArrayList<ArrayList<String>>();
		originalList = io.readFile();
		shuffle(originalList);			//Shuffles the original List. 
		
		for(int i = 0; i < x; i++)
		{
			newList.add(originalList.get(i));
		}
		return newList;
		
	}
	

	/**
	 * Shuffles arraylist as parameter with system time as seed.
	 */
	private void shuffle(ArrayList<ArrayList<String>> list)
	{
		long seed = System.nanoTime();				//New seed for randomizer.
		Collections.shuffle(list, new Random(seed));	
	}
	
	

}
