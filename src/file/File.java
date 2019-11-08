package file;

import java.io.BufferedReader;
import java.io.FileReader;

//Class that manipulates a file
public class File {
        
	//method that checks the word, returns true if it is right or false otherwise
    public Boolean WordCheck(String Line) {
    	return Line.matches("[A-Za-z]+");
    }
    
    /* Method that upload start file
     * Input:		 File path to be load
     * Return:		 None
     * PreCondition: None
     * PosCondition: words loaded
     */
    public void loadFileInsert(String FilePath) throws Exception{
        FileReader Read = new FileReader(FilePath);
        BufferedReader ReadFile = new BufferedReader(Read);
        String Line = ReadFile.readLine();
        while(Line != null) {
        	if(this.WordCheck(Line)) {
        		Line.toLowerCase();
//				inserir();
        	}
        	Line = ReadFile.readLine();        	
        }
        ReadFile.close();
    }
    
    /* Method that uploads the stopwords file and removes them from the dictionary
     * Input:		 File path to be load
     * Return:		 None
     * PreCondition: None
     * PosCondition: words loaded
     */
    public void loadFileRemove(String FilePath) throws Exception{
        FileReader Read = new FileReader(FilePath);
        BufferedReader ReadFile = new BufferedReader(Read);
        String Line = ReadFile.readLine();
        while(Line != null) {
        	Line.toLowerCase();
//			remove();
        	Line = ReadFile.readLine();        	
        }
        ReadFile.close();
    }
}
