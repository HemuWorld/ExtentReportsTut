package org.hemuWorld.utilities;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.hemuWorld.TestExecutionEngine;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Utility {

    public static String screenshotName;

    public static void captureScreenshot() {
        File scrFile = ((TakesScreenshot) TestExecutionEngine.driver).getScreenshotAs(OutputType.FILE);
        screenshotName = (new Date()).toString()
                .replace(":",
                        "_")
                .replace(" ",
                        "_") + ".jpg";
        try {
            FileHandler.copy(scrFile,
                    new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Markup getMarkup(String logText,
                                   String status) {
        Markup markup;
        switch (status) {
            case "Skip":
                markup = MarkupHelper.createLabel(logText,
                        ExtentColor.RED);
                break;
            case "Fail":
                markup = MarkupHelper.createLabel(logText,
                        ExtentColor.YELLOW);
                break;
            default:
                markup = MarkupHelper.createLabel(logText,
                        ExtentColor.AMBER);
        }
        return markup;
    }

    public static String getMethodName(ITestResult result) {
        String methodName = result.getMethod()
                .getMethodName()
                .toUpperCase();
        return methodName;
    }


}
