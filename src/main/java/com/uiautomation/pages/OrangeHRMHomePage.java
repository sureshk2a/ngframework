package com.uiautomation.pages;

import com.uiautomation.driver.DriverManager;
import com.uiautomation.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class OrangeHRMHomePage extends BasePage {

    private final By linkWelcome = By.id("welcome");
    private final By linkLogout = By.xpath("//a[text()='Logout']");

    public OrangeHRMHomePage clickWelcome(){
        click(linkWelcome, WaitStrategy.CLICKABLE);
        return this;
    }

    public OrangeHRMLoginPage clickLogout(){
        click(linkLogout,WaitStrategy.CLICKABLE);
        return new OrangeHRMLoginPage();
    }


}
