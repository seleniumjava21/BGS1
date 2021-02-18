package pages;

import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.PageAction;
import utilities.ThreadManager;

public class RegisterLoginInformation extends BasePage {
	
	public RegisterLoginInformation() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");
			
		}
	}
	
	@FindBy(id ="j_username") private WebElement emailAddress;
	@FindBy(id ="confirm_j_username") private WebElement confirEmailAddress;
	@FindBy(id ="password") private WebElement password;
	@FindBy(id ="confirm_password") private WebElement confirm_password;
	@FindBy(xpath ="//span[@class='checkmark stay']") private WebElement termsOfUse;
	@FindBy(xpath ="//input[@name='Register']") private WebElement registerBtn;
	@FindBy(xpath ="//button[@id='use_suggested_address_button']") private WebElement registerReplaceAddrBtn;
	
	
	
	
	
	public HomePage Provide_Login_Info(ConcurrentHashMap<String, String> loginInfo) {	
		String email = utils.generateEmail();
		Boolean replaceExists = action.isElementExistsIgnore(registerReplaceAddrBtn, "Register Replace Address Button");
		if (replaceExists) {
			action.clickAndIgnoreFailure(registerReplaceAddrBtn, "Replace Button");
		}
		action.inputText(emailAddress, "email id",email );
		action.inputText(confirEmailAddress, "confirm email id",email );
		ThreadManager.dash.get().setComments("Email id : - "+email);;
		ThreadManager.username.set(email);
		HomePage hp = registerLoginInfo(loginInfo);
		return hp;
		 
	}

	/**
	 * @param loginInfo
	 * @return 
	 */
	private HomePage registerLoginInfo(ConcurrentHashMap<String, String> loginInfo) {
		action.inputText(password, "password", loginInfo.get("Password"));
		action.inputText(confirm_password,"confirm password", loginInfo.get("ConfirmedPassword"));
		//action.scrollToElement(termsOfUse);
		action.clickWithClickableWait(termsOfUse, "terms of use checkbox");
		action.clickWithClickableWait(registerBtn, "register button");
		return new HomePage();
	}
	
	public AddBillingAddress Register_By_Providing_Login_Info_And_Login(ConcurrentHashMap<String, String> loginInfo) {	
		String generatedEmail =utils.generateEmail();
		action.inputText(emailAddress, "email id", generatedEmail);
		action.inputText(confirEmailAddress, "confirm email id",generatedEmail );
		registerLoginInfo(loginInfo);
		LoginPage login = new LoginPage();
		login.login(generatedEmail);
		return new AddBillingAddress();
	}
	

}
