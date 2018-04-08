package framework;

public interface Defaults {
    String WEBDRIVER_ERROR_MSG = "WebDriver instance can not be null";
    String ERROR_MSG = "parameter can not be null";

    //Driver Location
    String DRIVER_LOCATION = "\\src\\main\\resources";

    //Windows Driver
    String WINDOWS = "\\windowsdriver";
    String WINDOWS_EXTENSION = ".exe";

    //MAC Driver
    String MAC = "\\macdriver";
    String MAC_EXTENSION = ".dmg";

    //Driver name
    String CHROME_DRIVER = "chromedriver";
    String FIREFOX_DRIVER = "geckodriver";
    String IE_DRIVER = "IEDriverServer";
    String EDGE_DRIVER ="MicrosoftWebDriver";

    //Driver Wait TimeOut
    long DEFAULT_TIME_OUT = 10L;
    long DEFAULT_POLLING_TIME = 500L;


}
