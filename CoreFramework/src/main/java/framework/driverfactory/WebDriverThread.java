package framework.driverfactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WebDriverThread {

    private static Logger logger = LogManager.getLogger(WebDriverThread.class);
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();


    /**
     * @param browserType Name of Browser to invoke at run time
     * @throws Exception Note: If browserType==null, default chrome
     */
    public static void setDriver(String browserType, String host) throws Exception {
        WebDriver driver;
        driver = determineEffectiveDriverType(browserType);
        webDriver.set(driver);
    }


    public static WebDriver getDriver() {
        return webDriver.get();
    }

    private static WebDriver determineEffectiveDriverType(String browserType) {
        DriverType driverType;
        try {
            driverType = DriverType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ignored) {
            logger.error("An error has been occurred in  [" + ignored.getStackTrace()[1].getClassName() + "." + ignored.getStackTrace()[1].getMethodName() + "()]. Line No: "
                    + ignored.getStackTrace()[0].getLineNumber() + " And Exception is: " + ignored.getClass().getName());
            driverType = DriverType.CHROME;
        }
        return driverType.getWebDriverInstance();
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
        }
    }
}
