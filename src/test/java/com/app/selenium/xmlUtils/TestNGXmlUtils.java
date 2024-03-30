package com.app.selenium.xmlUtils;

import org.testng.xml.XmlSuite;

import java.io.FileWriter;
import java.io.IOException;

public class TestNGXmlUtils {
    public static void generateXmlFile(XmlSuite suite, String filePath) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write(suite.toXml());
            System.out.println("Generated TestNG XML file: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to write TestNG XML file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
    }
}
