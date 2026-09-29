package com.aisignpro.tests;


import org.testng.Assert;
import com.aisignpro.utils.ExtentManager;


import org.testng.annotations.Test;

import com.aisignpro.base.BaseClass;
import com.aisignpro.pages.AssignAgreementPage;
import com.aisignpro.pages.CustomerPage;
import com.aisignpro.pages.DashboardPage;
import com.aisignpro.pages.LoginPage;
import com.aisignpro.testdata.TestData;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import com.aisignpro.listeners.ExtentListeners;

@Listeners(ExtentListeners.class)
public class AISignProTest extends BaseClass {

	
	// START EXTENT REPORT



	@Test
	public void login() {

		LoginPage loginPage = new LoginPage(page);

		loginPage.open();

		loginPage.enterMobileNumber("6664446664");

		loginPage.acceptTerms();

		loginPage.clickSignIn();

		// CAPTCHA
		// Manually solve CAPTCHA here
		page.pause();

		// OTP
		loginPage.enterOTP("123456");

		loginPage.clickVerify();

	}

	@Test
	public void loginTest() {

		login();

		Assert.assertTrue(page.getByText("Documents").isVisible(), "Dashboard was not displayed");

	}

	@Test(dataProvider = "customerData", dataProviderClass = TestData.class)
	public void addCustomerTest(String contactName, String email, String mobileNumber) {

		ExtentListeners.getTest().info("Starting Add Customer end-to-end test");

		login();

		ExtentListeners.getTest().pass("Login completed");

		// DASHBOARD / CUSTOMER

		DashboardPage dashboardPage = new DashboardPage(page);
		dashboardPage.clickCustomers();

		CustomerPage customerPage = new CustomerPage(page);

		// ADD CUSTOMER

		customerPage.clickAddCustomer();

		customerPage.enterContactName(contactName);

		customerPage.enterEmailId(email);

		customerPage.enterMobileNumber(mobileNumber);

		customerPage.clickAssign();

		customerPage.clickOk();

		ExtentListeners.getTest().pass("Customer created successfully");

		// ASSIGN AGREEMENTS

		AssignAgreementPage assignAgreementPage = new AssignAgreementPage(page);

		// Verify Assign Agreements page
		Assert.assertTrue(page.locator("app-assign-agreements .aa-title").isVisible(),
				"Assign Agreements page was not displayed");

		ExtentListeners.getTest().pass("Assign Agreements page opened");

		// DOCUMENTS

		assignAgreementPage.clickDocuments();

		assignAgreementPage.clickFirstDocument();

		ExtentListeners.getTest().pass("First document selected");

		// DRAG SIGNATURE

		assignAgreementPage.dragSignatureToDocument();

		ExtentListeners.getTest().pass("Signature dragged to document");

		// SEND MAIL

		assignAgreementPage.clickSendMail();

		ExtentListeners.getTest().pass("Send Mail clicked");

		// SEND ANYWAYS

		assignAgreementPage.clickSendAnyway();

		ExtentListeners.getTest().pass("Send anyways confirmation completed");

		// CONTINUE TO SEND MAIL

		assignAgreementPage.clickContinueToSendMail();

		ExtentListeners.getTest().pass("Continue to Send Mail completed");

		// ASSIGNMENT OPTIONS → CONTINUE

		assignAgreementPage.clickContinueAssignment();

		ExtentListeners.getTest().pass("Assignment Options Continue completed");

		Locator successMessage = page.locator(".swal2-popup:visible");

		successMessage.waitFor(new Locator.WaitForOptions().setTimeout(30000));

		successMessage.locator("text=Sending mail…").waitFor(new Locator.WaitForOptions().setTimeout(30000));

		System.out.println("Sending mail message displayed.");

		page.waitForFunction(
				"() => document.querySelector('.swal2-popup.swal2-show') "
						+ "&& document.querySelector('.swal2-popup.swal2-show').innerText.includes('Sent')",
				null, new Page.WaitForFunctionOptions().setTimeout(10000));

		// FINAL SUCCESS VERIFICATION

		String finalMessage = page.locator(".swal2-popup:visible").innerText();

		System.out.println("FINAL POPUP:");
		System.out.println(finalMessage);

		Assert.assertTrue(finalMessage.contains("Sent"), "Expected success message 'Sent' but found: " + finalMessage);

		// Click QA Automation profile
		page.getByText("QA Automation", new Page.GetByTextOptions().setExact(true)).click();

		// Click Sign out
		Locator signOut = page.getByText("Sign out", new Page.GetByTextOptions().setExact(true));

		signOut.waitFor(new Locator.WaitForOptions().setTimeout(10000));

		signOut.click();

		// Verify logout page
		Locator welcomeMessage = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName("Welcome to AiSignPro"));

		welcomeMessage.waitFor(new Locator.WaitForOptions().setTimeout(10000));

		Assert.assertTrue(welcomeMessage.isVisible(), "Expected 'Welcome to AiSignPro' after logout");
		

	}
	
	@AfterSuite
	public void flushReport() {
	    ExtentManager.getExtentReports().flush();
	}

}