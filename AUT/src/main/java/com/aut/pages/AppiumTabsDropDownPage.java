package com.aut.pages;

import com.aut.pageobjects.AppiumTabsDropDownObjects;
import framework.driverfactory.WebDriverThread;
import framework.uievent.Browser;
import framework.uievent.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AppiumTabsDropDownPage {
    private WebDriver driver;

    public AppiumTabsDropDownPage() {
        driver = WebDriverThread.getDriver();
    }

    public void getAppiumPage() throws Exception {
        Browser.openURL(driver, "https://appium.io/");
    }

    public void verifySetTimeOutHeading() throws Exception {
//        Element element = new Element();
//        element.findAndClickOnElement(By.xpath(AppiumTabsDropDownObjects.getLocator("Documentation")), driver).
//                findAndClickOnElement(AppiumTabsDropDownObjects.ENGLISH, driver).
//                findAndClickOnElement(AppiumTabsDropDownObjects.COMMANDS_TAB, driver).
//                findAndClickOnElement(AppiumTabsDropDownObjects.SESSIONS, driver).
//                findAndClickOnElement(AppiumTabsDropDownObjects.TIMEOUTS, driver).
//                findAndClickOnElement(AppiumTabsDropDownObjects.TIMEOUTS_TIMEOUTS, driver);
//        Assert.assertEquals("Set Timeou", element.getText(AppiumTabsDropDownObjects.SET_TIMEOUT_HEADING, driver));
    }

    public void getAppiumServerDetails() {

    }


}
