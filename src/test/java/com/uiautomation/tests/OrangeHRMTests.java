package com.uiautomation.tests;

import com.uiautomation.pages.OrangeHRMLoginPage;
import com.uiautomation.utils.DataProviderUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public final class OrangeHRMTests extends BaseTest {

    private OrangeHRMTests(){}

    @Test(dataProvider = "getData",dataProviderClass = DataProviderUtils.class)
    public void loginLogoutTest(Map<String,String> data) throws Exception {
        String title = new OrangeHRMLoginPage().enterUsername(data.get("username")).enterPassword(data.get("password")).clickLogin()
                        .clickWelcome().clickLogout()
                        .getTitle();
        Assertions.assertThat(title).as("Verify login page title is %s","OrangeHRM").isEqualTo("OrangeHRM");
    }

    @Test(dataProvider = "getData",dataProviderClass = DataProviderUtils.class)
    public void test1(Map<String,String> data) throws Exception {
        String title = new OrangeHRMLoginPage().enterUsername(data.get("username")).enterPassword(data.get("password")).clickLogin()
                .clickWelcome().clickLogout()
                .getTitle();
        Assertions.assertThat(title).as("Verify login page title is %s","OrangeHRM").isEqualTo("OrangeHRM");
    }


}
