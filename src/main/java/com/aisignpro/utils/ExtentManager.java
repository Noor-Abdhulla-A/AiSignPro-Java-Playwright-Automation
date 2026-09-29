package com.aisignpro.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(
                            "test-output/ExtentReport.html"
                    );

            spark.config().setDocumentTitle("AiSignPro Automation Report");
            spark.config().setReportName("AiSignPro Test Automation");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Application", "AiSignPro");
            extent.setSystemInfo("Framework", "Playwright + TestNG");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Browser", "Chromium");
        }

        return extent;
    }
}