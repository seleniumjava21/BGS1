package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import pages.BasePage.Login;
import utilities.CustomException;
import utilities.ThreadManager;
import utilities.Utils;

public class AccountDetails extends BasePage {
	public AccountDetails() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");

		}
	}

	public static String changedEmailID = null;
	
	@FindBy(id = "accountNumber")
	private WebElement accountNumber;
	@FindBy(how = How.XPATH, using = "(//a[@title=\"Account Details\"])[1]")
	private WebElement myAccount;
	@FindBy(how = How.XPATH, using = "(//a[@title='Change Email / User ID'])[2]")
	private WebElement changeEmail;
	@FindBy(how = How.XPATH, using = "//input[@id='profile.newEmail']")
	private WebElement newEmail;
	@FindBy(how = How.XPATH, using = "//input[@id='profile.confirmEmail']")
	private WebElement confirmEmail;
	@FindBy(how = How.XPATH, using = "//input[@id='profile.pwd']")
	private WebElement password;
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
	private WebElement saveButton;
	@FindBy(how = How.XPATH, using = "//span[@id='confirmEmail.errors']")
	private WebElement confirmEmailError;
	@FindBy(how = How.XPATH, using = "//div[@id='notice']")
	private WebElement globalError;
	@FindBy(how = How.XPATH, using = "//span[@id='newEmail.errors']")
	private WebElement newEmailError;
	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-info alert-dismissable getAccAlert']")
	private WebElement alertmessage;

	public AccountDetails Get_Acccount_Number() {
		String act = action.getAttribute(accountNumber, "value", "Account number");
		String email = ThreadManager.dash.get().getComments();
		ThreadManager.dash.get().setComments(email+"<br>Account Number : " + act);
		return this;
	}

	 //This method requires rework.Not to be referred
	public AccountDetails Change_EmailValidation() {
		String changeEmail_User = action.getCurrentUsername();
		String changeEmail_jus = getUsername(Login.jus);
		String newemailID = null;
		String tempEmailID = null;
		try {
		
			// Missing input
			action.clickAndIgnoreFailure(changeEmail, "Change Email");
			action.inputText(newEmail, "new email id", changeEmail_User);
			action.clickAndIgnoreFailure(newEmail, "new email");
			action.inputText(newEmail, "new email id", changeEmail_User);
			action.clickAndIgnoreFailure(confirmEmail, "confirm Email");
			action.inputText(confirmEmail, "confirm Email", " ");						
			// clears confirm email
			action.clickAndIgnoreFailure(password, "password");
			action.inputText(password, "password", Utils.getPropertyValue("password"));
			action.clickWithClickableWait(saveButton, "save");

			if ((action.isElementExistsIgnore(confirmEmailError)) && (action.isElementExistsIgnore(globalError))) {
				ThreadManager.logger.get().info("Missing Confirm email");
				// clears new email and gives confirm email
				action.inputText(newEmail, "new email id", " ");				
				action.inputText(confirmEmail, "confirm Email", changeEmail_jus);
				action.clickAndIgnoreFailure(password, "password");
				action.inputText(password, "new password", Utils.getPropertyValue("password"));
				action.clickWithClickableWait(saveButton, "save");
				if ((action.isElementExistsIgnore(newEmailError)) && (action.isElementExistsIgnore(globalError))) {
					ThreadManager.logger.get().info("Missing New email text");
				}
			}
			// clears confirm email and new email
			action.inputText(confirmEmail, "confirm Email", " ");		
			action.inputText(password, "new password", Utils.getPropertyValue("password"));
			action.clickWithClickableWait(saveButton, "save");
			if ((action.isElementExistsIgnore(confirmEmailError)) && (action.isElementExistsIgnore(globalError))) {

				ThreadManager.logger.get().info("Missing Confirm email and new email");
				// Invalid data
				action.inputText(newEmail, "new email id", "change");
				action.inputText(confirmEmail, "confirm Email", changeEmail_jus);
				action.inputText(password, "new password",Utils.getPropertyValue("password"));
				action.clickWithClickableWait(saveButton, "save");
				if ((action.isElementExistsIgnore(newEmailError)) && (action.isElementExistsIgnore(globalError))) {

					ThreadManager.logger.get().info("invalid new email ID");
					action.inputText(newEmail, "new email id", changeEmail_jus);
					action.inputText(confirmEmail, "confirm Email", "change");
					action.inputText(password, "new password",Utils.getPropertyValue("password"));
					action.clickWithClickableWait(saveButton, "save");
					if ((action.isElementExistsIgnore(confirmEmailError))
							&& (action.isElementExistsIgnore(globalError))) {
						ThreadManager.logger.get().info("invalid confirm email ID");
						// Valid data
						String timeStamp = new SimpleDateFormat("ss").format(new Date()).replace(".", "")
								.replaceAll("\\s", "");
						// tempEmailID = userName.replaceAll("[0-9]", "");
						tempEmailID = "BGS";
						newemailID = tempEmailID + timeStamp;
						changedEmailID = newemailID + "@bgs.com";
						action.inputText(newEmail, "new email id", changedEmailID);
						action.inputText(confirmEmail, "confirm Email", changedEmailID);
						action.inputText(password, "new password", Utils.getPropertyValue("password"));
						action.clickWithClickableWait(saveButton, "save");
						if (action.isElementExistsIgnore(alertmessage)) {
							ThreadManager.logger.get().info("Successfully updated the email ID");
						}
					}

				}
			}

		} catch (Exception e) {
			throw new CustomException("Unable to read email from text document\"", e);			
		}
		return this;
	}
	


}
