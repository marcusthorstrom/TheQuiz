package MVC_test;

import java.util.ArrayList;
import java.util.Observable;

public class GModel extends Observable {
	
	private String rightAnswer;
	private String chosenAnswer;
	private boolean isReady;
	private ArrayList<String> activeQuestion;
	private int rightCount = 0;
	private int wrongCount = 0;
	private int qNumber = 0;
	private static final int GAME_ROUNDS = 5;
	private boolean correctAnswer = false;
	
	
	
	ArrayList<ArrayList<String>> qu;
	
	public GModel(GView view) {
		
		addObserver(view);
		Questions q = new Questions();
		qu = q.getQuestions(GAME_ROUNDS);
		
	}
	
	public void changeActiveQuestion(){
		if(qNumber >= GAME_ROUNDS){
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
	
	
	public void setChosenAnswer(String chosenAnswer)
	{
		this.chosenAnswer = chosenAnswer;
	}
	
	
	public void setRight(String rightAnswer) {
		this.rightAnswer = rightAnswer; 
	}
	
	
	
	
	public String getRight() {
		return rightAnswer;
	}
	
	
	public void isRightAnswer() {
		if(rightAnswer.equals(chosenAnswer)) {
			//correctAnswer = true;
			rightCount++;
			//return true;
		}
		else {
			//correctAnswer = false;
			wrongCount++;
			//return false;
		}
		setChanged();
		notifyObservers(chosenAnswer);
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
