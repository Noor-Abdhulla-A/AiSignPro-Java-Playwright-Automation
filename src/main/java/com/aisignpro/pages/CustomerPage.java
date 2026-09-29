package com.aisignpro.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CustomerPage {

    private final Page page;

    // Locators
    private final Locator addCustomerButton;
    private final Locator contactName;
    private final Locator emailId;
    private final Locator mobileNumber;
    private final Locator assignButton;
    private final Locator okButton;

    public CustomerPage(Page page) {
        this.page = page;
        
        addCustomerButton = page.locator("#addRow");
        contactName = page.locator("[formcontrolname='contactName']");
        emailId = page.locator("[formcontrolname='emailid']");
        mobileNumber = page.locator("[formcontrolname='mobileNumber']");
        assignButton = page.locator("#assign-btn");
        okButton = page.locator(
        	    "div.swal2-popup:visible button.swal2-confirm"
        	);

    }

    // Actions

    public void clickAddCustomer() {
        addCustomerButton.click();
    }
    
    public void enterContactName(String name) {
        contactName.fill(name);
    }
    
    public void enterEmailId(String email) {
        emailId.fill(email);
    }
    
    public void enterMobileNumber(String number) {
        mobileNumber.fill(number);
    }
    
    public void clickAssign() {
        assignButton.click();
    }
    public void clickOk() {
        okButton.click();
    }
}
