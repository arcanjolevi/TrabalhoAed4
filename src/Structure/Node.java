package Structure;

//Class that represents a Trie tree Node
public class Node {
    private Node left;          //Node with letters that come before info
    private Node middle;        //Node with letters equals to info
    private Node right;         //Node with letters that come after info
    private Character info;     //Character that keep the info
    private boolean wordEnd;    //Variable that says whether or not it is a end of word

    //Node constructor
    public Node(Node left, Node middle, Node right, Character info, boolean wordEnd) {
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.info = info;
        this.wordEnd = wordEnd;
    }

    //Left getter
    public Node getLeft() {
        return left;
    }

    //Left setter
    public void setLeft(Node left) {
        this.left = left;
    }

    //Middle getter
    public Node getMiddle() {
        return middle;
    }

    //Middle setter
    public void setMiddle(Node middle) {
        this.middle = middle;
    }

    //Right getter
    public Node getRight() {
        return right;
    }

    //Right setter
    public void setRight(Node right) {
        this.right = right;
    }

    //Info getter
    public Character getInfo() {
        return info;
    }

    //Info setter
    public void setInfo(Character info) {
        this.info = info;
    }

    //WordEnd getter
    public boolean isWordEnd() {
        return wordEnd;
    }

    //WordEnd setter
    public void setWordEnd(boolean wordEnd) {
        this.wordEnd = wordEnd;
    }
}
