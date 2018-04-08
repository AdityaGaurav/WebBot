package com.aut.pageobjects;

import org.openqa.selenium.By;

public class AmazonHomePageObjects {
    public static final By SEARCH_DROP_DOWN = By.id("searchDropdownBox");
    public static final By NAV_SEARCH_BAR = By.id("nav-search");
    public final static By SEARCH_BAR = By.id("twotabsearchtextbox");
    public final static By SEARCH_BUTTON_NAV_BAR = By.xpath("//input[@type='submit'] [@class='nav-input']");
    public static final By LEFT_NAV_BAR_SEARCH_PAGE = By.id("leftNav");
    public static final By SEARCH_RESULT_INFO = By.xpath("//span[@id='s-result-count']");
    public static final By SEARCH_OPTION = By.xpath("//img[@class='s-access-image cfMarker']");

    public static By selectOptionInSearchPage(int index) {
        return By.xpath("(//img[@class='s-access-image cfMarker'])[" + index + "]");
    }

    public static By IPHONE6_HEADING = By.xpath("//span[contains(text(),' Apple iPhone 6 (Space Grey, 32GB)')]");
    public static By AMAZON_FREE_DELIVERY_DETAILS = By.xpath("(//a[text()='Details'])[4]");
    public static By AMAZON_GLOBAL_STORE = By.xpath("//a[text()='Global Store']");
    public static By SEARCH_FEEDBACK = By.xpath("//h4[text()='Search Feedback']");

}
