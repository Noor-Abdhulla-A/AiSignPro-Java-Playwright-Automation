package com.aisignpro.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;

public class AssignAgreementPage {

    private final Page page;

    private final Locator documentsPanel;
    private final Locator firstDocument;
    private final Locator signature;
    private final Locator documentArea;
    private final Locator sendMailButton;
   // private final Locator continueToSendMailButton;


    public AssignAgreementPage(Page page) {

        this.page = page;

        documentsPanel =
            page.locator("[title='Show documents']");
        
        firstDocument =
                page.locator(".aa-doc-list .aa-doc-item").first();
        
        signature = page.locator(
                "div.fpe-chip[draggable='true'][title='Signature']"
        );
        
        documentArea = page.locator(".fpe-place-zone").first();
        
        sendMailButton = page.locator(
        	    ".aa-topbar button.btn-primary"
        	).filter(
        	    new Locator.FilterOptions().setHasText("Send Mail")
        	);
        
        
        
    }
    
    

    public void clickDocuments() {
        documentsPanel.click();
    }
    
    public void clickFirstDocument() {

        firstDocument.click();

        documentArea.waitFor(
            new Locator.WaitForOptions().setTimeout(30000)
        );

        signature.waitFor(
            new Locator.WaitForOptions().setTimeout(30000)
        );
    }
    
    public void dragSignatureToDocument() {

        // Get the center of the signature
        BoundingBox source = signature.boundingBox();

        // Get the center of the document area
        BoundingBox target = documentArea.boundingBox();

        double sourceX = source.x + source.width / 2;
        double sourceY = source.y + source.height / 2;

        double targetX = target.x + target.width / 2;
        double targetY = target.y + 200;   // place near top of document

        page.mouse().move(sourceX, sourceY);

        page.mouse().down();

        page.mouse().move(
            targetX,
            targetY,
            new Mouse.MoveOptions().setSteps(20)
        );

        page.mouse().up();
    }
    
    public void clickSendMail() {
        sendMailButton.click();
    }
    
    public void clickSendAnyway() {

        Locator popup = page.locator(".swal2-popup");

        popup.waitFor();

        Locator sendAnyway = popup.locator(
            "button.swal2-confirm"
        );

        sendAnyway.waitFor();

        sendAnyway.click();
    }
    
    public void clickContinueToSendMail() {

        Locator continueButton = page.locator("button")
                .filter(new Locator.FilterOptions()
                .setHasText("Continue to Send Mail"));

        continueButton.waitFor();
        continueButton.click();
    }
    
    public void clickContinueAssignment() {

        Locator popup = page.locator(".swal2-popup");

        popup.waitFor();

        Locator continueButton =
                popup.locator("button.swal2-confirm");

        continueButton.waitFor();
        continueButton.click();
    }
}
