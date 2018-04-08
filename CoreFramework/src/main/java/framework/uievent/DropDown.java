package framework.uievent;

import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

    public static void selectByVisibleTextFromTheDropDown(By locatorOfDropDown, WebDriver driver, String valueToSelect, long timeout, long pollingTime) throws Exception {
        PreConditionCheck.checkNotNull(locatorOfDropDown,"Locator should not be null");
        PreConditionCheck.checkNotNull(driver, "Driver instance should not be null");
        PreConditionCheck.checkNotNullNotBlankOrEmpty(valueToSelect,"valueToSelect should not be null");
        WebElement dropDownElement = Element.findWebElement(locatorOfDropDown, driver,timeout,pollingTime);
        Select dropDown = new Select(dropDownElement);
        dropDown.selectByVisibleText(valueToSelect);
    }

    public static void selectByValueFromTheDropDown(By locatorOfDropDown, WebDriver driver, String valueToSelect, long timeout, long pollingTime) throws Exception {
        PreConditionCheck.checkNotNull(locatorOfDropDown,"Locator should not be null");
        PreConditionCheck.checkNotNull(driver, "Driver instance should not be null");
        PreConditionCheck.checkNotNullNotBlankOrEmpty(valueToSelect,"valueToSelect should not be null");
        WebElement dropDownElement = Element.findWebElement(locatorOfDropDown, driver,timeout,pollingTime);
        Select dropDown = new Select(dropDownElement);
        dropDown.selectByVisibleText(valueToSelect);
    }

    public boolean canSelectMultipleValue(WebElement element){
        Select multipleValue = new Select(element);
        return multipleValue.isMultiple();
    }
}
