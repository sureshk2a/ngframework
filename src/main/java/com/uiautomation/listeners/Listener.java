package com.uiautomation.listeners;

import com.uiautomation.reports.ExtentLogger;
import com.uiautomation.reports.ExtentReport;
import org.testng.*;

import java.io.IOException;
import java.util.Arrays;

public class Listener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        try {
            ExtentReport.initReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        try {
            ExtentReport.flushReport();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            ExtentLogger.pass(result.getMethod().getMethodName()+" is passed",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentLogger.fail(result.getMethod().getMethodName()+" is failed",true);
            ExtentLogger.fail(result.getThrowable().toString());
            ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //attach screenshot
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            ExtentLogger.skip(result.getMethod().getMethodName()+" is skipped",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
