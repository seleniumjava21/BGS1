package pages;

import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.PageAction;
import utilities.ThreadManager;
import utilities.Utils;

public class RegisterAccountDetails extends BasePage {
	public RegisterAccountDetails() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");
			
		}
	}
	
	@FindBy(xpath="//label[@class='radio-button-container'][1]") private WebElement companyAccountRadioBtn;
	@FindBy(xpath="//label[@class='radio-button-container'][2]") private WebElement personalAccountRadioBtn;
	//comapany info
	@FindBy(id ="existing_company_name") private WebElement companyName;
	@FindBy(id ="existing_company_first_name") private WebElement firstName;
	@FindBy(id ="existing_company_last_name") private WebElement lastName;
	@FindBy(id="existing_company_account_number") private WebElement existingCompanyAccountNumber;
	@FindBy(xpath ="//div[@id='existing_company_account']//span[@class='checkmark stay']") private WebElement existingCompanyCheckBox;
	@FindBy(xpath ="//div[@id='existing_personal_account']//span[@class='checkmark stay']") private WebElement existingPersonalCheckBox;
	
	
	// company Billing Address
	@FindBy(xpath ="//div[@id='new_company_account']//span[@class='select2-selection select2-selection--single']") private WebElement countryDropdown;
	@FindBy(id ="new_company_state") private WebElement selectState;
	@FindBy(id ="company_address") private WebElement addressLine1;
	@FindBy(id ="company_address_two") private WebElement addressLine2;
	@FindBy(id ="company_city") private WebElement city;
	@FindBy(id ="company_zipcode") private WebElement zipcode;
	@FindBy(xpath ="//div[@id='new_company_account']//button[@id='countrydropdown-button']") private WebElement countryCode;
	@FindBy(xpath ="//div[@id='existing_company_account']//button[@id='countrydropdown-button']") private WebElement existingCompanycountryCode;
	@FindBy(xpath ="//div[@id='existing_company_account']//input[@id='area_code']") private WebElement existingAreaCode;
	@FindBy(xpath ="//div[@id='new_company_account']//input[@id='area_code']") private WebElement areaCode;
	@FindBy(xpath ="//div[@id='new_company_account']//input[@id='first_three_digits']") private WebElement phone;
	@FindBy(xpath ="//div[@id='existing_company_account']//input[@id='first_three_digits']") private WebElement existingphone;
	@FindBy(xpath ="//div[@id='new_company_account']//input[@id='last_four_digits']") private WebElement extension;
	@FindBy(xpath ="//div[@id='existing_company_account']//input[@id='last_four_digits']") private WebElement ExsitingExtension;
	@FindBy(xpath ="//div[@id='new_company_account']//button[@class='next button-primary mobile-full-width'][contains(text(),'Next')]") private WebElement nextBtn;
	@FindBy(xpath ="//button[@class='next button-primary mobile-full-width js-existing_company_account']") private WebElement retrieveAccountbtn;
	@FindBy(xpath ="//button[@class='next button-primary mobile-full-width js-existing_personal_account']") private WebElement retrievePersonalAccountbtn;
	

	//personal info
	@FindBy(id ="existing_personal_first_name") private WebElement personalfirstName;
	@FindBy(id ="existing_personal_last_name") private WebElement personallastName;
	@FindBy(id="existing_personal_account_number") private WebElement existingPersonalAccountNumber;
	@FindBy(xpath ="//div[@id='existing_personal_account']//button[@id='countrydropdown-button']") private WebElement existingPersonalCountryCode;
	@FindBy(xpath ="//div[@id='existing_personal_account']//input[@id='area_code']") private WebElement existingPersonalAreaCode;
	@FindBy(xpath ="//div[@id='existing_personal_account']//input[@id='first_three_digits']") private WebElement existingPersonalphone;
	@FindBy(xpath ="//div[@id='existing_personal_account']//input[@id='last_four_digits']") private WebElement exisitingPersonalExtension;
	
	// personal Billing Address
		@FindBy(xpath ="//div[@id='new_personal_account']//span[@class='select2-selection select2-selection--single']") private WebElement perosnalcountryDropdown;
		@FindBy(id ="new_personal_state") private WebElement selectPersonalState;
		@FindBy(id ="personal_address") private WebElement paddressLine1;
		@FindBy(id ="personal_address_two") private WebElement paddressLine2;
		@FindBy(id ="personal_city") private WebElement pcity;
		@FindBy(id ="personal_zipcode") private WebElement pzipcode;
		@FindBy(xpath ="//div[@id='new_personal_account']//button[@id='countrydropdown-button']") private WebElement pcountryCode;
		@FindBy(xpath ="//div[@id='new_personal_account']//input[@id='area_code']") private WebElement pareaCode;
		@FindBy(xpath ="//div[@id='new_personal_account']//input[@id='first_three_digits']") private WebElement pphone;
		@FindBy(xpath ="//div[@id='new_personal_account']//input[@id='last_four_digits']") private WebElement pextension;
		@FindBy(xpath ="//div[@id='new_personal_account']//button[@class='next button-primary mobile-full-width'][contains(text(),'Next')]") private WebElement pnextBtn;
		
		
	
	public RegisterLoginInformation Choose_Company_Account(ConcurrentHashMap<String, String> companyInfo) {
		action.clickWithoutClickableWait(companyAccountRadioBtn, "company account radio button");
		action.inputText(companyName, "company name", companyInfo.get("companyName"));
		action.inputText(firstName, "first name", companyInfo.get("companyFirstname"));
		action.inputText(lastName, "last name", companyInfo.get("companyLastName"));
		
		//action.scrollToElement(countryDropdown);
		
		action.clickWithClickableWait(countryDropdown, "Country Drop Down");
		action.selectCustomDropDown(companyInfo.get("BillingAddressCountry"));
		action.selectByVisibleText(selectState, companyInfo.get("BillingAddressState"), "billing state");
		
		action.inputText(addressLine1, "addressLine1", companyInfo.get("BillingAddresLane1"));
		action.inputText(addressLine2, "addressLine2", companyInfo.get("BillingAddresLane2"));
		action.inputText(city, "city", companyInfo.get("BillingAddresCity"));
		action.inputText(zipcode, "zipcode", companyInfo.get("BillingAddresZipCode"));
		//action.scrollToElement(countryCode);
		action.clickWithClickableWait(countryCode, "countryCode");
		action.selectCustomDropDown(companyInfo.get("ContactInfoCountryCode"));
		action.inputText(areaCode, "areaCode", companyInfo.get("ContactInfoAreaCode"));
		action.inputText(phone, "phone", companyInfo.get("ContactInfoPhoneNumber"));
		action.inputText(extension, "extension", companyInfo.get("ContactInfoExtension"));
		action.clickWithClickableWait(nextBtn, "Next button");
		return new RegisterLoginInformation();
		
	}
	
	
	public RegisterLoginInformation Choose_Existing_Company_Account(ConcurrentHashMap<String, String> companyInfo, AccountType accountType ) {
		Utils utils = new Utils();
		String inputData = utils.getAccountThroughAPI(companyInfo, accountType.toString());
		action.clickWithoutClickableWait(companyAccountRadioBtn, "company account radio button");
		action.inputText(existingCompanyAccountNumber, "Account Number", inputData);
		action.inputText(zipcode, "zipcode", companyInfo.get("companyZIPCode"));
		action.inputText(companyName, "company name", companyInfo.get("CompanyName"));
		action.inputText(firstName, "first name", companyInfo.get("companyFname"));
		action.inputText(lastName, "last name", companyInfo.get("companyLname"));
		
		//action.scrollToElement(existingCompanycountryCode);
		action.clickWithClickableWait(existingCompanycountryCode, "countryCode");
		action.selectCustomDropDown(companyInfo.get("companyCountryCode"));
		action.inputText(existingAreaCode, "areaCode", companyInfo.get("companyAreaCode"));
		action.inputText(existingphone, "phone", companyInfo.get("companyPhoneNumber"));
		action.inputText(ExsitingExtension, "extension", companyInfo.get("companyExtension"));
		//action.scrollToElement(existingCompanyCheckBox);
		action.clickWithClickableWait(existingCompanyCheckBox, "check box");
		action.clickWithClickableWait(retrieveAccountbtn, "Retrieve Account button");
		return new RegisterLoginInformation();
		
	}
	
	public RegisterLoginInformation Choose_Existing_Personal_Account(ConcurrentHashMap<String, String> info, AccountType accountType ) {
		Utils utils = new Utils();
		String inputData = utils.getAccountThroughAPI(info, accountType.toString());
		
		action.clickWithoutClickableWait(personalAccountRadioBtn, "personal account radio button");
		
		action.inputText(existingPersonalAccountNumber, "Account Number", inputData);
		action.inputText(pzipcode, "zipcode", info.get("personalZIPCode"));
		action.inputText(personalfirstName, "first name", info.get("personalFname"));
		action.inputText(personallastName, "last name", info.get("personalLname"));
		
		//action.scrollToElement(existingPersonalCountryCode);
		action.clickWithClickableWait(existingPersonalCountryCode, "countryCode");
		action.selectCustomDropDown(info.get("personalCountryCode"));
		action.inputText(existingPersonalAreaCode, "areaCode", info.get("personalAreaCode"));
		action.inputText(existingPersonalphone, "phone", info.get("personalPhoneNumber"));
		action.inputText(exisitingPersonalExtension, "extension", info.get("personalExtension"));
		//action.scrollToElement(existingPersonalCheckBox);
		action.clickWithClickableWait(existingPersonalCheckBox, "check box");
		action.clickWithClickableWait(retrievePersonalAccountbtn, "Retrieve Account button");
		return new RegisterLoginInformation();
		
	}
	
	
	
	public RegisterLoginInformation Register_Personal_Account(ConcurrentHashMap<String, String> personalInfo) {
		action.clickWithoutClickableWait(personalAccountRadioBtn, "personal account radio button");
		action.inputText(personalfirstName, "personalfirstName", personalInfo.get("personalFirstname"));
		action.inputText(personallastName, "personallastName", personalInfo.get("personalLastName"));
		
		//action.scrollToElement(perosnalcountryDropdown);
		action.clickWithClickableWait(perosnalcountryDropdown, "Country Drop Down");
		action.selectCustomDropDown(personalInfo.get("BillingAddressCountry"));
		action.selectByVisibleText(selectPersonalState, personalInfo.get("BillingAddressState"), "billing state");
		
		action.inputText(paddressLine1, "addressLine1", personalInfo.get("BillingAddresLane1"));
		action.inputText(paddressLine2, "addressLine2", personalInfo.get("BillingAddresLane2"));
		action.inputText(pcity, "city", personalInfo.get("BillingAddresCity"));
		action.inputText(pzipcode, "zipcode", personalInfo.get("BillingAddresZipCode"));
		//action.scrollToElement(pcountryCode);
		action.clickWithClickableWait(pcountryCode, "countryCode");
		action.selectCustomDropDown(personalInfo.get("ContactInfoCountryCode"));
		action.inputText(pareaCode, "areaCode", personalInfo.get("ContactInfoAreaCode"));
		action.inputText(pphone, "phone", personalInfo.get("ContactInfoPhoneNumber"));
		action.inputText(pextension, "extension", personalInfo.get("ContactInfoExtension"));
		action.clickWithClickableWait(pnextBtn, "Next button");
		return new RegisterLoginInformation();
		
	}
	
	
	
	
	
	
	

}
