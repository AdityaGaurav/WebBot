package framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import framework.driverfactory.WebDriverThread;
import framework.uievent.Constants;
import framework.uievent.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ExtentReportPlugin {

    private static ExtentReports extentReport;
    private static ExtentTest extentTest;
    private static ExtentHtmlReporter extentHtmlReporter;

    private static final String extentReportConfigFileLocation=
            "src\\test\\testResource\\extentreport-config.xml".replace("\\\\",Constants.FILE_SEPARATOR);


    private void setReportName(String reportName) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
        String reportFileName = new File(System.getProperty("user.dir")) + Constants.FILE_SEPARATOR + "Reports" + Constants.FILE_SEPARATOR + reportName.replaceAll(" ","")+ currentDate + ".html";
        extentHtmlReporter = new ExtentHtmlReporter(reportFileName);
    }

    private void loadExtentReportConfiguration(String extentReportConfigFileLocation) {
        String extentConfigFile = new File(System.getProperty("user.dir")) + Constants.FILE_SEPARATOR + extentReportConfigFileLocation.replace("\\", Constants.FILE_SEPARATOR);
        extentHtmlReporter.loadConfig(extentConfigFile);
    }

//    private void prepareReporter(Map<String, String> systemInfoToPrintOnReport) {
//        extentReport = new ExtentReports();
//        if (systemInfoToPrintOnReport != null) {
//            if (systemInfoToPrintOnReport.size() > 0) {
//                for (Map.Entry<String, String> systemInfo : systemInfoToPrintOnReport.entrySet()) {
//                    extentReport.setSystemInfo(systemInfo.getKey(), systemInfo.getValue());
//                }
//            }
//        }
//        extentReport.attachReporter(extentHtmlReporter);
//    }

    /**
     * Plug this method in @BeforeSuite of TestNG
     * @param suite
     */
    public void setUPReportingConfiguration(ISuite suite) {
        String suiteName = suite.getName();
        setReportName(suiteName);
        loadExtentReportConfiguration(extentReportConfigFileLocation);
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentHtmlReporter);
        //prepareReporter(systemInfoToPrintOnReport);
    }

    /**
     * Plug this method in @BeforeMethod of TestNG
     * @param result
     */
    public  synchronized void registerTestMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        extentTest = extentReport.createTest(testName);
    }

    public synchronized void captureTestResultAfterSuccess(ITestResult testResult) {
        extentTest.log(Status.PASS, "The Test: " + testResult.getName() + "() passed");
        extentTest.log(Status.PASS, "Total Execution time " + TimeUnit.MILLISECONDS.toSeconds(testResult.getEndMillis() - testResult.getStartMillis()));
    }

    public synchronized void captureTestResultAfterFailure(ITestResult testResult) throws IOException {
        captureTestResultAfterFail(testResult);
    }

    public synchronized void captureTestAfterSkipped(ITestResult testResult) {
        extentTest.log(Status.SKIP, "The Test: " + testResult.getName() + " skipped");
    }

    private synchronized void captureTestResultAfterFail(ITestResult testResult) throws IOException {
        extentTest.log(Status.FAIL, "The Test: " + testResult.getName() + " failed");
        extentTest.log(Status.FAIL, "Exception occurred:" + testResult.getThrowable());
        extentTest.log(Status.PASS, "Total Execution time " + (testResult.getEndMillis() - testResult.getStartMillis()));
        String screenshot = getLocationOfScreenShotOfFailedTest(WebDriverThread.getDriver(),testResult);
        extentTest.addScreenCaptureFromPath(screenshot);
    }

    public void cleanUp() {
        //close the extent report connection
        extentReport.flush();
    }

    private String getLocationOfScreenShotOfFailedTest(WebDriver driver, ITestResult testResult) throws IOException {
        return Screenshot.takeScreenShot(driver, testResult.getName());
    }
}
