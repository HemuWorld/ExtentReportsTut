package org.hemuWorld.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Use to Set up Basics Configurations of HTML Report Or
 * U can Say Create a Basics Report with required features.
 **/
public class ExtentManager {

    static ExtentSparkReporter spark;
    static ExtentReports extent;

    public static ExtentReports createExtentReportInstance(String fileName) {
        spark = new ExtentSparkReporter(fileName);

        /*  HTML VIEW Configuration  */
        spark.config()
                .setTheme(Theme.STANDARD);
        spark.config()
                .setDocumentTitle("My Extent Report");
        spark.config()
                .setEncoding("utf-8");
        spark.config()
                .setReportName("My Report Name");

        /*  HTML Extra System Information Setting  */
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Automation Tester",
                "Hitesh Paliwal");
        extent.setSystemInfo("Organization",
                "Searching");
        return extent;
    }


}
