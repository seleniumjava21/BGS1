package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Sync {
	protected static long waitDuration;
	private WebDriverWait wait;
	private static int count=0;
	private Wait<WebDriver> customWait;

	Sync() {
		waitDuration = Long.parseLong(System.getProperty("time"));
		if(!System.getProperty("browser").contentEquals("chrome")) {
			waitDuration = waitDuration + 30;
		}
		wait = new WebDriverWait(ThreadManager.getInstance().getDriver(), waitDuration);
		customWait = new FluentWait<WebDriver>(ThreadManager.getInstance().getDriver())
				.pollingEvery(Duration.ofMillis(250))
				.withTimeout(Duration.ofSeconds(waitDuration))
				.ignoring(NoSuchElementException.class);
	}
	
	public void setCustomExplicitWait(long duration) {
		if(!System.getProperty("browser").contentEquals("chrome")) {
			duration = duration + 30;
		}
		wait = new WebDriverWait(ThreadManager.getInstance().getDriver(), duration);
		customWait = new FluentWait<WebDriver>(ThreadManager.getInstance().getDriver())
				.pollingEvery(Duration.ofMillis(250))
				.withTimeout(Duration.ofSeconds(duration))
				.ignoring(NoSuchElementException.class);
		logInfo("setting custom wait to :-" + duration);
		
	}
	
	public void resetCustomExplicitWait() {
		if(!System.getProperty("browser").contentEquals("chrome")) {
			waitDuration = Long.parseLong(System.getProperty("time"))+30;
		}
		wait = new WebDriverWait(ThreadManager.getInstance().getDriver(), waitDuration);
		customWait = new FluentWait<WebDriver>(ThreadManager.getInstance().getDriver())
				.pollingEvery(Duration.ofMillis(250))
				.withTimeout(Duration.ofSeconds(waitDuration))
				.ignoring(NoSuchElementException.class);
		logInfo("resetting custom wait to :-" + waitDuration);
	}
	
	/**
	 * logger for extent report. Use this for general purpose information logging
	 */
	public void logInfo(String message) {
		ThreadManager.logger.get().info(message);
		System.out.println(message +" - "+ Thread.currentThread().getId());
	}
	
	/**
	 * logger for extent report. Use this for general purpose information logging
	 */
	public void logInfo(String message, Throwable e) {
		ThreadManager.logger.get().info(message);
		ThreadManager.logger.get().info(e);
		System.out.println(message +" - "+ Thread.currentThread().getId());
		System.out.println(e +" - "+ Thread.currentThread().getId());
	}
	
	/**
	 * logs the test case report as failed in RED Label in the report
	 * Use this log the test failure
	 * @param custom message logged in the report
	 */
	public void logFailure(String message) {
		ThreadManager.logger.get().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
		System.out.println("FAILED *** " +message +" - "+ Thread.currentThread().getId());
	}
	
	
	/**
	 * waits until text is visible in Element.
	 * @param ele
	 * @return 
	 */
	public void waitUntilTextVisibleInElement(WebElement ele, String name) {
		disableImplicitWait();
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver input) {
					return !(ele.getText().contentEquals(""));		
				}
				
			});
			logInfo("Element Text visible :- "+ name);
			resetImplicitWait();
		} catch (Exception e) {
			resetImplicitWait();
			throw new CustomException("Element Text Visiblity Time out :- " + name, e);
		}

	}
	
	
	
	
	/**
	 * logs the test case report as failed in the report
	 * Use this log the test failure
	 * @param custom message logged in the report
	 */
	public void logFailedException(Throwable message) {
		ThreadManager.logger.get().fail(message);
		System.out.println("FAILED *** " +message +" - "+ Thread.currentThread().getId());
	}
	
	
	/**
	 * logs the test case report as passed in GREEN Label in the report
	 * Use this log the test Pass
	 * @param custom message logged in the report
	 */
	public void logPass(String message) {
		ThreadManager.logger.get().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
		System.out.println(message +" :PASSED - "+ Thread.currentThread().getId());
	}
	
	/**
	 * logs the test case report as warning in amber Label in the report
	 * Use this log the test warning
	 * @param custom message logged in the report
	 */
	public void logWarning(String message) {
		ThreadManager.logger.get().pass(MarkupHelper.createLabel(message, ExtentColor.AMBER));
		System.out.println(message +" :WARNING - "+ Thread.currentThread().getId());
	}
	
	
	private synchronized static int  getCount() {
		return count++;
	}
	/**
	 * takes screenshot,logs the report with hyperlink and fails the test case
	 */
	public synchronized void takeScreenshot() {
		File screenShotName;
		try {
		File scrFile = ((TakesScreenshot) ThreadManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		String p = "Screenshots" + File.separator + getCount() + ".jpg";
		String fpath = System.getProperty("user.dir") + File.separator + "ExtentReports" + File.separator+p;
		screenShotName = new File(fpath);
		
			FileUtils.copyFile(scrFile, screenShotName);
			ThreadManager.logger.get().log(Status.FAIL, (new ScreenshotLink(p)));
		} catch (TimeoutException e1) {
			System.out.println(e1);
			System.out.println("Timout rendering");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in taking screenshot");
		}

	}

	/**
	 * takes screenshot,logs the report with hyperlink and does NOT fails the test
	 * case
	 */
	public synchronized void takeScreenshot(String linkname) {
		File screenShotName;
		try {
		File scrFile = ((TakesScreenshot) ThreadManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		String p = "Screenshots" + File.separator + getCount() + ".jpg";
		String fpath = System.getProperty("user.dir") + File.separator + "ExtentReports" + File.separator+ p;
				
		screenShotName = new File(fpath);
		
			FileUtils.copyFile(scrFile, screenShotName);
			screenShotName.toString();
			ThreadManager.logger.get().log(Status.INFO, (new ScreenshotLink(p, linkname)));
		}
			catch (TimeoutException e1) {
				System.out.println(e1);
				System.out.println("Timout rendering");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in taking screenshot");
		
		}

	}
	
	

	public void resetImplicitWait() {
		ThreadManager.getInstance().getDriver().manage().timeouts().implicitlyWait(waitDuration, TimeUnit.SECONDS);
	}

	public void disableImplicitWait() {
		ThreadManager.getInstance().getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	
	public void setImplicitWait(long durationInSeconds) {
		ThreadManager.getInstance().getDriver().manage().timeouts().implicitlyWait(durationInSeconds, TimeUnit.SECONDS);
	}
	
	public void scrollToElement(WebElement element, String name) {
		try {
		    if (ThreadManager.getInstance().getDriver() instanceof JavascriptExecutor) {
		        ((JavascriptExecutor) ThreadManager.getInstance().getDriver())
		            .executeScript("arguments[0].scrollIntoView(true);", element);
		        logInfo("Scroll to element :- " +name);
		    }  
		} catch(NoSuchElementException e) {			
			throw new CustomException("Element not found :- "+name, e);
		}
		catch (Exception e) {		
			throw new CustomException("Unable to scroll to the Element:- "+name,e);
		}
		}

	/**
	 * fails tests case and stops execution on TimeOUT
	 * 
	 * @param ele
	 */
	public void waitForElementInvisibility(WebElement ele) {
		disableImplicitWait();
		try {
			wait.until(ExpectedConditions.invisibilityOf(ele));
			resetImplicitWait();
		} catch (Exception e) {
			throw new CustomException("Element Invisiblity Time out", e);
		}

	}
	
	public void scrollToEndofPage() {
		((JavascriptExecutor) ThreadManager.getInstance().getDriver())
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToTopOfPage() {
		((JavascriptExecutor) ThreadManager.getInstance().getDriver())
		.executeScript("window.scrollTo(0, 0)");
	}
	
	/**
	 * returns the element if visible else returns null.
	 * Does not fails the test case. However, logs the as Element Visiblity Time out
	 * 
	 * @param ele
	 * @return 
	 */
	public void waitForElementVisibility(WebElement ele, String name) {
		disableImplicitWait();
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			logInfo("Element Visible :- " + name);
			resetImplicitWait();
		} catch (Exception e) {
			resetImplicitWait();
			throw new CustomException("Element Visiblity Time out :- " + name, e);
		}

	}
	
	

	/**
	 * returns the element if present in DOM, not necessary for element to visible.
	 * Does not fails the test case. However, logs the as Element Time out
	 * 
	 * @param locator
	 * @return 
	 * @return 
	 */
	public WebElement waitForElementPresence(By locator, String name) {
		disableImplicitWait();
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			resetImplicitWait();
			return element;
		} catch (Exception e) {
			resetImplicitWait();
			throw new CustomException("Element Presence in DOM Time out :- " + name, e);
		}

	}
	

	/**
	 * returns the element if Text present in the element
	 * Does not fails the test case. However, logs the as Text present in the element
	 * 
	 * @param ele
	 * @return 
	 */
	public void waitForTextToBePresentInElement(WebElement ele, String text) {
		disableImplicitWait();
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
			resetImplicitWait();
		} catch (Exception e) {
			resetImplicitWait();
			throw new CustomException("Text present in the element time out", e);
		}

	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it. 
	 * 
	 * @param ele
	 * @return same element else null
	 */
	public WebElement waitForElementClickable(WebElement ele) {
		disableImplicitWait();
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
		resetImplicitWait();
		return element;

	}

	/**
	 * An expectation for checking if the given element is selected. fails tests
	 * case and stops execution on TimeOUT
	 * 
	 * @param ele
	 */
	public void waitForElementSelected(WebElement ele) {
		disableImplicitWait();
		try {
			wait.until(ExpectedConditions.elementToBeSelected(ele));
			resetImplicitWait();
		} catch (Exception e) {
			ThreadManager.logger.get().fail("Element Clickable Time out");
			resetImplicitWait();
			throw new CustomException("Element Clickable Time out", e);
		}

	}

	/**
	 * An expectation for checking an element is displayed and enabled fails tests
	 * case and stops execution on TimeOUT
	 * 
	 * @param ele
	 * @return same element else stops the execution
	 */
	public WebElement waitForElementDisplayed(WebElement ele, String name) {
		disableImplicitWait();
		WebElement element;
		try {
			element = wait
						.ignoring(StaleElementReferenceException.class)
						.until(isElementDisplayed(ele));
			resetImplicitWait();
			
		}
		
		catch (Exception e1) {
			resetImplicitWait();
			throw new CustomException("Element is not displayed and Enabled : " + name, e1);
		}

		return element;

	}
	
	/**
	 * An expectation for checking an element is displayed and enabled 
	 * 
	 * @param ele
	 * @return same element else stops the execution
	 */
	public Boolean waitForElementDisplayedIgnore(WebElement ele, String name) {
		disableImplicitWait();
		setCustomExplicitWait(2);
		
		try {
			 wait
						.ignoring(StaleElementReferenceException.class)
						.until(isElementDisplayed(ele));
			resetImplicitWait();
			return true;
		}
		
		catch (Exception e1) {
			resetImplicitWait();
			resetCustomExplicitWait();
			return false;
		}

		

	}
	
	/**
	 * An expectation for checking all elements is displayed and enabled fails tests
	 * case and stops execution on TimeOUT
	 * 
	 * @param ele
	 * @return same element else stops the execution
	 */
	public List<WebElement> waitForElementsDisplayed(List<WebElement> ele, String name) {
		disableImplicitWait();
		List<WebElement> element;
		try {
			element = wait.until(ExpectedConditions.visibilityOfAllElements(ele));
			resetImplicitWait();
		} catch (Exception e1) {
			resetImplicitWait();
			ThreadManager.logger.get()
					.fail(MarkupHelper.createLabel("Element is not displayed and Enabled : " + name, ExtentColor.RED));
			throw new CustomException("Element is not displayed and Enabled : " + name, e1);
		}

		return element;

	}
	
	/**
	 * An expectation for checking an element is enabled fails tests
	 * case and stops execution on TimeOUT
	 * 
	 * @param ele
	 * @return same element else stops the execution
	 */
	public WebElement waitForElementEnabled(WebElement ele, String name) {
		disableImplicitWait();
		WebElement element;
		try {
			element = wait.until(isElementEnabled(ele));
			resetImplicitWait();
		} catch (Exception e1) {
			resetImplicitWait();
			ThreadManager.logger.get()
					.fail(MarkupHelper.createLabel("Element is not Enabled : " + name, ExtentColor.RED));
			throw new CustomException("Element is not Enabled : " + name, e1);
		}

		return element;

	}

	
	private ExpectedCondition<WebElement> isElementDisplayed(WebElement ele) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				if (ele.isDisplayed()&& ele.isEnabled()) {
					return ele;
				}
				return null;
			}
		};

	}
	
	private ExpectedCondition<WebElement> isElementEnabled(WebElement ele) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				if (ele.isEnabled()) {
					return ele;
				}
				return null;
			}
		};

	}
	
	
	protected void waitForElementInvisibleAfterClick(WebElement ele) {
		disableImplicitWait();
		customWait.until(isElementInvisible(ele));;
		resetImplicitWait();
	}

	

	protected void waitUntilOtherElementEnabled(WebElement ele1, WebElement ele2) {
		disableImplicitWait();
		customWait.until(isOtherElementEnabled(ele1, ele2));
		resetImplicitWait();		
	}

	protected void waitUntilOtherElementInvisible(WebElement ele1, WebElement ele2) {
		disableImplicitWait();
		customWait.until(isOtherElementInvisible(ele1, ele2));
		resetImplicitWait();		
	}
	
	private ExpectedCondition<Boolean> isElementInvisible(WebElement ele) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				Boolean b = null;
				try {
					b = ele.isDisplayed();
					if (!b) {
						return true;
					} else {
						ele.click();
						System.out.println("Clicked");
						return false;
					}
				} catch (Exception e) {
					return true;
				}

			}

		};

	}

	private ExpectedCondition<Boolean> isOtherElementInvisible(WebElement ele1,WebElement ele2) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				Boolean b = null;
				try {
					b = ele2.isDisplayed();
					if (!b) {
						return true;
					} else {
						ele1.click();
						System.out.println("Clicked");
						return false;
					}
				} catch (Exception e) {
					return true;
				}

			}

		};

	}

	private ExpectedCondition<Boolean> isOtherElementEnabled(WebElement ele, WebElement ele2) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				Boolean b = null;
				try {
					b = ele2.isDisplayed() && ele2.isEnabled();
					if (b) {
						return true;
					} else {
						ele.click();
						System.out.println("Clicked");
						return false;
					}
				} catch (Exception e) {
					return true;
				}

			}

		};

	}
	 

}
