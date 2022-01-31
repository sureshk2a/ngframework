package com.uiautomation.tests;

import com.uiautomation.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public final class HomepageTests extends BaseTest {

    private HomepageTests(){}

    @Test
    public void Test1(){
        DriverManager.getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys("dog", Keys.ENTER);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void Test2(){
        DriverManager.getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys("Cat", Keys.ENTER);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
