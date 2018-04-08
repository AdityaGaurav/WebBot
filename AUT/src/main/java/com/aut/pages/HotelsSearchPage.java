package com.aut.pages;

import com.aut.pageobjects.HotelsHomePageObjects;
//import com.sun.deploy.util.StringUtils;
import framework.driverfactory.WebDriverThread;
import framework.uievent.Browser;
import framework.uievent.Element;
import framework.uievent.TimeOutHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormatSymbols;
import java.util.Objects;

//import static com.sun.deploy.util.StringUtils.splitString;

public class HotelsSearchPage {

    private WebDriver driver;

    public HotelsSearchPage() {
        driver = WebDriverThread.getDriver();
    }

    public void getHomePage() {
        Browser.openURL(driver, "https://www.ixigo.com/hotels");
    }

    public void waitToLoadPage() {
        TimeOutHandler.waitForPageToLoad(driver);
    }

    public boolean isLogoDisplayed() throws Exception {
        return Element.isElementDisplayed(HotelsHomePageObjects.IXIGO_LOGO, driver);
    }

    public boolean isHotelsLinkIsHighLighted() throws Exception {
        WebElement element = new Element().findWebElement(HotelsHomePageObjects.HOTELS_LINK_TEXT, driver, 10, 500);
        String value = Element.getElementAttributeValue(element, "class");
        return value.equals("selected");
    }

    public void clickOnWhereTextBox() throws Exception {
        new Element().findWebElement(HotelsHomePageObjects.WHERE_TEXT_BOX, driver, 10, 500).click();
    }

    public WebElement getCountrySearchPopUp() throws Exception {
        return new Element().findWebElement(HotelsHomePageObjects.COUNTRY_POP_UP, driver);
    }

    public boolean isCountrySearchPopUpAvailable() throws Exception {
        return getCountrySearchPopUp().isDisplayed();
    }

    public void typeInWhereInputTextBox(String countryName) throws Exception {
        Objects.requireNonNull(countryName);
        new Element().findWebElement(HotelsHomePageObjects.WHERE_TEXT_BOX, driver).sendKeys(countryName);
    }

    public void selectOptionFromTheCountryPopUP(String countryName) throws Exception {
        Objects.requireNonNull(countryName);
        new Element().findWebElement(HotelsHomePageObjects.getCountryNmae(countryName), driver).click();
    }

    //12/01/2018
    public void selectDateFromTheCalendarPopUp(String dateOfBooking) throws Exception {
        new Element().findWebElement(HotelsHomePageObjects.getCalendarPopUp(1), driver);
        String[] fromDate={}; //StringUtils.splitString(dateOfBooking, "/");
        String requiredDate = fromDate[0];
        String requiredMonth = fromDate[1];
        String requiredYear = fromDate[2];
        String monthName = getMonthNameFromInt(Integer.valueOf(requiredMonth));
        //String getYear = findElement(HotelsHomePageLocator.getDateFromTheCalendar()).getText();
        // String getYearText = StringUtils.splitString(getYear," ");
        String getYearText = getTheCalendarYearText();
        Objects.requireNonNull(getYearText);
        if (!requiredYear.equalsIgnoreCase(getYearText)) {
            while (!requiredYear.equalsIgnoreCase(getYearText)) {
//            WebElement nextButton = findElement(HotelsHomePageLocator.NEXT_BUTTON_ON_CALENDAR);
//            nextButton.click();
                new Element().findWebElement(HotelsHomePageObjects.NEXT_BUTTON_ON_CALENDAR, driver).click();
                getYearText = getTheCalendarYearText();
            }
        }

        String getMonthText = getTheCalendarMonthText();
        Objects.requireNonNull(getYearText);
        if (!requiredMonth.equalsIgnoreCase(monthName)) {
            while (!getMonthText.equalsIgnoreCase(monthName)) {
//            WebElement nextButton = findElement(HotelsHomePageLocator.NEXT_BUTTON_ON_CALENDAR);
//            nextButton.click();
                new Element().findWebElement(HotelsHomePageObjects.NEXT_BUTTON_ON_CALENDAR, driver).click();
                monthName = getTheCalendarMonthText();
            }
        }

        new Element().findWebElement(HotelsHomePageObjects.getDateOnCalendar(Integer.valueOf(requiredDate)), driver).click();
    }

    public void clickOnSearchButton() throws Exception {
        new Element().findWebElement(HotelsHomePageObjects.SEARCH_BUTTON, driver).click();
    }

    private String getTheCalendarYearText() throws Exception {
        String getYear = new Element().findWebElement(HotelsHomePageObjects.getDateFromTheCalendar(), driver).getText();
        String[] getYearText = {};//StringUtils.splitString(getYear, " ");
        if (getYearText.length == 0) {
            throw new NullPointerException("Year Text is not available");
        }
        return getYearText[1];
    }

    private String getTheCalendarMonthText() throws Exception {
        String getYear = new Element().findWebElement(HotelsHomePageObjects.getDateFromTheCalendar(), driver).getText();
        String[] getYearText = {};//StringUtils.splitString(getYear, " ");
        if (getYearText.length == 0) {
            throw new NullPointerException("Year Text is not available");
        }
        return getYearText[0];
    }

    public static String getMonthNameFromInt(int month) {
        String monthName = new DateFormatSymbols().getMonths()[month - 1];
        return monthName;
    }
}
