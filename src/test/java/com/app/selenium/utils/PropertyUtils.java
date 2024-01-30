package com.app.selenium.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class PropertyUtils {

    public static Properties propertyLoader(String filepath){
        Properties properties;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to load Properties file: " + filepath);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Property File Not found at: " + filepath);
        }
        return properties;
    }
}
