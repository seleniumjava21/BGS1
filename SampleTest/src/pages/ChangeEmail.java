package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.Reporting;
import utilities.ThreadManager;
import utilities.Utils;


public class ChangeEmail extends BasePage {
	public ChangeEmail() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Update Email");

		}
	}

	String email_changed = null;
	public String changedEmailID = null;
	public String revertedEmailID = null;
	@FindBy(id = "j_username") private WebElement txt_username;
	@FindBy(xpath ="//button[@class='btn btn-primary btn-block login-btn']") private WebElement btn_login;
	@FindBy(id = "j_password") private WebElement txt_password;
	@FindBy(xpath = "(//a[@title='Change Email / User ID'])[3]")
	private WebElement changeEmail;

	@FindBy(xpath = "//input[@id='profile.newEmail']")
	private WebElement newEmail;

	@FindBy(xpath = "//input[@id='profile.confirmEmail']")
	private WebElement confirmEmail;

	@FindBy(xpath = "//input[@id='profile.pwd']")
	private WebElement password;

	@FindBy(xpath =  "(//button[@type='submit'])[2]")
	private WebElement saveButton;	

	@FindBy(xpath = "//input[@id='profile.email']")
	private WebElement currentEmail;

	@FindBy(xpath = "//div[@id='notice']")
	private WebElement errorMsg;	

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-info alert-dismissable getAccAlert']")
	private WebElement alertmessage;

	public ChangeEmail ChangeEmail_From_Myaccount(String emailChangedMsg)  {
		email_changed = utils.generateEmail();
		
			action.inputText(newEmail, "newEmail",email_changed);
			action.inputText(confirmEmail,"confirmnewemail",email_changed);
			action.inputText(password, "password",Reporting.getPassword());
			action.clickWithClickableWait(saveButton, "Save");
			if(verify.verifyTextContains(emailChangedMsg, alertmessage.getText(), "EmailID changed Successfully."))
	        {
	        	action.logPass("Successfully updated the email ID to  " + email_changed);
	        }
	        else
	        {
	              throw new CustomException("Failed to change email ID to random Email ID");
	        }

		return this;
	}
	
	public ChangeEmail ChangeEmail_For_Tripkit(String emailChangedMsg)  {
		changedEmailID = utils.Generate_Random_Email();
		action.inputText(newEmail, "new email id", changedEmailID);
		action.inputText(confirmEmail, "confirm Email", changedEmailID);
		action.inputText(password, "new password", Utils.getPropertyValue("password"));
		action.clickWithClickableWait(saveButton, "save");
		action.waitForElementDisplayed(alertmessage, "Email Change Success");
        if(verify.verifyTextContains(emailChangedMsg, alertmessage.getText(), "Tripkit original Email changed Successfully."))
        {
        	action.logPass("Successfully updated the email ID to random Email ID " + changedEmailID);
        }
        else
        {
              throw new CustomException("Failed to change Tripkit email ID to random Email ID");
        }
		return this;
	}

	public HomePage Verify_Tripkit_Changed_Email() {
		action.inputText(txt_username, "username text box", changedEmailID);
		action.inputText(txt_password, "password text box", Reporting.getPassword());
		action.clickWithClickableWait(btn_login, "Login Button");
		verify.pageTitle("Homepage");
		clickAccountIcon();
		action.clickLink("Account Details");		
		action.clickLink("Change Email / User ID");
		action.waitForElementVisibility(currentEmail, "Currentemail");
		String Strtext = action.getAttribute(currentEmail, "value", "Currentemail");

		if (Strtext.equalsIgnoreCase(changedEmailID))
		{
			action.logPass("Trikit changed email is displayed");			
		}
		else
		{				
			action.logFailure("Trikit changed email is not displayed");	
			throw new CustomException("Trikit Changed email is not available in MyAccount");	
		}
		return new HomePage();
	}	

	public ChangeEmail ChangeEmail_For_Tripkit(String resetEmail, String EmailChangedMsg)  {
		action.inputText(newEmail, "new email id", resetEmail);
		action.inputText(confirmEmail, "confirm Email", resetEmail);
		action.inputText(password, "new password", Utils.getPropertyValue("password"));
		action.clickWithClickableWait(saveButton, "save");
		if (verify.verifyTextContains(EmailChangedMsg, alertmessage.getText(), "Tripkit original Email changed Successfully.")) 
		{
			action.logPass("Successfully updated the email ID to random Email ID" + resetEmail);
		}
		else
		{
			throw new CustomException("Failed to change Tripkit email ID to random Email ID\"");			
		}
		return this;
	}
	
	public HomePage Verify_Tripkit_Changed_Email(String revertedEmailID) {
		action.inputText(txt_username, "username text box", revertedEmailID);
		action.inputText(txt_password, "password text box", Reporting.getPassword());
		action.clickWithClickableWait(btn_login, "Login Button");
		verify.pageTitle("Homepage");
		clickAccountIcon();
		action.clickLink("Account Details");		
		action.clickLink("Change Email / User ID");
		action.waitForElementVisibility(currentEmail, "Currentemail");
		String Strtext = action.getAttribute(currentEmail, "value", "Currentemail");

		if (Strtext.equalsIgnoreCase(revertedEmailID))
		{
			action.logPass("Successfully reverted to original Tripkit email.");			
		}
		else
		{
			action.logFailure("Trikit changed email is not displayed");	
			throw new CustomException("Trikit reverted email is not available in MyAccount");
		}
		return new HomePage();
	}
	
	public HomePage Login_And_Verify_ChangedEmail_Displayed() {
		action.inputText(txt_username, "username text box", email_changed);
		action.inputText(txt_password, "password text box", Reporting.getPassword());
		action.clickWithClickableWait(btn_login, "Login Button");
		verify.pageTitle("Homepage");
		clickAccountIcon();
		action.clickLink("Account Details");		
		action.clickLink("Change Email / User ID");
		action.waitForElementVisibility(currentEmail, "Currentemail");
		String Strtext = action.getAttribute(currentEmail, "value", "Currentemail");

		if (Strtext.equalsIgnoreCase(email_changed))
		{
			action.logPass("Successfully Changed Email");			
		}
		else
		{
			action.logFailure("Email ID was not changed");
		}
		return new HomePage();
	}	

	public ChangeEmail verify_Error_ChangeEmail_from_Myaccount()  {		
		action.waitForElementVisibility(currentEmail, "Currentemail");
		String currentEmailText = action.getAttribute(currentEmail, "value", "Currentemail");
		action.inputText(newEmail, "newEmail",currentEmailText);
	    action.inputText(confirmEmail,"confirmnewemail",currentEmailText);
		action.inputText(password, "password",Reporting.getPassword());
		action.clickWithClickableWait(saveButton, "Save");			
		return this;
	}


	public ChangeEmail verify_error_msg_Displayed()  {
					
			if (action.isElementExistsIgnore(errorMsg)) {
				if (action.getTextWithoutLogging(errorMsg).contains("error")) {
					ThreadManager.logger.get().info("Email is already tied to an existing account" + action.getTextWithoutLogging(errorMsg));

				}
			}
	
		
		return this;
	}

}
