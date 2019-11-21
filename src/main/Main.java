package main;

import structure.TrieRoot;
import window.Window;

public class Main {
    public static void main(String[] args) throws Exception{
        TrieRoot t = new TrieRoot();
        Window w = new Window();
        t.getTrie().subscribe(w);
        t.getTrie().startWordSearch();
        w.printAAAAA();
    }
}
