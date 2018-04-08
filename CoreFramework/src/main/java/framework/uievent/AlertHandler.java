package framework.uievent;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class AlertHandler {
    private AlertHandler(){

    }
    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public void getTextOfAlert(WebDriver driver) {
        driver.switchTo().alert().getText();
    }

    public void sendTestToAlert(WebDriver driver,String text) {
        Objects.requireNonNull(text);
        driver.switchTo().alert().sendKeys(text);
    }
}
