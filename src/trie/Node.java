/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author kiper
 */
public class Node {
    
    char content;
    boolean marker;
    Collection<Node> child;
    
    public Node(char c) {
        content = c;
        marker = false;
        child = new LinkedList<>();
    }
    
    public Node subNode(char c) {
        if(child != null) {
            for(Node eachNode:child) {
                if(eachNode.content == c) {
                    return eachNode;
                }
            }
        }
        
        return null;
    }
    
}
