package com.app.selenium.reportUtils;

import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class FailedTestNGXmlGenerator {

    public static void generateFailedTestsXml(List<ITestResult> failedTestMethods) {
        StringBuilder xmlContent = new StringBuilder();
        xmlContent.append("<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">");
        xmlContent.append("<suite name=\"RerunFailedTests\">");
        xmlContent.append("<listeners>");
        xmlContent.append("<listener name=\"io.qameta.allure.testng.AllureTestNg\"/>");
        xmlContent.append("</listeners>");
        xmlContent.append("<test name=\"Retry Failed Tests\">");
        xmlContent.append("<classes>");

        for (ITestResult failedTestMethod : failedTestMethods) {

            xmlContent.append("<class name=\"").append(getTestClassFullName(failedTestMethod)).append("\">");
            xmlContent.append("<methods>");
            xmlContent.append("<include name=\"").append(failedTestMethod.getMethod().getMethodName()).append("\"/>");
            xmlContent.append("</methods>");
            xmlContent.append("</class>");
        }

        xmlContent.append("</classes>");
        xmlContent.append("</test>");
        xmlContent.append("</suite>");

        try (FileWriter writer = new FileWriter("src/test/resources/runners/rerun_failed_1tests.xml")) {
            writer.write(xmlContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateXmlFile(XmlSuite suite, String filePath) {
        try {
            // Create JAXB context
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlSuite.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshal XML suite to file
            try (FileWriter writer = new FileWriter("src/test/resources/runners/rerun_failed_1tests.xml")) {
                marshaller.marshal(suite, writer);
            }

            System.out.println("Generated TestNG XML file: " + filePath);
        } catch (JAXBException | IOException e) {
            System.err.println("Failed to write TestNG XML file: " + e.getMessage());
        }
    }

    private static String getTestClassFullName(ITestResult iTestResult){
        Class<?> testClass = iTestResult.getTestClass().getRealClass();
        String packageName = testClass.getPackage().getName();
        String className = testClass.getSimpleName();
        return packageName + "."+ className;
    }
}
