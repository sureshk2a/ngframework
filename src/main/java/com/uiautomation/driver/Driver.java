package com.uiautomation.driver;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.utils.ReadPropertyFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public final class Driver {

    private Driver(){}

    public static void initDriver() throws Exception {
            System.setProperty("webdriver.chrome.driver", FrameworkConstants.getChromedriverPath());
            WebDriver driver = new ChromeDriver();
            DriverManager.setDriver(driver);
            DriverManager.getDriver().get(ReadPropertyFile.get("url"));
    }

    public static void quitDriver(){
        if(Objects.nonNull(DriverManager.getDriver())){
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

}
