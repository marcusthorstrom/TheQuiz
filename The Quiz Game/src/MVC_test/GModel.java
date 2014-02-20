package MVC_test;

import java.util.ArrayList;
import java.util.Observable;

public class GModel extends Observable {
	
	private String rightAnswer;
	private boolean isReady;
	private ArrayList<String> activeQuestion;
	
	
	public GModel(GView view) {
		
		addObserver(view);
		
		
		
		Questions q = new Questions();
		ArrayList<ArrayList<String>> qu = q.getQuestions(1);
		
		
		for(int i = 0; i < qu.size(); i++) {
			setRight(qu.get(i).get(1));
			activeQuestion = qu.get(i);
			notifyObservers(activeQuestion);
			
		
		}
		
	}
	public void setRight(String rightAnswer) {
		this.rightAnswer = rightAnswer; 
	}
	public String getRight() {
		return rightAnswer;
	}
	
	public boolean isRightAnswer(String answer) {
		if(rightAnswer.equals(answer)) {
			return true;
		}
		else {
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
