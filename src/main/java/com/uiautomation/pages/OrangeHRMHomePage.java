package com.uiautomation.pages;

import com.uiautomation.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrangeHRMHomePage {

    private final By link_welcome = By.id("welcome");
    private final By link_logout = By.xpath("//a[text()='Logout']");

    public OrangeHRMHomePage clickWelcome(){
        DriverManager.getDriver().findElement(link_welcome).click();
        return this;
    }

    public OrangeHRMLoginPage clickLogout(){
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5))
        .until(ExpectedConditions.elementToBeClickable(link_logout));
        DriverManager.getDriver().findElement(link_logout).click();
        return new OrangeHRMLoginPage();
    }


}
