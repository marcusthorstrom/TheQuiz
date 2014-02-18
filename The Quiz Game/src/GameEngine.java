import javax.swing.JButton;


public class GameEngine {
	public int rightCount = 0;
	public int wrongCount = 0;
	private String rightAnswer;
	
	/**
	 * 
	 */
	public GameEngine() {
		new OSDetector();
		GUI gui = new GUI(this);
		Questions questions = new Questions();
		gui.askQuestion(questions.getQuestions(1).get(0));
		
	}
	
	/**
	 * 
	 */
	public static void main(String [ ] args)
	{
	      new GameEngine();
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
	
	public void setRight(String rightAnswer) {
		this.rightAnswer = rightAnswer; 
	}
	public String getRight() {
		return rightAnswer;
	}
	
	public boolean isRightAnswer(String answer) {
		if(rightAnswer.equals(answer)) {
			rightCount++;									//adding one the number of rightAnswer
			return true;
		}
		else {
			wrongCount++;									//adding one the number of wrongAnswer
			return false;
		}
	}									
}
