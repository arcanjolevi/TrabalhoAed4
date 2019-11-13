package structure;

import structure.Node;

//Class that represents a Trie tree
public class Trie{
    private Node node;
    
    //Trie constructor
    public Trie(){
        this.node = null;
    }

    //Node getter
    public Node getNode() {
        return node;
    }

    //Node setter
    public void setNode(Node node) {
        this.node = node;
    }
    
    /*                          TESTE                   */
    private Node insert(String text, Node node){
        if(node == null){
            node = new Node();
            if(text.length() == 1){
                node.add(text.toCharArray()[0], true);
            }else if(text.length() >= 2){
                node.add(text.toCharArray()[0], false);
                insert(text.substring(1, text.length()),node.getNext(text.toCharArray()[0]));
            }
        }else{
            
        }
        return node;
    }
    
    /* Insert a word on the trie 
     * Input:        Word to be inserted
     * Return:       None
     * Precondition: None
    */
    public void insert(String text){
        if(this.node == null){
            this.node = new Node();
            if(text.length() == 1){
                this.node.add(text.toCharArray()[0], true);
            }else if(text.length() >= 2){
                this.node.add(text.toCharArray()[0], false);
                this.insert(text.substring(1, text.length()),this.node.getNext(text.toCharArray()[0]));
            }
        }else{
            
        }
    }
    
    
    /*                          TESTE                   */
    public void print(){
        int i;
        for(i=0;i<26;i++){
            if(this.node.isActive( (char) ('a' + i))){
                System.out.println(((char) ('a'+ i) )+ "\n");
            }
        }
    }
}
