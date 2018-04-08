package com.aut.pages;

import com.aut.pageobjects.IMDBPageObjects;
import framework.driverfactory.WebDriverThread;
import framework.uievent.Element;
import org.openqa.selenium.WebDriver;

public class IMDBPage {
    private WebDriver driver;

    public IMDBPage() {
        driver = WebDriverThread.getDriver();
    }

    public IMDBPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAtIMDBPage() throws Exception {
        return Element.isElementDisplayed(IMDBPageObjects.IMDB_LOGO, driver, 15, 500);
    }

    public String getRatingsOfTheMovie() throws Exception {
        return Element.getTextOfElement(IMDBPageObjects.IMDB_RATINGS, driver, 10, 500);
    }


}
