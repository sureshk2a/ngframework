package com.uiautomation.driver;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public final class Driver {

    private Driver(){}

    public static void initDriver(String browser) throws Exception {
    	
    	if(Objects.isNull(DriverManager.getDriver())) {
    		if(browser.equalsIgnoreCase("chrome")) {
        		System.setProperty("webdriver.chrome.driver", FrameworkConstants.getChromedriverPath());
        		ChromeOptions options = new ChromeOptions();
        		options.addArguments("--headless");
                WebDriver driver = new ChromeDriver(options);
                DriverManager.setDriver(driver);
                //
                DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
        	}else if(browser.equalsIgnoreCase("firefox")) {
        		System.setProperty("webdriver.gecko.driver", FrameworkConstants.getGeckodriverPath());
                WebDriver driver = new FirefoxDriver();
                DriverManager.setDriver(driver);
                DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
        	}
    	}
           
    }

    public static void quitDriver(){
        if(Objects.nonNull(DriverManager.getDriver())){
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

}
	