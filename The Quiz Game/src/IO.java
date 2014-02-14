import java.io.*;
import java.util.ArrayList;


public class IO {
	private ArrayList<String> questions;
	private ArrayList<ArrayList<String>> allQuestions;
	private BufferedReader br;
	public IO() {
	}
	private void readFile() {
		questions = new ArrayList<String>();
		allQuestions = new ArrayList<ArrayList<String>>();
		try {
			
			if (OSDetector.isMac()){
			//br = new BufferedReader(new FileReader(System.getProperty ("user.home") + "/Desktop/" + "fragor.txt"));
			br = new BufferedReader(new FileReader("fragor.txt"));
			}
			else if (OSDetector.isWindows())
			{
				br = new BufferedReader(new FileReader("fragor.txt"));
			}
			
			String line;
			while((line = br.readLine()) != null) {
				if(line.equals("")){
					@SuppressWarnings("unchecked")
					ArrayList<String> tempQuest = ((ArrayList<String>) questions.clone());
					allQuestions.add(tempQuest);
					questions.clear();
				}
				else { 
					questions.add(line);
				}
			}
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		
		catch (IOException e) {
			System.out.println("ERROR! Error occurd when file was read.");
		}
	}
	public ArrayList<ArrayList<String>> getArray(){
		readFile();
		return allQuestions;
	}
	
	/* public void newQuestion(ArrayList<String> question){
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter("fragor.txt"));
	            for (int i = 0; i < 4; i++) {
	                out.write("test " + "\n");
	            }
	            out.close();
	        } catch (IOException e) {}
	} 
	BufferedWriter out = null;
try  
{
    FileWriter fstream = new FileWriter("out.txt", true); //true tells to append data.
    out = new BufferedWriter(fstream);
    out.write("\nsue");
}
catch (IOException e)
{
    System.err.println("Error: " + e.getMessage());
}
finally
{
    if(out != null) {
        out.close();
    }
}
	*/
}

