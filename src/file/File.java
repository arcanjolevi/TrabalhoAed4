package file;

import java.io.BufferedReader;
import java.io.FileReader;
import structure.*;

//Class that manipulates a file
public class File {

    /* Method that check if the word is a valid one
     * Input:        Word to be checked
     * Return:       True if it's a valid word or false otherwise
     * Precondition: None
     */
    public Boolean WordCheck(String line) {
        return line.matches("[A-Za-z]+");
    }

    /*
     * Method that removes blank spaces in string
     * Input:        None
     * Return:       String without spaces 
     * Precondition: None
     */
    public String removeWhiteSpace(String str) {
        int i;
        String[] aux;
        String newString = "";
        aux = str.split(" ");
        for (i = 0; i < aux.length; i++) {
            newString += aux[i];
        }
        return newString;
    }

    /* Method that load the file with the strings to be added to the tree
     * Input:        File path to be loaded
     * Return:       Trie class filled with the read words
     * Precondition: None
     */
    public Trie loadWordsFile(String filePath) throws Exception {
        Trie trie = new Trie();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            line = this.removeWhiteSpace(line);
            if (this.WordCheck(line) == true) {
                line = line.toLowerCase();
                trie.insert(line);
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return trie;
    }

    /* Method that load the stopwords file and remove them from the dictionary
     * Input         File path to be loaded and trie tree root
     * Return:       tree root with words removed
     * Precondition: root not null
     */
    public TrieRoot loadStopWordsFile(String filePath, TrieRoot trie) throws Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
        	line = this.removeWhiteSpace(line);
        	if (this.WordCheck(line) == true) {
        		line = line.toLowerCase();
            	trie.remove(line);
        	}
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return trie;
    }
}
