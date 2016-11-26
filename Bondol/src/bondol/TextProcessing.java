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
    public ArrayList<String[]> posTagSentence(String sentence) {
        IndonesianPOSTagger ipt = new IndonesianPOSTagger();
        posTag = ipt.doPOSTag(sentence);
        posTag.stream().forEach((posTag1) -> {
            System.out.println(posTag1[0] + " - " + posTag1[1]);
        });
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
