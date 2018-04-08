package com.aut.tests;

import com.aut.pages.AppiumTabsDropDownPage;
import org.testng.annotations.Test;

public class AppiumTest {

    @Test
    void verifyTheTimeOutHeadingText() throws Exception {
        AppiumTabsDropDownPage appium = new AppiumTabsDropDownPage();
        appium.getAppiumPage();
        appium.verifySetTimeOutHeading();
    }

    @Test
    void verifyTheTimeOutHeadingText2() throws Exception {
        AppiumTabsDropDownPage appium = new AppiumTabsDropDownPage();
        appium.getAppiumPage();
        appium.verifySetTimeOutHeading();
    }
}
