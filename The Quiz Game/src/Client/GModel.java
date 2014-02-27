package Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;


public class GModel extends Observable {

	private Options options;
	private String rightAnswer;
	private String chosenAnswer;
	private SingleQuestionClient activeQuestion;
	private int rightCount = 0;
	private int wrongCount = 0;
	private int qNumber = 0;
	private ArrayList<SingleQuestionClient> qu;
	private boolean isCorrect = false;
	private QuestionsClient q;
	private Sounds sounds;

	public GModel() {
		q = new QuestionsClient();
		sounds = new Sounds();
	}

	public void changeActiveQuestion() {
		if (qNumber >= options.getGameRounds()) {
			int[] a = { rightCount, wrongCount };
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
			qu = c.getQuestions(options.getGameRounds());
		} catch (IOException e) {
			setChanged();
			notifyObservers(1);
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
		} else {
			wrongCount++;
			sounds.playIncorrect();
			isCorrect = false;
		}
		qNumber++;
		setChanged();
		notifyObservers(chosenAnswer);
		setChanged();
		notifyObservers(isCorrect);
	}

	public void createQuestion(SingleQuestionClient qu) {
		try {
			ConnectionToServer c = new ConnectionToServer();
			c.writeQuestion(qu);	

		} catch (IOException e) {
			setChanged();
			notifyObservers(2);						//Error code for not being able to write to the server
			q.writeQuestion(qu);
		}
	} 
}
