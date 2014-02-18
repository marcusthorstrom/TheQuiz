import java.util.ArrayList;

import javax.swing.JButton;


public class GameEngine {
	public int rightCount = 0;
	public int wrongCount = 0;
	private String rightAnswer;
	private ArrayList<ArrayList<String>> q;
	private GUI gui;
	private boolean isReady = true;
	
	/**
	 * 
	 */
	public GameEngine() {
		new OSDetector();
		gui = new GUI(this);
		Questions questions = new Questions();
		q = questions.getQuestions(5);
		playGame();
		
	}
	public void playGame() {
		for(int i = 0; i < q.size(); i++) {
			if(isReady) {
				gui.askQuestion(q.get(i));
			}
			else{
				i = i-1;
			}
		}

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
	
	public void setIsReady(boolean ready) {
		isReady = ready;
	}

}									


