import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI implements ActionListener{
	/**
	 * The instance Variables
	 */
	public JFrame gameWindow;
	public Container contentPane;
	public Container borderPane;
	public JLabel question;
	public JButton button;
	public String rightAnswer;
	public ArrayList<String> answers;
	
	/**
	 * Initiates the window 
	 */
	public GUI() {
		
		gameWindow = new JFrame("The Quiz Game");					//New JFrame for containing the whole game
		gameWindow.pack();											//Repacks the whole window
		gameWindow.setSize(500,200);								//Sets the size for the window
		gameWindow.setResizable(false);								//makes the window resizable
		gameWindow.setVisible(true);								//makes the window visable
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Closes the game on exit		
		askQuestion("Vem sköt president Kennedy?", "Alex", "Vidar", "Simon", "Emil");
		
	}
	/**
	 * Creates the frame for asking the question
	 * @param question1	The Question for the quiz
	 * @param AnswerA	Answer A a.k.a The right Answer
	 * @param AnswerB	Answer B a.k.a the Wrong Answer
	 * @param AnswerC	--||-- C --------||------------
	 * @param AnswerD	--||-- D --------||------------
	 */
	public void askQuestion(String question1, String AnswerA, String AnswerB, String AnswerC, String AnswerD) {
		rightAnswer = AnswerA;										//Declares the right Answer to compare the selected one with
		answers = new ArrayList<String>();							//Creates an arrayList to store the answers in
		answers.add(AnswerA);										//Adds the answer A to the List
		answers.add(AnswerB);			
		answers.add(AnswerC);
		answers.add(AnswerD);
		Collections.shuffle(answers);								//Shuffles the list to make them appear in different order.
		contentPane = gameWindow.getContentPane();					//The top content pane
		question = new JLabel(question1, JLabel.CENTER);			//Makes a new label with the question
		question.setFont(new Font("Sans", Font.BOLD, 20));			//Sets the font for the question
		contentPane.setLayout(new BorderLayout());					//adds a container, this one for storing the buttons in the bottom
		contentPane.setBackground(Color.WHITE);						//Sets the background of the pane
		contentPane.add(question, BorderLayout.CENTER);				//Adds the question to the top of the layout
		contentPane.add(borderPane = new Container(), BorderLayout.SOUTH); //adds the button layout to the bottom of the layout
		borderPane.setLayout(new GridLayout(2,2,5,5));				//Sets the layout of the buttons to a grid 2x2
		for(String answer: answers){								//Iterates through the shuffled answer List
			borderPane.add(button = new JButton(answer));			//Adding the button to the pane
			button.setFont(new Font("Sans", Font.BOLD, 15));		//Sets the font of the button and the size to 15
			button.setBackground(Color.WHITE);						//Sets the color of the button
			button.setName(answer);									//Seting the name for comparing the button to the right answer
			button.addActionListener(this);			
		}

	}
	/**
	 * Checks if the button pressed is the right button
	 * @param button The pressed button
	 * @throws InterruptedException 
	 */
	private void isRightAnswer(JButton button) {
		if(button.getName().equals(rightAnswer)) {					//Checks if the button pressed is the same as the correct one,
		//button.setText("RÄTT!");									//Changes the button text to "RÄTT!" if the answer is right
		button.setBackground(Color.GREEN);							//Changes the button color to greed to indicate a correct answer
		rightCounter();												//Counts the number of rightAnswer
		}
		else {
		//button.setText("FEL!");									//Changes the text to "FEL!" if the answer is wrong
		button.setBackground(Color.RED);							//Changes the color to red to indicate a wrong answer
		wrongCounter();												//Counts the number of wrongAnswer
		}
		//nextQuestion();
	}
/**
 * Gets the pane ready for the next question by clearing the pane
 */
	public void nextQuestion() {
		contentPane.removeAll();									//Removes all the containers in the contenPane to clear the window to get ready for the next question
		contentPane.repaint();										//Repaints the content
		contentPane.revalidate();									//Revalidates the content
		askQuestion("Fråga 2", "Svar A", "Svar B", "Svar C", "Svar D");	//Asks a new question
	}
/**
 * Counts the number of wrong answers
 */
	public void wrongCounter() {
		//Empty method
	}
/**
 * Counts the number of right answers
 */
	public void rightCounter() {
		//Empty method
	}
/**
 * the main function to be able to execute the program	
 * @param args
 */
public static void main (String[] args) {
		new GUI();
	}
public void actionPerformed(ActionEvent e){
	Object objekt = e.getSource();
    if(objekt instanceof JButton) {
    	JButton button = (JButton)objekt;
    	isRightAnswer(button);
    	
    }
    else {}
    }
}
