package structure;

import generic.*;
import java.util.ArrayList;

//Class that represents a Trie tree
public class Trie implements Speaker {
    private boolean endOfWord;
    private Trie alphabet[];
    private ArrayList<Listener> listeners;

    //Trie constructor
    public Trie() {
        this.alphabet = new Trie[26];
        this.endOfWord = false;
        this.listeners = new ArrayList<Listener>();
        this.initTrieVector();
    }

    //Listeners getter
    public ArrayList<Listener> getListeners() {
        return this.listeners;
    }

    //EndOfWord getter
    public Boolean isEndOfWord() {
        return endOfWord;
    }

    //EndOfWord setter
    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    //Node getter
    public Trie getNode(Integer index) {
        return alphabet[index];
    }

    //Node overloaded getter
    public Trie getNode(Character letter) {
        return alphabet[this.getIndex(letter)];
    }

    //Node setter
    public void setNode(Trie letter, Integer index) {
        this.alphabet[index] = letter;
    }

    //Node overloaded setter
    public void setNode(Trie letter, Character index) {
        this.alphabet[this.getIndex(index)] = letter;
    }

    /* Method that initialize the vector of the tree
     * Input:        None
     * Return:       None
     * Precondition: None
     */
    private void initTrieVector() {
        int i;
        for (i = 0; i < 26; i++) {
            this.alphabet[i] = null;
        }
    }

    /* Method that return the position of a letter in a vector with 26 positions
     * Input:        Letter to be calculated
     * Return:       Position in the vector
     * Precondition: The letter must be betwen a and z
     */
    public Integer getIndex(Character letter) {
        return (int) (letter - 'a');
    }

    /* Method that return the value of a position in a vector with 26 positions
     * Input:        Index of the vector to be calculated
     * Return:       Character represented by the position
     * Precondition: The index must be betwen 0 and 25
     */
    public Character getCharacter(Integer index) {
        return (char) ('a' + index);
    }

    /* Verify if a letter is or not in the vector
     * Input:        Letter to be checked
     * Return:       True if it's in or false otherwise
     * Precondition: The letter must be betwen a and z
     */
    public Boolean isInserted(Character letter) {
        return this.getNode(letter) != null;
    }

    /* Verify if a letter is or not in the vector
     * Input:        Letter to be checked
     * Return:       True if it's in or false otherwise
     * Precondition: The letter must be betwen a and z
     */
    public Boolean isInserted(Integer index) {
        return this.getNode(index) != null;
    }

    /* Insert a word in the trie
     * Input:        Word to be inserted
     * Return:       Node inserted
     * Precondition: The text must only contain letters betwen a and z or empty word
     */
    public void insert(String text) {
        if (text.length() > 0) {
            Character letterToInsert = text.toCharArray()[0];
            if (this.isInserted(letterToInsert) == true) {
                this.getNode(letterToInsert).insert(text.substring(1, text.length())); //Recursion
            } else {
                try {
                    this.alphabet[this.getIndex(letterToInsert)] = new Trie();
                    this.getNode(letterToInsert).insert(text.substring(1, text.length())); //Recursion
                } catch (Exception ex) {
                    this.speak("error," + ex.getMessage());
                }
            }
        } else {
            this.endOfWord = true;
        }
    }

    /* Method that get all the words on the trie in alphabetic order
     * Input:        String that contain the prefixed word formed until the moment
     * Return:       None
     * Precondition: None
     */
    public void getDictionary(String prefix, String speakMsg) {
        for (int i = 0; i < 26; i++) {
            if (this.isInserted(i) == true) {
                prefix += this.getCharacter(i); //Add one more letter to the word to be printed
                if (this.getNode(i).endOfWord == true) {
                    this.speak(speakMsg + prefix + "\n");
                }
                this.getNode(i).getDictionary(prefix, speakMsg); //Recursion
                prefix = prefix.substring(0, prefix.length() - 1); //clean the already printed word
            }
        }
    }

    /* Method used to initialize the word find function
     * Input:        None
     * Return:       None
     * Precondition: None
     */
    public void startWordSearch() {
        this.getDictionary("", "newDictionaryWord,");
    }

    /* Method that find and speak out all similar words by a given subWord
     * Input:        Subword to be find and Listeners list
     * Return:       None
     * Precondition: None
     */
    public void getSimilarWords(String subWord) {
        Trie aux = this;
        int i;
        for (i = 0; aux != null && i < subWord.length(); i++) {
            aux = aux.getNode(subWord.toCharArray()[i]);
        }
        if (aux == null) {
            this.speak("derivatedWordPrintEnded,Não existem paralavras similares");
        } else {
            aux.getDictionary(subWord, "newDerivatedWord,");
            this.speak("derivatedWordPrintEnded,");
        }
    }

    /* Method that returns how many positions of the vector are instantiated
     * Input:        None
     * Return:       true if there is more than one instance or false otherwise
     * Precondition: None
     */
    public boolean checkNode() {
        int validNodes = 0;
        for (int i = 0; i < 26; i++) {
            if (this.alphabet[i] != null) {
                validNodes++;
            }
        }
        return validNodes > 1;
    }
    /* Method that check if there are any valid nodes
     * Input:        None
     * Return:       Returns true if any is instantiated or false otherwise
     * Precondition: None
     */
    public boolean checkValidNode() {
        for (int i = 0; i < 26; i++) {
            if (this.alphabet[i] != null) {
                return true;
            }
        }
        return false;
    }

    /* Method that checks if there are next nodes from this node
     * Input:        None
     * Return:       True if it has one or more nodes and False otherwise
     * Precondition: None
     */
    public boolean checkNextNode() {
        Trie aux;
        for (int i = 0; i < 26; i++) {
            if (this.alphabet[i] != null) {
                aux = this.getNode(this.getCharacter(i));
                if (aux.checkValidNode()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Method that remove words from the tree
     * Input:        Word to be removed
     * Return:       Amount of nonzero positions in vector
     * Precondition: None
     */
    public boolean stopWords(String word) {
        if (word.length() != 0) {
            Character letterToInsert = word.toCharArray()[0];
            if (this.isInserted(letterToInsert) == true) {
                if (this.getNode(letterToInsert).stopWords(word.substring(1, word.length()))) {
                	if(!this.checkNextNode())
                		this.alphabet[this.getIndex(letterToInsert)] = null;
                    return this.checkNode();
                } else {
                    return false;
                }
            }
        }
        this.endOfWord = false;
        return this.checkNode();
    }

    /* Method that checks whether the word is similar to the input word within the stipulated distance
     * Input:        String with the main word, string with the word to be tested, and the stipulated distance
     * Return:       True if they look alike and if not false
     * Precondition: None
     */
    public boolean CheckSimilarity(String wordMain, String wordBeingTested, int distancFromWords) {
        if (distancFromWords >= 0) {
        	if( wordMain.length() == 0 && wordBeingTested.length() == 0) 
        		return true;
            if ( wordMain.length() == 0 ) 
            	return (wordBeingTested.length() <= distancFromWords);
            if ( wordBeingTested.length() == 0 ) 
            	return (wordMain.length() <= distancFromWords);
            Character a = wordMain.toCharArray()[0];
            Character b = wordBeingTested.toCharArray()[0];
            if (a.compareTo(b) == 0) 
                return this.CheckSimilarity(wordMain.substring(1, wordMain.length()), wordBeingTested.substring(1, wordBeingTested.length()), distancFromWords);
            else if(a.compareTo(b) < 0)
                return this.CheckSimilarity(wordMain, wordBeingTested.substring(1, wordBeingTested.length()), distancFromWords);
            else 
            	return this.CheckSimilarity(wordMain.substring(1, wordMain.length()),wordBeingTested, distancFromWords-1);
        }
        return false;
    }

    /* Method that queries all words to see if it's similar
     * Input:        String with the main word, the stipulated distance,String that contain the prefixed word formed until the moment
     * Return:       None
     * Precondition: None
     */
    public void consultSimilar(String wordMain, int distancFromWords, String prefix, String speakMsg) {
        for (int i = 0; i < 26; i++) {
            if (this.isInserted(i) == true) {
                prefix += this.getCharacter(i); //Add one more letter to the word to be printed
                if (this.getNode(i).endOfWord == true) {
                    if (this.CheckSimilarity(wordMain, prefix, distancFromWords)) {//
                        this.speak(speakMsg + prefix + "\n");
                    }
                }
                this.getNode(i).consultSimilar(wordMain, distancFromWords, prefix, speakMsg); //Recursion
                prefix = prefix.substring(0, prefix.length() - 1); //clean the already printed word
            }
        }
    }

    /* Method that subscribe a listener to all trie nodes
     * Input:        Listener to be subscribed
     * Return:       None
     * Precondition: None
     */
    @Override
    public void subscribe(Object listener) throws Exception {
        int i;
        if (listener instanceof Listener) {
            if (!this.listeners.contains((Listener) listener)) {
                this.listeners.add((Listener) listener);
            }
            for (i = 0; i < 26; i++) {
                if (this.isInserted(i)) {
                    this.getNode(i).subscribe(listener); //Subscribe recursion
                }
            }
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that unscribe a listener from this class
     * Input:        Listener to be unscribed
     * Return:       None
     * Precondition: None
     */
    @Override
    public void unscribe(Object listener) throws Exception {
        int i;
        if (listener instanceof Listener) {
            for (i = 0; i < 26; i++) {
                if (this.isInserted(i)) {
                    this.listeners.remove((Listener) listener);
                    this.getNode(i).unscribe(listener); //Unscribe recursion
                }
            }
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that send a message to all connected listeners
     * Input:        Message to be sent
     * Return:       None
     * Precondition: None
     */
    @Override
    public void speak(String msg) {
        for (Listener l : this.listeners) {
            l.listen(msg);
        }
    }
}
