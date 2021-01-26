package Demo;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoIt extends CaptureScreenshot {

	public static void main(String[] args) throws NoSuchElementException, Exception {

		
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");

		WebDriver drv = new ChromeDriver();
		drv.get("https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");


		drv.manage().window().maximize();

		CaptureScreenshot driver = new CaptureScreenshot();

		

		drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
		WebElement username = drv.findElement(By.xpath("//input[@id='identifierId']"));

		username.clear();
		username.sendKeys("selenium.java21@gmail.com");

		WebElement next = drv.findElement(By.xpath("//span[contains(text(),'Next')]"));
		next.click();

		WebElement password = drv.findElement(By.xpath("//input[@name='password']"));

		password.clear();

		Thread.sleep(3000);

		password.sendKeys("P@$$word1");

		WebElement next1 = drv.findElement(By.xpath("//span[contains(text(),'Next')]"));

		next1.click();

		// explicit wait - to wait for the compose button to be click-able

		WebDriverWait wait = new WebDriverWait(drv, 30);

		
		drv.findElement(By.xpath("//a[@href='https://www.google.co.in/intl/en/about/products?tab=kh']")).click();
		Thread.sleep(2000);
		driver.captureScreenShot();
		
		String parent = drv.getWindowHandle();
		drv.findElement(By.xpath("//span[contains(text(),'Gmail')]")).click();

		Set<String> l1 = drv.getWindowHandles();
		Iterator<String> I1 = l1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				drv.switchTo().window(child_window);
				Thread.sleep(2000);
				driver.screenShot();

				
				drv.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
				Thread.sleep(5000);
				drv.findElement(By.xpath("(//div[contains(text(),'Send')])[2]/following::td[3]/div/div[1]")).click();
				Thread.sleep(5000);
				Runtime.getRuntime().exec("C:\\AutoIt\\demo.exe");
				
			

				Thread.sleep(5000);
				System.out.print("file upload complete");

				driver.captureScreenShot();
				drv.switchTo().window(child_window).close();
				drv.switchTo().defaultContent().close();
				drv.quit();

			}
		}

		// https://autoitscript.com/site/autoit/downloads/

//Runtime.getRuntime().exec("G:/Tutorial/AutoItScripts/upload.exe");

		/*
		 * @AfterMethod public void tearDown(ITestResult result) {
		 * 
		 * if(ITestResult.FAILURE==result.getStatus()) { try {
		 *  TakesScreenshot
		 * ts=(TakesScreenshot)driver; File source=ts.getScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(source, new
		 * File("./Screenshots/"+result.getName()+".png"));
		 * 
		 * System.out.println("Screenshot taken"); } catch (Exception e) {
		 * 
		 * System.out.println("Exception while taking screenshot "+e.getMessage()); }
		 */

	}

}
