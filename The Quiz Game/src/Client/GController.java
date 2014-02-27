package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class GController {

	private GView view;
	private GModel model;
	private Options options = new Options();

	public GController(GView view, GModel model) {
		this.view = view;
		this.model = model;

		this.view.addTimerListener(new TimerListener());
		this.view.addMenuListener(new MenuListener());
		this.view.addSubmitListener(new SubmitListener());
		this.view.addSettingsListener(new SettingsListener());
		this.view.addHelpListener(new HelpListener());
		this.view.addResultListener(new ResultListener());
	}

	public void startGame(){
		this.view.addAnswerListener(new AnswerListener());
	}

	class HelpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton button = (JButton)objekt;
				if(button.getText().equals("Ok")) {
					view.closeHWindow();					
				}
			}
		}
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
					model.playGame(options);
					model.changeActiveQuestion();
				}
				else if(button.getText().equals("Egen Fråga")) {
					view.makeQuestion();
				}
				else if(button.getText().equals("Inställningar")) {
					view.options();
				}
				else if(button.getText().equals("Hjälp")) {
					view.makeHelp();
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
				model.setChosenAnswer(button.getText());
				model.isRightAnswer();
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
					SingleQuestionClient q = view.submitFields();
					model.createQuestion(q);
					view.closeQWindow();
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
				if(b.getText().equals("Använd")){
					options = view.submitOptions(options);
					view.closeSWindow();	
				}
				else if(b.getText().equals("Återställ")) {
					view.resetOptions(options);
					options = view.submitOptions(options);
					options.resetGamerounds();
					options.resetVolume();
				}
			}
		}
	}
	class ResultListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton b = (JButton)objekt;
				if(b.getText().equals("Återgå till meny")){			
					view.resetFrame();
					view.makeMenu();								//Återskapar menyn
				}
				else if(b.getText().equals("Avsluta")) {
					view.closeWindow();
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
				model.changeActiveQuestion();							//Hämtar nästa fråga.
			}
		}
	}
}



