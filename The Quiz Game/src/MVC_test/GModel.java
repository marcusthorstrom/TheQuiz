package MVC_test;

import java.util.ArrayList;
import java.util.Observable;


public class GModel extends Observable {

	private Options options;
	private String rightAnswer;
	private String chosenAnswer;
	private SingleQuestion activeQuestion;
	private int rightCount = 0;
	private int wrongCount = 0;
	private int qNumber = 0;
	private ArrayList<SingleQuestion> qu;
	private boolean isCorrect = false;
	private Questions q;
	private Sounds sound;

	
	public GModel() {
		q = new Questions();
		//sound = new Sounds();
	}

	public void changeActiveQuestion() {
		if (qNumber >= options.getGameRounds()) {
			int[] a = {rightCount, wrongCount};
			setChanged();
			notifyObservers(a);
			qNumber = 0;
			wrongCount = 0;
			rightCount = 0;
		}

		else {
			activeQuestion = qu.get(qNumber);
			setRight(activeQuestion.getCorrectAnswer());
			setChanged();
			notifyObservers(activeQuestion);
		}
	}

	public void playGame(Options options) {
		this.options = options;
		qu = q.getQuestions(options.getGameRounds());
		sound.setVolume(options.getVolume());
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
			sound.playCorrect();
			// return true;
		} else {
			wrongCount++;
			isCorrect = false;
			sound.playIncorrect();
			// return false;
		}
		qNumber++;
		setChanged();
		notifyObservers(chosenAnswer);
		setChanged();
		notifyObservers(isCorrect);
	}
	
	public void createQuestion(SingleQuestion qu) {
		q.writeQuestion(qu);
	}
}
