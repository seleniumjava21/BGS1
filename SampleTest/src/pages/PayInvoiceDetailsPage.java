package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.ThreadManager;

public class PayInvoiceDetailsPage extends BasePage {
	public PayInvoiceDetailsPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Guest");

		}
	}

	String InvoicePDFSite = "https://ebs-aventx-usw3-test.s3.amazonaws.com/";
	String iframeID = "xiFrameHosted";
	private ArrayList<String> countryWithoutStates = new ArrayList<String>(
			Arrays.asList("Germany", "France", "New Zealand", "Brazil"));

	@FindBy(xpath = "(//div[contains(@id,'header')]//a[contains(@href,'/my-account/invoices')])[1]")
	private WebElement LNKHdrPayInvoice;
	
	@FindBy(xpath = "//a[contains(@href,'/my-account/invoices?invoiceStatus=OP')]")
	private WebElement LNKOpenInvoiceTab;
	
	@FindBy(xpath = "//a[contains(@href,'/my-account/invoices?sortOrder=desc&invoiceStatus=CL')]")
	private WebElement LNKClosedInvoiceTab;
	
	@FindBy(xpath = "(//a[contains(@class,'btn-primary') and contains(@href,'/my-account/invoicesdetails?invoiceNumber')])[1]")
	private WebElement LNKPayOpenInvoice;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//*[contains(text(),'Invoice #')]")
	//@FindBy(xpath="//div[@class='col-md-12']//strong[contains(text(),'Invoice #')]")
	private WebElement LBLInvoiceNo;
	
	@FindBy(xpath="//strong[contains(text(),'Invoice #')]")
	private WebElement LBLOpenInvoiceNo;
	

	
	
	@FindBy(xpath = "//div[@class='col-md-12']//strong[@class='text-success pb-2'][contains(text(),'Closed')]")
	private WebElement LBLClosed;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//a[contains(text(),'DOWNLOAD PDF')]")
	private WebElement LNKDownloadPDF;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//h4[contains(text(),'Balance Due')]")
	private WebElement LBLBalanceDue;
	
	@FindBy(xpath = "//div[contains(@class,'col-md-12 pb-2')]//div[contains(text(),'Due Date: ')]")
	private WebElement LBLDueDate;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//strong[contains(@class,'dueSoon')]")
	private WebElement LBLDueStatus;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//div[contains(@class,'col-md-12 pb-2')]/div[3]")
	private WebElement LBLCustName;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//div[contains(@class,'col-md-12 pb-2')]/div[4]")
	private WebElement LBLAccountNum;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//div[contains(@class,'col-md-12 pb-2')]/div[5]")
	private WebElement LBLOrderNum;
	
	@FindBy(xpath = "//div[contains(@class,'col-md-4 pt-1 pb-1 stickybar')]//a[contains(@class,'btn btn-primary btn-gradiant btn-block payWithCard font-weight-bold')][contains(text(),'PAY WITH CARD')]")
	private WebElement LNKPayWithCard;
	
	@FindBy(xpath = "//form[contains(@id,'invoiceDetailsForm')]//h3[contains(text(),'Enter payment information')]")
	private WebElement LBLEnterPayInfo;
	
	@FindBy(xpath = "//h2[contains(text(),'Pay Invoice')]") private WebElement payinvoiceheader;
	
	@FindBy(xpath = "//td[contains(text(),'Shipping Charges')]")
	private WebElement LBLInvoiceShipping;
	
	@FindBy(xpath = "//td[contains(text(),'Subtotal')]")
	private WebElement LBLInvoiceSubTotal;
	
	@FindBy(xpath = "//div[contains(@class,'pb-2')][contains(text(),'Tax')]")
	private WebElement LBLInvoiceTaxAmt;
	
	@FindBy(xpath = "//td[contains(text(),'Total in')]")
	private WebElement LBLTotalInvoiceAmount;

	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//span[contains(text(),'Total in')]")
	private WebElement LBLTotalBalanceAmount;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//h4[contains(text(),'Terms & Conditions')]")
	private WebElement LBLPayInvoiceTnC;
	
	@FindBy(xpath = "//th[contains(text(),'Item number')]")	private WebElement LBLInvoiceItemNo;
	
	@FindBy(xpath = "//th[contains(text(),'Service description')]")	private WebElement LBLServiceDesc;
	
	@FindBy(xpath = "//th[contains(@class,'mb-2 text-center')]") private WebElement LBLInvoiceItemQty;
	
	@FindBy(xpath = "//th[contains(@class,'mb-2 text-right')]")	private WebElement LBLItemAmount;

	// Credit Card
	@FindBy(id = "xiFrameHosted") private WebElement iframe;
	
	@FindBy(id = "xieCommFrame") private WebElement iframe1;
	
	@FindBy(id = "iframewrapper") private WebElement iframe2;
	
	@FindBy(id = "c-cardname") private WebElement cardName;
	
	@FindBy(id = "c-cardnumber") private WebElement cardNumber;
	
	@FindBy(id = "c-exmth") private WebElement expiryMonth;
	
	@FindBy(id = "c-exyr") private WebElement expiryYear;
	
	@FindBy(id = "c-cvv") private WebElement cvvNumber;
	
	@FindBy(id = "c-cardname-error") private WebElement ccNameError;
	
	@FindBy(id = "c-cardnumber-error") private WebElement ccNumberError;
	
	@FindBy(id = "c-exmth-error") private WebElement ccExpMonthError;
	
	@FindBy(id = "c-exyr-error") private WebElement ccExpYearError;

	@FindBy(id = "c-cvv-error") private WebElement ccCVVError;
	
	// Billing Address CC
	@FindBy(xpath = "//span[@class='select2 select2-container select2-container--default']")
	private WebElement ccbillinCountry;
	
	@FindBy(xpath = "//span[@class = 'select2-selection select2-selection--single']")
	private WebElement DRDNCountrySelected;
	
	@FindBy(id = "billTo_street1") private WebElement ccbillingline1;
	
	@FindBy(id = "billTo_street2") private WebElement ccbillingline2;
	
	@FindBy(id = "billTo_city") private WebElement ccbillingTownCity;
	
	@FindBy(id = "billTo_state") private WebElement billingState;
	
	@FindBy(id = "billTo_postalCode") private WebElement ccbillingPostcode;
	
	@FindBy(xpath ="(//button[@id='cmdSubmit2'])[1]") private WebElement LNKPaymentDone;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//a[contains(text(),'CANCEL')]")
	private WebElement LNKCancelPayment;
	
	@FindBy(xpath = "//span[contains(text(),'Address Line 1 is required.')]")
	private WebElement errorMsgLine1;
	
	@FindBy(xpath = "//span[contains(text(),'Country is required.')]")
	private WebElement errorMsgCountry;
	
	@FindBy(xpath = "//span[contains(text(),'Zip Code is required.')]")
	private WebElement errorMsgZipCode;
	
	@FindBy(xpath = "//span[contains(text(),'City is required.')]")
	private WebElement errorMsgCity;
	
	@FindBy(xpath = "//span[contains(text(),'State / Province is required.')]")
	private WebElement errorMsgState;
	
	@FindBy(xpath = "//div[contains(@class,'col-md-4 pt-1 pb-1 stickybar')]//span[contains(@class,'pt-4p showCard')][contains(text(),'MASTERCARD xxx-xxxx-1005')]")
	private WebElement LBLCCType;
	
	@FindBy(xpath = "//div[contains(@class,'col-md-4 pt-1 pb-1 stickybar')]//span[contains(@class,'pt-4p showCard')]")
	private WebElement LBLCCType1;
	
	@FindBy(xpath = "//select[contains(@id,'billTo_country')]/option")
	private WebElement DRDNCountry;
	
	@FindBy(xpath = "//select[contains(@id,'c-exmth')]/option")
	private WebElement DRDNExpMonth;
	
	@FindBy(xpath = "//select[contains(@id,'c-exyr')]/option")
	private WebElement DRDNExpYear;
	
	@FindBy(xpath="//div[contains(@class,'col-md-4 pt-1 pb-1 stickybar')]//h4[contains(@class,'font-weight-bold pb-1')][contains(text(),'Payment Information')]//a[contains(text(),'RE-ENTER')]") 
	private WebElement LNKReEnterPayInfo;
	
	@FindBy(xpath = "//div[contains(@class,'col-md-4 pt-1 pb-1 stickybar')]//div[contains(@class,'showWhenPayInvoice')]//a[contains(@class,'blue-bold-link pull-right showWhenPayInvoice pt-10p letter-spacing-1')][contains(text(),'RE-ENTER')]")
	private WebElement LNKReEnterBillingInfo;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//a[contains(@href,'/termsAndConditions')]")
	private WebElement LNKTermsNCond;
	
	@FindBy(xpath = "(//button[(@type='submit')and(@class='btn btn-primary btn-gradiant p-2 btn-block pt-2 pb-2 payInvoiceBtn font-weight-bold')])[2]")
	private WebElement BTNPayInvoice;
	
	// Friction:

	@FindBy(xpath = "//h3[@class='pull-left font-weight-bold m-0 fs-27p']")
	private WebElement PaymentHeader;

	@FindBy(id = "xiFrameHosted") private WebElement iframeSQ;

	@FindBy(xpath = "//div[@class='has-error error' and @id='notice']")
	private WebElement cardError;

	@FindBy(id = "pmStepUpFrame") private WebElement iframeSQ1;

	@FindBy(xpath = "//iframe[contains(@id, 'cardinal-stepUpIframe')]")
	private WebElement iframeSQ2;

	@FindBy(id = "xiFrameTokenHosted") private WebElement iframeSQ3;

	@FindBy(xpath = "//header[@id='header']") private WebElement header1SQ;

	@FindBy(xpath = "//section[@id='content']/div/h2") private WebElement header2SQ;

	@FindBy(xpath = "(//section[@id='content']/div/p)[1]") private WebElement sqTextMSG;

	@FindBy(xpath = "//input[@placeholder=' Enter Code Here']")
	private WebElement sqEnterCode;

	@FindBy(xpath = "//input[@value='SUBMIT']") private WebElement sqSubmitButton;

	@FindBy(xpath = "//span[@class='error-message text-danger font-weight-bold fs-1_2em pb-1']")
	private WebElement erroronCard;

	@FindBy(xpath = "//input[@value='CANCEL']") private WebElement sqCancelButton;

	@FindBy(xpath = "//input[@value='RESEND CODE']")
	private WebElement sqResendButton;
		
	//Pay Invoice Confirmation
	@FindBy(xpath = "//div[contains(@class,'spinner-icon')]")
	private WebElement IMGSpinner;
	
	@FindBy(xpath = "//div[contains(@id,'order-confirmation')]//a[contains(@href,'/my-account/invoices')]")
	private WebElement LNKPayAnotherInvoice;
	
	@FindBy(xpath = "//h2[contains(@class,'checkout-success')]")
	private WebElement LBLThankYou;

	@FindBy(xpath = "//a[contains(@class,'js-continue-shopping-button')]")
	private WebElement LNKContinueShopping;

	@FindBy(xpath = "//div[contains(@class,'checkout-success')]/p[contains(text(),'Your Confirmation Number is')]")
	private WebElement LBLPayInvoiceConfirmation;
	
	@FindBy(xpath = "//h2[contains(text(),'Guest Pay')]")
	private WebElement LBLGuestPay;
	
	@FindBy(xpath = "//form[@id='silentOrderPostForm']//tr[1]//td[1]")
	private WebElement LBLInvoiceNum;
	
	@FindBy(xpath = "//h3[contains(text(),'Customer Information')]")
	private WebElement LBLCustInfo;
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement TXTBXCustEmail;

	// Method to verify PAY WITH CARD link on OPEN Invoice Details page
	public PayInvoiceDetailsPage Verify_Invoice_DetailsPage()
	{		
		action.jsVerify(LBLInvoiceNo, "Invoice # Header");
		action.jsVerify(LBLDueDate, "Invoice Due Date");
		action.jsVerify(LBLCustName, "Invoice Customer Name");
		action.jsVerify(LBLAccountNum, "Invoice Account Number");
		action.jsVerify(LBLInvoiceItemNo, "Invoice Item Number");
		action.jsVerify(LBLServiceDesc, "Invoice Service Description");
		action.jsVerify(LBLInvoiceItemQty, "Invoice Item Quantity");
		action.jsVerify(LBLItemAmount, "Invoice Item Amount");
		action.jsVerify(LBLInvoiceShipping, "Shipping Amount for Invoice");
		action.jsVerify(LBLInvoiceSubTotal, "Invoice Sub Total Amount");
		action.jsVerify(LBLInvoiceTaxAmt, "Invoice Tax Amount");
		action.jsVerify(LBLTotalInvoiceAmount, "Total Invoice Amount");
		action.jsVerify(LBLBalanceDue, "Balance Due Label");
		action.jsVerify(LBLTotalBalanceAmount, "Total Balance Amount");
		action.jsVerify(LBLPayInvoiceTnC, "Terms & Condition Label");
		action.jsVerify(LNKTermsNCond, "Terms & Condition Link");
		return new PayInvoiceDetailsPage();
	}
	
	public PayInvoiceDetailsPage Verify_Open_Invoice_DetailsPage()
	{
		action.jsVerify(LBLInvoiceNo, "Invoice # Header");
		action.jsVerify(LBLDueDate, "Invoice Due Date");
		action.jsVerify(LNKPayWithCard, "Pay with card");
		return this;
	}
	
	public PayInvoiceDetailsPage Verify_Retrieve_Invoice_DetailsPage()
	{
		verify.isElementDisplayed(LBLGuestPay, "Guest Pay");
		verify.isElementDisplayed(LBLInvoiceNum, "Invoice Number");
		verify.isElementDisplayed(LBLCustInfo, "Customer Email");
		action.inputText(TXTBXCustEmail, "Customer Email", "guestpayinvoice@test.com");
		return this;

	}
	
	// Method to verify PAY INVOICE button is appearing disabled on Invoice Details Page
	public PayInvoiceDetailsPage Verify_PayInvoice_Button_Disabled()
	{
		action.getAttributeIgnoreEmpty(BTNPayInvoice, "disabled", "PAY INVOICE button");
		return new PayInvoiceDetailsPage();
	}
	
	// Method to verify Download PDF on Invoice Details Page
	public PayInvoiceDetailsPage VerifyPayInvoicePDFDownload()
	{
		verify.isElementDisplayed(LNKDownloadPDF, "Download PDF link");
		action.clickWithClickableWait(LNKDownloadPDF, "Download PDF link");
		action.switchToNewTab();
		String PDFURL = verify.getCurentURL();
		if(PDFURL.contains(InvoicePDFSite))
		{
			action.logInfo(PDFURL + "Invoice PDF opened in a a new tab");
		}
		else {
			action.logFailure(PDFURL + " is not opened in a a new tab");
		}
		action.closeNewTab();
		action.switchToParentTab();	
		return new PayInvoiceDetailsPage();

	}
	
	// Method to click on PAY WITH CARD link on OPEN Invoice Details page
	public PayInvoiceDetailsPage Pay_Invoice_With_Card()
		{
			action.jsClick(LNKPayWithCard, "Pay With Card link");
			return new PayInvoiceDetailsPage();
		}
		
	// Method to verify VIEW link on Closed Invoice Details page
	public PayInvoiceDetailsPage Verify_Closed_Invoice_DetailsPage()
	{
		action.jsVerify(LBLInvoiceNo, "Invoice # Header");
		action.jsVerify(LBLClosed, "Closed Label");
		return new PayInvoiceDetailsPage();
	}

	public PayInvoiceDetailsPage Use_Credit_Card_Payment_PayInvoice(ConcurrentHashMap<String, String> cardPayment) {
		action.switchToFrame(iframeID);
		action.switchToFrame(iframe1);
		action.waitForElementDisplayed(cardName, "Card Name");
		action.inputText(cardName, "Card Name", cardPayment.get("name"));
		action.inputText(cardNumber, "Card Number", cardPayment.get("number"));
		action.selectByVisibleText(expiryMonth, cardPayment.get("month"), "card expiry month");
		action.selectByVisibleText(expiryYear, cardPayment.get("year"), "card expiry year");
		action.inputText(cvvNumber, "Card CVV number", cardPayment.get("cvv"));
		action.switchToDefaultContent();
		return new PayInvoiceDetailsPage();
	}

	public PayInvoiceDetailsPage Verify_JEU_BillTo_Address(ConcurrentHashMap<String, String> NewEUShipping)
	{
		action.scrollToElement(ccbillingline1,"Address Line 1 billing address");
		action.inputText(ccbillingline1, "Address Line 1 billing address", NewEUShipping.get("addressLine1"));
		action.inputText(ccbillingline2, "Address Line 2 billing address", NewEUShipping.get("addressLine2Optional"));
		action.clickWithClickableWait(DRDNCountrySelected, "Billing Country drop down");
		action.selectCustomDropDown(NewEUShipping.get("country"));
		action.switchToDefaultContent();
		action.inputText(ccbillingPostcode, "Postcode billing address", NewEUShipping.get("zipPostCode"));
		action.inputText(ccbillingTownCity, "TownCity billing address", NewEUShipping.get("towncity"));
		if (!countryWithoutStates.contains(NewEUShipping.get("country"))) {
			action.selectByVisibleText(billingState, NewEUShipping.get("state"), "State in Billing Address");
		}

		return this;
	}
	
	public PayInvoiceDetailsPage Clear_BillTo_Address()
	{
		action.scrollToElement(ccbillingline1,"Address Line 1 billing address");
		action.clearTextBox(ccbillingline1, "Billing Address Line 1");
		action.clearTextBox(ccbillingline2, "Billing Address Line 2");
		action.clearTextBox(ccbillingPostcode, "Billing Address Post Code");
		action.clearTextBox(ccbillingTownCity,"Billing Address City");
		return this;
	}

	public PayInvoiceDetailsPage Verify_JAU_BillTo_Address(ConcurrentHashMap<String, String> addressAustralia)
	{
		action.scrollToElement(ccbillingline1,"Address Line 1 billing address");
		action.inputText(ccbillingline1, "Address Line 1 billing address", addressAustralia.get("addressLine1"));
		action.inputText(ccbillingline2, "Address Line 2 billing address", addressAustralia.get("addressLine2Optional"));
		action.clickWithClickableWait(ccbillinCountry, "Billing Country drop down");
		action.selectCustomDropDown(addressAustralia.get("country"));
		action.switchToDefaultContent();
		action.inputText(ccbillingPostcode, "Postcode billing address", addressAustralia.get("zipPostCode"));
		action.inputText(ccbillingTownCity, "TownCity billing address", addressAustralia.get("towncity"));
		if (!countryWithoutStates.contains(addressAustralia.get("country"))) {
			action.selectByVisibleText(billingState, addressAustralia.get("state"), "State in Billing Address");
		}

		return this;
	}
	
	// Modified the method as per Pay with Card Popup fields
	public PayInvoiceDetailsPage Enter_New_Billing_Address_CC_PayInvoice(ConcurrentHashMap<String, String> billingAddress) {
		action.scrollToElement(ccbillingline1,"Address Line 1 billing address");
		action.inputText(ccbillingline1, "Address Line 1 billing address", billingAddress.get("addressLine1"));
		action.inputText(ccbillingline2, "Address Line 2 billing address", billingAddress.get("addressLine2Optional"));
		action.clickWithClickableWait(ccbillinCountry, "Billing Country drop down");
		action.selectCustomDropDown(billingAddress.get("country"));action.switchToDefaultContent();
		action.inputText(ccbillingPostcode, "Postcode billing address", billingAddress.get("zipPostCode"));
		action.inputText(ccbillingTownCity, "TownCity billing address", billingAddress.get("towncity"));
		if (!countryWithoutStates.contains(billingAddress.get("country"))) {
			action.selectByVisibleText(billingState, billingAddress.get("state"), "State in Billing Address");
		}
		return this;
	}

	// Extracted the Method to click on DONE button to reuse
	public PayInvoiceDetailsPage PayWithCard_Popup_DoneButton() {
        action.jsClick(LNKPaymentDone, "DONE link");
		return this;
	}
	
	// Method to click on Cancel link on Pay With Card form
	public PayInvoiceDetailsPage PayWithCard_Popup_CancelButton() {
		action.jsClick(LNKCancelPayment, "CANCEL link");
		return this;
	}
	
	
	// Method to verify error messages for required Billing Address fields when user clicks on DONE without entering any value on the Popup
	public PayInvoiceDetailsPage Verify_RequiredFields_PayWithCard_Popup_BillingAddress()
	{
		action.jsVerify(errorMsgLine1, "Address Line 1 Error Message");
		action.jsVerify(errorMsgCountry, "Country Error Message");
		action.jsVerify(errorMsgZipCode, "Zip Code Message");
		action.jsVerify(errorMsgCity, "City Error Message");
		action.jsVerify(errorMsgState, "State Error Message");
		return this;
	}

	// Method to verify error messages for required Credit Card fields when user clicks on DONE without entering any value on the Popup
	public PayInvoiceDetailsPage Verify_RequiredFields_PayWithCard_Popup_CC()
	{
		action.waitForElementPresence(By.id(iframeID), "iframe");
		action.switchToFrame(iframe);
		action.switchToFrame(iframe1);
		action.scrollToElement(cardName,"Card Name");
		action.waitForElementDisplayed(cardName, "Card Name");
		verify.isElementDisplayed(ccNameError, "CC Name Error Message");
		verify.isElementDisplayed(ccNumberError, "CC Number Error Message");
		verify.isElementDisplayed(ccExpMonthError, "CC Expiry Month Message");
		verify.isElementDisplayed(ccExpYearError, "CC Expiry Year Error Message");
		verify.isElementDisplayed(ccCVVError, "CC CVV Error Message");
		action.switchToDefaultContent();
		return this;
	}
	
	// Method to verify Pay Invoice page with CC and Billing details and PAY INVOICE button is enabled on Pay Invoice Page
	public PayInvoiceDetailsPage Verify_CC_BillingDetails_PayInvoice()
	{
		action.jsVerify(LBLCCType, "LBLCCType");
		action.jsVerify(LNKReEnterPayInfo, "Re-enter link for CC");
		action.jsVerify(LNKReEnterBillingInfo, "Re-enter link for Billing Address");
		action.jsVerify(BTNPayInvoice, "PAY INVOICE button is enabled");
		return this;
	}

	// Method to click on PAY INVOICE button
	public PayInvoiceDetailsPage PayInvoice() 
	{
		action.jsClick(BTNPayInvoice, "PAY INVOICE button");
	    return this;
	}

	// Method to verify the confirmation page after successful invoice payment
	public PayInvoiceDetailsPage Verify_Invoice_Pay_Confirmation()
	{
		action.waitForElementDisplayed(LBLThankYou, "Thank You");
		verify.isElementDisplayed(LBLPayInvoiceConfirmation, "Invoice Payment Confirmation");
		verify.isElementDisplayed(LNKContinueShopping, "Continue Shopping link");
        action.clickWithClickableWait(LNKPayAnotherInvoice, "Pay Another Invoice link");
		action.waitForElementClickable(LNKOpenInvoiceTab);
		if(action.getCurentURL().contains("/my-account/invoices"))
		{
			action.logInfo("User navigated to Open Invoices - Pay Invoice page.");
		}
		else
		{
			throw new CustomException("User did not navigate to Open Invoices page");
		}
		return this;
	}
	
	// Method to click on Re-enter link next to Payment Info
	public PayInvoiceDetailsPage ReEnter_PayInfo() {

		//action.clickWithClickableWait(LNKReEnterPayInfo, "Click on Re-enter link for CC");
		action.jsClick(LNKReEnterPayInfo, "Re-enter link for CC");
		return this;
	}
	
	// Method to click on Re-enter link next to Billing Address
	public PayInvoiceDetailsPage ReEnter_BillingAddress() {
		action.jsClick(LNKReEnterBillingInfo, "Re-enter link for Billing Address");
		return this;
	}
	
	// Method to verify if Credit Card fields are cleared when user clicks on Re-Enter Pay Info
	public PayInvoiceDetailsPage Verify_PayInvoice_CCInfo_Empty()
	{
		action.waitForElementPresence(By.id(iframeID), "iframe");
        action.switchToFrame(iframeID);
        action.switchToFrame(iframe1);
        action.scrollToElement(cardName, "card name");
        action.getAttributeIgnoreEmpty(cardName, "value", "Card Name");
        action.getAttributeIgnoreEmpty(cardNumber, "value", "card Nubmer");
        action.getAttributeIgnoreEmpty(DRDNExpMonth, "disabled", "Expiry Month");
        action.getAttributeIgnoreEmpty(DRDNExpYear, "disabled", "Expiry Year");
        action.getAttributeIgnoreEmpty(cvvNumber, "value", "CVV");
        action.switchToDefaultContent();
		return this;
	}
	
	
	//Method to verify if Billing Address fields are cleared when user clicks on Re-Enter Billing Info
	public PayInvoiceDetailsPage Verify_PayInvoice_BillingInfo_Empty()
	{
		action.getAttributeIgnoreEmpty(ccbillingline1, "value", "Billing Line 1");
		action.getAttributeIgnoreEmpty(ccbillingline2, "value", "Billing Line 2");
		action.getAttributeIgnoreEmpty(DRDNCountry, "disabled", "Country");
		action.getAttributeIgnoreEmpty(ccbillingPostcode, "value", "Post Code");
		action.getAttributeIgnoreEmpty(ccbillingTownCity, "value", "City");
		action.getAttributeIgnoreEmpty(billingState, "disabled", "State");
		return this;
	}

	public PayInvoiceDetailsPage Verify_CC_BillingDetails_PayInvoice(ConcurrentHashMap<String, String> cardPayment) {
		String CardName = cardPayment.get("name");
		action.waitForElementDisplayed(LBLCCType1, "LBLCCType1");
		String CardNameActual = action.getText(LBLCCType1, "card name on UI");
		verify.verifyTextContains(CardName, CardNameActual, "Card name matches");
		action.jsVerify(LNKReEnterPayInfo, "Re-enter link for CC");
		action.jsVerify(LNKReEnterBillingInfo, "Re-enter link for Billing Address");
		action.jsVerify(BTNPayInvoice, "PAY INVOICE button is enabled");
		return this;
	}
	
	public PayInvoiceDetailsPage FrictionFlow_Validation_3DS2(ConcurrentHashMap<String, String> CardWithFriction,
			String button) {
		action.setCustomExplicitWait(40);
		action.waitForElementDisplayed(PaymentHeader, "PaymentHeader");
		action.waitForElementVisibility(iframeSQ, "iframeSQ");
		action.switchToFrame(iframeSQ);
		action.waitForElementVisibility(iframeSQ1, "iframeSQ");
		action.switchToFrame(iframeSQ1);
		action.waitForElementVisibility(iframeSQ2, "iframeSQ");
		action.switchToFrame(iframeSQ2);
		verify.isElementDisplayed(header1SQ, "Header on Security Question");
		verify.isElementDisplayed(header2SQ, "Header on Security Question");
		verify.isElementDisplayed(sqCancelButton, "cancel button");
		verify.isElementDisplayed(sqResendButton, "Resend button");
		verify.isElementDisplayed(sqTextMSG, "Text message");
		action.scrollToElement(sqTextMSG, "sqTextMSG");
		if (button.equals("submit")) {
			action.waitForElementClickable(sqSubmitButton);
			action.scrollToElement(sqSubmitButton, "sqSubmitButton");
			action.inputText(sqEnterCode, "code", CardWithFriction.get("code"));
			action.clickWithClickableWait(sqSubmitButton, "Submit security code");

		} else if (button.equals("cancel")) {
			action.waitForElementClickable(sqCancelButton);
			action.scrollToElement(sqCancelButton, "sqCancelButton");
			action.clickWithClickableWait(sqCancelButton, "cancel security code");
			verify.isElementDisplayed(erroronCard, "erroronCard");

		}
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();
		return this;

	}
	
}
