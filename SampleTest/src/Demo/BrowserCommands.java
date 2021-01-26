package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserCommands {
	
	
	public static void main(String[] args) throws Exception {
		
		 System.setProperty("webdriver.gecko.driver",
				 "C:\\Users\\srilekha.shivadevuni\\geckodriver-v0.24.0-win64\\geckodriver.exe"
				 );
		 
		 FirefoxDriver driver = new FirefoxDriver();
		 
		
		
		driver.get("https://www.google.co.in");

		driver.manage().window().maximize();//browser commands
		 
		
	//	driver.navigate();

		WebElement search = driver.findElement(By.xpath("//input[@type='text']"));
		
		
	//	driver.navigate()

		search.sendKeys("amazon", Keys.ENTER);
		//search.sendKeys(KeysToSend);
		
		//selenium
		Thread.sleep(5000);
		
		//search.sendKeys("sri", Keys.);


	String Title= driver.getTitle();//browser  watch or bag

		//String Title = driver.getTitle();

		System.out.print(Title);
		String CurrentUrl = driver.getCurrentUrl();//
		System.out.print(CurrentUrl);
		 
		// String PageSource = driver.getPageSource(); System.out.print(PageSource);
		 
		
		/*
		 * driver.get("https://yahoo.co.in/");
		 * 
		 * Thread.sleep(5000); ArrayList<String> tabs = new ArrayList<String>
		 * (driver.getWindowHandles()); driver.switchTo().window(tabs.get(0));
		 */ 
		 
		 /* prog.keyPress(KeyEvent.VK_CONTROL); prog.keyPress(KeyEvent.VK_T);
		 * prog.keyRelease(KeyEvent.VK_T); prog.keyRelease(KeyEvent.VK_CONTROL);
		 * prog.delay(2000);
		 */
		  

		
		 
		driver.close();//current // selenium cmds
		driver.quit();//destroys instance of driver.
		
		
		


	}

}
