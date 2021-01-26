package Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @param args
 * @throws Exception
 */

public class getClass {
	public static void main(String[] args) throws Exception {
		int i = 0, j = 0;
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");

		WebDriver d = new ChromeDriver();

		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		d.get("https://www.google.com/");
		d.manage().window().maximize();

		WebElement search = d.findElement(By.xpath("//input[@title='Search']"));
		search.sendKeys("Amazon");
		search.sendKeys(Keys.ENTER);

		System.out.println("success");
		WebElement amazonlink = d.findElement(By.xpath("//div/ol/li/div/a/h3"));
		amazonlink.click();

		System.out.printf(" name of the window", d.getWindowHandle());
		System.out.printf("title of the page", d.getTitle());
		System.out.printf("current url", d.getCurrentUrl());

		WebElement Your_orders = d.findElement(By.id("nav-link-yourAccount"));

		System.out.println("the class of element is " + d.getClass());
		System.out.println("the tag name of element is " + Your_orders.getTagName());
		System.out.println("the text of element is " + Your_orders.getText());
		System.out.println("the css line height value of element is " + Your_orders.getCssValue("line-height")
				+ " and font family " + Your_orders.getCssValue("font-family"));
		System.out.println("the class of the element is " + Your_orders.getAttribute("class"));
		System.out.println("the size of the element is " + Your_orders.getSize());
		Point point = Your_orders.getLocation();
		System.out.println("the coordinates of the element are " + point.x + " and y coordinate " + point.y);

		WebElement sign_in = d
				.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']//span[contains(text(),'Sign in')]"));

		WebDriverWait wait = new WebDriverWait(d, 5);

		Actions action = new Actions(d);
		action.moveToElement(Your_orders).build().perform();

		wait.until(ExpectedConditions.visibilityOf(sign_in));
		action.moveToElement(sign_in).build().perform();
		sign_in.click();

		WebElement email = d.findElement(By.name("email"));
		WebElement continue_b = d.findElement(By.id("continue"));

		email.clear();
		email.sendKeys("8297126060");
		continue_b.click();
		WebElement password = d.findElement(By.xpath("//input[@type='password']"));
		WebElement submit = d.findElement(By.xpath("//input[@id='signInSubmit']"));
		password.sendKeys("Goodluck@19");// enter your password
		submit.click();

		WebElement Categorylink = d.findElement(By.xpath("//span[contains(text(),'Category')]"));
		Thread.sleep(5000);
		
		action.moveToElement(Categorylink).build().perform();
		
		List<WebElement> category = d.findElements(By.xpath("//span[@role='navigation']/span[@class='nav-text']"));
		
		for (j = 0; j < category.size(); j++) {
			System.out.println("the category name are  " + category.get(j).getText());

		}
		
		
		
	
		action.moveToElement(category.get(6)).build().perform();

		WebElement DSLR = d.findElement(By.xpath("//span[contains(text(),'DSLR')]"));
		DSLR.click();

		/*
		 * List<WebElement> productslist = d .findElements(By.xpath(
		 * "//div[@class='a-carousel-viewport']/ol/li/div[1]/a/span"));
		 */
		List<WebElement> productslist = d
				.findElements(By.xpath("//div[@class='crwTitle']/a"));
		
		
		for (i = 0; i < productslist.size(); i++) {
			System.out.println("the product details are " + productslist.get(i).getText());
		}

		List<WebElement> brands = d.findElements(
				By.xpath("//div[@aria-label='Categories and Refinements']/div/ul[4]/div/li/span/span/div/label/input"));

		for (i = 1; i < brands.size(); i++) {
			System.out.println("the brand names are " + brands.get(i).getAttribute("name"));

		}

	}
}