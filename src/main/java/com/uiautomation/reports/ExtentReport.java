package com.uiautomation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    private ExtentReport(){}

    private static  ExtentReports extent = new ExtentReports();

    public static void initReports(){
        if(Objects.isNull(extent)){
            ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Selenium Training"); //Right-hand TOP
        }
    }

    public static void flushReport() throws IOException {
        if(Objects.nonNull(extent)){
            //TEARDOWN
            extent.flush();
            //Open the file in default browser
            Desktop.getDesktop().browse(new File("index.html").toURI());
        }
    }

    public static void createTest(String testName){
        extent.createTest(testName);
    }

}
