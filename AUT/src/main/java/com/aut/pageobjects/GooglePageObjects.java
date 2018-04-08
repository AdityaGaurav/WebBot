package com.aut.pageobjects;

import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.By;

public class GooglePageObjects {
    public static final By GoogleSearchBar = By.id("lst-ib");
    public static final By GoogleSearchButton = By.xpath("//input[@value=Google Search']");
    public static final By GoogleSearchFirstSuggestion = By.xpath("//li[1]//div[text()='titanic']");
    public static final By GoogleSearchResultPageContainer = By.id("ires");
    public static By getExpectedLinks(String hrefValue, String textOfAnchorTag){
        return By.xpath("//a[contains(@href, '"+hrefValue+"') and contains(text(),'"+textOfAnchorTag+"')]");
    }

    public static final By getFirstSearchSuggestion(String keyword){
        PreConditionCheck.checkNotNullNotBlankOrEmpty(keyword,"Keyword should not be null or empty");
        return By.xpath("//li[1]//div[text()='"+keyword+"']");
    }
}

