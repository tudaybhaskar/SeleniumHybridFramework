package com.app.selenium.listeners;

import com.app.selenium.reportUtils.FailedTestNGXmlGenerator;
import com.app.selenium.xmlUtils.TestNGXmlUtils;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class FailedTestListener implements ITestListener {
    /*
    In summary, while ITestListener is more focused on individual test methods, ITestNGListener provides a more
    comprehensive set of callbacks for events at different levels within the TestNG test execution lifecycle.
    Depending on your needs, you may choose to implement one or the other, or even both if you require a
    combination of specific and general event handling.
     */
    private static final List<ITestResult> failedTests = new ArrayList<>();

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String className = result.getMethod().getTestClass().getName();
        String fullyQualifiedMethodName = className + "." + methodName;

        if(!result.isSuccess()){
            failedTests.add(result);
        }
    }

    // Implement other ITestListener methods as needed

    public static List<ITestResult> getFailedTests() {
        return failedTests;
    }

    @Override
    public void onFinish(ITestContext context){
        FailedTestNGXmlGenerator.generateFailedTestsXml(failedTests);

        List<ITestResult> failedTests = (List<ITestResult>) context.getFailedTests().getAllResults();
        System.out.println("OnFinish: FailedTests: " + failedTests.size());

        if( !failedTests.isEmpty()){
            // Create a new XML suite
            XmlSuite suite = new XmlSuite();
            suite.setName("RerunFailedTests");

            //Create a new XML Test
            XmlTest test = new XmlTest();
            test.setName("Retry Failed Tests");

            // Add failed test methods to the XML test
            for( ITestResult failedTest : failedTests ){
                XmlClass xmlClass = new XmlClass(failedTest.getTestClass().getName());
                List<XmlInclude> includes = new ArrayList<>();
                includes.add(new XmlInclude(failedTest.getMethod().getMethodName()));
                xmlClass.setIncludedMethods(includes);
                test.getClasses().add(xmlClass);
            }
            System.out.println("Invoking Generate XML File");
            TestNGXmlUtils.generateXmlFile(suite, "src/test/resources/runners/rerun_failed_tests.xml");
        }
    }


}
