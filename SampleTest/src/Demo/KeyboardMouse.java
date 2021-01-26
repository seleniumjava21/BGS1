package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class KeyboardMouse {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");
		// WebDriver driver =new ChromeDriver();
		WebDriver driver;
		/*
		 * ChromeOptions options = new ChromeOptions();
		 * 
		 * //options. addArguments("--disable-extensions");
		 * 
		 * options.addArguments("Open-maximized"); driver= new
		 * ChromeDriver(ChromeOptions);
		 */
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);

		Actions
		driver.get("https://www.google.com");

		// driver.manage().window().maximize();
		
		try {
			java.util.List<WebElement> li = driver.findElements(By.xpath("//input[@id='fakeox-input']"));
			
			for (WebElement webElement : li) {
				
				webElement.click();
				
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			e.getMessage();
			System.out.print(e);
			
		}
		
		
		driver.close();

		// WebElement cameras =
		// driver.findElement(By.xpath("(//span[contains(text(),'Cameras')])[2]"));
		/*
		 * WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		 * search.clear();
		 * 
		 * Actions action = new Actions(driver); org.openqa.selenium.Point pt =
		 * driver.findElement(By.id("twotabsearchtextbox")).getLocation();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * 
		 * action.moveToElement(category).build().perform(); Thread.sleep(3000);
		 * action.moveToElement(subCategory).build().perform();
		 * 
		 * Thread.sleep(5000);
		 * 
		 * subCategory.click();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * 
		 * // action.moveToElement(cameras).build().perform();
		 * 
		 * 
		 * String baseUrl = "https://www.amazon.in/"; driver.get(baseUrl); WebElement
		 * category =
		 * driver.findElement(By.xpath("//span[contains(text(),'Category')]"));
		 * WebElement subCategory = driver.findElement(By.
		 * xpath("//span[contains(text(),'TV, Appliances, Electronics')]")); WebElement
		 * cameras =
		 * driver.findElement(By.xpath("(//span[contains(text(),'Cameras')])[2]"));
		 * WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		 * 
		 * // Actions action = new Actions(driver);
		 * 
		 * 
		 * String baseUrl = "https://www.flipkart.com/"; driver.get(baseUrl); WebElement
		 * category = driver.findElement(By.
		 * xpath("//span[@class='_1QZ6fC _3Lgyp8' and text()='Electronics']"));
		 * WebElement subCategory =
		 * driver.findElement(By.xpath("//a[@title='Speakers']"));
		 * 
		 * 
		 * Action ex = action.keyDown(search, Keys.SHIFT).sendKeys(search,
		 * "test").keyUp(search, Keys.SHIFT).build();
		 * 
		 * action.keyDown(search, Keys.CONTROL).S
		 * 
		 * ex.perform();
		 * 
		 * 
		 * 
		 * Thread.sleep(3000);
		 * 
		 * Action act = action.keyDown(search, Keys.SHIFT).sendKeys(search,
		 * "watches").keyUp(search, Keys.SHIFT) .sendKeys(Keys.ENTER).build();
		 * 
		 * keys.al
		 * 
		 * act.perform();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * System.out.print("success");
		 * 
		 * driver.navigate().to("");
		 * 
		 * 
		 * Thread.sleep(3000);
		 * 
		 * // action.contextClick();
		 * 
		 * // action.moveToElement(category).moveToElement(subCategory).click().build();
		 * 
		 * 
		 * moveToElement(WebElement)-- Mouse Hover
		 * 
		 * contextClick()-- Right click on page
		 * 
		 * contextClick(WebElement)-- Right click on specific Element
		 * 
		 * sendKeys(KEYS.TAB)--For keyboard events
		 * 
		 * clickAndHold(WebElement)--Click on element and hold until next operation
		 * 
		 * release() Release the current control
		 * 
		 * 
		 * 
		 * 
		 * driver.close();
		 * 
		 * driver.quit();
		 * 
		 * 
		 * 
		 */
		/*
		 * driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		 * 
		 * Thread.sleep(3000);
		 * 
		 * Actions action = new Actions(driver);
		 * 
		 * WebElement draggable = driver.findElement(By.id("draggable"));
		 * 
		 * WebElement droppable = driver.findElement(By.id("droppable"));
		 * 
		 * Thread.sleep(3000);
		 * 
		 * action.dragAndDrop(draggable, droppable).build().perform();
		 * action.dragAndDropBy(draggable, 150, 200).build().perform();
		 * 
		 * // action.moveToElement(draggable, xOffset, yOffset)
		 * 
		 * action.clickAndHold(draggable).pause(2000).moveToElement(droppable).release()
		 * .build().perform();
		 */	 	
	}

}
