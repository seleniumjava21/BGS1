package Demo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsAndFrames extends CaptureScreenshot {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("C:\\Users\\srilekha.shivadevuni\\OneDrive - Accenture\\Desktop\\alert.html");
		driver.manage().window().maximize();

		// clicking on try it button
		WebElement tryIt = driver.findElement(By.xpath("//button[contains(text(),'Try it')]"));
		tryIt.click();

		Thread.sleep(3000);

		// accepting javascript alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		CaptureScreenshot obj = new CaptureScreenshot();
		
				
		
		Thread.sleep(5000);

		// clicking on try it button
		tryIt.click();
		Thread.sleep(3000);
		// rejecting javascript alert
		driver.switchTo().alert().dismiss();
		Thread.sleep(5000);
		// clicking on try it button
		tryIt.click();

		// print test of javascript alert
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		//accept, dismiss, getText

		driver.quit();

	}

}

/*
 * Alert is a small message box which displays on-screen notification to give the user some kind of information or ask
 *  for permission to perform certain kind of operation. It may be also used for warning purpose.


 * 

 * WebDriver offers the users with a very efficient way to handle these pop ups
 * using Alert interface.
 * 
 * There are the four methods that we would be using along with the Alert
 * interface.
 * 
 * 1) void dismiss() – The dismiss() method clicks on the “Cancel” button as
 * soon as the pop up window appears. 2) void accept() – The accept() method
 * clicks on the “Ok” button as soon as the pop up window appears. 
 * 3) String getText() – The getText() method returns the text displayed on the alert box.
 * 4) void sendKeys(String stringToSend) – The sendKeys() method enters the
 * specified string pattern into the alert box.
 */