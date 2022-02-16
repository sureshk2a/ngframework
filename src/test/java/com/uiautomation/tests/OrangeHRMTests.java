package com.uiautomation.tests;

import com.uiautomation.pages.OrangeHRMLoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

public final class OrangeHRMTests extends BaseTest {

    private OrangeHRMTests(){}

    @Test()
    public void loginLogoutTest(Map<String,String> data) throws Exception {
        String title = new OrangeHRMLoginPage().enterUsername(data.get("username")).enterPassword(data.get("password")).clickLogin()
                        .clickWelcome().clickLogout()
                        .getTitle();
        Assertions.assertThat(title).as("Verify login page title is %s","OrangeHRM").isEqualTo("OrangeHRM");
    }

    @Test()
    public void test1(Map<String,String> data) throws Exception {
        String title = new OrangeHRMLoginPage().enterUsername(data.get("username")).enterPassword(data.get("password")).clickLogin()
                .clickWelcome().clickLogout()
                .getTitle();
        Assertions.assertThat(title).as("Verify login page title is %s","OrangeHRM").isEqualTo("OrangeHRM");
    }


}
