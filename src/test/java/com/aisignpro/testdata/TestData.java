package com.aisignpro.testdata;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "customerData")
    public static Object[][] customerData() {

        String uniqueId =
                String.valueOf(System.currentTimeMillis());

        String contactName = "Noor_" + uniqueId;

        String email =
                "customer_" + uniqueId + "@mailinator.com";

        // 10-digit test number
        String mobileNumber =
                "9" + uniqueId.substring(uniqueId.length() - 9);

        return new Object[][] {
            {
                contactName,
                email,
                mobileNumber
            }
        };
    }
}
