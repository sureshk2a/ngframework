package com.uiautomation.listeners;

import com.uiautomation.annotation.FrameworkAnnotation;
import com.uiautomation.reports.ExtentLogger;
import com.uiautomation.reports.ExtentReport;
import com.uiautomation.utils.EmailUtils;

import org.testng.*;

import java.io.IOException;
import java.util.Arrays;

public class Listener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
 
        ExtentReport.flushReport();
        try {
			EmailUtils.mailTestReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getDescription());
        
        //Add values from custom annotation
        ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
        .author());
        ExtentReport.addCategory(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
                .category());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName()+" is passed",true);

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName()+" is failed",true);
        ExtentLogger.fail(result.getThrowable().toString());
        ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
        //attach screenshot
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getMethodName()+" is skipped",true);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
    }


}
