/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import java.io.*;
import weka.core.*;
/**
 *
 * @author theaolivia
 */
public class Text2Arff {
    public static String querytxt;
    
    public Instances createDataset(String query) throws Exception {

        FastVector atts = new FastVector(2);
        atts.addElement(new Attribute("tweets", (FastVector) null));
        atts.addElement(new Attribute("energy", (FastVector) null));
        Instances data = new Instances(query, atts, 0);

        File dir = new File(query);
        String[] files = dir.list();
        for (String file : files) {
            if (file.endsWith(".txt")) {
                try {
                    double[] newInst = new double[2];
                    newInst[0] = (double) data.attribute(0).addStringValue(file);
                    File txt = new File("/Users/theaolivia/GitHub/JakartaYangLebihBaik/Bondol/src/bondol" + File.separator + file);
                    InputStreamReader is;
                    is = new InputStreamReader(new FileInputStream(txt));
                    StringBuilder txtStr = new StringBuilder();
                    int c;
                    while ((c = is.read()) != -1) {
                        txtStr.append((char)c);
                    }         newInst[1] = (double)data.attribute(1).addStringValue(txtStr.toString());
                    data.add(new Instance(1.0, newInst));
                }catch (Exception e) {
                    //System.err.println("failed to convert file: " + directoryPath + File.separator + files[i]);
        }   }
        }
        return data;
    }
    
    public static void main(String[] args) throws IOException {
        // retrieve Tweets with queries
        // query = "ahok"
        Tweets.bearerToken = Tweets.requestBearerToken("https://api.twitter.com/oauth2/token"); 
        String query = "q=ahok+exclude%3Alinks+-filter:media+exclude%3Areplies+exclude%3Aretweets&count=25&lang=id";
        String endpoint = Tweets.searchUrl+query;
        for (int i=0; i<25; i++) {
            querytxt = Tweets.fetchTweets(endpoint).get(i) + "\n";
            
            if (args.length == 1) {
                Text2Arff tdta = new Text2Arff();
                try {
                    Instances dataset = tdta.createDataset(querytxt);
                    System.out.println(dataset);
                  } catch (Exception e) {
                    System.err.println(e.getMessage());
                  }
            } else {
              System.out.println("Usage: java Text2Arff <directory name>");
            }
        }
        
        
    }  
}
