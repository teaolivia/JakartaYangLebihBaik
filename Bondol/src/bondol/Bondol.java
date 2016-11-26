/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import java.io.IOException;

/**
 *
 * @author theaolivia
 */

public class Bondol {
    protected static String posts;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // get Twitter posts
        Tweets.getTweets(posts);
        // tokenize words
        // lemmatize words
        // implement POS Tagging
        // pick adjectives
        // assign rating
    }
    
}
