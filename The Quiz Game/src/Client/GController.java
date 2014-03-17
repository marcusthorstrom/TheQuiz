package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;
/**
 * The controller, managing both the View and Model
 * @param view
 * @param model
 */
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
	/**
	 * A Class for listen on the helpWindow in view
	 */
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
	/**
	 * A Class for listen on the Main Menu
	 */
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
				else if(button.getText().equals("Egen Fraga")) {
					view.makeQuestion();
				}
				else if(button.getText().equals("Installningar")) {
					view.options();
				}
				else if(button.getText().equals("Hjalp")) {
					view.makeHelp();
				}
				else if(button.getText().equals("Avsluta")) {
					view.closeWindow();
				}
			}
		}
	}
	/**
	 * A Class for listen on the answer pressed when the game is played
	 * this class passes the pressed answer to the GModel class for
	 * comparing if it was the right answer
	 */ 
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
	/**
	 * A Class used for listen on the submission of
	 * creating your own question
	 */
	class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton b = (JButton)objekt;
				if(b.getText().equals("Ok")){
					SingleQuestion q = view.submitFields();
					model.createQuestion(q);
					view.closeQWindow();
				}
				else if(b.getText().equals("Avbryt")){
					view.closeQWindow();
				}
			}
		}
	}
	/**
	 * A class for listen to the settings enterd by the user
	 */
	class SettingsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton b = (JButton)objekt;
				if(b.getText().equals("Anvand")){
					options = view.submitOptions(options);
					view.closeSWindow();	
				}
				else if(b.getText().equals("Aterstall")) {
					view.resetOptions(options);
					options = view.submitOptions(options);
					options.resetGamerounds();
					options.resetVolume();
				}
			}
		}
	}
	/**
	 * A class for listen on the "Back to menu" or "exit" frame
	 * that appears in the end of a game
	 *
	 */
	class ResultListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof JButton) {
				JButton b = (JButton)objekt;
				if(b.getText().equals("Aterga till meny")){			
					view.resetFrame();
					view.makeMenu();								//aterskapar menyn
				}
				else if(b.getText().equals("Avsluta")) {
					view.closeWindow();
				}
			}
		}
	}
	/**
	 * The timer used for displaying the next question in the gui
	 */
	class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objekt = e.getSource();
			if (objekt instanceof Timer) {
				Timer timer = (Timer) objekt;
				timer.stop();
				model.changeActiveQuestion();							//Hamtar nasta fraga.
			}
		}
	}
}



