package com.aut.pages;

import com.aut.pageobjects.GooglePageObjects;
import framework.conditioncheck.PreConditionCheck;
import framework.customwaits.CustomFluentWait;
import framework.customwaits.CustomWaitConditions;
import framework.driverfactory.WebDriverThread;
import framework.uievent.Browser;
import framework.uievent.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchPage {
    WebDriver driver;

    public GoogleSearchPage() {
        driver = WebDriverThread.getDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void getMeGooglePage() {
        String google = "https://www.google.com";
        Browser.openURL(driver, google);
    }

    public void doSearch(String keyword) throws Exception {
        PreConditionCheck.checkNotNullNotBlankOrEmpty(keyword, "Keyword should not be null or empty");
        Element.senTextToElement(GooglePageObjects.GoogleSearchBar, driver, keyword, 15, 500);
        Element.findAndClickOnElement(GooglePageObjects.getFirstSearchSuggestion(keyword), driver, 10, 500);
        WebElement element = new CustomFluentWait<>(driver, 20, 500).
                waitForTheElementAsPerTheGivenCondition(CustomWaitConditions.waitForTheElementTillItVisible(GooglePageObjects.GoogleSearchResultPageContainer));

    }

    public void findTheExpectedLinkOnSearchResultPage(String url, String textInsideAnchorTag) {
        PreConditionCheck.checkNotNullNotBlankOrEmpty(url, "Should not be null or empty");
        PreConditionCheck.checkNotNullNotBlankOrEmpty(textInsideAnchorTag, "Should not be null or empty");
        List<WebElement> linksOfSearch = Element.findWebElements(GooglePageObjects.
                getExpectedLinks(url, textInsideAnchorTag), driver, 15, 500);
        Element.clickOnWebElement(linksOfSearch.get(0));
    }
}
