package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
/**
 * the Whole Graphical User Interface
 * in one class, this class is responsible for
 * all viewing in the program
 * @author Marcus
 *
 */

public class GView implements Observer {
	private JFrame gameWindow;
	private JDialog questionWindow = new JDialog(gameWindow,"Skapa Fråga");
	private JDialog settingsWindow = new JDialog(gameWindow,"Inställningar");
	private JDialog helpWindow = new JDialog(gameWindow,"Hjälp");

	private ArrayList<String> answers;
	private ArrayList<JButton> buttons;
	private ArrayList<JButton> menuButtons = new ArrayList<JButton>();

	private Container contentPane;
	private Container borderPane;

	private JLabel question;
	private JPanel gridPane;

	private JButton buttonA;
	private JButton buttonB;
	private JButton buttonC;
	private JButton buttonD;
	private JButton create = new JButton("Egen Fråga");
	private JButton options = new JButton("Inställningar");
	private JButton help = new JButton("Hjälp");
	private JButton quit = new JButton("Avsluta");
	private JButton start = new JButton("Start");
	private JButton okButtonQuestion = new JButton("Ok");
	private JButton cancelButtonQuestion = new JButton("Avbryt");
	private JButton okButtonSetting = new JButton("Använd");
	private JButton resetButtonSetting = new JButton("Återställ");
	private JButton backToMenu = new JButton("Återgå till meny");
	private JButton resultquit = new JButton("Avsluta");
	private JButton okButtonHelp = new JButton("Ok");

	private String rightAnswer;
	private Dimension dialog = new Dimension(300,250);

	private int qLength = 15;
	private JTextField questionField = new JTextField(qLength);
	private JTextField answerA = new JTextField(qLength);
	private JTextField answerB = new JTextField(qLength);
	private JTextField answerC = new JTextField(qLength);
	private JTextField answerD = new JTextField(qLength);

	private JSpinner spinner = new JSpinner(new SpinnerNumberModel(5,1,10,1));

	private JSlider slider = new JSlider(0,1,1);
	
	private Timer timer = new Timer(2000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
		}
	});
	private boolean isSBuild = false;
	private boolean isQBuild = false;		
	private boolean isHBuild = false;		
	private Sounds sound;
/**
 *The constructor for the GView, this is responsible 
 *for setting up the JFrame and its conditions
 *adding listeners to the windows. 
 */
	public GView() {
		gameWindow = new JFrame("The Quiz Game"); 	
		gameWindow.setSize(450, 300); 										
		gameWindow.setResizable(false); 									
		gameWindow.setVisible(true); 										
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			
		questionWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 	
		settingsWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		helpWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		contentPane = gameWindow.getContentPane();
		makeMenu();

		settingsWindow.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent arg0) {setMenuFrameState(false);}
			public void windowActivated(WindowEvent arg0) {setMenuFrameState(false);}
			public void windowClosed(WindowEvent arg0) {setMenuFrameState(true);}
			public void windowClosing(WindowEvent arg0) {setMenuFrameState(true);}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
		});

		questionWindow.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent arg0) {setMenuFrameState(false);}
			public void windowActivated(WindowEvent arg0) {setMenuFrameState(false);}
			public void windowClosed(WindowEvent arg0) {setMenuFrameState(true);}
			public void windowClosing(WindowEvent arg0) {setMenuFrameState(true);}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
		});
		helpWindow.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent arg0) {setMenuFrameState(false);}
			public void windowActivated(WindowEvent arg0) {setMenuFrameState(false);}
			public void windowClosed(WindowEvent arg0) {setMenuFrameState(true);}
			public void windowClosing(WindowEvent arg0) {setMenuFrameState(true);}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
		});
	}
	/**
	 * Makes the menu
	 * Draws the main window, with all buttons
	 */
	public void makeMenu(){

		contentPane.add(borderPane = new JPanel(), BorderLayout.CENTER);
		borderPane.add(gridPane = new JPanel());
		gridPane.setLayout(new GridLayout(5, 1, 5, 5));

		Dimension d = new Dimension(200, 49);

		gridPane.add(start);
		start.setPreferredSize(d);
		start.setBackground(Color.white);
		menuButtons.add(start);

		gridPane.add(create);
		create.setPreferredSize(d);
		create.setBackground(Color.white);	
		menuButtons.add(create);

		gridPane.add(options);
		options.setPreferredSize(d);
		options.setBackground(Color.white);	
		menuButtons.add(options);

		gridPane.add(help);
		help.setPreferredSize(d);
		help.setBackground(Color.white);
		menuButtons.add(help);

		gridPane.add(quit);
		quit.setPreferredSize(d);
		quit.setBackground(Color.white);
		menuButtons.add(quit);

	}
	/**
	 * Makes the frame for asking the questions
	 */
	public void makeQFrame() {

		question = new JLabel("",JLabel.CENTER);								//Makes a new label with the question
		question.setFont(new Font("Arial", Font.BOLD, 20));

		contentPane.setLayout(new BorderLayout()); 								// adds a container, this one for storing the buttons in the bottom
		contentPane.setBackground(Color.WHITE); 								// Sets the background of the pane
		contentPane.add(question, BorderLayout.CENTER); 						// Adds the question to the top of the layout
		contentPane.add(borderPane = new Container(), BorderLayout.SOUTH);		// adds the button layout to the bottom the layout

		borderPane.setLayout(new GridLayout(2, 2, 5, 5)); 						// Sets the layout of the buttons to a grid 2x2

		buttons = new ArrayList<JButton>();

		borderPane.add(buttonA = new JButton("")); // Adding the buttons to the pane
		borderPane.add(buttonB = new JButton("")); 
		borderPane.add(buttonC = new JButton("")); 
		borderPane.add(buttonD = new JButton("")); 

		buttons.add(buttonA);
		buttons.add(buttonB);
		buttons.add(buttonC);
		buttons.add(buttonD);

		for (JButton b : buttons) {
			b.setPreferredSize(new Dimension(10, 70));
		}

		for (JButton b : buttons) {
			b.setBackground(Color.white);
		}
	}
	/**
	 * Asks the question by naming all
	 * components from makeQFrame
	 * @param quest
	 */
	public void askQuestion(SingleQuestionClient quest) {

		for(JButton b: buttons)
			b.setBackground(Color.white);

		answers = new ArrayList<String>(); 										// Creates an arrayList to store the
		answers.add(quest.getCorrectAnswer()); 									// Adds the answer A to the List
		answers.add(quest.getAnswer2());
		answers.add(quest.getAnswer3());
		answers.add(quest.getAnswer4());

		rightAnswer = answers.get(0);
		Collections.shuffle(answers); 											// Shuffles the list to make them appear in different order.
		question.setText("<html><center>"+quest.getQuestion()+"</center></html>");
		buttonA.setText(answers.get(0));
		buttonB.setText(answers.get(1));
		buttonC.setText(answers.get(2));
		buttonD.setText(answers.get(3));
		enableButtons();

		if(quest.hasSound()){
			sound.playSound(quest.getSound());
		}
	}
	
	void addMenuListener(ActionListener listenForMenu) {
		start.addActionListener(listenForMenu);
		create.addActionListener(listenForMenu);
		options.addActionListener(listenForMenu);
		help.addActionListener(listenForMenu);
		quit.addActionListener(listenForMenu);
	}
	void addAnswerListener(ActionListener listenForPressedAnswer) {
		for (JButton b : buttons) {
			b.addActionListener(listenForPressedAnswer);
		}
	}
	void addSubmitListener(ActionListener listenForSubmission) {
		okButtonQuestion.addActionListener(listenForSubmission);
		cancelButtonQuestion.addActionListener(listenForSubmission);
	}
	void addSettingsListener(ActionListener listenForSetting) {
		okButtonSetting.addActionListener(listenForSetting);
		resetButtonSetting.addActionListener(listenForSetting);
	}
	void addTimerListener(ActionListener listenForTimer) {
		timer.addActionListener(listenForTimer);
	}
	void addStartListener(ActionListener listenForStart) {
		start.addActionListener(listenForStart);
	}
	void addHelpListener(ActionListener helpListener) {
		okButtonHelp.addActionListener(helpListener);
	}
	void addResultListener(ActionListener listenForResult) {
		backToMenu.addActionListener(listenForResult);
		resultquit.addActionListener(listenForResult);
	}
	public void resetFrame() {
		contentPane.removeAll(); 											// Removes all the containers in the contentPane
		contentPane.revalidate();											// to clear the window to get ready for the next question		
		contentPane.repaint(); 												// Repaints the content
	}
	/**
	 * Creates the pop-up for creating
	 * your own question
	 */
	public void makeQuestion() {
		if(isQBuild) {
			questionField.setText(null);
			answerA.setText(null);
			answerB.setText(null);
			answerC.setText(null);
			answerD.setText(null);
			
			questionWindow.setVisible(true);
		}
		else {
			isQBuild = true;
			JPanel buttonsPanel = new JPanel();
			JPanel all = new JPanel();
			questionWindow.setLayout(new BorderLayout());
			all.setLayout(new GridLayout(5,2));

			//The Components
			JLabel l = new JLabel("Skriv in din fråga!", JLabel.CENTER);
			JLabel q = new JLabel("Fråga:", JLabel.CENTER);
			JLabel qa = new JLabel("Svar:", JLabel.CENTER);
			JLabel qb = new JLabel("Alt 1:", JLabel.CENTER);
			JLabel qc = new JLabel("Alt 2:", JLabel.CENTER);
			JLabel qd = new JLabel("Alt 3:", JLabel.CENTER);
			//The Components

			all.add(q);
			all.add(questionField);
			all.add(qa);
			all.add(answerA);
			all.add(qb);
			all.add(answerB);
			all.add(qc);
			all.add(answerC);
			all.add(qd);
			all.add(answerD);

			okButtonQuestion.setBackground(Color.WHITE);
			okButtonQuestion.setPreferredSize(new Dimension(70, 47));
			cancelButtonQuestion.setBackground(Color.WHITE);
			cancelButtonQuestion.setPreferredSize(new Dimension(70, 47));

			questionWindow.add(l, BorderLayout.NORTH);
			questionWindow.add(all,BorderLayout.CENTER);
			questionWindow.add(buttonsPanel,BorderLayout.SOUTH);

			buttonsPanel.add(cancelButtonQuestion);
			buttonsPanel.add(okButtonQuestion);

			questionWindow.setVisible(true);
			questionWindow.setSize(dialog);	
			questionWindow.setResizable(false);
		}
	}
	/**
	 * Creates the pop-up help
	 */
	public void makeHelp(){
		if(isHBuild) {
			helpWindow.setVisible(true);
		}
		else {
			this.isHBuild = true;
			JPanel header = new JPanel();
			JPanel textpanel = new JPanel();
			JPanel buttonpanel = new JPanel();
			JPanel all = new JPanel();

			okButtonHelp.setBackground(Color.WHITE);
			okButtonHelp.setPreferredSize(new Dimension(10, 47));
			all.setLayout(new BorderLayout());

			textpanel.setLayout(new BorderLayout());
			header.setLayout(new BorderLayout());
			buttonpanel.setLayout(new BorderLayout());

			//The Components
			JLabel headertext = new JLabel("", BoxLayout.X_AXIS);
			header.add(headertext);
			JLabel helptext = new JLabel("<html><center>"+"Spelet The Quiz Game går ut"
					+ " på att svara rätt på frågorna man får. Svarar gör man genom att "
					+ "trycka på svaret som man tror är rätt. I slutet av spelet får du reda på antal rätt av "
					+ "antalet frågor som man svarat på. Lycka till!"+"<html><center>", BoxLayout.X_AXIS);
			textpanel.add(helptext);
			buttonpanel.add(okButtonHelp, BorderLayout.CENTER);

			all.add(header, BorderLayout.NORTH);
			all.add(textpanel, BorderLayout.CENTER);

			all.add(buttonpanel, BorderLayout.SOUTH);

			helpWindow.add(all);

			helpWindow.setVisible(true);
			helpWindow.setSize(dialog);	
			helpWindow.setResizable(false);
		}
	}
	/**
	 * This is for getting the values in the "Create your own question"
	 * fields and sent them to the GModel class, via the GController
	 * @return
	 */
	public SingleQuestionClient submitFields() {
		ArrayList<String> a = new ArrayList<String>();	
		a.add(questionField.getText());
		a.add(answerA.getText());
		a.add(answerB.getText());
		a.add(answerC.getText());
		a.add(answerD.getText());
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i).isEmpty()) {
				Component errFrame = null;
				//System.out.println("Du måste fylla i alla fält");
				JOptionPane.showMessageDialog(errFrame, "Du måste fylla i alla fält.");
				return null;
			}	
		}
		SingleQuestionClient q = new SingleQuestionClient(a);
		return q;
	}
	public void closeWindow() {
		gameWindow.dispose();
	}
	public void closeQWindow() {
		questionWindow.dispose();
	}
	public void closeSWindow() {
		settingsWindow.dispose();
	}
	public void closeHWindow() {
		helpWindow.dispose();
	}
/**
 * The options pop-up window
 */
	public void options() {
		if(isSBuild){
			settingsWindow.setVisible(true);					//If this is not done this way, we have to reDraw the entire window every time
		}
		else {
			isSBuild = true;
			settingsWindow.revalidate();
			JPanel noQuestions = new JPanel();
			JPanel sounds = new JPanel();
			JPanel soundBar = new JPanel();
			JPanel buttonsPanel = new JPanel();
			JPanel all = new JPanel();
			all.setLayout(new GridLayout(4,1,10,10));
			all.add(noQuestions);
			all.add(sounds);
			all.add(soundBar);
			all.add(buttonsPanel);
			
			settingsWindow.add(all);

			noQuestions.setLayout(new FlowLayout());
			noQuestions.add(new JLabel("Antal frågor: "));
			noQuestions.add(spinner);
			noQuestions.revalidate();

			sounds.setLayout(new FlowLayout());

			sounds.add(new JLabel("Ställ in ljud On/Off"));

			soundBar.add(slider);
			soundBar.setLayout(new FlowLayout());
			soundBar.revalidate();

			resetButtonSetting.setBackground(Color.WHITE);
			resetButtonSetting.setPreferredSize(new Dimension(100, 47));
			okButtonSetting.setBackground(Color.WHITE);
			okButtonSetting.setPreferredSize(new Dimension(100, 47));
			buttonsPanel.add(resetButtonSetting);
			buttonsPanel.add(okButtonSetting);
			buttonsPanel.setLayout(new FlowLayout());
			buttonsPanel.revalidate();

			settingsWindow.setVisible(true);
			settingsWindow.setSize(dialog);
			settingsWindow.setResizable(false);
			settingsWindow.validate();
			settingsWindow.repaint();
		}
	}
	/**
	 * This is for submitting the options the user set
	 * and being able to get those values
	 * @param option
	 * @return
	 */
	public Options submitOptions(Options option) {
		boolean volume = option.getVolume();
		option.setGameRounds((int)spinner.getValue());
		if((int)slider.getValue() == 0) {
			volume = false;
		}
		else if((int)slider.getValue() == 1) {
			volume = true;
		}
 		option.setVolume(volume);
		return option;
	}
	/**
	 * Resets the options to default
	 * @param option
	 */
	public void resetOptions(Options option) {
		slider.setValue(1);
		spinner.setValue(option.resetGamerounds());
	}
	/**
	 * This frame is for showing the result in the end of a game round
	 * @param a
	 */
	public void showResultRestart(int [] a) {
		int rightA = a[0];
		int wrongA = a[1];
		borderPane.removeAll();
		question.setText("Du fick: "+rightA+" rätt av totalt "+(wrongA+rightA)+ " frågor");
		backToMenu.setBackground(Color.WHITE);
		resultquit.setBackground(Color.WHITE);
		backToMenu.setPreferredSize(new Dimension(10, 60));
		resultquit.setPreferredSize(new Dimension(10, 60));
		borderPane.add(backToMenu);
		borderPane.add(resultquit);
	}
	/**
	 * Disable the question answers
	 * so the user cannot press 
	 * multiple answers
	 */
	public void disableButtons() {
		for (JButton b : buttons) {
			b.setEnabled(false);
		}
	}
/**
 * Enables the question answers
 * in the next question
 */
	public void enableButtons() {
		for (JButton b : buttons) {
			b.setEnabled(true);
		}
	}
/**
 * Sets the color of the pressed
 *  button, depending on wether
 *  the answer is right or wrong
 * @param chosenAnswer
 */
	public void setColor(String chosenAnswer) {
		for (JButton b : buttons) {
			if (b.getText().equals(chosenAnswer)){
				b.setBackground(new Color(200, 0, 0));
				b.setOpaque(true);
			}
			if (b.getText().equals(rightAnswer)){
				b.setBackground(new Color(0, 150, 0));
				b.setOpaque(true);
			}
		}
		timer.start();
	}
	/**
	 * Sets the state of the menu 
	 * buttons whenever a pop-up is opened
	 * @param state
	 */
	public void setMenuFrameState(boolean state) {
		for(JButton b:menuButtons)
			b.setEnabled(state);
	}
	/**
	 * The update method from the observable class
	 * This is used for:
	 * -Asking the question
	 * -Telling which answer to be set correct
	 * -Sending ErrorCodes from the model to be displayed
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GModel && arg instanceof SingleQuestionClient) {
			SingleQuestionClient quest = (SingleQuestionClient) arg;
			askQuestion(quest);
		} 

		else if (o instanceof GModel && arg instanceof String) {
			String ans = (String) arg;
			setColor(ans);
		}
		else if(o instanceof GModel && arg instanceof int[]) {
			showResultRestart((int[]) arg);
		}
		else if(o instanceof GModel && arg instanceof Integer) {			//A way of displaying error codes from the model
			int errorCode = (int)arg;
			 Component errFrame = null;
			 if(errorCode==1) {												//If a connection to the server can not be established, send error message
				 JOptionPane.showMessageDialog(errFrame, "Kunde inte ansluta till servern, använder lokala frågor.");
			 }
			 else if(errorCode==2) {										//If a connection to the server can not be established, send error message
				 JOptionPane.showMessageDialog(errFrame, "Kunde inte ansluta till servern, skriver frågan lokalt istället.");
			 }
		}
	}
}
