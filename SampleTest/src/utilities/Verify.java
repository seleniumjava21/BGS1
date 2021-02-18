package utilities;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.text.IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace;

import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLEngineResult.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Verify extends PageAction {

	/**
	 * validates current page Tile against expected Title by using contains method
	 * of String class
	 * 
	 * @param expectedTitle
	 * @return true if matches else false, logs in green if matches else in grey.
	 *         Does not stop execution, logs test case as failed in Red if the page
	 *         title is "Request Rejected"
	 */
	public Boolean pageTitle(String expectedTitle) {
		if (ThreadManager.getInstance().getDriver().getTitle().contains(expectedTitle)) {
			logPass("Page Title :-" + ThreadManager.getInstance().getDriver().getTitle());
			return true;
		} else if (ThreadManager.getInstance().getDriver().getTitle().equalsIgnoreCase("Request Rejected")) {
			logFailure("Page Title :-" + ThreadManager.getInstance().getDriver().getTitle());
			throw new CustomException("Request Rejected Page");

		} else {
			ThreadManager.logger.get().pass(MarkupHelper.createLabel(
					"Page Title :-" + ThreadManager.getInstance().getDriver().getTitle(), ExtentColor.GREY));
			return false;
		}
	}

	/**
	 * check element is displayed, logs the test case as failed, does not stop the
	 * execution. uses explicit wait
	 * 
	 * @param ele
	 *            takes either By or WebElement
	 * @param name
	 *            representational name to log
	 * @return true if element is displayed
	 */
	public <T> Boolean isElementDisplayed(T ele, String name) {
		try {
			boolean displayed;
			if (ele.getClass().getName().contains("String")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement(By.linkText((String) ele));
				waitForElementDisplayed(ment, name);
				displayed = ment.isDisplayed();
			} else if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				waitForElementDisplayed(ment, name);
				displayed = ment.isDisplayed();
			} else {
				waitForElementDisplayed((WebElement) ele, name);
				displayed = ((WebElement) ele).isDisplayed();
			}
			if (displayed) {
				logInfo("Element Displayed :- " + name);
			} else {
				throw new NoSuchElementException("Element not found :- " + name);
			}
			return displayed;

		} catch (NoSuchElementException e) {
			logFailure("Element not found :- " + name);
			logFailedException(e);
			takeScreenshot();
			return false;
		} catch (Exception e) {
			logFailure("Element is not displayed:- " + name);
			logFailedException(e);
			takeScreenshot();
			return false;

		}
	}

	/**
	 * check element is NOT displayed, logs the test case as failed if element
	 * displayed, does not stop the execution
	 * 
	 * @param ele
	 *            takes either By or WebElement
	 * @param name
	 *            representational name to log
	 * @return false if element is displayed, true if Not displayed
	 */
	public <T> Boolean isElementNotDisplayed(T ele, String name) {
		try {
			boolean displayed;
			setImplicitWait(4);
			if (ele.getClass().getName().contains("String")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement(By.linkText((String) ele));
				displayed = ment.isDisplayed();
			} else if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				displayed = ment.isDisplayed();
			} else {
				displayed = ((WebElement) ele).isDisplayed();
			}
			if (displayed) {
				logFailure("Element Displayed :- " + name);
				takeScreenshot();
			} else {
				logInfo("Element not found :- " + name);
			}
			resetImplicitWait();
			return displayed;

		} catch (NoSuchElementException e) {
			logInfo("Element not found :- " + name);
			resetImplicitWait();
			return true;
		} catch (Exception e) {
			logInfo("Element is not displayed:- " + name);
			resetImplicitWait();
			return true;

		}
	}

	/**
	 * check element is displayed, logs the test case as failed, does not stop the
	 * execution
	 * 
	 * @param ele
	 *            takes either By or WebElement
	 * @param name
	 *            representational name to log
	 * @return true if element is displayed
	 */
	public <T> Boolean isElementEnabled(T ele, String name) {
		Boolean b;
		try {
			if (ele.getClass().getName().contains("By")) {
				WebElement ment = ThreadManager.getInstance().getDriver().findElement((By) ele);
				b = ment.isEnabled();
			} else {

				b = ((WebElement) ele).isEnabled();
			}
			logInfo("Element Enabled :- " + name);
			return b;

		} catch (NoSuchElementException e) {
			logFailure("Element not found :- " + name);
			takeScreenshot();
			logFailedException(e);
			return false;
		} catch (Exception e) {
			logFailure("Element is not Enabled:- " + name);
			takeScreenshot();
			logFailedException(e);
			return false;

		}
	}

	/**
	 * Asserts text actual contains expected text, logs the test case as failed,
	 * does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @param message
	 *            to log
	 * @return true if matches else false
	 */
	public Boolean verifyTextContains(String expected, String actual, String message) {
		try {
			assertThat(actual, containsString(expected));
			logPass("Text Contains Verification passed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			return true;
		} catch (AssertionError e) {
			logFailure("Text Contains Verification failed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			takeScreenshot();
			logFailedException(e);
			return false;

		}
	}

	public boolean verifyTextContainsIgnoreCase(String expected, String actual, String message) {
		try {
			assertThat(actual, equalToIgnoringCase(expected));
			assertThat(actual, containsString(expected));
			logPass("Verification of Text Match ignoring case passed :" + message + ", Actual: " + actual
					+ ", Expected: " + expected);
			return true;
		} catch (AssertionError e) {
			logFailure("Verification of Text Match ignoring case failed :" + message + ", Actual: " + actual
					+ ", Expected: " + expected);
			takeScreenshot();
			logFailedException(e);
			return false;
		}
	}

	/**
	 * Asserts text actual does contains expected text, logs the test case as
	 * failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @param message
	 *            to log
	 * @return true if matches else false
	 */
	public boolean verifyTextNotContains(String expected, String actual, String message) {
		try {
			assertThat(actual, not(containsString(expected)));
			logPass("Text Not Contains Verification passed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			return true;
		} catch (AssertionError e) {
			logFailure("Text Not Contains Verification failed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			takeScreenshot();
			logFailedException(e);
			return false;
		}
	}

	/**
	 * Asserts text actual matches with expected text ignoring case, logs the test
	 * case as failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @param message
	 *            to log
	 * @return true if matches else false
	 */
	public boolean verifyTextMatchIgnoringCase(String expected, String actual, String message) {
		try {
			assertThat(actual, equalToIgnoringCase(expected));
			logPass("Verification of Text Match ignoring case passed :" + message + ", Actual: " + actual
					+ ", Expected: " + expected);
			return true;
		} catch (AssertionError e) {
			logFailure("Verification of Text Match ignoring case failed :" + message + ", Actual: " + actual
					+ ", Expected: " + expected);
			takeScreenshot();
			logFailedException(e);
			return false;
		}
	}

	/**
	 * Asserts text actual matches with expected text , logs the test case as
	 * failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @param message
	 *            to log
	 * @return true if matches else false
	 */
	public boolean verifyTextMatch(String expected, String actual, String message) {
		try {
			assertThat(actual, equalTo(expected));
			logPass("Verification of Text Match passed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			return true;
		} catch (AssertionError e) {
			logFailure("Verification of Text Match failed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			takeScreenshot();
			logFailedException(e);
			return false;
		}
	}

	public void verifyListIgnoreSpace(List<String> deviceExpected, List<String> deviceActual, int i) {

		if (equalToIgnoringWhiteSpace(deviceActual.get(i)).matches(deviceExpected.get(i))) {
			logInfo("values name matches list saved from previous page " + deviceExpected.get(i) + deviceActual.get(i));
		} else {
			logFailure("Verification of Text Match failed : Actual: " + deviceActual.get(i) + ", Expected: "
					+ deviceExpected.get(i));
			takeScreenshot();

		}
	}

	/**
	 * Asserts text actual matches with expected text ignoring case, logs the test
	 * case as failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @param message
	 *            to log
	 * @return true if matches else false
	 */
	public <T> boolean verifyTextFromElementIgnoringCase(T element, String expected, String message) {
		WebElement ment;
		String actual = null;
		try {
			if (element.getClass().getName().contains("By")) {
				ment = ThreadManager.getInstance().getDriver().findElement((By) element);
			} else {
				ment = (WebElement) element;
			}
			actual = getText(ment, message);
			assertThat(actual, equalToIgnoringWhiteSpace(expected));
			logPass("Verification of Text Match passed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			return true;
		} catch (AssertionError e) {
			logFailure("Verification of Text Match failed :" + message + ", Actual: " + actual + ", Expected: "
					+ expected);
			takeScreenshot();
			logFailedException(e);
			return false;
		}
	}

	/**
	 * Matches text actual equal To expected float, does not logs the test case as
	 * failed, does not stop the execution
	 * 
	 * @param expected
	 * @param actual
	 * @return true if matches else false
	 */
	public Boolean matchFloatEquals(float expected, float actual) {
		try {
			assertThat(actual, equalTo(expected));
			logPass("Float Equal Match passed :Actual: " + actual + ", Expected: " + expected);
			return true;
		} catch (AssertionError e) {
			logFailure("Float Equal  Match failed : Actual: " + actual + ", Expected: " + expected);
			return false;

		}
	}

	// API verification methods

	private static PrintStream ps;

	public static void printError(String message, AssertionError e) {
		ps = Utils.getPrintStream();
		ps.print("------------------ " + message + ": FAILED ------");
		ps.println(e.getMessage());
		ps.println();
	}

	/**
	 * status code verification
	 */
	public static boolean verifyStatusCode(Api api, int expectedStatusCode) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();
		try {
			int statuscode = resp.getStatusCode();
			assertThat(statuscode, is(expectedStatusCode));
			test.log(Status.PASS, "StatusCode verification PASSED " + " = " + statuscode);
			return true;
		} catch (AssertionError e) {
			printError("Status code verification", e);
			test.log(Status.FAIL,
					MarkupHelper.createLabel("StatusCode verification FAILED " + e.getMessage(), ExtentColor.RED));
			return false;
		}

	}

	/**
	 * provide status code with comma separated values Example : 201,409,200 status
	 * code verification
	 */
	public static boolean verifyMultipleStatusCode(Api api, String statusCodes) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();
		try {
			int statuscode = resp.getStatusCode();
			assertThat(statusCodes.split(","), hasItemInArray(String.valueOf(statuscode)));
			test.log(Status.PASS, "Status Code: " + statuscode + " exists in " + statusCodes);
			return true;
		} catch (AssertionError e) {

			printError("Status code verification", e);
			test.log(Status.FAIL,
					MarkupHelper.createLabel("StatusCode verification FAILED " + e.getMessage(), ExtentColor.RED));

			return false;
		}

	}

	/**
	 * Jsonpath existence
	 */
	public static boolean verifyJsonPathExistence(Api api, String jsonPath) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();
		try {
			String json = resp.getBody().asString();
			assertThat(json, hasJsonPath(jsonPath));
			test.log(Status.PASS, "Existence of jsonPath :- " + jsonPath);
			return true;
		} catch (AssertionError e) {
			printError("JsonPath existence verification", e);
			test.log(Status.FAIL, MarkupHelper.createLabel(
					"JsonPath existence verification FAILED: " + e.getMessage().substring(0, 80), ExtentColor.RED));
			return false;

		}

	}

	public static boolean verifyJsonPathContentMatch(Api api, String jsonPath, String expectedValue) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();
		try {
			String json = resp.getBody().asString();
			verifyJsonPathExistence(api, jsonPath);
			assertThat(json, hasJsonPath(jsonPath, equalTo(expectedValue)));
			test.log(Status.PASS, "JsonPath content::: " + jsonPath + ": = " + expectedValue);
			return true;
		} catch (AssertionError e) {
			printError("JsonPath content verification", e);
			test.log(Status.FAIL, MarkupHelper.createLabel("JsonPath content verification FAILED: " + e.getMessage(),
					ExtentColor.RED));
			e.printStackTrace(ps);
			return false;
		}

	}

	public static boolean verifyJsonPathContentMatch(Api api, String jsonPath, int expectedValue) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();

		try {
			String json = resp.getBody().asString();
			verifyJsonPathExistence(api, jsonPath);
			assertThat(json, hasJsonPath(jsonPath, equalTo(expectedValue)));
			test.log(Status.PASS, "JsonPath content::: " + jsonPath + ": = " + expectedValue);
			return true;

		} catch (AssertionError e) {

			printError("JsonPath content verification", e);
			test.log(Status.FAIL, MarkupHelper.createLabel("JsonPath content verification FAILED : " + e.getMessage(),
					ExtentColor.RED));
			return false;
		}

	}

	public static boolean verifyObjects(Api api, Object actual, Object expected) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();
		try {

			assertThat(actual, equalTo(expected));
			test.log(Status.PASS, actual + "=" + expected);
			return true;

		} catch (AssertionError e) {

			printError(" verification failed", e);
			test.log(Status.FAIL, MarkupHelper.createLabel("verification FAILED : " + e.getMessage(), ExtentColor.RED));
			return false;
		}

	}

	public static String getJsonPathValue(Api api, String jsonPath) {
		String value = null;
		Response resp = api.getResp();
		ExtentTest test = api.getNode();
		if (verifyJsonPathExistence(api, jsonPath)) {
			value = com.jayway.jsonpath.JsonPath.read(resp.getBody().asString(), jsonPath).toString();
			test.log(Status.PASS, "Json Value from::: " + jsonPath + ": = " + value);
		} else {
			test.info("Unable to extract JsonPath Value from Jsonpath:-" + jsonPath);
		}
		return value;
	}

	public static Boolean verifyTextContainsAPI(Api api, String expected, String actual, String message) {
		ExtentTest test = api.getNode();
		try {
			assertThat(actual, containsString(expected));
			test.log(Status.PASS, "Text Contains Verification passed :" + message + ", Actual: " + actual
					+ ", Expected: " + expected);
			return true;
		} catch (AssertionError e) {
			printError("Text Contains verification", e);
			test.log(Status.FAIL,
					MarkupHelper.createLabel("Text Contains verification FAILED : " + e.getMessage(), ExtentColor.RED));
			return false;

		}
	}

	public static boolean verifyXMLPathContentMatch(Api api, String xmlPath, String expectedValue, String message) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();

		try {
			String actualValue = resp.xmlPath().getString(xmlPath).trim();
			assertThat(actualValue, equalTo(expectedValue));
			test.log(Status.PASS, "XML Content Verification passed :" + message + ", Actual: " + actualValue
					+ ", Expected: " + expectedValue);
			return true;

		} catch (AssertionError e) {

			printError("XMLPath content verification", e);
			test.log(Status.FAIL, MarkupHelper.createLabel(
					"XML content Verification : " + message + "  FAILED : " + e.getMessage(), ExtentColor.RED));
			return false;
		}

	}

	public static boolean verifyJsonContentNotNull(Api api, String jsonPath, String message) {

		Response resp = api.getResp();
		ExtentTest test = api.getNode();

		try {
			String actualValue = com.jayway.jsonpath.JsonPath.read(resp.getBody().asString(), jsonPath).toString();
			assertThat(actualValue, notNullValue());
			test.log(Status.PASS, "JSON Value from ::: " + jsonPath + ": = " + actualValue);
			return true;

		} catch (AssertionError e) {
			printError("JSONPath content verification", e);
			test.log(Status.FAIL,
					MarkupHelper.createLabel(
							"JSON content Value :  " + message + " IS NULL. Error Message : " + e.getMessage(),
							ExtentColor.RED));
			return false;
		}

	}

	public static boolean verifyXmlContentNotNull(Api api, String xmlPath, String message) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();

		try {
			String actualValue = resp.xmlPath().getString(xmlPath);
			assertThat(actualValue, notNullValue());
			test.log(Status.PASS, "XML Value from ::: " + xmlPath + ": = " + actualValue);
			return true;

		} catch (AssertionError e) {
			printError("XMLPath content verification", e);
			test.log(Status.FAIL,
					MarkupHelper.createLabel(
							"XML content Value : " + message + " IS NULL.  Error Message  : " + e.getMessage(),
							ExtentColor.RED));
			return false;
		}

	}

	public static void verifyResponseTime(Api api) {
		Response resp = api.getResp();
		ExtentTest test = api.getNode();
		long responseTime = resp.getTimeIn(TimeUnit.MILLISECONDS);
		test.info("Response time in ms " + " = " + responseTime);
		ThreadManager.dash.get().setComments("Response time " + " = " + responseTime + "ms");
	}

}
