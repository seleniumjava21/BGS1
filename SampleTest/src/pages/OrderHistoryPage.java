package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.ThreadManager;

public class OrderHistoryPage extends BasePage {
	
	public OrderHistoryPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Order History");
			
		}
	}
	
	protected OrderDetailsPage clickOrderNumber(String OrderNumber) {
		
		if(action.isElementExistsIgnore(By.linkText(OrderNumber), OrderNumber)) {
			action.clickWithClickableWait(By.linkText(OrderNumber), OrderNumber);
			return new OrderDetailsPage();
		}else {
			throw new CustomException("This order number does not exist :- "+ OrderNumber);
		}
		
	}

	

}
