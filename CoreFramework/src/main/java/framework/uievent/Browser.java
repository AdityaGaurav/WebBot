package framework.uievent;

import framework.Defaults;
import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class Browser {

    private static String Empty_URL = "URL can not be Empty/null";

    Browser() {
    }

    /**
     * @param driver            WebDriver Instance
     * @param url               URL to be Open
     * @param conditionToBeMeet Condition ti be checked after page load
     */
    public static void openURLWithExpectedCondition(WebDriver driver, String url, Function conditionToBeMeet) {
        PreConditionCheck.checkNotNull(driver, Defaults.WEBDRIVER_ERROR_MSG);
        PreConditionCheck.checkNotNullNotBlankOrEmpty(url, Empty_URL);
        if (PreConditionCheck.isValidURL(url)) {
            openUrl(driver, url);
        } else {
            throw new IllegalArgumentException("Not a valid URL");
        }
    }

    /**
     * @param driver WebDriver Instance
     * @param url    URL to be Open
     */
    public static void openURL(WebDriver driver, String url) {
        PreConditionCheck.checkNotNull(driver, Defaults.WEBDRIVER_ERROR_MSG);
        PreConditionCheck.checkNotNullNotBlankOrEmpty(url, Empty_URL);
        if (PreConditionCheck.isValidURL(url)) {
            openUrl(driver, url);
        } else {
            throw new IllegalArgumentException("Not a valid URL");
        }
    }

    private static void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public static String getTitleOfThePage(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver, Defaults.WEBDRIVER_ERROR_MSG);
        return driver.getTitle();
    }

    public String getCurrentPageURL(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver, Defaults.WEBDRIVER_ERROR_MSG);
        return driver.getCurrentUrl();
    }

    public static String getCurrentPageSource(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver, Defaults.WEBDRIVER_ERROR_MSG);
        return driver.getPageSource();
    }

    public static void closeBrowser(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver, Defaults.WEBDRIVER_ERROR_MSG);
        driver.close();
    }

    public static void quitDriverInstance(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver, Defaults.WEBDRIVER_ERROR_MSG);
        driver.quit();
    }

    public static void switchToWindow(WebDriver driver, String windowHandle) {
        PreConditionCheck.checkNotNull(driver, "Driver instance should not be null");
        PreConditionCheck.checkNotNullNotBlankOrEmpty(windowHandle, "Window name should not be null");
        driver.switchTo().window(windowHandle);
    }
}
