package com.tests.selenium.logicalPrograming;

import com.app.selenium.practiceJava.StringProgramming;
import org.testng.annotations.Test;

public class StringLogicals_Test {
    StringProgramming stringProgramming = new StringProgramming();

    @Test
    public void verifyString_Are_Anagrams(){
        String s1 = "Levis";
        String s2 = "elvis";
        System.out.println("String are anagrams: " + stringProgramming.verifyAnagrams(s1, s2));
        System.out.println("String Table and Bleat are anagrams: " + stringProgramming.verifyAnagrams("Table", "Bleat"));
        System.out.println("String Faster and testar s2 anagrams: " + stringProgramming.verifyAnagrams("faster", "tester"));

    }

    @Test
    public void verifyAnagrams_Using_Arrays(){
        stringProgramming.verifyAnagrams_Using_Arrays("Table", "Bleat");
        stringProgramming.verifyAnagrams_Using_Arrays("faster", "tester");
        stringProgramming.verifyAnagrams_Using_Arrays("Levis", "ELVIS");
    }
}
