package framework.reporting;

public class ReportPlugin {
    private static ThreadLocal<ExtentReportPlugin> extentReport = new ThreadLocal<>();
    private static ExtentReportPlugin extentReportPlugin;


    public ExtentReportPlugin getReportPlugin() {
        return extentReport.get();
    }

    public static void setReportPlugin() {
        extentReportPlugin = new ExtentReportPlugin();
        extentReport.set(extentReportPlugin);
    }

}
