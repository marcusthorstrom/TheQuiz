package MVC_test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;


public class GController {
	
	private GView view;
	private GModel model;
	public int rightCount = 0;
	public int wrongCount = 0;
	
	
	public GController(GView view, GModel model) {
		this.view = view;
		this.model = model;
		
		this.view.addAnswerListner(new AnswerListener());
		this.view.addTimerListner(new TimerListener());
		
	}

	
	class AnswerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if(objekt instanceof JButton) {
				JButton button = (JButton)objekt;
				if(model.isRightAnswer(button.getText())) {
					view.setRightAnswer(model.getRight());
				}
				else {
					view.setWrongAnswer(button.getText());
					view.setRightAnswer(model.getRight());
				}
			}
			view.disableButtons();
		}
	}
	class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof Timer) {
				Timer timer = (Timer)objekt;
				view.resetFrame();
				timer.stop();
			}
		}
		
	}

}
