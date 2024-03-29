package com.uiautomation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.enums.CategoryType;
import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.utils.PropertyUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    private ExtentReport(){}

    private static ExtentReports extent;
    public static ExtentTest test;
    public static String reportPath;

    public static void initReports() {
        if(Objects.isNull(extent)){
            extent = new ExtentReports();
            reportPath = FrameworkConstants.getExtentReportPath();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportPath());
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Selenium Training"); //Right-hand TOP
        }
    }

    public static void flushReport() {
        if(Objects.nonNull(extent)){
            //TEARDOWN
            extent.flush();
        }
        ExtentManager.unload();
        
        if(PropertyUtils.get(ConfigProperties.OPENREPORTAFTERTEST).equalsIgnoreCase("yes")) {
        	try {
				Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportPath()).toURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }

    public static void createTest(String testName){
       ExtentTest test = extent.createTest(testName);
       ExtentManager.setExtTest(test);
    }

    public static void addAuthors(String[] authors) {
    	
    	for(String author : authors) {
    		ExtentManager.getExtTest().assignAuthor(author);
    	}
    	
    }

    public static void addCategory(CategoryType[] categories) {
    	
    	for(CategoryType category : categories) {
    		ExtentManager.getExtTest().assignCategory(category.toString());
    	}
    	
    }
    
    
}








