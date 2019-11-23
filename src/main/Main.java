package main;

import structure.*;
import window.Window;
import file.File;

public class Main {
    public static void main(String[] args) throws Exception{
        File f = new File();
        TrieRoot t = new TrieRoot();
        Window w = new Window();
        
        w.subscribe(t);           //The root listen to the window
        t.subscribe(w);           //The window listen the root and all Nodes
    }
}
