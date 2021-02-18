package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.Reporting;
import utilities.ThreadManager;

public class LoginPage extends BasePage {
	public LoginPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Login");
		}
	}

	
	@FindBy(id = "j_username") private WebElement txt_username;
	@FindBy(xpath ="//button[@class='btn btn-primary btn-block login-btn']") private WebElement btn_login;
	@FindBy(id = "j_password") private WebElement txt_password;
	@FindBy(id="notice") private WebElement incorrectCredentialsBanner;
	@FindBy(id="forgottenPwd.email") private WebElement enterpasswdforgot;
	@FindBy(xpath="//button[@class='btn btn-primary btn-block']") private WebElement resetpasswd;
	@FindBy(xpath="//button[@class='btn button-primary btn-block closeColorBox']") private WebElement closebtn;
	@FindBy(xpath="//span[@class='headline-text']") private WebElement termsheadertxt;
	@FindBy(xpath="//h2[contains(text(),'TERMS OF USE')]") private WebElement termstitletxt;
	@FindBy(id="cboxClose") private WebElement termsclosebtn;
	
	String registerLnk = "REGISTER";
	String forgotpasswdlink = "FORGOT YOUR PASSWORD?";
	String termsofuselink = "Terms of Use";
	
	/**************************************************************************************************************************************/
	public HomePage Login_With_Credentials_And_Verify_Login(Login username) {
		login(getUsername(username));
		verifyLogin();
		return new HomePage();
	}
	
	public void Validate_ForgotPassword(String forgotpasswd) {
		action.clickLinkWithoutScroll(forgotpasswdlink);
		action.switchToDefaultContent();
		action.waitForElementVisibility(enterpasswdforgot,"Passwrd reset email");
		action.inputText(enterpasswdforgot, "Passwrd reset email", forgotpasswd);
		action.clickWithClickableWait(resetpasswd, "Reset password");
		action.switchToDefaultContent();
		action.waitForElementVisibility(closebtn,"Click on Terms Close button");
		action.clickWithClickableWait(closebtn, "Click on Terms Close button");
	
	}
	
	public void Validate_TermsofUse() {
		action.clickLink(termsofuselink);
		verify.verifyTextFromElementIgnoringCase(termsheadertxt, "Terms And Conditions", "Terms header text");
		verify.verifyTextFromElementIgnoringCase(termstitletxt, "TERMS OF USE", "Terms title text");
		verify.isElementDisplayed(termsclosebtn, "Terms Close button");
		action.clickWithClickableWait(termsclosebtn, "Click on Terms Close button");
	
	}
	
	public BuyDatabasePage Login_With_Credentials_And_Verify_BuyDB(Login username) {
		login(getUsername(username));
		verifyLogin();
		return new BuyDatabasePage();
	}
	
	public ContactUsPage Login_With_Credentials_And_GotTo_ContactUs(Login username) {
		login(getUsername(username));
		verifyLogin();
		return new ContactUsPage();
	}
	
	public BuyDatabasePage Login_Back_And_Verify_BuyDB() {
		login(action.getCurrentUsername());
		verifyLogin();
		return new BuyDatabasePage();
	}
	
	public HomePage Login_Back_And_Verify_Home() {
		 login(action.getCurrentUsername());
		 verifyLogin();
		 HomePage hop = new HomePage();
		 hop.Validate_HomePage();
		 return hop;
	}
	

	public CartPage Login_Back_And_Verify_Cart() {
		 login(action.getCurrentUsername());
		 verifyLogin();
		 CartPage ctp = new CartPage();
		 ctp.Validate_CartPage();
		 return ctp;
	}
	
	public PayInvoiceDetailsPage Login_Verify_InvoicePage(Login username) {
		login(getUsername(username));
		verifyLogin();
		 PayInvoiceDetailsPage payinv = new PayInvoiceDetailsPage();
		 return payinv;
	}
	
	public CheckOutPage Login_Back_And_Verify_CheckOut() {
		 login(action.getCurrentUsername());
		 verifyLogin();
		 CheckOutPage cop = new CheckOutPage();
		 cop.Validate_CheckoutPage();
		 return cop;
	}
	

	private void verifyLogin() {
		action.clickWithClickableWait(link_account_icon_bgs,"account circle");
		if(action.isElementExistsIgnore(logOut, "Logout button")) {
			action.logPass("Login Successfull");
		}else if(action.isElementExistsIgnore(incorrectCredentialsBanner, "Incorrect Credential label")){
			throw new CustomException("Login Failed due to Incorrect Credentials");
		}else
			{
			action.getCurrentPageTitle();
			throw new CustomException("Login Failed");
		}
		action.clickWithClickableWait(link_account_icon_bgs,"account circle");
	}
	
	public ProductListPage Login_With_Credentials_And_Verify_PLP(Login username) {	
		login(getUsername(username));
		verifyLogin();
		verify.pageTitle("Search");
		 ProductListPage plp = new ProductListPage();
		 plp.Validate_PLPPage();
		 return plp;
	}

	
	public ProductDescriptionPage Login_Again_And_Verify_PDP() {
		login(action.getCurrentUsername());
		verifyLogin();
		ProductDescriptionPage pdp = new ProductDescriptionPage();
		pdp.Validate_PDPage();
		return pdp;
	}
	
	
	/**
	 * @param username
	 * @return 
	 */
	protected void login(String username) {
		action.inputText(txt_username, "username text box", username);
			action.inputText(txt_password, "password text box", Reporting.getPassword());
			action.clickWithClickableWait(btn_login, "Login Button");
	}
	
	
	
	private void clickRegister() {
		action.clickLink(registerLnk);
	}
	
	
	public RegisterAccountDetails Register_New_Account() {
		clickRegister();
		RegisterAccount registerAccount = new RegisterAccount();
		return registerAccount.newAccount();
	}
	
	public RegisterAccountDetails Register_Existing_Account() {
		clickRegister();
		RegisterAccount registerAccount = new RegisterAccount();
		return registerAccount.existingAccount();
	}
	
	
	

}
