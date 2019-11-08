package structure;

import structure.Node;

//Class that represents a Trie tree
public class Trie{
    private Node node;
    
    public Trie(){
        this.node = null;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
    
}
