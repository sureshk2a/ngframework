package com.uiautomation.pages;

import com.uiautomation.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OrangeHRMLoginPage extends BasePage{

    private final By textboxUsername = By.id("txtUsername");
    private final By textboxPassword = By.xpath("//input[@id='txtPassword' and @type='password']");
    private final By buttonLogin = By.id("btnLogin");

    public OrangeHRMLoginPage enterUsername(String username) throws Exception {
        sendKeys(textboxUsername,username, WaitStrategy.PRESENCE,"Username TextBox");
        return this;
    }

    public OrangeHRMLoginPage enterPassword(String password) throws Exception {
        sendKeys(textboxPassword,password,WaitStrategy.PRESENCE,"Password TextBox");
        return this;
    }

    public OrangeHRMHomePage clickLogin() throws Exception {
        click(buttonLogin,WaitStrategy.CLICKABLE,"Login button");
        return new OrangeHRMHomePage();
    }

    public String getTitle(){
        return getPageTitle();
    }


}
