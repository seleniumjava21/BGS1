package Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

//import org.openqa.selenium.support.ui.ExpectedConditions;

//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImplicitAndExplicit {

	public static void main(String[] args) throws NoSuchElementException, Exception {

		/*
		 * System.setProperty("webdriver.gecko.driver",
		 * "C:\\Users\\srilekha.shivadevuni\\geckodriver-v0.24.0-win64\\geckodriver.exe"
		 * ); FirefoxDriver drv = new FirefoxDriver();
		 */
		// drv.get("https://www.google.co.in");

		
		  System.setProperty("webdriver.chrome.driver",
		  "C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");
		  
		  WebDriver drv = new ChromeDriver();
		  drv.get("https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");

		drv.manage().window().maximize();
		

		// Implicit

		drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement username = drv.findElement(By.xpath("//input[@id='identifierId']"));

		username.clear();
		username.sendKeys("selenium.java21@gmail.com");

		WebElement next = drv.findElement(By.xpath("//span[contains(text(),'Next')]"));
		next.click();

		WebElement password = drv.findElement(By.xpath("//input[@name='password']"));

		password.clear();

		password.sendKeys("P@$$word1");

		WebElement next1 = drv.findElement(By.xpath("//span[contains(text(),'Next')]"));

		next1.click();

		// explicit wait - to wait for the compose button to be click-able

		WebDriverWait wait = new WebDriverWait(drv, 30);

		drv.findElement(By.xpath("(//a[@href=\"https://www.google.co.in/intl/en/about/products?tab=kh\"])[1]")).click();
		drv.findElement(By.xpath("//span[contains(text(),'Gmail')]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Compose')]")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(locator)
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("")));

		WebElement compose = drv.findElement(By.xpath("//div[contains(text(),'Compose')]"));
		
		Select ele = new Select(drv.findElement(By.id("")));
		
		if(ele.isMultiple()) {
			ele.selectByIndex(1);
			ele.selectByIndex(2);
			ele.selectByIndex(3);
			
		}

		if (compose.isDisplayed()) {
			compose.click();
		}
		drv.quit();

		// wait.until(ExpectedConditions.elementToBeClickable(""));
		// wait.until(ExpectedConditions.textToBePresentInElement("//div[contains(text(),'COMPOSE')]",
		// "compose")));

		/*
		 * Wait<WebDriver> gWait = new
		 * FluentWait<WebDriver>(drv).withTimeout(Duration.ofSeconds(100))
		 * .pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		 */
		// click on the compose button as soon as the "compose" button is visible

	}

}
/*
 * test script execution time as each of the commands would be ceased to wait
 * for a stipulated amount of time before resuming the execution. The implicit
 * wait mandates to pass two values as parameters. The first argument indicates
 * the time in the numeric digits that the system needs to wait. import
 * java.util.concurrent.TimeUnit – To be able to access and apply implicit wait
 * in our test scripts, we are bound to import this package into our test
 * script.
 * 
 * The second argument indicates the time measurement scale.
 */
/*
 * WebDriver introduces Explicit waits where we can explicitly apply waits
 * whenever the situation arises instead of forcefully waiting while executing
 * each of the test steps. WebDriver introduces classes like WebDriverWait and
 * ExpectedConditions to enforce Explicit waits into the test scripts.
 * 
 * 
 * WebDriver-I, implemented -> WebDriverWait-Class
 * -------------implemented->ExpectedCondtions-class
 * 
 * class imple inter
 * 
 * webDriverwait(C) implement WebDiver ExpetedConditons implements WebDriver
 * 
 * 
 * 
 * 
 * 
 * 
 */
