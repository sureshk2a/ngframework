package com.uiautomation.pages;

import org.openqa.selenium.By;

import com.uiautomation.enums.WaitStrategy;
import com.uiautomation.utils.DynamicXpathUtils;

public final class AmazonHamburgerMenuPage extends BasePage {

	
	private String mainMenuItem = "//div[text()='%s']/parent::a";
	private String subMenuItem = "//a[text()='%s']";
	
	
	public AmazonHamburgerMenuPage ClickComputers() throws Exception {
		click(By.xpath(DynamicXpathUtils.getXpath(mainMenuItem, "Mobiles, Computers")), WaitStrategy.CLICKABLE, "Mobile and Computers menu");
		return this;
	}
	
	public AmazonLaptopPage ClickOnSubMenuItem(String menuItem) throws Exception {
		click(By.xpath(DynamicXpathUtils.getXpath(subMenuItem, menuItem)), WaitStrategy.CLICKABLE, menuItem);
		if(menuItem.contains("Laptops")) {
			return new AmazonLaptopPage();
		}
		return null;
	}
}
