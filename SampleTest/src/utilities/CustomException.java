package utilities;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CustomException extends RuntimeException {
	

	public  CustomException(String message, Throwable throwable) {
		super(message, throwable);
		PageAction act = new PageAction();
		String currentPageTitle = act.getCurrentPageTitle();
		ThreadManager.dash.get().setComments(message + " :: Page :- "+currentPageTitle);
		ThreadManager.logger.get().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
		ThreadManager.logger.get().fail(throwable);	
		System.out.println(message);
		System.out.println(throwable);
	}
	
	
	public CustomException(String message) {
		PageAction act = new PageAction();
		String currentPageTitle = act.getCurrentPageTitle();
		ThreadManager.dash.get().setComments(message + " :: Page :- "+currentPageTitle);
		ThreadManager.logger.get().fail(MarkupHelper.createLabel(message, ExtentColor.RED));	
		System.out.println(message);
	}
	

}
