package framework.uievent;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    private Screenshot() {

    }

    public static String takeScreenShot(WebDriver driver, String screenShotName) throws IOException {
      //  String fileSeparator = File.separator;
        String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String fileName =screenShotName + currentDate;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenShotFileType = screenshot.getScreenshotAs(OutputType.FILE);
//        String screenShotFileLocation = System.getProperty("user.dir") + Constants.FILE_SEPARATOR + "ScreenShots" +
//                Constants.FILE_SEPARATOR + screenShotName + currentDate + ".png".replaceAll("\\\\", Constants.FILE_SEPARATOR);
        String screenShotFileLocation = "C:\\Users\\adityag\\Documents\\WebServer\\"+ fileName + ".png";
        File finalDestination = new File(screenShotFileLocation);
        FileUtils.copyFile(screenShotFileType, finalDestination);
        return "http://127.0.0.1:8887/"+fileName+".png";
    }
}
