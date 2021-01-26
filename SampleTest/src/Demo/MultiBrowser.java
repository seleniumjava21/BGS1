package Demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultiBrowser {

	public static void main(String[] args) {

		
		 System.setProperty("webdriver.gecko.driver",
		 "C:\\Users\\srilekha.shivadevuni\\geckodriver-v0.24.0-win64\\geckodriver");
		 WebDriver driver = new FirefoxDriver();
		 driver.get("https://google.com");
	}
}

	/*
	 * System.setProperty("webdriver.ie.driver",
	 * "C:\\Users\\srilekha.shivadevuni\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe"
	 * );
	 * 
	 * WebDriver driver = new InternetExplorerDriver();
	 * driver.get("https://www.google.co.in");
	 * 
	 * JavascriptExecutor js = (JavascriptExecutor) driver;
	 * 
	 * WebElement ele = driver.findElement(By.xpath("//img[@id='hplogo']"));
	 * 
	 * System.out.println("font-size = "+ele.getCssValue("font-size"));
	 * System.out.println("background = "+ele.getCssValue("background"));
	 * System.out.println("line-height = "+ele.getCssValue("line-height"));
	 * System.out.println("color = "+ele.getCssValue("color"));
	 * System.out.println("font-family = "+ele.getCssValue("font-family"));
	 * 
	 * // js.executeScript("alert('Hello')");
	 * 
	 * 
	 * 
	 * 
	 * //FirefoxDriver drv = new FirefoxDriver();
	 * 
	 * System.setProperty("webdriver.chrome.driver",
	 * "C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");
	 * 
	 * // WebDriver drv = new ChromeDriver(); //drv.get(
	 * "https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin"
	 * );
	 * 
	 * // drv.manage().window().maximize();
	 * 
	 * // CaptureScreenshot driver = new CaptureScreenshot();
	 * 
	 * 
	 * 
	 * 
	 * drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * 
	 * WebElement username =
	 * drv.findElement(By.xpath("//input[@id='identifierId']"));
	 * 
	 * username.clear(); username.sendKeys("selenium.java21@gmail.com");
	 * 
	 * WebElement next =
	 * drv.findElement(By.xpath("//span[contains(text(),'Next')]")); next.click();
	 * 
	 * WebElement password = drv.findElement(By.xpath("//input[@name='password']"));
	 * 
	 * // password.clear();
	 * 
	 * // Thread.sleep(3000);
	 * 
	 * password.sendKeys("P@$$word1"); //
	 * js.executeScript("document.getElementByClass('whsOnd //
	 * zHQkBf').value='P@$$word1';"); //
	 * js.executeScript("document.getElementByName('password').value='P@$$word1';");
	 * // js.executeScript(
	 * "document.getElementById('Email').value='SoftwareTestingMaterial.com';");
	 * 
	 * WebElement next1 =
	 * drv.findElement(By.xpath("//span[contains(text(),'Next')]"));
	 * 
	 * 
	 * 
	 * System.setProperty("webdriver.gecko.driver",
	 * "C:\\Users\\srilekha.shivadevuni\\geckodriver-v0.24.0-win64\\geckodriver.exe"
	 * ); FirefoxDriver drv = new FirefoxDriver(); FirefoxOptions options = new
	 * FirefoxOptions(); options.setHeadless(true);
	 * 
	 * 
	 * 
	 * drv.get("https://www.google.co.in");
	 * 
	 * 
	 * // System.setProperty("webdriver.gecko.driver","Pathdriver exe"); //
	 * WebDriver driver = new FirefoxDriver(options); //
	 * driver.navigate().to("https://google.com"); //
	 * driver.findElement(By.name("q")).sendKeys("hellodriver.cl
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // RemoteWebDriver obj = new RemoteWebDriver();
	 * 
	 * driver.manage().deleteAllCookies();
	 * 
	 * 
	 * // System.setProperty("webdriver.chrome.driver", //
	 * "C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe"); //
	 * * // * WebDriver d = new ChromeDriver(); //
	 */

	// driver.close(); driver.quit();


