package framework.uievent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar {
    public void clickOnDate(int d, String month, int year, WebDriver driver) {
        DateFormat dateFormat = new SimpleDateFormat("d-MMMM-yyyy");
        Date date = new Date();
        Date newdate = null;
        try {
            newdate = dateFormat.parse(d + "-" + month + "-" + year);
        } catch (ParseException e) {
            System.out.println("unable to parse date, please enter date format correctly in d-MMMM-yyyy format.");
        }
        System.out.println("current date: " + dateFormat.format(date));
        System.out.println("Leaving date: " + dateFormat.format(newdate));
        int noOfClicks = 0;
        if (newdate.compareTo(date) == 0) {
        } else if (newdate.compareTo(date) > 0) {
            noOfClicks = (newdate.getYear() - date.getYear()) * 12 + (newdate.getMonth() - date.getMonth());
        } else {
            System.out.println("error...date is before current date...");
        }
        System.out.println("hops: " + noOfClicks);
        for (int i = 0; i < noOfClicks; i++) {
            driver.findElement(By.xpath("//a[@class='ui-datepicker-next ui-corner-all']")).click();
        }
        driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//*[@data-handler='selectDay']//*[text()='" + d + "']")).click();
    }
}
