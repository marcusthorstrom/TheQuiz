package MVC_test;

public class GModel {
	
	private String rightAnswer;
	private boolean isReady;
	public GModel() {
		
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
