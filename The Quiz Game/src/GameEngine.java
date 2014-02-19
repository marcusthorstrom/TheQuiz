import java.util.ArrayList;

import javax.swing.JButton;


public class GameEngine {
	public int rightCount = 0;
	public int wrongCount = 0;
	private String rightAnswer;
	private ArrayList<ArrayList<String>> q;
	private GUI gui;
	private boolean isReady = true;
	private boolean isFinished;
	
	/**
	 * 
	 */
	public GameEngine() {
		new OSDetector();
		gui = new GUI(this);
		Questions questions = new Questions();
		q = questions.getQuestions(3);
		playGame();
		
	}
	public void playGame() {
		for(int i = 0; i < q.size(); i++) {
			if(isReady) {
				gui.askQuestion(q.get(i));
				isFinished = false;
				
			}
			else{
				i = i-1;
			}
		}
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				//
				e.printStackTrace();
			}
			if(isFinished) {
				gui.showResult(rightCount, wrongCount);
				break;
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
			isFinished = true;
			rightCount+= 1;									//adding one the number of rightAnswer
			return true;
		}
		else {
			wrongCount+= 1;									//adding one the number of wrongAnswer'
			isFinished = true;
			return false;
		}
	}
	
	public void setIsReady(boolean ready) {
		isReady = ready;
	}
	public boolean getIsReady() {
		return isReady;
	}
	public void setIsFinished(boolean ready) {
		isFinished = ready;
	}
}									


