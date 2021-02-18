package utilities;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.aventstack.extentreports.ExtentTest;




public class ThreadManager
{
	private ThreadManager() {}

	private static ThreadManager instance = new ThreadManager();

	public static ThreadManager getInstance()
	{
		return instance;
	}

	
	public static ThreadLocal<ExtentTest> testLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<Summary> dash = new ThreadLocal<Summary>();
	public static ThreadLocal<String> username = new ThreadLocal<String>();
	public static ThreadLocal<Placeholder> placeholder = new ThreadLocal<Placeholder>() {
		@Override
		protected Placeholder initialValue()
		{
			return new Placeholder();
		}
	};
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() 
	{
		@Override
		protected WebDriver initialValue()
		{
			if(System.getProperty("browser").equalsIgnoreCase("firefox")) {
				return getFirefox();
			}else if(System.getProperty("browser").equalsIgnoreCase("edge")) {
				return getEdge();
			}else {
				return getChrome();
			}
			 
		}

	private WebDriver getChrome() {
			System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
			ChromeOptions chromeOptions = new ChromeOptions();
			Proxy proxy = new Proxy();
			proxy.setProxyType(ProxyType.AUTODETECT);
			chromeOptions.setCapability("proxy", proxy);
			chromeOptions.setAcceptInsecureCerts(true);
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("enable-features=NetworkServiceInProcess");
			chromeOptions.addArguments("--no-sandbox");
			return new ChromeDriver(chromeOptions);
		}
		
		/* private WebDriver getChrome() {
			System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
			return new ChromeDriver();
		} */
		
		private WebDriver getFirefox() {
			System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			Proxy proxy = new Proxy();
			proxy.setProxyType(ProxyType.AUTODETECT);
			firefoxOptions.setCapability("proxy", proxy);
			return new FirefoxDriver(firefoxOptions);
		}
		
		/*private WebDriver getIE() {
			System.setProperty("webdriver.ie.driver", getIEdriverPath());
			InternetExplorerOptions ieoptions = new InternetExplorerOptions();
			Proxy proxy = new Proxy();
			proxy.setProxyType(ProxyType.AUTODETECT);
			ieoptions.setCapability("proxy", proxy);
			return new InternetExplorerDriver(ieoptions);
		}*/
	
	
	private WebDriver getEdge() {
		System.setProperty("webdriver.edge.driver",getIEdriverPath());
          EdgeOptions edgeoptions = new EdgeOptions();
          Proxy proxy = new Proxy();
		  proxy.setProxyType(ProxyType.AUTODETECT);
		  edgeoptions.setCapability("proxy", proxy);
		  return new EdgeDriver(edgeoptions);
		
	}
	};

	public WebDriver getDriver() 
	{
		return driver.get();
	}

	public void removeDriver() 
	{
		driver.get().quit();
		driver.remove();
		logger.get().info("Driver instance closed successfully");
		
	}
	
	public void removeDriverWithoutLog() {
		driver.get().quit();
		driver.remove();
	}
	
	private static String getChromeDriverPath() {
		String win = System.getProperty("user.dir")
				+ "/BrowserDrivers/chromedriver_win/chromedriver.exe";
		
		String linux = System.getProperty("user.dir")
				+ "/BrowserDrivers/chromedriver_linux/chromedriver";
		
		String OS=null;
		try {
			OS = System.getProperty("os.name");

			if (OS.contains("Window")) {

				return win;

			} else if (OS.contains("linux")) {

				return linux;

			} else {

				return linux;
			}
		} catch (Exception e) {

			System.out.println("Unable to Get the Chrome File Path" + e.getMessage());
		}
		return OS;
	}
	
	private static String getFirefoxDriverPath() {
		String win = System.getProperty("user.dir")
				+ "/BrowserDrivers/geckodriver_win/geckodriver.exe";
		
		String linux = System.getProperty("user.dir")
				+ "/BrowserDrivers/geckodriver_linux/geckodriver";
		
		String OS=null;
		try {
			OS = System.getProperty("os.name");

			if (OS.contains("Window")) {

				return win;

			} else if (OS.contains("linux")) {

				return linux;

			} else {

				return linux;
			}
		} catch (Exception e) {

			System.out.println("Unable to Get the firefox File Path" + e.getMessage());
		}
		return OS;
	}
	
	private static String getIEdriverPath() {
		String win = System.getProperty("user.dir")
				+ "/BrowserDrivers/iedriver/MicrosoftWebDriver.exe";
		return win;
		
	}
	
	
	
	
	
	
	
	
}

