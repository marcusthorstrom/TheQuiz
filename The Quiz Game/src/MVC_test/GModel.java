package MVC_test;

import java.util.ArrayList;
import java.util.Observable;

public class GModel extends Observable {
	
	private String rightAnswer;
	private boolean isReady;
	private ArrayList<String> activeQuestion;
	private int rightCount = 0;
	private int wrongCount = 0;
	private int qNumber = 0;
	private int gameRounds = 5;
	
	
	ArrayList<ArrayList<String>> qu;
	
	public GModel(GView view) {
		
		addObserver(view);
		Questions q = new Questions();
		qu = q.getQuestions(gameRounds);
		
		playGame();
		
	}
	
	public void changeActiveQuestion(){
		if(qNumber >= gameRounds){
			System.out.println("Slutspelat: \n rätt: " + rightCount + "\n fel: " + wrongCount);
		}
		
		else{
		activeQuestion = qu.get(qNumber);
		setRight(activeQuestion.get(1));
		setChanged();
		notifyObservers(activeQuestion);
		}
	}
	
	public void playGame(){
		
		changeActiveQuestion();
	}
	
	public void setRight(String rightAnswer) {
		this.rightAnswer = rightAnswer; 
	}
	
	public String getRight() {
		return rightAnswer;
	}
	
	public boolean isRightAnswer(String answer) {
		if(rightAnswer.equals(answer)) {
			rightCount++;
			return true;
		}
		else {
			wrongCount++;
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
	}
}
