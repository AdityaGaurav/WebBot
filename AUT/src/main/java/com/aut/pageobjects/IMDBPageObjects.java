package com.aut.pageobjects;

import org.openqa.selenium.By;

public class IMDBPageObjects {
    public static final By IMDB_LOGO = By.id("home_img");
    public static final By IMDB_RATINGS = By.xpath("//span[@itemprop='ratingValue']");
}
