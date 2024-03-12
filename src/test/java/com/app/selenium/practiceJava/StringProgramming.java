package com.app.selenium.practiceJava;

import java.util.Arrays;
import java.util.Locale;

public class StringProgramming {

    public boolean verifyAnagrams(String str1, String str2){
        //find length of string first
        int l1 = str1.length();
        int l2 = str2.length();

        if(l1 == l2) {
            char[] ch1 = str1.toLowerCase().toCharArray();
            char[] ch2 = str2.toLowerCase().toCharArray();
            //sort both the arrays
            Arrays.sort(ch1);
            Arrays.sort(ch2);
            return Arrays.equals(ch1, ch2);
        }
        return false;
    }

    public boolean verifyAnagrams_Using_Arrays(String str1, String str2){
        //Remove white spaces in Strings
        str1 = str1.replaceAll("\\s","");
        str2 = str2.replaceAll("\\s","");
        if( str1.length() == str2.length() ){
            char[] ch1 = str1.toLowerCase().toCharArray();
            char[] ch2 = str2.toLowerCase().toCharArray();
            Arrays.sort(ch1);
            Arrays.sort(ch2);
            boolean status = Arrays.equals(ch1,ch2);
            if( status ){
                System.out.println(str1 + " and " + str2 + " : are anagrams");
                return true;
            }else{
                System.out.println(str1 + " and " + str2 + " : are not anagrams");
                return false;
            }
        }else{
            return false;
        }
    }
}
