package com.uiautomation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int counter = 0;
    int limit = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(counter<limit){
            counter++;
            return true;
        }
        return false;
    }
}
