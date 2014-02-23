package MVC_test;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.Timer;

public class GView implements Observer {
	private JFrame gameWindow;
	private JDialog questionWindow;
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
	private Dimension dialog = new Dimension(300,300);
	private Sounds sound;
	private JTextField questionField = new JTextField(15);
	private JTextField answerA = new JTextField(15);
	private JTextField answerB = new JTextField(15);
	private JTextField answerC = new JTextField(15);
	private JTextField answerD = new JTextField(15);
	private JButton okButton = new JButton("Submit");


	Timer timer = new Timer(2000, new ActionListener() {
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
		makeQFrame();
		
		//makeQuestion();
	}
		public void makeQFrame() {
		contentPane = gameWindow.getContentPane();
		question = new JLabel("",JLabel.CENTER);				//Makes a new label with the question
		question.setFont(new Font("Arial", Font.BOLD, 20));

		contentPane.setLayout(new BorderLayout()); 				// adds a container, this one for storing the buttons in the bottom
							 
		contentPane.setBackground(Color.WHITE); 				// Sets the background of the pane

		contentPane.add(question, BorderLayout.CENTER); 		// Adds the question to the top of the layout
		
		contentPane.add(borderPane = new Container(), BorderLayout.SOUTH);// adds the button layout to the bottom the layout

		borderPane.setLayout(new GridLayout(2, 2, 5, 5)); 		// Sets the layout of the buttons to a grid 2x2

		buttons = new ArrayList<JButton>();

		borderPane.add(buttonA = new JButton("")); // Adding the button to the
													// pane
		borderPane.add(buttonB = new JButton("")); // Adding the button to the
													// pane
		borderPane.add(buttonC = new JButton("")); // Adding the button to the
													// pane
		borderPane.add(buttonD = new JButton("")); // Adding the button to the
													// pane
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
	
	
	

	public void askQuestion(ArrayList<String> questions) {
		
		sound = new Sounds();
	}


	public void askQuestion(SingleQuestion quest) {

		for( JButton b: buttons)
			b.setBackground(Color.white);

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

	void addAnswerListner(ActionListener listenForPressedAnswer) {
		for (JButton b : buttons) {
			b.addActionListener(listenForPressedAnswer);
		}
	}
	void addSubmitListner(ActionListener listenForSubmission) {
		okButton.addActionListener(listenForSubmission);
	}

	void addTimerListner(ActionListener listenForTimer) {
		timer.addActionListener(listenForTimer);
	}

	public void resetFrame() {
		contentPane.removeAll(); 										// Removes all the containers in the contenPane !!FUCKAR UPP ALLT!!
		// to clear the window to get ready for the
		// next question		
		contentPane.repaint(); 											// Repaints the content
	}
	
	
	public void makeQuestion() {
		questionWindow = new JDialog(gameWindow,"Inställningar");
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
		JLabel l = new JLabel("Skriv in din fråga!");
		JLabel q = new JLabel("Frågan:     ");
		JLabel qa = new JLabel("Rätt svar:");
		JLabel qb = new JLabel("Svar b:    ");
		JLabel qc = new JLabel("Svar c:    ");
		JLabel qd = new JLabel("Svar d:    ");	
		JTextField questionField = new JTextField(15);
		JTextField answerA = new JTextField(15);
		JTextField answerB = new JTextField(15);
		JTextField answerC = new JTextField(15);
		JTextField answerD = new JTextField(15);
		JButton cancelButton = new JButton("Cancel");
		//The Components
		
		all.add(l);
		
		all.add(questionpanel);
		all.add(answerApanel);
		all.add(answerBpanel);
		all.add(answerCpanel);
		all.add(answerDpanel);
		all.add(buttonsPanel);
		
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(okButton);
		
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
	}
	public SingleQuestion submitFields() {
		ArrayList<String> a = new ArrayList<String>();
		a.add(questionField.getText());
		a.add(answerA.getText());
		a.add(answerB.getText());
		a.add(answerC.getText());
		a.add(answerD.getText());
		SingleQuestion q = new SingleQuestion(a);
		return q;
		
	}
	

	public void showResult(int [] a) {
		int rightA = a[0];
		int wrongA = a[1];
		borderPane.removeAll();
		question.setText("Du fick: "+rightA+" rätt av totalt "+(wrongA+rightA)+ " frågor");
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
