package utilities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import okio.Timeout;

public class PageAction extends Sync {

	public PageAction() {
	}

	public String getCurrentUsername() {
		return ThreadManager.username.get();
	}

	private List<String> errorPageList = new ArrayList<>(Arrays.asList("Request Rejected", "Server Error"));

	/**
	 * uses submit method under WebElement
	 * 
	 * @param ele
	 *            WebElement having tag button and type submit
	 * @param name
	 */
	public void submitElement(WebElement ele, String name) {
		try {
			ele.submit();
			logInfo("Clicked on  :- " + name);
		} catch (Exception e) {
			throw new CustomException("Unable to submit on the Element:- " + name, e);
		}
	}

	/**
	 * checks where the list of WebElements of exists. if does not exists, fails and
	 * stops test case execution
	 * 
	 * @param webElements
	 * @param name
	 * @return true if element found
	 */
	public boolean isElementsExists(List<WebElement> webElements, String name) {
		if (webElements.size() == 0) {
			throw new CustomException("Elements does not exists :- " + name);
		} else {
			logInfo("Element found :- " + name);
			return true;
		}
	}

	/**
	 * takes either By and WebElement,uses waitForElementClickable and scrolls to
	 * element before clicking element, halts the test execution if exception is
	 * thrown
	 * 
	 * @param ele
	 * @param name
	 */
	public <T> void clickWithClickableWait(T ele, String name) {
		try {
			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
			} else {
				WebElement ee = waitForElementClickable((WebElement) ele);
				scrollToElement(ee, name);
				ee.click();
			}
			logInfo("Clicked on :- " + name);
			checkForPageFailure();
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (ElementClickInterceptedException e) {
			logInfo("Caught ElementClickInterceptedException while trying click " + name
					+ " . Retrying clicking..... ");
			sleep(1000);
			clickElementAdditional(ele, name);
		} catch (StaleElementReferenceException e) {
			logInfo("Caught StaleElementReferenceException. Refreshing Page..... ");
			refreshCurrentPage();
			clickElementAdditional(ele, name);
		} catch (Exception e) {
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}
	}

	/**
	 * takes either By and WebElement,uses waitForElementClickable and scrolls to
	 * element before clicking element, halts the test execution if exception is
	 * thrown
	 * 
	 * @param ele
	 *            - Main element to be clicked, ele2 - element to be clicked on the
	 *            event of ElementClickInterceptedException name- representation
	 *            name of ele, name2- representation name of ele2
	 * @param name
	 */
	public <T> void clickAndHandleElementClickInterceptedException(T ele, String name, WebElement ele2, String name2) {
		try {
			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
			} else {
				WebElement ee = waitForElementClickable((WebElement) ele);
				scrollToElement(ee, name);
				ee.click();
			}
			logInfo("Clicked on :- " + name);
			checkForPageFailure();
		} catch (NoSuchElementException e) {
			logFailure("Element not found :- " + name);
			throw new CustomException("Element not found :- " + name, e);
		} catch (ElementClickInterceptedException e) {
			logInfo("Caught ElementClickInterceptedException while trying click " + name
					+ " . Retrying clicking..... ");
			sleep(1000);
			jsClick(ele2, name2);
			clickElementAdditional(ele, name);
		} catch (StaleElementReferenceException e) {
			logInfo("Caught StaleElementReferenceException. Refreshing Page..... ");
			refreshCurrentPage();
			clickElementAdditional(ele, name);
		} catch (Exception e) {
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}
	}

	private void checkForPageFailure() {
		String title = ThreadManager.getInstance().getDriver().getTitle();
		if (errorPageList.contains(title)) {
			logFailure("Page Title :-" + title);
			throw new CustomException("Page Error");

		}
	}

	public <T> void clickAndIgnoreFailure(T ele, String name) {
		if (ele.getClass().getName().contains("By")) {
			WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
			WebElement e = waitForElementClickable(ment);
			scrollToElement(e, name);
			e.click();
		} else {
			WebElement ee = waitForElementClickable((WebElement) ele);
			scrollToElement(ee, name);
			ee.click();
		}
		logInfo("Clicked on :- " + name);
		checkForPageFailure();

	}

	/**
	 * takes either By and WebElement,uses waitForElementClickable and without
	 * scrolls to element , halts the test execution if exception is thrown
	 * 
	 * @param ele
	 * @param name
	 */

	public <T> void clickWithoutScroll(T ele, String name) {
		try {
			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				WebElement e = waitForElementClickable(ment);

				e.click();
			} else {
				WebElement ee = waitForElementClickable((WebElement) ele);

				ee.click();
			}
			logInfo("Clicked on :- " + name);
			checkForPageFailure();
		} catch (NoSuchElementException e) {
			logFailure("Element not found :- " + name);
			throw new CustomException("Element not found :- " + name, e);
		} catch (ElementClickInterceptedException | StaleElementReferenceException e) {
			logInfo("Caught ElementClickInterceptedException OR StaleElementReferenceException while trying click "
					+ name + " . Retrying clicking..... ");
			sleep(1000);
			clickElementAdditional(ele, name);
		} catch (Exception e) {
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}
	}

	/**
	 * checks element existence and ignores noSuchElement exception, takes By type
	 * as parameter
	 * 
	 * @param element
	 *            of By type or WebElement
	 * @param name
	 * @return true if element found else false, does not fails test case
	 */
	public <T> Boolean isElementExistsIgnore(T ele, String name) {
		try {
			Boolean isDisplayed;
			setImplicitWait(1);
			if (ele.getClass().getName().contains("By")) {
				ThreadManager.getInstance().getDriver().findElement((By) ele);
				isDisplayed = true;
			} else {
				isDisplayed = ((WebElement) ele).isDisplayed();
			}

			if (isDisplayed) {
				logInfo("Element found :- " + name);
			} else {
				logInfo("Element not found :- " + name);
			}
			resetImplicitWait();
			return isDisplayed;
		} catch (Exception e) {
			logInfo("Element not found :- " + name);
			resetImplicitWait();
			return false;
		}

	}

	/**
	 * checks element No existence and ignores noSuchElement exception, takes By
	 * type as parameter, set implicitWait to 1 and resets implicitwait
	 * 
	 * @param element
	 *            of By type or WebElement
	 * @param name
	 * @return true if element Not found else false, does not fails test case
	 */
	public <T> Boolean isElementNotExistsIgnore(T ele, String name) {
		setImplicitWait(1);
		try {
			Boolean isDisplayed;
			if (ele.getClass().getName().contains("By")) {
				ThreadManager.getInstance().getDriver().findElement((By) ele);
				isDisplayed = true;
			} else {
				isDisplayed = ((WebElement) ele).isDisplayed();
			}

			if (isDisplayed) {
				logInfo("Element found :- " + name);
				isDisplayed = false;
			} else {
				logInfo("Element not found :- " + name);
				isDisplayed = true;
			}
			resetImplicitWait();
			return isDisplayed;
		} catch (Exception e) {
			logInfo("Element not found :- " + name);
			resetImplicitWait();
			return true;
		}

	}

	/**
	 * checks element existence and ignores noSuchElement exception,
	 * 
	 * @param ele
	 *            of By type or Webelement
	 * @return true if element exists else false, does not fails test case
	 */
	public <T> Boolean isElementExistsIgnore(T ele) {
		Boolean isExists;

		setImplicitWait(2);
		try {

			WebElement element;
			if (ele.getClass().getName().contains("By")) {
				element = ThreadManager.getInstance().getDriver().findElement((By) ele);
				isExists = element.isDisplayed();
			} else {
				isExists = ((WebElement) ele).isDisplayed();
			}

			if (isExists) {
				logInfo("Element found :- " + ele.toString());
				isExists = true;
			} else {
				logInfo("Element not found :- " + ele.toString());
				isExists = false;
			}
			resetImplicitWait();
			return isExists;
		} catch (Exception e) {
			logInfo("Element not found :- " + ele.toString());
			resetImplicitWait();
			return false;
		}

	}

	public WebElement isRelativeElementExistsIgnore(WebElement web, By element, String name) {
		try {
			WebElement findElement = web.findElement(element);
			logInfo("Element found :- " + name);
			return findElement;
		} catch (NoSuchElementException e) {
			logInfo("Element not found :- " + name);
			return null;
		}

	}

	public WebElement findElement(By element, String name) {
		try {

			WebElement findElement = ThreadManager.getInstance().getDriver().findElement(element);
			logInfo("Element found :- " + name);
			return findElement;
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (Exception e) {
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}

	}

	public WebElement findRelativeElement(WebElement web, By element, String name) {
		try {

			WebElement findElement = web.findElement(element);
			logInfo("Element found :- " + name);
			return findElement;
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (Exception e) {
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}

	}

	public List<WebElement> findRelativeElements(WebElement web, By element, String name) {
		try {

			List<WebElement> findElements = web.findElements(element);
			logInfo("Elements found :- " + name);
			return findElements;
		} catch (NoSuchElementException e) {
			throw new CustomException("Elements not found :- " + name, e);
		} catch (Exception e) {
			throw new CustomException("Unable to find the Elements:- " + name, e);
		}

	}

	public List<WebElement> findElements(By element, String name) {
		List<WebElement> ele = ThreadManager.getInstance().getDriver().findElements(element);
		if (!ele.isEmpty()) {
			logInfo("Element found :- " + name);
		} else {
			throw new CustomException("Element not found :- " + name);
		}
		return ele;
	}

	/**
	 * checks element exits or not. does not fails the test case
	 * 
	 * @param element
	 *            By type only
	 * @param name
	 * @return element
	 */
	public List<WebElement> isElementsExistsIgnore(By element, String name) {
		List<WebElement> ele = ThreadManager.getInstance().getDriver().findElements(element);
		if (!ele.isEmpty()) {
			logInfo("Elements found :- " + name);
		} else {
			logInfo("Elements not found :- " + name);
		}
		return ele;
	}

	/**
	 * checks elements existence and igores failure. does not stop or fail test case
	 * 
	 * @param ele
	 *            By or WebElement
	 * @param name
	 * @return true if exists else not
	 */
	@SuppressWarnings("unchecked")
	public <T> boolean isElementsExistsIgnore(T ele, String name) {
		List<WebElement> elements;
		if (ele.getClass().getName().contains("By")) {
			elements = ThreadManager.getInstance().getDriver().findElements((By) ele);
		} else {
			elements = (List<WebElement>) ele;
		}
		if (!elements.isEmpty()) {
			logInfo("Elements found :- " + name);
			return true;
		} else {
			logInfo("Elements not found :- " + name);
			return false;
		}
	}

	/**
	 * takes either By and WebElement,scrolls to element before clicking element,
	 * halts the test execution if exception is thrown
	 * 
	 * @param ele
	 * @param name
	 */
	public <T> void clickWithoutClickableWait(T ele, String name) {
		try {
			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				scrollToElement(ment, name);
				(ment).click();
			} else {
				scrollToElement((WebElement) ele, name);
				((WebElement) ele).click();
			}
			logInfo("Clicked on :- " + name);
			checkForPageFailure();
		} catch (NoSuchElementException e) {
			logFailure("Element not found :- " + name);
			throw new CustomException("Element not found :- " + name, e);
		} catch (Exception e) {
			logFailure("Unable to click on the Element:- " + name);
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}
	}

	public void inputText(WebElement ele, String name, String inputData) {
		try {
			ele.sendKeys(inputData);
			logInfo("Entered data to " + name + " : " + inputData);
		} catch (NoSuchElementException e) {
			throw new CustomException("Unable to Enter data to " + name + " : " + inputData, e);
		} catch (Exception e) {
			throw new CustomException("Unable to input text on the Element:- " + name, e);
		}
	}

	public void clearTextBox(WebElement ele, String name) {
		try {
			ele.clear();
			logInfo("Cleared the textbox " + name);

		} catch (Exception e) {
			throw new CustomException("Unable to clear the textbox " + name, e);
		}
	}

	public void inputText(WebElement ele, String name, Keys keyStroke) {
		try {
			ele.sendKeys(keyStroke);
			logInfo("Entered data to " + name + " : " + keyStroke.toString());
		} catch (NoSuchElementException e) {
			throw new CustomException("Unable to Enter data to " + name + " : " + keyStroke.toString(), e);
		} catch (Exception e) {
			throw new CustomException("Unable to input text on the Element:- " + name, e);
		}
	}

	public <T> String getText(T ele, String name) {
		String text = "";
		try {
			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				text = ment.getText();
			} else {
				text = ((WebElement) ele).getText();
			}

			logInfo("Fetched text from the Element :- " + name);
			logInfo("Text :-" + text);
			if (text.contentEquals("")) {
				logInfo("Fetched text is empty");
			}
			return text;
		} catch (NoSuchElementException e) {
			logFailure("Element not found :- " + name);
			throw new CustomException("Element not found :- " + name, e);
		} catch (Exception e) {
			logFailure("Unable to get Text from this Element:- " + name);
			throw new CustomException("Unable to get Text from this Element:- " + name, e);
		}
	}

	public <T> String getTextWithoutLogging(T ele) {
		String text = "";
		if (ele.getClass().getName().contains("By")) {
			WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
			text = ment.getText();
		} else {
			text = ((WebElement) ele).getText();
		}
		return text;
	}

	/**
	 * Returns attribute from the given element
	 * 
	 * @param ele
	 * @param attributeName
	 * @param name
	 * @return
	 */

	public <T> String getAttribute(T ele, String attributeName, String name) {
		String value;
		try {
			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				value = ment.getAttribute(attributeName);
			} else {
				value = ((WebElement) ele).getAttribute(attributeName);
			}
			logInfo("Fetched attribute" + attributeName + " from the Element :- " + name);
			logInfo("Value :-" + value);
			if (value.contentEquals("")) {

				throw new CustomException("Fetched value is empty");
			}
			return value;
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (Exception e) {
			throw new CustomException("Unable to get attribute from this Element:- " + name, e);
		}
	}

	/**
	 * Returns attribute from the given element
	 * 
	 * @param ele
	 * @param attributeName
	 * @param name
	 * @return
	 */
	public String getAttributeIgnoreEmpty(WebElement ele, String attributeName, String name) {
		try {
			String value = ele.getAttribute(attributeName);
			logInfo("Fetched attribute " + attributeName + " from the Element :- " + name);
			logInfo("Value :-" + value);
			if (value.contentEquals("")) {
				logInfo("The attribute " + attributeName + " from the Element :- " + name + " is Empty");
			}
			return value;
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (Exception e) {
			throw new CustomException("Unable to get attribute from this Element:- " + name, e);
		}
	}

	/**
	 * clicks the link, finds Element with link test, uses
	 * clickElementWithClickableWait method
	 * 
	 * @param linkName
	 *            visible link text
	 */
	public void clickLink(String linkName) {
		clickWithClickableWait((By.linkText(linkName)), linkName + " link");
	}

	/**
	 * clicks the link, finds Element with link test, uses
	 * clickElementWithClickableWait method
	 * 
	 * @param linkName
	 *            visible link text
	 */
	public void clickLinkWithoutScroll(String linkName) {
		clickWithoutScroll((By.linkText(linkName)), linkName + " link");
	}

	/**
	 * mouse hover
	 * 
	 * @param ele
	 * @param elementName
	 */

	public void inputUsingActions(WebElement element, String value, String fieldName) {

		try {

			Actions actions = new Actions(ThreadManager.getInstance().getDriver());
			actions.moveToElement(element);
			actions.click();
			actions.sendKeys(value);
			actions.build().perform();
			logInfo("Entered Text into " + fieldName + " : " + value);

		} catch (Exception e) {

			throw new CustomException("Unable to click Element using js:- " + value + fieldName, e);

		}

	}

	public void clearUsingActions(WebElement element) {
		try {
			Actions actions = new Actions(ThreadManager.getInstance().getDriver());
			actions.moveToElement(element);
			actions.click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build()
					.perform();
		} catch (Exception e) {
			throw new CustomException("Unable to click Element using js:- " + e);
		}
	}

	public void mouseHover(WebElement ele, String elementName) {
		Actions action = new Actions(ThreadManager.getInstance().getDriver());
		try {
			action.moveToElement(ele).build().perform();
			logInfo("Mouse Hover to this Element : " + elementName);
		} catch (Exception e) {
			throw new CustomException("Unable to mouse hover on the Element:- " + elementName, e);
		}
	}

	/*
	 * js click
	 * 
	 * @param ele
	 * 
	 * @param name
	 */
	public void jsClick(WebElement ele, String name) {
		try {
			ThreadManager.getInstance().getDriver().switchTo().defaultContent();
			JavascriptExecutor executor = (JavascriptExecutor) ThreadManager.getInstance().getDriver();
			;
			executor.executeScript("arguments[0].click();", ele);
			logInfo("Clicked on " + name);
		} catch (Exception e) {
			throw new CustomException("Unable to click Element using js:- " + name, e);

		}
	}

	// Method to verify an element using Javascript
	public boolean jsVerify(WebElement ele, String name) {
		try {
			ThreadManager.getInstance().getDriver().switchTo().defaultContent();
			JavascriptExecutor executor = (JavascriptExecutor) ThreadManager.getInstance().getDriver();
			;
			executor.executeScript("return typeof(arguments[0]) != 'undefined' && arguments[0] != null;", ele);
			logInfo("Verified the element " + name);
			return true;
		} catch (Exception e) {
			throw new CustomException("Unable to verify Element using js:- " + name, e);

		}
	}

	/**
	 * Return first selected option from drop down Element. Applicable for Element
	 * with select tag only
	 * 
	 * @param ele
	 *            Element with select tag
	 * @return
	 */
	public String getFirstSelected(WebElement ele) {
		String firstSelected = null;
		try {
			Select select = new Select(ele);
			firstSelected = select.getFirstSelectedOption().getText().trim();
			logInfo(firstSelected);
		} catch (Exception e) {
			logInfo("Unable to Fetch First Selected Option due to " + e.getMessage());
		}
		return firstSelected;
	}

	/**
	 * Selects option with Visible Text from drop down
	 * 
	 * @param element
	 *            dropdown Webelement
	 * @param visibleText
	 * @param name
	 */
	public void selectByVisibleText(WebElement element, String visibleText, String name) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(visibleText);
			logInfo("Selected the text :- " + visibleText + " from this Element :- " + name);
		} catch (Exception e) {
			throw new CustomException("Unable to select this text " + visibleText, e);
		}
	}

	/**
	 * 
	 * @param element
	 * @param option
	 *            required option to verify the existence in drop down
	 * @param name
	 * @return text if found else null. Does not logs fail or fails test case if not
	 *         found
	 */
	public String isThisOptionExistsDropDown(WebElement element, String option, String name) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		logInfo("Total options under this dropdown " + name + " :- " + options.size());
		for (WebElement e : options) {
			String txt = e.getText();
			if (txt.contains(option)) {
				logInfo("Required value :- " + option + " matched with the value :- " + txt);
				return txt;
			}
		}
		logInfo("Required value :- " + option + " not found in list of drop down values :- ");
		return null;
	}

	/**
	 * Selects option with index value from drop down
	 * 
	 * @param element
	 *            dropdown Webelement
	 * @param index
	 * @param name
	 */
	public void selectByIndex(WebElement ele, int index, String name) {
		try {
			Select select = new Select(ele);
			select.selectByIndex(index);
			logInfo("Selected the index value :- " + index + " from this Element :- " + name);
		} catch (Exception e) {
			logInfo("Unable to select the index value " + index + e.getMessage());
		}
	}

	/**
	 * Static wait. uses Thread sleep. logs sleep duration in the report Avoid using
	 * this method.
	 * 
	 * @param duration
	 *            in milliSecond
	 */
	public void sleep(long duration) {
		try {
			Thread.sleep(duration);
			logInfo("Thread Sleep :- " + duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns Current page title.
	 * 
	 * @return
	 */
	public String getCurrentPageTitle() {
		String title = ThreadManager.getInstance().getDriver().getTitle();
		if (title.equals("Request Rejected")) {
			logFailure("Current page title :- " + title);
		} else {
			logInfo("Current page title :- " + title);
		}
		return title;
	}

	/**
	 * get Current URL
	 */
	public String getCurentURL() {
		String currentURL = ThreadManager.getInstance().getDriver().getCurrentUrl();
		logInfo("get Current URL");
		return currentURL;
	}

	/**
	 * current page refresh
	 */
	public void refreshCurrentPage() {
		ThreadManager.getInstance().getDriver().navigate().refresh();
		logInfo("Refreshing current page");
		getCurrentPageTitle();

	}

	public void switchToDefaultContent() {

		ThreadManager.getInstance().getDriver().switchTo().defaultContent();
		logInfo("Browser control switched default Content");
	}

	/**
	 * selects required value from drop down WebElement having li tag. Throws custom
	 * exception if passed value not found in the drop down
	 * 
	 * @param visibleTextToChoose
	 */
	public void selectCustomDropDown(String visibleTextToChoose) {
		Boolean isfound = false;
		String xp = "//li[contains(text(),'" + visibleTextToChoose + "')]";
		switchToDefaultContent();
		List<WebElement> findElements = findElements(By.xpath(xp), visibleTextToChoose);
		for (WebElement e : findElements) {
			if (e.getText().contentEquals(visibleTextToChoose)) {
				clickWithClickableWait(e, visibleTextToChoose);
				waitForElementInvisibility(e);
				isfound = true;
				break;
			}
		}
		if (!isfound) {
			ThreadManager.logger.get()
					.fail("Unable to choose this value from dropdown. This Value Not found in drop down:- "
							+ visibleTextToChoose);
			throw new CustomException("Unable to choose this value from dropdown :-  " + visibleTextToChoose);
		}
	}

	public void switchToFrame(WebElement frame) {
		ThreadManager.getInstance().getDriver().switchTo().frame(frame);
		logInfo("Switched to iframe");
	}

	public void switchToFrame(String frame) {
		ThreadManager.getInstance().getDriver().switchTo().frame(frame);
		logInfo("Switched to iframe");
	}

	/**
	 * clicks the element until the element becomes invisible
	 * 
	 * @param ele
	 *            takes either By or WebElement
	 * @param name
	 */
	public <T> void clickUntilInvisible(T ele, String name) {
		try {

			if (ele.getClass().getName().contains("String")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement(By.linkText((String) ele));
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
				logInfo("Clicked on :- " + name);
				waitForElementInvisibleAfterClick(e);
			} else if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
				logInfo("Clicked on :- " + name);
				waitForElementInvisibleAfterClick(e);
			} else {
				WebElement ee = waitForElementClickable((WebElement) ele);
				scrollToElement(ee, name);
				ee.click();
				logInfo("Clicked on :- " + name);
				waitForElementInvisibleAfterClick(ee);
			}
			checkForPageFailure();
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (ElementClickInterceptedException e) {
			logInfo("Caught ElementClickInterceptedException while trying click " + name
					+ " . Retrying clicking..... ");
			sleep(1000);
			clickElementAdditional(ele, name);
		} catch (TimeoutException e) {
			throw new CustomException("Element invisibility time out after click:- " + name, e);
		} catch (Exception e) {
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}

	}

	/**
	 * clicks the element until the element becomes invisible
	 * 
	 * @param ele
	 *            takes either By or WebElement
	 * @param name
	 * @throws Timeout
	 *             exception if intended element is invisible after click
	 */
	public <T> void clickUntilInvisibleThrowFailure(T ele, String name) {
		try {

			if (ele.getClass().getName().contains("String")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement(By.linkText((String) ele));
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
				logInfo("Clicked on :- " + name);
				waitForElementInvisibleAfterClick(e);
			} else if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
				logInfo("Clicked on :- " + name);
				waitForElementInvisibleAfterClick(e);
			} else {
				WebElement ee = waitForElementClickable((WebElement) ele);
				scrollToElement(ee, name);
				ee.click();
				logInfo("Clicked on :- " + name);
				waitForElementInvisibleAfterClick(ee);
			}
			checkForPageFailure();
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (ElementClickInterceptedException e) {
			logInfo("Caught ElementClickInterceptedException while trying click " + name
					+ " . Retrying clicking..... ");
			sleep(1000);
			clickElementAdditional(ele, name);
		} catch (TimeoutException e) {
			logFailure("Element invisibility time out after click:- " + name);
			throw new TimeoutException(e);
		} catch (Exception e) {
			throw new CustomException("Unable to click on the Element:- " + name, e);
		}

	}

	public <T> void clickUntilOtherElementEnabled(T elementToBeClicked, WebElement elementToBeEnabled, String name) {
		try {
			if (elementToBeClicked.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) elementToBeClicked);
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
				logInfo("Clicked on :- " + name);
				waitUntilOtherElementEnabled(e, elementToBeEnabled);
			} else {
				WebElement ee = waitForElementClickable((WebElement) elementToBeClicked);
				scrollToElement(ee, name);
				ee.click();
				logInfo("Clicked on :- " + name);
				waitUntilOtherElementEnabled(ee, elementToBeEnabled);
			}

			checkForPageFailure();
		} catch (NoSuchElementException e) {
			throw new CustomException("Element not found :- " + name, e);
		} catch (ElementClickInterceptedException e) {
			logInfo("Caught ElementClickInterceptedException while trying click " + name
					+ " . Retrying clicking..... ");
			sleep(1000);
			clickElementAdditional(elementToBeClicked, name);
		} catch (Exception e) {
			throw new CustomException(
					"Unable to click OR corresonding element is enabled after clicking on this :- " + name, e);
		}
	}

	public <T> void clickUntilOtherElementInvisible(T elementToBeClicked, WebElement elementToBeInvisible,
			String name) {
		try {
			if (elementToBeClicked.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) elementToBeClicked);
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);
				e.click();
				logInfo("Clicked on :- " + name);
				waitUntilOtherElementInvisible(e, elementToBeInvisible);
			} else {
				WebElement ee = waitForElementClickable((WebElement) elementToBeClicked);
				scrollToElement(ee, name);
				ee.click();
				logInfo("Clicked on :- " + name);
				waitUntilOtherElementInvisible(ee, elementToBeInvisible);
			}

			checkForPageFailure();
		} catch (NoSuchElementException e) {

			throw new CustomException("Element not found :- " + name, e);
		} catch (ElementClickInterceptedException e) {
			logInfo("Caught ElementClickInterceptedException while trying click " + name
					+ " . Retrying clicking..... ");
			sleep(1000);
			clickElementAdditional(elementToBeClicked, name);
		} catch (Exception e) {

			throw new CustomException("Unable to click on the Element:- " + name, e);
		}
	}

	/************************************************************************************************************************************************/

	/**
	 * DO Not USE this method
	 * 
	 * @param ele
	 * @param name
	 */
	private <T> void clickElementAdditional(T ele, String name) {
		try {

			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				WebElement e = waitForElementClickable(ment);
				scrollToElement(e, name);

				e.click();
			} else {
				WebElement ee = waitForElementClickable((WebElement) ele);
				scrollToElement(ee, name);
				ee.click();
			}
			logInfo("Clicked on :- " + name);
		} catch (NoSuchElementException e) {

			throw new CustomException("Element not found :- " + name, e);
		} catch (Exception e) {

			throw new CustomException("Unable to click on the Element:- " + name, e);
		}
	}

	public void switchToNewTab() {
		ArrayList<String> tab = new ArrayList<>(ThreadManager.getInstance().getDriver().getWindowHandles());
		ThreadManager.getInstance().getDriver().switchTo().window(tab.get(1));
		logInfo("Switched to new tab 1");
	}

	public void closeNewTab() {
		ArrayList<String> tab = new ArrayList<>(ThreadManager.getInstance().getDriver().getWindowHandles());
		ThreadManager.getInstance().getDriver().switchTo().window(tab.get(1)).close();
		logInfo("Closed to new tab 1");
		;
	}

	public void switchToParentTab() {
		ArrayList<String> tab = new ArrayList<>(ThreadManager.getInstance().getDriver().getWindowHandles());
		ThreadManager.getInstance().getDriver().switchTo().window(tab.get(0));
		logInfo("Switched to Parent tab");
	}

	/**
	 * selects the First Value from DropDown
	 * 
	 * @param ele
	 * @param name
	 */
	public void selectFirstValue(WebElement ele, String name) {
		;
		if (new Select(ele).getOptions().size() > 0) {
			try {
				new Select(ele).getOptions().get(1).click();
			} catch (Exception e) {
				new Select(ele).getOptions().get(0).click();
			}
			logInfo("Selected from" + name);

		} else {
			ele.click();
			logInfo("Data is Not Available in " + name + " drop down list");

		}

	}

	/**
	 * Matches text actual contains expected text, does not logs the test case as
	 * failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @return true if matches else false
	 */
	public Boolean matchTextContains(String expected, String actual) {
		try {
			assertThat(actual, containsString(expected));
			logInfo("Text Contains Match passed :Actual: " + actual + ", Expected: " + expected);
			return true;
		} catch (AssertionError e) {
			logInfo("Text Contains Match failed : Actual: " + actual + ", Expected: " + expected);
			return false;

		}
	}

	/**
	 * Matches text actual equal To expected text, does not logs the test case as
	 * failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @return true if matches else false
	 */
	public Boolean matchTextEquals(String expected, String actual) {
		try {
			assertThat(actual, equalTo(expected));
			logInfo("Text Equal Match passed :Actual: " + actual + ", Expected: " + expected);
			return true;
		} catch (AssertionError e) {
			logInfo("Text Contains Match failed : Actual: " + actual + ", Expected: " + expected);
			return false;

		}
	}

	/**
	 * Matches text actual equal To expected text, does not logs the test case as
	 * failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @return true if matches else false
	 */
	public Boolean matchTextEqualIgnoringcase(String expected, String actual) {
		try {
			assertThat(actual, equalToIgnoringCase(expected));
			logInfo("Text Equal Match Ignoringcase passed :Actual: " + actual + ", Expected: " + expected);
			return true;
		} catch (AssertionError e) {
			logInfo("Text Equal Match Ignoringcase failed : Actual: " + actual + ", Expected: " + expected);
			return false;

		}
	}

	public String extractSequenceFromString(String givenString, String regex) {
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(givenString);
		if (matcher.find()) {
			String ext = matcher.group();
			logInfo("Extracted Text :-" + ext);
			return ext;
		} else {
			logInfo("Given regex not matched in given String");
			return "";
		}

	}

	public String convertToLowerCase(String givenString) {
		String lowerCaseString = givenString.toLowerCase();
		logInfo("String converted to lowercase :- " + lowerCaseString);
		return lowerCaseString;
	}

	public void writeToNote(String ordNum) {

		try {

			String str = System.getProperty("user.dir") + "/order.txt";
			File file = new File(str);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.newLine();
			bw.write("," + ordNum);
			bw.close();
			System.out.println("Data successfully appended at the end of file");
		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
	}

	public void writeERPToNote(String erpNum) {

		try {
			String str = System.getProperty("user.dir") + "/ERPNUM.txt";
			File file = new File(str);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.newLine();

			bw.append("\n");
			bw.write(erpNum);
			bw.close();
			System.out.println("Data successfully appended at the end of file");
		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}

	}

	public PageAction readFromNote() {
		String str = System.getProperty("user.dir") + "/order.txt";
		BufferedReader br = null;
		List<String> OrderIDs = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(str));
			System.out.println("Reading the file using readLine() method:");
			String contentLine = br.readLine();
			while (contentLine != null) {
				System.out.println(contentLine);
				// contentLine = br.readLine();
				// String[] lines = contentLine.split("\\r?\\n").toString();
				String Orders = contentLine.toString();

				String OrderList[] = Orders.split(",");
				System.out.println(Arrays.deepToString(OrderList));
				for (String string : OrderList) {

					OrderIDs.add(string);

				}

				ThreadManager.placeholder.get().setArrayList(OrderIDs);
				break;
			}

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
		return this;
	}

}
