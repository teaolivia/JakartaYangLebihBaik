/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

/**
 *
 * @author theaolivia
 */


import twitter4j.*;
import twitter4j.Query;


public class Bondol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // make requests to Twitter
        Twitter twitter = TwitterFactory.getSingleton();
        
        Query query;
        query = new Query("source:twitter4j yusukey");
        
        QueryResult result = twitter.search(query);
        
        result.getTweets().stream().forEach((status) -> {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        });
    }
    
}
