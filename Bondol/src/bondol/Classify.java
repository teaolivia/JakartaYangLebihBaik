/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bondol;

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
/**
 *
 * @author theaolivia
 */
public class Classify {
    
    public boolean classifyWords(String words) throws FileNotFoundException {
        Scanner scpos = new Scanner(new File("./resource/positif.txt"));
        Scanner scneg = new Scanner(new File("./resource/negatif.txt"));
        scpos.useDelimiter("");
        scneg.useDelimiter("");
        while (scpos.hasNext()) {
            String sp = scpos.next();
            if (sp.trim().isEmpty()) {
            continue;
            }
            System.out.println(sp);
        }
            scpos.close();
        while (scneg.hasNext()) {
            String sn = scneg.next();
            if (sn.trim().isEmpty()) {
            continue;
            }
            System.out.println(sn);
        }
            scneg.close();
//        File neg = new File("./resource/negatif.txt"); 
//        if () {
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
}
