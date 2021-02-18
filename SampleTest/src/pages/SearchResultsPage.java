package pages;

import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.ThreadManager;


public class SearchResultsPage extends BasePage {

	//PageAction actions;

	public SearchResultsPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();	
		if((ThreadManager.logger.get()==null) ||(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName)))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Search");

		}

	}

	
	@FindBy(xpath ="//b[contains(text(),'Search results')]") private WebElement searchBreadcrumb;
	@FindBy(xpath ="//b[contains(text(),'Search results')]/../i") private WebElement searchTerm;
	@FindBy(xpath ="//h1[@class='yCmsContentSlot']") private WebElement searchHeader;
	
	@FindBy(xpath ="//strong[contains(text(),'Search Tips')]") private WebElement searchTips;
	@FindBy(xpath ="//li[contains(text(),'Check your spelling.')]") private WebElement spellCheck;
	@FindBy(xpath ="//h2[contains(text(),'Browse by Category')]") private WebElement browseCategory;
	@FindBy(xpath ="//li[contains(text(),'Try searching by product type, brand, model number')]") private WebElement searchModel;
	@FindBy(xpath ="//li[contains(text(),'Broaden your search by using fewer or more general')]") private WebElement broadenSearch;
	@FindBy(xpath ="//h2[contains(text(),'Need Help?')]") private WebElement needHelp;
	//@FindBy(xpath ="//div[contains(@class,'yCmsContentSlot no-results-contact')]//a[contains(text(),'Contact Us')]") private WebElement contactUs;
	
	
	
	private String Home;



	/**
	 * @return ****************************************************************************************************************************************/
	public SearchResultsPage Validate_NoSearchResultsPage() {
		verify.isElementDisplayed(searchTips, "Search Tips element");
		verify.pageTitle("Search");
		return this;
	}

	public SearchResultsPage Validate_NoSearchResults(ConcurrentHashMap<String, String> searchtext)
	{
		verify.isElementDisplayed(searchBreadcrumb, "No Search results Breadcrumb");
		verify.verifyTextContains(searchtext.get("searchterm1"),action.getText(searchTerm, "Search term"), "Search term value");
		verify.verifyTextContains(searchtext.get("searchterm1"), action.getText(searchHeader, "Search term"), "Search term in header");
		verify.verifyTextFromElementIgnoringCase(searchTips,searchtext.get("searchtips"), "Search tips text");
		verify.verifyTextFromElementIgnoringCase(spellCheck,searchtext.get("spellcheck"), "Spell check text");
		verify.verifyTextFromElementIgnoringCase(searchModel,searchtext.get("searchprdtype"), "Search model text");
		verify.verifyTextFromElementIgnoringCase(broadenSearch,searchtext.get("broadsearch"), "Broaden search text");
		verify.isElementDisplayed(browseCategory, "Browse by Category");
		verify.isElementDisplayed(needHelp, "Need Help");
		return this;
	}
	
	public HomePage Navigate_To_HomePage() {
		action.clickLink("Home");
		return new HomePage();
		
	}


	public ContactUsPage Navigate_To_ContactUsPage() {
		action.clickLink("Contact Us");
		return new ContactUsPage();
		
	}

	public SearchResultsPage Navigate_to_contactus_validate_landingPg() {
		action.clickLink("Contact Us");
		if(ThreadManager.getInstance().getDriver().getWindowHandles().size()>1)
		{	action.switchToNewTab();
			new ContactUsPage().Validate_ContactUsPage();
			action.closeNewTab();
			action.switchToParentTab();
			
		}
		else
		{
			new ContactUsPage().Validate_ContactUsPage();
			ThreadManager.getInstance().getDriver().navigate().back();
			
		}
		return this;
	}
	
}
