package framework.customwaits;

import framework.uievent.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class CustomWaitConditions {

    /**
     *
     * @param inputElement WebElement instance
     * @return <WebElement> The return type
     */
    public static Function<WebElement, WebElement> waitForTheElementTillItVisible(WebElement inputElement) {
        Objects.requireNonNull(inputElement);
        return (WebElement element) -> {
            return inputElement;
        };
    }

    /**
     *
     * @param loc
     * @return
     */
    public static Function<WebDriver, WebElement> waitForTheElementTillItVisible(By loc) {
        Objects.requireNonNull(loc);
        return (WebDriver webDriver) -> {
            return  webDriver.findElement(loc);
        };
    }

    /**
     *
     * @param loc
     * @return
     */
    public static ExpectedCondition<List<WebElement>> waitForAllElementsToVisible(By loc) {
        return ExpectedConditions.presenceOfAllElementsLocatedBy(loc);
    }

    public static ExpectedCondition<WebElement> isAllElementDisplayed(By locator){
        return ExpectedConditions.presenceOfElementLocated(locator);
    }
}
