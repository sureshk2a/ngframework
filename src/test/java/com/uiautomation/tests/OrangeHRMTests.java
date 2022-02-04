package com.uiautomation.tests;

import com.uiautomation.pages.OrangeHRMHomePage;
import com.uiautomation.pages.OrangeHRMLoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public final class OrangeHRMTests extends BaseTest {

    private OrangeHRMTests(){}

    @Test(dataProvider = "loginTestDataProvider")
    public void loginLogoutTest(String username,String password){
        String title = new OrangeHRMLoginPage().enterUsername(username).enterPassword(password).clickLogin()
                        .clickWelcome().clickLogout()
                        .getTitle();
        Assertions.assertThat(title).as("Verify login page title is %s","OrangeHRM").isEqualTo("OrangeHRM");
    }


    @DataProvider(name = "loginTestDataProvider",parallel = true)
    public Object[][] getData(){
        return new Object[][]{
                {"Admin","admin123"},
                {"Admin","admin1234"}
        };
    }

}
