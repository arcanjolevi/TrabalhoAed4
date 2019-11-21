package structure;

import generic.*;
import java.util.ArrayList;

//Class that represents a Trie tree
public class Trie implements Speaker{
    private boolean endOfWord;
    private Trie alphabet[];
    private ArrayList<Listener> listeners;
    
    //Trie constructor
    public Trie(){
        this.alphabet  = new Trie[26];
        this.endOfWord = false;
        this.listeners = new ArrayList<Listener>();
        this.initTrieVector();
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
    private void initTrieVector(){
        int i;
        for(i=0;i<26;i++){
            this.alphabet[i]  = null;
        }
    }
    
    /* Method that return the position of a letter in a vector with 26 positions
     * Input:        Letter to be calculated
     * Return:       Position in the vector
     * Precondition: The letter must be betwen a and z
    */
    public Integer getIndex(Character letter){
        return (int) (letter - 'a');
    }
    
    /* Method that return the value of a position in a vector with 26 positions
     * Input:        Index of the vector to be calculated
     * Return:       Character represented by the position
     * Precondition: The index must be betwen 0 and 25
    */
    public Character getCharacter(Integer index){
        return (char) ('a' + index);
    }
    
    /* Verify if a letter is or not in the vector
     * Input:        Letter to be checked
     * Return:       True if it's in or false otherwise
     * Precondition: The letter must be betwen a and z
    */
    public Boolean isInserted(Character letter){
        return this.getNode(letter) != null;
    }

        /* Verify if a letter is or not in the vector
     * Input:        Letter to be checked
     * Return:       True if it's in or false otherwise
     * Precondition: The letter must be betwen a and z
    */
    public Boolean isInserted(Integer index){
        return this.getNode(index) != null;
    }
    
    /* Insert a word in the trie
     * Input:        Word to be inserted
     * Return:       Node inserted
     * Precondition: The text must only contain letters betwen a and z or empty word
    */
    public void insert(String text){
        if(text.length() > 0){
            Character letterToInsert = text.toCharArray()[0];
            if(this.isInserted(letterToInsert) == true){
               this.getNode(letterToInsert).insert(text.substring(1, text.length())); //Recursion
            }else{
                try {
                    this.alphabet[this.getIndex(letterToInsert)] = new Trie();
                    this.subscribe(this.listeners, this.alphabet[this.getIndex(letterToInsert)]);
                    this.getNode(letterToInsert).insert(text.substring(1, text.length())); //Recursion
                } catch (Exception ex) {
                    this.speak("error," + ex.getMessage());
                }
            }
        }else{
            this.endOfWord = true;
        }
    }
    
    /* Method that print all the words on the trie in alphabetic order
     * Input:        String that contain the word to be printed
     * Return:       None
     * Precondition: None
    */
    private void printDictionary(String prefix){
        for(int i=0;i<26;i++){
            if(this.isInserted(i) == true){
                prefix += this.getCharacter(i); //Add one more letter to the word to be printed
                if(this.getNode(i).endOfWord == true){
                    //this.speak(prefix + "\n.");
                }
                this.getNode(i).printDictionary(prefix); //Recursion
                prefix = prefix.substring(0, prefix.length()-1); //clean the already printed word
            }
        }
    }
    
    /* Method used to initialize the print function
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    public void printDictionary(){
        this.printDictionary("");
    }

    /* Method that subscribe a listener to this class
     * Input:        Listener to be subscribed
     * Return:       None
     * Precondition: None
    */
    @Override
    public void subscribe(Object listener) throws Exception{
        if(listener instanceof Listener){
            this.listeners.add((Listener) listener);
        }else{
            throw new Exception("A classe não é um listener");
        }
    }
    
    /* Method that subscribe a ArrayList of listeners to a Trie node
     * Input:        ArrayList of listeners and Trie node
     * Return:       None
     * Precondition: None
    */
    private void subscribe(ArrayList<Listener> listeners, Trie t) throws Exception{
        for(Listener l:listeners){
            t.subscribe(l);
        }
    }

    /* Method that unscribe a listener from this class
     * Input:        Listener to be unscribed
     * Return:       None
     * Precondition: None
    */
    @Override
    public void unscribe(Object listener) throws Exception{
        if(listener instanceof Listener){
            this.listeners.remove((Listener) listener);
        }else{
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
        for(Listener l:this.listeners){
            l.listen(msg);
        }
    }
}
