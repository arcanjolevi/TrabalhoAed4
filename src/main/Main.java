package main;

import structure.*;
import window.Window;

public class Main {
    public static void main(String[] args) throws Exception{
        TrieRoot t = new TrieRoot();
        Window w = new Window();
        w.subscribe(t);           //The root listen to the window
        t.subscribe(w);           //The window listen the root and all Nodes
    }
}
