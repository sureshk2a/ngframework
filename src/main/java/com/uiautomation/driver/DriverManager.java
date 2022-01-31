package com.uiautomation.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager(){}

    private static ThreadLocal<WebDriver> _driver = new ThreadLocal<>();

    public static  WebDriver getDriver(){ return _driver.get(); }

    public static void setDriver(WebDriver driver){ _driver.set(driver); }

    public static void unload(){ _driver.remove(); }

}
