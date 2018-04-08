package framework.uievent;

import framework.Defaults;
import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.Objects;

public class BrowserNavigator {

    public static void navigateForward(WebDriver webDriver) {
        PreConditionCheck.checkNotNull(webDriver, Defaults.WEBDRIVER_ERROR_MSG);
        webDriver.navigate().forward();
    }

    public static void navigateBack(WebDriver webDriver) {
        PreConditionCheck.checkNotNull(webDriver,Defaults.WEBDRIVER_ERROR_MSG);
        webDriver.navigate().back();
    }

    public static void refreshPage(WebDriver webDriver) {
        PreConditionCheck.checkNotNull(webDriver,Defaults.WEBDRIVER_ERROR_MSG);
        webDriver.navigate().refresh();
    }

    public static void navigateToURL(String URL, WebDriver webDriver) {
        PreConditionCheck.checkNotNull(webDriver,Defaults.WEBDRIVER_ERROR_MSG);
        Objects.requireNonNull(URL);
        webDriver.navigate().to(URL);
    }

    public static void navigateToURL(URL url, WebDriver webDriver) {
        PreConditionCheck.checkNotNull(webDriver,Defaults.WEBDRIVER_ERROR_MSG);
        PreConditionCheck.checkNotNull(url,Defaults.ERROR_MSG);
        webDriver.navigate().to(url);
    }
}
