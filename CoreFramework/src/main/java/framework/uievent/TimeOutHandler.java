package framework.uievent;

import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TimeOutHandler {
    //private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    TimeOutHandler() {
        // threadLocal.set(webDriverThreadLocal.get());
    }

    private static long defaultImplicitTimeOut = Constants.DEFAULT_IMPLICIT_TIME_OUT;
    private static long pageLoadTimeOut = Constants.DEFAULT_PAGE_LOAD_TIME_OUT;
    //private WebDriver driver;

    public long getDefaultImplicitTimeOut() {
        return defaultImplicitTimeOut;
    }

    public static void setDefaultImplicitTimeOut(long defaultTimeToWait) {
        defaultImplicitTimeOut = defaultTimeToWait;
    }

    public static long getPageLoadTimeOut() {
        return pageLoadTimeOut;
    }

    public static void setPageLoadTimeOut(long pageLoadTimeOut) {
        pageLoadTimeOut = pageLoadTimeOut;
    }

    public static void resetPageLoadTimeOut() {
        pageLoadTimeOut = Constants.DEFAULT_PAGE_LOAD_TIME_OUT;
    }

    public static void resetDefaultImplicitTimeOut() {
        defaultImplicitTimeOut = Constants.DEFAULT_IMPLICIT_TIME_OUT;
    }


    public static void setImplicitWait(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver,"Driver insatnce can not be null");
        driver.manage().timeouts().implicitlyWait(defaultImplicitTimeOut, TimeUnit.SECONDS);
    }

    public static void waitForPageToLoad(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver,"Driver insatnce can not be null");
        driver.manage().timeouts().implicitlyWait(pageLoadTimeOut, TimeUnit.SECONDS);
    }
}
