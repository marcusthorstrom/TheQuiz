import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GUI implements ActionListener{
	/**
	 * The instance Variables
	 */
	private JFrame gameWindow;
	private Container contentPane;
	private Container borderPane;
	private JLabel question;
	private JButton button;
	private ArrayList<String> answers;
	private ArrayList<JButton> buttons;
	private Sounds sound;
	private GameEngine ge;
	private Timer timer =  new Timer(2000, this);;

	/**
	 * Initiates the window 
	 */
	public GUI(GameEngine ge) {
		this.ge = ge;
		makeGameWindow();
		sound = new Sounds();											//GUI should probably not be playing the sound.
	}
	public void makeGameWindow(){
		gameWindow = new JFrame("The Quiz Game");						//New JFrame for containing the whole game
		gameWindow.pack();												//Repacks the whole window
		gameWindow.setSize(400,200);									//Sets the size for the window
		gameWindow.setResizable(false);									//makes the window resizable
		gameWindow.setVisible(true);									//makes the window visible
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Closes the game on exit
	}
	
	
	/**
	 * Creates the frame for asking the question
	 * @param question1	The Question for the quiz
	 * @param AnswerA	Answer A a.k.a The right Answer
	 * @param AnswerB	Answer B a.k.a the Wrong Answer
	 * @param AnswerC	--||-- C --------||------------
	 * @param AnswerD	--||-- D --------||------------
	 */
	public void askQuestion(ArrayList<String> questions) {
		ge.setIsReady(false);
		ge.setRight(questions.get(1));											//Declares the right Answer to compare the selected one with
		answers = new ArrayList<String>();								//Creates an arrayList to store the answers in
		answers.add(questions.get(1));											//Adds the answer A to the List
		answers.add(questions.get(2));			
		answers.add(questions.get(3));
		answers.add(questions.get(4));
		Collections.shuffle(answers);									//Shuffles the list to make them appear in different order.
		contentPane = gameWindow.getContentPane();						//The top content pane
		question = new JLabel("<html><center>"+questions.get(0)+"</center></html>", JLabel.CENTER);				//Makes a new label with the question
		question.setFont(new Font("Arial", Font.BOLD, 20));				//Sets the font for the question
		contentPane.setLayout(new BorderLayout());						//adds a container, this one for storing the buttons in the bottom
		contentPane.setBackground(Color.WHITE);							//Sets the background of the pane
		contentPane.add(question, BorderLayout.CENTER);					//Adds the question to the top of the layout
		contentPane.add(borderPane = new Container(), BorderLayout.SOUTH); //adds the button layout to the bottom of the layout
		borderPane.setLayout(new GridLayout(2,2,5,5));					//Sets the layout of the buttons to a grid 2x2
		buttons = new ArrayList<JButton>();
		for(String answer: answers){									//Iterates through the shuffled answer List
			borderPane.add(button = new JButton(answer));				//Adding the button to the pane
			button.setFont(new Font("Arial", Font.BOLD, 15));			//Sets the font of the button and the size to 15
			button.setBackground(Color.WHITE);							//Sets the color of the button
			button.setName(answer);										//Setting the name for comparing the button to the right answer
			button.addActionListener(this);
			buttons.add(button);
		}
		
		if(questions.size() == 6 && questions.get(5) != null ){		//If there is a 6th element in arrayList that isn't empty... 
			sound.playSound(questions.get(5));						//Play as sound.
		}
		
	}
	public void showResult(int rightCount, int wrongCount) {
		JLabel label;
		gameWindow.add(label = new JLabel("Du svarade rätt på: "+rightCount+" av: "+(rightCount+wrongCount), JLabel.CENTER), new BorderLayout().CENTER);
	}
	/**
	 * Checks if the button pressed is the right button
	 * @param button The pressed button
	 * @throws InterruptedException 
	 */
	private void isRightAnswer(JButton button) {
		if(ge.isRightAnswer(button.getName())) {						//Checks if the button pressed is the same as the correct one,
			//button.setText("RÄTT!");									//Changes the button text to "RÄTT!" if the answer is right
			button.setBackground(new Color(0, 150, 0));					//Changes the button color to greed to indicate a correct answer
			button.setOpaque(true);
			sound.stopSound();
			sound.playCorrect();	//GUI should probably not be playing the sound!
		}
		else {
			//button.setText("FEL!");									//Changes the text to "FEL!" if the answer is wrong
			button.setBackground(new Color(200, 0, 0));					//Changes the color to red to indicate a wrong answer
			button.setOpaque(true);
			setRightAnswer();
			sound.stopSound();
			sound.playIncorrect();	//GUI should probably not be playing the sound!
		}
		for(JButton b: buttons){
			b.setEnabled(false);
		}
		windowDelay();
	}

	public void setRightAnswer(){
		for(JButton b: buttons){
			if(b.getName().equals(ge.getRight())){
				b.setBackground(new Color(0, 150, 0));					//Nice looking green
				b.setOpaque(true);
			}
		}
	}

	/**
	 * Gets the pane ready for the next question by clearing the pane
	 */
	public void resetFrame() {
		ge.setIsReady(true);
		contentPane.removeAll();										//Removes all the containers in the contenPane to clear the window to get ready for the next question
		contentPane.repaint();											//Repaints the content
	}
	
	public void actionPerformed(ActionEvent e){
		Object objekt = e.getSource();
		if(objekt instanceof JButton) {
			JButton button = (JButton)objekt;
			isRightAnswer(button);

		}
		else if (objekt instanceof Timer) {
			resetFrame();
			timer.stop();
			
		}
		
	}
	public void windowDelay(){
		timer.restart();
	}

}
