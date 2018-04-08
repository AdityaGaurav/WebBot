package framework.uievent;

import framework.conditioncheck.PreConditionCheck;
import framework.customwaits.CustomFluentWait;
import framework.customwaits.CustomWaitConditions;
import org.openqa.selenium.*;

import java.util.List;

public class Element {

    /**
     * @param elementLocation       Locator of Element
     * @param driver                WebDriver Instance
     * @param timeOutInSeconds      The timeout duration
     * @param pollingInMilliSeconds The Polling duration
     * @return WebElement instance
     * @throws TimeoutException If the timeout expires
     */
    public static WebElement findWebElement(By elementLocation, WebDriver driver, long timeOutInSeconds, long pollingInMilliSeconds) throws Exception {
        PreConditionCheck.checkNotNull(elementLocation, "Locator can not be null");
        PreConditionCheck.checkNotNull(driver, "Driver can not be null");
        return findElement(elementLocation, driver, timeOutInSeconds, pollingInMilliSeconds);

    }

    public static WebElement findWebElement(By locatorOfElement, WebDriver driver) throws Exception {
        PreConditionCheck.checkNotNull(locatorOfElement, "Locator can not be null");
        PreConditionCheck.checkNotNull(driver, "Driver can not be null");
        return findElement(locatorOfElement, driver, 0, 0);
    }

    private static WebElement findElement(By locatorOfElement, WebDriver driver, long timeOutInSeconds, long pollingInMilliSeconds) throws Exception {
        WebElement element;
        try {
            element = driver.findElement(locatorOfElement);
        } catch (NoSuchElementException | TimeoutException e) {
            try {
                int size = driver.findElements(locatorOfElement).size();
                if (size > 1) {
                    throw new IllegalStateException("More than one element is available for the given locator");
                } else if (size == 0) {
                    throw new IllegalStateException("Expected element is not available for the given locator");
                } else {
                    throw new Exception("Not able to find element");
                }
            } catch (Exception e3) {
                element = enableWaitOnWebDriverToFindElement(locatorOfElement, driver, timeOutInSeconds, pollingInMilliSeconds);
            }
        } catch (StaleElementReferenceException e) {
            try {
                element = driver.findElement(locatorOfElement);
            } catch (Exception e1) {
                element = enableWaitOnWebDriverToFindElement(locatorOfElement, driver, timeOutInSeconds, pollingInMilliSeconds);
            }
        } catch (Exception e) {
            element = enableWaitOnWebDriverToFindElement(locatorOfElement, driver, timeOutInSeconds, pollingInMilliSeconds);
        }
        return element;
    }

    public static WebElement enableWaitOnWebDriverToFindElement(By locator, WebDriver driver, long timeOutInSeconds, long pollingInMilliSeconds) {
        CustomFluentWait<WebDriver> webDriverCustomFluentWait;
        if (timeOutInSeconds == 0 || pollingInMilliSeconds == 0) {
            webDriverCustomFluentWait = new CustomFluentWait<>(driver);
        } else {
            webDriverCustomFluentWait = new CustomFluentWait<>(driver, timeOutInSeconds, pollingInMilliSeconds);
        }
        return webDriverCustomFluentWait.waitForTheElementAsPerTheGivenCondition(
                CustomWaitConditions.waitForTheElementTillItVisible(locator));
    }

    /**
     * @param elementLocation       Locator of Element
     * @param driver                WebDriver Instance
     * @param timeOutInSeconds      The timeout duration
     * @param pollingInMilliSeconds The Polling duration
     * @return WebElement instance
     */
    public static List<WebElement> findWebElements(By elementLocation, WebDriver driver, long timeOutInSeconds, long pollingInMilliSeconds) {
        PreConditionCheck.checkNotNull(elementLocation, "Locator can not be null");
        PreConditionCheck.checkNotNull(driver, "Driver can not be null");
        return new CustomFluentWait<>(driver, timeOutInSeconds, pollingInMilliSeconds).waitForTheElementAsPerTheGivenCondition(CustomWaitConditions.waitForAllElementsToVisible(elementLocation));
    }

    public WebElement getActiveElement(WebDriver driver) {
        PreConditionCheck.checkNotNull(driver, "Driver can not be null");
        return driver.switchTo().activeElement();
    }

    public static boolean isElementDisplayed(By locator, WebDriver driver) {
        PreConditionCheck.checkNotNull(driver, "Driver onstance could not be null");
        CustomFluentWait<WebDriver> customFluentWait = new CustomFluentWait<>(driver);
        return customFluentWait.waitForTheElementAsPerTheGivenCondition(
                CustomWaitConditions.waitForTheElementTillItVisible(locator)).isDisplayed();
    }

    public static WebElement removeAttributeFromElement(WebElement element, WebDriver driver, String attributeName) {
        PreConditionCheck.checkNotNull(element, "Element can not be null");
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('" + attributeName + "')", element);
        return element;
    }

    public static String getValueOfElementByJS(WebElement element, WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return arguments[0].value", element).toString();
    }

    public static boolean isAttributePresent(WebElement element, String attributeName) {
        PreConditionCheck.checkNotNull(element, "Element can not be null");
        String attributeValue = element.getAttribute(attributeName);
        return attributeName != null;
    }

    public static String getElementAttributeValue(WebElement element, String attributeName) {
        PreConditionCheck.checkNotNull(element, "Element can not be null");
        return element.getAttribute(attributeName);
    }

    public static boolean isElementSelected(WebElement element) {
        PreConditionCheck.checkNotNull(element, "Element can to be null/empty.");
        return element.isSelected();
    }

    public static void isElementEnabled(WebElement element) {

    }

    public static boolean isElementDisplayed(WebElement element) {
        PreConditionCheck.checkNotNull(element, "Expected element instance is null.");
        return element.isDisplayed();
    }

    /**
     * Need to work on implementation part
     *
     * @param locatorOfElement
     * @param driver
     * @param timeout
     * @param pollingTime
     * @return
     * @throws Exception
     */
    public static boolean isElementDisplayed(By locatorOfElement, WebDriver driver, long timeout, long pollingTime) throws Exception {
        PreConditionCheck.checkNotNull(locatorOfElement, "Locator is null");
        PreConditionCheck.checkNotNull(driver, "Driver instance can not be null");
        WebElement expectedElement = findWebElement(locatorOfElement, driver, timeout, pollingTime);
        if (expectedElement != null) {
            return expectedElement.isDisplayed();
        }
        return expectedElement != null && expectedElement.isDisplayed();
    }

    public static String getTextOfElement(By locator, WebDriver driver, long timeOutInSeconds, long pollingInMilliseconds) throws Exception {
        PreConditionCheck.checkNotNull(locator, "Locator can not be null");
        PreConditionCheck.checkNotNull(driver, "Driver can not be null");
        WebElement element = findWebElement(locator, driver, timeOutInSeconds, pollingInMilliseconds);
        if (element != null) {
            return element.getText();
        } else {
            return null;
        }
    }

    public static String getTextOfElement(WebElement element, long timeOutInSeconds, long pollingInMilliseconds) {
        PreConditionCheck.checkNotNull(element, "Element can not be null");
        return element.getText();
    }

    public static String getTextOfReadOnlyElement(WebElement element, WebDriver driver, String attributeName) {
        WebElement webElement = removeAttributeFromElement(element, driver, attributeName);
        return Element.getElementAttributeValue(element, "datavalue");
    }

    public static int getSizeOfElement(WebElement element) {
        PreConditionCheck.checkNotNull(element, "Element can not be null");
        return element.getSize().getHeight();
    }

    public static void clickOnWebElement(WebElement element) {
        PreConditionCheck.checkNotNull(element, "Element instance should not be null");
        element.click();
    }

    public static void findAndClickOnElement(By locatorOfElement, WebDriver driver, long timeout, long pollingTime) throws Exception {
        WebElement element = Element.findWebElement(locatorOfElement, driver, 15, 500);
        element.click();
    }

    public static void senTextToElement(By locator, WebDriver driver, String inputText, long timeout, long pollingTime) throws Exception {
        PreConditionCheck.checkNotNull(locator, "Locator should not be empty");
        PreConditionCheck.checkNotNullNotBlankOrEmpty(inputText, "Text should not be null or empty");
        WebElement element = Element.findWebElement(locator, driver, timeout, pollingTime);
        element.clear();
        element.sendKeys(inputText);
    }
}
