/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author kiper
 */
public class Trie {

    private Node root;
    
    public Trie() {
        root = new Node(' ');
    }
    
    public void insert(String s) {
        Node current = root;
        if (s.length() == 0) //For an empty character
        {
            current.marker = true;
        }
        for (int i = 0; i < s.length(); i++) {
            Node child = current.subNode(s.charAt(i));
            if (child != null) {
                current = child;
            } else {
                current.child.add(new Node(s.charAt(i)));
                current = current.subNode(s.charAt(i));
            }
            // Set marker to indicate end of the word
            if (i == s.length() - 1) {
                current.marker = true;
            }
        }
    }
    
    public boolean search(String s) {
        Node current = root;
        while (current != null) {
            for (int i = 0; i < s.length(); i++) {
                if (current.subNode(s.charAt(i)) == null) {
                    return false;
                } else {
                    current = current.subNode(s.charAt(i));
                }
            }
            /* 
             * This means that a string exists, but make sure its
             * a word by checking its 'marker' flag
             */
            if (current.marker == true) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        Trie trie = new Trie();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Podaj nazwę pliku do wczytania:");
        String pathin = br.readLine();
        System.out.println("Podaj nazwę pliku do zapisu:");
        String pathout = br.readLine();
        
        FileReader fr = new FileReader(pathin);
        Scanner scan = new Scanner(fr);
        
        while(scan.hasNext()) {
            trie.insert(scan.next());
        }
        
        fr.close();
        
        XStream xstream = new XStream(new DomDriver());
        FileWriter fw = new FileWriter(pathout);
        
        ObjectOutputStream out = xstream.createObjectOutputStream(fw);
        out.writeObject(trie);
        out.close();
        
//        fr = new FileReader("plik.txt");
        fr = new FileReader(pathout);
        ObjectInputStream in = xstream.createObjectInputStream(fr);
        Trie newTrie = (Trie) in.readObject();
        fr.close();
        
    }
}
