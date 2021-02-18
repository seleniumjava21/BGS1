package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.ThreadManager;

public class CartPage extends BasePage {

	public CartPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Your Shopping Cart");
		}

	}

	@FindBy(xpath = "//button[contains(@class,'button-primary check hidden-xs btn--continue-checkout js-continue-checkout-button')]")
	private WebElement checkout_btn_bgs;
	@FindBy(xpath = "//div[@id='actionEntry_0']//a[contains(text(),'REMOVE')]")
	private WebElement removeLnk;
	private By emptyCartLabel = By.xpath("//h4[contains(text(),'Your cart is empty')]");
	private By removelink = By.linkText("REMOVE");

	@FindBy(xpath="//div[@class='item__total js-item-total']") private List<WebElement> pricesList;
	@FindBy(xpath="//div[@class='cart-amount cart-total__final']") private WebElement totalcartpriceval;
	@FindBy(xpath="//div[@class='cart-title']/div[contains (text(),'Subtotal')]") private WebElement subtotalcart;
	@FindBy(xpath="//div[@class='cart-title']/div[contains (text(),'Included Discounts')]") private WebElement incldiscounts;
	@FindBy(xpath="//div[@class='cart-title']/div[contains (text(),'Subtotal')]/../div[2]") private WebElement subtotalcartpriceval;
	@FindBy(xpath="//div[@class='cart-title']/div[contains (text(),'Included Discounts')]/../div[2]") private WebElement incldiscountsprice;
	@FindBy(xpath = "//div[@class='coverage']")
	private WebElement coverage;
	@FindBy(xpath = "//div[@class='part-num']")
	private WebElement partnum;
	@FindBy(xpath = "//div[@class='term']")
	private WebElement term;
	@FindBy(xpath = "//div[@class='tail-num']")
	private WebElement tailnum;
	@FindBy(xpath = "//div[@class='device']")
	private WebElement device;
	@FindBy(xpath = "//div[@class='systemid']")
	private WebElement systemid;
	@FindBy(xpath = "//div[contains(text(),'Contract')]")
	private WebElement LBLCartContract1;
	@FindBy(xpath = "//div[contains(text(),'Subscription')]")
	private WebElement LBLCartSubscr1;
	@FindBy(xpath = "//div[contains(@class,'pdp-code-num')]//strong[3]")
	private WebElement LBLSubsFromDate;
	@FindBy(xpath = "//div[contains(@class,'pdp-code-num')]//strong[4]")
	private WebElement LBLSubsToDate;
	@FindBy(xpath = "(//div[@class='tail-num'])[1]")
	private WebElement LBLAircraft;
	@FindBy(xpath = "(//div[@class='fulfillment'])[1]")
	private WebElement fulfillmentVal1;
	@FindBy(xpath = "(//div[@class='fulfillment'])[2]")
	private WebElement fulfillmentVal2;
	@FindBy(xpath = "//div[@class='pb-1 pdp-code-num f-size-mobile-12p']")
	private WebElement messageForOneTimeOnCartPage;

	// bundles:
	@FindBy(xpath = "//div[@class='device']")
	private List<WebElement> deviceAll;

	@FindBy(xpath = "//div[@class='version']")
	private WebElement G1000Version;

	@FindBy(xpath = "//a[@id='configureEntry']")
	private WebElement cartEdit;
	@FindBy(xpath = "//i[@class='material-icons iconwhite']/following::strong")
	private List<WebElement> bundleVersionSelection;
	@FindBy(xpath = "//button[@id='modalCancelButton']")
	private WebElement bundleCancel;
	@FindBy(xpath = "//button[@id='continueButton']")
	private WebElement bundleContinue;

	@FindBy(xpath = "//div[@class='item__brief pb-1']")
	private List<WebElement> aircraftName;

	@FindBy(xpath = "//div[@class='coverage']")
	private List<WebElement> bundlCoverage;
	@FindBy(xpath = "//div[@class='term']")
	private List<WebElement> bundleTerm;

	@FindBy(xpath = "(//div[@class='item__list-middle'])[1]//div[@class='device']")
	private List<WebElement> device1;

	protected CartPage validateAlacarteInfo(ConcurrentHashMap<String, String> aeroDetails) {
		// String partnumTxt = action.getText(partnum, "part number").split(":")[1];
		// verify.verifyTextContains(aeroDetails.get("region"), action.getText(coverage,
		// "coverage").split(":")[1], "coverage");
		verify.verifyTextContains(aeroDetails.get("aircraftMake"), action.getText(tailnum, "Tail id").split(":")[1],
				"Tail id");
		// verify.verifyTextContains(aeroDetails.get("devicemodel"),
		// action.getText(device, "Device").split(":")[1], "Device");

		return this;
	}

	public CartPage Validate_Coverage_Details_OnCart(String Term) {
		String coverageText = action.getText(term, "Term text on Cart").split(":")[1];

		if (action.matchTextEqualIgnoringcase("Until Renewal Date", Term)) {
			verify.isElementDisplayed(LBLCartContract1, "Contract");
			verify.isElementDisplayed(LBLCartSubscr1, "Subscription");
			verify.isElementDisplayed(tailnum, "Aircraft");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
			action.logInfo("Subscription start date on cart : " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsFromDate, utils.Get_Current_Date(),
					"The coverage begins from " + utils.Get_Current_Date());
			String renewalDate = ThreadManager.placeholder.get().getStringData();
			verify.verifyTextFromElementIgnoringCase(LBLSubsToDate, renewalDate, "The coverage ends on " + renewalDate);
		} else if (action.matchTextEqualIgnoringcase("One Time", Term)) {
			verify.isElementDisplayed(LBLCartContract1, "Contract");
			verify.isElementDisplayed(LBLCartSubscr1, "Subscription");
			verify.isElementDisplayed(tailnum, "Aircraft");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
			action.logInfo("Subscription start date on cart : " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsFromDate, utils.Get_Current_Date(),
					"The coverage begins from " + utils.Get_Current_Date());
			verify.verifyTextFromElementIgnoringCase(LBLSubsToDate, utils.Get_OneTime_Date_From_Current(),
					"The coverage ends on " + utils.Get_OneTime_Date_From_Current());
		} else if (action.matchTextEqualIgnoringcase("Annual", Term)) {
			verify.isElementDisplayed(tailnum, "Aircraft");
			verify.verifyTextContains(Term, coverageText, "The coverage is as expected.");
		}
		return this;
	}

	public CartPage validate_editBundles() {
		for (WebElement webElement : device1) {
			String text = action.getText(webElement, "name value");

			if (text.endsWith("G1000")) {
				if (G1000Version.isDisplayed()) {
					String text2 = action.getText(G1000Version, "version value");
					cartEdit.click();
					action.waitForElementClickable(bundleCancel);

					action.waitForElementClickable(cartEdit);
					for (WebElement webElement2 : bundleVersionSelection) {
						webElement2.click();
						if (bundleContinue.isEnabled()) {
							bundleContinue.click();
							break;
						}

					}
					String text1 = action.getText(G1000Version, "version value");
					if (!text2.equals(text1)) {
					} else {
						action.logFailure("version didnt change even after edit.");
					}

				}
			} else {
				action.logFailure("version details not available for G1000");
			}
		}

		return this;
	}

	public CartPage Validate_Bundle_Coverage_Details_OnCart() {
		HashMap<String, String> hm = ThreadManager.placeholder.get().getHashmap();
		List<String> deviceBDB = ThreadManager.placeholder.get().getArrayList();
		action.logInfo("Getting placholder List data :- " + deviceBDB);
		List<String> deviceCart = new ArrayList<String>();
		deviceCart.clear();
		for (int i = 0; i < device1.size(); i++) {
			String deviceOncart = action.getText(device1.get(i), "device name");
			deviceOncart = deviceOncart.replace("Avionic:  ", "");
			deviceCart.add(deviceOncart);

		}

		Collections.sort(deviceCart);

		for (int i = 0; i < device1.size(); i++) {
			verify.verifyListIgnoreSpace(deviceBDB, deviceCart, i);

		}
		action.logInfo("Getting placholder map data :- " + hm);
		verify.verifyTextContains(hm.get("term"), action.getText(term, "Term"), "Matching Term");
		verify.verifyTextContains(hm.get("coverage"), action.getText(coverage, "coverage"), "Matching Coverage");

		return this;
	}

	
	public CartPage Validate_All_Devices_Added_OnCart() {
		List<String> cartValues = new ArrayList<String>();
		for (int i = 0; i < deviceAll.size(); i++) {
			String deviceOncart = action.getText(deviceAll.get(i), "device name");
			deviceOncart = deviceOncart.replace("Avionic:  ", "");
			cartValues.add(deviceOncart);

		}
		for (int i = 0; i < bundleTerm.size(); i++) {
			String termsOncart = action.getText(bundleTerm.get(i), "terms name");
			termsOncart = termsOncart.replaceAll("  ", "");
			cartValues.add(termsOncart);
		}
		for (int i = 0; i < bundlCoverage.size(); i++) {
			String coveragesOncart = action.getText(bundlCoverage.get(i), "coverage name");
			coveragesOncart = coveragesOncart.replaceAll("  ", "");
			cartValues.add(coveragesOncart);
		}

		ThreadManager.placeholder.get().setArrayList(cartValues);

		return this;
	}

	public CartPage Validate_New_Coverage_Details_OnCart() {
		verify.isElementDisplayed(tailnum, "Aircraft");
		return this;
	}

	public CartPage Validate_Message_For_OneTime_Purchase_On_CartPage() {
		action.waitForElementVisibility(messageForOneTimeOnCartPage, "msg for one time purchase");
		verify.verifyTextMatch(
				"*Please note that your coverage begins today " + utils.Get_Current_Date() + " and will end on "
						+ utils.Get_OneTime_Date_From_Current() + ".",
				action.getText(messageForOneTimeOnCartPage, "msg"), "msg on cart page");

		return this;
	}

	public CartPage Validate_Fulfillment_Cart(ConcurrentHashMap<String, String> fulfillment) {

		verify.verifyTextMatchIgnoringCase(fulfillment.get("Aviallfulfillment"),
				action.getText(fulfillmentVal1, "Fulfillment1 value on Cart"), "Validate fulfillment1 on Cart");
		verify.verifyTextMatchIgnoringCase(fulfillment.get("Jeppfulfillment"),
				action.getText(fulfillmentVal2, "Fulfillment2 value on Cart"), "Validate fulfillment2 on Cart");

		return this;
	}

	public CartPage Validate_CartPage() {
		verify.pageTitle("Your Shopping Cart");
		return this;
	}

	public CheckOutPage Checkout() {
		action.clickWithClickableWait(checkout_btn_bgs, "Checkout button");
		return new CheckOutPage();
	}

	protected CheckOutPage checkout() {
		action.clickLink("Checkout");
		return new CheckOutPage();

	}

	protected CartPage removeProductsFromCart() {
		List<WebElement> exists;
		int counter = 0;
		action.disableImplicitWait();
		do {
			exists = verify.isElementsExistsIgnore(removelink, "Remove link");
			try {
				if (!(exists.size() == 0)) {
					action.clickAndIgnoreFailure(exists.get(0), "Remove link");
					action.setImplicitWait(1);
					exists = verify.isElementsExistsIgnore(removelink, "Remove link");
				} else {
					action.resetImplicitWait();
					break;
				}
			} catch (StaleElementReferenceException e) {
				action.logInfo("Caught StaleElementReferenceException while trying remove product from cart ");
				action.logInfo(e.toString());
				action.refreshCurrentPage();
			} catch (Exception e) {
				throw new CustomException("Unable to remove product from cart ", e);
			}
			counter++;
			if (counter > 20) {
				action.logWarning(
						"Total products in the cart is more than 20, or something wrong with remove link, Exiting the loop");
				break;
			}

		} while (!exists.isEmpty());

		action.resetImplicitWait();

		/*
		 * int size = elements.size(); int i=1; while((size!=0)&&(i<=size)) { WebElement
		 * e = action.isElementExistsIgnore(removelink, "Remove"); if(null!=e) {
		 * action.clickWithClickableWait(e, "Remove link"); }else { break; } i++; }
		 */
		return this;
	}
	
	public CartPage Validate_Price_In_Cart() {
		List<Float> pricetotal = new ArrayList<Float>();
		float productprice = 0;
		float calctotalprice = 0;
		float totalprice = 0;
		float price1eachpricemidval = 0;
		float priceeachrightvl = 0;
		int qty =0;
        float savedprice = 0;
        float indsaveprice = 0;
        float diff = 0;
        float subtotalprice = 0;
        int j= 1;
	    for (WebElement e : pricesList) {
	    	if(j <= pricesList.size())
	    	{
	    	if (action.isElementExistsIgnore(By.xpath("(//li[@class='item__list--item panel'])["+ j + "]//div[@class='product_offer']" ))) {
	    	    String price1 = action.getText(pricesList.get(j-1), "Price value");
				productprice = utils.priceExtract(price1);
				action.logInfo("Item price" + productprice);
				pricetotal.add(productprice);
				String price0eachpricemid = action.getText(By.xpath("(//div[@class='item__list-middle']//span[@class='item__price'])["+ j + "]"), "Price each value");
				price1eachpricemidval = utils.priceExtract(price0eachpricemid);
				action.logInfo("Item price" + price1eachpricemidval);
				qty = Integer.parseInt(action.getAttribute(By.xpath("(//input[@class='form-control js-update-entry-quantity-input qtyValue'])["+ j +"]"), "value", "Quantity value"));
				action.logInfo("Quantity" +  qty);
				String saveval = action.getText(By.xpath("(//div[@class='item_save'])["+ j + "]/div/span"), "Saved prices");
				indsaveprice= utils.priceExtract(saveval);
				savedprice = savedprice + indsaveprice;
				diff = ((price1eachpricemidval*qty)-(indsaveprice + productprice));
				action.logInfo("Difference in price" +  diff);
				if(diff!=0)
				{
					action.logFailure("Price mismatch in cart with promos in block" +  j);
				}
				else
				{
					action.logPass(" Price match in cart in block " + j);
				}		
				j++;
	         }
	    	else {
			    String price1 = action.getText(e, "Price value");
				productprice = utils.priceExtract(price1);
				action.logInfo("Item price" + productprice);
				pricetotal.add(productprice);
				j++;
	    	  }
	    	}
	      }
			action.logInfo("Price total extracted " + pricetotal);
			if(action.isElementExistsIgnore(incldiscounts))
			{
				verify.matchFloatEquals(savedprice, utils.priceExtract(action.getText(incldiscountsprice, "Discount prices")));
				for (Float e : pricetotal) {
					calctotalprice = e + calctotalprice;
		
				}
				subtotalprice = savedprice + calctotalprice;
				calctotalprice = subtotalprice - savedprice;
				verify.matchFloatEquals(utils.priceExtract(action.getText(subtotalcartpriceval, "Sub total prices")),subtotalprice);
				verify.matchFloatEquals(utils.priceExtract(action.getText(totalcartpriceval, "Total price value")), calctotalprice);
			}
			else
			{
			for (Float e : pricetotal) {
				calctotalprice=e+calctotalprice;
			}
			action.logInfo("Total value calculated " + totalprice );
			verify.matchFloatEquals(utils.priceExtract(action.getText(subtotalcartpriceval, "Sub Total price value")), calctotalprice);
	        verify.matchFloatEquals(utils.priceExtract(action.getText(totalcartpriceval, "Total price value")), calctotalprice);
		}
	        return this;
		}

}
