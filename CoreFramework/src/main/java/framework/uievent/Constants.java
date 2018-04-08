package framework.uievent;

public interface Constants {
    long DEFAULT_IMPLICIT_TIME_OUT = 10L;
    long DEFAULT_PAGE_LOAD_TIME_OUT = 10L;
    long DEFAULT_TIME_TO_FOR_ELEMENT = 4L;

    //Driver Wait TimeOut : Fluent/ Explicit
    long DEFAULT_TIME_OUT = 10L;
    long DEFAULT_POLLING_TIME = 500L;

    String WEB_DRIVER_ERROR_MSG = "WebDriver instance can not be null";
    String ERROR_MSG = "parameter can not be null";

    String USER_DIRECTORY = System.getProperty("user.dir");
    String FILE_SEPARATOR = System.getProperty("file.separator");
    String OS = System.getProperty("os.name");
    //Driver Location
    String DRIVER_LOCATION = "src" + FILE_SEPARATOR + "main" + FILE_SEPARATOR + "resources";

    //Windows Driver
    String CHROME_DRIVER_LOCATION = "chromeDriver";
    String FIREFOX_DRIVER_LOCATION = "fireFoxDriver";
    String WINDOWS_EXTENSION = ".exe";

    //MAC Driver
    String MAC_EXTENSION = ".dmg";

    //Driver name
    String CHROME_DRIVER = "chromedriver";
    String FIREFOX_DRIVER = "geckodriver";
    String IE_DRIVER = "IEDriverServer";
    String EDGE_DRIVER = "MicrosoftWebDriver";

    static String getOSSpecificExtension() {
        if (OS.toUpperCase().contains("WINDOWS")) {
            return WINDOWS_EXTENSION;
        } else if (OS.toUpperCase().contains("MAC")) {
            return MAC_EXTENSION;
        } else {
            throw new IllegalArgumentException("Your os is not listed here. Please add" + OS + " here");
        }
    }
}
