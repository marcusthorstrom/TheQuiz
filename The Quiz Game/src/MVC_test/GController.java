package MVC_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;

public class GController {

	private GView view;
	private GModel model;

	public GController(GView view, GModel model) {
		this.view = view;
		this.model = model;

		this.view.addAnswerListner(new AnswerListener());
		this.view.addTimerListner(new TimerListener());
		this.view.addSubmitListner(new SubmitListener());

	}

	class AnswerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton button = (JButton) objekt;
				view.stopSound();
				model.setChosenAnswer(button.getText());
				model.isRightAnswer();

				/*
				 * if(model.isRightAnswer(button.getText())) {
				 * view.setRightAnswer(model.getRight()); } else {
				 * view.setWrongAnswer(button.getText());
				 * view.setRightAnswer(model.getRight()); }
				 */
			}
			view.disableButtons();
		}
	}
	class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton b = (JButton)objekt;
				if(b.getText().equals("Ok")){
					SingleQuestion q = view.submitFields();
					q.printQ();
				}
				else if(b.getText().equals("Avbryt")){
					
				}
			}
		
		}
	}
	class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof Timer) {
				Timer timer = (Timer) objekt;
				timer.stop();
				model.playGame();			//Hämtar nästa fråga.
			}
		}

	}

}
