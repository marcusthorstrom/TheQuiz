import java.util.ArrayList;
import java.util.Collections;


public class Questions {
	private ArrayList<ArrayList<String>> a;
	private ArrayList<ArrayList<String>> b;
	private IO io;

	public Questions() {
		io = new IO();
	}
	
	/**
	 * 
	 */
	public ArrayList<ArrayList<String>> getQuestion(int x){
		a = io.readFile();
		b = new ArrayList<ArrayList<String>>();
		Collections.shuffle(a);
		for(int i = 0; i <= x; i++) {
			b.add((a.get(i)));
		}
		return b;	

	}
	
	/**
	 * 
	 */
	public void setQuestion(int x){
		// här ska vi skriva kod för att lägga till en fråga	
	}

}



