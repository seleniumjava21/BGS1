package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Frames extends CaptureScreenshot {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		CaptureScreenshot drv = new CaptureScreenshot();
		String baseUrl = "https://classic.crmpro.com/index.cfm?CFID=343670&CFTOKEN=15920659&jsessionid=8e30489fe5ba1a9379ba17545646167646b5";
		driver.get(baseUrl);
		driver.findElement(By.name("username")).sendKeys("sree557");
		driver.findElement(By.name("password")).sendKeys("Goodluck@19");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		
		int size = driver.findElements(By.tagName("frame")).size();
		System.out.println(size);

		driver.switchTo().frame("mainpanel"); // switching the frame by name

		System.out.println("********We are switch to the iframe*******");
		driver.findElement(By.xpath("//div[@id='navmenu']/ul/li[4]/a[contains(text(),'Contacts')]")).click();
		// Clicks the iframe
		drv.captureScreenShot();
		drv.screenShot();
		System.out.println("*********We are done***************");
		int size1 = driver.findElements(By.tagName("frame")).size();
		System.out.println(size);
		// driver.switchTo().parentFrame();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
	}

}

/*
 * there are two frames one inside other like shown in below image and our
 * requirement is printing the text in the outer frame and inner frame. In the
 * case of nested frames, At first we must switch to the outer frame by either
 * Index or ID of the iframe Once we switch to the outer frame we can find the
 * total number of iframes inside the outer frame, and We can switch to the
 * inner frame by any of the known methods. While exiting out of the frame, we
 * must exit out in the same order as we entered into it from the inner frame
 * first and then outer frame.
 */

/*
 * int size = driver.findElements(By.tagName("iframe")).size();
 * System.out.println("Total Frames --" + size);
 * 
 * // prints the total number of frames driver.switchTo().frame(0); // Switching
 * the Outer Frame System.out.println
 * (driver.findElement(By.xpath("xpath of the outer element ")).getText());
 * 
 * //Printing the text in outer frame size =
 * driver.findElements(By.tagName("iframe")).size(); // prints the total number
 * of frames inside outer frame
 * 
 * System.out.println("Total Frames --" + size); driver.switchTo().frame(0); //
 * Switching to innerframe
 * System.out.println(driver.findElement(By.xpath("xpath of the inner element ")
 * ).getText());
 * 
 * //Printing the text in inner frame
 * 
 *  
 *  driver.switchTo().defaultContent();
 */