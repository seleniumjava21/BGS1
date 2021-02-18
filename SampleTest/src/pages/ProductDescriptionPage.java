package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.PageAction;
import utilities.ThreadManager;

public class ProductDescriptionPage extends BasePage {
	
	public ProductDescriptionPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Product Name");
		}
		
	}

	@FindBy(id="addToCartButton") private WebElement addToCartBtn;
	@FindBy(xpath="//a[contains(text(),'See Details')]") private WebElement seeDetailsBtn;
	@FindBy(xpath="//span[@class='input-group-btn stay']") private WebElement plusBtn;
	@FindBy(xpath="//div[@class='qty-selector input-group js-qty-selector']//span[@class='input-group-btn']") private WebElement minusBtn;
	@FindBy(xpath="//div[@class='qty']") private WebElement prdQnty;

	@FindBy(xpath="//input[@id='instructorName']") private WebElement instructorName;
	@FindBy(id="institution") private WebElement institution;
	@FindBy(id="instructorName") private WebElement instructorname;
	
	@FindBy(xpath="//img[@class='product-image-panel-img']") private WebElement pdpimage;
	@FindBy(xpath="//div[@class='text-toggle-content']") private WebElement pdpdescription;
	@FindBy(xpath="//div[@class='product-name']") private WebElement pdpproductname;
	@FindBy(xpath="//div[@class='fulfillment']") private WebElement fulfillmentVal;
	
	@FindBy(xpath="//li[@id='accessibletabsnavigation0-0']//a[contains(text(),'Features')]") private WebElement pdpfeatures;
	@FindBy(xpath="//li[@id='accessibletabsnavigation0-1']//a[contains(text(),'Specifications')]") private WebElement pdpspecifications;
	@FindBy(xpath="//li[@id='accessibletabsnavigation0-2']//a[@id='tabreview']") private WebElement pdpsupport;
	@FindBy(xpath="//li[@id='accessibletabsnavigation0-3']//a[@id='tabreview']") private WebElement pdpdelivery;
	
	@FindBy(xpath="//span[@class='code']") private WebElement pdpconfigcode;
	@FindBy(id="pdpAddtoCartInput") private WebElement pdpvaluefield;
	
	@FindBy(xpath="//button[@class='text-toggle-button btn btn-secondary']//span[contains(text(),'View More')]")
	private WebElement viewMoreLink;
	@FindBy(xpath="//button[@class='text-toggle-button btn btn-secondary']//span[contains(text(),'View Less')]")
	private WebElement viewLessLink;
	
	public ProductDescriptionPage Validate_PDPage() {
		verify.isElementDisplayed(pdpdescription, "PDP Description");
		verify.isElementDisplayed(pdpfeatures, "PDP features");
		verify.isElementDisplayed(pdpspecifications, "PDP Specificaitons");
		
		return this;
	}
	
	protected void clickAddToCarButton() {
		//action.scrollToElement(addToCartBtn);
		action.clickWithClickableWait(addToCartBtn, "Add to Cart Btn");
	}
	
	public ProductDescriptionPage Enter_Institution_Details_LMS() {
		action.selectByIndex(institution, 1, "Institution dropdown in PDP");
		action.inputText(instructorname, "Instrctor name", "Automation test");
		clickAddToCarButton();
		action.switchToDefaultContent();
		action.clickLink("CONTINUE SHOPPING");
		action.waitForElementInvisibility(panel_pop_up);
		return this;
		
	}
	

	protected ProductDescriptionPage validateNonConfigPDPElement() {
		verify.isElementDisplayed(pdpimage, "PDP Image");
		verify.isElementDisplayed(pdpdescription, "PDP Description");
		verify.isElementDisplayed(pdpproductname, "PDP product Name");
		verify.isElementDisplayed(pdpfeatures, "PDP features");
		verify.isElementDisplayed(pdpspecifications, "PDP Specificaitons");
		return this;
	}

	public ProductDescriptionPage Validate_Fulfillment_PDP(String fulfillmentvalue)
	{
		
		verify.verifyTextMatchIgnoringCase(fulfillmentvalue, action.getText(fulfillmentVal, "Fulfillment value on PDP"), "Validate fulfillment on PDP");
        return this;
	}
	
	protected ProductDescriptionPage validateConfig() {
		validateNonConfigPDPElement();
		String codevalue = action.getText(pdpconfigcode, "pdf config code");
		verify.verifyTextContains("_", codevalue, "config code");
		return this;
	}
	public ProductDescriptionPage Verify_Product_Quantity_Update() {
		action.clickWithClickableWait(plusBtn, "plus btn");
		clickAddToCarButton();
		action.switchToDefaultContent();
		String updatedQnty = utils.getOnlyDigitsFromString(action.getText(prdQnty, "current product quantity"));
		verify.verifyTextMatchIgnoringCase("2", updatedQnty, "product quantity after clicking on + button once");
		action.clickLink("CONTINUE SHOPPING");
		action.waitForElementInvisibility(panel_pop_up);
		action.clickWithClickableWait(minusBtn, "minus btn");
		clickAddToCarButton();
		action.switchToDefaultContent();
		String reducedQnty = utils.getOnlyDigitsFromString(action.getText(prdQnty, "current product quantity"));
		verify.verifyTextMatchIgnoringCase("1", reducedQnty, "product quantity after clicking on - button once");
		action.clickLink("CONTINUE SHOPPING");
		action.waitForElementInvisibility(panel_pop_up);
		return this;
		
	}
	
	public CartPage Enter_Product_Quantity(String quantity) {
		for (int i = 1; i < Integer.parseInt(quantity); i++) {
			action.clickWithClickableWait(plusBtn, "plus btn");
		}
		clickAddToCarButton();
		action.switchToDefaultContent();
		action.clickLink("CONTINUE SHOPPING");
		action.waitForElementInvisibility(panel_pop_up);
		return new CartPage();
		
	}
	
	
	public ProductDescriptionPage Verify_ViewMore_ViewLess_PDP() {
		
		String description=pdpdescription.getText();
 
		if(description.length()>171)
		{	
			action.logInfo("No: of characters in description greater than 171, View more link available");
		    action.clickWithoutScroll(viewMoreLink, "viewMoreLink");
		    verify.isElementDisplayed(pdpdescription,"pdpdescription");
		    verify.isElementNotDisplayed(viewMoreLink, "View More Link ");
		    verify.isElementDisplayed(viewLessLink,"View Less link");
		    action.clickWithClickableWait(viewLessLink, "viewLessLink");
			verify.isElementDisplayed(viewMoreLink,"View more link");
			verify.isElementNotDisplayed(viewLessLink, "View Less Link ");
		}		
		else
		{
			action.logInfo("No: of characters in description less than 171, no View more link");
		    verify.isElementNotDisplayed(viewMoreLink, "View More Link ");
		    verify.isElementNotDisplayed(viewLessLink, "View Less Link "); 
		}
		return this;
		
	}


	
	

}
