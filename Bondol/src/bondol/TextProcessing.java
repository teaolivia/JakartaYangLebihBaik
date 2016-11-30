/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import IndonesianNLP.IndonesianNETagger;
import IndonesianNLP.IndonesianPOSTagger;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianStemmer;
import IndonesianNLP.IndonesianSentenceTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
/**
 *
 * @author atia
 */
public class TextProcessing {
    private static final String kategloUrl = "http://kateglo.com/api.php?format=json&phrase=";
    protected ArrayList<String[]> posTag;
    /**
     *
     * @param args
     */
    
    /**
     *
     * @param sentence
     * @return 
     */
    
    public static String lemmatizeSentence(String sentence) {
        // formalization
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        sentence = formalizer.normalizeSentence(sentence);
        // stopwords removal
        formalizer.initStopword();
        return formalizer.deleteStopword(sentence);
    }
    
    public static ArrayList tokenizeSentence(String sentence) {
        IndonesianSentenceTokenizer ist = new IndonesianSentenceTokenizer();
        ArrayList tokens = ist.tokenizeSentence(sentence);
        return tokens;
    }
    
    public static String stemWord(String word) {
        IndonesianStemmer stemmer = new IndonesianStemmer();
        return stemmer.stem(word);
        
        /*
        System.out.println("*************************");
        System.out.println("Indonesian STEMMER");
        IndonesianStemmer stemmer = new IndonesianStemmer();
        String word = "memperbantukannya";
        System.out.println("Kata masukan: " + word);
        System.out.println("Kata dasar: " + stemmer.stem(word));
        for(int i = 0; i < stemmer.derivationalprefix.size(); i++){
            System.out.println("Derivational Prefix: " + stemmer.derivationalprefix.get(i));
        }
        System.out.println("Particle Suffix: " + stemmer.particlesuffix);
        System.out.println("Possessive Pronoun Suffix : " + stemmer.possessivepronounsuffix);
        System.out.println("Derivational Suffix : " + stemmer.derivationalsuffix);
        */
    }
    
    public static String NETagSentence(String sentence) {
        IndonesianNETagger inner = new IndonesianNETagger();
        inner.NETagFile("inputfile","outputfile"); 
        ArrayList<String[]> NETag = inner.NETagLine(sentence); 
        NETag.stream().forEach((NETag1) -> {
            System.out.println(NETag1[0] + " - " + NETag1[2]);
        });
        return sentence;
    }    
    
    public static String removeHashtags(String sentence) {
        Pattern p = Pattern.compile("#\\S*");
        Matcher m = p.matcher(sentence);
        while (m.find()) {
            String s = sentence.subSequence(m.start(),m.end()).toString();
            sentence = sentence.replaceAll(s, "");
            m = p.matcher(sentence);
        }
        return sentence;
    }
    
    public static String getPOSTag(String token) throws IOException {        
        HttpURLConnection conn = null;
        try {
            String endpoint = kategloUrl + token;
            URL url = new URL(endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "JakartaYangLebihBaik");
            JSONObject obj = (JSONObject)JSONValue.parse(readResponse(conn));
            JSONObject kateglo = (JSONObject) obj.get("kateglo");
            return kateglo.get("lex_class").toString();
        } catch (MalformedURLException e) {
            throw new IOException("Invalid endpoint URL", e);
        } finally {
            conn.disconnect();
        }
    }
    
    private static String readResponse(HttpURLConnection conn) {
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
}
