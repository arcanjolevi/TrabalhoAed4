package structure;

//Structure that represent's a Node from trie tree
public class Node {
    private Node alphabet[]; //positional alphabet vector
    private boolean endOfWord[]; //positional vector that indicates if it's end of word
    
    //Node constructor
    public Node(){
        int i;
        this.alphabet  = new Node[26];
        this.endOfWord = new boolean[26];
        for(i=0;i<26;i++){
            this.alphabet[i]  = null;
            this.endOfWord[i] = false;
        }   
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
    public boolean isEndOfWord(Character letter){
        if(letter >= 'a' && letter <= 'z'){
            return this.endOfWord[this.getIndex(letter)];  
        }
        return false;
    }

    //EndOfWord setter
    public void setEndOfWord(boolean[] endOfWord) {
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
    
    /* Add a new letter to this node
     * Input:         Letter to be inserted and if it is endOfWord
     * Return:        None
     * Precondition:  The letter must not be in the node
     * Postcondition: The letter is added to the node
    */
    public void add(Character letter, boolean endOfWord){
        Node newNode = new Node();
        this.alphabet[this.getIndex(letter)]  = newNode;
        this.endOfWord[this.getIndex(letter)] = endOfWord;
    }
    
    /* Remove a letter from this node
     * Input:         Letter to be deleted
     * Return:        None
     * Precondition:  None
     * Postcondition: The letter is deleted from this node
    */
    public void delete(Character letter){
        this.alphabet[this.getIndex(letter)]  = null;
        this.endOfWord[this.getIndex(letter)] = false;
    }
}
