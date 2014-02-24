package MVC_test;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;

public class GView implements Observer {
	private JFrame gameWindow;
	private JDialog questionWindow = new JDialog(gameWindow,"Skapa Fr�ga");
	private JDialog settingsWindow = new JDialog(gameWindow,"Inst�llningar");

	private ArrayList<String> answers;
	private Container contentPane;
	private JLabel question;
	private Container borderPane;
	private JButton buttonA;
	private JButton buttonB;
	private JButton buttonC;
	private JButton buttonD;
	private ArrayList<JButton> buttons;
	private String rightAnswer;
	private Dimension dialog = new Dimension(300,250);
	private Sounds sound;
	private int qLength = 15;
	private JTextField questionField = new JTextField(qLength);
	private JTextField answerA = new JTextField(qLength);
	private JTextField answerB = new JTextField(qLength);
	private JTextField answerC = new JTextField(qLength);
	private JTextField answerD = new JTextField(qLength);
	private JButton okButtonQuestion = new JButton("Ok");
	private JButton cancelButtonQuestion = new JButton("Avbryt");
	private JButton okButtonSetting = new JButton("Spara");
	private JButton cancelButtonSetting = new JButton("Avbryt");
	
	private JSpinner spinner = new JSpinner(new SpinnerNumberModel(5,1,10,1));
	private JSlider slider = new JSlider(0,100,50);
	private JPanel gridPane;
	private JButton start;
	private JButton create;
	private JButton options;
	private JButton help;
	private JButton quit;
	
	private Timer timer = new Timer(2000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
		}
	});
	
	public GView() {


		gameWindow = new JFrame("The Quiz Game"); // New JFrame for containing the whole game
		gameWindow.setSize(450, 300); // Sets the size for the window
		gameWindow.setResizable(false); // makes the window resizable
		gameWindow.setVisible(true); // makes the window visible
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the game on exit
		contentPane = gameWindow.getContentPane();
		makeMenu();
		//makeQFrame();
		//settings();
		//makeQuestion();
	}
	public void makeMenu(){
		
		contentPane.add(borderPane = new JPanel(), BorderLayout.CENTER);
		borderPane.add(gridPane = new JPanel());
		gridPane.setLayout(new GridLayout(5, 1, 5, 5));

		Dimension d = new Dimension(200, 49);

		gridPane.add(start = new JButton("Start"));
		start.setPreferredSize(d);
		start.setBackground(Color.white);

		gridPane.add(create = new JButton("Egen Fråga"));
		create.setPreferredSize(d);
		create.setBackground(Color.white);
		

		gridPane.add(options = new JButton("Inställningar"));
		options.setPreferredSize(d);
		options.setBackground(Color.white);
		

		gridPane.add(help = new JButton("Hjälp"));
		help.setPreferredSize(d);
		help.setBackground(Color.white);
		
		
		gridPane.add(quit = new JButton("Avsluta"));
		quit.setPreferredSize(d);
		quit.setBackground(Color.white);
		
	}

	public void makeQFrame() {
		
		question = new JLabel("",JLabel.CENTER);				//Makes a new label with the question
		question.setFont(new Font("Arial", Font.BOLD, 20));

		contentPane.setLayout(new BorderLayout()); 				// adds a container, this one for storing the buttons in the bottom

		contentPane.setBackground(Color.WHITE); 				// Sets the background of the pane

		contentPane.add(question, BorderLayout.CENTER); 		// Adds the question to the top of the layout

		contentPane.add(borderPane = new Container(), BorderLayout.SOUTH);// adds the button layout to the bottom the layout

		borderPane.setLayout(new GridLayout(2, 2, 5, 5)); 		// Sets the layout of the buttons to a grid 2x2

		buttons = new ArrayList<JButton>();

		borderPane.add(buttonA = new JButton("")); // Adding the button to the pane
		
		borderPane.add(buttonB = new JButton("")); // Adding the button to the pane
		
		borderPane.add(buttonC = new JButton("")); // Adding the button to the pane
		
		borderPane.add(buttonD = new JButton("")); // Adding the button to the pane
		 
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



	public void askQuestion(SingleQuestion quest) {

		for(JButton b: buttons)
			b.setBackground(Color.white);
		sound = new Sounds();
		answers = new ArrayList<String>(); 								// Creates an arrayList to store the
		// answers in
		answers.add(quest.getCorrectAnswer()); 									// Adds the answer A to the List
		answers.add(quest.getAnswer2());
		answers.add(quest.getAnswer3());
		answers.add(quest.getAnswer4());
		rightAnswer = answers.get(0);
		Collections.shuffle(answers); 									// Shuffles the list to make them appear in different order.
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
		cancelButtonSetting.addActionListener(listenForSetting);
	}

	void addTimerListener(ActionListener listenForTimer) {
		timer.addActionListener(listenForTimer);
	}

	void addStartListener(ActionListener listenForStart) {
		start.addActionListener(listenForStart);
	}
	
	public void resetFrame() {
		contentPane.removeAll(); 										// Removes all the containers in the contenPane !!FUCKAR UPP ALLT!!
		// to clear the window to get ready for the
		// next question		
		contentPane.repaint(); 											// Repaints the content
	}


	public void makeQuestion() {

		JPanel questionpanel = new JPanel();
		JPanel answerApanel = new JPanel();
		JPanel answerBpanel= new JPanel();
		JPanel answerCpanel = new JPanel();
		JPanel answerDpanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		JPanel all = new JPanel();
		questionWindow.setLayout(new FlowLayout());
		questionWindow.add(all);
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

		//The Components
		JLabel l = new JLabel("Skriv in din fr�ga!");
		JLabel q = new JLabel("Fr�gan:     ");
		JLabel qa = new JLabel("R�tt svar:");
		JLabel qb = new JLabel("Svar b:    ");
		JLabel qc = new JLabel("Svar c:    ");
		JLabel qd = new JLabel("Svar d:    ");	
		//The Components

		all.add(l);

		all.add(questionpanel);
		all.add(answerApanel);
		all.add(answerBpanel);
		all.add(answerCpanel);
		all.add(answerDpanel);
		all.add(buttonsPanel);

		buttonsPanel.add(cancelButtonQuestion);
		buttonsPanel.add(okButtonQuestion);

		questionpanel.add(q);
		questionpanel.add(questionField);

		answerApanel.add(qa);
		answerApanel.add(answerA);
		answerBpanel.add(qb);
		answerBpanel.add(answerB);
		answerCpanel.add(qc);
		answerCpanel.add(answerC);
		answerDpanel.add(qd);
		answerDpanel.add(answerD);

		questionWindow.setVisible(true);
		questionWindow.setSize(dialog);	
		questionWindow.setResizable(false);
	}
	public SingleQuestion submitFields() {
		ArrayList<String> a = new ArrayList<String>();	
		a.add(questionField.getText());
		a.add(answerA.getText());
		a.add(answerB.getText());
		a.add(answerC.getText());
		a.add(answerD.getText());
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i).isEmpty()) {
				System.out.println("Du m�ste fylla i alla f�lt");
				return null;
			}	
		}
		SingleQuestion q = new SingleQuestion(a);
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
	

	public void options() {
		JPanel noQuestions = new JPanel();
		JPanel sounds = new JPanel();
		JPanel soundBar = new JPanel();
		JPanel header = new JPanel();
		JPanel all = new JPanel();
		JPanel buttonsPanel = new JPanel();
		all.setLayout(new GridLayout(5,1,10,10));
		all.add(header);
		all.add(noQuestions);
		all.add(sounds);
		all.add(soundBar);
		all.add(buttonsPanel);
		JLabel installningar = new JLabel("Inst�llningar");
		installningar.setFont(new Font("Verdana", Font.CENTER_BASELINE, 25));
		header.add(installningar);
		settingsWindow.add(all);
		noQuestions.setLayout(new FlowLayout());
		noQuestions.add(new JLabel("Antal fr�gor: "));
		noQuestions.add(spinner);
		sounds.setLayout(new FlowLayout());
		sounds.add(new JLabel("St�ll in ljudniv�:"));
		soundBar.add(slider);
		soundBar.setLayout(new FlowLayout());
		buttonsPanel.add(cancelButtonSetting);
		buttonsPanel.add(okButtonSetting);

		settingsWindow.setVisible(true);
		settingsWindow.setSize(dialog);
		settingsWindow.setResizable(false);
	}

	public int[] submitSettings() {
		int a[] = new int[2];
		a[0] = (Integer)spinner.getValue();
		a[1] = (int)slider.getValue();
		return a;

	}

	public void showResult(int [] a) {
		int rightA = a[0];
		int wrongA = a[1];
		borderPane.removeAll();
		question.setText("Du fick: "+rightA+" r�tt av totalt "+(wrongA+rightA)+ " fr�gor");
	}

	public void disableButtons() {
		for (JButton b : buttons) {
			b.setEnabled(false);
		}
	}

	public void enableButtons() {
		for (JButton b : buttons) {
			b.setEnabled(true);
		}
	}

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

	public void stopSound()
	{
		sound.stopSound();
	}

	public void playSoundFeedback(boolean isCorrect){
		if(isCorrect == true)
			sound.playCorrect();
		else
			sound.playIncorrect();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GModel && arg instanceof SingleQuestion) {
			SingleQuestion quest = (SingleQuestion) arg;
			askQuestion(quest);
		} 

		else if (o instanceof GModel && arg instanceof String) {
			String ans = (String) arg;
			setColor(ans);
		}
		else if(o instanceof GModel && arg instanceof int[]) {
			showResult((int[]) arg);
		}

		else if(o instanceof GModel && arg instanceof Boolean){
			boolean isCorrect = (Boolean)arg;
			playSoundFeedback(isCorrect);

		}
	}
}
