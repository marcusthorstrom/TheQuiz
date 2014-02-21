package MVC_test;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;


public class Questions{
	
	private ArrayList<ArrayList<String>> originalList;
	private ArrayList<SingleQuestion> newList;
	private ArrayList<SingleQuestion> objectList;
	IO io;
	
	
	
	public Questions()
	{
	    io = new IO();	//Creates a new instance of IO.
	    objectList = new ArrayList<SingleQuestion>();
	    createQuestions();
	}
	
	
	/**
	 * Get an array from IO, calls shuffle-method. Takes x lists and puts them in a new list to be returned. 
	 */
	/*public ArrayList<ArrayList<String>> getQuestions(int x)
	{
		newList = new ArrayList<ArrayList<String>>();
		originalList = io.getArray();
		shuffle(originalList);			//Shuffles the original List. 
		
		for(int i = 0; i < x; i++)
		{
			newList.add(originalList.get(i));
		}
		return newList;
		
	}*/
	
	public void createQuestions(){
		
		originalList = io.getArray();
		
		for(int i = 0; i < originalList.size(); i++)
		{
			objectList.add(new SingleQuestion(originalList.get(i)));
		}
		
	}
	
	
	
	public ArrayList<SingleQuestion> getQuestions(int x){
		
		newList = new ArrayList<SingleQuestion>();
		shuffle(objectList);	

		for(int i = 0; i < x; i++){
			newList.add(objectList.get(i));
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
