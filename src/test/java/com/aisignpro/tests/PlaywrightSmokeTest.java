package com.aisignpro.tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightSmokeTest {

	@Test
	public void verifyPlaywright() {

		try (Playwright playwright = Playwright.create()) {

			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			Page page = browser.newPage();

			page.navigate("https://cpastaging.aisignpro.com/");

			System.out.println("Page Title: " + page.title());
			System.out.println("Current URL: " + page.url());

			browser.close();
		}
	}
}