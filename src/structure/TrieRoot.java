package structure;

public class TrieRoot {
    private Trie trie;
    
    //TrieRoot constructor
    public TrieRoot(){
        this.trie = new Trie();
    }
    
    //Trie getter
    public Trie getTrie(){
        return this.trie;
    }
    
    //Trie setter
    public void setTrie(Trie trie){
        this.trie = trie;
    }
    
    /* Insert a word in the trie
     * Input:        Text to be inserted
     * Return:       None
     * Precondition: None
    */
    public void insert(String text) throws Exception{
        if(text.toLowerCase().compareTo(text) != 0) throw new Exception("Palavra inválida");
        this.trie.insert(text);
    }
}
