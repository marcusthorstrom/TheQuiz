package Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import Server.Questions;
import Server.SingleQuestion;


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
	private Sounds sounds;

	
	public GModel() {
		q = new Questions();
		sounds = new Sounds();
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
		
		
		try {
			ConnectionToServer c = new ConnectionToServer();
			
			
			
		} catch (IOException e) {
			qu = q.getQuestions(options.getGameRounds());
		}
		
		
		

		sounds.onOff(options.getVolume());

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
			sounds.playCorrect();
			rightCount++;
			isCorrect = true;
			// return true;
		} 
		else {
			wrongCount++;
			sounds.playIncorrect();
			isCorrect = false;
			// return false;
		}
		qNumber++;
		setChanged();
		notifyObservers(chosenAnswer);
		setChanged();
		notifyObservers(isCorrect);
	}
	
	public void createQuestion(SingleQuestion qu) {
		if(c.conectionIsActive()) {
			c.writeQuestion(qu);
		}
		else {
			q.writeQuestion(qu);
		}
		
	}
}
