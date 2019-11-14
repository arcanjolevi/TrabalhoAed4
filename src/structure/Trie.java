package structure;

//Class that represents a Trie tree
public class Trie{
    private boolean endOfWord;
    private Trie alphabet[];
    
    //Trie constructor
    public Trie(){
        this.alphabet  = new Trie[26];
        this.endOfWord = false;
        this.initTrie();
    }
    
    private void initTrie(){
        int i;
        for(i=0;i<26;i++){
            this.alphabet[i]  = null;
        }
    }
    
    public Integer getIndex(Character letter){
        return (int) (letter - 'a');
    }
    
    public Character getCharacter(Integer index){
        return (char) ('a' + index);
    }

    //EndOfWord getter
    public boolean isEndOfWord() {
        return endOfWord;
    }

    //EndOfWord setter
    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    //Letter getter
    public Trie getLetter(Integer index) {
        return alphabet[index];
    }

    //Letter setter
    public void setLetter(Trie letter,Integer index) {
        this.alphabet[index] = letter;
    }
    
    //Letter overloaded setter
    public void setLetter(Trie letter,Character index) {
        this.alphabet[this.getIndex(index)] = letter;
    }
    
    /* Verify if a letter is or not in the vector
     * Input:        Letter to be checked
     * Return:       True if it's in or false otherwise
     * Precondition: The letter must be betwen a and z
    */
    public boolean isInserted(Character letter){
        return this.alphabet[this.getIndex(letter)] != null;
    }

        /* Verify if a letter is or not in the vector
     * Input:        Letter to be checked
     * Return:       True if it's in or false otherwise
     * Precondition: The letter must be betwen a and z
    */
    public boolean isInserted(Integer index){
        return this.alphabet[index] != null;
    }
    
    /* Insert a word in the trie
     * Input:        Word to be inserted
     * Return:       Node inserted
     * Precondition: The text must only contain letters betwen a and z or empty word
    */
    public void insert(String text){
        if(text.length() > 0){
            Character letterToInsert = text.toCharArray()[0];
            if(this.isInserted(letterToInsert)){
               this.alphabet[this.getIndex(letterToInsert)].insert(text.substring(1, text.length()));
            }else{
                this.alphabet[this.getIndex(letterToInsert)] = new Trie();
                this.alphabet[this.getIndex(letterToInsert)].insert(text.substring(1, text.length()));
            }
        }else{
            this.endOfWord = true;
        }
    }
    
    private void print(String prefix){
        int i;
        for(i=0;i<26;i++){
            if(this.isInserted(i)){
                prefix += this.getCharacter(i);
                if(this.alphabet[i].endOfWord == true){
                    System.out.println(prefix);
                }
                this.alphabet[i].print(prefix);
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
    }
    
    public void print(){
        this.print("");
    }
    
}
