package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CustomException;
import utilities.PageAction;
import utilities.Reporting;
import utilities.ThreadManager;
import utilities.Utils;
import utilities.Verify;

public abstract class BasePage {
	protected PageAction action;
	protected Verify verify;
	protected Utils utils;

	BasePage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		verify = new Verify();
		utils = new Utils();
	}

	protected String orderNum = "";
	private String orderHistorylink = "Order History";
	@FindBy(xpath = "//div[@class='header-logo-container']/div/a/img")
	protected WebElement logo;

	@FindBy(xpath = "//li[@class='dropdown-items second-nav']")
	private WebElement dropdownNavPanel;
	@FindBy(id = "js-site-search-input")
	private WebElement txt_searchBox_bgs;
	@FindBy(xpath = "//button[contains(@class,'btn btn-link js_search_button')]")
	private WebElement btn_search_bgs;
	@FindBy(xpath = "(//button[@class='header-opt nav-bg-hover header-opt-btn-dropdown dropdown-toggle'])[2]")
	protected WebElement link_account_icon_bgs;
	@FindBy(id = "cboxContent")
	protected WebElement panel_pop_up;
	@FindBy(xpath = "//span[@class='cart-counter-number']")
	private WebElement countercartIcon;
	@FindBy(xpath = "//li[@class='material-icons cart-icon visible-lg visible-md visible-sm visible-xs']//a")
	private WebElement cartIcon;
	@FindBy(xpath = "//a[contains(@class,'col-xs-12 button-primary small-button mobile-full-width')]")
	private WebElement addnewuser;
	@FindBy(xpath = "//div[@class='alert alert-warning alert-dismissable getAccAlert']")
	private WebElement signOutLabel;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement asmusername;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement asmpassword;
	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	private WebElement asmsigninbtn;
	@FindBy(id = "stopEmulate")
	private WebElement sessionBtn;
	@FindBy(linkText = "Pay Invoice")
	private WebElement payinvoiceheaderlink;
	@FindBy(linkText = "Pay Your Invoice")
	private WebElement payinvoicefooterlink;
	@FindBy(xpath = "//button[@class='close closeAccAlert']")
	private WebElement logouttxt;
	@FindBy(xpath = "//a[@class='btn btn-default btn-block btn-icon button-primary small-button pull-right learn-more-btn']")
	private WebElement seeDetailsBtn;

	protected String accountdetailsleftlink = "Account Details";
	protected String aircraftdetailsleftlink = "Aircraft Details";
	protected String changepasswordleftlink = "Change Password";
	protected String changeemailleftlink = "Change Email / User ID";
	protected String orderhistoryleftlink = "Order History";
	protected String mysubscriptionsleftlink = "My Subscriptions";

	public String expectedlogouttxt = "You have signed out of your account.";
	public String buyDatabaseBtn = "BUY DATABASE";
	@FindBy(id = "accountNumber")
	private WebElement accountnumber;
	@FindBy(id = "invoiceNumber")
	private WebElement invoicenumber;
	@FindBy(xpath = "//form[@id='retrieveInvoiceForm']/h2")
	private WebElement payinvoiceheadertxt;
	@FindBy(xpath = "(//button[@class='header-opt nav-bg-hover header-opt-btn-dropdown dropdown-toggle'])[2]")
	private WebElement unAuthLoginRegisterIcon;
	@FindBy(xpath = "//a[@class='header-dropdown-cta button-primary']")
	protected WebElement loginLinkheader;
	@FindBy(xpath = "//a[@class='button-primary header-dropdown-cta header-dropdown-cta-end']")
	protected WebElement logOut;
	private String payInvoiceLnk = "PAY INVOICE";

	private String payYourInvoiceLnk = "Pay Your Invoice";
	private String contactUsLnk = "Contact Us";

	@FindBy(xpath = "//div[@class='order-billing-address']/address")
	private WebElement billingAddress;
	@FindBy(xpath = "//table[@id='order_history_table']/tbody/tr[1]/td/a")
	private WebElement OrderIDLink;

	// Splunk
	@FindBy(xpath = "//input[@id='username']")
	private WebElement splunkuser;

	@FindBy(xpath = "#password")
	private WebElement splunkPass;

	@FindBy(xpath = "//input[@value='Sign In']")
	private WebElement splunkLogin;

	/******************************************************************************************************************************************/

	public enum Login {
		norenewal22,jau, jus, jeu, agent, subscription, renewusr, changeemail, qaautonosubscription, alacarte, compusr, GuestPayInvoice, payinvoicetest, ClosedPayInvoice, tripkit, tripannual, triponetime, bundles, jaurenewal,autoRenewal,jeuautoRenewal, jusrenewalbundle, jaurenewalCheck, norenewal, bundleJUS1, bundleJUS3, bundleJUS12, bundleJUS7, bundleJUS10, ccmfa, bundleJUS8, bundlesJUS05, bundlesJUS06, bundleJUS09, bundleJUS012, bundlesJUS04, bundlesJUS03, bundlesJUS02, bundleJUS01, bundleJUS013, bundleJUS014, bundleJUS015, bundleJAU05, bundleJAU03, bundleJEU06, bundleJUS016, bundleJUS0012, bundleJUS4, bundleJUS08, bundleJEU6, bundleJEU1, bundleJAU3, bundleJAU5, bundlesJUS, bundleJUS9, jaupayInvoice, juspayInvoice, jeupayInvoice,

	}

	public enum AccountType {
		JUSPERSONAL, JUSCOMPANY, JEUPERSONAL, JEUCOMPANY, JAUPERSONAL, JAUCOMPANY
	}

	/**
	 * @return
	 *****************************************************************************************************************************************/

	public AccountDetails Validate_Left_Navigation_Menu_Links_MyAccount() {
		action.clickLink(accountdetailsleftlink);
		action.clickLink(changepasswordleftlink);
		action.clickLink(changeemailleftlink);
		action.clickLink(orderhistoryleftlink);
		action.clickLink(mysubscriptionsleftlink);
		action.clickLink(accountdetailsleftlink);
		return new AccountDetails();

	}

	public LoginPage GoTo_LogIn() {
		action.clickWithoutScroll(unAuthLoginRegisterIcon, "Login Register icon");
		action.clickWithoutScroll(loginLinkheader, "Login link");
		return new LoginPage();
	}

	public BuyDatabasePage Navigate_Buy_Database() {
		action.clickWithClickableWait(logo, "Logo");
		navigateFromMainMenu("FLIGHT OPERATIONS", "Buy Database");
		return new BuyDatabasePage();

	}

	public <T> BuyDatabasePage Add_Config_Bundle_Product_To_Cart_Auth(T productList) {
		ProductListPage plp = null;
		String cls;
		try {
			cls = productList.getClass().getName();
		} catch (NullPointerException e) {
			throw new CustomException(
					"May be failed due to non-existent product code in test data : " + productList.toString(), e);
		}
		if (cls.contains("ArrayList")) {
			for (String s : (ArrayList<String>) productList) {
				plp = Search_This_Product(s);
				if (plp.isBuydataBaseButtonExists()) {
					break;
				} else {
					action.logWarning("Add to Cart button NOT found for this product :- " + s);
				}
			}

		} else if (cls.contains("String")) {
			plp = Search_This_Product((String) productList);
		}

		plp.buydatabaseClickFromPLP();
		return new BuyDatabasePage();
	}

	public LoginPage Navigate_Buy_Database_Unauth() {
		navigateFromMainMenu("FLIGHT OPERATIONS", "Buy Database");
		return new LoginPage();

	}

	private void navigateFromMainMenu(String mainMenu, String product) {
		WebElement element = action.findElement(By.linkText(mainMenu), mainMenu);
		action.scrollToElement(element, mainMenu);
		action.mouseHover(element, mainMenu);
		action.sleep(500);
		action.switchToDefaultContent();
		action.clickLinkWithoutScroll(product);
		// action.waitForElementInvisibility(dropdownNavPanel);
	}

	public BuyDatabasePage Navigate_Buy_Database_GNB_Authenticated(String mainMenu, String product) {

		navigateFromMainMenu(mainMenu, product);
		if (verify.pageTitle("Buy databases"))
			return new BuyDatabasePage();
		else
			throw new CustomException("Not navigated to Buy DB page");

	}

	public PayInvoicePage Goto_PayInvoice_Header() {
		action.clickLink(payInvoiceLnk);
		return new PayInvoicePage();

	}

	public ContactUsPage Goto_ContactPage() {
		action.clickLink(contactUsLnk);
		return new ContactUsPage();

	}

	public PayInvoicePage Goto_PayInvoice_Footer() {
		action.clickLink(payYourInvoiceLnk);
		return new PayInvoicePage();

	}

	public PayProForma navigate_PayProformaURL() {
		String payproforma_url = Utils.getEnvUrl().getString(System.getProperty("env")) + "payproforma";
		ThreadManager.getInstance().getDriver().get(payproforma_url);
		action.logInfo("Launching PayProforma invoice page :- " + payproforma_url);
		return new PayProForma();

	}

	public HomePage ASM_Login_With_Credentials_And_Verify_Login(Login username) {
		action.waitForElementVisibility(txt_searchBox_bgs, "Search box");
		action.waitForElementDisplayed(asmusername, "asmusername");
		action.waitForElementVisibility(asmusername, "ASM Username");
		action.waitForElementVisibility(asmpassword, "ASM Password");
		action.inputText(asmusername, "Agent ID", getUsername(username));
		action.inputText(asmpassword, "password text box", Reporting.getPassword());
		action.submitElement(asmsigninbtn, "Login Button");
		// action.refreshCurrentPage();
		/*
		 * if(action.isElementExistsIgnore(sessionBtn)) {
		 * action.getLogger().pass("ASM Login Successfull"); }else
		 * if(action.isElementExistsIgnore(asmsigninbtn)){ throw new
		 * CustomException("ASM Login Failed due to Incorrect Credentials"); }else {
		 * action.getCurrentPageTitle(); throw new CustomException("ASM Login Failed");
		 * }
		 */
		return new HomePage();
	}

	public ProductListPage Search_This_Product(Object productName) {
		action.inputText(txt_searchBox_bgs, "Search text box", productName.toString());
		action.inputText(txt_searchBox_bgs, "Space Bar", Keys.SPACE);
		action.clickWithClickableWait(btn_search_bgs, "search icon button");
		return new ProductListPage();
	}

	public SearchResultsPage Search_This_Product_No_Results(Object productName) {
		action.inputText(txt_searchBox_bgs, "Search text box", productName.toString());
		action.inputText(txt_searchBox_bgs, "Space Bar", Keys.SPACE);
		action.clickWithClickableWait(btn_search_bgs, "search icon button");
		return new SearchResultsPage();
	}

	public LoginPage Navigate_To_BuyDB_From_PLP_NonAuth() {
		action.clickLink(buyDatabaseBtn);
		return new LoginPage();
	}

	public BuyDatabasePage Navigate_To_BuyDB_From_PLP_Auth() {
		action.clickLink(buyDatabaseBtn);
		return new BuyDatabasePage();
	}

	/**
	 * @param takes
	 *            either single product or List of Products
	 */
	@SuppressWarnings("unchecked")
	public <T> ProductDescriptionPage Add_This_Product_To_Cart(T productList) {
		ProductListPage plp = null;
		String cls;
		try {
			cls = productList.getClass().getName();
		} catch (NullPointerException e) {
			throw new CustomException(
					"May be failed due to non-existent product code in test data : " + productList.toString(), e);
		}
		if (cls.contains("ArrayList")) {
			for (String s : (ArrayList<String>) productList) {
				plp = Search_This_Product(s);
				if (plp.isAddToCartButtonExists()) {
					break;
				} else {
					action.logWarning("Add to Cart button NOT found for this product :- " + s);
				}
			}

		} else if (cls.contains("String")) {
			plp = Search_This_Product((String) productList);
		}

		ProductDescriptionPage pdp = plp.clickFirstProductFromList();
		pdp.clickAddToCarButton();
		action.switchToDefaultContent();
		action.clickLink("CONTINUE SHOPPING");
		action.waitForElementInvisibility(panel_pop_up);
		return pdp;
	}

	/**
	 * @param takes
	 *            either single product or List of Products
	 */
	@SuppressWarnings("unchecked")
	public <T> ProductListPage Add_This_Product_To_Cart_From_PLP_Continue(T productList) {
		ProductListPage plp = null;
		String cls;
		try {
			cls = productList.getClass().getName();
		} catch (NullPointerException e) {
			throw new CustomException(
					"May be failed due to non-existent product code in test data : " + productList.toString(), e);
		}
		if (cls.contains("ArrayList")) {
			for (String s : (ArrayList<String>) productList) {
				plp = Search_This_Product(s);
				if (plp.isAddToCartButtonExists()) {
					plp.addtoCartFromPLP();
					plp.clickContinueShopping();
					break;
				} else {
					action.logWarning("Add to Cart button NOT found for this product list :- " + s);
				}
			}

		} else if (cls.contains("String")) {
			plp = Search_This_Product((String) productList);
			plp.clickContinueShopping();
		}
		return plp;
	}

	/**
	 * @param takes
	 *            either single product or List of Products
	 */
	@SuppressWarnings("unchecked")
	public <T> CartPage Add_This_Product_To_Cart_From_PLP_Checkout(T productList) {
		ProductListPage plp = null;
		CartPage cart = null;
		String cls;
		try {
			cls = productList.getClass().getName();
		} catch (NullPointerException e) {
			throw new CustomException(
					"May be failed due to non-existent product code in test data : " + productList.toString(), e);
		}
		if (cls.contains("ArrayList")) {
			for (String s : (ArrayList<String>) productList) {
				plp = Search_This_Product(s);
				if (plp.isAddToCartButtonExists()) {
					plp.addtoCartFromPLP();
					cart = plp.clickCheckout();
					break;
				} else {
					action.logWarning("Add to Cart button NOT found for this product list:- " + s);
				}
			}

		} else if (cls.contains("String")) {
			plp = Search_This_Product((String) productList);
			plp.addtoCartFromPLP();
			cart = plp.clickCheckout();

		}
		return cart;
	}

	@SuppressWarnings("unchecked")
	public <T> ProductDescriptionPage Add_LMS_Product_To_Cart(T productList) {
		ProductListPage plp = null;
		String cls;
		try {
			cls = productList.getClass().getName();
		} catch (NullPointerException e) {
			throw new CustomException(
					"May be failed due to non-existent LMS product code in test data : " + productList.toString(), e);
		}
		if (cls.contains("ArrayList")) {
			for (String s : (ArrayList<String>) productList) {
				plp = Search_This_Product(s);
				if (plp.isSeeDetailsButtonExists()) {
					break;
				} else {
					action.logWarning("See Details button NOT found for this product :- " + s);
				}
			}

		} else if (cls.contains("String")) {
			plp = Search_This_Product((String) productList);
		}
		if (action.isElementExistsIgnore(seeDetailsBtn)) {
			action.clickWithClickableWait(seeDetailsBtn, "See Details Button");
		} else {
			plp.clickFirstProductFromList();
		}

		// ProductDescriptionPage pdp = plp.clickFirstProductFromList();
		return new ProductDescriptionPage();
	}

	public ProductDescriptionPage Validate_Non_Config_Product_In_PDP(Object productName) {
		ProductListPage plp = Search_This_Product(productName.toString());
		ProductDescriptionPage pdp = plp.clickFirstProductFromList();
		return pdp.validateNonConfigPDPElement();

	}

	public ProductDescriptionPage Validate_Config_Product_In_PDP(Object productName) {
		ProductListPage plp = Search_This_Product(productName.toString());
		ProductDescriptionPage pdp = plp.clickFirstProductFromList();
		return pdp.validateConfig();

	}

	protected String getUsername(Login un) {
		String usr;
		for (int i = 0; i < Reporting.pool.size(); i++) {
			usr = Reporting.pool.get(i);
			if (usr.contains(un.toString())) {
				Reporting.pool.remove(i);
				ThreadManager.username.set(usr);
				return action.getCurrentUsername();

			}

		}
		throw new CustomException("Required login username not found in test data : - " + un.toString());
	}

	/**
	 * 
	 * @param mainMenu
	 *            Top menu bar
	 * @param product
	 *            name to choose from drop down menu, if specified emtpy "", clicks
	 *            direclty on main menu
	 * @return
	 */
	public ProductDescriptionPage Add_This_Product_To_Cart_From_Main_Menu(String mainMenu, String product) {
		WebElement element = action.findElement(By.linkText(mainMenu), mainMenu);
		if (product.contentEquals("")) {
			action.clickWithClickableWait(element, mainMenu);
		} else {
			action.mouseHover(element, mainMenu);
			action.switchToDefaultContent();
			action.clickLink(product);
		}
		// action.waitForElementInvisibility(dropdownNavPanel);
		ProductListPage plp = new ProductListPage();
		ProductDescriptionPage pdp = plp.clickProductFromListHavingAddToCartBtn();
		pdp.clickAddToCarButton();
		action.switchToDefaultContent();
		action.clickLink("CONTINUE SHOPPING");
		action.waitForElementInvisibility(panel_pop_up);
		return pdp;

	}

	public ProductListPage Category_PLP_Page_From_Main_Menu(String mainMenu, String product) {
		WebElement element = action.findElement(By.linkText(mainMenu), mainMenu);
		if (product.contentEquals("")) {
			action.clickWithClickableWait(element, mainMenu);
		} else {
			action.mouseHover(element, mainMenu);
			action.switchToDefaultContent();
			action.clickLink(product);
		}
		ProductListPage plp = new ProductListPage();
		return plp.Validate_Popularity_SortOption_On_Category_PLPPage();
	}

	public BasePage Empty_Out_Cart() {
		String text = action.getText(countercartIcon, "Cart Counter");
		if (!text.contentEquals("")) {
			action.clickWithClickableWait(countercartIcon, "Cart icon");
			CartPage cartPage = new CartPage();
			action.logInfo("Emptying the cart ...");
			cartPage.removeProductsFromCart();
			return cartPage;
		} else {
			action.logInfo("Cart quantity is empty. Hence skipping navigating to cart page");
			return new HomePage();
		}

	}

	public BasePage LogOut_Account() {
		clickAccountIcon();
		action.clickWithoutScroll(logOut, "Logout button");
		return this;
	}

	public BasePage LogOut_Account_From_AuthenticatedPages() {
		clickAccountIcon();
		action.clickWithoutScroll(logOut, "Logout button");
		action.waitForTextToBePresentInElement(logouttxt, expectedlogouttxt);
		verify.verifyTextFromElementIgnoringCase(logouttxt, expectedlogouttxt, "Logout text message check");
		return this;
	}

	void clickAccountIcon() {
		action.clickWithClickableWait(link_account_icon_bgs, "Account icon");
	}

	public AccountDetails Goto_AccountDetails() {
		clickAccountIcon();
		action.clickLink("Account Details");
		return new AccountDetails();

	}

	public AircraftDetails GoTo_AircraftDetails() {
		clickAccountIcon();
		action.clickLink("Aircraft Details");
		return new AircraftDetails();

	}

	public ChangePassword Goto_ChangePassword() {
		clickAccountIcon();
		action.clickLink("Change Password");
		return new ChangePassword();

	}

	public ChangeEmail Goto_ChangeEmail() {
		clickAccountIcon();
		action.clickLink("Change Email / User ID");
		return new ChangeEmail();

	}

	public OrderHistoryPage Goto_OrderHistory() {
		clickAccountIcon();
		action.clickLink("Order History");
		return new OrderHistoryPage();

	}

	public MySubscriptions Goto_MySubscriptions() {
		clickAccountIcon();
		action.clickLink("My Subscriptions");
		return new MySubscriptions();
	}

	public BasePage Capture_SubscriptionDetails_OnOrderDetailsPage(ConcurrentHashMap<String, String> cardPayment) {
		HashMap<String, String> hm = new HashMap<String, String>();

		action.clickWithClickableWait(OrderIDLink, "1st Order ID link in Order history");
		verify.isElementDisplayed(billingAddress, "Billing address");

		String ExpMont = cardPayment.get("month");
		String[] ExpMonth = ExpMont.split("-");
		String EXPMONTH = ExpMonth[0];
		hm.put("CN", cardPayment.get("name"));
		hm.put("EM", EXPMONTH);
		hm.put("EY", cardPayment.get("year"));
		ThreadManager.placeholder.get().setHashmap(hm);

		return this;
	}

	public AddUserPage GoTo_AddUser() {
		clickAccountIcon();
		action.clickLink("My Company");
		action.clickWithClickableWait(addnewuser, "Add new user");
		return new AddUserPage();

	}

	public OrderDetailsPage Verify_Order() {
		clickAccountIcon();
		action.clickLink(orderHistorylink);
		OrderHistoryPage ordHistry = new OrderHistoryPage();
		OrderDetailsPage od = ordHistry.clickOrderNumber(orderNum);
		od.getOrderDetailsScreenshot();
		return od;
	}

	public OrderDetailsPage Verify_Alacarte_Order() {
		clickAccountIcon();
		action.clickLink(orderHistorylink);
		OrderHistoryPage ordHistry = new OrderHistoryPage();
		OrderDetailsPage od = ordHistry.clickOrderNumber(orderNum);
		od.getOrderDetailsScreenshot();
		return od;
	}

	public CartPage Go_To_CartPage() {
		action.clickWithClickableWait(countercartIcon, "Cart icon");
		return new CartPage();
	}

	/*
	 * protected void acceptCookies() { try { action.jsClick(
	 * ThreadManager.getInstance().getDriver().findElement(By.
	 * xpath("//button[@title='Accept Cookies Button']")), "Accept Cookies");
	 * action.jsClick( ThreadManager.getInstance().getDriver() .findElement(By.
	 * xpath("//button[@class='optanon-alert-box-close banner-close-button']")),
	 * "Accept Cookies"); } catch (Exception e) {
	 * 
	 * } }
	 */

	/**
	 * 
	 * @return
	 */
	public CheckOutPage Checkout_With_Cart() {
		CheckOutPage checkout = Go_To_CartPage().Checkout();
		return checkout;
	}

	public OrderConfirmationPage Place_Order() {
		CheckOutPage checkout = new CheckOutPage();
		OrderConfirmationPage order = checkout.placeOrderWithoutFetchingOrderNumber();
		return order.getOrdernumber();

	}

	public OrderConfirmationPage Write_PO_to_Note() {

		OrderConfirmationPage order = new OrderConfirmationPage();
		return order.getOrdernumberToNote();

	}

	public PageAction Read_PO_From_Note() {

		PageAction order = new PageAction();
		return order.readFromNote();

	}

	public HomePage GoTo_HomePage() {
		action.clickWithClickableWait(logo, "Logo");
		return new HomePage();

	}

}
