package com.uiautomation.tests;

import com.uiautomation.pages.OrangeHRMHomePage;
import com.uiautomation.pages.OrangeHRMLoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public final class OrangeHRMTests extends BaseTest {

    private OrangeHRMTests(){}

    @Test
    public void loginLogoutTest(){
        String title = new OrangeHRMLoginPage().enterUsername("Admin").enterPassword("admin123").clickLogin()
                        .clickWelcome().clickLogout()
                        .getTitle();
        Assertions.assertThat(title).as("Verify login page title is %s","OrangeHR").isEqualTo("OrangeHR");
    }



}
