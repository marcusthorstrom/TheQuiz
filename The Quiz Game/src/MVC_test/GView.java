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
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GView implements Observer {
	private JFrame gameWindow;
	private JDialog questionWindow = new JDialog(gameWindow,"Skapa Fr�ga");
	private JDialog settingsWindow = new JDialog(gameWindow,"Inst�llningar");
	private JDialog helpWindow = new JDialog(gameWindow,"Hj�lp");
	
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
	private JButton create = new JButton("Egen Fr�ga");
	private JButton options = new JButton("Inst�llningar");
	private JButton help = new JButton("Hj�lp");
	private JButton quit = new JButton("Avsluta");
	private JButton start = new JButton("Start");
	private JButton okButtonQuestion = new JButton("Ok");
	private JButton cancelButtonQuestion = new JButton("Avbryt");
	private JButton okButtonSetting = new JButton("Anv�nd");
	private JButton resetButtonSetting = new JButton("�terst�ll");
	private JButton backToMenu = new JButton("�terg� till meny");
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
	private JSlider slider = new JSlider(0,10,5);
	
	private Timer timer = new Timer(2000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
		}
	});
	private boolean isSBuild = false;		//If the SettingsWindow is build
	private boolean isQBuild = false;		//If the QuestionWindow is build
	private boolean isHBuild = false;		//If the HelpWindoe is build
	private Sounds sound;

	public GView() {
		
		sound = new Sounds();

		gameWindow = new JFrame("The Quiz Game"); 						// New JFrame for containing the whole game
		gameWindow.setSize(450, 300); 									// Sets the size for the window
		gameWindow.setResizable(false); 								// makes the window resizable
		gameWindow.setVisible(true); 									// makes the window visible
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// Closes the game on exit
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
	public void askQuestion(SingleQuestion quest) {

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

	public void makeQuestion() {
		if(isQBuild) {
			questionWindow.setVisible(true);
		}
		else {
			
			isQBuild = true;
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
			JLabel q = new JLabel("Fr�ga:", JLabel.TRAILING);
			JLabel qa = new JLabel("Svar:", JLabel.TRAILING);
			JLabel qb = new JLabel("Alt 1:", JLabel.TRAILING);
			JLabel qc = new JLabel("Alt 2:", JLabel.TRAILING);
			JLabel qd = new JLabel("Alt 3:", JLabel.TRAILING);
			//The Components
		
			okButtonQuestion.setBackground(Color.WHITE);
			okButtonQuestion.setPreferredSize(new Dimension(70, 47));
			cancelButtonQuestion.setBackground(Color.WHITE);
			cancelButtonQuestion.setPreferredSize(new Dimension(70, 47));
			
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
	}

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
			JLabel helptext = new JLabel("<html><center>"+"Spelet The Quiz Game g�r ut"
					+ " p� att svara r�tt p� fr�gorna man f�r. Svarar g�r man genom att "
					+ "trycka p� svaret som man tror �r r�tt. I slutet av spelet f�r du reda p� antal r�tt av "
					+ "antalet fr�gor som man svarat p�. Lycka till!"+"<html><center>", BoxLayout.X_AXIS);
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
	public SingleQuestion submitFields() {
		ArrayList<String> a = new ArrayList<String>();	
		a.add(questionField.getText());
		a.add(answerA.getText());
		a.add(answerB.getText());
		a.add(answerC.getText());
		a.add(answerD.getText());
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i).isEmpty()) {
				Component errFrame = null;
				//System.out.println("Du m�ste fylla i alla f�lt");
				JOptionPane.showMessageDialog(errFrame, "Du m�ste fylla i alla f�lt.");
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
	public void closeHWindow() {
		helpWindow.dispose();
	}
	public void validateH () {

	}
	
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
			noQuestions.repaint();
					
			settingsWindow.add(all);
			
			noQuestions.setLayout(new FlowLayout());
			noQuestions.add(new JLabel("Antal fr�gor: "));
			noQuestions.add(spinner);
			noQuestions.revalidate();
			
			sounds.setLayout(new FlowLayout());
		
			sounds.add(new JLabel("St�ll in ljudniv�:"));
	
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

	public Options submitOptions(Options option) {
		 option.setGameRounds((int)spinner.getValue());
		 option.setVolume((int)slider.getValue());
		return option;

	}
	
	public void resetOptions() {
		slider.setValue(50);
		spinner.setValue(5);
	}

	public void showResultRestart(int [] a) {
		int rightA = a[0];
		int wrongA = a[1];
		borderPane.removeAll();
		question.setText("Du fick: "+rightA+" r�tt av totalt "+(wrongA+rightA)+ " fr�gor");
		backToMenu.setBackground(Color.WHITE);
		resultquit.setBackground(Color.WHITE);
		backToMenu.setPreferredSize(new Dimension(10, 60));
		resultquit.setPreferredSize(new Dimension(10, 60));
		borderPane.add(backToMenu);
		borderPane.add(resultquit);
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
			showResultRestart((int[]) arg);
		}
	}
	public void setMenuFrameState(boolean state) {
		for(JButton b:menuButtons)
			b.setEnabled(state);
	}
	public void stopSound() {
		sound.stopSound();
		
	}
	
}
