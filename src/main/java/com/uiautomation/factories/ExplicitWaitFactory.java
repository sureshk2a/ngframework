package com.uiautomation.factories;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.driver.DriverManager;
import com.uiautomation.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitFactory {

    public static WebElement performExplicitWait(WaitStrategy wait, By by){
        WebElement element = null;
        if(wait == WaitStrategy.CLICKABLE){
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()))
                    .until(ExpectedConditions.elementToBeClickable(by));
        }else if(wait == WaitStrategy.PRESENCE){
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        }else if(wait == WaitStrategy.VISIBLE){
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        }else if(wait == WaitStrategy.NONE){
            element = DriverManager.getDriver().findElement(by);
        }
        return element;
    }

}
