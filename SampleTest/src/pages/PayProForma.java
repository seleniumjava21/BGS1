package pages;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.ThreadManager;

public class PayProForma extends BasePage {
	public PayProForma() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Guest");

		}
	}

	@FindBy(xpath = "//h2[contains(text(),'Pay Invoice')]")
	private WebElement payinvoiceheader;
	@FindBy(id = "accountNumber")
	private WebElement accountnumber;
	@FindBy(id = "invoiceNumber")
	private WebElement invoicenumber;
	@FindBy(xpath = "//a[@class='hint-text']")
	private WebElement invoiceexamplelink;
	@FindBy(xpath = "//button[@class='btn btn-primary btn-block']")
	private WebElement loginandpaybtn;
	@FindBy(xpath = "btn btn-info btn-block")
	private WebElement paywithoutloginbtn;
	@FindBy(xpath = "//button[@id='retrieve']")
	private WebElement retrievebtn;

	@FindBy(xpath = "//a[@class='inheritHoverColor']")
	private WebElement contactUs;

	@FindBy(xpath = "//input[@id='orderNumber']")
	private WebElement ProFormaOrderNumber;

	@FindBy(xpath = "//input[@id='amount']")
	private WebElement ProFormaInvoiceAmount;

	@FindBy(xpath = "//select[@id='currency']")
	private WebElement PayProformaCurrency;

	@FindBy(xpath = "//input[@id='accountNumber']")
	private WebElement accountNumber;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement payemail;

	@FindBy(xpath = "//input[@id='signature']")
	private WebElement signature;

	@FindBy(xpath = "//input[@id='termsAgreement']")
	private WebElement termsAgreement;

	@FindBy(xpath = "//button[@id='payProformaButton']")
	private WebElement payProformaButton;

	@FindBy(xpath = "//div[@id='order-confirmation']")
	private WebElement payInvoiceConfirm;

	@FindBy(xpath = "//div[@id='notice']")
	private WebElement errorMsg;

	@FindBy(xpath =  "//a[contains(text(),'Store FAQ')]")
	private WebElement storeFAQ;

	@FindBy(xpath =  "//dt[contains(text(),'How do I pay a Pro Forma invoice?')]")
	private WebElement FAQPayProforma;

	@FindBy(xpath = "//p[contains(text(),'To pay a Pro Forma invoice please access the Pro F')]//a[contains(text(),'here')]")
	private WebElement FAQPayProformaLink;
	// Credit Card
	@FindBy(id = "xiFrameHosted")
	private WebElement iframe;
	@FindBy(id = "xieCommFrame")
	private WebElement iframe1;
	@FindBy(id = "c-cardname")
	private WebElement cardName;
	@FindBy(id = "c-cardnumber")
	private WebElement cardNumber;
	@FindBy(id = "c-exmth")
	private WebElement expiryMonth;
	@FindBy(id = "c-exyr")
	private WebElement expiryYear;
	@FindBy(id = "c-cvv")
	private WebElement cvvNumber;

	// Billing Address CC
	@FindBy(xpath = "//span[@class='select2 select2-container select2-container--default']")
	private WebElement ccbillinCountry;
	@FindBy(id = "billTo_street1")
	private WebElement ccbillingline1;
	@FindBy(id = "billTo_street2")
	private WebElement ccbillingline2;
	@FindBy(id = "billTo_city")
	private WebElement ccbillingTownCity;
	@FindBy(id = "billTo_state")
	private WebElement billingState;
	@FindBy(id = "billTo_postalCode")
	private WebElement ccbillingPostcode;
	private ArrayList<String> countryWithoutStates = new ArrayList<String>(
			Arrays.asList("Germany", "France", "New Zealand", "Brazil"));
	private ArrayList<String> countryWithTax = new ArrayList<String>(Arrays.asList("Brazil"));

	public PayProForma Validate_PayInvoiceScreen() {
		verify.isElementDisplayed(payinvoiceheader, "Pay Invoice Header");
		verify.isElementDisplayed(accountnumber, "Account Number");
		verify.isElementDisplayed(invoicenumber, "Invoice Number");
		verify.isElementDisplayed(invoiceexamplelink, "Invoice example link");
		verify.isElementDisplayed(loginandpaybtn, "Login and Pay Button");
		verify.isElementDisplayed(paywithoutloginbtn, "Pay Without Login Button");
		return new PayProForma();

	}

	public PayProForma Proforma_Input_Invoice_and_Order_Information(String Currency) {
		LocalTime time = LocalTime.now();
		String LocalTime = time.toString().replace(".", "").replace(":", "");
		String randomString = LocalTime;
		String Ordernumber = randomString.substring(0, 6);
		String amount = randomString.substring(0, 2);
		action.inputText(ProFormaOrderNumber, "Order Number", Ordernumber);
		action.inputText(ProFormaInvoiceAmount, "invoiceAmount Number", amount);
		action.selectByVisibleText(PayProformaCurrency, Currency, "currency");
		return new PayProForma();
	}

	public PayProForma enterCustomer_emailInformation_ProFormaInvoice(ConcurrentHashMap<String, String> info,
			AccountType accountType) {
		String accountno = utils.getAccountThroughAPI(info, accountType.toString());
		action.inputText(accountNumber, "Pay Account Number", accountno);
		String payEmail = utils.generateEmail();
		action.inputText(payemail, "Pay Email", payEmail);
		return this;
	}

	public PayProForma Validate_RetrieveInvoiceScreen() {		
		verify.isElementDisplayed(retrievebtn, "Retrieve Invoice button");
		return new PayProForma();

	}

	public PayProForma verify_proforma_invoice_FAQPage() {		
		action.waitForElementVisibility(storeFAQ,"store FAQ");
		action.clickAndIgnoreFailure(storeFAQ, "store FAQ");
		if (ThreadManager.getInstance().getDriver().getWindowHandles().size() > 0) {
			action.switchToNewTab();
			ThreadManager.logger.get().info("Navigated on Frequently Asked Questions\"");
			action.scrollToElement(FAQPayProforma,"FAQ PayProform");		
			action.clickWithClickableWait(FAQPayProforma, "FAQ PayProform");
			verify.isElementExistsIgnore(FAQPayProformaLink);
			action.clickWithClickableWait(FAQPayProformaLink, "Click Here for PayProformalink");			
			if (action.getCurentURL().contains("payproforma")) {
				ThreadManager.logger.get().info("Clicked on payproforma link");			
			}
		} else {
			ThreadManager.logger.get()
			.fail(MarkupHelper.createLabel(
					"Clicked on PayProforma link"+ " but user is redirected to "+ action.getCurentURL(),
					ExtentColor.RED));
		}
		return this;
	}

	public PayProForma verify_ContactUs_onPayProforma() {
		verify.isElementExistsIgnore(contactUs, "Contact Us");
		String href = action.getAttribute(contactUs, "href", "href");
		action.waitForElementVisibility(contactUs,"Contact US");
		action.clickAndIgnoreFailure(contactUs, "Contact US");
		if (ThreadManager.getInstance().getDriver().getWindowHandles().size() > 0) {
			action.switchToNewTab();
			ThreadManager.logger.get().info("Navigated on contactUs link");
			if (action.getCurentURL().contains("contactUs")) {
				ThreadManager.logger.get().info("Clickes on contactUs link");
				action.closeNewTab();
				action.switchToParentTab();
			}
		} else {
			ThreadManager.logger.get()
			.fail(MarkupHelper.createLabel(
					"Click on contactUs link" + " but user is redirected to " + action.getCurentURL(),
					ExtentColor.RED));
		}
		return this;
	}

	public PayProForma Use_Credit_Card_Payment_PayProforma(ConcurrentHashMap<String, String> cardPayment) {
		action.switchToFrame(iframe);
		action.switchToFrame(iframe1);
		action.waitForElementDisplayed(cardName, "Card Name");
		action.inputText(cardName, "Card Name", cardPayment.get("name"));
		action.inputText(cardNumber, "Card Number", cardPayment.get("number"));
		action.selectByVisibleText(expiryMonth, cardPayment.get("month"), "card expiry month");
		action.selectByVisibleText(expiryYear, cardPayment.get("year"), "card expiry year");
		action.inputText(cvvNumber, "Card CVV number", cardPayment.get("cvv"));
		action.switchToDefaultContent();
		return new PayProForma();
	}

	public PayProForma Add_New_Billing_Address_CC_Proforma(ConcurrentHashMap<String, String> billingAddress) {
		action.scrollToElement(ccbillinCountry,"Billing Country drop down");
		action.clickWithClickableWait(ccbillinCountry, "Billing Country drop down");
		action.selectCustomDropDown(billingAddress.get("country"));
		action.switchToDefaultContent();
		action.setCustomExplicitWait(40);
		action.inputText(ccbillingline1, "Address Line 1 billing address", billingAddress.get("addressLine1"));
		action.inputText(ccbillingline2, "Address Line 2 billing address", billingAddress.get("addressLine2Optional"));
		action.inputText(ccbillingTownCity, "TownCity billing address", billingAddress.get("towncity"));
		action.inputText(ccbillingPostcode, "Postcode billing address", billingAddress.get("zipPostCode"));
		if (!countryWithoutStates.contains(billingAddress.get("country"))) {
			action.selectByVisibleText(billingState, billingAddress.get("state"), "State in Billing Address");
		}
		action.switchToDefaultContent();
		return this;
	}

	public PayProForma Provide_Signature_and_Submit_PayProforma() {
		action.inputText(signature, "BGSPayProforma", "SignatureMarvel");
		action.jsClick(termsAgreement, "Terms");
		action.clickWithClickableWait(payProformaButton, "Pay ProForma");
		if (verify.isElementDisplayed(payInvoiceConfirm, "Pay Proforma confirmation")) {
			String text = action.getTextWithoutLogging(payInvoiceConfirm);
			ThreadManager.logger.get().info("PayProforma Confirmation " + text);
		} else if (verify.isElementDisplayed(errorMsg, "Error mesage")) {
			String Errortext = action.getTextWithoutLogging(payInvoiceConfirm);
			ThreadManager.logger.get()
			.fail(MarkupHelper.createLabel("Pay ProForma Failed due to ------> " + Errortext, ExtentColor.RED));
		}
		return this;
	}

	public HomePage Goto_HomePage() {
		action.clickLink("Shop");
		return new HomePage();
	}

}
