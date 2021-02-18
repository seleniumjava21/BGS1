package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.PageAction;
import utilities.ThreadManager;

public class RegisterAccount extends BasePage {
	public RegisterAccount() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");
			
		}
	}
	
	@FindBy(xpath="//label[@class='radio-button-container'][2]") private WebElement noRadioButton;
	@FindBy(xpath="//label[@class='radio-button-container'][1]") private WebElement yesRadioButton;
	@FindBy(xpath ="//button[contains(@class,'next button-primary mobile-full-width')]") private WebElement nextBtn;
	
	
	public RegisterAccountDetails newAccount() {
		action.clickWithClickableWait(noRadioButton, "No radio button");		
		clickNextButton();
		return new RegisterAccountDetails();
	}
	
	public RegisterAccountDetails existingAccount() {
		action.clickWithoutClickableWait(yesRadioButton, "Yes radio button");
		clickNextButton();
		return new RegisterAccountDetails();
	}
	
	private void clickNextButton() {
		action.clickWithClickableWait(nextBtn, "Next button");
	}
	

}
