package main;

import structure.TrieRoot;
import window.Window;

public class Main {
    public static void main(String[] args){
        try {
            TrieRoot t = new TrieRoot(); //Initialize the trie
            Window w = new Window();    //Initialize the window
            w.subscribe(t);           //The root listen to the window
            t.subscribe(w);           //The window listen the root and all Nodes
        } catch (Exception ex) {
            System.out.println("Impossível inicializar o programa");
        }
    }
}
