package org.hemuWorld.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.hemuWorld.utilities.Utility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Date;

import static org.hemuWorld.utilities.Utility.getMarkup;
import static org.hemuWorld.utilities.Utility.getMethodName;

/* The class will Listen all the behaviours of Test Class and it's methods*/
public class ExtentListeners implements ITestListener {

    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
    public static ExtentTest test;
    static String fileName = "Extent_" + (new Date()).toString()
            .replace(":", "_")
            .replace(" ", "_") + ".html";
    private static final ExtentReports extent =
            ExtentManager.createExtentReportInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + "@TestCase :" + getMethodName(result)).
                assignAuthor(System.getProperty("user.name")).
                assignDevice(System.getProperty("os.name") + " :" + System.getProperty("os.version"));
        testReport.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        Status status = Status.PASS;
        String logText = "<b>" + "TEST CASE:- " + getMethodName(result) + " PASSED" + "</b>";
        testReport.get()
                .log(status,
                        getMarkup(logText,
                                String.valueOf(status)));
    }

    public void onTestFailure(ITestResult result) {
        Status status = Status.FAIL;
        String logText = "TEST CASE FAILED";
        String exceptionMessage = Arrays.toString(result.getThrowable()
                .getStackTrace());
        testReport.get()
                .fail(
                        "<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occurred:Click to see" +
                                "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",",
                                "<br>") + "</details>" + " \n");
        Utility.captureScreenshot();
        testReport.get()
                .fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                        MediaEntityBuilder.createScreenCaptureFromPath(Utility.screenshotName)
                                .build());
        testReport.get()
                .log(status,
                        getMarkup(logText,
                                String.valueOf(status)));
    }

    public void onTestSkipped(ITestResult result) {
        Status status = Status.SKIP;
        String logText = "<b>" + "Test Case:- " + getMethodName(result) + " Skipped" + "</b>";
        testReport.get()
                .log(status,
                        getMarkup(logText,
                                String.valueOf(status)));

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

}
