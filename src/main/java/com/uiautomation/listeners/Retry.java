package com.uiautomation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.utils.PropertyUtils;

public class Retry implements IRetryAnalyzer {

    private int counter = 0;
    private int limit = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
    	boolean value = false;
    	try {
			if(PropertyUtils.get(ConfigProperties.RETRYFAILEDTESTS).equalsIgnoreCase("yes")) {
				value = counter<limit;
		        counter++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return value;
        
    }
}
