package framework.driverfactory;

import framework.uievent.Constants;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LoggingPreferences;

public enum DriverType implements DriverSetup {

    FIREFOX {
        private FirefoxProfile setFireFoxCapabilities() {
            String binaryLocation = Constants.USER_DIRECTORY + Constants.FILE_SEPARATOR + Constants.DRIVER_LOCATION + Constants.FILE_SEPARATOR +
                    Constants.FIREFOX_DRIVER_LOCATION + Constants.FILE_SEPARATOR + Constants.FIREFOX_DRIVER+ Constants.getOSSpecificExtension();
            System.setProperty("webdriver.gecko.driver", binaryLocation);
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("webdriver.gecko.driver", "DriverAttributes.FIRE_FOX_BINARY_PATH");
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            profile.setPreference("dom.webnotifications.enabled", false);
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.download.panel.shown", false);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/anytext ,text/plain,text/html,application/plain");
            profile.setPreference("browser.download.defaultFolder", "C:\\Downloads");
            profile.setPreference("browser.download.dir", "C:\\Downloads");
            profile.setPreference("pdfjs.disabled", true);
            return profile;
        }

        @Override
        public WebDriver getWebDriverInstance() {
            FirefoxOptions fireFirefoxOptions = new FirefoxOptions().setProfile(setFireFoxCapabilities());
            return new FirefoxDriver(fireFirefoxOptions);
        }

    },

    CHROME {
        @Override
        public WebDriver getWebDriverInstance() {
            return new ChromeDriver(setChromeCapabilities());
        }

        private ChromeOptions setChromeCapabilities() {
            String binaryLocation = Constants.USER_DIRECTORY + Constants.FILE_SEPARATOR + Constants.DRIVER_LOCATION + Constants.FILE_SEPARATOR +
                    Constants.CHROME_DRIVER_LOCATION + Constants.FILE_SEPARATOR + Constants.CHROME_DRIVER +Constants.getOSSpecificExtension();
            System.setProperty("webdriver.chrome.driver", binaryLocation);
            System.setProperty("webdriver.chrome.args", "--disable-logging");
            System.setProperty("webdriver.chrome.silentOutput", "true");
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            options.addArguments("--start-maximized");
            //options.setBinary(binaryLocation);
            return options;
        }
    },
    REMOTE{
        @Override
        public WebDriver getWebDriverInstance() {
            return null;
        }

    }
}
