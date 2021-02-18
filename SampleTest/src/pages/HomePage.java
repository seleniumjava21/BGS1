package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.ThreadManager;


public class HomePage extends BasePage {

	//PageAction actions;

	public HomePage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();	
		if((ThreadManager.logger.get()==null) ||(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName)))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Homepage");

		}

	}

	@FindBy(id ="accountNumber") private WebElement accountnumber;
	@FindBy(id ="invoiceNumber") private WebElement invoicenumber;
	@FindBy(xpath ="//form[@id='retrieveInvoiceForm']/h2") private WebElement payinvoiceheadertxt;
	@FindBy(xpath ="//div[@class='checkoutTopContent']") private WebElement Login_payinvoiceheadertxt;
	@FindBy(xpath ="//div[@class='col-xs-6 text-uppercase invoiceStatusHeader open selected']") private WebElement invoiceOpentab;
	@FindBy(xpath ="//div[@class='col-xs-6 text-uppercase invoiceStatusHeader closed ']") private WebElement invoiceClosedtab;
	@FindBy(xpath ="//div[@class='header-logo-container']") private WebElement boeinglogo;
	@FindBy(xpath ="//a[@class='visible-sm visible-md visible-lg header_shop']") private WebElement shoplink;
	@FindBy(xpath ="(//button[@class='header-opt nav-bg-hover header-opt-btn-dropdown dropdown-toggle'])[1]") private WebElement bentomenu;
	@FindBy(xpath ="//div[@class='dropdown hidden-xs hidden-sm open']") private WebElement bentoMenuClosed;
	@FindBy(xpath ="//a[@class='header-opt header-opt-cart nav-bg-hover']") private WebElement carticon;
	@FindBy(id ="js-site-search-input") private WebElement searchbar;
	
	@FindBy(xpath ="(//div[@class='yCmsComponent dropdown-link-group'])[1]/h2") private WebElement ourbrands;
	@FindBy(xpath ="(//div[@class='yCmsComponent dropdown-link-group'])[1]/a[contains(text(),'Aviall')]") private WebElement AviallLinktxt;
	@FindBy(xpath ="//ul[@class='dropdown-menu']/li//div/ul/li[2]/a[2]") private WebElement AerDataLinktxt;
	@FindBy(xpath ="//ul[@class='dropdown-menu']/li//div/ul/li[2]/a[3]") private WebElement ILSLinktxt;
	@FindBy(xpath ="(//div[@class='yCmsComponent dropdown-link-group'])[1]/a[contains(text(),'ForeFlight')]") private WebElement foreFlightLinktxt;
	@FindBy(xpath = "//h4[@class='panel-title']")private List<WebElement> footerHeaderPanel;
	@FindBy(xpath = "//h4[@class='panel-title']/following-sibling::ul/li/a") private List<WebElement> headerNameLinks;
	@FindBy(xpath = "//ul[@class='footer-brand-list']/li//img") private List<WebElement> footerBrands;
	@FindBy(xpath = "//div[@class='footer-social-icons']/a") private List<WebElement> socialMedia;	
	@FindBy(xpath = "//div[@id='top']//a") private List<WebElement> bottomLinks;
	@FindBy(xpath = "//div[@class='footer-content']") private WebElement privacyContent;
	@FindBy(xpath = "//div[@class='footer-content']/p/a") private WebElement privacylink;
	@FindBy(xpath = "//a[@class='button-primary']") private WebElement payInvoiceLnk;
	
	@FindBy(xpath="//h2[contains(text(),'SHOP')]") private WebElement shopHeader;	
	@FindBy(xpath="//a[contains(text(),'Boeing PART Page')]") private WebElement boeingPartpage;
	@FindBy(xpath="//a[contains(text(),'Boeing distribution (formerly Aviall)')]") private WebElement aviallLink;
	@FindBy(xpath="//a[contains(text(),'Boeing distribution (formerly KLX)')]") private WebElement klxLink;
	@FindBy(xpath="//div[@class='yCmsComponent dropdown-link-group']//a[contains(text(),'Jeppesen')]") private WebElement jeppesenLink;
	@FindBy(xpath="//div[@class='yCmsComponent dropdown-link-group']//a[contains(text(),'ForeFlight')]") private WebElement foreFlightLink;
	//private String payInvoiceLnk = "PAY INVOICE";
	private String payYourInvoiceLnk ="Pay Your Invoice";
	protected String AviallLink = "Aviall";
	protected String AerDataLink = "AerData";
	protected String ILSLink = "ILS";
	protected String JeppesenLink = "Jeppesen";
	protected int footer_headercolumn = 2;
	protected String footer_headerhelp = "HELP & INSTRUCTIONS";
	protected String footer_headerorders = "YOUR ORDER";
	protected String footer_headercatory1 = "CATEGORY HEADER";
	protected String footer_headercatory2 = "CATEGORY HEADER";
	protected String footer_privacycontent = "*The Privacy and Cookie Statement for The Boeing Company and its family of brands has changed, effective May 22, 2019, to allow for a more unified customer experience. You can review the updated Statement here";
	protected String footer_facebook = "facebook-icon";
	protected String footer_youtube =  "linkedin-icon";
	protected String footer_linkedin = "youtube-icon";
	protected String footer_twitter =  "twitter-icon";


	/**
	 * @return ****************************************************************************************************************************************/
	public HomePage Validate_HomePage() {
		verify.isElementDisplayed(payInvoiceLnk, "Homepage element");
		verify.pageTitle("Homepage");

		return this;
	}
	
	public void acceptCookies() {
		if(System.getProperty("env").equals("QA")) {
			WebElement ac= null;
			try {
				ac = ThreadManager.getInstance().getDriver().findElement(By.id("onetrust-accept-btn-handler"));
				//waitForElementDisplayedIgnore(ac, "Accept Cookies");
				ThreadManager.getInstance().getDriver().switchTo().defaultContent();
				JavascriptExecutor executor = (JavascriptExecutor) ThreadManager.getInstance().getDriver();;
				executor.executeScript("arguments[0].click();", ac);
				action.logInfo("clicked Accept Cookies");
				action.waitForElementInvisibility(ac);
			} catch (Exception e) {
				System.out.println("Unable to click on Accept cookies in QA");
			}
		}
		
		
	}

	public HomePage Validate_GNB_Headers() {
		verify.isElementDisplayed(boeinglogo, "Boeing Logo");
		//verify.isElementNotDisplayed(shoplink, "Shop");
		verify.isElementDisplayed(bentomenu, "Bento Menu");
		verify.isElementDisplayed(searchbar, "Search bar");
		verify.isElementDisplayed(carticon, "Cart Icon");
		return this;
	}

	public HomePage Validate_Bento_Menu() {
		action.clickWithoutScroll(bentomenu, "Bento Menu Open");
		action.waitForElementDisplayed(shopHeader, "Shop header text in Bento dropdown");
		verify.isElementDisplayed(aviallLink, "Aviall Brand Link");
		verify.isElementDisplayed(klxLink, "KLX Brand Link");
		verify.isElementDisplayed(boeingPartpage, "Boeing PART page Link");
		verify.isElementDisplayed(jeppesenLink, "Jeppesen Brand Link");
		verify.isElementDisplayed(foreFlightLink, "Foreflight Link");
		action.clickWithoutScroll(bentoMenuClosed, "Bento Menu Close");
		return this;
	}

	public AccountDetails Goto_AccountDetails() {
		clickAccountIcon();
		action.clickLink("Account Details");
		return new AccountDetails();

	}

	//Authenticated PayInvoice 
	public PayInvoicePage Goto_PayInvoice_Header() {
		action.clickWithoutScroll(payInvoiceLnk, "Pay Invoice header button");	
		verify.isElementDisplayed(invoiceOpentab, "Pay Invoice Open tab");	
		verify.isElementDisplayed(invoiceClosedtab, "Pay Invoice Closed tab");
		return new PayInvoicePage();

	}
	
	//UnAuth PayInvoice 
	public PayInvoicePage Goto_PayInvoice_Header_unAuth() {
		action.clickWithoutScroll(payInvoiceLnk, "Pay Invoice header button");	
		verify.isElementDisplayed(payinvoiceheadertxt, "Pay Invoice page text");			
		verify.verifyTextFromElementIgnoringCase(payinvoiceheadertxt, "Pay Invoice", "Pay Invoice page text check");
		verify.isElementDisplayed(accountnumber, "Pay Invoice account number");
		verify.isElementDisplayed(invoicenumber, "Pay Invoice number");
		return new PayInvoicePage();

	}

	public PayInvoicePage Goto_PayInvoice_Footer() {
		//actions.clickElement(payinvoicefooterlink, "Pay Invoice Footer");
		action.clickLink(payYourInvoiceLnk);
		//UnAuth PayInvoice 
		//verify.isElementDisplayed(payinvoiceheadertxt, "Pay Invoice page text");
		verify.isElementDisplayed(invoiceOpentab, "Pay Invoice Open tab");	
		verify.isElementDisplayed(invoiceClosedtab, "Pay Invoice Closed tab");
		return new PayInvoicePage();

	}
	public PayInvoicePage Goto_PayInvoice_Footer_unAuth() {		
		action.clickLink(payYourInvoiceLnk);
		//UnAuth PayInvoice 
		verify.isElementDisplayed(payinvoiceheadertxt, "Pay Invoice page text");		
		//UnAuth PayInvoice 
		verify.verifyTextFromElementIgnoringCase(payinvoiceheadertxt, "Pay Invoice", "Pay Invoice page text check");		
		verify.isElementDisplayed(accountnumber, "Pay Invoice account number");
		verify.isElementDisplayed(invoicenumber, "Pay Invoice number");
		return new PayInvoicePage();

	}


	public HomePage verifyFooterColumnsCount() {
		int footerpanel = footerHeaderPanel.size();
		if (footerpanel == footer_headercolumn) {
			ThreadManager.logger.get().info(footerpanel + " Columns are displayed in Footer Section desktop view");
			for (WebElement ele : footerHeaderPanel) {
				String headerText = action.getTextWithoutLogging(ele);
				if (headerText.contains(footer_headerhelp)) {
					ThreadManager.logger.get().info(headerText + " is displayed as Expected");
				} else if (headerText.contains(footer_headerorders)) {
					ThreadManager.logger.get().info(headerText + " is displayed as Expected");					

				} else {
					ThreadManager.logger.get().info(headerText + " is not displayed as Expected");		
					ThreadManager.logger.get()
					.fail(MarkupHelper.createLabel(headerText + " is not displayed as Expected",
							ExtentColor.RED));
				}
			}

		} else {
			ThreadManager.logger.get().info("Expected 2 Columns but " + footerpanel + " Columns are displayed in Footer Section desktop view");
			ThreadManager.logger.get()
			.fail(MarkupHelper.createLabel("Expected 2 Columns but " + footerpanel + " Columns are displayed in Footer Section desktop view",
					ExtentColor.RED));

		}
		return this;

	}

	public HomePage verifyHeaderLinksFooterSection() {
		verifyNewTabLinks(headerNameLinks, "title");
		return this;
	}

	public HomePage verifyFooterSocialLinks() {

		verifyNewTabLinks(socialMedia, "class");
		return this;
	}
	
	public HomePage verifyFooterBottomLinks() {
		verifyNewTabLinks(bottomLinks, "title");
		return this;
	}
	
	public HomePage verifyBrandsAvailable() {

		for (WebElement ele : footerBrands) {
			ThreadManager.logger.get().info(action.getTextWithoutLogging(ele) + " Brand is displayed");
		
		}
		return this;
	}
	
	public HomePage verifyPrivacyFooterText() {

		if (action.getTextWithoutLogging(privacyContent).contains(footer_privacycontent)) {
			ThreadManager.logger.get().info("Redirected to Expected URL " + action.getCurentURL());
			action.clickAndIgnoreFailure(privacylink, "Privacy Link");			
			if(action.getCurentURL().equalsIgnoreCase("http://www.boeing.com/privacy-and-cookie-policy.page")) {
				ThreadManager.logger.get().info("Redirected to Expected URL " + action.getCurentURL());

			}else {			
				ThreadManager.logger.get()
				.fail(MarkupHelper.createLabel("Redirected to " + action.getCurentURL(),
						ExtentColor.RED));
			
			}
			
		} else {
			ThreadManager.logger.get()
			.fail(MarkupHelper.createLabel("Privacy and Cookie-Policy Content is not Matching ",
					ExtentColor.RED));			
		}
		return this;
	}

	public void verifyNewTabLinks(List<WebElement> links, String linkName) {
		String title = null;
		for (WebElement ele : links) {

			String href = action.getAttribute(ele, "href", "href");
			if (!href.substring(href.length() - 1).equals("#")) {

				title = action.getAttribute(ele, linkName, linkName);								

				if (!(title.equals("Pay Your Invoice"))) {	
					action.clickAndIgnoreFailure(ele, title);
					action.switchToNewTab();												
					if (action.getCurentURL().equalsIgnoreCase(href)) {
						ThreadManager.logger.get().info("Redirected to Expected URL " + action.getCurentURL());

					} else {

						ThreadManager.logger.get()
						.fail(MarkupHelper.createLabel("Expected " + href + " but Redirected to " + action.getCurentURL(),
								ExtentColor.RED));
					}
					action.closeNewTab();
					action.switchToParentTab();				
				}						



			} else {
				ThreadManager.logger.get().info(ele.getAttribute(linkName) + " does not contains reference link to validate -----> "
						+ href);
				
			}
		}
	}
}
