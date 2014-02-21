package MVC_test;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.text.View;

public class GModel extends Observable {

	private String rightAnswer;
	private String chosenAnswer;
	private boolean isReady;
	private ArrayList<String> activeQuestion;
	private int rightCount = 0;
	private int wrongCount = 0;
	private int qNumber = 0;
	private static final int GAME_ROUNDS = 5;
	ArrayList<ArrayList<String>> qu;
	private boolean isCorrect = false;

	public GModel() {
		Questions q = new Questions();
		qu = q.getQuestions(GAME_ROUNDS);

	}

	public void changeActiveQuestion() {
		if (qNumber >= GAME_ROUNDS) {
			int[] a = {rightCount, wrongCount};
			setChanged();
			notifyObservers(a);
			
		}

		else {
			activeQuestion = qu.get(qNumber);
			setRight(activeQuestion.get(1));
			setChanged();
			notifyObservers(activeQuestion);
		}
	}

	public void playGame() {

		changeActiveQuestion();
		qNumber++;

	}

	public void setChosenAnswer(String chosenAnswer) {
		this.chosenAnswer = chosenAnswer;
	}

	public void setRight(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getRight() {
		return rightAnswer;
	}

	public void isRightAnswer() {
		if (rightAnswer.equals(chosenAnswer)) {
			rightCount++;
			isCorrect = true;
			// return true;
		} else {
			wrongCount++;
			isCorrect = false;
			// return false;
		}
		setChanged();
		notifyObservers(chosenAnswer);
		setChanged();
		notifyObservers(isCorrect);
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
