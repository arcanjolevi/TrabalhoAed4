package main;

import structure.*;
import window.Window;
import file.File;
import generic.Listener;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception{
        File f = new File();
        TrieRoot t = new TrieRoot();
        ArrayList<Listener> listeners = new ArrayList<Listener>();
        t.setTrie(f.loadWordsFile("/home/caio/dictionary"));
        listeners.add(t.getTrie());
        Window w = new Window();
        w.subscribe(t.getTrie());
        t.subscribe(w);
    }
}
