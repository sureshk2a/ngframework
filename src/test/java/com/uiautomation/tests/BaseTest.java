package com.uiautomation.tests;

import com.uiautomation.driver.Driver;
import com.uiautomation.listeners.MethodInterceptor;
import com.uiautomation.reports.ExtentManager;
import com.uiautomation.reports.ExtentReport;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

public class BaseTest {

    protected BaseTest(){}

    @BeforeMethod
    protected void setUp() throws Exception {
        Driver.initDriver();
    }

    @AfterMethod
    protected void tearDown(ITestResult result){
        Driver.quitDriver();
    }

}
