package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.Placeholder;
import utilities.ThreadManager;

public class AddCoveragePage extends BasePage {
	public AddCoveragePage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Guest");

		}
	}
	
	String WarningMessage = " Purchasing an additional coverage will be delivered as a separate file and once you transfer the new data to your data card the old coverage will be overwritten.";
	
	@FindBy(xpath = "//h1[contains(text(),'Add coverage to your subscription')]")
	private WebElement LBLAddCoverageHeader;

	@FindBy(xpath = "//div[contains(text(),'Subscription :')]")
	private WebElement LBLAddCoverageSubscr;
	
	@FindBy(xpath = "//div[contains(text(),'Aircraft :')]")
	private WebElement LBLAddCoverageAircraft;
	
	@FindBy(xpath = "//div[contains(text(),'Avionic :')]")
	private WebElement LBLAddCoverageDevice;
	
	@FindBy(xpath = "//div[contains(text(),'System ID :')]")
	private WebElement LBLAddCoverageSystemID;
	
	@FindBy(xpath = "//div[contains(text(),'Term :')]")
	private WebElement LBLAddCoverageTerm;
	
	@FindBy(xpath = "(//div[contains(text(),'Term :')]//following-sibling::div)[1]")
	private WebElement LBLTermDesc;
	
	@FindBy(xpath = "//div[contains(@class,'panel panel-default region-main-section')]//i[contains(@class,'material-icons oval')][contains(text(),'brightness_1')]")
	private WebElement LBLSelectCoverage;
	
	@FindBy(xpath = "//span[contains(text(),'Worldwide')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionWorldwide;
	
	@FindBy(xpath = "//span[contains(text(),'Worldwide')]")
	private WebElement LBLRegionWorldwide;

	@FindBy(xpath = "//span[contains(text(),'North America')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionNAmerica;
	
	@FindBy(xpath = "//span[contains(text(),'North America')]")
	private WebElement LBLRegionNAmerica;
	
	@FindBy(xpath = "//span[contains(text(),'Europe')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionEurope;
	
	@FindBy(xpath = "//span[contains(text(),'Europe')]")
	private WebElement LBLRegionEurope;
	
	@FindBy(xpath = "//span[contains(text(),'Pacific')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionPacific;
	
	@FindBy(xpath = "//span[contains(text(),'Pacific')]")
	private WebElement LBLRegionPacific;
	
	@FindBy(xpath = "//span[contains(text(),'Eurasia')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionEurasia;
	
	@FindBy(xpath = "//span[contains(text(),'Eurasia')]")
	private WebElement LBLRegionEurasia;
	
	@FindBy(xpath = "//span[contains(text(),'South America')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionSAmerica;
	
	@FindBy(xpath = "//span[contains(text(),'South America')]")
	private WebElement LBLRegionSAmerica;
	
	@FindBy(xpath = "//span[contains(text(),'Africa')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionAfrica;
	
	@FindBy(xpath = "//span[contains(text(),'Africa')]")
	private WebElement LBLRegionAfrica;
	
	@FindBy(xpath = "//span[contains(text(),'Middle East')]//preceding-sibling::div[contains(@class,'checkmark stay')]")
	private WebElement CHKBXRegionMEast;
	
	@FindBy(xpath = "//span[contains(text(),'Middle East')]")
	private WebElement LBLRegionMEast;
	
	@FindBy(xpath="//button[@data-service='YR']") 
	private WebElement BTNUntilRenewalCoverage;
	
	@FindBy(xpath="//button[@data-service='1TM']")
	private WebElement BTNOneTimeCoverage;
	
	@FindBy(xpath="//div[contains(text(),'Select length of coverage')]")
	private WebElement LBLCoverageLength;
	
	@FindBy(xpath="//span[contains(text(),'Choose to buy additional trip kit')]")
	private WebElement LBLChooseTripkit;
	
	@FindBy(xpath="//b[contains(text(),'For One Time')]")
	private WebElement LBLOneTimeCoverageText;
	
	@FindBy(xpath="//div[contains(@class,'data-bases-section')]//i[@class='material-icons oval active']")
	private WebElement ICONSelectDB;
	
	@FindBy(xpath="//div[contains(@class,'data-bases-section')]//div[contains(text(),'Databases')]")
	private WebElement LBLDatabases;
	
	@FindBy(xpath="//div[contains(@class,'data-bases-section')]//div[contains(@class,'caution-box')]//img")
	private WebElement IMGCaution;
	
	@FindBy(xpath="//div[contains(@class,'caution-box caution-border')]")
	private WebElement TXTWarningMessage;
	
	@FindBy(xpath="//h1[@class='panel-title']//a[contains(text(),'Obstacles')]")
	private WebElement LBLObstacles;
	
	@FindBy(xpath="//h1[@class='panel-title']//a[contains(text(),'Terrain')]")
	private WebElement LBLTerrain;
	
	@FindBy(xpath="//h1[@class='panel-title']//a[contains(text(),'Airport Directory')]")
	private WebElement LBLAirportDir;
	
	@FindBy(xpath="//h1[@class='panel-title']//a[contains(text(),'SafeTaxi')]")
	private WebElement LBLSafeTaxi;
	
	@FindBy(xpath="//h1[@class='panel-title']//a[contains(text(),'NavData')]")
	private WebElement LBLNavData;
	
	@FindBy(xpath="//h1[@class='panel-title']//a[contains(text(),'Basemap')]")
	private WebElement LBLBasemap;
	
	@FindBy(xpath="//h1[@class='panel-title']//a[contains(text(),'Obstacles')]//parent::h1//parent::div")
	private WebElement AccordianObstacles;
	
	@FindBy(xpath="(//div[@class='panel-collapse collapse in']//a[@class='product__list--name'])[1]") 
	private WebElement productName;
	
	@FindBy(xpath="(//a[contains(text(),'VIEW COVERAGE')])[1]") private WebElement LNKViewCoverage;
	@FindBy(xpath="//button[@class='modal-content']") private WebElement coverageDialog;
	@FindBy(xpath="//div[@class='product-name']") private WebElement productCoverageDialog;
	@FindBy(xpath="//div[@class='part-num']") private WebElement partnumCoverage;
	@FindBy(xpath="//div[@class='term']") private WebElement termCoverage;
	@FindBy(xpath="//div[@class='description']") private WebElement deviceDescCoverage;
	@FindBy(xpath="//button[@class='close']") private WebElement closeCoverageDialog;
	@FindBy(xpath="//div[@class='panel-collapse collapse in']/div/ul/li/a/img") private List<WebElement> coverageImages;
	@FindBy(xpath="//div[@class='panel panel-default']//div[@class='panel-collapse collapse in']") private WebElement CoverageCat;
	@FindBy(xpath="//span[@class='selectedNum selectedlength']") private WebElement productSelectedText;
	@FindBy(xpath="//button[@class='button-primary checkout-btn']") private WebElement checkoutBtn;
	@FindBy(xpath="//h1[@class='panel-title']/a") private List<WebElement> categoryNames;
	@FindBy(xpath="(//p[contains(text(),'Your selected coverage is already included in your')])[1]") private WebElement LBLCartErrMsg;
	String prdList ="//ul[@class='products-container']/li";
	By productList = By.xpath(prdList);
	String btn = "//button[@id='addToCartButton']";
	By addToCarBtn = By.xpath(btn);
	
	@FindBy(xpath="//div[contains(@id,'NavData')]//button[contains(@id,'addToCartButton') and not(@disabled) and not(contains(@style,'display: none;'))]") private List<WebElement> ActiveProducts;
	@FindBy(xpath="(//div[contains(@id,'NavData')]//button[contains(@id,'addToCartButton')])[1]") private WebElement BTNAddToCartNavD;
	
	String btnFaded = "//button[contains(@class,'remove-cart')]";
	By BTNRemoveCart = By.xpath(btnFaded);
	
	public AddCoveragePage Get_Subscription_EndDate()
	{
		String subDuration = action.getText(LBLTermDesc, "Subscription Duration");
		String[] subDates = subDuration.split("through");
		String subEndDate = subDates[1].toString().trim();
		Placeholder ph = new Placeholder();
		ph.setStringData(subEndDate);
		ThreadManager.placeholder.set(ph);
		action.logInfo("Subscription End date : " + subEndDate);
		return this;
	}
	
	//Method to verify Add Coverage page elements
	public AddCoveragePage Verify_AddCoveragePage() {
		verify.waitForElementDisplayed(LBLAddCoverageHeader, "Add coverage to your subscription");
		verify.isElementDisplayed(LBLAddCoverageSubscr, "Subscription label for coverage");
		verify.isElementDisplayed(LBLAddCoverageAircraft, "Aircraft label for coverage");
		verify.isElementDisplayed(LBLAddCoverageDevice, "Device label for coverage");
		verify.isElementDisplayed(LBLAddCoverageSystemID, "System ID label for coverage");
		verify.isElementDisplayed(LBLAddCoverageTerm, "Term label for coverage");
		verify.isElementDisplayed(LBLSelectCoverage, "Select where you want to fly");
		verify.isElementDisplayed(CHKBXRegionWorldwide, "Worldwide Coverage checkbox");
		verify.isElementDisplayed(LBLRegionWorldwide, "Worldwide Coverage");
		verify.isElementDisplayed(CHKBXRegionNAmerica, "North America Coverage checkbox");
		verify.isElementDisplayed(LBLRegionNAmerica, "North America Coverage");
		verify.isElementDisplayed(CHKBXRegionPacific, "Pacific Coverage checkbox");
		verify.isElementDisplayed(LBLRegionPacific, "Pacific Coverage");
		verify.isElementDisplayed(CHKBXRegionEurasia, "Eurasia Coverage checkbox");
		verify.isElementDisplayed(LBLRegionEurasia, "Eurasia Coverage");
		verify.isElementDisplayed(CHKBXRegionSAmerica, "South America Coverage checkbox");
		verify.isElementDisplayed(LBLRegionSAmerica, "South America Coverage");
		verify.isElementDisplayed(CHKBXRegionAfrica, "Africa Coverage checkbox");
		verify.isElementDisplayed(LBLRegionAfrica, "Africa Coverage");
		verify.isElementDisplayed(CHKBXRegionMEast, " MiddleEast Coverage checkbox");
		verify.isElementDisplayed(LBLRegionMEast, "MiddleEast Coverage");
		return this;
	}
		
	public AddCoveragePage Choose_Fly_Location(String location) {
		WebElement e = action.findElement(By.xpath("//input[@id='"+location+"']//ancestor::label[contains(@class,'region-checkbox')]"), location);
		action.scrollToElement(e, location+" check box");
		action.clickWithClickableWait(e, location+" check box");
		action.waitForElementDisplayed(LBLCoverageLength, "Select length of coverage");
		action.scrollToElement(LBLCoverageLength, "Select length of coverage");
		verify.isElementDisplayed(LBLCoverageLength, "Select length of coverage");
		verify.isElementDisplayed(LBLChooseTripkit, "Choose to buy additional trip kit");
		verify.isElementDisplayed(LBLOneTimeCoverageText, "One Time Coverage text");
		verify.isElementDisplayed(BTNOneTimeCoverage, "One Time Button");
		verify.isElementDisplayed(BTNUntilRenewalCoverage,"Until Renewal");
		return this;
	}
	
	public AddCoveragePage Verify_CoverageProds_Based_On_FlyLoc()
	{
		action.waitForElementDisplayed(ICONSelectDB, "Database");
		verify.isElementDisplayed(ICONSelectDB, "Database");
		verify.isElementDisplayed(productName, "First Product");
		return this;
	}
	
	//DABM-2204 : Method to select multiple fly location
	public AddCoveragePage Choose_Multiple_Fly_Location(String location, String location1) {
		WebElement e = action.findElement(By.xpath("//input[@id='"+location+"']//ancestor::label[contains(@class,'region-checkbox')]"), location);
		action.scrollToElement(e, location+" check box");
		action.clickWithClickableWait(e, location+" check box");
		WebElement e1 = action.findElement(By.xpath("//input[@id='"+location1+"']//ancestor::label[contains(@class,'region-checkbox')]"), location1);
		action.clickWithClickableWait(e1, location1+" check box");
		action.waitForElementDisplayed(LBLCoverageLength, "Select length of coverage");
		action.scrollToElement(LBLCoverageLength, "Select length of coverage");
		verify.isElementDisplayed(LBLCoverageLength, "Select length of coverage");
		verify.isElementDisplayed(LBLChooseTripkit, "Choose to buy additional trip kit");
		verify.isElementDisplayed(LBLOneTimeCoverageText, "One Time Coverage text");
		verify.isElementDisplayed(BTNOneTimeCoverage, "One Time Button");
		verify.isElementDisplayed(BTNUntilRenewalCoverage,"Until Renewal");
		return this;
	}
	
	public AddCoveragePage Verify_CoverageSection_Hidden_When_Region_Unselected(String location) {
		WebElement e = action.findElement(By.xpath("//input[@id='"+location+"']//ancestor::label[contains(@class,'region-checkbox')]"), location);
		action.scrollToElement(e, location+" check box");
		action.clickWithClickableWait(e, location+" check box");
		verify.isElementNotDisplayed(LBLCoverageLength, "Select length of coverage");
		verify.isElementNotDisplayed(LBLChooseTripkit, "Choose to buy additional trip kit");
		verify.isElementNotDisplayed(LBLOneTimeCoverageText, "One Time Coverage text");
		return this;
	}
	
	public AddCoveragePage Select_Coverage_Until_Renewal() {
		selectLengthOfCoverage(BTNUntilRenewalCoverage,"Until Renewal");
		return this;
	}

	public AddCoveragePage Select_Length_Of_Coverage_OneTime() {
		selectLengthOfCoverage(BTNOneTimeCoverage,"One Time");
		return this;
	}
	
	public AddCoveragePage VerifyDatabaseElements()
	{
		action.waitForElementDisplayed(ICONSelectDB, "Database Icon");
		verify.isElementDisplayed(ICONSelectDB, "Database Icon");
		verify.isElementDisplayed(LBLDatabases, "Databases Label");
		verify.isElementDisplayed(IMGCaution, "Caution Icon");
		String CautionNote = action.getText(TXTWarningMessage, "CautionNote");
		verify.verifyTextContains(WarningMessage, CautionNote, "Warning message is as expected..");
		verify.waitForElementDisplayed(LBLObstacles, "LBLObstacles");
		action.clickWithClickableWait(LBLObstacles, " AccordianObstacles");
		verify.isElementDisplayed(LBLTerrain, "LBLTerrain");
		verify.isElementDisplayed(LBLAirportDir, "LBLAirportDir");
		verify.isElementDisplayed(LBLSafeTaxi, "LBLSafeTaxi");
		verify.isElementDisplayed(LBLNavData, "LBLNavData");
		verify.isElementDisplayed(LBLBasemap, "LBLBasemap");
		return this;
	}
	
	public AddCoveragePage VerifyNavDataDBElements()
	{
		action.waitForElementDisplayed(ICONSelectDB, "Database");
		verify.isElementDisplayed(ICONSelectDB, "Database");
		verify.isElementDisplayed(LBLDatabases, "LBLDatabases");
		verify.isElementNotDisplayed(IMGCaution, "IMGCaution");
		verify.isElementDisplayed(LBLNavData, "LBLNavData");
		return this;
	}
	
	public AddCoveragePage Select_And_Verify_Coverage(String term)
	{
		verify.waitForElementDisplayed(LBLNavData, "LBLNavData");
		action.clickWithClickableWait(LBLNavData, "LBLNavData");
		String txtprdname = action.getText(productName, "Product name before click on View coverage");
		action.logInfo("Before coverage popup prdtxt"+txtprdname);
		action.jsClick(LNKViewCoverage, "View Coverage");
		action.switchToDefaultContent();
		action.waitUntilTextVisibleInElement(productCoverageDialog, "Coverage dialog");
		verify.waitUntilTextVisibleInElement(partnumCoverage, "Part number field");
		verify.verifyTextContains(term,action.getText(termCoverage,"Term text" ) , "Term validation on Coverage dialog");
		action.clickWithClickableWait(closeCoverageDialog, "Close coverage dialog");
		action.waitForElementInvisibility(partnumCoverage);
		action.clickWithClickableWait(LBLNavData, " Accordian LBLNavData");
		return this;
	}
	
	private void selectLengthOfCoverage(WebElement element, String lengthOfCoverage) {
		action.waitForElementVisibility(element, lengthOfCoverage +"button");
		action.clickWithClickableWait(element, lengthOfCoverage +"button");
	}
	
	public AddCoveragePage Add_Coverage_Product_To_Cart() {
		action.waitForElementDisplayed(CoverageCat, "Category on Coverage Page");
		List<WebElement> pl =  action.findElements(productList, "product block");
		action.disableImplicitWait();
		int i = 1;
		for(WebElement e:pl){
			action.scrollToElement(pl.get(i), "Product block:-"+i);
			By bttn = By.xpath("(" + prdList + "[" + i + "]" + btn + ")[1]");
			WebElement ment = action.isRelativeElementExistsIgnore(e, bttn, "add to Cart button");
			if (ment != null) {
				verify.isElementDisplayed(addToCarBtn, "AddtoCart button is Enabled");                      
				action.clickWithClickableWait(bttn, " add to cart button");
				action.waitForElementVisibility(productSelectedText, "1 SELECTED");
				verify.verifyTextFromElementIgnoringCase(productSelectedText, "1 SELECTED", "Product selected text after add to cart");
				verify.isElementEnabled(checkoutBtn, "Checkout button");
				action.resetImplicitWait();
				return this;
			}else {
				i++;
			}
		}
		throw new CustomException("Product with Add to Cart button not found in the category");
	}
	
	public AddCoveragePage Verify_Checkout_Button_Disabled()
	{
		action.getAttributeIgnoreEmpty(checkoutBtn, "disabled", "Checkout button disabled");
		return this;
	}
	
	public AddCoveragePage Add_CoverageProduct_To_Cart_For_Specific_Category(String catgname) {
		action.waitForElementsDisplayed(categoryNames, "categories");
		int size = categoryNames.size();
		Boolean catgfound = false;
		action.logInfo("Total categories found in BuyDB page  :- "+size);
		
		if(size==0) {
			throw new CustomException("Categories not found in BuyDB page");
		}
		
		int i=0;
		for(WebElement e:categoryNames) {
			String categoryText = action.getText(e, "category names");
			if(action.matchTextContains(catgname, categoryText)) {
				action.scrollToElement(e, "Scroll to matched category");
				action.clickWithoutClickableWait(categoryNames.get(i), categoryText);
				action.logPass("Category match found and clicked  "  + catgname);
				action.disableImplicitWait();

				//Click on the 2nd active add to cart button under NavData category
				int activeProducts = ActiveProducts.size();
				action.logInfo("ActiveProducts under " + categoryText + " are " + activeProducts);
				if(!ActiveProducts.isEmpty()){
					WebElement firstActiveProd = ActiveProducts.get(0);
					//action.clickWithClickableWait(firstActiveProd, "Add To Cart first active product");
					action.jsClick(firstActiveProd, "Add To Cart first active product");
				}
				else
				{
					 new CustomException("No products found under "+ categoryText + " category.");
				}
				
				action.waitForElementVisibility(productSelectedText, "1 SELECTED");
				verify.verifyTextFromElementIgnoringCase(productSelectedText, "1 SELECTED", "Product selected text after add to cart");
				action.resetImplicitWait();
				/*//verify.isElementDisplayed(LBLCartErrMsg, "Coverage already selected");
				verify.isElementEnabled(checkoutBtn, "Checkout button");
				action.scrollToElement(checkoutBtn, "Checkout button");
				action.clickWithClickableWait(BTNRemoveCart, " Remove from Cart button");
				verify.isElementEnabled(checkoutBtn, "Checkout button");
				action.clickWithClickableWait(BTNAddToCartNavD, "Add To Cart Nav Data");*/

				catgfound = true;
				break;
			}
			i++;
			
		}
		if(!catgfound)
		{
		 action.logFailure("Given category is not found in the page " +  catgname);
		}
		return this;

	}
	
	
	public AddCoveragePage Add_NavData_Coverage_Product_To_Cart() {
		action.waitForElementDisplayed(CoverageCat, "Category on Coverage Page");
		List<WebElement> pl =  action.findElements(productList, "product block");
		action.disableImplicitWait();
		int i = 1;
		for(WebElement e:pl){
			action.scrollToElement(pl.get(i), "Product block:-"+i);
			By bttn = By.xpath(prdList + "[" + i + "]" + btn);
			WebElement ment = action.isRelativeElementExistsIgnore(e, bttn, "add to Cart button");
			if (ment != null) {
				action.getAttributeIgnoreEmpty(checkoutBtn, "disabled", "Checkout button disabled");
				action.clickWithClickableWait(bttn, " add to cart button");
				action.waitForElementVisibility(productSelectedText, "1 SELECTED");
				verify.verifyTextFromElementIgnoringCase(productSelectedText, "1 SELECTED", "Nav Data Product added to cart");
				action.resetImplicitWait();
				return this;
			}else {
				i++;
			}
		}
		throw new CustomException("Product with Add to Cart button not found in the category");
	}
	
	public CartPage Checkout_From_Add_Coverage() {
		action.clickWithClickableWait(checkoutBtn, "Checkout button");
		return new CartPage();
	}
	
	
}
