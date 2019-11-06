package structure;

//Structure that represent's a Node from trie tree
public class Node {
    private Node alphabet[]; //positional alphabet vector
    private boolean endOfWord; //indicates if it's end of word
    
    //Node constructor
    public Node(){
        int i;
        this.alphabet  = new Node[26];
        this.endOfWord = false;
        for(i=0;i<26;i++){
            this.alphabet[i] = null;
        }   
    }
    
    //Overloaded node constructor
    public Node(Node alphabet[]){
        this.alphabet  = alphabet;
        this.endOfWord = false;  
    }

    //Alphabet getter
    public Node[] getAlphabet() {
        return alphabet;
    }

    //Alphabet setter
    public void setAlphabet(Node[] alphabet) {
        this.alphabet = alphabet;
    }

    //EndOfWord getter
    public boolean isEndOfWord() {
        return endOfWord;
    }

    //EndOfWord setter
    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
    
    /* Calculate the position of an alphabet letter in a vector with 26 positions
     * Input:         Letter to be calculated
     * Return:        Position that the letter take in the vector
     * Precondition:  The letter must be betwen (a-z)
     * Postcondition: None
    */
    private Integer getIndex(Character letter){
        return letter - 'a';
    }
    
    /* Calculate if this node contain the index of the alphabet vector activated
     * Input:         Index of the vector
     * Return:        True if the value is activated or false otherwise
     * Precondition:  None
     * Postcondition: None
    */
    public boolean isActive(Character letter){
        if(letter >= 'a' && letter <= 'z'){
            return this.alphabet[this.getIndex(letter)] != null;
        }
        return false;
    }
    
    /* Return the next node from a letter
     * Input:         Letter
     * Return:        Next node or null if it doesn't exist
     * Precondition:  None
     * Postcondition: None
    */
    public Node getNext(Character letter){
        if(this.isActive(letter)){
            return this.alphabet[this.getIndex(letter)];
        }
        return null;
    }
}
