package com.aut.pages;

import com.aut.pageobjects.AmazonHomePageObjects;
import framework.conditioncheck.PreConditionCheck;
import framework.driverfactory.WebDriverThread;
import framework.uievent.Browser;
import framework.uievent.DropDown;
import framework.uievent.Element;
import framework.uievent.WindowHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class AmazonHomePage {
    private WebDriver driver;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public AmazonHomePage() {
        driver = WebDriverThread.getDriver();
    }

    public void getMeAmazonPage() {
        String url = "https://www.amazon.in";
        Browser.openURL(driver, url);
    }

    public boolean isAtAmazonPage() throws Exception {
        return Element.isElementDisplayed(AmazonHomePageObjects.NAV_SEARCH_BAR, driver, 15, 500);
    }

    public void doSearchBySelectingCategory(String category, String searchKeyword, long timeout, long pollingTime) throws Exception {
        DropDown.selectByVisibleTextFromTheDropDown(AmazonHomePageObjects.SEARCH_DROP_DOWN, driver, category, timeout, pollingTime);
        doSearchAProduct(searchKeyword, 10, 500);
    }

    public void doSearchAProduct(String keywordToSearch, long timeout, long pollingTime) throws Exception {
        PreConditionCheck.checkNotNullNotBlankOrEmpty(keywordToSearch, "Keyword to seach should not be null");
        Element.senTextToElement(AmazonHomePageObjects.SEARCH_BAR, driver, keywordToSearch, 15, 100);
        Element.findAndClickOnElement(AmazonHomePageObjects.SEARCH_BUTTON_NAV_BAR, driver, 5, 500);
    }

    public void getInFoFromSearchPage() throws Exception {
        Element.isElementDisplayed(AmazonHomePageObjects.SEARCH_BUTTON_NAV_BAR, driver, 20, 100);
        String searchCountInfo = Element.getTextOfElement(AmazonHomePageObjects.SEARCH_RESULT_INFO, driver, 15, 500);
        System.out.println(searchCountInfo);
    }

    public void selectFirstLinkInSearchPage() throws Exception {
        List<WebElement> searchResultCount = Element.findWebElements(AmazonHomePageObjects.SEARCH_OPTION, driver, 15, 500);
        WebElement element = searchResultCount.get(0);
        String currentWindowHandle = WindowHandler.getWindowHandleName(driver);
        Element.clickOnWebElement(element);
        Set<String> windows = WindowHandler.getWindowHandles(driver);
        windows.remove(currentWindowHandle);
        String window =windows.iterator().next();
        Browser.switchToWindow(driver,window);
        Element.isElementDisplayed(AmazonHomePageObjects.IPHONE6_HEADING,driver,20,500);
        Element.findAndClickOnElement(AmazonHomePageObjects.AMAZON_FREE_DELIVERY_DETAILS,driver,10,500);
        Set<String> handles = WindowHandler.getWindowHandles(driver);
        handles.remove(currentWindowHandle);
        handles.remove(window);
        Browser.switchToWindow(driver,handles.iterator().next());
        Assert.assertTrue(Element.isElementDisplayed(AmazonHomePageObjects.AMAZON_GLOBAL_STORE,driver,20,500));
        Browser.closeBrowser(driver);
        Browser.switchToWindow(driver,window);
        Browser.closeBrowser(driver);
        Browser.switchToWindow(driver,currentWindowHandle);
        System.out.println(Element.isElementDisplayed(AmazonHomePageObjects.SEARCH_FEEDBACK,driver,15,500));
    }


}
