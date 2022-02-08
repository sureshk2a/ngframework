package com.uiautomation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExtendReportDemo {

    @Test
    public void test1() throws IOException {

        //SETUP
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Selenium Training"); //Right-hand TOP


        //TEST
        ExtentTest test = extent.createTest("First Test");
        test.pass("Checking the logs");

        ExtentTest test1 = extent.createTest("First Test");
        test1.pass("Checking the logs in second test");


        //TEARDOWN
        extent.flush();
        //Open the file in default browser
        Desktop.getDesktop().browse(new File("index.html").toURI());
    }


}
