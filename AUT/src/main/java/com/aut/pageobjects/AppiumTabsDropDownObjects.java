package com.aut.pageobjects;

import org.openqa.selenium.By;

public class AppiumTabsDropDownObjects {
    public static final By DOCUMENTATION = By.xpath("//a[text()='Documentation']");
    public static final By ENGLISH = By.xpath("//a[text()='English']");
    public static final By COMMANDS_TAB = By.xpath("//a[text()='Commands ']");
    public static final By SESSIONS = By.xpath("//a[text()='Session']");
    public static final By TIMEOUTS = By.xpath("//a[text()='Timeouts' and (@data-toggle)]");
    public static final By TIMEOUTS_TIMEOUTS = By.xpath("//a[text()='Timeouts' and not(@data-toggle)]");
    public static final By SET_TIMEOUT_HEADING = By.xpath("//h1[text()='Set Timeouts']");

    public static final String getLocator(String input) {
        return "//a[text()='" + input + "']";
    }
}
