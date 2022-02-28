package com.uiautomation.driver;

import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.utils.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

/**
 * 
 * Driver class is responsible for invoking and closing the drivers
 * 
 * <p>
 * It is responsible for setting the driver variable to DriverManager and handles the thread safety of driver instance 
 * <p>
 * 
 * @author Suresh
 *
 */

public final class Driver {

    private Driver(){}

    public static void initDriver(String browser)  {
    	
    	if(Objects.isNull(DriverManager.getDriver())) {
    		if(browser.equalsIgnoreCase("chrome")) {
        		//System.setProperty("webdriver.chrome.driver", FrameworkConstants.getChromedriverPath());
    			WebDriverManager.chromedriver().setup();
        		ChromeOptions options = new ChromeOptions();
        		options.addArguments("--headless");
                WebDriver driver = new ChromeDriver(options);
                DriverManager.setDriver(driver);
                //
                DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
        	}else if(browser.equalsIgnoreCase("firefox")) {
        		//System.setProperty("webdriver.gecko.driver", FrameworkConstants.getGeckodriverPath());
        		WebDriverManager.firefoxdriver().setup();
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
	