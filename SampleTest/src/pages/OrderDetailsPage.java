package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.ThreadManager;

public class OrderDetailsPage extends BasePage {

	public OrderDetailsPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Order Details");

		}
	}

	@FindBy(xpath = "//div[@class='order-number']")
	private WebElement orderNumberInfo;
	@FindBy(xpath = "//div[@class='coverage']")
	private WebElement coverageOrderpage;
	@FindBy(xpath = "//div[@class='part-num']")
	private WebElement partnumOrderpage;
	@FindBy(xpath = "//div[@class='term']")
	private WebElement termOrderpage;
	@FindBy(xpath = "//div[@class='device']")
	private WebElement deviceOrderpage;
	@FindBy(xpath = "//div[@class='systemid']")
	private WebElement sysidOrderpage;
	@FindBy(xpath = "(//div[@class='tail'])[1]")
	private WebElement tailOrderpage;
	@FindBy(xpath = "(//div[@class='item__list-middle']/a/h4)[1]")
	private WebElement catgOrderHistorypage;
	@FindBy(xpath = "(//div[@class='shipping-status pb-1'])[1]")
	private WebElement shipstatusHistorypage;
	@FindBy(xpath = "//div[contains(@class,'order-ship-items')]//div[contains(text(),'Contract')]")
	private WebElement LBLContract;
	@FindBy(xpath = "//div[contains(@class,'order-ship-items')]//div[contains(text(),'Subscription')]")
	private WebElement LBLSubscription;
	@FindBy(xpath = "//div[contains(@class,'pdp-code-num')]//strong[3]")
	private WebElement LBLSubsFromDate;
	@FindBy(xpath = "//div[contains(@class,'pdp-code-num')]//strong[4]")
	private WebElement LBLSubsToDate;
	@FindBy(xpath = "//div[@class='pb-1 pdp-code-num f-size-mobile-12p']")
	private WebElement messageForOneTimeOnOrderHistoryPage;

	// Bundles
	@FindBy(xpath = "//div[@class='coverage']")
	private List<WebElement> bundleCoverage;

	@FindBy(xpath = "//div[@class='device']")
	private List<WebElement> bundleDevice;

	@FindBy(xpath = "//div[@class='term']")
	private List<WebElement> bundleTerm;

	// Auto-renewal

	@FindBy(xpath = "//i[contains(@class,'material-icons alram-on')]")
	private WebElement alarmIcon;
	@FindBy(xpath = "//div[contains(@class,'content pl-10p')]")
	private WebElement autoPay;
	@FindBy(xpath = "//span[contains(@class,'font-weight-bold')]")
	private WebElement autorenewPrice;
	@FindBy(xpath = "//span[contains(@class,'price pb-2 font-weight-normal fs-16p d-flex')]")
	private WebElement contractprice;
	@FindBy(xpath = "//div[contains(@class,'order-subtotal')]/p")
	private WebElement subtotalPrice;
	@FindBy(xpath = "//div[contains(@class,'order-shipping-add-on-info')]/p")
	private WebElement shippingPrice;
	@FindBy(xpath = "//div[@class='order-discounts-add-on-info']/p")
	private WebElement discountPrice;
	@FindBy(xpath = "//div[contains(@class,'order-tax-add-on-info')]/p")
	private WebElement taxAmount;
	@FindBy(xpath = "//div[contains(@class,'order-total-info')]/h3")
	private WebElement totalAmount;
	@FindBy(xpath = "//li[@class='item__list--item']")
	private List<WebElement> productsList;
	@FindBy(xpath = "//div[@class='total-price f-size-mobile-22p']")
	private List<WebElement> pricesList;
	@FindBy(xpath = "//span[@class='qtyValue']")
	private List<WebElement> quantityList;
	@FindBy(xpath = "//span[@class='font-weight-bold']")
	private WebElement autoRenwalContractval;
	@FindBy(xpath = "//div[@class='value-order']")
	private WebElement paymentType;
	@FindBy(xpath = "//div[@class='order-billing-address']/address")
	private WebElement billingAddress;

	@FindBy(xpath = "//div[@class='pb-1 pdp-code-num f-size-mobile-12p']")
	private WebElement messageForOneTimeOnOrderConfirmationPage;
	
	
	
	@FindBy(xpath = "//table[@id='order_history_table']/tbody/tr[1]/td/a")
	private WebElement OrderIDLink;
	

	protected OrderDetailsPage getOrderDetailsScreenshot() {
		action.scrollToElement(orderNumberInfo, "Order Number Info");
		action.takeScreenshot("Order Details Screenshot");
		return this;
	}

	public OrderDetailsPage Validate_Alacarte_Order_Details(String tail, String LOS) {
		verify.isElementDisplayed(partnumOrderpage, "Part number in Order Details page");
		verify.isElementDisplayed(coverageOrderpage, "Coverage in Order Details page");
		verify.isElementDisplayed(catgOrderHistorypage, "Category in Order Details page");
		verify.isElementDisplayed(shipstatusHistorypage, "Shipping status in Order Details page");
		if (action.getText(termOrderpage, "Term details").contains(LOS)
				&& action.getText(tailOrderpage, "Tail").contains(tail)) {
			action.logPass("The tail and term matches in Order Details page");
		} else {
			action.logFailure("The tail and term do not match in Order Details page");
		}
		return this;

	}

	public OrderDetailsPage Validate_Bundle_Order_Details() {
		verify.isElementDisplayed(partnumOrderpage, "Part number in Order Details page");
		verify.isElementDisplayed(coverageOrderpage, "Coverage in Order Details page");
		verify.isElementDisplayed(catgOrderHistorypage, "Category in Order Details page");
	

		List<String> ordConfValues = ThreadManager.placeholder.get().getArrayList();

		List<String> ordDetailsValues = new ArrayList<String>();
		ordDetailsValues.clear();

		for (int i = 0; i < bundleDevice.size(); i++) {
			String deviceOnOVP = action.getText(bundleDevice.get(i), "device name");
			deviceOnOVP = deviceOnOVP.replace("Avionic:", "");
			ordDetailsValues.add(deviceOnOVP);
		}

		for (int i = 0; i < bundleTerm.size(); i++) {
			String orderbundleTerm = action.getText(bundleTerm.get(i), "bundleTerm");
			orderbundleTerm=orderbundleTerm.replaceAll(" ", "");
			ordDetailsValues.add(orderbundleTerm);
		}
		for (int i = 0; i < bundleCoverage.size(); i++) {
			String orderbundleCoverage = action.getText(bundleCoverage.get(i), "bundleCoverage");
			orderbundleCoverage = orderbundleCoverage.replaceAll(" ", "");
			ordDetailsValues.add(orderbundleCoverage);
		}

		
		for (int i = 0; i < ordConfValues.size(); i++) {
			verify.verifyListIgnoreSpace(ordDetailsValues, ordConfValues, i);

		}

	/*	if (ordConfValues.size() == ordDetailsValues.size()) {

			for (String element : ordDetailsValues) {
				if (ordConfValues.contains(element)) {
					continue;
				} else {
					action.logFailure("Values doesnt match");
				}
			}
		}
*/
		return this;

	}
	public OrderDetailsPage Capture_SubscriptionDetails_OnOrderDetailsPage(ConcurrentHashMap<String, String> cardPayment){
		HashMap<String, String> hm = new HashMap<String, String>();
		List<String> billingAddressOnOD = new ArrayList<String>();
		clickAccountIcon();
		action.clickLink("Order History");
		action.clickWithClickableWait(OrderIDLink, "1st Order ID link in Order history");
		verify.isElementDisplayed(billingAddress, "Billing address");
		String billingAddressText = billingAddress.getText();
		String str[] = billingAddressText.split("\"\"");
		billingAddressOnOD = Arrays.asList(str);
		ThreadManager.placeholder.get().setArrayList(billingAddressOnOD);
		
		String cardName = cardPayment.get("name");
		String expMonth = cardPayment.get("name");
		String expYear = cardPayment.get("name");
		hm.put("CN", cardName);
		hm.put("EM", expMonth);
		hm.put("EY", expYear);
		ThreadManager.placeholder.get().setHashmap(hm);
		
		
		return this;
	}
	

	public OrderDetailsPage Validate_Autorenewal_Annual_OrderDetails(String ordertype) {
		HashMap<String, String> hm = new HashMap<String, String>();
		List<String> billingAddressOnOD = new ArrayList<String>();
		verify.isElementDisplayed(billingAddress, "Billing address");
		String billingAddressText = billingAddress.getText();
		String str[] = billingAddressText.split("\"\"");
		billingAddressOnOD = Arrays.asList(str);
			verify.isElementDisplayed(paymentType, "Payment type");

		if ((action.matchTextEqualIgnoringcase("Annual", ordertype))
				|| (action.matchTextEqualIgnoringcase("Mixed", ordertype))) {
			float autorenewprice = ThreadManager.placeholder.get().getFloatData();
			verify.isElementDisplayed(alarmIcon, "Alarm icon");
			verify.verifyTextFromElementIgnoringCase(autoPay, "Auto-Pay", "Auto Pay text");

			verify.matchFloatEquals(
					utils.priceExtract(action.getText(autoRenwalContractval, "Autorenewal contract price value")),
					autorenewprice);
		}

		else if (action.matchTextEqualIgnoringcase("No", ordertype)) {
			verify.isElementNotDisplayed(alarmIcon, "Alarm icon");
			verify.isElementNotDisplayed(autoPay, "Autopay");
			ThreadManager.placeholder.get().setArrayList(billingAddressOnOD);
		}
		return this;
	}

	public OrderDetailsPage Validate_Message_For_OneTime_Purchase_On_OrderHistoryPage() {
		action.waitForElementVisibility(messageForOneTimeOnOrderHistoryPage,
				"msg for one time purchase on Order History page");
		verify.verifyTextMatch(
				"*Please note that your coverage begins today " + utils.Get_Current_Date() + " and will end on "
						+ utils.Get_OneTime_Date_From_Current() + ".",
				action.getText(messageForOneTimeOnOrderHistoryPage, "msg"), "message on order history page");

		return this;
	}

	public OrderDetailsPage Validate_Tripkit_Order_History_Details(String Term) {

		String coverageText = action.getText(termOrderpage, "Term text on Cart").split(":")[1];

		if (action.matchTextEqualIgnoringcase("Until Renewal Date", Term)) {
			verify.isElementDisplayed(catgOrderHistorypage, "Subscription Category");
			verify.isElementDisplayed(LBLContract, "Contract");
			verify.isElementDisplayed(LBLSubscription, "Subscription");
			verify.isElementDisplayed(coverageOrderpage, "Coverage in Order Details page");
			verify.isElementDisplayed(partnumOrderpage, "Part number in Order Details page");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
			action.logInfo("Subscription start date on cart : " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsFromDate, utils.Get_Current_Date(),
					"The coverage begins from " + utils.Get_Current_Date());

			String renewalDate = ThreadManager.placeholder.get().getStringData();
			verify.verifyTextFromElementIgnoringCase(LBLSubsToDate, renewalDate, "The coverage ends on " + renewalDate);
			verify.isElementDisplayed(shipstatusHistorypage, "Shipping status in Order Details page");
		} else if (action.matchTextEqualIgnoringcase("One Time", Term)) {
			verify.isElementDisplayed(catgOrderHistorypage, "Subscription Category");
			verify.isElementDisplayed(LBLContract, "Contract");
			verify.isElementDisplayed(LBLSubscription, "Subscription");
			verify.isElementDisplayed(coverageOrderpage, "Coverage in Order Details page");
			verify.isElementDisplayed(partnumOrderpage, "Part number in Order Details page");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
			action.logInfo("Subscription start date on cart : " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsFromDate, utils.Get_Current_Date(),
					"The coverage begins from " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsToDate, utils.Get_OneTime_Date_From_Current(),
					"The coverage ends on " + utils.Get_OneTime_Date_From_Current());
			verify.isElementDisplayed(shipstatusHistorypage, "Shipping status in Order Details page");
		} else if (action.matchTextEqualIgnoringcase("Annual", Term)) {
			verify.isElementDisplayed(catgOrderHistorypage, "Subscription Category");
			verify.isElementDisplayed(coverageOrderpage, "Coverage in Order Details page");
			verify.isElementDisplayed(partnumOrderpage, "Part number in Order Details page");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
			verify.isElementDisplayed(shipstatusHistorypage, "Shipping status in Order Details page");
		}
		return this;
	}

}
