package structure;

import generic.*;
import java.util.ArrayList;

//Class that represents a Trie tree
public class Trie implements Speaker,Listener{
    private boolean endOfWord;
    private Trie alphabet[];
    private ArrayList<Listener> listeners;
    
    //Trie constructor
    public Trie(){
        this.alphabet   = new Trie[26];
        this.endOfWord  = false;
        this.listeners  = new ArrayList<Listener>();
        this.initTrieVector();
    }
    
    //Listeners getter
    public ArrayList<Listener> getListeners(){
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
                    this.getNode(letterToInsert).insert(text.substring(1, text.length())); //Recursion
                } catch (Exception ex) {
                    this.speak("error," + ex.getMessage());
                }
            }
        }else{
            this.endOfWord = true;
        }
    }
    
    /* Method that get all the words on the trie in alphabetic order
     * Input:        String that contain the prefixed word formed until the moment
     * Return:       None
     * Precondition: None
    */
    public void getDictionary(String prefix, String speakMsg){
        for(int i=0;i<26;i++){
            if(this.isInserted(i) == true){
                prefix += this.getCharacter(i); //Add one more letter to the word to be printed
                if(this.getNode(i).endOfWord == true){
                    this.speak(speakMsg + prefix + "\n");
                }
                this.getNode(i).getDictionary(prefix,speakMsg); //Recursion
                prefix = prefix.substring(0, prefix.length()-1); //clean the already printed word
            }
        }
    }
    
    /* Method used to initialize the word find function
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    public void startWordSearch(){
        this.getDictionary("","newWord,");
        this.speak("dictionaryEnd,");
    }
    
    /* Method that find and speak out all similar words by a given subWord
     * Input:        Subword to be find and Listeners list
     * Return:       None
     * Precondition: None
    */
    public void getSimilarWords(String subWord){
        Trie aux = this;
        int i;
        for(i=0;aux != null && i<subWord.length();i++)
            aux = aux.getNode(subWord.toCharArray()[i]);
        if(aux == null){
            this.speak("derivatedWordEnd,Não existem paralavras similares");   
        }else{
            aux.getDictionary(subWord,"newDerivatedWord,");
            this.speak("derivatedWordEnd,");   
        }
    }

    /* Method that subscribe a listener to all trie nodes
     * Input:        Listener to be subscribed
     * Return:       None
     * Precondition: None
    */
    @Override
    public void subscribe(Object listener) throws Exception{
        int i;
        if(listener instanceof Listener){
            for(i=0;i<26;i++){
                if(this.isInserted(i)){
                    if(!this.listeners.contains((Listener) listener))
                        this.listeners.add((Listener) listener);
                    this.getNode(i).subscribe(listener); //Subscribe recursion
                }
            }
        }else{
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that unscribe a listener from this class
     * Input:        Listener to be unscribed
     * Return:       None
     * Precondition: None
    */
    @Override
    public void unscribe(Object listener) throws Exception{
        int i;
        if(listener instanceof Listener){
            for(i=0;i<26;i++){
                if(this.isInserted(i)){
                    this.listeners.remove((Listener) listener);
                    this.getNode(i).unscribe(listener); //Unscribe recursion
                }
            }
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

    /* Method that listen a message from a speaker
     * Input:        Message listen
     * Return:       None
     * Precondition: None
    */
    @Override
    public void listen(String msg) {
        String aux[] = msg.split(",");
        
        if(aux[0].compareTo("printButtonPressed") == 0){
                this.startWordSearch();
        }
        
        if(aux[0].compareTo("consultButtonPressed") == 0){
            if(aux[1].compareTo("NULL") == 0){
                this.speak("derivatedWordEnd,Não existem paralavras similares");
            }else{
                this.getSimilarWords(aux[1]);
            }
        }
    }
}
