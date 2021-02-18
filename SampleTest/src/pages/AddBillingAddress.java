package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.PageAction;
import utilities.ThreadManager;

public class AddBillingAddress extends BasePage {
	public AddBillingAddress() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");
			
		}
	}
	
	
	@FindBy(id ="firstName") private WebElement billingFirstName;
	@FindBy(id ="lastName") private WebElement billingLastName;
	@FindBy(xpath ="//span[@class='select2-selection select2-selection--single']") private WebElement countrydropdown;
	@FindBy(id ="state") private WebElement billingState;
	@FindBy(id ="address_line_one") private WebElement billingAddressLine1;
	@FindBy(id ="address_line_two") private WebElement billingAddressLine2;
	@FindBy(id ="city") private WebElement billingTownCity;
	@FindBy(id ="zipcode") private WebElement billingPostalCode;
	@FindBy(xpath="//button[@class='btn btn-primary save-btn']") private WebElement addBtn;
	private ArrayList<String> countryWithoutStates = new ArrayList<String>(Arrays.asList("Germany","France","New Zealand","Brazil"));
	
	public HomePage Add_Billing_Address(ConcurrentHashMap<String, String> companyInfo) {
		action.inputText(billingFirstName, "first name", companyInfo.get("BillingFname"));
		action.inputText(billingLastName, "last name", companyInfo.get("BillingLname"));
		
		action.scrollToElement(countrydropdown, "Country drop down under billing address");
		action.clickWithClickableWait(countrydropdown, "Country Drop Down");
		action.selectCustomDropDown(companyInfo.get("BillingCountry"));
		if(!countryWithoutStates.contains(companyInfo.get("BillingCountry"))) {
			action.selectByVisibleText(billingState, companyInfo.get("BillingState"), "billing state");}
		action.inputText(billingAddressLine1, "addressLine1", companyInfo.get("BillingAddressline1"));
		action.inputText(billingAddressLine2, "addressLine2", companyInfo.get("BillingAddressline2"));
		action.inputText(billingTownCity, "city", companyInfo.get("BillingCity"));
		action.inputText(billingPostalCode, "zipcode", companyInfo.get("BillingZipcode"));
		action.clickWithClickableWait(addBtn, "Add button");
		return new HomePage();
	}

}
