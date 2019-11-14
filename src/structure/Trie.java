package structure;

//Class that represents a Trie tree
public class Trie{
    private boolean endOfWord;
    private Trie alphabet[];
    
    //Trie constructor
    public Trie(){
        this.alphabet  = new Trie[26];
        this.endOfWord = false;
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
                this.alphabet[this.getIndex(letterToInsert)] = new Trie();
                this.getNode(letterToInsert).insert(text.substring(1, text.length())); //Recursion
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
    private void print(String prefix){
        for(int i=0;i<26;i++){
            if(this.isInserted(i) == true){
                prefix += this.getCharacter(i); //Add one more letter to the word to be printed
                if(this.getNode(i).endOfWord == true){
                    System.out.println(prefix);
                }
                this.getNode(i).print(prefix); //Recursion
                prefix = prefix.substring(0, prefix.length()-1); //clean the already printed word
            }
        }
    }
    
    /* Method used to initialize the print function
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    public void print(){
        this.print("");
    }
    
}
