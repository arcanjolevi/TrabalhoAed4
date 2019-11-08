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

}
