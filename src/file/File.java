package file;

import java.io.BufferedReader;
import java.io.FileReader;
import structure.Trie;

//Class that manipulates a file
public class File {
    /* Method that check if the word is a valid word
     * Input:        Word to be checked
     * Return:       True if it's a valid word or false otherwise
     * Precondition: None
    */
    public Boolean WordCheck(String line) {
    	return line.matches("[A-Za-z]+");
    }
    
    /* Method that load the file with the strings to be added to the tree
     * Input:        File path to be loaded
     * Return:       Trie class filled with the read words
     * PreCondition: None
     */
    public Trie loadWordsFile(String filePath) throws Exception{
        Trie trie = new Trie();
        FileReader Read = new FileReader(filePath);
        BufferedReader ReadFile = new BufferedReader(Read);
        String Line = ReadFile.readLine();
        while(Line != null) {
        	if(this.WordCheck(Line) == true) {
        		Line = Line.toLowerCase();
				trie.insert(Line);
        	}
        	Line = ReadFile.readLine();        	
        }
        ReadFile.close();
        return trie;
    }
    
    /* Method that load the stopwords file and remove them from the dictionary
     * Input         File path to be loaded
     * Return:       None
     * PreCondition: None
     */
    public void loadStopWordsFile(String filePath) throws Exception{
        FileReader Read = new FileReader(filePath);
        BufferedReader ReadFile = new BufferedReader(Read);
        String Line = ReadFile.readLine();
        while(Line != null) {
        	Line = Line.toLowerCase();
//			remove();
        	Line = ReadFile.readLine();        	
        }
        ReadFile.close();
    }
}