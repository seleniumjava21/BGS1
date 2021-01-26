package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstProg {

	public static void main(String[] args) {
		System.out.println("My 1st project check");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");

		driver.get("https://www.google.co.in");
		

		driver.manage().window().maximize();

		WebElement search = driver.findElement(By.xpath("//input[@type='text']"));
		
		System.out.println(search.getAttribute("outerHTML"));

		search.sendKeys("amazon", Keys.ENTER);
		
		
		
		
		//open browser
		//get google page
		// search box
		//amazon

		// String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		// driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);

		// driver.findElement(By.xpath("//body[@class=\"srp tbo
		// vasq\"]")).sendKeys(Keys.CONTROL+"t");

		
		// label[@class='radio-button-container']/preceding::label

		// label[@class='radio-button-container']/preceding::div

		// span[@class='radio-checkmark stay']/preceding-sibling::input

		// input[@id='account_type2']/following-sibling::span-with in class

		// input[@id='account_type2']/following::span---all the followed spans

		// input[@id='account_type2']/ancestor::label

	}
}
