/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    protected static PrintWriter out;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // get Twitter posts
//        Tweets tw = new Tweets();
//        ArrayList<String> tweets;
//        tweets = tw.getTweets(posts);
//        twit = tweets.get(index);
        
        Tweets.bearerToken = Tweets.requestBearerToken("https://api.twitter.com/oauth2/token"); 
        String query = "q=ahok+exclude%3Alinks+-filter:media+exclude%3Areplies+exclude%3Aretweets&count=25&lang=id";
        String endpoint = Tweets.searchUrl+query;
        for (int i=0; i<; i++) {
            System.out.println(Tweets.fetchTweets(endpoint).get(i) + "\n");
            out = new PrintWriter(new FileWriter("/Users/theaolivia/GitHub/JakartaYangLebihBaik/Bondol/resource/input/input_ahok.txt", true));
            //out.println((i+1)+")http://twitter.com/"+Tweets.getString("from_user")+" at "+Tweets.getString("created_at"));
            out.println(Tweets.fetchTweets(endpoint).get(i) + "\n");

            out.close();
        }

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
