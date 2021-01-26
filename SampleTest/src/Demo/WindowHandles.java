package Demo;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//multiple windows in selenium webdriver using Switch To methods which will allow us to switch 
//control from one window to another window

//downloads will not work here

//to close only specific window look for getTitle of that window.
public class WindowHandles {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\srilekha.shivadevuni\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();

		// Load app
		driver.get("http://www.naukri.com/");

		Thread.sleep(5000);
		// It will return the parent window name as a String
		String parent = driver.getWindowHandle();

		// String p = driver.getwi

		// This will return the number of windows opened by Webdriver and will return
		// Set of Strings
		Set<String> s1 = driver.getWindowHandles();
		
	//	3, 4 windows wndow1, window2, window3-set s1

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();
		

		while (I1.hasNext()) { 

			String child_window = I1.next();

			// Here we will compare if parent window is not equal to child window then
			// we will close

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);

				System.out.println(driver.switchTo().window(child_window).getTitle());

				driver.close();
				
				
			}

		}
		
		System.out.print(driver.switchTo().alert().getText());
		// once all pop up closed now switch to parent window
		driver.switchTo().window(parent);
		
	//	driver.close();
		//driver.quit();
		

	}

}
