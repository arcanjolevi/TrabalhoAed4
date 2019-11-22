package main;

import structure.*;
import window.Window;
import file.File;

public class Main {
    public static void main(String[] args) throws Exception{
        File f = new File();
        TrieRoot t = new TrieRoot();
        Window w = new Window();
        t.setTrie(f.loadWordsFile("/home/caio/dictionary"));
        t.subscribe(w);
        t.getTrie().getSimilarWords("a");
        w.printDerivatedWords();
//        t.getTrie().startWordSearch(listeners);
//        w.printAAAAA();
    }
}
