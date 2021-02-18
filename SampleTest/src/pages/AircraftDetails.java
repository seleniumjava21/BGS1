package pages;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.CustomException;
import utilities.ThreadManager;


public class AircraftDetails extends BasePage {
	public AircraftDetails() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Boeing");
			
		}
	}
	
	
	@FindBy(xpath ="//a[@class='add-aircraft-button']") private WebElement addNewAircraft;
	@FindBy(id ="tailId") private WebElement aircrafttailid;
	@FindBy(id ="make") private WebElement aircraftmake;
	@FindBy(id ="model") private WebElement aircraftmodel;
	@FindBy(id ="serialNumber") private WebElement aircraftserialnumber;
	@FindBy(id ="yearManufactured") private WebElement aircraftyear;
	@FindBy(xpath ="//div[contains(@class,'checkmark stay')]") private WebElement checkterms;
	@FindBy(xpath ="//button[contains(@class,'btn btn-primary save-btn')]") private WebElement saveBtn;
	
	@FindBy(xpath ="//div[contains(@class,'aircraft-box-container')]//div[1]//form[1]//button[1]") private WebElement viewDetails;
	@FindBy(xpath ="//a[@class='button-secondary add-aircraft-button']") private WebElement addNewDevice;
	@FindBy(id ="device_manufacturer") private WebElement devicemanufacturer;
	@FindBy(id ="device_model") private WebElement devicemodel;
	@FindBy(id ="system_id") private WebElement systemid;
	@FindBy(xpath="//div[@class='aircraft-tail']") private List<WebElement> tailvalue;	
	@FindBy(xpath = "//div[@class='aircraft-box']/div[1]") private List<WebElement> exisitngAircraftDetails;
	@FindBy(xpath = "//div[@class='aircraft-box']") private List<WebElement> tailDetails;
	String details = "//div[@class='aircraft-box']";
	String details2 = "//button[contains(text(),\"View Details\")]";
	@FindBy(xpath ="(//div[@class='device-content']/div[2])[1]") private WebElement detailView1;
	@FindBy(xpath ="(//div[@class='device-content']/div[2])[2]") private WebElement detailView2;
	@FindBy(xpath ="(//div[@class='device-content']/div[2])[3]") private WebElement detailView3;
	@FindBy(xpath = "//a[@title='Aircraft Details']")
	private WebElement aircraftDetails;
	@FindBy(xpath =  "(//div[@class='aircraft-no-of-devices'])[1]")
	private WebElement deviceCount;
	@FindBy(xpath =  "(//div[@class='added-device-box'])")
	private List<WebElement> deviceCount1;
	@FindBy(xpath =  "(//button[contains(text(),\\\"View Details\\\")])[1]")
	private WebElement devcieDetails;
	@FindBy(xpath =  "//a[@class='btn btn-secondary']") private WebElement cancelBtn;
	@FindBy(xpath ="//div[@class='alert alert-info alert-dismissable getAccAlert']") private WebElement addconfmsg;
	
	@FindBy(xpath = "//button[@class='button-primary buy-database-btn']")
	private WebElement buyDBBtn;
	private String tailadded = "Aircraft TailNumber is created";
	private String deviceadded = "Avionic has been successfully added";
	
	
	public AircraftDetails Add_New_Device(ConcurrentHashMap<String, String> aeroDetails) {
		action.clickWithClickableWait(viewDetails, "View details");
		action.clickWithClickableWait(addNewDevice, "Add New Device");
		action.clickWithClickableWait(cancelBtn, "Clicked Cancel button");
		action.clickWithClickableWait(addNewDevice, "Add New Device");
		inputNewDeviceInfoAndSave(aeroDetails);
		return this;
	}
	
	public BuyDatabasePage Navigate_To_BuyDB_Screen_From_AircraftDetails() {
		//action.clickWithClickableWait(aircraftDetails, "Click Aircraft details");
		action.clickWithClickableWait(viewDetails, "Clicked on View Details");
		action.clickWithClickableWait(buyDBBtn,"Buy database button click");
		return new BuyDatabasePage();
	}

	protected void inputNewDeviceInfoAndSave(ConcurrentHashMap<String, String> aeroDetails) {
		action.selectByVisibleText(devicemanufacturer, aeroDetails.get("devicemanufacturer"), "Device Manufacturer");
		action.selectByVisibleText(devicemodel, aeroDetails.get("devicemodel"), "Device model");
		String deviceId = utils.getLocalTime();		
		action.inputText(systemid, "System Id",deviceId );
		action.clickWithClickableWait(saveBtn, "Save button");
		action.waitForElementDisplayed(addconfmsg, "Message banner");
		verify.verifyTextMatchIgnoringCase(deviceadded, action.getText(addconfmsg, "Message Banner Text"), "New Device added message");
	}
	
	public AircraftDetails verify_aircraft_device_match()  {
		try {
			action.jsClick(aircraftDetails, "Aircraft Details");			
			String Text = action.getTextWithoutLogging(deviceCount);
			Text = Text.replaceAll("[^\\d.]", "");
			int deviceCount = Integer.parseInt(Text);
			action.clickAndIgnoreFailure(devcieDetails, "device details button");		
			int deviceDisplayed = deviceCount1.size();
			if (deviceCount != deviceDisplayed) {
				ThreadManager.logger.get()
				.fail(MarkupHelper.createLabel("Device count doesnt match with devices displayed ",
						ExtentColor.RED));
				
			} else {
				ThreadManager.logger.get().info("devices match");				
			}

		} catch (Exception e) {
			ThreadManager.logger.get().info("Could not find device count");	
		}
		
		return this;
	}
	
	public AircraftDetails Add_New_Aircraft(ConcurrentHashMap<String, String> aeroDetails) {
		String randomString = utils.getTimestamp();		
		action.clickWithClickableWait(addNewAircraft, "Add new aircraft");
		action.clickWithClickableWait(cancelBtn, "Clicked Cancel button");
		action.clickWithClickableWait(addNewAircraft, "Add new aircraft");
		inputAircraftInfoAndSave(aeroDetails, randomString);
		action.refreshCurrentPage();
		GoTo_AircraftDetails();
		try {			
			for (WebElement element : exisitngAircraftDetails) {
				String actualTailId = action.getTextWithoutLogging(element);				
				if (actualTailId.equals(randomString)) {					
					ThreadManager.logger.get().info("Tail number is displayed");					
					break;
				}
			}
		} catch (Exception e) {
			throw new CustomException("Created Tail Id Not Found");
		}
		try {
			for (int i = 1; i <= tailDetails.size(); i++) {
				By Details = By.xpath(details + "[" + i + "]");
				String Detail = action.getTextWithoutLogging(Details);				
				By Details2 = By.xpath(details2 + "[" + i + "]");
				action.findElement(Details2, "Details").click();				
				String detailview1 = action.getTextWithoutLogging(detailView1);
				String detailview2 = action.getTextWithoutLogging(detailView2);
				String detailview3 = action.getTextWithoutLogging(detailView3);	
			
				
				if ((Detail.contains(detailview1)) && (Detail.contains(detailview2))
						&& (Detail.contains(detailview3))) {
					ThreadManager.logger.get().info("List values match both on aircraft and details page");								
				} else
					ThreadManager.logger.get().info("doesnt match");					
				continue;
			}

		} catch (Exception e) {
			ThreadManager.logger.get().info("Could not find tail details");
		
		}
		GoTo_AircraftDetails();		
		
		return this;
	}

	protected void inputAircraftInfoAndSave(ConcurrentHashMap<String, String> aeroDetails, String randomString) {
		action.inputText(aircrafttailid, "Aircraft Tail Id", randomString);
		action.selectByVisibleText(aircraftmake, aeroDetails.get("aircraftMake"), "Aircraft make");
		action.selectByVisibleText(aircraftmodel, aeroDetails.get("aircraftModel"), "Aircraft model");
		action.inputText(aircraftserialnumber, "Aircraft serial number", randomString);
		action.inputText(aircraftyear, "Aircraft year", aeroDetails.get("aircraftYear"));
		action.clickWithClickableWait(checkterms, "terms of use checkbox");
		action.clickWithClickableWait(saveBtn, "Save button");
		action.waitForElementDisplayed(addconfmsg, "Message banner");
		verify.verifyTextMatchIgnoringCase(tailadded, action.getText(addconfmsg, "Message Banner Text"), "Aircraft tail added message");
	}

	
}
