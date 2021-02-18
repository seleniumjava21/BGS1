package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.ThreadManager;


public class ContactUsPage extends BasePage {
	public ContactUsPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Jeppesen Aviation");
			
		}
	}
	
	
	//Home link in breadcrumb
	private String Home;
	
	@FindBy(xpath ="//h3[contains(text(),'Email Us')]") private WebElement emailfield;
	
	
	public ContactUsPage Validate_ContactUsPage() {
		verify.isElementDisplayed(emailfield, "Email fieldn Contactus page");
		String pageURL=action.getCurentURL();
		String pageURLLC= action.convertToLowerCase(pageURL);
		verify.verifyTextContains("/contactus",pageURLLC, "Verifying Contact us page");
    	return new ContactUsPage();
	}
	
	
}
