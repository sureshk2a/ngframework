package com.uiautomation.tests;

import com.uiautomation.driver.Driver;
import com.uiautomation.reports.ExtentReport;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Objects;

public class BaseTest {

    protected BaseTest(){}

    @BeforeSuite
    public void beforeSuite(){
        ExtentReport.initReports();
    }

    @AfterSuite
    public void afterSuite() throws IOException {
        ExtentReport.flushReport();
    }

    @BeforeMethod
    protected void setUp() throws Exception {
        Driver.initDriver();
    }

    @AfterMethod
    protected void tearDown(){
        Driver.quitDriver();
    }

}
