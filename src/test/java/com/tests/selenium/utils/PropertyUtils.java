package com.tests.selenium.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filepath){
        Properties properties = new Properties();
        BufferedReader bufferedReader;

        try{
            bufferedReader = new BufferedReader(new FileReader(filepath));
            try{
                properties.load(bufferedReader);
                bufferedReader.close();
            }catch(IOException e){
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties " +  filepath);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Properties file not Found at " + filepath);
        }
        return properties;
    }
}
