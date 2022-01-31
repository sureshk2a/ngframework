package com.uiautomation.tests;

import com.uiautomation.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public final class LoginpageTests extends BaseTest{

    private LoginpageTests(){}

    @Test
    public void Test3(){
        DriverManager.getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys("tiger"+ Keys.ENTER);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void Test4(){
        DriverManager.getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys("Lion"+ Keys.ENTER);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
