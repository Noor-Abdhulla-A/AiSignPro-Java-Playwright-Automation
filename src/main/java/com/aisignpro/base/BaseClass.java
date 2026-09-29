package com.aisignpro.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseClass {

	protected Playwright playwright;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;

	@BeforeMethod
	public void setUp() {

		playwright = Playwright.create();

		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		context = browser.newContext();

		page = context.newPage();

		page.setDefaultTimeout(15000);
	}
	
	public Page getPage() {
		return page;
	}

	@AfterMethod
	public void tearDown() {

		if (context != null) {
			context.close();
		}

		if (browser != null) {
			browser.close();
		}

		if (playwright != null) {
			playwright.close();
		}
	}
}