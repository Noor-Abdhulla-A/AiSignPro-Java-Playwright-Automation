package com.aisignpro.listeners;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aisignpro.base.BaseClass;
import com.aisignpro.utils.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

public class ExtentListeners implements ITestListener {

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	private static ThreadLocal<Page> page = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest test = ExtentManager.getExtentReports().createTest(result.getMethod().getMethodName());

		extentTest.set(test);

		Object testInstance = result.getInstance();

		if (testInstance instanceof BaseClass) {

			Page currentPage = ((BaseClass) testInstance).getPage();

			page.set(currentPage);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail("Test Failed");

		if (result.getThrowable() != null) {
			extentTest.get().fail(result.getThrowable());
		}

		captureScreenshot(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.get().skip("Test Skipped");
	}

	private void captureScreenshot(ITestResult result) {

		try {

			Page currentPage = page.get();

			if (currentPage == null) {
				return;
			}

			Path screenshotFolder = Paths.get("test-output", "screenshots");

			Files.createDirectories(screenshotFolder);

			String fileName = result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";

			Path screenshotPath = screenshotFolder.resolve(fileName);

			currentPage.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));

			extentTest.get().addScreenCaptureFromPath(screenshotPath.toString());

		} catch (Exception e) {

			extentTest.get().warning("Screenshot could not be captured: " + e.getMessage());
		}
	}

	public static ExtentTest getTest() {

		return extentTest.get();
	}
}