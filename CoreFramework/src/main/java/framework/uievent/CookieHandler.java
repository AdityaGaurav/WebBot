package framework.uievent;

import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.Set;

public class CookieHandler {
    CookieHandler() {
        //threadLocal.set(webDriverThreadLocal.get());
    }

    public static void addCookie(Cookie cookie, WebDriver driver) {
        PreConditionCheck.checkNotNull(cookie,"Cookie instance is null");
        PreConditionCheck.checkNotNull(driver,"Driver instance should not be null");
        driver.manage().addCookie(cookie);
    }

    public static Cookie getCookieByName(String nameOfCookie, WebDriver driver) {
        Objects.requireNonNull(nameOfCookie);
        return driver.manage().getCookieNamed(nameOfCookie);
    }

    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void deleteCookie(Cookie cookie, WebDriver driver) {
        Objects.requireNonNull(cookie);
        driver.manage().deleteCookie(cookie);
    }

    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public static void deleteCookieByName(WebDriver driver,String name) {
        Objects.requireNonNull(name);
        driver.manage().deleteCookieNamed(name);
    }

    public static String getCookieInfo(Cookie cookie, String cookieName){
        PreConditionCheck.checkNotNull(cookie,"Input should not be null");
        return cookie.getValue();
    }
    public static String getNamedCookieIDValue(String nameOfCookie, WebDriver driver) {
        PreConditionCheck.checkNotNullNotBlankOrEmpty(nameOfCookie,"Cookie can not be null");
        PreConditionCheck.checkNotNull(driver,"Driver instance can not be null");
        Cookie cookie = CookieHandler.getCookieByName(nameOfCookie, driver);
        return cookie.getValue();
    }
}
