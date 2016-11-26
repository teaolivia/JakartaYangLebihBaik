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
    public static void main (String [] args) {
        System.out.println("Demo NLP Tools utk bahasa Indonesia");
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        System.out.println("*************************");
        System.out.println("Formalisasi Kata");
        String sentence = "kata2nya 4ku donk loecoe bangedh gt .";
        System.out.println(sentence);
        System.out.println(formalizer.normalizeSentence(sentence));
        
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
        
        System.out.println("*************************");
        sentence = "Pak SBY bertemu dengan Bu Mega .";
        
        /* MASIH ERROR */
        IndonesianPOSTagger ipt = new IndonesianPOSTagger();
        ArrayList posTag = ipt.doPOSTag(sentence);
        posTag.stream().forEach((posTag1) -> {
            System.out.println(posTag1 + " - " + posTag1);
        });
        
        System.out.println("*************************");
        IndonesianSentenceTokenizer ist = new IndonesianSentenceTokenizer();
        ArrayList tokens = ist.tokenizeSentenceWithCompositeWords("Alexander Graham Bell dilahirkan di Edinburgh , Skotlandia , Britania Raya pada 3 Maret 1847 .");
        tokens.stream().forEach((token) -> {
            System.out.println(token);
        });
    }
}
