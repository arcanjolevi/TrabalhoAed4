package structure;

import generic.*;
import java.util.ArrayList;
import file.File;

public class TrieRoot implements Listener, Speaker {
    private Trie trie;
    private File file;
    private ArrayList<Listener> listeners;

    //TrieRoot constructor
    public TrieRoot() {
        this.trie = new Trie();
        this.listeners = new ArrayList<Listener>();
        this.file = new File();
    }

    //Trie getter
    public Trie getTrie() {
        return this.trie;
    }

    //Trie setter
    public void setTrie(Trie trie) throws Exception {
        this.trie = trie;
    }

    /* Insert a word in the trie
     * Input:        Text to be inserted
     * Return:       None
     * Precondition: None
     */
    public void insert(String text) throws Exception {
        if (text.toLowerCase().compareTo(text) != 0) {
            throw new Exception("Palavra inválida");
        }
        this.trie.insert(text);
    }
    
    /* remove a word in the trie
     * Input:        Text to be removed
     * Return:       None
     * Precondition: None
     */
    public void remove(String Text) throws Exception {
    	this.trie.stopWords(Text);
    }

    public void readNewWordsFile(String pathToFile) {
        try {
            this.trie = this.file.loadWordsFile(pathToFile); //Load new trie
            for (Listener l : this.listeners) {
                this.trie.subscribe(l);
            }
            this.speak("newWordsFileSuccessfullyRead,");
        } catch (Exception e) {
            this.speak("errorOnReadingNewWordsFile,");
        }
    }

    /* Method that subscribe a listener to this class
     * Input:        Listener to be subscribed
     * Return:       None
     * Precondition: Argument must be a Listener
     */
    @Override
    public void subscribe(Object listener) throws Exception {
        if (listener instanceof Listener) {
            this.trie.subscribe(listener);
            this.listeners.add((Listener) listener);
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that unscribe a listener from this class
     * Input:        Listener to be unscribed
     * Return:       None
     * Precondition: Argument must be a Listener
     */
    @Override
    public void unscribe(Object listener) throws Exception {
        if (listener instanceof Listener) {
            this.trie.unscribe((Listener) listener);
            this.listeners.remove((Listener) listener);
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    @Override
    public void listen(String msg) {
        String aux[] = msg.split(",");

        if (aux[0].compareTo("buttonReadNewWordsFile") == 0) {
            this.readNewWordsFile(aux[1]);
        }

        if (aux[0].compareTo("printButtonPressed") == 0) {
            this.trie.startWordSearch();
        }

        if (aux[0].compareTo("consultButtonPressed") == 0) {
            if (aux[1].compareTo("NULL") == 0) {
                this.speak("derivatedWordEnd,Não existem paralavras similares");
            } else {
                this.trie.getSimilarWords(aux[1]);
            }
        }
    }

    @Override
    public void speak(String msg) {
        for (Listener l : this.listeners) {
            l.listen(msg);
        }
    }
}
