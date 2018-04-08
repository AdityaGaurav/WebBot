package framework.uievent;

import com.google.common.base.Preconditions;
import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class WindowHandler {

    public static String getWindowHandleName(WebDriver driver){
        PreConditionCheck.checkNotNull(driver,"Driver instance should not be null");
        return driver.getWindowHandle();
    }

    public static Set<String> getWindowHandles(WebDriver driver){
        PreConditionCheck.checkNotNull(driver,"Driver instance should not be null");
        return driver.getWindowHandles();
    }

    public static void getExpectedWindowHandler(WebDriver driver, String url, WebElement element){
    }

    public static void closeExpectedWindow(WebDriver driver){

    }
}
