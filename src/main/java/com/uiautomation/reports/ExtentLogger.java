package com.uiautomation.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.uiautomation.driver.DriverManager;
import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.utils.PropertyUtils;
import com.uiautomation.utils.ScreenshotUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ExtentLogger {

    private ExtentLogger(){}

    public static void pass(String message){
        ExtentManager.getExtTest().pass(message);
    }
    public static void fail(String message){
        ExtentManager.getExtTest().fail(message);
    }
    public static void skip(String message){
        ExtentManager.getExtTest().skip(message);
    }
    public static void pass(String message,boolean isScreenshotRequired) throws Exception {
        if(PropertyUtils.get(ConfigProperties.PASSEDTESTSCREENSHOTS).equalsIgnoreCase("yes")){
            ExtentManager.getExtTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }else {
            pass(message);
        }
    }
    public static void fail(String message,boolean isScreenshotRequired) throws Exception {
        if(PropertyUtils.get(ConfigProperties.FAILEDTESTSCREENSHOTS).equalsIgnoreCase("yes")){
            ExtentManager.getExtTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }else {
            fail(message);
        }
    }
    public static void skip(String message,boolean isScreenshotRequired) throws Exception {
        if(PropertyUtils.get(ConfigProperties.SKIPPEDTESTSCREENSHOTS).equalsIgnoreCase("yes")){
            ExtentManager.getExtTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }else {
            skip(message);
        }
    }

}
