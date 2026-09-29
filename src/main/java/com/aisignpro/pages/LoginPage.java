package com.aisignpro.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

	private Page page;

    // Login locators
    private final Locator mobileNumber;
    private final Locator termsCheckbox;
    private final Locator signInButton;

    
    // OTP
    //private Locator otp1;
    private Locator otp1;
    private Locator otp2;
    private Locator otp3;
    private Locator otp4;
    private Locator otp5;
    private Locator otp6;
    private Locator verifyButton;
    


    public LoginPage(Page page) {

        this.page = page;

        mobileNumber =
                page.getByPlaceholder("Enter the mobile number");

        termsCheckbox =
                page.locator("[formcontrolname='termsAndConditions']");

        signInButton = page.getByRole(
        	    AriaRole.BUTTON,
        	    new Page.GetByRoleOptions().setName("Sign in")
        	);

        // OTP

        otp1 = page.locator("input[name='otp1']");
        otp2 = page.locator("input[name='otp2']");
        otp3 = page.locator("input[name='otp3']");
        otp4 = page.locator("input[name='otp4']");
        otp5 = page.locator("input[name='otp5']");
        otp6 = page.locator("input[name='otp6']");

        verifyButton = page.locator("button.verify-button");
    }


    // Login Actions

    public void open() {
        page.navigate("https://cpastaging.aisignpro.com/");
    }

    public void enterMobileNumber(String number) {
        mobileNumber.fill(number);
    }

    public void acceptTerms() {
        termsCheckbox.check();
    }

    public void clickSignIn() {
        signInButton.click();
    }
    
    

    // OTP Actions
    public void enterOTP(String otp) {
        otp1.fill(String.valueOf(otp.charAt(0)));
        otp2.fill(String.valueOf(otp.charAt(1)));
        otp3.fill(String.valueOf(otp.charAt(2)));
        otp4.fill(String.valueOf(otp.charAt(3)));
        otp5.fill(String.valueOf(otp.charAt(4)));
        otp6.fill(String.valueOf(otp.charAt(5)));
    }

    public void clickVerify() {
        verifyButton.click();
    }
}