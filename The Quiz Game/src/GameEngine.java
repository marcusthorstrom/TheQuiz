import javax.swing.JButton;


public class GameEngine {
	
	/**
	 * 
	 */
	public GameEngine() {
	
		GUI gui = new GUI();
		Questions questions = new Questions();
		gui.askQuestion(questions.getQuestion(1).get(0));
		
	}
	
	/**
	 * 
	 */
	public static void main(String [ ] args)
	{
	      new Questions();
	}
	
	/**
	 * 
	 */
	public JButton GetSource(){
		return null;
	}
	
	/**
	 * 
	 */
	public Boolean CheckAnswer(){
		Boolean statement = true;
		
		return statement;
	}
	/**
	 * 
	 */
	//rightCounter();												//Counts the number of rightAnswer
	
	/**
	 * 
	 */
	//wrongCounter();												//Counts the number of wrongAnswer
}