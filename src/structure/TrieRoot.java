package structure;

import generic.*;
import java.util.ArrayList;
import file.File;

//Class that represent the trie root
public class TrieRoot implements Listener, Speaker {

    private Trie trie;
    private File file;
    private ArrayList<Listener> listeners;

    //TrieRoot constructor
    public TrieRoot() {
        this.trie = new Trie();
        this.file = new File();
        this.listeners = new ArrayList<Listener>();
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
     * Input:        Word to be removed
     * Return:       None
     * Precondition: None
     */
    public void remove(String Text) {
        this.trie.stopWords(Text);
    }

    /* Method that read and load a new stopWords file to the trie
     * Input:        Path to the file to be read
     * Return:       None
     * Precondition: None
    */
    public void readNewStopWordsFile(String pathToFile) {
        try {
            this.trie = this.file.loadStopWordsFile(pathToFile, this).getTrie();
            for (Listener l : this.listeners) {
                this.trie.subscribe(l);
            }
            this.speak("newStopWordsFileSuccessfullyRead,");
        } catch (Exception ex) {
            this.speak("errorOnReadingNewStopWordsFile,");
        }
    }
    
    /* Method that read and load a new words file to the trie
     * Input:        Path to the file to be read
     * Return:       None
     * Precondition: None
    */
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

    /* Method that listen to a message from a speaker
     * Input:        Message listen
     * Return:       None
     * Precondition: None
    */
    @Override
    public void listen(String msg) {
        String aux[] = msg.split(",");

        if (aux[0].compareTo("buttonReadNewWordsFileClicked") == 0) {
            this.readNewWordsFile(aux[1]);
        }

        else if (aux[0].compareTo("buttonReadNewStopWordsFileClicked") == 0) {
            this.readNewStopWordsFile(aux[1]);
        }

        else if (aux[0].compareTo("buttonPrintDictionaryClicked") == 0) {
            this.trie.startWordSearch();
            this.speak("dictionaryPrintEnded,");
        }

        else if (aux[0].compareTo("buttonConsultWordClicked") == 0) {
            if (aux[1].compareTo("NULL") == 0) {
                this.speak("derivatedWordPrintEnded,Não existem paralavras similares");
            } else {
                this.trie.getSimilarWords(aux[1]);
            }
        }
        
        else if(aux[0].compareTo("buttonConsultSimilarWordsClicked") == 0){
            this.trie.consultSimilar(aux[1], Integer.parseInt(aux[2]), "", "newSimilarWord,");
            this.speak("similarWordPrintEnded,");
        }

    }

    /* Method that speak a message to all connected listeners
     * Input:        Message to speak
     * Return:       None
     * Precondition: None
    */
    @Override
    public void speak(String msg) {
        for (Listener l : this.listeners) {
            l.listen(msg);
        }
    }
}
