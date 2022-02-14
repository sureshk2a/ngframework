package com.uiautomation.pages;

import com.uiautomation.enums.WaitStrategy;
import com.uiautomation.reports.ExtentLogger;
import com.uiautomation.reports.ExtentManager;
import org.openqa.selenium.By;

public final class OrangeHRMHomePage extends BasePage {

    private final By linkWelcome = By.id("welcome");
    private final By linkLogout = By.xpath("//a[text()='Logout']");

    public OrangeHRMHomePage clickWelcome() throws Exception {
        click(linkWelcome, WaitStrategy.CLICKABLE,"Welcome button");
        return this;
    }

    public OrangeHRMLoginPage clickLogout() throws Exception {
        click(linkLogout,WaitStrategy.CLICKABLE,"Logout button");
        return new OrangeHRMLoginPage();
    }


}
