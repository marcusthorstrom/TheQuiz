import java.util.ArrayList;
import java.util.Collections;


public class Questions {

	private IO io;

	public Questions() {
		IO io = new IO();
		getQuestion(1);
	}
	/**
	 * 
	 */
	public ArrayList<ArrayList<String>> getQuestion(int x){
		ArrayList<ArrayList<String>> a = io.readFile();
		ArrayList<ArrayList<String>> b = new ArrayList<ArrayList<String>>();
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



