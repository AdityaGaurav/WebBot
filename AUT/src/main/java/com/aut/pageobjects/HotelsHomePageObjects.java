package com.aut.pageobjects;

import org.openqa.selenium.By;

import java.util.Objects;

public class HotelsHomePageObjects {
    public static final By IXIGO_LOGO = By.xpath("//img[@class='ixigo-logo']");
    public static final By HOTELS_LINK_TEXT = By.xpath("//a[text()='hotels']");
    public static final By SEARCH_BAR = By.className("hotel-form max-container-width");
    public static final By WHERE_TEXT_BOX = By.xpath("//input[@placeholder='Enter destination or hotel name']");
    public static final By CHECK_IN_TEXT_BOX = By.xpath("//input[@placeholder='check-in']");
    public static final By CHECK_OUT_TEXT_BOX = By.xpath("//input[@placeholder='check-out']");
    public static final By TRAVELLING_AS_AND_GUEST = By.xpath("//div[contains(text() ,'Travelling as')]");
    public static final By SEARCH_BUTTON = By.xpath("//div[@class='search u-ib']/button");

    public static final By COUNTRY_POP_UP = By.xpath("//div[contains(@class,'autocompleter-results')]");

    public static By getCountryNmae(String nameOfCountry) {
        Objects.requireNonNull(nameOfCountry);
        String countryName = "//div[contains(text(),'" + nameOfCountry + "')]";
        return By.xpath(countryName);
    }

    public static By getCalendarPopUp(int i){
        if(i < 1 || i>2){
            throw new IllegalArgumentException("input should be 1 or 2");
        }
        String calendarPopUp = "(//div[@class ='rd-date'])["+i+"]";
        return By.xpath(calendarPopUp);
    }

    public static By getDateFromTheCalendar(){
        return By.xpath("(//div[@class ='rd-month-label'])[1]");
    }

    public static final By NEXT_BUTTON_ON_CALENDAR = By.xpath("(//button[@type='button'])[2]");
    public static By getDateOnCalendar(int date){
        return By.xpath("//div[@class = 'day' and contains(text(),'"+date+"')]");
    }


}
