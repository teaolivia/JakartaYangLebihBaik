/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import IndonesianNLP.IndonesianPOSTagger;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianStemmer;
import IndonesianNLP.IndonesianSentenceTokenizer;
import java.util.ArrayList;
/**
 *
 * @author atia
 */
public class TextProcessing {

    /**
     *
     * @param args
     */
    
    public static ArrayList<String[]> posTagSentence(String sentence) {
        ArrayList<String[]> posTag = IndonesianPOSTagger.doPOSTag(sentence);
        for(int i = 0; i < posTag.size(); i++){
            System.out.println(posTag.get(i)[0] + " - " + posTag.get(i)[1]);
        }
        return posTag;
    }
    
    public static String lemmatizeSentence(String sentence) {
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        return formalizer.normalizeSentence(sentence);
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
}
