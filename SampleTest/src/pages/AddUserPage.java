package pages;

import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.Reporting;
import utilities.ThreadManager;
import utilities.Utils;


public class AddUserPage extends BasePage {
	public AddUserPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");
			
		}
	}
	
	String randomString = null;
	String myCompanylink = "My Company";
	String email = null;

	@FindBy(xpath ="//a[@class='add-aircraft-button']") private WebElement addNewAircraft;
	@FindBy(xpath = "(//button[@class='header-opt nav-bg-hover header-opt-btn-dropdown dropdown-toggle'])[2]") private WebElement unAuthLoginRegisterIcon;
	@FindBy(xpath ="//div[@class='mycompanyPageSideContent col-md-3']//a[@title='Users']") private WebElement addnewuser;
	@FindBy(id ="existing_personal_first_name") private WebElement adduserfirstname;
	@FindBy(id ="existing_personal_last_name") private WebElement adduserlastname;
	@FindBy(id ="password") private WebElement adduserpassword;
	@FindBy(id ="updatePwd.checkPwd") private WebElement adduserconfirmpassword;
	@FindBy(id ="existing_personal_email") private WebElement adduseremail;
	@FindBy(xpath ="//input[@id='last_four_digits']") private WebElement adduserextension;
	@FindBy(xpath ="//input[@id='first_three_digits']") private WebElement adduserphone;
	@FindBy(xpath ="//input[@id='area_code']") private WebElement adduserareacode;
	@FindBy(id ="countrydropdown-button") private WebElement addusercountrycode;
	@FindBy(xpath ="//a[@class='button-secondary']") private WebElement restepasswordNewUserbtn;
	@FindBy(xpath ="//button[@class='btn btn-primary']") private WebElement restepasswordSavebtn;
	@FindBy(xpath ="(//div[@class='checkmark stay'])[1]") private WebElement b2badmincheck;
	@FindBy(xpath ="//button[contains(@class,'btn btn-primary')]") private WebElement addusersaveBtn;
	@FindBy(id = "j_username") private WebElement txt_username;
	@FindBy(xpath ="//button[@class='btn btn-primary btn-block login-btn']") private WebElement btn_login;
	@FindBy(id = "j_password") private WebElement txt_password;
	@FindBy(id="notice") private WebElement incorrectCredentialsBanner;
	
	@FindBy(xpath ="//div[@class='alert alert-info alert-dismissable getAccAlert']") private WebElement useraddedmsg;
	private String useradded = "Customer successfully created";
	
	private String generateEmail() {
		Utils utils = new Utils();
		return utils.generateEmail();
	}
	

	
	
	public AddUserPage Add_New_User(ConcurrentHashMap<String, String> addNewUser) {
        email = generateEmail();
        //actions.clickElement(addnewuser, "Add new user");
        action.inputText(adduserfirstname, "Add user first name", addNewUser.get("firstName"));
        action.inputText(adduserlastname, "Add user last name", addNewUser.get("lastName"));
		action.inputText(adduseremail, "email id",email );
		action.clickWithClickableWait(addusercountrycode, "countryCode in Shipping Address");
		action.selectCustomDropDown(addNewUser.get("countryCode"));
		action.inputText(adduserareacode, "Add user area code", addNewUser.get("areaCode"));
		action.inputText(adduserphone, "Add user phone number", addNewUser.get("phoneNumber"));
		action.inputText(adduserextension, "Add user extension number", addNewUser.get("extension"));
		action.clickWithClickableWait(b2badmincheck, "Check b2b administrator");
		action.clickWithClickableWait(addusersaveBtn, "Save button");
		verify.verifyTextMatchIgnoringCase(useradded, useraddedmsg.getText(), "User added message");
		ThreadManager.dash.get().setComments("New User:- "+email);	
		action.clickWithClickableWait(restepasswordNewUserbtn, "reset password for new user created");
		action.inputText(adduserpassword, "New Password", Reporting.getPassword());
		action.inputText(adduserconfirmpassword, "Confirm Password", Reporting.getPassword());
		action.clickWithClickableWait(restepasswordSavebtn, "reset password for new user saved");
		return this;
	}
	
	public AddUserPage Login_With_NewUser_And_Verify_Login() {
		clickAccountIcon();
		action.clickWithoutScroll(logOut, "Logout button");
		action.clickWithoutScroll(unAuthLoginRegisterIcon, "Login Register icon");
		action.clickWithClickableWait(loginLinkheader, "Login");
		action.inputText(txt_username, "username text box", email);
		action.inputText(txt_password, "password text box", Reporting.getPassword());
		action.clickWithClickableWait(btn_login, "Login Button");
		if(verify.pageTitle("Login")) {
			throw new CustomException("Login Failed");
		}else if(verify.isElementDisplayed(link_account_icon_bgs,"account circle")) {
			action.logPass("Login Successfull");
		}else if(verify.isElementDisplayed(incorrectCredentialsBanner, "Incorrect Credential label")){
			throw new CustomException("Login Failed due to Incorrect Credentials");
		}else
			{
			action.getCurrentPageTitle();
			throw new CustomException("Login Failed");
		}
		verify.pageTitle("Search");
		ThreadManager.logger.get().info("New User loggedIn"+email);
		return this;
	}

}
