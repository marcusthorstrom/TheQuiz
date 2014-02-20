package MVC_test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class GView implements Observer{
	private JFrame gameWindow;
	private ArrayList<String> answers;
	private Container contentPane;
	private JTextArea question;
	private Container borderPane;
	private JButton buttonA;
	private JButton buttonB;
	private JButton buttonC;
	private JButton buttonD;
	private ArrayList<JButton> buttons;
	
	
	Timer timer =  new Timer(2000, new ActionListener() { 
		public void actionPerformed(ActionEvent e) {
			resetFrame();
			timer.stop();
		}
	});


	public GView() {
		
		
		gameWindow = new JFrame("The Quiz Game");						//New JFrame for containing the whole game
		gameWindow.pack();												//Repacks the whole window
		gameWindow.setSize(500,200);									//Sets the size for the window
		gameWindow.setResizable(false);									//makes the window resizable
		gameWindow.setVisible(true);									//makes the window visible
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Closes the game on exit
		contentPane = gameWindow.getContentPane();
		question = new JTextArea();	
		question.setFont(new Font("Arial", Font.BOLD, 20));				//Sets the font for the question
		question.setWrapStyleWord(true);
		question.setLineWrap(true);
		question.setSize(10, 10);
		question.setEditable(false);
		question.setEditable(false);
		contentPane.setLayout(new BorderLayout());						//adds a container, this one for storing the buttons in the bottom
		contentPane.setBackground(Color.WHITE);							//Sets the background of the pane
		contentPane.add(question, BorderLayout.CENTER);					//Adds the question to the top of the layout
		contentPane.add(borderPane = new Container(), BorderLayout.SOUTH);//adds the button layout to the bottom of the layout
		borderPane.setLayout(new GridLayout(2,2,5,5));					//Sets the layout of the buttons to a grid 2x2
		buttons = new ArrayList<JButton>();
		borderPane.add(buttonA = new JButton(""));						//Adding the button to the pane
		borderPane.add(buttonB = new JButton(""));						//Adding the button to the pane
		borderPane.add(buttonC = new JButton(""));						//Adding the button to the pane
		borderPane.add(buttonD = new JButton(""));						//Adding the button to the pane
		
		buttons.add(buttonA);
		buttons.add(buttonB);
		buttons.add(buttonC);
		buttons.add(buttonD);
		
		for(JButton b: buttons) {
			b.setBackground(Color.white);
		}
	}
	public void askQuestion(ArrayList<String> questions) {
		answers = new ArrayList<String>();								//Creates an arrayList to store the answers in
		answers.add(questions.get(1));									//Adds the answer A to the List
		answers.add(questions.get(2));			
		answers.add(questions.get(3));
		answers.add(questions.get(4));
		Collections.shuffle(answers);									//Shuffles the list to make them appear in different order.
		question.setText(questions.get(0));
		buttonA.setText(answers.get(0));
		buttonB.setText(answers.get(1));
		buttonC.setText(answers.get(2));
		buttonD.setText(answers.get(3));
		
		/*if(questions.size() == 6 && questions.get(5) != null ){		//If there is a 6th element in arrayList that isn't empty... 
			sound.playSound(questions.get(5));						//Play as sound.
		*/
	}
	
	void addAnswerListner(ActionListener listenForPressedAnswer) {
		for(JButton b: buttons) {
			b.addActionListener(listenForPressedAnswer);
		}
	}
	void addTimerListner(ActionListener listenForTimer) {
		timer.addActionListener(listenForTimer);
	}
	public void resetFrame() {
		contentPane.removeAll();										//Removes all the containers in the contenPane to clear the window to get ready for the next question
		contentPane.repaint();											//Repaints the content
	}
	public void disableButtons() {
		for(JButton b: buttons) {
			b.setEnabled(false);
		}
	}
	public void setRightAnswer(String rightAnswer) {
		for(JButton b: buttons) {
			if(b.getText().equals(rightAnswer)) {
				b.setBackground(new Color(0, 150, 0));
			}
		}
		timer.start();
	}
	public void setWrongAnswer(String wrongAnswer) {
		for(JButton b: buttons) {
			if(b.getText().equals(wrongAnswer)) {
				b.setBackground(new Color(200, 0, 0));
			}
		}
		timer.start();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GModel && arg instanceof ArrayList<?>)
		{
			ArrayList<String> qList = (ArrayList<String>)arg;
			askQuestion(qList);
		}
		
	}

	
	
}