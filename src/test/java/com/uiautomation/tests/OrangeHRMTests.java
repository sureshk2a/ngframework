package com.uiautomation.tests;

import com.uiautomation.pages.OrangeHRMLoginPage;
import org.testng.annotations.Test;

public final class OrangeHRMTests extends BaseTest {

    private OrangeHRMTests(){}

    @Test
    public void loginLogoutTest(){
        OrangeHRMLoginPage ohlp = new OrangeHRMLoginPage();
        ohlp.enterUsername("Admin").enterPassword("admin123").clickLogin();
    }



}
