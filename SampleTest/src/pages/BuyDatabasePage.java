
package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.CustomException;
import utilities.PageAction;
import utilities.ThreadManager;

public class BuyDatabasePage extends BasePage {

	public BuyDatabasePage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		action = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Buy databases");

		}
	}

	@FindBy(linkText = "ADD NEW AIRCRAFT")
	private WebElement addNewAircraft;
	@FindBy(xpath = "//div[@class='yCmsContentSlot buy-database-header']/div/h1")
	private WebElement buydbheader;
	@FindBy(xpath = "//div[@class='yCmsContentSlot buy-database-header']/div/p")
	private WebElement buydbtext;
	@FindBy(linkText = "ADD NEW AVIONIC")
	private WebElement addDevice;
	@FindBy(xpath = "//i[@class='material-icons oval active']")
	private WebElement blueDot;
	@FindBy(id = "Aircraft")
	private WebElement selectBuyaircraft;

	@FindBy(id = "aircraft")
	private WebElement selectAircraft;
	@FindBy(xpath = "//ul[@class='devices']")
	private WebElement devices;
	@FindBy(xpath = "//div[@class='empty-devices']/div")
	private WebElement noDeviceText;
	@FindBy(xpath = "//ul[@class='devices']/li")
	private List<WebElement> deviceList;
	@FindBy(xpath = "//button[@data-service='YR']")
	private WebElement annualBtn;
	@FindBy(xpath = "//button[@data-service='MON']")
	private WebElement monthlyBtn;
	@FindBy(xpath = "//button[@data-service='1TM']")
	private WebElement oneTimeBtn;
	@FindBy(xpath = "//div[contains(@class,'plan-msg m-0')]")
	private WebElement saveMoreLbl;
	@FindBy(xpath = "//li[@class='bundles-tab width-mobile-50 p-mobile-1 m-mobile-0 active']")
	private WebElement bundleAvionics;
	@FindBy(xpath = "//h2[contains(text(),'Individual Avionics Databases')]")
	private WebElement individualAvionics;
	@FindBy(xpath = "//button[@class='button-primary checkout-btn']")
	private WebElement checkoutBtn;
	String prdList = "//ul[@class='products-container']/li";
	By productList = By.xpath(prdList);
	String btn = "//button[@id='addToCartButton']";
	By addToCarBtn = By.xpath(btn);
	@FindBy(xpath = "//div[@class='panel panel-default']//div[@class='panel-collapse collapse in']")
	private WebElement category1;

	@FindBy(xpath = "//span[@class='selectedNum selectedlength']")
	private WebElement productSelectedText;
	@FindBy(xpath = "//p[text()='Product already present in cart']")
	private WebElement productExistsMsg;
	@FindBy(xpath = "(//div[@class='panel-collapse collapse in']//a[@class='product__list--name'])[1]")
	private WebElement productName;
	String Coverage = "VIEW COVERAGE";
	@FindBy(xpath = "//button[@class='modal-content']")
	private WebElement coverageDialog;
	@FindBy(xpath = "//div[@class='product-name']")
	private WebElement productCoverageDialog;
	@FindBy(xpath = "//div[@class='part-num']")
	private WebElement partnumCoverage;
	@FindBy(xpath = "//div[@class='term']")
	private WebElement termCoverage;
	@FindBy(xpath = "//div[@class='description']")
	private WebElement deviceDescCoverage;
	@FindBy(xpath = "//button[@class='close']")
	private WebElement closeCoverageDialog;
	@FindBy(xpath = "//div[@class='panel-collapse collapse in']/div/ul/li/a/img")
	private List<WebElement> coverageImages;

	@FindBy(linkText = "ADD NEW AIRCRAFT")
	private WebElement addnewaircraft;
	@FindBy(xpath = "//select[@id='aircraft']")
	private WebElement aircraftselect;
	@FindBy(xpath = "//select[@id='aircraft']/following-sibling::label")
	private WebElement selectaircraftdropdown;
	@FindBy(xpath = "//i[@class='material-icons oval active']/../div")
	private WebElement selectaircraftheader;
	String Addnewdevicelink = "ADD NEW AVIONIC";
	@FindBy(xpath = "//div[@class='associated-devices']/div/p")
	private WebElement devicesHeader;
	@FindBy(xpath = "//div[@class='empty-devices']/div")
	private WebElement nodevicetxt;
	@FindBy(xpath = "//div[contains(text(),'To start purchasing databases please add your airc')]")
	private WebElement noaircraftText;
	@FindBy(xpath = "//h1[@class='panel-title']/a")
	private List<WebElement> categoryNames;

	@FindBy(xpath = "(//a[@class='item__name'])[1]")
	private WebElement catgOrderpage;
	@FindBy(xpath = "//div[@class='coverage']")
	private WebElement coverageOrderpage;
	@FindBy(xpath = "//div[@class='part-num']")
	private WebElement partnumOrderpage;
	@FindBy(xpath = "//div[@class='term']")
	private WebElement termOrderpage;
	@FindBy(xpath = "//div[@class='device']")
	private WebElement deviceOrderpage;
	@FindBy(xpath = "//div[@class='systemid']")
	private WebElement sysidOrderpage;

	@FindBy(xpath = "(//div[@class='product-name'])[1]")
	private WebElement catgOrderHistorypage;
	@FindBy(xpath = "(//div[@class='shipping-status'])[1]")
	private WebElement shipstatusHistorypage;

	@FindBy(xpath = "//div[@class='pb-1 pdp-code-num m-0 ml-1-5 mt-1 ml-mobile-1 mt-mobile-4p ml-tablet-2-5']")
	private WebElement messageForOneTime;

	// bundles
	@FindBy(xpath = "//div[@class='bundle-name mt-1']")
	private List<WebElement> bundleDeviceTitle;
	@FindBy(xpath = "//div[@class='bundle-coverage']")
	private List<WebElement> bundleCoverage;
	@FindBy(xpath = "//div[@class='bundle-price']")
	private List<WebElement> bundlePrice;

	String bundleItm = "(//div[@class='tab-pane pl-2 active']//div[@class='pb-1 bundle-item'])";
	By bundleItem = By.xpath(bundleItm);

	String versionDtls = "//div[@class='fieldset-name pb-1 ']";
	By versionDetails = By.xpath(versionDtls);

	String viewBundleCoverage = "//div[@class='term pl-6 pb-1']";

	String coverageExtn = "/span";

	String viewBundleAvailOptn = "/div/span[@class='available-opt']";

	String versionRows = "((//div[@class='contentalignment pb-mobile-1'])";

	String versionSelection = "//span[@class='d-flex'])";

	@FindBy(xpath = "//div[@class='select-version-content']")
	private List<WebElement> selectionVersionRows;

	@FindBy(id = "bundleProduct")
	private List<WebElement> btnBundleAndSave;

	@FindBy(id = "modalCancelButton")
	private WebElement bundNsaveVersionPopUpCancel;

	@FindBy(xpath = "//span[@class='d-flex']")
	private List<WebElement> btnSoftwareVer;
	@FindBy(xpath = "//div[@class='content d-flex']//strong")
	private WebElement versionPopUpDevice;

	@FindBy(xpath = "//div[@class='modal-content pr-2 pl-2']")
	private WebElement versionModelPopUp;

	@FindBy(id = "continueButton")
	private WebElement continueButton;
	@FindBy(xpath = "//span[@class='included-devices']")
	private List<WebElement> includeDevices;
	@FindBy(xpath = "(//a[@href='/contactUs'])[1]")
	private WebElement contactUSLink;
	@FindBy(xpath = "//p[@class='letter-spacing-1 emptyBundleMessage plr-25p']")
	private WebElement contactUsText;

	@FindBy(xpath = "//div[@class='contact__headline']")
	private WebElement contactUSPage;
	@FindBy(xpath = "//ul[@class='nav nav-tabs']")
	private WebElement contactUSPageTabs;
	@FindBy(xpath = "//div[text()='A bundle for this aircraft is already in your cart']")
	private WebElement alreadyexitsText1;
	@FindBy(xpath = "//div[text()='A bundle for this aircraft is already in your cart. Please remove to start over or purchase individual avionic databases']")
	private WebElement alreadyexitsText2;

	@FindBy(xpath = "//div[@class='bundle-view-details mt-2']")
	private List<WebElement> viewBundleDetails;

	@FindBy(xpath = "//ul[@class='nav nav-pills nav-stacked font-weight-bold hidden-xs']/li")
	private List<WebElement> multipleBundleDetails;
	@FindBy(xpath = "//div[@class='details-header details-header-bundle font-weight-bold pb-1 pl-1']")
	private WebElement viewBundleDetailsHeader;
	@FindBy(xpath = "//div[@class='tab-pane pl-2 active']//div[@class='details-header font-weight-bold pt-1 pb-1 pl-mobile-0 view-device-details device-name']")
	private WebElement viewBundleDetailsDevice;
	@FindBy(xpath = "//div[@class='tab-pane pl-2 active']//div[@class='coverage-details']")
	private WebElement viewBundleDetailsCoverage;
	@FindBy(xpath = "//div[@class='tab-pane pl-2 active']//div[@class='pb-1 bundle-item']")
	private List<WebElement> viewBundleDetailsIncludedDB;
	@FindBy(xpath = "(//div[@class='bundle-databases']/ul)[1]/li")
	private List<WebElement> includedDB;

	@FindBy(xpath = "//div[@class='tab-pane pl-2 active']//img[@class='center-block coverage-image']")
	private WebElement viewBundleImage;
	@FindBy(xpath = "//div[@class='tab-pane pl-2 active']//div[@class='part-num']/span")
	private WebElement viewBundlePartNum;

	@FindBy(xpath = "//div[@class='tab-pane pl-2 active']//div[@class='span d-flex pl-4 version-details']/div")
	private List<WebElement> viewBundleVersion;

	@FindBy(xpath = "//span[@class='d-flex']/strong")
	private List<WebElement> versionSelectionList;

	@FindBy(xpath = "//button[@class='font-weight-normal close mt-1 mr-1 mb-1']")
	private WebElement viewBundleClose;

	@FindBy(xpath = "//button[@class='close' and text()='x']")
	private WebElement versionSelectionClose;

	@FindBy(xpath = "(//ul[contains(@id,'tab-section')]//h2)[1]")
	private WebElement LBLAvionicBundles;
	@FindBy(xpath = "(//button[contains(@id,'bundleProduct')])[1]")
	private WebElement BTNBundleNSave;
	@FindBy(xpath = "(//span[contains(@class,'included-devices')])[1]")
	private WebElement LBLIncludedDevices;

	public BuyDatabasePage Validate_ViewBundleDetails() {

		long partNum1 = 0;
		long partNum2;

		for (WebElement viewBundlesLink : viewBundleDetails) {
			action.waitForElementClickable(viewBundlesLink);
			action.clickWithClickableWait(viewBundlesLink, "viewBundleDetails");
			verify.isElementDisplayed(viewBundleDetailsHeader, "viewBundleDetailsHeader");
			for (WebElement webElement : multipleBundleDetails) {
				action.switchToDefaultContent();
				action.waitForElementClickable(webElement);
				action.clickWithClickableWait(webElement, "Bundle list on list pane");
				String bundleonTabText = action.getText(webElement, "Bundle list value on list pane");
				String bundleDevice = action.getText(viewBundleDetailsDevice, "Device name on view details");
				verify.verifyTextMatch(bundleonTabText, bundleDevice, "Bundle device value matches");
				verify.isElementDisplayed(viewBundleDetailsCoverage, "Header coverage details");
				verify.isElementDisplayed(viewBundleImage, "viewBundleImage");
				for (WebElement webElement2 : viewBundleDetailsIncludedDB) {
					verify.isElementDisplayed(webElement2, "Included DB's on list");
				}
				if (partNum1 == 0) {
					String PN1 = action.getText(viewBundlePartNum, "viewBundlePartNum");
					partNum1 = Integer.parseInt(PN1);
				} else {
					String PN2 = action.getText(viewBundlePartNum, "viewBundlePartNum");
					partNum2 = Integer.parseInt(PN2);
					if (partNum2 != partNum1) {
						ThreadManager.logger.get().info("scenario is valid : Part num's should not match");
					}
				}

			}
			action.clickWithClickableWait(viewBundleClose, "close the view bundle details");

		}

		return this;

	}

	public BuyDatabasePage Validate_VersionSelection(String Coverage) {
		List<String> versionListOnViewBundles = new ArrayList<String>();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Coverage", Coverage);
		int i = 0;
		for (WebElement e : bundleCoverage) {
			String title = action.getText(bundleDeviceTitle.get(i), "Bundle title");
			if (action.matchTextContains(title, "G1000H")) {
				i++;
			} else {
				String CoverageOnPage = action.getText(bundleCoverage.get(i), "bundleCoverage");
				if (action.matchTextEquals(Coverage, CoverageOnPage)) {
					action.waitForElementClickable(viewBundleDetails.get(i));
					action.clickWithClickableWait(viewBundleDetails.get(i), "viewBundleDetails");
					for (WebElement webElement : multipleBundleDetails) {
						action.switchToDefaultContent();
						action.waitForElementClickable(webElement);
						action.clickWithClickableWait(webElement, "Bundle list on list pane");
						List<WebElement> bitem = action.findElements(bundleItem, "bundle items which have version");
						for (int j = 1; j <= bitem.size(); j++) {
							By bundleAvailVersion = By.xpath(bundleItm + "[" + j + "]" + viewBundleAvailOptn);
							Boolean versionAvailExists = action.isElementExistsIgnore(bundleAvailVersion);

							if (versionAvailExists) {
								List<WebElement> bundleAvailVersions = action.findElements(bundleAvailVersion,
										"bundle items with available option");
								for (int k = 1; k <= bundleAvailVersions.size(); k++) {
									By bundleVersion = By.xpath(bundleItm + "[" + j + "]" + versionDtls);
									Boolean versionExists = verify.isElementExistsIgnore(bundleVersion,
											"bundleVersion");
									if (versionExists) {
										List<WebElement> bundleVersions = action.findElements(bundleVersion,
												"bundle items with available option");
										for (int l = 0; l < bundleVersions.size(); l++) {
											String version = action.getText(bundleVersions.get(l),
													"version available on view bundle");
											versionListOnViewBundles.add(version);
										}
									}
								}
							} else {
								action.logInfo("No version available for database");
								// break;

							}
						}
					}
					action.clickWithClickableWait(viewBundleClose, "viewBundleClose");
					action.switchToDefaultContent();
					if (!versionListOnViewBundles.isEmpty()) {
						action.clickWithClickableWait(btnBundleAndSave.get(i), "Bundle Save button");
						action.waitForElementDisplayedIgnore(versionModelPopUp, "checks version pop up display");
						action.waitForElementDisplayedIgnore(versionPopUpDevice, "checks for device text on pop up");
						if (action.isElementExistsIgnore(versionModelPopUp)) {
							action.switchToDefaultContent();
							if (versionSelectionList.size() != 0) {
								for (int k = 0; k < versionSelectionList.size(); k++) {
									String version = action.getText(versionSelectionList.get(k), "Bundle title");
									if (version.isEmpty()) {
										action.logFailure("Version text should not be null.");
									}
									if (versionListOnViewBundles.contains(version)) {
										action.clickWithClickableWait(versionSelectionList.get(k), "version value");
										action.logInfo(
												"Version list on view bundles matches version on bundle and save");
									} else {
										action.logFailure(
												"Version details on View details dont match version Pop up values");
									}

								}
								action.scrollToElement(versionSelectionClose, "versionSelectionClose");
								action.waitForElementDisplayed(versionSelectionClose, "Close button");
								// if(versionSelectionClose.isDisplayed()) {
								action.jsClick(versionSelectionClose, "Bundle Save button");
							}

							action.switchToDefaultContent();
						}
						break;
					} else {
						action.logInfo(
								"Since no versions available for given combination skipping version up check on Bundle and Save");
						break;
					}
				}

			}

			i++;
		}
		return this;
	}

	public CartPage Select_Bundles_With_This_Device(String device, String Coverage) {
		boolean deviceMatch = selectDeviceNonConfig(device, Coverage);
		if (deviceMatch) {
			selectDeviceConfig(device);
			return new CartPage();
		} else {
			throw new CustomException("Required device bundle:- " + device + " not found in this Buy Database page");
		}

	}

	private void selectDeviceConfig(String device) {
		try {

			action.waitForElementDisplayedIgnore(versionModelPopUp, "checks version pop up display");
			action.waitForElementDisplayedIgnore(versionPopUpDevice, "checks for device text on pop up");
			if (action.isElementExistsIgnore(versionModelPopUp)) {
				action.switchToDefaultContent();
				int i = 1;
				for (int j = 1; j <= selectionVersionRows.size(); j++) {
					By bundleVersion = By.xpath(versionRows + "[" + j + "]" + versionSelection + "[" + i + "]");
					action.clickWithClickableWait(bundleVersion, "clicks version of each DB available on pop up");
				}

				action.clickWithClickableWait(continueButton, "Continue buttn");

			}
		} catch (Exception e) {
			action.logInfo("Software version choosing panel didnt appear");

		}
	}

	private boolean selectDeviceNonConfig(String device, String Coverage) {
		action.waitForElementsDisplayed(bundleDeviceTitle, "Bundle Device Title");
		boolean deviceMatch = false;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<String> deviceBDB = new ArrayList<>();
		hm.put("device", device);
		hm.put("CoverageName", Coverage);
		int i = 0;
		for (WebElement e : includeDevices) {
			String title = action.getText(bundleDeviceTitle.get(i), "Bundle title");
			String coverageOnpage = action.getText(bundleCoverage.get(i), "Bundle title");
			if (action.matchTextContains(title, "G1000H")) {
				i++;
			} else {
				String bundleDeviceText = action.getAttribute(e, "innerText", "text value");
				bundleDeviceText = bundleDeviceText.replace("check_circle_outline", "");
				bundleDeviceText = bundleDeviceText.replace("Includes ", "");
				if (action.matchTextContains(device, bundleDeviceText)
						&& action.matchTextEquals(Coverage, coverageOnpage)) {
					String str[] = bundleDeviceText.split(",");

					deviceBDB = Arrays.asList(str);

					hm.put("coverage", action.getText(bundleCoverage.get(i), "Bundle Coverage"));
					String[] bundlePriceTerm = action.getText(bundlePrice.get(i), "Bundle Price and Term")
							.split("Annually");
					hm.put("price", bundlePriceTerm[0]);
					hm.put("term", "Annual");
					action.logInfo("bundle place holder data - " + hm);
					action.clickWithClickableWait(btnBundleAndSave.get(i), "Bundle Save button");
					ThreadManager.placeholder.get().setHashmap(hm);
					ThreadManager.placeholder.get().setArrayList(deviceBDB);
					deviceMatch = true;
					break;
				}
				i++;

			}
		}
		return deviceMatch;
	}

	public CartPage Error_Validation_On_Bundles_Selection() {
		int i = 0;
		action.clickWithClickableWait(btnBundleAndSave.get(i), "Bundle Save button");
		verify.isElementDisplayed(alreadyexitsText1, "alreadyexitsText1");
		verify.isElementDisplayed(alreadyexitsText2, "alreadyexitsText2");

		action.logInfo("Another bundle is already in cart");

		return new CartPage();
	}

	public BuyDatabasePage Validate_BuyDatabase_with_aircraft(ConcurrentHashMap<String, String> aircraftDetails) {
		verify.isElementDisplayed(addnewaircraft, "Add New Aircraft button");
		verify.verifyTextFromElementIgnoringCase(buydbheader, aircraftDetails.get("buydbHeader"),
				"Buy Database header text");
		verify.verifyTextFromElementIgnoringCase(buydbtext, aircraftDetails.get("buydbText"), "Buy Database text");
		verify.verifyTextFromElementIgnoringCase(selectaircraftheader, aircraftDetails.get("selectAircraft"),
				"Validate select aircraft header");
		verify.isElementDisplayed(selectaircraftdropdown, "Select aircraft dropdown");
		action.selectFirstValue(selectAircraft, "Select aircraft");
		verify.verifyTextFromElementIgnoringCase(devicesHeader, aircraftDetails.get("devicesHeader"),
				"Devices header validation");
		verify.verifyTextFromElementIgnoringCase(nodevicetxt, aircraftDetails.get("noDevicestext"),
				"No devices text validation");
		return this;
	}

	public BuyDatabasePage Validate_BuyDatabase_without_aircraft(ConcurrentHashMap<String, String> aircraftDetails) {
		verify.isElementDisplayed(addnewaircraft, "Add New Aircraft button");
		verify.verifyTextFromElementIgnoringCase(buydbheader, aircraftDetails.get("buydbHeader"),
				"Buy Database header text");
		verify.verifyTextFromElementIgnoringCase(buydbtext, aircraftDetails.get("buydbText"), "Buy Database text");
		verify.verifyTextFromElementIgnoringCase(noaircraftText, aircraftDetails.get("noAircrafttext"),
				"No aircraft text");
		return this;
	}

	public BuyDatabasePage Validate_Message_For_OneTime_Purchase() {
		action.waitForElementVisibility(messageForOneTime, "message displayed for one time on buy databse page");
		verify.verifyTextMatch(
				"For One Time your coverage begins today " + utils.Get_Current_Date() + " and will end on "
						+ utils.Get_OneTime_Date_From_Current() + ".",
				action.getText(messageForOneTime, "message"), "displayed message");

		return this;
	}

	public BuyDatabasePage Select_Your_Aircraft(ConcurrentHashMap<String, String> aeroDetails) {

		if (!action.isElementExistsIgnore(selectAircraft, "Select Aircraft")) {
			action.logInfo("Select Aircraft drop down does not exists");
			addAircraftAndDevice(aeroDetails);
		}
		action.logInfo("Select Aircraft drop down exists");
		String txt = action.isThisOptionExistsDropDown(selectAircraft, aeroDetails.get("aircraftMake"),
				"Select Aircraft dropdown");
		if (txt == null) {
			addAircraftAndDevice(aeroDetails);
		} else {
			action.selectByVisibleText(selectAircraft, txt, "Select Aircraft dropdown");
		}
		if (action.isElementExistsIgnore(noDeviceText, "No Device text")
				|| action.isElementsExistsIgnore(deviceList, "device list")) {
			List<String> deviceName = new ArrayList<String>();
			for (WebElement e : deviceList) {
				deviceName.add(action.getText(e, "Device name"));
			}
			String dlist = aeroDetails.get("deviceMakeModel");
			action.logInfo("Device list from test data :- " + dlist);
			String str[] = dlist.split(",");
			List<String> al = new ArrayList<String>();
			al = Arrays.asList(str);
			for (String s : al) {
				ConcurrentHashMap<String, String> deviceInfo = new ConcurrentHashMap<String, String>();
				String[] split = s.split("-");
				deviceInfo.put("devicemanufacturer", split[0]);
				deviceInfo.put("devicemodel", split[1]);
				if (!deviceName.contains(split[1])) {
					addMultipleDevices(deviceInfo);
					txt = action.isThisOptionExistsDropDown(selectAircraft, aeroDetails.get("aircraftMake"),
							"Select Aircraft dropdown");
					action.selectByVisibleText(selectAircraft, txt, "Select Aircraft dropdown");
				}
			}
		} else {
			throw new CustomException("Neither device list nor No device message appears");
		}

		return this;
	}

	private void addMultipleDevices(ConcurrentHashMap<String, String> deviceInfo) {
		action.clickWithClickableWait(addDevice, "Add New Device");
		AircraftDetails ac = new AircraftDetails();
		ac.inputNewDeviceInfoAndSave(deviceInfo);
		ac.Navigate_Buy_Database();
	}

	private void addAircraftAndDevice(ConcurrentHashMap<String, String> aeroDetails) {
		action.clickWithClickableWait(addNewAircraft, "Add New Aircraft button");
		AircraftDetails ac = new AircraftDetails();
		ac.inputAircraftInfoAndSave(aeroDetails, utils.getTimestamp());
		ac.Navigate_Buy_Database();
		String txt = action.isThisOptionExistsDropDown(selectAircraft, aeroDetails.get("aircraftMake"),
				"Select Aircraft dropdown");
		action.selectByVisibleText(selectAircraft, txt, "Select Aircraft dropdown");
		action.clickWithClickableWait(addDevice, "Add New Device");
		ac = new AircraftDetails();
		ac.inputNewDeviceInfoAndSave(aeroDetails);
		ac.Navigate_Buy_Database();
		txt = action.isThisOptionExistsDropDown(selectAircraft, aeroDetails.get("aircraftMake"),
				"Select Aircraft dropdown");
		action.selectByVisibleText(selectAircraft, txt, "Select Aircraft dropdown");
		action.waitForElementDisplayed(devices, "device list");

	}

	public BuyDatabasePage Choose_Fly_Location(String location) {
		WebElement e = action.findElement(By.xpath("//span[contains(text(),'" + location + "')]"), location);
		action.clickWithoutClickableWait(e, location + " check box");
		return this;
	}

	public BuyDatabasePage Select_Length_Of_Coverage_Annual() {
		selectLengthOfCoverage(annualBtn, "Annual");
		return this;
	}

	public BuyDatabasePage Select_Length_Of_Coverage_OneTime() {
		selectLengthOfCoverage(oneTimeBtn, "One Time");
		return this;
	}
	
	public BuyDatabasePage Select_Length_Of_Coverage_Monthly() {
		selectLengthOfCoverage(monthlyBtn, "monthlyBtn");
		return this;
	}


	//Method to verify the new monthly option and disclaimer text
	public BuyDatabasePage Verify_Length_Of_Coverga_Options()
	{
		verify.isElementDisplayed(annualBtn, "Annual Option");
		verify.isElementDisplayed(monthlyBtn, "Monthly Option");
		verify.isElementDisplayed(oneTimeBtn, "One Time Option");
		verify.verifyTextFromElementIgnoringCase(saveMoreLbl, "Save more with an annual coverage plan", "Save More with Annual");
		return this;
	}

	
	public BuyDatabasePage Select_Bundle_Avionics() {
		action.clickWithClickableWait(bundleAvionics, "Bundle Avionics");
		return this;
	}

	public BuyDatabasePage Disabled_AvionicDBBundle(ConcurrentHashMap<String, String> aircraftDetails) {

		verify.isElementNotDisplayed(bundleAvionics, "bundleAvionics");
		action.logInfo("Avionics Data base bundle is NOT available for this tail combination ");

		verify.isElementDisplayed(contactUSLink, "contactUSLink");
		action.logInfo("Contact US link is displayed on Bundles Tab ");
		String contactText = action.getText(contactUsText, "Contact Us Text");

		if (action.matchTextContains(aircraftDetails.get("contactUsText"), contactText)) {
			action.clickWithClickableWait(contactUSLink, "contactUSLink");
			action.switchToNewTab();
			verify.isElementDisplayed(contactUSPage, "new tab of contact us");
			verify.isElementDisplayed(contactUSPageTabs, "tabs on contact us page");

			action.logInfo("Contact US page is opened");

		}
		action.switchToParentTab();

		return this;
	}

	public BuyDatabasePage Select_Individual_Avionics() {
		action.clickWithClickableWait(individualAvionics, "Individual Avionics");
		return this;
	}

	private void selectLengthOfCoverage(WebElement element, String lengthOfCoverage) {
		action.waitForElementVisibility(element, lengthOfCoverage + "button");
		action.clickWithClickableWait(element, lengthOfCoverage + "button");
	}

	public BuyDatabasePage Select_Device(String device) {
		WebElement ele = action.findElement(By.xpath("//button[(text()='" + device + "')]"), device + " button");
		action.clickWithClickableWait(ele, device + " button");
		return this;
	}

	public BuyDatabasePage Validate_Coverage(String term) {
		String txtprdname = action.getText(productName, "Product name before click on View coverage");
		action.logInfo("Before coverage popup prdtxt" + txtprdname);
		action.clickLinkWithoutScroll(Coverage);
		action.switchToDefaultContent();
		action.waitUntilTextVisibleInElement(productCoverageDialog, "Coverage dialog");
		verify.verifyTextFromElementIgnoringCase(productCoverageDialog, txtprdname,
				"Product name on Coverage validation");
		verify.waitUntilTextVisibleInElement(partnumCoverage, "Part number field");
		verify.verifyTextContains(term, action.getText(termCoverage, "Term text"),
				"Term validation on Coverage dialog");
		// verify.verifyTextContains(deviceval,action.getText(deviceDescCoverage,
		// "Device value"),"Device validation");
		action.clickWithClickableWait(closeCoverageDialog, "Close coverage dialog");
		action.waitForElementInvisibility(partnumCoverage);
		return this;

	}

	public BuyDatabasePage Add_AlaCarte_Product_To_Cart() {
		action.waitForElementDisplayed(category1, "Category 1 in buydb screen");
		List<WebElement> pl = action.findElements(productList, "product block");
		if( pl.size() == 1)
		{
			action.clickWithClickableWait(addToCarBtn, "Add to cart in category");
		}
		else
		{
		action.disableImplicitWait();
		int i = 1;
		for (WebElement e : pl) {
			action.scrollToElement(pl.get(i), "Product block:-" + i);
			By bttn = By.xpath(prdList + "[" + i + "]" + btn);
			WebElement ment = action.isRelativeElementExistsIgnore(e, bttn, "add to Cart button");
			if (ment != null) {
				action.clickWithClickableWait(bttn, "Click on add to cart button");
				verify.verifyTextFromElementIgnoringCase(productSelectedText, "1 SELECTED",
						"Product selected text after add to cart");
				action.resetImplicitWait();
				return this;
			} else {
				i++;
			}
		}
		throw new CustomException("Product with Add to Cart button not found in the category");
		}
		return this;
	}

	public BuyDatabasePage Verify_Category(String categoryname) {
		action.waitForElementDisplayed(category1, "Category 1 in buydb screen");
		int size = categoryNames.size();
		Boolean catgfound = false;
		action.logInfo("Total categories found in BuyDB page  :- " + size);
		if (size == 0) {
			throw new CustomException("Categories not found in BuyDB page");
		}
		for (WebElement e : categoryNames) {
			if (action.getText(e, "Category val").contains(categoryname)) {
				action.logPass("Given category is found in the page" + categoryname);
				catgfound = true;
				break;
			}
		}
		if (!catgfound) {
			action.logFailure("Given category is not found in the page" + categoryname);
		}
		return this;
	}

	public CartPage Validate_AlaCarte_Product_Info_InCart(ConcurrentHashMap<String, String> aeroDetails) {
		CartPage cart = Checkout_From_Alacarte();
		return cart.validateAlacarteInfo(aeroDetails);
	}

	public CartPage Checkout_From_Alacarte() {
		action.waitForElementEnabled(checkoutBtn, "Checkout button enable wait");
		action.clickWithClickableWait(checkoutBtn, "Checkout button");
		return new CartPage();
	}

	public BuyDatabasePage Verify_Tail_Prepopulated_BuyDB() {
		verify.isElementDisplayed(selectAircraft, "Select aircraft");
		if (action.getAttributeIgnoreEmpty(selectAircraft, "value", "aircraft") != null) {
			action.logInfo("Aircraft Number is pre-populated");
		} else {
			action.logInfo("Aircraft Number is not pre-populated. Need to add aircraft...");
		}
		return this;
	}

	public CartPage Add_Product_To_Cart_For_All_Category() {
		action.waitForElementsDisplayed(categoryNames, "categories");
		int i = 0;
		for (WebElement e : categoryNames) {
			String categoryText = action.getText(e, "category names");
			if (i != 0) {
				action.clickWithoutClickableWait(categoryNames.get(i), categoryText);
			}
			if (action.matchTextContains("NavData", categoryText)) {
				categoryText = "NavData _";
			}
			List<WebElement> ee = action.findRelativeElements(
					action.findElement(By.id(categoryText.replace(" ", "_")), "category product list panel"),
					By.id("addToCartButton"), "add to cart btn");
			action.clickWithoutScroll(ee.get(ee.size() - 1), "add to cart button of last product");
			i++;
		}
		return Checkout_From_Alacarte();

	}

	public BuyDatabasePage Add_Product_To_Cart_For_Specific_Category(String catgname) {
		action.waitForElementsDisplayed(categoryNames, "categories");
		int size = categoryNames.size();
		Boolean catgfound = false;
		action.logInfo("Total categories found in BuyDB page  :- " + size);
		if (size == 0) {
			throw new CustomException("Categories not found in BuyDB page");
		}
		int i = 0;
		for (WebElement e : categoryNames) {
			String categoryText = action.getText(e, "category names");
			if (action.matchTextContains("NavData", categoryText)) {
				categoryText = "NavData _";
			}

			if (action.matchTextContains(catgname, categoryText)) {
				action.scrollToElement(e, "Scroll to matched category");
				action.clickWithoutClickableWait(categoryNames.get(i), categoryText);
				action.logPass("Category match found and clicked  " + catgname);
				List<WebElement> ee = action.findRelativeElements(
						action.findElement(By.id(categoryText.replace(" ", "_")), "category product list panel"),
						By.id("addToCartButton"), "add to cart btn");
				action.clickWithoutScroll(ee.get(ee.size() - 2), "add to cart button of last product");

				catgfound = true;
				break;
			}
			i++;

		}
		if (!catgfound) {
			action.logFailure("Given category is not found in the page " + catgname);
		}
		return this;

	}

	public BuyDatabasePage Add_Same_AlaCarte_Product_To_Cart() {
		action.waitForElementDisplayed(category1, "Category 1 in buydb screen");
		List<WebElement> pl = action.findElements(productList, "product block");
		action.disableImplicitWait();
		int i = 1;
		for (WebElement e : pl) {
			action.scrollToElement(pl.get(i), "Product block:-" + i);
			By bttn = By.xpath(prdList + "[" + i + "]" + btn);
			WebElement ment = action.isRelativeElementExistsIgnore(e, bttn, "add to Cart button");
			if (ment != null) {
				action.clickWithClickableWait(bttn, "Add to cart button");
				action.waitUntilTextVisibleInElement(productExistsMsg, "Product exists");
				verify.verifyTextFromElementIgnoringCase(productExistsMsg, "Product already present in cart",
						"Product already exists in cart");
				action.resetImplicitWait();
				return this;
			} else {
				i++;
			}
		}
		throw new CustomException("Product with Add to Cart button not found in the category");
	}

	public BuyDatabasePage Add_AlaCarte_Product_Again_Deselect_Validation() {
		action.waitForElementDisplayed(category1, "Category 1 in buydb screen");
		List<WebElement> pl = action.findElements(productList, "product block");
		action.disableImplicitWait();
		int i = 1;
		for (WebElement e : pl) {
			action.scrollToElement(pl.get(i), "Product block:-" + i);
			By bttn = By.xpath(prdList + "[" + i + "]" + btn);
			WebElement ment = action.isRelativeElementExistsIgnore(e, bttn, "add to Cart button");
			if (ment != null) {
				action.clickWithoutScroll(bttn, "Add to cart button again");
				// verify.isElementNotDisplayed(productSelectedText, "Product selected text ala
				// carte");
				action.resetImplicitWait();
				return this;
			} else {
				i++;
			}
		}
		throw new CustomException("Product with Add to Cart button not found in the category");
	}

}
