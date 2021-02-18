package pages;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.PageAction;
import utilities.ThreadManager;

public class OrderConfirmationPage extends BasePage {

	public OrderConfirmationPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Order Confirmation");
		}
	}

	@FindBy(xpath = "//p[contains(@class,'checkout-success__body__order__number')]")
	private WebElement orderConfirmationNumer;

	@FindBy(xpath = "(//div[@class='item__list-middle f-size-mobile-12p']/a/h4)[1]")
	private WebElement catgOrderpageFirst;
	@FindBy(xpath = "//div[@class='coverage-details w-100 pb-mobile-1']")
	private WebElement coverageOrderpage;
	@FindBy(xpath = "//div[@class='part-num']")
	private WebElement partnumOrderpage;
	@FindBy(xpath = "//div[@class='term']")
	private WebElement termOrderpage;
	String terms = "//div[@class='term']";
	@FindBy(xpath = "//div[@class='device']")
	private WebElement deviceOrderpage;
	@FindBy(xpath = "//div[@class='systemid systemid-xs']")
	private WebElement sysidOrderpage;
	@FindBy(xpath = "//div[@class='tail-num']")
	private WebElement tailOrderpage;
	@FindBy(xpath = "//div[contains(@class,'checkout-success__body')]//p[4]")
	private WebElement LBLSubscrNote;
	@FindBy(xpath = "(//div[@class='tail-num' and contains(text(),'Aircraft')])[1]")
	private WebElement LBLAircraft1;
	@FindBy(xpath = "(//div[@class='tail-num' and contains(text(),'Aircraft')])[2]")
	private WebElement LBLAircraft2;
	@FindBy(xpath = "(//div[contains(@class,'coverage-details')]//div[contains(@class,'coverage')])[1]")
	private WebElement LBLCoverage1;
	@FindBy(xpath = "(//div[contains(@class,'coverage-details')]//div[contains(@class,'coverage')])[2]")
	private WebElement LBLCoverage2;
	@FindBy(xpath = "//div[contains(@class,'order-ship-items')]//span[contains(text(),'Contract')]")
	private WebElement LBLOCContract1;
	@FindBy(xpath = "//div[contains(@class,'order-ship-items')]//span[contains(text(),'Subscription')]")
	private WebElement LBLOCSubscr1;
	@FindBy(xpath = "(//div[contains(@class,'order-ship-items')]//strong[contains(text(),'Please note')])[1]")
	private WebElement LBLCovDesc;

	@FindBy(xpath = "(//div[contains(@class,'order-ship-items')]//Strong[3])[2]")
	private WebElement LBLSubsFromDate;
	@FindBy(xpath = "(//div[contains(@class,'order-ship-items')]//Strong[4])[2]")
	private WebElement LBLSubsToDate;
	@FindBy(xpath = "//h3[contains(text(),'Items to be shipped')]")
	private WebElement LBLItemToShip;

	@FindBy(xpath = "(//div[@class='fulfillment'])[1]")
	private WebElement fulfillmentVal1;
	@FindBy(xpath = "(//div[@class='fulfillment'])[2]")
	private WebElement fulfillmentVal2;

	// Price check

	@FindBy(xpath = "//i[contains(@class,'material-icons alram-on')]") private WebElement alarmIcon;
	@FindBy(xpath = "//div[contains(@class,'content pl-10p fs-27p')]") private WebElement autoPay;
	@FindBy(xpath = "//span[contains(@class,'font-weight-bold')]") private WebElement autorenewPrice;
	@FindBy(xpath = "//span[contains(@class,'price pb-2 font-weight-normal fs-16p d-flex')]") private WebElement contractprice;
	@FindBy(xpath = "//div[contains(@class,'order-subtotal')]/p") private WebElement subtotalPrice;
	@FindBy(xpath = "//div[contains(@class,'order-shipping-add-on-info')]/p") private WebElement shippingPrice;
	@FindBy(xpath = "//div[@class='order-discounts-add-on-info']/h4[contains(text(),'Included Discounts')]/../p") private WebElement discountPrice;
	@FindBy(xpath = "//div[contains(@class,'order-tax-add-on-info')]/p") private WebElement taxAmount;
	@FindBy(xpath = "//div[contains(@class,'order-total-info')]/h3") private WebElement totalAmount;
	@FindBy(xpath = "//li[@class='item__list--item']") private List<WebElement> productsList;
	@FindBy(xpath = "//div[@class='total-price f-size-mobile-22p']") private List<WebElement> pricesList;
	@FindBy(xpath = "//span[@class='qtyValue']") private List<WebElement> quantityList;
	@FindBy(xpath = "//span[@class='font-weight-bold']") private WebElement autoRenwalContractval;
	@FindBy(xpath = "//span[@class='order-payment-type']") private WebElement paymentType;
	@FindBy(xpath = "//span[@class='order-billing-info']") private WebElement billingAddress;
	@FindBy(xpath = "//div[@class='pb-1 pdp-code-num f-size-mobile-12p']") private WebElement messageForOneTimeOnOrderConfirmationPage;
	@FindBy(xpath = "//div[@class='order-details']/div/h4[contains(text(),'Subtotal')]/../p") private WebElement subTotal;	
	@FindBy(xpath = "//div[@class='order-details']/div/h4[contains(text(),'Shipping total')]/../p") private WebElement shippingTotal;	
	@FindBy(xpath = "//div[@class='order-details']/div/h4[contains(text(),'Tax')]/../p") private WebElement taxvalue;
	@FindBy(xpath = "//div[@class='order-total-info']/p[contains(text(),'Total')]/../h3") private WebElement totalpricefinal;
	
	// Bundles:

	@FindBy(xpath = "//div[@class='item__brief pb-1 pt-1']")
	private List<WebElement> aircraftName;

	@FindBy(xpath = "//div[@class='coverage']")
	private List<WebElement> bundleCoverage;

	@FindBy(xpath = "//div[@class='device']")
	private List<WebElement> bundleDevice;

	@FindBy(xpath = "//div[@class='term']")
	private List<WebElement> bundleTerm;

	@FindBy(xpath = "//div[@class='order-payment-type']/p")
	private WebElement paymentTypeAR;

	String OrderConfTripkit = "Please note, new subscriptions may take up to an hour to display on your My Subscriptions page.";

	public OrderConfirmationPage getOrdernumber() {
		// action.waitForElementVisibility(orderConfirmationNumer);
		String ord = action.getText(orderConfirmationNumer, "Order Number");
		orderNum = utils.getOnlyDigitsFromString(ord);
		ThreadManager.dash.get().setComments("Order No:- " + orderNum);
		return this;
	}

	public OrderConfirmationPage getOrdernumberToNote() {

		String ord = action.getText(orderConfirmationNumer, "Order Number");
		orderNum = utils.getOnlyDigitsFromString(ord);
		verify.writeToNote(orderNum);
		return this;
	}

	public OrderConfirmationPage Validate_Alacarte_Order_Confirmation(String tail, String LOS) {
		action.waitForElementDisplayed(catgOrderpageFirst, "Category in Order Conf page");
		verify.isElementDisplayed(catgOrderpageFirst, "Category in Order Conf page");
		verify.isElementDisplayed(partnumOrderpage, "Part number in Order Conf page");
		verify.isElementDisplayed(coverageOrderpage, "Coverage in Order Conf page");
		if (action.getText(termOrderpage, "Term details").contains(LOS)
				&& action.getText(tailOrderpage, "Tail").contains(tail)) {
			action.logPass("The tail and term matches in Order Confirmation page");
		} else {
			action.logFailure("The tail and term do not match in Order Confirmation page");
		}
		return this;

	}

	public OrderConfirmationPage Validate_Bundle_Order_Confirmation() {
		int count=0;
			action.waitForElementDisplayed(catgOrderpageFirst, "Category in Order Conf page");
			verify.isElementDisplayed(sysidOrderpage, "System ID in Order Conf page");
			verify.isElementDisplayed(partnumOrderpage, "Part number in Order Conf page");

			List<String> cartValues = ThreadManager.placeholder.get().getArrayList();

			List<String> ordConfValues = new ArrayList<String>();
			ordConfValues.clear();

			for (int i = 0; i < bundleDevice.size(); i++) {
				String deviceOnOCP = action.getText(bundleDevice.get(i), "device name");
				deviceOnOCP = deviceOnOCP.replaceAll("Avionic: ", "");
				ordConfValues.add(deviceOnOCP);
			}

			for (int i = 0; i < bundleTerm.size(); i++) {
				String orderbundleTerm = action.getText(bundleTerm.get(i), "bundleTerm");
				orderbundleTerm = orderbundleTerm.replaceAll(" ", "");
				ordConfValues.add(orderbundleTerm);
			}
			for (int i = 0; i < bundleCoverage.size(); i++) {
				String orderbundleCoverage = action.getText(bundleCoverage.get(i), "bundleCoverage");
				orderbundleCoverage = orderbundleCoverage.replaceAll(" ", "");
				ordConfValues.add(orderbundleCoverage);
			}
			
			for (String string : ordConfValues) {
				for (int j = 0; j < cartValues.size(); j++) {
					
					if (cartValues.get(j).equals(string)) {
						action.logInfo("Verification of Text Match passed for :" + string);
						 count++;
						 break;
					}
				}			
			}
			if(count==0) {
				action.logFailure("none of the values matched");
			}

						ThreadManager.placeholder.get().setArrayList(ordConfValues);

			return this;

		}


	public OrderConfirmationPage Validate_Message_For_OneTime_Purchase_On_OrderConfirmationPage() {
		action.waitForElementVisibility(messageForOneTimeOnOrderConfirmationPage,
				"msg for one time purchase on order confirmation page");
		verify.verifyTextMatch(
				"*Please note that your coverage begins today " + utils.Get_Current_Date() + " and will end on "
						+ utils.Get_OneTime_Date_From_Current() + ".",
				action.getText(messageForOneTimeOnOrderConfirmationPage, "message"),
				"message on order confirmation page");

		return this;
	}

	public OrderConfirmationPage Validate_Tripkit_Order_Confirmation(String Term) {

		verify.verifyTextFromElementIgnoringCase(LBLSubscrNote, OrderConfTripkit, "Subscription Message");
		action.scrollToElement(LBLItemToShip, "Items to be shipped");
		verify.isElementDisplayed(LBLAircraft1, "Aircraft Label");
		verify.isElementDisplayed(LBLCoverage1, "Aircraft Coverage");
		String coverageText = action.getText(termOrderpage, "Term text on Cart").split(":")[1];

		if (action.matchTextEqualIgnoringcase("Until Renewal Date", Term)) {
			verify.isElementDisplayed(LBLOCContract1, "Contract Details");
			verify.isElementDisplayed(LBLOCSubscr1, "Subscription Details");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
			action.logInfo("Subscription start date on cart : " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsFromDate, utils.Get_Current_Date(),
					"The coverage begins from " + utils.Get_Current_Date());

			String renewalDate = ThreadManager.placeholder.get().getStringData();
			verify.verifyTextFromElementIgnoringCase(LBLSubsToDate, renewalDate, "The coverage ends on " + renewalDate);
		} else if (action.matchTextEqualIgnoringcase("One Time", Term)) {
			verify.isElementDisplayed(LBLOCContract1, "Contract Details");
			verify.isElementDisplayed(LBLOCSubscr1, "Subscription Details");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
			action.logInfo("Subscription start date on cart : " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsFromDate, utils.Get_Current_Date(),
					"The coverage begins from " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsToDate, utils.Get_OneTime_Date_From_Current(),
					"The coverage ends on " + utils.Get_OneTime_Date_From_Current());
		} else if (action.matchTextEqualIgnoringcase("Annual", Term)) {
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
		}
		return this;
	}

	public OrderConfirmationPage Validate_Fulfillment_OrderConfPage(ConcurrentHashMap<String, String> fulfillment) {

		verify.verifyTextMatchIgnoringCase(fulfillment.get("Jeppfulfillment"),
				action.getText(fulfillmentVal1, "Fulfillment1 value on Cart"), "Validate fulfillment1 on Cart");
		verify.verifyTextMatchIgnoringCase(fulfillment.get("Aviallfulfillment"),
				action.getText(fulfillmentVal2, "Fulfillment2 value on Cart"), "Validate fulfillment2 on Cart");
		return this;
	}

	public OrderConfirmationPage Validate_Autorenewal_Annual_OrderConfirmation(String ordertype) {
		HashMap<String, String> hm = ThreadManager.placeholder.get().getHashmap();
		List<Float> pricetotal = new ArrayList<Float>();
		float autorenewprice = 0;
		float productprice = 0;
		String cardName = action.getText(paymentTypeAR, "CardType");
		// cardName = cardName.replace(" XXXX-XXXX-XXXX-5559", "");
		//String cardNamesub = cardName.substring(0, cardName.length() - 25);
		String cardNameSub;
		if( cardName.contains("AMERICAN") || cardName.contains("DISCOVER"))
		   cardNameSub=cardName.split(" XXXX")[0].trim();
		else
		   cardNameSub=cardName.split("CARD")[0].trim();
		verify.verifyTextMatchIgnoringCase(cardNameSub, hm.get("CN"), "Matching Card Name");
		if (action.matchTextEqualIgnoringcase("Annual", ordertype)) {
			verify.isElementDisplayed(alarmIcon, "Alarm icon");
			verify.verifyTextFromElementIgnoringCase(autoPay, "Auto-Pay", "Auto Pay text");

			for (WebElement e : pricesList) {
				String price1 = action.getText(e, "Price value");
				productprice = utils.priceExtract(price1);
				action.logInfo("Item price" + productprice);
				pricetotal.add(productprice);

			}
			action.logInfo("Price total extracted " + pricetotal);
			for (Float e : pricetotal) {
				autorenewprice = e + autorenewprice;
			}
			action.logInfo("Total value calculated " + autorenewprice);
			verify.matchFloatEquals(
					utils.priceExtract(action.getText(autoRenwalContractval, "Autorenewal contract price value")),
					autorenewprice);

		} else if (action.matchTextEqualIgnoringcase("Mixed", ordertype)) {
			verify.isElementDisplayed(alarmIcon, "Alarm icon");
			verify.verifyTextFromElementIgnoringCase(autoPay, "Auto-Pay", "Auto Pay text");
			int i = 0;
			action.logInfo("Total blocks" + productsList.size());
			for (WebElement e : productsList) {
				int j = i + 1;
				if (action.isElementExistsIgnore(By.xpath("//li[@class='item__list--item'][" + j
						+ "]//div[@class='term' and contains(text(),'Annual')]"))) {
					String price1 = action.getText(pricesList.get(i), "Price value");
					productprice = utils.priceExtract(price1);
					action.logInfo("Item price" + productprice);
					pricetotal.add(productprice);
				}
				i++;
			}
			action.logInfo("Price total extracted " + pricetotal);
			for (Float e : pricetotal) {
				autorenewprice = e + autorenewprice;
			}
			action.logInfo("Total value calculated " + autorenewprice);
			verify.matchFloatEquals(
					utils.priceExtract(action.getText(autoRenwalContractval, "Autorenewal contract price value")),
					autorenewprice);

		} else if (action.matchTextEqualIgnoringcase("No", ordertype)) {
			verify.isElementNotDisplayed(alarmIcon, "Alarm icon");
			verify.isElementNotDisplayed(autoPay, "Autopay");
			// verify.isElementDisplayed(billingAddress, "Billing address");
			// verify.isElementDisplayed(paymentType, "Payment type");
		}
		ThreadManager.placeholder.get().setFloatData(autorenewprice);
		return this;
	}
	
	public OrderConfirmationPage Validate_Price_OrderConfirmation() {
		List<Float> pricetotal = new ArrayList<Float>();
		float totalprice = 0;
		float price1eachpricemidval = 0;
		int qty =0;
        float savedprice = 0;
        float indsaveprice = 0;
        float diff = 0;
        float subtotalprice = 0;
        float calctotalprice = 0;
        int j= 1;
		float totalpriceval = 0;
		float taxvalamt = 0;
		float shippingamt = 0;
		float productprice = 0;
		for (WebElement e : pricesList) {
	    	if(j <= pricesList.size())
	    	{
	    	if (action.isElementExistsIgnore(By.xpath("(//li[@class='item__list--item'])["+ j + "]//div[@class='product_offer']" ))) {
	    	    String price1 = action.getText(pricesList.get(j-1), "Price value");
				productprice = utils.priceExtract(price1);
				action.logInfo("Item price" + productprice);
				pricetotal.add(productprice);
				
				
				//middle each price
				String price0eachpricemid = action.getText(By.xpath("(//div[@class='item__list-middle f-size-mobile-12p']//div[@class='item__price'])["+ j + "]"), "Price each value");
				price1eachpricemidval = utils.priceExtract(price0eachpricemid);
				action.logInfo("Item price" + price1eachpricemidval);
				qty = Integer.parseInt(utils.getOnlyDigitsFromString(action.getText(By.xpath("(//span[@class='qtyValue'])[" + j + "]"), "Quantity in order conf")));
				action.logInfo("Quantity" +  qty);
				indsaveprice= ((qty*price1eachpricemidval)-productprice);
				savedprice = savedprice + indsaveprice;
				diff = ((price1eachpricemidval*qty)-(indsaveprice + productprice));
				action.logInfo("Difference in price" +  diff);
				if(diff!=0)
				{
					action.logFailure("Price mismatch in order confirmation screen with promos in block" +  j);
				}
				else
				{
					action.logPass(" Price match in order confirmation screen in block " + j);
				}		
				j++;
	         }
	    	else
	    	{
	  
	    		String price1 = action.getText(e, "Price value");
				productprice = utils.priceExtract(price1);
				action.logInfo("Item price" + productprice);
				pricetotal.add(productprice);
				j++;

			}
	    }
	  }
			action.logInfo("Price total extracted initial vlue" + pricetotal);
			
			if(action.isElementExistsIgnore(discountPrice))
			{
				verify.matchFloatEquals(savedprice, utils.priceExtract(action.getText(discountPrice, "Discount prices")));
				for (Float e : pricetotal) {
					calctotalprice = e + calctotalprice;
		
				}
				subtotalprice = Float.parseFloat(new DecimalFormat("0.00").format(savedprice + calctotalprice));
				calctotalprice = Float.parseFloat(new DecimalFormat("0.00").format(subtotalprice - savedprice));
				verify.matchFloatEquals(utils.priceExtract(action.getText(subtotalPrice, "Sub total prices")),subtotalprice);
				if(!action.getText(shippingTotal,"shipping value").equals("FREE"))
				{
				shippingamt = utils.priceExtract(action.getText(shippingTotal, "Shipping price"));
				}
				taxvalamt = utils.priceExtract(action.getText(taxvalue, "Tax price"));
				totalpriceval = totalpriceval + subtotalprice + shippingamt + taxvalamt - savedprice;
				totalpriceval = Float.parseFloat(new DecimalFormat("0.00").format(totalpriceval));
				verify.matchFloatEquals(utils.priceExtract(action.getText(totalpricefinal, "Total price value")), totalpriceval);
			}
			else
			{
			for (Float e : pricetotal) {
				calctotalprice=e +calctotalprice;
			}
			action.logInfo("Total value calculated " + totalprice );
			subtotalprice = Float.parseFloat(new DecimalFormat("0.00").format(savedprice + calctotalprice));
			verify.matchFloatEquals(utils.priceExtract(action.getText(subtotalPrice, "Sub Total price value")), subtotalprice);
			if(!action.getText(shippingTotal,"shipping value").equals("FREE"))
			{
			shippingamt = utils.priceExtract(action.getText(shippingTotal, "Shipping price"));
			}
			taxvalamt = utils.priceExtract(action.getText(taxvalue, "Tax price"));
			totalpriceval = totalpriceval + subtotalprice + shippingamt + taxvalamt;
			totalpriceval = Float.parseFloat(new DecimalFormat("0.00").format(totalpriceval));
	        verify.matchFloatEquals(utils.priceExtract(action.getText(totalpricefinal, "Total price value")), totalpriceval);
		}

		return this;
  }
}
