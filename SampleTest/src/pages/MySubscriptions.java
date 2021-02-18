package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.CustomException;
import utilities.PageAction;
import utilities.ThreadManager;

public class MySubscriptions extends BasePage {
	public MySubscriptions() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");

		}
	}

	String randomString = null;
	List<LocalDate> list = new ArrayList<LocalDate>();
	LocalDate date = null;
	LocalDate prevDate = null;
	By elementCell = null;
	By elementCellSub = null;
	By contractName = null;
	String subToolTipText = "The contract number defines the set of services that are purchased as a subscription service. "
			+ "The contract number entails the terms of the services and the list of the services along with other account information.";
	List<String> contractNumbers = new ArrayList<>();
	Set<String> tail_nums = new HashSet<String>();
	@FindBy(xpath = "//table[@id='my_subscription_table']/tbody/tr/td/table/tbody/tr/td[4]")
	private WebElement status;
	String cuesele = "//following-sibling::span";
	String eachStatusele = "//table[@id='my_subscription_table']/tbody/tr/td/table/tbody/tr/td[4]";
	By statusList = By.xpath(eachStatusele);
	String subheader = "//table[@id='my_subscription_table']/thead/tr/th";
	String eachSubSatuscell = "//table[@id='my_subscription_subtable']/tbody/tr/td";
	String subcontract1 = "//table[@id='my_subscription_table']/tbody/tr";
	String subcontract2 = "/td/table/tbody/tr/td[1]";
	@FindBy(xpath = "//table[@id='my_subscription_table']/tbody/tr/td/table/tbody/tr/td[5]//following-sibling::span")
	private List<WebElement> dueDatesList;
	@FindBy(xpath = "//a[@class='btn btn-primary btn-gradiant renewBtn mr-0 ml-2 mobile-center ml-mobile-0 text-uppercase fs-12p font-weight-bold']")
	private WebElement renewBtn;
	@FindBy(xpath = "//table[@id='my_subscription_table']/thead/tr/th")
	private List<WebElement> mySubHeaderName;
	@FindBy(xpath = "//table[@id='my_subscription_table']")
	private WebElement mySubMenu;
	@FindBy(xpath = "//ul[@class='pagination']")
	private WebElement paginationMenu;
	@FindBy(xpath = "//div[@class='contract-found hidden-xs']/span")
	private WebElement resultHeader;
	@FindBy(xpath = "//table[@id='my_subscription_subtable']/tbody/tr/td[1]")
	private List<WebElement> contracts;
	@FindBy(xpath = "//li[@class='pagination-next']/a")
	private WebElement paginationNext;
	@FindBy(xpath = "//li[@class='pagination-next disabled']/a")
	private WebElement paginationDisbaled;
	@FindBy(xpath = "//h4[text()='Contract']/button")
	private WebElement subToolTip;
	@FindBy(xpath = "//span[contains(text(),'VIEW MORE')]")
	private List<WebElement> subViewmore;
	@FindBy(xpath = "//span[contains(text(),'VIEW LESS')]")
	private List<WebElement> subViewless;
	@FindBy(xpath = "//table[@id='my_subscription_subtable']/tbody/tr[@class='view-more']/preceding-sibling::tr")
	private WebElement subscriptionItemslist;
	String subscriptionItems = "//table[@id='my_subscription_subtable']/tbody/tr[@class='view-more']/preceding-sibling::tr";
	@FindBy(xpath = "//iframe[@id='xiFrameHosted']")
	private WebElement creditcardframe;
	@FindBy(xpath = "//iframe[@id='xieCommFrame']")
	private WebElement creditcardframe1;
	@FindBy(xpath = "//input[@id='c-cardname']")
	private WebElement creditcardname;
	@FindBy(xpath = "//input[@id='c-cardname']")
	private WebElement cardName;
	@FindBy(xpath = "//input[@id='c-cardnumber']")
	private WebElement cardNumber;
	@FindBy(xpath = "//select[@id='c-exmth']")
	private WebElement expiryMonth;
	@FindBy(xpath = "//select[@id='c-exyr']")
	private WebElement expiryYear;
	@FindBy(xpath = "//input[@id='c-cvv']")
	private WebElement cvvNumber;
	@FindBy(xpath = "//table[@id='my_subscription_table']//tbody/tr[1]//table//a[contains(text(),'SEE DETAILS')][1]")
	private WebElement seeDetailsBtn;
	@FindBy(xpath = "//table[@id='my_subscription_table']//tbody//tr[1]//table//tbody//tr[1]//td[2]")
	private WebElement TailNoHead;
	@FindBy(xpath = "//table[@id='my_subscription_table']//tbody//tr[1]//table//tbody//tr[1]//td[4]")
	private WebElement StatusHead;
	@FindBy(xpath = "//table[@id='my_subscription_table']//tbody//tr[1]//table//tbody//tr[1]//td[3]")
	private WebElement serviceDescriptionHead;
	@FindBy(xpath = "//div[@class='font-weight-bold pb-1 fs-1_2em']")
	private WebElement serviceDescriptionValue;
	@FindBy(xpath = "(//div[@class='mb-1 tailHeaderView']//div[@class='pull-left']//h3//a)[1]")
	private WebElement tailNo;
	@FindBy(xpath = "//a[@class='toggleViewHandler font-weight-bold blue-bold-link'][1]")
	private WebElement subscriptionDetailsLink;
	@FindBy(xpath = "(//div[@class='tail-content border-top-light-grey pt-2 mt-2'])[1]")
	private WebElement tailContent;
	@FindBy(xpath = "//div[@class='col-sm-6']")
	private WebElement tailContainer;
	@FindBy(xpath = "//span[@class='data activationKey']")
	private WebElement activationKey;
	@FindBy(xpath = "//strong[contains(text(),'Status')]")
	private WebElement Status;
	@FindBy(xpath = "//table[@id='my_subscription_table']//tbody//tr[1]//table//tr[3]//div[@class='pb-2 activationKeyStatusLeftPanel']//span[@class='data status']")
	private WebElement StatusValue;
	@FindBy(xpath = "//small[ text()='Billing Address']")
	private WebElement billingAddress;
	@FindBy(xpath = "//small[ text()='Shipping Address']")
	private WebElement shippingAddress;

	@FindBy(xpath = "//div[@class='account-section-content content-empty']")
	private WebElement nosubscriptionmessage;

	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//*[contains(text(),'Invoice #')]")
	private WebElement LBLInvoiceNo;

	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//h4[contains(text(),'Balance Due')]")
	private WebElement LBLBalanceDue;

	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//a[contains(@class,'payWithCard')]")
	private WebElement LNKPayWithCard;

	@FindBy(xpath = "//a[contains(@href,'/my-account/subscriptions?sort=contractEndDate&sortOrder=desc')]")
	private WebElement LNKSortByDueDate;

	@FindBy(xpath = "(//a[contains(text(),'SEE DETAILS')])[1]")
	private WebElement LNKSeeDetails;

	@FindBy(xpath = "//table[@class='subscription-subtable']")
	private List<WebElement> subscriptionsList;

	@FindBy(xpath = "(//i[@class='material-icons alarm-on'])[1]")
	private WebElement alarmIcon;

	@FindBy(xpath = "(//tr[@class='p-mobile-0'])[2]//a[@class='pull-left blue-bold-link d-block pt-27p pl-2 d-flex auto-payment-info toggleViewHandler d-show-mobile-up']")
	private WebElement paymentInforow2;

	@FindBy(xpath = "(//tr[@class='p-mobile-0'])[1]//a[@class='pull-left blue-bold-link d-block pt-27p pl-2 d-flex auto-payment-info toggleViewHandler d-show-mobile-up']")
	private WebElement paymentInforow1;

	@FindBy(xpath = "(//div[contains(@class,'tailview')]//button[contains(text(),'ADD COVERAGE')])[2]")
	private WebElement BTNAddCoverage;

	@FindBy(xpath = "//div[contains(@class,'coverageConfDialogue')]//h3[contains(text(),'Add Coverage')]")
	private WebElement LBLAddCoverage;

	@FindBy(xpath = "//div[contains(@class,'coverageConfDialogue')]//i[contains(@class,'glyphicon-remove')]")
	private WebElement LNKCloseAddCoverage;

	@FindBy(xpath = "//div[contains(@class,'coverageConfDialogue')]//a[contains(@href,'/databaseproductsearch?tailId')]")
	private WebElement BTNStartNewCoverage;

	@FindBy(xpath = "//div[contains(@class,'coverageConfDialogue')]//a[contains(@href,'/addcoverage?')]")
	private WebElement BTNAddNewCoverage;

	private String paymentInfo = "PAYMENT INFORMATION";
	private String nosubscriptionmsg = "There are no subscriptions associated to your account. If you have subscriptions which should be displaying, please try loading this subscriptions page again in a few minutes. For additional assistance please contact us.";

	// Auto-Renewal

	@FindBy(xpath = "(//div[@class='payment-info fs-16p pb-mobile-1']/p)[1]")
	private WebElement paymentTypeAR;

	@FindBy(xpath = "(//div[@class='payment-info fs-16p pb-mobile-1']/p)[2]")
	private WebElement paymentCardExpAR;

	@FindBy(xpath = "(//div[@class='subscription-billing-info fs-16p ml-3 ml-mobile-0'])[1]/p")
	private List<WebElement> billingAddressAR;

	@FindBy(xpath = "//button[@class='button-primary small-button mt-1  saved-payments text-white ']")
	private WebElement useASavedCard;

	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement useSavedCardHeader;

	@FindBy(xpath = "//div[@class='font-weight-normal stay']/div/strong")
	private WebElement cardNameOnPopUp;

	@FindBy(xpath = "//div[@class='font-weight-normal stay']/div/br")
	private List<WebElement> cardDetailsOnPopUp;

	@FindBy(xpath = "(//div[@class='font-weight-normal']/div)[1]")
	private WebElement popUpDetails;

	@FindBy(xpath = "(//div[@class='font-weight-normal stay']/div/br[2])")
	private List<WebElement> cardExpOnPopUp;

	String cardEXP = "(//div/br[2]/following-sibling::text())[1]";
	By cardEXPS = By.xpath(cardEXP);

	@FindBy(xpath = "//div[@class='pt-10p']")
	private WebElement addressOnPopUp;

	@FindBy(xpath = "(//div[@id='savedcardmodal']//button[@type='button'])[1]")
	private WebElement closePopUp;

	@FindBy(xpath = "(//div[@id='savedcardmodal']//button[@type='button'])[2]")
	private WebElement useThisCard;

	@FindBy(xpath = "//span[@class='hint-text-success']")
	private WebElement updatedText;

	@FindBy(xpath = "//div[@class='alert alert-info alert-dismissable getAccAlert']")
	private WebElement confirmationMSG;

	@FindBy(xpath = "(//button[@class='button-tertiary add-new-card border-none p-0'])[2]")
	private WebElement addDifferentCard;

	// Billing address details
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//input[@id='address.firstName.ap']")
	private WebElement billingFirstName;
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//input[@id='address.surname.ap']")
	private WebElement billingLastName;
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//input[@id='billTo_street1']")
	private WebElement billingline1;
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//input[@id='billTo_street2']")
	private WebElement billingline2;
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//input[@id='billTo_city']")
	private WebElement billingTownCity;
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//input[@id='billTo_postalCode']")
	private WebElement billingPostcode;
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//span[@class='select2-selection select2-selection--single']")
	private WebElement billinCountry;
	@FindBy(xpath = "//div[@id='invoicePopupContainer']//select[@id='billTo_state']")
	private WebElement billingState;
	
	private ArrayList<String> countryWithoutStates = new ArrayList<String>(
			Arrays.asList("Germany", "France", "New Zealand", "Brazil"));


	@FindBy(xpath = "//label[@class='checkbox_container']/span")
	private WebElement saveCardforFuture;

	@FindBy(xpath = "(//button[contains(@class,'btn-primary btn-gradiant')])[1]")
	private WebElement doneButtonBillingAddress;

	@FindBy(xpath = "//div[@class='has-error error']")
	private WebElement errorMessage;

	@FindBy(xpath = "(//a[contains(@class,'auto-payment-info')])[1]")
	protected WebElement autoPayLink;
	@FindBy(xpath = "//div[contains(text(),'Auto-Pay')]")
	protected WebElement autoPayLabel;
	@FindBy(xpath = "//button[@class='button-primary small-button mt-1  saved-payments text-white ']")
	protected WebElement useSavedCardBtn;
	@FindBy(xpath = "(//button[contains(@class,'add-new-card')])[1]")
	protected WebElement addNewCardBtn;
	@FindBy(xpath = "(//button[@class='button-tertiary add-new-card border-none p-0'])[2]")
	protected WebElement addDiffCardBtn;
	@FindBy(xpath = "//div[@id='savedcardmodal']//h4")
	protected WebElement savedcardLabel;
	@FindBy(xpath = "(//div[contains(@class,'preAuthSavedCardMsg')]//h5)[1]")
	protected WebElement preAuthNote;
	@FindBy(xpath = "(//button[contains(text(),'USE THIS CARD')])[1]")
	protected WebElement useThisCardBtn;
	@FindBy(xpath = "//span[@class='hint-text-success']")
	protected WebElement updatedLbl;

	// Friction:

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

	public MySubscriptions Access_AutoPayInfo() {
		action.clickWithoutClickableWait(autoPayLink, "Auto Pay Link");
		return this;
	}

	public MySubscriptions Validate_AutoPay() {
		verify.isElementDisplayed(autoPayLabel, "Auto Pay Label");
		verify.isElementDisplayed(useSavedCardBtn, "Saved Card Button");
		verify.isElementDisplayed(addDiffCardBtn, "Add Different Card Button");
		return this;
	}

	public MySubscriptions Vaildate_PreAuthMessage(String preAuthMsg) {
		action.clickWithClickableWait(useSavedCardBtn, "Saved Card Button");
		verify.isElementDisplayed(savedcardLabel, "Saved Card Label");
		if (verify.verifyTextContains(preAuthMsg, preAuthNote.getText(),
				"Pre Auth Alert for Saved Credit Card Usage.")) {
			action.clickWithClickableWait(useThisCardBtn, "USE THIS CARD");
			action.waitForElementInvisibility(savedcardLabel);
			action.logPass("Successfully verified Pre Auth Alert for Saved Credit Card Usage. ");
		} else {
			throw new CustomException("Pre Auth Alert for Saved Credit Card Usage verification failed.");
		}
		return this;
	}

	public MySubscriptions Verify_SavedCard_Updated() {
				verify.isElementDisplayed(updatedLbl, "Updated");
		return this;
	}

	public MySubscriptions Validate_Security_Popup_On_Add_Diff_CC(ConcurrentHashMap<String, String> cardPayment,
			ConcurrentHashMap<String, String> billingAddress) {
		action.clickWithClickableWait(addDiffCardBtn, "Add Another Card");
		action.waitForElementVisibility(creditcardframe, "creditcardframe");
		action.switchToFrame(creditcardframe);
		action.switchToFrame(creditcardframe1);
		action.scrollToElement(creditcardname, "Card Name");
		action.waitForElementDisplayed(cardName, "Card Name");
		action.inputText(cardName, "Card Name", cardPayment.get("name"));
		action.inputText(cardNumber, "Card Number", cardPayment.get("number"));
		action.selectByVisibleText(expiryMonth, cardPayment.get("month"), "card expiry month");
		action.selectByVisibleText(expiryYear, cardPayment.get("year"), "card expiry year");
		action.inputText(cvvNumber, "Card CVV number", cardPayment.get("cvv"));
		addBillingaddressDetails(billingAddress);
		return this;
	}

	public MySubscriptions Verify_AutoPayment_On_Subscriptions() {
		HashMap<String, String> hm = ThreadManager.placeholder.get().getHashmap();
		verify.isElementDisplayed(alarmIcon, "alarmIcon");
		action.clickWithClickableWait(alarmIcon, "alarmIcon");

		if (!billingAddressAR.isEmpty()) {
			action.logInfo("Billing information is displayed");
		}
		String cardNamesub1 = action.getText(paymentTypeAR, "CardType");
		String cardNamesub = cardNamesub1.substring(0, cardNamesub1.length() - 20);
		String cardExpMont = action.getText(paymentCardExpAR, "CardType");
		String cardExpMonth[] = cardExpMont.split("/");
		String ExpMonth = cardExpMonth[0];
		ExpMonth = ExpMonth.replace("Expires ", "");
		String ExpYr = "20" + cardExpMonth[1];

		List<String> cardDetails = new ArrayList<String>();
		cardDetails.add(cardNamesub);
		cardDetails.add(cardNamesub1);
		cardDetails.add(cardExpMont);
		verify.verifyTextContains(hm.get("CN"), cardNamesub, "Matching Card Name");
		verify.verifyTextContains(hm.get("EM"), ExpMonth, "Matching Month Name");
		verify.verifyTextContains(hm.get("EY"), ExpYr, "Matching Year Name");
		List<String> cardDetailsonPop = captureUseSaveCardDetails();

		for (int i = 0; i < cardDetailsonPop.size(); i++) {
			verify.verifyTextContains(cardDetails.get(i), cardDetailsonPop.get(i), "text matches");
		}
		action.waitForElementClickable(closePopUp);
		action.clickWithClickableWait(closePopUp, "closePopUp");
		action.switchToDefaultContent();

		return this;
	}


	private List<String> captureUseSaveCardDetails() {
		action.waitForElementClickable(useASavedCard);
	//	action.scrollToElement(useASavedCard, "useASavedCard");
		action.clickWithClickableWait(useASavedCard, "click on saved card");
		action.switchToDefaultContent();
		action.waitForElementDisplayed(useSavedCardHeader, "Pop up for using saved card");
		verify.isElementDisplayed(useSavedCardHeader, "Pop up for using saved card");

		String cardDetailsOnPopUP = action.getText(popUpDetails, "text");
		String cardDetailsOnPopUP1[] = cardDetailsOnPopUP.split("\\R");
		String cardName = cardDetailsOnPopUP1[0];
		String cardNum = cardDetailsOnPopUP1[1];
		String cardExp = cardDetailsOnPopUP1[2];
		List<String> cardDetailsonPop = new ArrayList<String>();
		cardDetailsonPop.add(cardName);
		cardDetailsonPop.add(cardNum);
		cardDetailsonPop.add(cardExp);
		return cardDetailsonPop;
	}

	public MySubscriptions Verify_Use_Saved_Card() {
		action.scrollToElement(alarmIcon, "alram icon");
		action.clickWithClickableWait(alarmIcon, "alarmIcon");
		action.clickWithClickableWait(alarmIcon, "alarmIcon");
		List<String> cardDetailsonPop = captureUseSaveCardDetails();
		action.waitForElementClickable(useThisCard);
		action.clickWithClickableWait(useThisCard, "useThisCard");
		action.switchToDefaultContent();

		verify.isElementDisplayed(updatedText, "Updated text value");
		verify.isElementDisplayed(confirmationMSG, "Header Message");

		String cardNamesub1 = action.getText(paymentTypeAR, "CardType");
		String cardNamesub = cardNamesub1.substring(0, cardNamesub1.length() - 20);

		for (String string : cardDetailsonPop) {
			if (string.contains(cardNamesub)) {
				action.logInfo("Cardname matches with pop up");
				break;
			}
		}

		return this;
	}

	public MySubscriptions Verify_No_AutoPaymentInfo_On_Subscription() {
		verify.isElementNotDisplayed(alarmIcon, "alarmIcon");
		return this;
	}

	public MySubscriptions Verify_No_SubscriptionsMessage() {
		verify.verifyTextFromElementIgnoringCase(nosubscriptionmessage, nosubscriptionmsg,
				"Validate No Subscriptions message");
		return this;
	}

	public MySubscriptions Verify_status_renew_button() {
		randomString = utils.getTimestamp();
		action.clickWithClickableWait(LNKSortByDueDate, "Sort By Due Date");
		verifySubscriptionStatus();
		return this;
	}

	// Method to verify PAY WITH CARD link on OPEN Invoice Details page
	public PayInvoiceDetailsPage Navigate_To_Invoices_From_Subscriptions_Renew() {
		// action.clickWithClickableWait(LNKSortByDueDate, "Sort By Due Date");
		action.clickWithClickableWait(renewBtn, "Renew");
		return new PayInvoiceDetailsPage();
	}

	// Method to access Add Coverage button
	public AddCoveragePage Access_Add_Coverage() {
		action.clickWithClickableWait(LNKSeeDetails, "See Details link");
		action.waitForElementDisplayed(BTNAddCoverage, "Add Coverage button");
		action.clickWithClickableWait(BTNAddCoverage, " Add Coverage button");
		verify.waitForElementDisplayed(LBLAddCoverage, "Add Coverage Dialogue Box");
		verify.isElementDisplayed(LNKCloseAddCoverage, "Close Add Coverage Dialogue Box link");
		verify.isElementDisplayed(BTNStartNewCoverage, "Start New Coverage button");
		verify.isElementDisplayed(BTNAddNewCoverage, "Add New Coverage button");
		action.clickWithClickableWait(BTNAddNewCoverage, "Add New Coverage button");
		return new AddCoveragePage();
	}

	// Method to Access Start New Coverage or Subscription
	public BuyDatabasePage Start_New_Coverage() {
		action.clickWithClickableWait(LNKSortByDueDate, "Sort By Due Date");
		action.clickWithClickableWait(LNKSeeDetails, "See Details link");
		action.waitForElementDisplayed(BTNAddCoverage, "Add Coverage button");
		action.clickWithClickableWait(BTNAddCoverage, "Add Coverage button");
		verify.waitForElementDisplayed(LBLAddCoverage, "Add Coverage Dialogue Box");
		action.clickWithClickableWait(BTNStartNewCoverage, "Add New Coverage button");
		return new BuyDatabasePage();
	}

	public MySubscriptions Verify_SeeDetails_Link() {
		action.clickWithClickableWait(seeDetailsBtn, "See Details");
		verify.isElementDisplayed(tailContainer, "Tail Container");
		verify.isElementDisplayed(tailContent, "Tail Content");
		verify.isElementDisplayed(tailNo, "Tail no");
		if (action.getText(TailNoHead, "Tail No Head Value").equals(action.getText(tailNo, "Tail no"))
				&& action.getText(serviceDescriptionHead, "Service Description Head Value")
						.equals(action.getText(serviceDescriptionValue, "Service Description Value"))) {
			action.logPass("Tail number and Service Description value in Table is same as that under See Details link");
		} else {
			action.logFailure(
					"Tail number and Service Description value in Table is NOT same as that under See Details link");
		}
		// verify.isElementDisplayed(activationKey, "Activation Key");
		verify.isElementDisplayed(Status, "Status");
		verify.isElementDisplayed(billingAddress, "Billing Address");
		verify.isElementDisplayed(shippingAddress, "Shipping Address");
		action.clickWithClickableWait(subscriptionDetailsLink, "SUBSCRIPTION DETAILS");
		verify.isElementNotDisplayed(tailContent, "Tail Content");
		action.clickWithClickableWait(seeDetailsBtn, "See Details");
		verify.isElementNotDisplayed(tailContainer, "Tail Container");

		return this;
	}

	public MySubscriptions Validate_PaymentInfo_Renewal_Subscriptions() {
		int i = 1;
		action.logInfo("Total blocks" + subscriptionsList.size());
		for (WebElement e : subscriptionsList) {
			if (action.isElementExistsIgnore(
					By.xpath("(//table[@class='subscription-subtable'])[" + i + "]/tbody/tr[2]/td[3]"))) {
				verify.isElementDisplayed(seeDetailsBtn, "See Details link");
				verify.isElementNotDisplayed(By.xpath(
						"(//a[@class='pull-left blue-bold-link d-block toggleViewHandler pt-27p pb-2 d-show-mobile-up subscriptionDetailSrcEle'])["
								+ i + "]/../a//i[@class='material-icons alarm-on']"),
						"Alarm icon in subscriptions  block");

			} else {
				verify.isElementDisplayed(seeDetailsBtn, "See Details link");
				/*verify.isElementDisplayed(By.xpath(
						"(//a[@class='pull-left blue-bold-link d-block toggleViewHandler pt-27p pb-2 d-show-mobile-up subscriptionDetailSrcEle'])["
								+ i + "]/../a//i[@class='material-icons alarm-on']"),
						"Alarm icon in subscriptions  block");*/
			}
			i++;
		}
		return this;
	}

	public MySubscriptions verify_Subscription_Status() {
		verifySubscriptionStatus();
		return this;
	}

	public MySubscriptions Add_Credit_Card_Payment_Autopay(ConcurrentHashMap<String, String> cardPayment,
			ConcurrentHashMap<String, String> billingAddress) {

		action.switchToDefaultContent();
		action.setCustomExplicitWait(40);
		action.scrollToElement(alarmIcon, "alarmIcon");
		action.waitForElementClickable(addDifferentCard);
		action.clickWithClickableWait(addDifferentCard, "add new card");
		action.waitForElementVisibility(creditcardframe, "creditcardframe");
		action.switchToFrame(creditcardframe);
		action.switchToFrame(creditcardframe1);
		// action.scrollToElement(creditcardname, "Card Name");
		action.waitForElementDisplayed(cardName, "Card Name");
		action.inputText(cardName, "Card Name", cardPayment.get("name"));
		action.inputText(cardNumber, "Card Number", cardPayment.get("number"));
		action.selectByVisibleText(expiryMonth, cardPayment.get("month"), "card expiry month");
		action.selectByVisibleText(expiryYear, cardPayment.get("year"), "card expiry year");
		action.inputText(cvvNumber, "Card CVV number", cardPayment.get("cvv"));
		action.resetCustomExplicitWait();
		addBillingaddressDetails(billingAddress);
		// action.waitForElementDisplayed(alarmIcon, "alarmIcon");
		action.scrollToElement(alarmIcon, "alarmIcon");

		// Boolean errorMsg = verify.isElementDisplayed(errorMessage, "Error message on
		// screen");
		if (action.isElementExistsIgnore(errorMessage)) {
			action.logInfo(cardPayment.get("name") + "card details not applicable");
		} else {
			addedCardValidation(cardPayment);
		}

		return this;

	}

	private void addBillingaddressDetails(ConcurrentHashMap<String, String> billingAddress) {
		action.switchToDefaultContent();
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
		action.clickWithClickableWait(saveCardforFuture, "saveCardforFuture");
		action.clickWithClickableWait(billingState, "Billing state drop down");

		if (!countryWithoutStates.contains(billingAddress.get("country"))) {
			action.selectByVisibleText(billingState, billingAddress.get("state"), "State in Billing Address");
		}
		// action.selectCustomDropDown(billingAddress.get("state"));
		// action.selectByVisibleText(billingState, billingAddress.get("state"), "state
		// name");
		action.switchToDefaultContent();
		done_addCreditCard();
	}

	private void done_addCreditCard() {
		// action.setCustomExplicitWait(40);
		// action.clickUntilInvisible(doneButtonBillingAddress, "DONE button Billing
		// Address");
		action.jsClick(doneButtonBillingAddress, "DONE button Billing Address");
		// action.resetCustomExplicitWait();
		action.switchToDefaultContent();
	}


	//private void 
	public  MySubscriptions Validate_FrictionCC_AuthPopup(ConcurrentHashMap<String, String> CardWithFriction, String button) {
		if (action.isElementExistsIgnore(iframeSQ)) {
			action.switchToFrame(iframeSQ);
		} else if (action.isElementExistsIgnore(iframeSQ3)) {
			action.switchToFrame(iframeSQ3);
		}
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
			if (verify.isElementDisplayed(sqSubmitButton, "sqSubmitButton")) {
				action.scrollToElement(sqSubmitButton, "sqSubmitButton");
				action.clearTextBox(sqEnterCode, "CODE textBox");
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
		return this;
	}

	private void addedCardValidation(ConcurrentHashMap<String, String> cardPayment) {
		action.switchToParentTab();
		action.scrollToElement(updatedText, "Updated text on UI");
		verify.isElementDisplayed(updatedText, "Updated text value");
		verify.isElementDisplayed(confirmationMSG, "Header Message");

		String cardNamesub1 = action.getText(paymentTypeAR, "CardType");
		String cardNamesub = cardNamesub1.substring(0, cardNamesub1.length() - 20);

		boolean isFound = cardPayment.get("name").indexOf(cardNamesub) != -1 ? true : false;
		if (isFound) {
			action.logInfo("Card value mataches");
		}
		// verify.verifyTextContainsIgnoreCase(cardPayment.get("name"), cardNamesub,
		// "Updated card detials are reflected");
	}

	public MySubscriptions Verify_My_Subscriptions_Column_Names() {
		System.out.println(mySubHeaderName.size());
		for (WebElement ele : mySubHeaderName) {
			String headerName = action.getText(ele, "status");
			if (headerName.contains("Contract") || headerName.contains("Tail")
					|| headerName.contains("Service Description") || headerName.contains("Status")
					|| headerName.contains("Due Date")) {
				ThreadManager.logger.get().info(headerName + " is displayed");
			} else {
				ThreadManager.logger.get()
						.fail(MarkupHelper.createLabel(headerName + " is not displayed", ExtentColor.RED));
			}
		}

		return this;
	}

	public MySubscriptions Verify_My_Subscriptions_tool_tip() {

		String subtooltip = action.getAttribute(subToolTip, "data-original-title", "subToolTip");
		if (subtooltip.contains(subToolTipText)) {
			ThreadManager.logger.get().info("tool tip message is validated");
		} else {
			ThreadManager.logger.get().fail(MarkupHelper.createLabel(" toolt tip msg is not valid", ExtentColor.RED));
		}

		return this;
	}

	public MySubscriptions Verify_My_Subscriptions_viewLess_viewMore() {

		try {
			for (WebElement webElement : subViewmore) {
				List<WebElement> subItems = action.findElements(elementCellSub, "Subscription item");
				int count = subItems.size();
				if (count > 4) {
					ThreadManager.logger.get()
							.info("View more is available only when subscription items are greaterthan four");
					action.scrollToElement(webElement, "view more");
					action.clickWithClickableWait(webElement, "view more");

				} else {
					ThreadManager.logger.get().fail(MarkupHelper.createLabel(
							"View more is available when subscription items are lessthan four", ExtentColor.RED));

				}
			}

			for (WebElement webElement : subViewless) {
				action.clickWithClickableWait(webElement, "View less");
			}
		} catch (Exception e) {
			throw new CustomException("No subscriptions have view more details", e);
		}

		return this;
	}

	public MySubscriptions VerifyContractsNotinExpiredStatus() {
		int i = 1;
		for (WebElement ele : mySubHeaderName) {
			By elem = By.xpath(subheader + "[" + i + "]");
			action.disableImplicitWait();
			String headerName = action.getText(elem, "header");
			if (headerName.equalsIgnoreCase("Status")) {
				elementCell = By.xpath(eachSubSatuscell + "[" + i + "]");
				List<WebElement> celllist = action.findElements(elementCell, "cell list");
				action.disableImplicitWait();
				for (WebElement element : celllist) {
					String cellName = action.getTextWithoutLogging(element);
					if (cellName.equalsIgnoreCase("EXPIRED")) {
						i = i + 1;
						contractName = By.xpath(subcontract1 + "[" + i + "]" + subcontract2);
						ThreadManager.logger.get()
								.fail(MarkupHelper.createLabel(
										"Expired Status is displayed for " + action.getText(contractName, "contract"),
										ExtentColor.RED));

					} else if (cellName.equalsIgnoreCase("BOOKED") || (cellName.equalsIgnoreCase("Active"))
							|| (cellName.equalsIgnoreCase("Entered"))) {
						ThreadManager.logger.get().info("Status is" + cellName);

					}
				}

			}
			i++;
		}

		return this;
	}

	public MySubscriptions VerifyDueDateColumnDisplayOrder() {
		int i = 1;
		for (WebElement ele : mySubHeaderName) {
			By elem = By.xpath(subheader + "[" + i + "]");
			action.disableImplicitWait();
			String headerName = action.getText(elem, "header");
			if (headerName.contains("Due Date")) {
				elementCell = By.xpath(eachSubSatuscell + "[" + i + "]");
				List<WebElement> celllist = action.findElements(elementCell, "cell list");
				action.disableImplicitWait();
				for (WebElement element : celllist) {

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
					// Create a calendar object with today date. Calendar is in java.util package.
					Calendar calendar = Calendar.getInstance();
					// Move calendar to yesterday
					calendar.add(Calendar.DATE, -1);
					// Get current date of calendar which point to the yesterday now
					String pattern = "dd-MMM-yyyy";
					// Create an instance of SimpleDateFormat used for formatting
					// the string representation of date according to the chosen pattern
					DateFormat df = new SimpleDateFormat(pattern);
					Date yesterday = calendar.getTime();
					String yesterdayAsString = df.format(yesterday);
					prevDate = LocalDate.parse(yesterdayAsString, formatter);
					if (!action.getTextWithoutLogging(element).isEmpty()) {
						String dd = action.getTextWithoutLogging(element).replace(" ", "-");
						date = LocalDate.parse(dd, formatter);
						if (date.equals(prevDate)) {
							ThreadManager.logger.get().fail(
									MarkupHelper.createLabel("cant have expired date displayed", ExtentColor.RED));
						} else {
							list.add(date);
						}
					}

				}

			} else if (headerName.contains("Contract")) {
				elementCell = By.xpath(eachSubSatuscell + "[" + i + "]");
				List<WebElement> celllist = action.findElements(elementCell, "cell list");
				action.disableImplicitWait();
				for (WebElement contract : celllist) {
					if (!action.getTextWithoutLogging(contract).isEmpty()) {
						contractNumbers.add(action.getTextWithoutLogging(contract));
					}
				}

			}
		}
		for (int j = 0; j < list.size() - 1; j++) {
			if (list.get(j).compareTo(list.get(j + 1)) > 0) {
				ThreadManager.logger.get().fail(
						MarkupHelper.createLabel(list.get(j) + " is greater than " + list.get(j + 1), ExtentColor.RED));
				ThreadManager.logger.get().info(list.get(j) + " is greater than " + list.get(j + 1));
			} else if (list.get(j).compareTo(list.get(j + 1)) == 0) {
				ThreadManager.logger.get().info(list.get(j) + " and " + list.get(j + 1)
						+ " are equal. Hence verifying Respective Contract Numbers");
				if (contractNumbers.get(j).compareTo(contractNumbers.get(j + 1)) > 0) {
					ThreadManager.logger.get().fail(MarkupHelper.createLabel(
							contractNumbers.get(j) + " is less than " + contractNumbers.get(j + 1), ExtentColor.RED));
				}
			}
			i++;
		}
		return this;
	}

	public MySubscriptions Validate_Pagination_Tail_number_My_Subscriptions() {
		verify.isElementDisplayed(mySubMenu, "My Subscription Menu");
		verify.isElementDisplayed(paginationMenu, "Pagination");
		if (contractNumbers.size() != 5) {
			ThreadManager.logger.get().info("Total No.of Contract Numbers Per Page is " + contractNumbers.size());
		}
		int i = 1;
		for (WebElement ele : mySubHeaderName) {
			By elem = By.xpath(subheader + "[" + i + "]");
			action.disableImplicitWait();
			String headerName = action.getTextWithoutLogging(elem);
			if (headerName.contains("Tail")) {
				elementCell = By.xpath(eachSubSatuscell + "[" + i + "]");
				List<WebElement> celllist = action.findElements(elementCell, "cell list");
				action.disableImplicitWait();
				for (WebElement element : celllist) {
					if (!action.getTextWithoutLogging(element).isEmpty()) {
						tail_nums.add(action.getTextWithoutLogging(element));
					} else if (action.getTextWithoutLogging(element).isEmpty()) {
						ThreadManager.logger.get().info("this account has no tail numbers");
					}

				}

			}
			i++;
		}
		return this;
	}

	public MySubscriptions Validate_Pagination_MySubscription_Results() {

		while (action.isElementExistsIgnore(resultHeader)) {
			String displayText = action.getTextWithoutLogging(resultHeader);
			displayText = displayText.replaceAll("[^\\d.]", "");
			char c = displayText.charAt(0);
			int count = Character.getNumericValue(c);
			int count1 = 0;
			for (WebElement contract : contracts) {
				if (!action.getTextWithoutLogging(contract).isEmpty()) {
					count1++;
				}
			}
			if (count != count1) {
				ThreadManager.logger.get().fail(MarkupHelper.createLabel(
						"count displayed on header is not equal to the contracts on page", ExtentColor.RED));
			} else {
				ThreadManager.logger.get().info("count displayed on header matches contracts");
				if (action.isElementExistsIgnore(paginationNext))
					action.clickWithClickableWait(paginationNext, "clicks on next");
				else
					ThreadManager.logger.get().info("Pagination is over");
				break;
			}
			if (action.isElementExistsIgnore(paginationDisbaled)) {
				break;
			}
		}
		return this;
	}

	private void verifySubscriptionStatus() {
		List<WebElement> statuslist = action.findElements(statusList, "status list");
		action.setImplicitWait(10);
		int i = 1;
		int j = 1;
		// action.waitForElementsDisplayed(statuslist, "Sub Status");
		for (WebElement elem : statuslist) {
			// for (int i = 1; i < statuslist.size(); i++) {
			By eachstas = By.xpath(eachStatusele + "[" + i + "]");
			action.disableImplicitWait();
			String substatus = action.getTextWithoutLogging(eachstas);
			try {
				if (!substatus.equals(null)) {
					if (substatus.contains("Active")) {
						By Cues = By.xpath(eachStatusele + "[" + i + "]" + cuesele);
						if (action.isElementExistsIgnore(Cues, "status displayed")) {
							String cuesText = action.getTextWithoutLogging(Cues);
							int cuesDate = Integer.parseInt(cuesText.replaceAll("[^\\d.]", ""));
							LocalDateTime now = LocalDateTime.now();
							LocalDateTime expectedDate = now.plusDays(cuesDate);
							Instant instant = expectedDate.atZone(ZoneId.of("GMT+05:30")).toInstant();
							LocalDateTime expectedDueDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
							String formattedDueDate = expectedDueDate.format(formatter);

							for (WebElement webElement : dueDatesList) {
								String actualDate = action.getTextWithoutLogging(webElement);
								actualDate = actualDate.replace(" ", "-");

								if (actualDate.equals(formattedDueDate)) {
									ThreadManager.logger.get().info("Due date matches the count of Due days text");
								}
							}

						}
					}
				}

				else {
					if (substatus.contains("Needs renewal")) {
						By Cues = By.xpath(eachStatusele + "[" + i + "]" + cuesele);
						if (verify.isElementDisplayed(Cues, "status displayed")) {
							String cuesText = action.getTextWithoutLogging(Cues);
							int cuesDate = Integer.parseInt(cuesText.replaceAll("[^\\d.]", ""));
							LocalDateTime now = LocalDateTime.now();
							LocalDateTime expectedDate = now.minusDays(cuesDate);
							Instant instant = expectedDate.atZone(ZoneId.of("GMT+05:30")).toInstant();
							System.out.println("TimeZone India : " + expectedDate);
							System.out.println("TimeZone  USA: " + instant);
							LocalDateTime expectedDueDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
							System.out.println("Formatted  USA: " + expectedDueDate);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
							String formattedDueDate = expectedDueDate.format(formatter);
							for (WebElement webElement : dueDatesList) {
								String actualDate = action.getTextWithoutLogging(webElement);
								actualDate = actualDate.replace(" ", "-");
								if (actualDate.equals(formattedDueDate)) {
									ThreadManager.logger.get().info("Due date matches the count of OverDue days text");
								}
							}

						}

					}
				}
			}

			catch (Exception excep) {
				action.logInfo("Pay invoice already made for this contract ", excep);
			}

		}
		i++;
	}
}
