package com.tests.selenium.testRunners;

import com.app.selenium.listeners.FailedTestListener;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;


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
        System.out.println("FailedTestsProvider is invoked");
        System.out.println("FailedTestListener.GetFailedTests(): " + FailedTestListener.getFailedTests());
        Object[][] tests = new Object[FailedTestListener.getFailedTests().size()][2];
        for(int i = 0; i < FailedTestListener.getFailedTests().size(); i++ ){
            ITestResult iTestResult = FailedTestListener.getFailedTests().get(i);
            tests[i][0] = iTestResult.getTestClass().getName();//Test class name
            tests[i][1] = iTestResult.getMethod().getMethodName();//Test methods name
            System.out.println("TestClassName: " + tests[i][0]);
            System.out.println("TestClassMethod: " + tests[i][1]);
        }
        return tests;
    }
}
