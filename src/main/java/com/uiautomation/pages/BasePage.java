package com.uiautomation.pages;

import com.uiautomation.driver.DriverManager;
import com.uiautomation.enums.WaitStrategy;
import com.uiautomation.factories.ExplicitWaitFactory;
import com.uiautomation.reports.ExtentLogger;
import org.openqa.selenium.By;


public class BasePage {

    protected void click(By by, WaitStrategy waitStrategy,String elementName) throws Exception {
        ExplicitWaitFactory.performExplicitWait(waitStrategy,by).click();
        ExtentLogger.pass(String.format("Clicked '%s'",elementName),true);
    }

    protected void sendKeys(By by,String value,WaitStrategy waitStrategy,String elementName) throws Exception {
        ExplicitWaitFactory.performExplicitWait(waitStrategy,by).sendKeys(value);
        ExtentLogger.pass(String.format("Entered value '%s' in '%s'",value,elementName),true);
    }

    protected String getPageTitle(){
        return DriverManager.getDriver().getTitle();
    }

}
