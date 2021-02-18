
package base;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.path.json.JsonPath;
import pages.HomePage;
import utilities.CustomException;
import utilities.Reporting;
import utilities.ThreadManager;
import utilities.Utils;

public class BaseTest extends Reporting {

	protected HomePage home;
	
	
	

	// testData
	protected ConcurrentHashMap<String, String> basicAddress;
	protected ConcurrentHashMap<String, String> billingAddress;
	protected ConcurrentHashMap<String, String> newEUBillingAddress;
	protected ConcurrentHashMap<String, String> newEUFranceShippingAddress;
	protected ConcurrentHashMap<String, String> newEUGermanyShippingAddress;
	protected ConcurrentHashMap<String, String> newNZShippingAddress;
	protected ConcurrentHashMap<String, String> AUbilling;
	protected ConcurrentHashMap<String, String> addressAustralia;
	protected ConcurrentHashMap<String, String> AutoRenewal;
	protected ConcurrentHashMap<String, String> newEUGermanyShippingAddressSplchars;
	protected ConcurrentHashMap<String, String> newBrazilShippingAddressSplchars;
	protected ConcurrentHashMap<String, String> validUser;
	protected ConcurrentHashMap<String, String> forgetpassword;
	protected ConcurrentHashMap<String, Object> simpleProduct;
	protected ConcurrentHashMap<String, Object> configProduct;
	protected ConcurrentHashMap<String, String> visaCard3DS2NoFriction;
	protected ConcurrentHashMap<String, String> DiscoverCard3DS2NoFriction;
	protected ConcurrentHashMap<String, String> masterCard3DS2NoFriction;
	protected ConcurrentHashMap<String, String> amexCard3DS2NoFriction;
	protected ConcurrentHashMap<String, String> visaCard3DS2Friction;
	protected ConcurrentHashMap<String, String> DiscoverCard3DS2Friction;
	protected ConcurrentHashMap<String, String> masterCard3DS2Friction;
	protected ConcurrentHashMap<String, String> amexCard3DS2Friction;
	protected ConcurrentHashMap<String, String> visaCard3DS1Friction;
	protected ConcurrentHashMap<String, String> DiscoverCard3DS1Friction;
	protected ConcurrentHashMap<String, String> masterCard3DS1Friction;
	protected ConcurrentHashMap<String, String> ErrorVisaCardFriction;
	protected ConcurrentHashMap<String, String> amexCard3DS1Friction;
	protected ConcurrentHashMap<String, String> visaCard3DS1NoFriction;
	protected ConcurrentHashMap<String, String> DiscoverCard3DS1NoFriction;
	protected ConcurrentHashMap<String, String> masterCard3DS1NoFriction;
	protected ConcurrentHashMap<String, String> amexCard3DSNo1NoFriction;
	protected ConcurrentHashMap<String, String> expCard;
	protected ConcurrentHashMap<String, String> companyAccountInfo;
	protected ConcurrentHashMap<String, String> companyJAUAccountInfo;
	protected ConcurrentHashMap<String, String> existingCompanyAccountInfo;
	protected ConcurrentHashMap<String, String> EUCompanyAccountInfo;
	protected ConcurrentHashMap<String, String> personalAccountInfo;
	protected ConcurrentHashMap<String, String> EUpersonalAccountInfo;
	protected ConcurrentHashMap<String, String> existingPersonalAccountInfo;
	protected ConcurrentHashMap<String, String> existingPersonalAccountInfoAUS;
	protected ConcurrentHashMap<String, String> brazilAddressShipping;
	protected ConcurrentHashMap<String, String> NZBilling;
	protected ConcurrentHashMap<String, String> CanadaShipping;
	protected ConcurrentHashMap<String, String> CanadaBilling;
	protected ConcurrentHashMap<String, String> MexicoBilling;
	protected ConcurrentHashMap<String, String> aircraftDetails;
	protected ConcurrentHashMap<String, String> aircraftDetailsTier1;
	protected ConcurrentHashMap<String, String> tier1Proline21;
	protected ConcurrentHashMap<String, String> tier2Proline21;
	protected ConcurrentHashMap<String, String> tier3Proline21;
	protected ConcurrentHashMap<String, String> aircraftDetailsTier2;
	protected ConcurrentHashMap<String, String> aircraftDetailsTier3;
	protected ConcurrentHashMap<String, String> alacarteRegions;
	protected ConcurrentHashMap<String, String> alacarteLOS;
	protected ConcurrentHashMap<String, String> alacarteCategories;
	protected ConcurrentHashMap<String, String> addUser;
	protected ConcurrentHashMap<String, String> mySubscription;
	protected ConcurrentHashMap<String, Object> payinvoicedata;
	protected ConcurrentHashMap<String, String> searchtext;
	protected ConcurrentHashMap<String, String> changeEmailData;
	protected ConcurrentHashMap<String, String> savedCardMessage;
	protected ConcurrentHashMap<String, String> fulfillment;
	protected ConcurrentHashMap<String, String> bundlesTier1IFD5IFD4;
	protected ConcurrentHashMap<String, String> bundlesTier25WS4WS;
	
	protected ConcurrentHashMap<String, String> bundlesTier1G1K;
	protected ConcurrentHashMap<String, String> bundlesTier1G2k;
	protected ConcurrentHashMap<String, String> bundlesTier1G1KAIFD500;
	protected ConcurrentHashMap<String, String> bundlesTier3G3k;
	protected ConcurrentHashMap<String, String> bundlesTier1G1KG950;
	protected ConcurrentHashMap<String, String> bundlesTier1G1KH;
	protected ConcurrentHashMap<String, String> bundlesTier1GNxi;
	protected ConcurrentHashMap<String, String> bundlesTier1GNxiG3K;
	protected ConcurrentHashMap<String, String> bundlesTier1GTNseries;
	protected ConcurrentHashMap<String, String> bundlesTier1GTN7S;
	protected ConcurrentHashMap<String, String> bundlesTier1TXISeries;
	protected ConcurrentHashMap<String, String> bundlesTier1XISeries;
	protected ConcurrentHashMap<String, String> bundlesTier1GTN6SGTN6S;
	protected ConcurrentHashMap<String, String> bundlesTier1GTN6S;
	protected ConcurrentHashMap<String, String> bundlesTier1GPS175GNX375;
	protected ConcurrentHashMap<String, String> bundlesTier1GPS175GNX375A;
	protected ConcurrentHashMap<String, String> bundlesTier1GNC355A355;
	protected ConcurrentHashMap<String, String> bundlesTier3GPS175GTN6S;
	protected ConcurrentHashMap<String, String> bundlesTier1G6S7Xi6XiG1k;
	protected ConcurrentHashMap<String, String> bundlesTier1G3K175G7SGI275;
	protected ConcurrentHashMap<String, String> bundlesTier1IFD5EX4;
	protected ConcurrentHashMap<String, String> bundlesTier1IFD5G1K;
	protected ConcurrentHashMap<String, String> ordertype;

	@SuppressWarnings("unchecked")
	@BeforeClass
	// Intializing test data
	protected void intializeTestData() {
		JsonPath url = Utils.getEnvUrl();
		JsonPath shippingAddr = Utils.getShippingAddres();
		JsonPath login = Utils.getLogin();
		JsonPath product = Utils.getProduct();
		JsonPath paymentType = Utils.getPaymentType();
		JsonPath registration = Utils.getRegistration();
		JsonPath myAccount = Utils.getMyAccount();
		JsonPath aircraft = Utils.getAircraftDetails();
		JsonPath payinvoice = Utils.getPayInvoice();

		try {
			basicAddress = (ConcurrentHashMap<String, String>) shippingAddr.getObject("basicaddress[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			billingAddress = (ConcurrentHashMap<String, String>) shippingAddr.getObject("billingaddress[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			validUser = (ConcurrentHashMap<String, String>) login.getObject("validuser[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			forgetpassword = (ConcurrentHashMap<String, String>) login.getObject("forgetpassword[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			simpleProduct = (ConcurrentHashMap<String, Object>) product.getObject("simpleProduct[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			configProduct = (ConcurrentHashMap<String, Object>) product.getObject("configProduct[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			searchtext = (ConcurrentHashMap<String, String>) product.getObject("searchText[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			AutoRenewal = (ConcurrentHashMap<String, String>) paymentType.getObject("AutoRenewal[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			fulfillment = (ConcurrentHashMap<String, String>) product.getObject("fulfillment[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			addressAustralia = (ConcurrentHashMap<String, String>) shippingAddr.getObject("addressAustralia[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			newEUFranceShippingAddress = (ConcurrentHashMap<String, String>) shippingAddr.getObject("franceAddress[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			newEUGermanyShippingAddress = (ConcurrentHashMap<String, String>) shippingAddr.getObject("addressGermany[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			newNZShippingAddress = (ConcurrentHashMap<String, String>) shippingAddr.getObject("addressNewZealand[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			newEUGermanyShippingAddressSplchars = (ConcurrentHashMap<String, String>) shippingAddr.getObject("addressGermanysplchars[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			newBrazilShippingAddressSplchars = (ConcurrentHashMap<String, String>) shippingAddr.getObject("brazilbillSpeCharc[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			visaCard3DS2NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("VisaCard3DS2NF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			DiscoverCard3DS2NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("DiscoverCard3DS2NF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
            masterCard3DS2NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("MasterCard3DS2NF[0]",	Class.forName("java.util.concurrent.ConcurrentHashMap"));
			amexCard3DS2NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("AmericanExpressCard3DS2NF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			visaCard3DS2Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("VISACARD3DS2FF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			DiscoverCard3DS2Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("DiscoverCard3DS2FF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
            masterCard3DS2Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("MasterCard3DS2FF[0]",	Class.forName("java.util.concurrent.ConcurrentHashMap"));
			amexCard3DS2Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("AmericanExpressCard3DS2FF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			visaCard3DS1Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("VISACARD3DS1FF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			DiscoverCard3DS1Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("DiscoverCard3DS1FF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
            masterCard3DS1Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("MasterCard3DS1FF[0]",	Class.forName("java.util.concurrent.ConcurrentHashMap"));
            ErrorVisaCardFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("ErrorVisaCardFriction[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
            amexCard3DS1Friction = (ConcurrentHashMap<String, String>) paymentType.getObject("AmericanExpressCard3DS1FF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			visaCard3DS1NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("VISACARD3DS1NF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			DiscoverCard3DS1NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("DiscoverCard3DS1NF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
            masterCard3DS1NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("MasterCard3DS1NF[0]",	Class.forName("java.util.concurrent.ConcurrentHashMap"));
            amexCard3DSNo1NoFriction = (ConcurrentHashMap<String, String>) paymentType.getObject("AmericanExpressCard3DS1NF[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			expCard = (ConcurrentHashMap<String, String>) paymentType.getObject("expCard[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
            companyAccountInfo = (ConcurrentHashMap<String, String>) registration.getObject("companyAccountInfo[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			companyJAUAccountInfo = (ConcurrentHashMap<String, String>) registration.getObject("companyJAUAccountInfo[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
            personalAccountInfo = (ConcurrentHashMap<String, String>) registration.getObject("personalAccountInfo[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			existingCompanyAccountInfo = (ConcurrentHashMap<String, String>) registration.getObject("companyregister[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			EUCompanyAccountInfo = (ConcurrentHashMap<String, String>) registration.getObject("EUcompanyregister[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			EUpersonalAccountInfo = (ConcurrentHashMap<String, String>) registration.getObject("GermanypersonalAccountInfo[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			existingPersonalAccountInfo = (ConcurrentHashMap<String, String>) registration.getObject("personalregister[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			existingPersonalAccountInfoAUS = (ConcurrentHashMap<String, String>) registration.getObject("JAUpersonalregister[0]", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			newEUBillingAddress = (ConcurrentHashMap<String, String>) shippingAddr.getObject("newEUbillingaddress[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			brazilAddressShipping = (ConcurrentHashMap<String, String>) shippingAddr.getObject("BrazilBasicaddress[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			AUbilling = (ConcurrentHashMap<String, String>) shippingAddr.getObject("billingAustralia[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			NZBilling = (ConcurrentHashMap<String, String>) shippingAddr.getObject("billingNewZealand[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			CanadaBilling = (ConcurrentHashMap<String, String>) shippingAddr.getObject("CAbillingaddress[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			CanadaShipping = (ConcurrentHashMap<String, String>) shippingAddr.getObject("CAbasicaddress[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			MexicoBilling = (ConcurrentHashMap<String, String>) shippingAddr.getObject("MexicoBill2[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			aircraftDetails = (ConcurrentHashMap<String, String>) aircraft.getObject("aeroDetails[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			aircraftDetailsTier1 = (ConcurrentHashMap<String, String>) aircraft.getObject("tier1a",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			aircraftDetailsTier2 = (ConcurrentHashMap<String, String>) aircraft.getObject("tier2a",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			aircraftDetailsTier3 = (ConcurrentHashMap<String, String>) aircraft.getObject("tier3a",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			tier2Proline21=(ConcurrentHashMap<String, String>) aircraft.getObject("tier2Proline21",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			tier1Proline21=(ConcurrentHashMap<String, String>) aircraft.getObject("tier1Proline21",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			tier3Proline21=(ConcurrentHashMap<String, String>) aircraft.getObject("tier3Proline21",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			alacarteRegions = (ConcurrentHashMap<String, String>) aircraft.getObject("regions",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			alacarteLOS = (ConcurrentHashMap<String, String>) aircraft.getObject("LOS",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			alacarteCategories = (ConcurrentHashMap<String, String>) aircraft.getObject("categories",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			addUser = (ConcurrentHashMap<String, String>) myAccount.getObject("addNewUser[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			changeEmailData = (ConcurrentHashMap<String, String>) myAccount.getObject("changeEmail[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			savedCardMessage = (ConcurrentHashMap<String, String>) myAccount.getObject("useSavedCard[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			ordertype = (ConcurrentHashMap<String, String>) paymentType.getObject("ordertype[0]",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			// bundles:
			bundlesTier1G1K = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1G1K",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1G2k = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1G2k",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1G1KAIFD500 = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1G1KAIFD500",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier3G3k = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier3G3k",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1G1KG950 = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1G1KG950",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1G1KH = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1G1KH",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GNxi = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GNxi",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GNxiG3K = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GNxiG3K",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GTNseries = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GTNseries",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GTN7S = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GTN7S",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1TXISeries = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1TXISeries",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1XISeries = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1XISeries",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GTN6SGTN6S = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GTN6SGTN6S",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GTN6S = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GTN6S",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GPS175GNX375 = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GPS175GNX375", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GPS175GNX375A = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GPS175GNX375A", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier3GPS175GTN6S = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier3GPS175GTN6S",Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1GNC355A355 = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1GNC355A355",Class.forName("java.util.concurrent.ConcurrentHashMap"));
            bundlesTier1G3K175G7SGI275 = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1G3K175G7SGI275", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1G6S7Xi6XiG1k = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1G6S7Xi6XiG1k", Class.forName("java.util.concurrent.ConcurrentHashMap"));
			bundlesTier1IFD5EX4 = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1IFD5EX4",Class.forName("java.util.concurrent.ConcurrentHashMap"));
         	bundlesTier1IFD5G1K = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1IFD5G1K",Class.forName("java.util.concurrent.ConcurrentHashMap"));
         	bundlesTier25WS4WS = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier25WS4WS",
					Class.forName("java.util.concurrent.ConcurrentHashMap"));	
         	bundlesTier1IFD5IFD4 = (ConcurrentHashMap<String, String>) aircraft.getObject("bundlesTier1IFD5IFD4",
					Class.forName("java.util.concurrent.ConcurrentHashMap"));
		
			// Pay invoice data
			payinvoicedata = (ConcurrentHashMap<String, Object>) payinvoice.getObject("",Class.forName("java.util.concurrent.ConcurrentHashMap"));

			String env = System.getProperty("env");
			URL = url.getString(env);

			if (env.equals("QA")) {
				pool = (CopyOnWriteArrayList<String>) login.getObject("QA",
						Class.forName("java.util.concurrent.CopyOnWriteArrayList"));
			} else if (env.equals("STAGE")) {
				pool = (CopyOnWriteArrayList<String>) login.getObject("STAGE",
						Class.forName("java.util.concurrent.CopyOnWriteArrayList"));
			} else if (env.equals("DEV")) {
				pool = (CopyOnWriteArrayList<String>) login.getObject("DEV",
						Class.forName("java.util.concurrent.CopyOnWriteArrayList"));
			} else if (env.equals("VAL")) {
				pool = (CopyOnWriteArrayList<String>) login.getObject("VAL",
						Class.forName("java.util.concurrent.CopyOnWriteArrayList"));
			} else if (env.equals("QA2")) {
				pool = (CopyOnWriteArrayList<String>) login.getObject("QA2",
						Class.forName("java.util.concurrent.CopyOnWriteArrayList"));
			}else if (env.equals("AZDEV")) {
				pool = (CopyOnWriteArrayList<String>) login.getObject("AZDEV",
						Class.forName("java.util.concurrent.CopyOnWriteArrayList"));
			} else if (env.equals("Splunk")) {
				pool = (CopyOnWriteArrayList<String>) login.getObject("Splunk",
						Class.forName("java.util.concurrent.CopyOnWriteArrayList"));
			} else {
				System.out.println("Invalid Environment. Provide any of these keyword - QA, STAGE, DEV, VAL,QA2,AZDEV");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Unable to intialize test data");
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@BeforeSuite
	protected void beforeSuite() {
		startReporting();
	}

	@AfterSuite
	protected void afterSuite(ITestContext ctx) {
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		int ptest = ctx.getCurrentXmlTest().getThreadCount();
		System.out.println("SuiteName is :-" + suiteName);
		System.out.println("Thread Count " + ptest);
		endReport(suiteName, ptest);

	}

	@BeforeMethod
	protected void beforeTest(Method res1, ITestResult res2) {
		ThreadManager.username.set("");
		String str = res1.getDeclaringClass().getName().toString().substring(6);
		String testType = System.getProperty("testType");
		startTest(res1, res2, getEnv(str));

		if (testType.contains("API")) {
			System.out.println("Method Execution Started : " + res1.getName());
		} else {
			home = new HomePage();
			//sPlunkhome = new SplunkLogs();
			// home.acceptCookies();
		}

	}

	@AfterMethod
	public void afterMethod(Method res1, ITestResult res) {
		String str = ThreadManager.username.get();
		if (!str.contentEquals("") && !pool.contains(str)) {
			pool.add(str);
		}
		ThreadManager.username.remove();
		endTest(res1, res);
	}

	private String getEnv(String clsName) {
		String env = System.getProperty("env");
		switch (env) {
		case "QA":
			if (clsName.contains("ASM")) {
				return URL + "?asm=true";
			} else {
				return URL;
			}
		case "STAGE":
			if (clsName.contains("ASM")) {
				return URL + "?asm=true";
			} else {
				return URL;
			}
		case "DEV":
			if (clsName.contains("ASM")) {
				return URL + "?asm=true";
			} else {
				return URL;
			}

		case "VAL":
			if (clsName.contains("ASM")) {
				return URL + "?asm=true";
			} else {
				return URL;
			}
		case "QA2":
			if (clsName.contains("ASM")) {
				return URL + "?asm=true";
			} else {
				return URL;
			}
		case "AZDEV":
			if (clsName.contains("ASM")) {
				return URL + "?asm=true";
			} else {
				return URL;
			}
		case "Splunk":
			if (clsName.contains("Autorenewal")) {
				return URL = "https://lspl-tst-1.aviallinc.com:8000/en-US/account/login?";
			} else {
				return URL;
			}


		default:
			System.out.println("******* Invalid Environment **********");
			throw new CustomException("Environment not choosen");
		}
	}

}
