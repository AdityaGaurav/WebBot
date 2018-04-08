package com.aut.listener;

import framework.driverfactory.WebDriverThread;
import framework.reporting.ExtentReportPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener, ISuiteListener, IClassListener, IInvokedMethodListener {

    private static Logger logger = LogManager.getLogger(TestListener.class);

    private static ExtentReportPlugin extentReportPlugin;
    private static ThreadLocal<ExtentReportPlugin> extentReportPluginThreadLocal = new ThreadLocal<>();

    interface Flag {
        void setParallelFlag(boolean parallelFlag);

        boolean getDriverFlag();
    }

    enum ParallelFlag implements Flag {
        TESTS {
            boolean flag;

            public void setParallelFlag(boolean parallelFlag) {
                flag = parallelFlag;
            }

            @Override
            public boolean getDriverFlag() {
                return flag;
            }
        }, METHODS {
            boolean flag;

            public void setParallelFlag(boolean parallelFlag) {
                flag = parallelFlag;
            }

            @Override
            public boolean getDriverFlag() {
                return flag;
            }
        }, CLASSES {
            boolean flag;

            public void setParallelFlag(boolean parallelFlag) {

                flag = parallelFlag;
            }

            @Override
            public boolean getDriverFlag() {

                return flag;
            }
        }, NONE {
            boolean flag;

            @Override
            public void setParallelFlag(boolean parallelFlag) {
                flag = parallelFlag;
            }

            @Override
            public boolean getDriverFlag() {

                return flag;
            }
        }
    }

    //###################################### ISuiteListener #######################################

    @Override
    public void onStart(ISuite suite) {
        extentReportPlugin = new ExtentReportPlugin();
        extentReportPluginThreadLocal.set(extentReportPlugin);
        logger.info("System and environment info: OS: " + System.getProperty("os.name") + " OS version: " + System.getProperty("os.version") + " Java Version: " + System.getProperty("java.version"));
        logger.info("Execution start of the suite '" + suite.getName() + "'");
        extentReportPluginThreadLocal.get().setUPReportingConfiguration(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Execution completed of the suite '" + suite.getName() + "'");
        extentReportPlugin.cleanUp();
    }

    //###################################### ITestListener #######################################
    @Override
    public void onTestStart(ITestResult result) {
        extentReportPluginThreadLocal.get().registerTestMethod(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentReportPluginThreadLocal.get().captureTestResultAfterSuccess(result);
        logger.info("Test " + result.getName() + " passed. Total execution time: " + TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis()) + " seconds.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            extentReportPluginThreadLocal.get().captureTestResultAfterFailure(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentReportPluginThreadLocal.get().captureTestAfterSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Execution start of the test '" + context.getCurrentXmlTest().getName() + "'");

        String suiteParallel = context.getSuite().getXmlSuite().getParallel().toString();
        String testParallel = context.getCurrentXmlTest().getParallel().toString();
        String parallel;
        if (!suiteParallel.equalsIgnoreCase("none")) {
            if (testParallel.equalsIgnoreCase("none")) {
                parallel = testParallel;
            } else {
                parallel = testParallel;
            }
        } else {
            if (!testParallel.equalsIgnoreCase("none")) {
                parallel = testParallel;
            } else {
                parallel = suiteParallel;
            }
        }

//        if (testParallel != null || !testParallel.trim().equalsIgnoreCase("")) {
        // boolean parallelFlag = false;
        ParallelFlag.valueOf(parallel.toUpperCase()).setParallelFlag(true);
//        }
        String browserName = context.getCurrentXmlTest().getParameter("browser");
        String host = context.getCurrentXmlTest().getParameter("host");
        if (ParallelFlag.valueOf("TESTS").getDriverFlag()) {
            try {
                instantiateWebDriver(browserName, host);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Execution completed of the test '" + context.getCurrentXmlTest().getName() + "'");
        if (ParallelFlag.valueOf("TESTS").getDriverFlag()) {
            try {
                WebDriverThread.quitDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //###################################### IClassListener #######################################
    @Override
    public void onBeforeClass(ITestClass testClass) {
        logger.info("Execution start of the class '" + testClass.getXmlTest().getName() + "'");
        String browserName = testClass.getXmlClass().getAllParameters().get("browser");
        String host = testClass.getXmlClass().getAllParameters().get("host");
        if (ParallelFlag.valueOf("CLASSES").getDriverFlag()) {
            try {
                instantiateWebDriver(browserName, host);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        logger.info("Execution completed of the class '" + testClass.getXmlTest().getName() + "'");
        if (ParallelFlag.valueOf("CLASSES").getDriverFlag()) {
            try {
                WebDriverThread.quitDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //###################################### InvokedMethodListener #######################################
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

        logger.info("Execution start of the test method '" + method.getTestMethod().getMethodName() + "'");
//        String methodName = method.getTestMethod().getMethodName();
//        String classContainsMethod = method.getTestMethod().getTestClass().getName();
        String browserName = null;
        String host = null;
        for (XmlClass s : method.getTestMethod().getXmlTest().getXmlClasses()) {
            for (XmlInclude includeMethod : s.getIncludedMethods()) {
                if (includeMethod.getName().equalsIgnoreCase(method.getTestMethod().getMethodName())) {
                    browserName = s.getAllParameters().get("browser");
                    host = s.getAllParameters().get("host");
                    break;
                }
            }
        }

        if (ParallelFlag.valueOf("METHODS").getDriverFlag() || ParallelFlag.valueOf("NONE").getDriverFlag()) {
            try {
                instantiateWebDriver(browserName, host);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        logger.info("Execution completed of the test method '" + method.getTestMethod().getMethodName() + "'");
        if (ParallelFlag.valueOf("METHODS").getDriverFlag() || ParallelFlag.valueOf("NONE").getDriverFlag()) {
            try {
                WebDriverThread.quitDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void instantiateWebDriver(String browser, String host) throws Exception {
        WebDriverThread.setDriver(browser, host);
    }
}
