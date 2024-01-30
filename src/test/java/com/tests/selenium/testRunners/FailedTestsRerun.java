package com.tests.selenium.testRunners;

import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

import static com.app.selenium.listeners.FailedTestListener.failedTests;

public class FailedTestsRerun {

    @Test(dataProvider = "failedTestsProviders")
    public void rerunFailedTest(String testClassName, String testMethodName){
        Class<?> testClass;
        Object testInstance;
        java.lang.reflect.Method testMethod;
        try {
            testClass = Class.forName(testClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            testInstance = testClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            testMethod = testClass.getMethod(testMethodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            testMethod.invoke(testInstance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Test Failed: " + testMethodName);
        }
    }

    //DateProvider to provide information about failed tests
    @DataProvider(name = "failedTestsProviders")
    public Object[][] getFailedTests(){
        Object[][] tests = new Object[failedTests.size()][2];
        for(int i = 0; i < failedTests.size(); i++ ){
            ITestResult iTestResult = failedTests.get(i);
            tests[i][0] = iTestResult.getTestClass().getName();//Test class name
            tests[i][0] = iTestResult.getMethod().getMethodName();//Test methods name
        }
        return tests;
    }
}
