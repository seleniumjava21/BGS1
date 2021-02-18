package pages;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.ThreadManager;

public class CheckOutPage extends BasePage {

	public CheckOutPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Checkout");
		}
	}

	@FindBy(id = "notice")
	private WebElement errorBanner;
	@FindBy(xpath = "//select[@id='shipMethodSelect']")
	private WebElement shippingMethod;
	@FindBy(xpath = "//input[@id='addressBook']")
	private WebElement addressBookBtn;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='addressBook']")
	private WebElement ccaddressBookBtn;
	@FindBy(id = "deliveryMethodSubmit")
	private WebElement shippingDetailsNextButton;
	@FindBy(id = "PurchaseOrderNumber")
	private WebElement poNumbertxtBox;
	@FindBy(id = "choosePaymentType_continue_button")
	private WebElement paymentTypeNextButton;
	@FindBy(xpath = "//button[@class='btn btn-primary btn-block paymentInfoBtn'][contains(text(),'Next')]")
	private WebElement ccpaymentTypeNextButton;
	@FindBy(xpath = "//span[@class='terms']")
	private WebElement termsAndConditonsCheckBox;
	@FindBy(id = "placeOrder")
	private WebElement placeOrderButton;
	@FindBy(xpath = "//span[@class='select2-selection__arrow']//b")
	private WebElement billinCountry;
	@FindBy(xpath = "//span[@class='select2-selection__arrow']//b")
	private WebElement shippingCountryDropDown;
	@FindBy(id = "select2-addresscountry-results")
	private WebElement billingCountryResults;
	@FindBy(id = "address.postcode")
	private WebElement postcodeTextBox;
	@FindBy(xpath = "//form[@id='silentOrderPostForm']//div[@id='billingAddressForm']//img")
	private WebElement spinnerGif;
	@FindBy(xpath = "//button[contains(text(),'Use Address')]")
	private List<WebElement> useAddressBtnList;
	@FindBy(xpath = "//button[@id='accountPayUseThisAddress']")
	private List<WebElement> billinguseAddressBtnList;
	@FindBy(xpath = "//ul[@class='stay']//li")
	private List<WebElement> existingShippingAddressList;
	@FindBy(xpath = "//div[@class='addressEntry']/form/ul/li")
	private List<WebElement> existingBillingAddressList;
	private ArrayList<String> countryWithoutStates = new ArrayList<String>(
			Arrays.asList("Australia", "Germany", "France", "New Zealand", "Brazil", "Mexico"));
	private ArrayList<String> countryWithTax = new ArrayList<String>(Arrays.asList("Brazil"));

	// shipping address details
	@FindBy(id = "address.firstName")
	private WebElement shippingFirstName;
	@FindBy(id = "address.surname")
	private WebElement shippingLastName;
	@FindBy(id = "address.line1")
	private WebElement shippingAddressLine1;
	@FindBy(id = "address.line2")
	private WebElement shippingAddressLine2;
	@FindBy(id = "address.townCity")
	private WebElement shippingTownCity;
	@FindBy(id = "address.region")
	private WebElement shippingState;
	@FindBy(id = "address.postcode")
	private WebElement shippingPostalCode;
	@FindBy(id = "countrydropdown-button")
	private WebElement shippingCountryCode;
	@FindBy(id = "phoneNumber")
	private WebElement shippingPhoneNumber;
	@FindBy(id = "address.taxRegistrationNumber")
	private WebElement taxfield;
	@FindBy(id = "addressSubmit")
	private WebElement shippingNextButton;

	// Billing address details
	@FindBy(xpath = "//div[@id='billingAddressFormAP']//input[@id='address.firstName']")
	private WebElement billingFirstName;
	@FindBy(xpath = "//div[@id='billingAddressFormAP']//input[@id='address.surname']")
	private WebElement billingLastName;
	@FindBy(xpath = "//div[@id='billingAddressFormAP']//input[@id='address.line1']")
	private WebElement billingline1;
	@FindBy(xpath = "//div[@id='billingAddressFormAP']//input[@id='address.line2']")
	private WebElement billingline2;
	@FindBy(xpath = "//div[@id='billingAddressFormAP']//input[@id='address.townCity']")
	private WebElement billingTownCity;
	@FindBy(xpath = "//div[@id='billingAddressFormAP']//input[@id='address.postcode']")
	private WebElement billingPostcode;
	@FindBy(xpath = "//div[@id='billingAddressFormAP']//input[@id='address.phone']")
	private WebElement billingPhone;
	@FindBy(id = "choosePaymentType_continue_button")
	private WebElement nextButtonBillingAddress;

	// Billing Address CC
	@FindBy(xpath = "//div[@id='creditCardContainer']//span[@class='select2-selection__arrow']//b")
	private WebElement ccbillinCountry;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='address.firstName']")
	private WebElement ccbillingFirstName;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='address.surname']")
	private WebElement ccbillingLastName;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='address.line1']")
	private WebElement ccbillingline1;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='address.line2']")
	private WebElement ccbillingline2;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='address.townCity']")
	private WebElement ccbillingTownCity;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='address.postcode']")
	private WebElement ccbillingPostcode;
	@FindBy(xpath = "//div[@id='creditCardContainer']//input[@id='address.phone']")
	private WebElement ccbillingPhone;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-block paymentInfoBtn'][contains(text(),'Next')]")
	private WebElement ccnextButtonBillingAddress;
	// PO payment
	@FindBy(xpath = "//label[@class='radio-button-container account-payment-select']")
	private WebElement poRadioButton;

	// Billing State Dropdown
	@FindBy(xpath = "//*[@name='billTo_state']")
	private WebElement billingState;

	// credit card details
	@FindBy(xpath = "//label[@class='radio-button-container credit-card-select']//span")
	private WebElement cardRadioButton;
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
	String editCart = "EDIT CART";
	@FindBy(xpath = "//div[contains(text(),'4. Final Review')]")
	private WebElement finalReview;
	@FindBy(xpath = "//div[contains(text(),'3. Payment Type')]")
	private WebElement paymentType;

	// address Verify popup
	@FindBy(id = "verificationModalClose")
	private WebElement addressVerifyClose;
	@FindBy(id = "use_suggested_address_button")
	private WebElement replaceAddress;

	// Order summary
	@FindBy(xpath = "(//div[@class='fulfillment'])[1]")
	private WebElement fulfillmentVal1;
	@FindBy(xpath = "(//div[@class='fulfillment'])[2]")
	private WebElement fulfillmentVal2;

	// save credit card

	@FindBy(xpath = "//span[@class='checkmark stay']")
	private WebElement checkboxSC;

	@FindBy(xpath = "//span[contains(text(),'Save credit card for future online transactions.')]")
	private WebElement saveCardText;

	@FindBy(xpath = "//div[contains(text(),' 2. Shipping Details')]")
	private WebElement shippingDetailsHeader;

	@FindBy(xpath = "//button[@type='button' and contains(text(),'Use a saved card')]")
	private WebElement useSaveCardButton;

	@FindBy(xpath = "//h4[@id='myModalLabel' and contains(text(),'Use a saved card')]")
	private WebElement useSaveCardHeader;

	@FindBy(xpath = "(//button[@type='submit' and contains(text(),'USE THIS CARD')])[2]")
	private WebElement usethisCardButton;

	@FindBy(xpath = "//a[@href='/checkout/multi/payment-type/choose']")
	private WebElement useSaveCardEdit;

	@FindBy(xpath = "//div[@class='font-weight-normal stay']/div/strong")
	private List<WebElement> cardNameSaveCard;

	@FindBy(xpath = "//div[@class='font-weight-normal stay']/div[1]/br[1]")
	private List<WebElement> cardTypeSaveCard;

	String cardEXPSave = "//*[@id=\"savedcardmodal\"]/div/div/div[2]/div/form/div/div[1]";
	By cardEXPSaveCard = By.xpath(cardEXPSave);

	String expText = "/br[2]/following-sibling::text()";
	// "/br[2]" ;
	// "/text()[4]";
	By expDateText = By.xpath(expText);

	String prdList = "//ul[@class='products-container']/li";
	By productList = By.xpath(prdList);
	String btn = "//button[@id='addToCartButton']";
	By addToCarBtn = By.xpath(btn);

	@FindBy(xpath = "//div[contains(text(),'CVV is required')]")
	private WebElement cvvRequiredText;

	@FindBy(xpath = "//input[@id='card_cvvNumber']")
	private WebElement cvvSavedCard;

	@FindBy(xpath = "//button[@type='button' and contains(text(),'×')]")
	private WebElement useSaveCardClose;

	// Auto renewal

	@FindBy(xpath = "//label[@class='radio-button-container font-weight-bold pr-2 mb-0 mb-mobile-1 mb-tablet-1']")
	private WebElement autoRenewalModule;

	@FindBy(xpath = "//div[contains(text(),'Auto-Pay Annual Contracts')]")
	private WebElement autoRenewalHeader;

	@FindBy(xpath = "//div[contains(text(),'Current Price:')]")
	private WebElement autoRenewalPrice;

	@FindBy(xpath = "(//div[@class='auto-pay-desc font-weight-normal pb-1'])")
	private WebElement autoDraftText;

	@FindBy(xpath = "//i[@style='font-size:16.5px']")
	private WebElement autoTermsText;

	@FindBy(xpath = "//i[contains(text(),'alarm_on')]")
	private WebElement autoAlarmOn;

	@FindBy(xpath = "//div[contains(text(),'Auto-Pay Annual Contracts - On')]")
	private WebElement annualContractHeader;

	@FindBy(xpath = "//div[@class='price p-1 pl-2 font-weight-normal']")
	private WebElement autoPrice;

	@FindBy(xpath = "(//div[@class='subtotal']/span)[2]")
	private WebElement totalPrice;

	@FindBy(xpath = "//input[@type='radio' and @value='yes']/following-sibling::span")
	private WebElement autoPayYes;

	@FindBy(xpath = "//input[@type='radio' and @value='no']/following-sibling::span")
	private WebElement autoPayNo;

	@FindBy(id = "auto-renew-error-div")
	private WebElement noSelectionARError;

	// price
	@FindBy(xpath = "//div[contains(@class,'subtotals dark')]//div[contains(@class,'subtotal')][contains(text(),'Subtotal')]")
	private WebElement subtotal;

	@FindBy(xpath = "//div[contains(@class,'finalShipping')]")
	private WebElement delivery;

	@FindBy(xpath = "(//div[@class='discount'])[1]")
	private WebElement incldiscounts;

	@FindBy(xpath = "//div[contains(text(),'Estimated Tax:')]")
	private WebElement tax;

	@FindBy(xpath = "//div[contains(@class,'subtotals dark')]//div[contains(@class,'totals')][contains(text(),'Total')]")
	private WebElement totalprice;

	@FindBy(id = "xiFrameHosted")
	private WebElement iframeSQ;

	@FindBy(id = "pmStepUpFrame")
	private WebElement iframeSQ1;

	@FindBy(xpath = "//iframe[contains(@id, 'cardinal-stepUpIframe')]")
	private WebElement iframeSQ2;

	@FindBy(id = "xiFrameTokenHosted")
	private WebElement iframeSQ3;

	// String iframeSQ2= "cardinal-stepUpIframe-1602490235312";

	@FindBy(xpath = "(//div[@class='title'])[1]")
	private WebElement step3Header;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement pswdText3DS1;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement Submit3DS1;

	@FindBy(xpath = "(//div[@class='content pl-10p fs-18p'])[2]")
	private WebElement monthlyHeader;

	@FindBy(xpath = "(//div[@class='price p-1 pl-2'])[2]")
	private WebElement monthlyPrice;
	@FindBy(xpath = "(//div[@class='auto-pay-desc font-weight-normal pb-1'])[2]")
	private WebElement autoDraftTextMonthly;

	@FindBy(xpath = "//header[@id='header']")
	private WebElement header1SQ;

	@FindBy(xpath = "//section[@id='content']/div/h2")
	private WebElement header2SQ;

	@FindBy(xpath = "(//section[@id='content']/div/p)[1]")
	private WebElement sqTextMSG;

	@FindBy(xpath = "//input[@placeholder=' Enter Code Here']")
	private WebElement sqEnterCode;

	@FindBy(xpath = "//input[@value='SUBMIT']")
	private WebElement sqSubmitButton;

	@FindBy(xpath = "//input[@value='CANCEL']")
	private WebElement sqCancelButton;

	@FindBy(xpath = "//input[@value='RESEND CODE']")
	private WebElement sqResendButton;

	@FindBy(xpath = "//div[@class='has-error error']")
	private WebElement erroronCard;

	public CheckOutPage FrictionFlow_Validation_3DS2(ConcurrentHashMap<String, String> CardWithFriction,
			String button) {
		action.setCustomExplicitWait(40);
		action.waitForElementDisplayed(step3Header, "step3Header");
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
		String SecurityQuestionText = action.getAttribute(sqTextMSG, "innerText", "autoDraftText");
		verify.isElementDisplayed(sqTextMSG, "sqTextMSG");
		action.scrollToElement(sqTextMSG, "sqTextMSG");
		if (button.equals("submit")) {
			action.waitForElementClickable(sqSubmitButton);
			if (verify.isElementDisplayed(sqSubmitButton, "sqSubmitButton")) {
				action.scrollToElement(sqSubmitButton, "sqSubmitButton");
				action.inputText(sqEnterCode, "code", CardWithFriction.get("code"));
				action.clickWithClickableWait(sqSubmitButton, "Submit security code");
			}
		} else if (button.equals("cancel")) {
			action.waitForElementClickable(sqCancelButton);
			if (verify.isElementDisplayed(sqCancelButton, "sqCancelButton")) {
				action.scrollToElement(sqCancelButton, "sqCancelButton");
				action.clickWithClickableWait(sqCancelButton, "cancel security code");
				verify.isElementDisplayed(erroronCard, "erroronCard");
			}
		}
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();
		return this;

	}

	public CheckOutPage Validate_ErrorCard_On_Checkout(ConcurrentHashMap<String, String> cardPayment) {
		action.setCustomExplicitWait(40);

		if (verify.isElementDisplayed(errorBanner, "Error message banner")) {
			action.scrollToElement(errorBanner, "Error message banner");
			verify.verifyTextMatchIgnoringCase(cardPayment.get("msg"), action.getText(errorBanner, "error text"),
					"error message text validation");
			action.logPass("Cant place order with Error card");
		}
		action.resetCustomExplicitWait();
		return this;
	}

	public CheckOutPage FrictionFlow_Validation_3DS1(ConcurrentHashMap<String, String> CardWithFriction) {
		action.waitForElementDisplayed(step3Header, "step3Header");
		action.switchToFrame(iframeSQ);
		if (verify.isElementDisplayed(Submit3DS1, "threeDS1Submit")) {
			action.inputText(pswdText3DS1, "code", CardWithFriction.get("code"));
			action.clickWithClickableWait(Submit3DS1, "Submit security code");
		}

		return this;

	}

	public CheckOutPage Auto_Renewal_validation() {
		verify.isElementDisplayed(autoAlarmOn, "Alarm Icon");
		verify.isElementDisplayed(annualContractHeader, "Alarm Icon");
		verify.isElementDisplayed(autoPrice, "autoPrice");
		return this;

	}

	public CheckOutPage Auto_Renewal_Selection(ConcurrentHashMap<String, String> AutoRenewal, String autoval) {
		// action.clickUntilOtherElementInvisible(cardRadioButton, poNumbertxtBox, "Card
		// radio button");
		/*
		 * if (action.matchTextEqualIgnoringcase(autoval, "No")) {
		 * action.clickWithClickableWait(autoPayNo, "Selection for no renewal");
		 * action.logInfo("Opted for No renewal of subscription"); } else { if
		 * (action.matchTextEqualIgnoringcase(autoval, "Yes")) {
		 * action.clickWithClickableWait(autoPayYes, "Selection for no renewal");
		 * action.logInfo("Opted for  renewal of subscription"); } }
		 */
		String autoval1 = autoval;
		switch (autoval1) {
		case "No":
			action.clickWithClickableWait(autoPayNo, "Selection for no renewal");
			action.logInfo("Opted for No renewal of subscription");
			break;
		case "Yes":
			action.clickWithClickableWait(autoPayYes, "Selection for no renewal");
			action.logInfo("Opted for  renewal of subscription");

			break;
		case "NoSelection":
			action.clickWithClickableWait(ccpaymentTypeNextButton, "Payment Type Next Button");
			verify.isElementDisplayed(noSelectionARError, "noSelectionARError");
			action.logInfo("Mandatory field check for Auto renewal");
			break;
		}

		action.scrollToElement(autoRenewalModule, "autoRenewalModule");
		verify.isElementDisplayed(autoRenewalModule, "autoRenewalModule");
		verify.isElementDisplayed(autoRenewalHeader, "autoRenewalHeader");
		verify.isElementDisplayed(autoRenewalPrice, "autoRenewalPrice");
		verify.isElementDisplayed(autoTermsText, "autoTermsText");
		// String autoDraftPaymentText = action.getAttribute(autoDraftText, "innerText",
		// "autoDraftText");

		verify.verifyTextMatch(AutoRenewal.get("msg"), action.getText(autoDraftText, "autorenewal text"),
				"validate autorenewal text");
		// verify.matchTextContains(autoDraftPaymentText, AutoRenewal.get("msg"));
		verify.isElementDisplayed(saveCardText, "saveCardText");
		action.clickWithClickableWait(checkboxSC, "saved card checkbox");
		return this;
	}

	public CheckOutPage Auto_Renewal_Price_Check() {

		float auto_price = 0;
		float total_price = 0;
		String price1 = action.getText(autoPrice, "autoPrice");
		auto_price = utils.priceExtract(price1);
		String price2 = action.getText(totalPrice, "totalPrice");
		total_price = utils.priceExtract(price2);
		verify.matchFloatEquals(auto_price, total_price);
		return this;
	}

	public CheckOutPage Auto_Renewal_noCheck() {

		verify.isElementNotDisplayed(autoRenewalHeader, "autoRenewalHeader");
		action.logPass("No auto renewal functionality available for One time subscription and ebook products");
		return this;
	}

	public CheckOutPage Use_Existing_Billing_Address_CC_SavedCC() {
		action.scrollToElement(ccaddressBookBtn, "Address Book button");
		action.clickWithClickableWait(ccaddressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(useAddressBtnList.get(0), "Use Address button");
		verify.isElementDisplayed(saveCardText, "saveCardText");
		action.clickWithClickableWait(checkboxSC, "saved card checkbox");

		action.clickWithClickableWait(ccpaymentTypeNextButton, "Payment Type Next Button");
		action.switchToDefaultContent();
		return this;
	}

	public CheckOutPage Use_AccountPO_NewSavedCC(ConcurrentHashMap<String, String> cardPayment) {
		action.scrollToElement(useSaveCardEdit, "useSaveCardEdit");
		action.clickWithClickableWait(useSaveCardEdit, "useSaveCardEdit");
		action.clickUntilOtherElementInvisible(cardRadioButton, poNumbertxtBox, "Card radio button");

		action.clickWithClickableWait(useSaveCardButton, "useSaveCardButton");
		action.clickWithClickableWait(useSaveCardClose, "useSaveCardClose");
		action.switchToDefaultContent();
		action.clickWithClickableWait(poRadioButton, "poRadioButton");
		action.waitForElementDisplayed(poNumbertxtBox, "Account PO");
		action.inputText(poNumbertxtBox, "PO Number text box", "8435");

		return this;
	}

	public CheckOutPage Use_Existing_Billing_Address_USESavedCC(ConcurrentHashMap<String, String> cardPayment) {

		// edit and validate the added credit card

		action.scrollToElement(useSaveCardEdit, "useSaveCardEdit");
		action.clickWithClickableWait(useSaveCardEdit, "useSaveCardEdit");
		action.clickUntilOtherElementInvisible(cardRadioButton, poNumbertxtBox, "Card radio button");

		action.clickWithClickableWait(useSaveCardButton, "useSaveCardButton");

		verify.isElementDisplayed(useSaveCardHeader, "useSaveCardHeader");
		for (WebElement webElement : cardNameSaveCard) {

			verify.verifyTextMatchIgnoringCase(cardPayment.get("name"), action.getText(webElement, "card name"),
					"provided credit card details  match in saved details");
		}
		action.clickWithClickableWait(usethisCardButton, "usethisCardButton");
		action.switchToDefaultContent();
		verify.isElementDisplayed(cvvRequiredText, "cvvRequiredText");
		action.inputText(cvvSavedCard, "Card CVV number", cardPayment.get("cvv"));
		action.scrollToElement(ccpaymentTypeNextButton, "Payment Type Next Button");
		action.clickWithClickableWait(ccpaymentTypeNextButton, "Payment Type Next Button");

		return this;
	}

	public CheckOutPage Validate_CheckoutPage() {
		verify.pageTitle("Checkout");
		return this;
	}

	public CheckOutPage verify_FreeShipping_Option() {
		action.getFirstSelected(shippingMethod);
		return this;

	}

	public CheckOutPage Validate_Fulfillment_CheckoutPage(ConcurrentHashMap<String, String> fulfillment) {

		verify.verifyTextMatchIgnoringCase(fulfillment.get("Jeppfulfillment"),
				action.getText(fulfillmentVal1, "Fulfillment1 value on Cart"), "Validate fulfillment1 on Cart");
		verify.verifyTextMatchIgnoringCase(fulfillment.get("Aviallfulfillment"),
				action.getText(fulfillmentVal2, "Fulfillment2 value on Cart"), "Validate fulfillment2 on Cart");
		return this;
	}

	public CheckOutPage Use_Existing_Shipping_Address() {
		action.clickWithClickableWait(addressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(useAddressBtnList.get(0), "Use Address button");
		return this;
	}

	public CheckOutPage Use_Existing_Shipping_Address(String country) {
		action.scrollToElement(addressBookBtn, "Address Book button");
		action.clickWithClickableWait(addressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(findCountryAddress(country, existingShippingAddressList),
				"Use Address button for this " + country);
		return this;
	}

	private WebElement findCountryAddress(String country, List<WebElement> adressList) {
		int i = 0;
		verify.isElementsExists(adressList, "Existing address list panel");
		for (WebElement e : adressList) {
			String text = action.getTextWithoutLogging(e);
			if (text.contains(country)) {
				action.scrollToElement(e, text);
				return useAddressBtnList.get(i);
			}
			i++;
		}
		throw new CustomException("Provided country not found in existing address list :- " + country);
	}

	public CheckOutPage Use_Default_Shipping_Method() {
		action.disableImplicitWait();
		Boolean isExists = action.isElementExistsIgnore(shippingDetailsNextButton, "Shipping details block");
		action.resetImplicitWait();
		if (isExists) {
			action.setCustomExplicitWait(40);
			action.clickUntilInvisible(shippingDetailsNextButton, "Next button under Shipping details block");
			action.resetCustomExplicitWait();
		} else {
			action.logInfo("Shipping Details block does not exists");
		}
		action.switchToDefaultContent();
		return this;
	}

	public CheckOutPage Monthly_AutoRenewal_Validation(ConcurrentHashMap<String, String> AutoRenewal) {
		verify.isElementDisplayed(monthlyHeader, "Auto renewal Monthly header ");
		verify.isElementDisplayed(monthlyPrice, "Auto renewal Monthly price ");
		verify.verifyTextMatch(AutoRenewal.get("monthlyMsg"), action.getText(autoDraftTextMonthly, "autorenewal text"),
				"validate autorenewal text");

		return this;

	}

	public CartPage Edit_this_Cart() {
		action.clickUntilOtherElementEnabled(termsAndConditonsCheckBox, placeOrderButton,
				"Check Terms and Conditions Check Box");
		action.scrollToTopOfPage();
		action.clickLink(editCart);
		return new CartPage();
	}

	public CheckOutPage Use_Account_Payment() {
		action.waitForElementDisplayed(poNumbertxtBox, "Account PO");
		action.inputText(poNumbertxtBox, "PO Number text box", "8435");
		action.switchToDefaultContent();
		return this;
	}

	public CheckOutPage Use_Credit_Card_Payment(ConcurrentHashMap<String, String> cardPayment) {
		// action.waitForElementDisplayed(cardRadioButton, "Card radio button");
		HashMap<String, String> hm = new HashMap<String, String>();
		action.clickUntilOtherElementInvisible(cardRadioButton, poNumbertxtBox, "Card radio button");
		action.switchToFrame(iframe);
		action.switchToFrame(iframe1);
		action.waitForElementDisplayed(cardName, "Card Name");
		action.inputText(cardName, "Card Name", cardPayment.get("name"));
		hm.put("CN", cardPayment.get("name"));
		action.inputText(cardNumber, "Card Number", cardPayment.get("number"));
		action.selectByVisibleText(expiryMonth, cardPayment.get("month"), "card expiry month");

		action.selectByVisibleText(expiryYear, cardPayment.get("year"), "card expiry year");

		action.inputText(cvvNumber, "Card CVV number", cardPayment.get("cvv"));
		ThreadManager.placeholder.get().setHashmap(hm);
		action.switchToDefaultContent();
		return this;
	}

	public CheckOutPage Use_Existing_Billing_Address() {
		Use_Existing_Shipping_Address();
		action.clickWithClickableWait(paymentTypeNextButton, "Payment Type Next Button");
		action.switchToDefaultContent();
		return this;

	}

	public CheckOutPage Use_Existing_Billing_Address(String Country) {
		Use_Existing_Shipping_Address(Country);
		action.clickWithClickableWait(paymentTypeNextButton, "Payment Type Next Button");
		action.switchToDefaultContent();
		return this;

	}

	public CheckOutPage Use_Existing_Billing_Address_PO(String Country) {
		action.scrollToElement(addressBookBtn, "Address Book button");
		action.clickWithClickableWait(addressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(findCountryAddress(Country, existingBillingAddressList),
				"Use Address button for this " + Country);
		action.clickWithClickableWait(paymentTypeNextButton, "Payment Type Next Button");
		action.switchToDefaultContent();
		return this;

	}

	public CheckOutPage Use_Existing_Billing_Address_CC() {
		action.scrollToElement(ccaddressBookBtn, "Address Book button");
		action.clickWithClickableWait(ccaddressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(useAddressBtnList.get(0), "Use Address button");
		action.clickWithClickableWait(ccpaymentTypeNextButton, "Payment Type Next Button");
		action.switchToDefaultContent();
		return this;
	}

	public CheckOutPage Use_Existing_Billing_Address_CC_No_Renewal() {
		action.scrollToElement(ccaddressBookBtn, "Address Book button");
		action.clickWithClickableWait(ccaddressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(useAddressBtnList.get(0), "Use Address button");
		action.clickWithClickableWait(autoPayNo, "Auto pay No");
		action.clickWithClickableWait(ccpaymentTypeNextButton, "Payment Type Next Button");
		action.switchToDefaultContent();
		return this;
	}

	public CheckOutPage Use_Existing_Billing_Address_CC(String country) {
		action.scrollToElement(ccaddressBookBtn, "Address Book button");
		action.clickWithClickableWait(ccaddressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(findCountryAddress(country, existingBillingAddressList),
				"Use Address button for this " + country);
		action.setCustomExplicitWait(40);
		action.clickUntilInvisible(ccpaymentTypeNextButton, "Payment Type Next Button");
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();
		return this;

	}

	public CheckOutPage Use_Existing_Billing_Address_CC_No_Renewal(String country) {
		action.scrollToElement(ccaddressBookBtn, "Address Book button");
		action.clickWithClickableWait(ccaddressBookBtn, "Address Book button");
		action.switchToDefaultContent();
		action.clickWithClickableWait(findCountryAddress(country, existingBillingAddressList),
				"Use Address button for this " + country);
		action.setCustomExplicitWait(40);
		action.clickWithClickableWait(autoPayNo, "Auto pay No");
		action.clickUntilInvisible(ccpaymentTypeNextButton, "Payment Type Next Button");
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();
		return this;

	}

	public CheckOutPage Add_New_Shipping_Address(ConcurrentHashMap<String, String> shippingAddress) {
		action.clickWithClickableWait(shippingCountryDropDown, "Shipping address drop down");
		action.selectCustomDropDown(shippingAddress.get("country"));
		action.inputText(shippingFirstName, "First Name Shipping Address", shippingAddress.get("firstName"));
		action.inputText(shippingLastName, "Last Name shipping address", shippingAddress.get("lastName"));
		action.inputText(shippingAddressLine1, "Address Line 1 shipping address", shippingAddress.get("addressLine1"));
		action.inputText(shippingAddressLine2, "Address Line 2 shipping address",
				shippingAddress.get("addressLine2Optional"));
		action.inputText(shippingTownCity, "TownCity shipping address", shippingAddress.get("towncity"));
		if (!countryWithoutStates.contains(shippingAddress.get("country"))) {
			action.selectByVisibleText(shippingState, shippingAddress.get("state"), "State in Shipping Address");
		}
		action.inputText(shippingPostalCode, "Postal Code shipping address", shippingAddress.get("zipPostCode"));
		action.clickWithClickableWait(shippingCountryCode, "countryCode in Shipping Address");
		action.selectCustomDropDown(shippingAddress.get("countryCode"));
		action.inputText(shippingPhoneNumber, "Phone shipping address", shippingAddress.get("phoneNumber"));
		if (countryWithTax.contains(shippingAddress.get("country"))) {
			action.inputText(taxfield, "Tax value", shippingAddress.get("TAX"));
		}
		action.setCustomExplicitWait(40);
		action.clickUntilInvisible(shippingNextButton, "Next button Shipping Address");
		Boolean addressSuggestionExists = action.isElementExistsIgnore(addressVerifyClose);
		if (addressSuggestionExists) {
			action.switchToDefaultContent();
			action.clickUntilInvisible(replaceAddress, "Replace Address click on Verify address");

		}
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();
		return this;

	}

	public CheckOutPage Add_New_Billing_Address(ConcurrentHashMap<String, String> billingAddress) {
		action.scrollToElement(billinCountry, "Billing Country drop down");
		action.clickWithClickableWait(billinCountry, "Billing Country drop down");
		action.selectCustomDropDown(billingAddress.get("country"));
		action.switchToDefaultContent();
		action.setCustomExplicitWait(40);
		action.waitForElementDisplayed(billingFirstName, "First Name billing Address");
		action.waitForElementVisibility(billingFirstName, "First Name billing Address");
		action.inputText(billingFirstName, "First Name billing Address", billingAddress.get("firstName"));
		action.resetCustomExplicitWait();
		action.inputText(billingLastName, "Last Name billing address", billingAddress.get("lastName"));
		action.inputText(billingline1, "Address Line 1 billing address", billingAddress.get("addressLine1"));
		action.inputText(billingline2, "Address Line 2 billing address", billingAddress.get("addressLine2Optional"));
		action.inputText(billingTownCity, "TownCity billing address", billingAddress.get("towncity"));
		action.inputText(billingPostcode, "Postcode billing address", billingAddress.get("zipPostCode"));
		action.inputText(billingPhone, "Phone billing address", billingAddress.get("phoneNumber"));
		if (!countryWithoutStates.contains(billingAddress.get("country"))) {
			action.selectFirstValue(billingState, "Billing state drop down");
		}
		action.scrollToElement(nextButtonBillingAddress, "Next button billing Address");
		action.setCustomExplicitWait(40);
		action.clickUntilInvisible(nextButtonBillingAddress, "Next button billing Address");
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();
		return this;
	}

	public CheckOutPage Add_New_Billing_Address_CC(ConcurrentHashMap<String, String> billingAddress) {

		action.scrollToElement(ccbillinCountry, "Billing Country drop down");
		action.clickWithClickableWait(ccbillinCountry, "Billing Country drop down");
		action.selectCustomDropDown(billingAddress.get("country"));
		action.switchToDefaultContent();
		action.setCustomExplicitWait(40);
		action.waitForElementDisplayed(ccbillingFirstName, "First Name billing Address");
		action.waitForElementVisibility(ccbillingFirstName, "First Name billing Address");
		action.inputText(ccbillingFirstName, "First Name billing Address", billingAddress.get("firstName"));
		action.resetCustomExplicitWait();
		action.inputText(ccbillingLastName, "Last Name billing address", billingAddress.get("lastName"));
		action.inputText(ccbillingline1, "Address Line 1 billing address", billingAddress.get("addressLine1"));
		action.inputText(ccbillingline2, "Address Line 2 billing address", billingAddress.get("addressLine2Optional"));
		action.inputText(ccbillingTownCity, "TownCity billing address", billingAddress.get("towncity"));
		action.inputText(ccbillingPostcode, "Postcode billing address", billingAddress.get("towncity"));
		action.inputText(ccbillingPhone, "Phone billing address", billingAddress.get("phoneNumber"));
		if (!countryWithoutStates.contains(billingAddress.get("country"))) {
			action.selectFirstValue(billingState, "Billing state drop down");
		}
		action.scrollToElement(ccnextButtonBillingAddress, "Next button billing Address");
		action.setCustomExplicitWait(40);
		action.clickUntilInvisible(ccnextButtonBillingAddress, "Next button billing Address");
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();

		return this;
	}

	public CheckOutPage Add_New_Billing_Address_CC_With_No_Renewal(ConcurrentHashMap<String, String> billingAddress) {
		action.scrollToElement(ccbillinCountry, "Billing Country drop down");
		action.clickWithClickableWait(ccbillinCountry, "Billing Country drop down");
		action.selectCustomDropDown(billingAddress.get("country"));
		action.switchToDefaultContent();
		action.setCustomExplicitWait(40);
		action.waitForElementDisplayed(ccbillingFirstName, "First Name billing Address");
		action.waitForElementVisibility(ccbillingFirstName, "First Name billing Address");
		action.inputText(ccbillingFirstName, "First Name billing Address", billingAddress.get("firstName"));
		action.resetCustomExplicitWait();
		action.inputText(ccbillingLastName, "Last Name billing address", billingAddress.get("lastName"));
		action.inputText(ccbillingline1, "Address Line 1 billing address", billingAddress.get("addressLine1"));
		action.inputText(ccbillingline2, "Address Line 2 billing address", billingAddress.get("addressLine2Optional"));
		action.inputText(ccbillingTownCity, "TownCity billing address", billingAddress.get("towncity"));
		action.inputText(ccbillingPostcode, "Postcode billing address", billingAddress.get("zipPostCode"));
		action.inputText(ccbillingPhone, "Phone billing address", billingAddress.get("phoneNumber"));
		if (!countryWithoutStates.contains(billingAddress.get("country"))) {
			action.selectFirstValue(billingState, "Billing state drop down");
		}
		action.clickWithClickableWait(autoPayNo, "Auto pay No");
		action.scrollToElement(ccpaymentTypeNextButton, "Next button billing Address");
		action.setCustomExplicitWait(40);
		action.clickUntilInvisible(ccpaymentTypeNextButton, "Next button billing Address");
		action.resetCustomExplicitWait();
		action.switchToDefaultContent();
		return this;
	}

	/**
	 * @return
	 * 
	 */
	protected OrderConfirmationPage placeOrderWithoutFetchingOrderNumber() {
		action.waitForElementDisplayed(termsAndConditonsCheckBox, "Check Terms and Conditions Check Box");
		action.clickUntilOtherElementEnabled(termsAndConditonsCheckBox, placeOrderButton,
				"Check Terms and Conditions Check Box");
		try {
			action.setCustomExplicitWait(2);
			action.clickUntilInvisibleThrowFailure(placeOrderButton, "Place Order Button");
			action.resetCustomExplicitWait();
		} catch (TimeoutException e) {
			if (!action.isElementNotExistsIgnore(errorBanner, "Error message banner")) {
				action.scrollToElement(errorBanner, "Error message banner");
			}
			throw new CustomException("Error in placing order", e);
		}

		return new OrderConfirmationPage();
	}

	public CheckOutPage Validate_Price_In_Checkout() {
		float subtotalpriceval = 0;
		float totalpriceval = 0;
		float delval = 0;
		float taxval = 0;
		float discval = 0;
		action.setCustomExplicitWait(40);
		subtotalpriceval = utils.priceExtract(action.getText(subtotal, "Sub total value"));
		action.resetCustomExplicitWait();
		if (action.isElementExistsIgnore(delivery, "Delivery price")) {
			delval = utils.priceExtract(action.getText(delivery, "Delivery price value"));
		}
		if (action.isElementExistsIgnore(incldiscounts, "Discount price")) {
			discval = utils.priceExtract(action.getText(incldiscounts, "Delivery price value"));
		}

		taxval = utils.priceExtract(action.getText(tax, "Tax value"));
		totalpriceval = subtotalpriceval + delval + taxval - discval;
		totalpriceval = Float.parseFloat(new DecimalFormat("0.00").format(totalpriceval));
		verify.matchFloatEquals(utils.priceExtract(action.getText(totalprice, "Total price value")), totalpriceval);
		return this;
	}

	public CheckOutPage saveCardCheck() {
		verify.isElementDisplayed(saveCardText, "saveCardText");
		action.clickWithClickableWait(checkboxSC, "saved card checkbox");
		return this;
	}

}
