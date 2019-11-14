package main;

import structure.Trie;
import file.File;

public class Main {
    public static void main(String[] args) throws Exception{
        File f = new File();
        Trie t;
        t = f.loadWordsFile("/home/caio/Desktop/lucasGay.txt");
        t.print();
    }
}
