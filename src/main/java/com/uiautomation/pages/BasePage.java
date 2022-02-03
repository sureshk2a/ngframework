package com.uiautomation.pages;

import com.uiautomation.driver.DriverManager;
import com.uiautomation.enums.WaitStrategy;
import com.uiautomation.factories.ExplicitWaitFactory;
import org.openqa.selenium.By;


public class BasePage {

    protected void click(By by, WaitStrategy waitStrategy){
        ExplicitWaitFactory.performExplicitWait(waitStrategy,by).click();
    }

    protected void sendKeys(By by,String value,WaitStrategy waitStrategy){
        ExplicitWaitFactory.performExplicitWait(waitStrategy,by).sendKeys(value);
    }

    protected String getPageTitle(){
        return DriverManager.getDriver().getTitle();
    }

}
