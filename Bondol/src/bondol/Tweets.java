/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author atia
 */
public class Tweets {
    private static final String consumerKey = "a6V8ZsVRNvgbeOHUiCDFCMUCT";
    private static final String consumerSecret = "n5lbNQuioFk1zj9qRU01INzB9jEKS9BJggSzTqOj1G9gCG0Dav";
    private static final String searchUrl = "https://api.twitter.com/1.1/search/tweets.json?";
    private static String bearerToken = "";
    
    private static String encodeKeys(String consumerKey, String consumerSecret) {
        try {
            String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
            String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");
            String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
            String encodedBytes = Base64.getEncoder().encodeToString(fullKey.getBytes());
            return encodedBytes;
        } catch (UnsupportedEncodingException e) {
            System.out.println("SW 1");
            return new String();
        }
    }
    
    private static String requestBearerToken(String endpointUrl) throws IOException {
        HttpsURLConnection conn = null;
        String encodedCredentials = encodeKeys(consumerKey, consumerSecret);
        
        try {
            URL url = new URL(endpointUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Host","api.twitter.com");
            conn.setRequestProperty("User-Agent", "JakartaYangLebihBaik");
            conn.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Content-Length", "29");
            conn.setUseCaches(false);
            
            writeRequest(conn, "grant_type=client_credentials");
            
            JSONObject obj = (JSONObject)JSONValue.parse(readResponse(conn));
            
            if (obj != null) {
                String tokenType = (String)obj.get("token_type");
                String accessToken = (String)obj.get("access_token");
                
                return tokenType.equals("bearer") && (accessToken != null) ? accessToken : "";
            }
            return new String();
        } catch (MalformedURLException e) {
            throw new IOException("Invalid endpoint URL", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    
    private static boolean writeRequest(HttpsURLConnection conn, String body) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(body);
            bw.flush();
            bw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    private static String readResponse(HttpsURLConnection conn) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator"));
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println(e);
            return new String();
        }
    }
    
    private static ArrayList<String> fetchTweets(String endpointUrl) throws IOException {
        ArrayList<String> tweets = new ArrayList<String>();
        HttpsURLConnection conn = null;
        
        try {
            URL url = new URL(endpointUrl);
            conn = (HttpsURLConnection) url.openConnection();       
            conn.setDoOutput(true);
            conn.setDoInput(true); 
            conn.setRequestMethod("GET"); 
            conn.setRequestProperty("Host", "api.twitter.com");
            conn.setRequestProperty("User-Agent", "Your Program Name");
            conn.setRequestProperty("Authorization", "Bearer " + bearerToken);
            conn.setUseCaches(false);
            
            JSONObject obj = (JSONObject)JSONValue.parse(readResponse(conn));
            if (obj != null) {
                JSONArray statuses = (JSONArray)obj.get("statuses");
                for (Object status : statuses) {
                    String tweet = ((JSONObject) status).get("text").toString();
                    tweets.add(tweet);
                }
                return tweets;
            }
            return new ArrayList<String>();
        } catch (MalformedURLException e) {
            throw new IOException("Invalid endpoint URL", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    
    public static ArrayList<String> getTweets(String urlEncodedQuery) throws IOException {
        bearerToken = requestBearerToken("https://api.twitter.com/oauth2/token"); 
        String endpoint = searchUrl + urlEncodedQuery;
        return fetchTweets(endpoint);
    }
}
