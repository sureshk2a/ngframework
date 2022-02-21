package com.uiautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiautomation.driver.DriverManager;
import com.uiautomation.enums.WaitStrategy;

public final class AmazonHomePage extends BasePage {
	

	private String linkHamburger = "nav-hamburger-menu";
		
	public AmazonHomePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public AmazonHamburgerMenuPage clickHamburger() throws Exception {
		click(By.id(linkHamburger), WaitStrategy.CLICKABLE, "Hamburger Icon");
		return new AmazonHamburgerMenuPage();
	}
	

}
