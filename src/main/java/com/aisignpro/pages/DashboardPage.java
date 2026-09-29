package com.aisignpro.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DashboardPage {

    private final Page page;

    // Locators
    private final Locator customersTab;

    public DashboardPage(Page page) {

        this.page = page;

        customersTab = page.locator("a.nav-link[role='button']")
                .filter(new Locator.FilterOptions()
                .setHasText("Customers"));
    }

    // Actions

    public void clickCustomers() {
    	customersTab.click();
    } 
}