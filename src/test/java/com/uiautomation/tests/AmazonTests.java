package com.uiautomation.tests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.uiautomation.annotation.FrameworkAnnotation;
import com.uiautomation.enums.CategoryType;
import com.uiautomation.pages.AmazonHomePage;
import com.uiautomation.reports.ExtentManager;

public final class AmazonTests extends BaseTest{

	private AmazonTests() {}
	
	
	@FrameworkAnnotation(author = {"Suresh","Kumar"},category= {CategoryType.SMOKE,CategoryType.REGRESSION})
	@Test
	public void amazonTest(Map<String,String> data) throws Exception {
		
		String title = new AmazonHomePage().clickHamburger().ClickComputers().ClickOnSubMenuItem(data.get("menutext")).getTitle();
		
		Assertions.assertThat(title).isNotNull().isNotBlank();	
		
	}
	
}
