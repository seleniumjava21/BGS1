package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.PageAction;
import utilities.ThreadManager;

public class ProductListPage extends BasePage {
	PageAction actions;

	public ProductListPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		actions = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Search");
		}
	}

	String prdList = "//ul[contains(@class,'product__listing product__list')]//li";
	By productList = By.xpath(prdList);
	String btn = "//button[@value='addtocart']";
	By addToCarBtn = By.xpath(btn);
	String prd = "//a[@class='product__list--name']";
	@FindBy(xpath = "//a[contains(text(),'See Details')]")
	private WebElement seeDetailsBtn;
	@FindBy(xpath = "//p[contains (text(),'Search Results')]")
	private WebElement searchHeader;
	@FindBy(xpath = "//button[@class='addToCartBtn btn btn-default btn-block btn-icon js-add-to-cart addtocart-button js-enable-btn button-primary small-button pull-right stay']")
	private WebElement addToCartBtnPLP;
	By productLink = By.xpath(prd);

	@FindBy(xpath = "//ul[contains(@class,'product__listing product__list')]//li[1]")
	private List<WebElement> productListingItems;
	@FindBy(xpath = "//li[@class='panel panel-default product__list--item']//a[@class='product__list--name']")
	private List<WebElement> productNames;
	@FindBy(xpath = "//button[contains(@class,'addToCartBtn btn btn-default btn-block btn-icon js-add-to-cart addtocart-button js-enable-btn button-primary small-button pull-right stay')]")
	private WebElement btn_addToCart_bgs;

	// Bundles :

	@FindBy(xpath = "//a[contains(text(),'BUY DATABASE')]")
	protected WebElement buyDatabase;

	/*************************
	 * Element Declaration Ends here
	 * 
	 * @return
	 *******************************************/

	@FindBy(xpath = "//select[@id='sortOptions1']")
	private WebElement sortSelectDropdown;

	/*************************
	 * Element Declaration Ends here
	 * 
	 * @return
	 *******************************************/

	public ProductListPage Validate_PLPPage() {
		verify.pageTitle("Search");
		verify.isElementDisplayed(searchHeader, "Search results header");
		return this;
	}

	public ProductListPage Validate_Default_Sort_On_Search_Results_Page() {
		String defaultSelectedSortOption = verify.getFirstSelected(sortSelectDropdown);
		verify.verifyTextMatch("RELEVANCE", defaultSelectedSortOption,
				"Default selected sort option on search result page");

		return this;
	}

	public ProductListPage Validate_Popularity_SortOption_On_Category_PLPPage() {
		String defaultSelectedSortOption = verify.getFirstSelected(sortSelectDropdown);
		verify.verifyTextMatch("POPULARITY", defaultSelectedSortOption, "Default selected sort option on category PLP");
		verify.selectByVisibleText(sortSelectDropdown, "POPULARITY", "popularity option is present");
		return this;
	}

	protected boolean isAddToCartButtonExists() {
		return actions.isElementExistsIgnore(addToCarBtn, "Add to cart btn");

	}

	protected void addtoCartFromPLP() {
		action.clickWithClickableWait(addToCartBtnPLP, "Add to Cart Button");
	}

	protected void clickContinueShopping() {
		action.switchToDefaultContent();
		action.clickLink("CONTINUE SHOPPING");
		action.waitForElementInvisibility(panel_pop_up);
	}

	protected CartPage clickCheckout() {
		action.switchToDefaultContent();
		action.clickLink("CHECK OUT");
		action.waitForElementInvisibility(panel_pop_up);
		return new CartPage();
	}

	protected void clickSeeDetailsButton() {
		action.clickWithClickableWait(seeDetailsBtn, "See Details Btn");
	}

	protected boolean isSeeDetailsButtonExists() {
		return actions.isElementExistsIgnore(seeDetailsBtn, "See Details btn");
	}

	protected ProductDescriptionPage clickFirstProductFromList() {
		String text = actions.getText(productLink, "product link");
		actions.clickWithClickableWait(productLink, text);
		return new ProductDescriptionPage();
	}

	public ProductDescriptionPage GoTo_PDP() {
		ProductDescriptionPage pdp = clickFirstProductFromList();
		pdp.Validate_PDPage();
		return pdp;

	}

	protected ProductDescriptionPage clickProductFromListHavingAddToCartBtn() {
		List<WebElement> pl = actions.findElements(productList, "product block");
		actions.disableImplicitWait();
		int i = 1;
		for (WebElement e : pl) {
			actions.scrollToElement(pl.get(i), "Product block:-" + i);
			By bttn = By.xpath(prdList + "[" + i + "]" + btn);
			By lnk = By.xpath(prdList + "[" + i + "]" + prd);
			WebElement ment = actions.isRelativeElementExistsIgnore(e, bttn, "add to Cart button");
			if (ment != null) {
				WebElement element = actions.findRelativeElement(e, lnk, "product link");
				String text = actions.getText(element, "product link");
				actions.clickWithClickableWait(element, text);
				actions.resetImplicitWait();
				return new ProductDescriptionPage();
			} else {
				i++;
			}
		}
		throw new CustomException("Product with Add to Cart button not found in the product list page");
	}

	public ProductListPage Verify_Pagination_In_PLP() {
		getProductCountPerPage();
		actions.clickLink("2");
		getProductCountPerPage();
		return this;
	}

	private void getProductCountPerPage() {
		int size = productNames.size();
		action.logInfo("Total products found in page  :- " + size);
		if (size == 0) {
			throw new CustomException("Products not found in PLP page");
		}
		for (WebElement e : productNames) {
			action.scrollToElement(e, "Products");
			action.logInfo(actions.getTextWithoutLogging(e));
		}
	}

	// Bundles:
	protected boolean isBuydataBaseButtonExists() {
		return verify.isElementDisplayed(buyDatabase, "Buydatabase button");

	}

	protected void buydatabaseClickFromPLP() {
		action.clickWithClickableWait(buyDatabase, "Buydatabase button");
	}
}
