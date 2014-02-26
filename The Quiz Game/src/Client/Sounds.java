package Client;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sounds {

	private String correct = "correct.wav";	
	private String incorrect = "incorrect.wav";
	private Clip clip;
	private boolean isOn = true; 

	public Sounds() {}
	/*
	 * Method which plays a sound when called upon. 
	 * Path to file must be specified as parameter.
	 */
	public void playSound(String filePath)
	{
		if(isOn){
			try{
				AudioInputStream aIS = 
						AudioSystem.getAudioInputStream(new File(filePath));
				clip = AudioSystem.getClip();
				clip.open(aIS);	//Opens the AudioInputStream.
				clip.start();	//Plays the sound once. (Might need closing?)
			}

			catch(UnsupportedAudioFileException e){
				System.out.println("Audio-file not supported.");
			}
			catch (LineUnavailableException e) {
				System.out.println("File seem to be busy.");
			}
			catch(IOException e){
				System.out.println("Cannot find " + filePath);
			}
		}
	}

	public void stopSound()
	{
		try{
			clip.stop();
		}
		catch(NullPointerException e){}
	}
	/*
	 * Plays the correct sound.
	 */
	public void playCorrect()
	{
		playSound(correct);
	}
	/*
	 * Plays the incorrect sound.
	 */
	public void playIncorrect()
	{
		playSound(incorrect);
	}
	public void onOff(boolean x){
		isOn = x;	
	}
}
