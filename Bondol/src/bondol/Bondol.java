/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author theaolivia
 */

public class Bondol {

    protected static String stem;
    protected static String lemma;
    protected static ArrayList<String[]> pt;
    protected static ArrayList token;
    protected static int index;
    protected static String posts;
    protected static String twit;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // get Twitter posts
        Tweets tw = new Tweets();
        ArrayList<String> tweets;
        tweets = tw.getTweets(posts);
        twit = tweets.get(index);

        TextProcessing tp = new TextProcessing();
        
        // lemmatize words
        lemma = TextProcessing.lemmatizeSentence(twit);
        System.out.println(lemma);
        
        // tokenize words
        token = TextProcessing.tokenizeSentence(lemma);
        System.out.println(token);
        
        
        stem = TextProcessing.stemWord(lemma);
        System.out.println(stem); 
        pt = tp.posTagSentence(lemma);
        System.out.println(pt);        
        // implement POS Tagging
        // pick adjectives
        // assign rating
    }
    
}
