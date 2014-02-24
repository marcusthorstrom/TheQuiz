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

		this.view.addTimerListener(new TimerListener());
		this.view.addMenuListener(new MenuListener());
		this.view.addSubmitListener(new SubmitListener());
		this.view.addSettingsListener(new SettingsListener());
	}

	public void startGame(){
		this.view.addAnswerListener(new AnswerListener());
	}

	class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton button = (JButton)objekt;
				if(button.getText().equals("Start")) {
					view.resetFrame();
					view.makeQFrame();
					startGame();
					model.playGame();
				}
				else if(button.getText().equals("Egen Fråga")) {
					view.makeQuestion();
				}
				else if(button.getText().equals("Inställningar")) {
					view.options();
				}
				else if(button.getText().equals("Hjälp")) {
					//view.makeHelp();
				}
				else if(button.getText().equals("Avsluta")) {
					view.closeWindow();
				}
			}
		}

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
					view.closeQWindow();
				}
			}

		}
	}

	class SettingsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton b = (JButton)objekt;
				if(b.getText().equals("Spara")){
					view.submitSettings();
				}
				else if(b.getText().equals("Avbryt")){
					view.closeSWindow();
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
				model.playGame();			//H�mtar n�sta fr�ga.
			}
		}
	}
}


