package com.app.selenium.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result){
        if ( !result.isSuccess() && retryCount < MAX_RETRY_COUNT) {
            System.out.println(" Result of Test Method : " + result.getMethod().getMethodName() + " Result: " + result.getStatus());
            retryCount++;
            System.out.println(" Retrycount: " + retryCount);
            return true;
        }
        return false;
    }
}
