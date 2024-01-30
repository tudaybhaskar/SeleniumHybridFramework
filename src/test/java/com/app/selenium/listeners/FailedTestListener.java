package com.app.selenium.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class FailedTestListener implements ITestListener {
    /*
    In summary, while ITestListener is more focused on individual test methods, ITestNGListener provides a more
    comprehensive set of callbacks for events at different levels within the TestNG test execution lifecycle.
    Depending on your needs, you may choose to implement one or the other, or even both if you require a
    combination of specific and general event handling.
     */
    public static List<ITestResult> failedTests = new ArrayList<>();

    @Override
    public void onTestFailure(ITestResult iTestResult){
        System.out.println("Test Failed: " + iTestResult.getName());
        failedTests.add(iTestResult);
    }

}
