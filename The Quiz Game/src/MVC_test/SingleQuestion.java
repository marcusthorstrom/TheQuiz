package MVC_test;

import java.util.ArrayList;

public class SingleQuestion {

	private String question;
	private String correctAnswer;
	private String answer2;
	private String answer3;
	private String answer4;
	private String sound;
	public boolean containsSound = false;
	
	public SingleQuestion(ArrayList<String> questionArray){

		this.question = questionArray.get(0);
		this.correctAnswer = questionArray.get(1);
		this.answer2 = questionArray.get(2);
		this.answer3 = questionArray.get(3);
		this.answer4 = questionArray.get(4);
		
		if(questionArray.size() > 5){
			
			containsSound = true;
			this.sound = questionArray.get(5);
		}
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
	

	public String getSound()
	{
		return sound;
	}
	
	public boolean hasSound()
	{
		return containsSound;
	}
	//For testing
	public ArrayList<String> printArrayList(ArrayList<String> inList) {
		inList.add(0,question);
		inList.add(1, correctAnswer);
		inList.add(2, answer2);
		inList.add(3, answer3);
		inList.add(4, answer4);
		return inList;
	}

}
