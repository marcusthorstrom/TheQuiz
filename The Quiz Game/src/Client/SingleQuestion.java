package Client;

import java.util.ArrayList;
/**
 * This class is for storing a question object
 *
 */
public class SingleQuestion{


	private String question;
	private String correctAnswer;
	private String answer2;
	private String answer3;
	private String answer4;


	/**
	 * SingleQuestion takes an array and puts each element in each respective field.
	 * @param questionArray containing one question with 4 answers.
	 */
	public SingleQuestion(ArrayList<String> questionArray){
		this.question = questionArray.get(0);
		this.correctAnswer = questionArray.get(1);
		this.answer2 = questionArray.get(2);
		this.answer3 = questionArray.get(3);
		this.answer4 = questionArray.get(4);
	}


	public String getQuestion()
	{
		return question;
	}

	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	public String getAnswer2()
	{
		return answer2;
	}

	public String getAnswer3()
	{
		return answer3;
	}

	public String getAnswer4()
	{
		return answer4;
	}

	public ArrayList<String> printArray() {
		ArrayList<String> returnList = new ArrayList<String>();
		returnList.add(question);
		returnList.add(correctAnswer);
		returnList.add(answer2);
		returnList.add(answer3);
		returnList.add(answer4);
		return returnList;

	}

	/**
	 * A method for checking if the question is empty or not.
	 * @return true is all fields are empty,  false.
	 */
	public boolean isEmpty() {
		if(question.equals(null)||correctAnswer.equals(null)||answer2.equals(null)||answer3.equals(null)||answer4.equals(null)) {return true;}
		else {return false;}

	}
}
