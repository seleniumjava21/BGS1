
package regression;


import org.testng.annotations.Test;

import base.BaseTest;
import pages.BasePage.Login;

public class Bundles extends BaseTest {
	
   
	@Test(description="Validation Non-Config G2000 bundle")

	public void R73_Bundles_NonConfig_G2000_GNS400WAAS() {
		

			home
				.GoTo_LogIn()
				.Login_With_Credentials_And_Verify_Login(Login.bundleJUS8)
				.Empty_Out_Cart()
				.Navigate_Buy_Database()
				.Select_Your_Aircraft(bundlesTier1G2k)
				.Choose_Fly_Location(alacarteRegions.get("regionNA"))
				.Select_Bundles_With_This_Device(bundlesTier1G2k.get("devicemodel2K"),bundlesTier1GTN7S.get("CoverageAmericas"))
				.Validate_Bundle_Coverage_Details_OnCart()
				.Navigate_Buy_Database()
				.Select_Your_Aircraft(bundlesTier25WS4WS)
				.Choose_Fly_Location(alacarteRegions.get("regionNA"))
				.Select_Bundles_With_This_Device(bundlesTier25WS4WS.get("devicemodelG4WS"),bundlesTier1GTN7S.get("CoverageAmericas"))
				.Validate_Bundle_Coverage_Details_OnCart()
				.Validate_All_Devices_Added_OnCart()
				.Checkout()	
				.Add_New_Shipping_Address(basicAddress)
				.Use_Default_Shipping_Method()
				.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
				.Auto_Renewal_Selection(AutoRenewal,"Yes")
				.Add_New_Billing_Address_CC(billingAddress)
				.Auto_Renewal_validation()
				.Auto_Renewal_Price_Check()
				.Place_Order()
				.Validate_Bundle_Order_Confirmation()
				.Validate_Autorenewal_Annual_OrderConfirmation(ordertype.get("RenewalAnnualYes"))
				.Verify_Order()
				.Validate_Bundle_Order_Details()
				.Validate_Autorenewal_Annual_OrderDetails(ordertype.get("RenewalAnnualYes"))
				.LogOut_Account();				
		

	}
	
	@Test(description="Validation of Config G1000 bundle")
	public void R74_Bundles_Config_G1000() {
			
			home
				.GoTo_LogIn()
				.Login_With_Credentials_And_Verify_Login(Login.bundlesJUS05)
				.Empty_Out_Cart()
				.Navigate_Buy_Database()
				.Select_Your_Aircraft(bundlesTier1G1K)
				.Choose_Fly_Location(alacarteRegions.get("regionNA"))
				.Validate_ViewBundleDetails()
				.Select_Bundles_With_This_Device(bundlesTier1G1K.get("devicemodelG1K"),bundlesTier1GTN7S.get("CoverageAmericas"))
				.Validate_Bundle_Coverage_Details_OnCart()
				.Checkout()	
				.Add_New_Shipping_Address(basicAddress)
				.Use_Default_Shipping_Method()
				.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
				.Auto_Renewal_Selection(AutoRenewal,"Yes")
				.Add_New_Billing_Address_CC(billingAddress)
				.Auto_Renewal_validation()
				.Auto_Renewal_Price_Check()
				.Edit_this_Cart()
				.validate_editBundles()
				.Checkout_With_Cart()
				.Use_Existing_Shipping_Address()
				.Use_Default_Shipping_Method()
				.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
				.Auto_Renewal_Selection(AutoRenewal,"Yes")
				.Add_New_Billing_Address_CC(billingAddress)
				.Auto_Renewal_validation()
				.Auto_Renewal_Price_Check()
				.Place_Order()
				.Verify_Order()
				.LogOut_Account();		

	}
	@Test(description="Validation of Avidyne bundle")
	public void R75_Bundles_NonConfig_IFD500Series() {
			
			home
				.GoTo_LogIn()
				.Login_With_Credentials_And_Verify_Login(Login.bundleJEU06)
				.Empty_Out_Cart()
				.Navigate_Buy_Database()
				.Select_Your_Aircraft(bundlesTier1G1KAIFD500)
				.Choose_Fly_Location(alacarteRegions.get("regionEU"))
				.Select_Bundles_With_This_Device(bundlesTier1G1KAIFD500.get("devicemodelAIFD5S"),bundlesTier1GTN7S.get("CoverageEuropeIFR"))
				.Validate_Bundle_Coverage_Details_OnCart()
				.Validate_All_Devices_Added_OnCart()
				.Checkout()	
				.Use_Existing_Shipping_Address()
				.Use_Default_Shipping_Method()
				.Use_Credit_Card_Payment(masterCard3DS1NoFriction)
				.Auto_Renewal_Selection(AutoRenewal,"Yes")
				.Use_Existing_Billing_Address_CC()
				.Auto_Renewal_validation()
				.Auto_Renewal_Price_Check()
				.Place_Order()
				.Validate_Bundle_Order_Confirmation()
				.Verify_Order()
				.Validate_Bundle_Order_Details()
				.LogOut_Account();		

	}
	
	@Test(description = "error validation G1000,G2000,G3000")
	public void R76_Bundles_errorvalidation() {
		

		home.GoTo_LogIn()
		.Login_With_Credentials_And_Verify_Login(Login.bundleJUS09)
		.Empty_Out_Cart()
		
		//G2000 with Europe
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G2k)
		.Choose_Fly_Location(alacarteRegions.get("regionEU"))
		.Disabled_AvionicDBBundle(aircraftDetails)
		
		//G2000 with with Pacific
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G2k)
		.Choose_Fly_Location(alacarteRegions.get("regionPCF"))
		.Disabled_AvionicDBBundle(aircraftDetails)
		
		//Tier3
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier3G3k)
		.Choose_Fly_Location(alacarteRegions.get("regionSA"))
		.Disabled_AvionicDBBundle(aircraftDetails)		
		
		//G1000
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G1K)
		.Choose_Fly_Location(alacarteRegions.get("regionNA"))
		.Select_Bundles_With_This_Device(bundlesTier1G1K.get("devicemodelG1K"),bundlesTier1GTN7S.get("CoverageAmericas"))
			
		//G1000 added again with different region but same tail
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G1K)
		.Choose_Fly_Location(alacarteRegions.get("regionSA"))	
		.Error_Validation_On_Bundles_Selection()
		
		
		//G2000
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G2k)
		.Choose_Fly_Location(alacarteRegions.get("regionNA"))
		.Select_Bundles_With_This_Device(bundlesTier1G2k.get("devicemodel2K"),bundlesTier1GTN7S.get("CoverageAmericas"))
				
		//G2000 added again with same tail for error validation
		.GoTo_AircraftDetails()
		.Navigate_To_BuyDB_Screen_From_AircraftDetails()
		.Select_Your_Aircraft(bundlesTier1G2k)
		.Choose_Fly_Location(alacarteRegions.get("regionNA"))
		.Error_Validation_On_Bundles_Selection()
		
		//G2000 added again with different region
		.GoTo_AircraftDetails()
		.Navigate_To_BuyDB_Screen_From_AircraftDetails()
		.Select_Your_Aircraft(bundlesTier1G2k)
		.Choose_Fly_Location(alacarteRegions.get("regionSA"))
		.Error_Validation_On_Bundles_Selection()
		.LogOut_Account();
		
			}
	
@Test(description = "Bundle Validation for G950,G1000H,GNXi")
	
	public void R77_validate_G950_G1000H_GNXI() {
	home
	.GoTo_LogIn()
	.Login_With_Credentials_And_Verify_Login(Login.bundleJUS0012)
	.Empty_Out_Cart()
	.Navigate_Buy_Database()
	.Select_Your_Aircraft(bundlesTier1G1KG950)
	.Choose_Fly_Location(alacarteRegions.get("regionSA"))
	.Disabled_AvionicDBBundle(aircraftDetails)
	
	.Navigate_Buy_Database()
	.Select_Your_Aircraft(bundlesTier1G1KH)
	.Choose_Fly_Location(alacarteRegions.get("regionNA"))
	.Disabled_AvionicDBBundle(aircraftDetails)
	
	.Navigate_Buy_Database()
	.Select_Your_Aircraft(bundlesTier1GNxi)
	.Choose_Fly_Location(alacarteRegions.get("regionNA"))
	.Select_Bundles_With_This_Device(bundlesTier1GNxi.get("devicemodelG1KN"),bundlesTier1GTN7S.get("CoverageAmericas"))
	.Validate_Bundle_Coverage_Details_OnCart()
	
	.Navigate_Buy_Database()
	.Select_Your_Aircraft(bundlesTier1GNxiG3K)
	.Choose_Fly_Location(alacarteRegions.get("regionNA"))
	.Select_Bundles_With_This_Device(bundlesTier1GNxi.get("devicemodelG1KN"),bundlesTier1GTN7S.get("CoverageAmericas"))
	.Validate_Bundle_Coverage_Details_OnCart()
	.Checkout_With_Cart()
	.Add_New_Shipping_Address(newNZShippingAddress)
	.Use_Default_Shipping_Method()
	.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
	.Auto_Renewal_Selection(AutoRenewal,"No")
	.Add_New_Billing_Address_CC(billingAddress)
	.Place_Order()
	.Verify_Order();
	
}
	
@Test(description= "Verify Bundle search functionality with authentic user")
    public void R78_Bundle_AuthsearchPLP()  {	
	
	home	
	.Search_This_Product(simpleProduct.get("bundleProduct1"))
	.Navigate_To_BuyDB_From_PLP_NonAuth()
	.Login_With_Credentials_And_Verify_BuyDB(Login.bundlesJUS04)
	.Select_Your_Aircraft(bundlesTier1GNxi)
	.Empty_Out_Cart()
	.Navigate_Buy_Database()
	.Select_Your_Aircraft(bundlesTier1GNxi)
	.Choose_Fly_Location(alacarteRegions.get("regionNA"))
	.Select_Bundles_With_This_Device(bundlesTier1GNxi.get("devicemodelG1KN"),bundlesTier1GTN7S.get("CoverageAmericas"))
	.Validate_Bundle_Coverage_Details_OnCart()
	.Search_This_Product(simpleProduct.get("bundleProduct2"))
	.Navigate_To_BuyDB_From_PLP_Auth()
	.Select_Your_Aircraft(bundlesTier1G1KAIFD500)
	.Choose_Fly_Location(alacarteRegions.get("regionNA"))
	.Select_Bundles_With_This_Device(bundlesTier1G1KAIFD500.get("devicemodelAIFD5S"),bundlesTier1GTN7S.get("CoverageAMEIFR"))
	.Validate_Bundle_Coverage_Details_OnCart()
	.Checkout_With_Cart()
	.Add_New_Shipping_Address(basicAddress)
	.Use_Default_Shipping_Method()
	.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
	.Auto_Renewal_Selection(AutoRenewal,"No")
	.Add_New_Billing_Address_CC(billingAddress)
	.Place_Order()
	.Verify_Order()
	.LogOut_Account();		
	
}
 
@Test(description = "Mixed cart with LMS,LMS141,Hardgood,Jepp,MFD, A-La-Carte,G1000,G2000,G3000,IFD500")
	
	public void R79_validate_MixedCart() {
		home
		.GoTo_LogIn()
		.Login_With_Credentials_And_Verify_Login(Login.bundlesJUS03)
		.Empty_Out_Cart()
		.Add_This_Product_To_Cart(simpleProduct.get("JeppFullfilled"))
		.Add_This_Product_To_Cart(simpleProduct.get("hardgoodProduct3"))
		.Add_This_Product_To_Cart(simpleProduct.get("AviallFullfilled"))
		.Add_This_Product_To_Cart(simpleProduct.get("LMSproduct1"))
		//.Add_LMS_Product_To_Cart(simpleProduct.get("LMSproduct2"))
		//.Enter_Institution_Details_LMS()
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G1KAIFD500)
		.Choose_Fly_Location(alacarteRegions.get("regionNA"))
		.Select_Individual_Avionics()
		.Select_Length_Of_Coverage_OneTime()
		.Select_Device(bundlesTier1G1KAIFD500.get("devicemodelAIFD5S"))
		.Add_AlaCarte_Product_To_Cart()
		.Validate_AlaCarte_Product_Info_InCart(bundlesTier1G1KAIFD500)
		
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G2k)
		.Choose_Fly_Location(alacarteRegions.get("regionSA"))
		.Select_Individual_Avionics()
		.Select_Length_Of_Coverage_Annual()
		.Select_Device(bundlesTier1G2k.get("devicemodel2K"))
		.Add_AlaCarte_Product_To_Cart()
		
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1GNxiG3K)
		.Choose_Fly_Location(alacarteRegions.get("regionNA"))
		.Select_Bundles_With_This_Device(bundlesTier1GNxiG3K.get("devicemodelG3K"),bundlesTier1GTN7S.get("CoverageAmericas"))
		.Validate_Bundle_Coverage_Details_OnCart()
		
		.GoTo_AircraftDetails()
		.Navigate_To_BuyDB_Screen_From_AircraftDetails()
		.Select_Your_Aircraft(bundlesTier1G1K)
		.Choose_Fly_Location(alacarteRegions.get("regionNA"))
		.Select_Bundles_With_This_Device(bundlesTier1G1K.get("devicemodelG1K"),bundlesTier1GTN7S.get("CoverageAmericas"))
		.Validate_Bundle_Coverage_Details_OnCart()
		
		.Navigate_Buy_Database()
		.Select_Your_Aircraft(bundlesTier1G1KAIFD500)
		.Choose_Fly_Location(alacarteRegions.get("regionEU"))
		.Select_Bundles_With_This_Device(bundlesTier1G1KAIFD500.get("devicemodelAIFD5S"),bundlesTier1GTN7S.get("CoverageEuropeIFR"))
		.Validate_Bundle_Coverage_Details_OnCart()
		.Validate_All_Devices_Added_OnCart()
		.Checkout_With_Cart()	
		.Add_New_Shipping_Address(basicAddress)
		.Use_Default_Shipping_Method()
		.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
		.Auto_Renewal_Selection(AutoRenewal,"No")
		.Add_New_Billing_Address_CC(billingAddress)
		.Place_Order()
		.Validate_Bundle_Order_Confirmation()
		.Verify_Order()
		.Validate_Bundle_Order_Details()
		.LogOut_Account();		
		

	}

	
@Test(description="Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_355_355A")
	public void R80_Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_355_355A() {
				home
		     	.GoTo_LogIn()
			    .Login_With_Credentials_And_Verify_Login(Login.bundlesJUS02)
			    .Empty_Out_Cart()
			  	.Navigate_Buy_Database()
				.Select_Your_Aircraft(bundlesTier1GNC355A355)
				.Choose_Fly_Location(alacarteRegions.get("regionNA"))
				.Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageUSA"))
				.Select_Bundles_With_This_Device(bundlesTier1GNC355A355.get("devicemodel335"),bundlesTier1GTN7S.get("CoverageUSA"))
				.Validate_Bundle_Coverage_Details_OnCart()
				.Navigate_Buy_Database()
				.Select_Your_Aircraft(bundlesTier1GPS175GNX375)
				.Choose_Fly_Location(alacarteRegions.get("regionNA"))
				.Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageUSA"))
				.Select_Bundles_With_This_Device(bundlesTier1GPS175GNX375.get("devicemodel175"),bundlesTier1GTN7S.get("CoverageUSA"))		
				.Validate_Bundle_Coverage_Details_OnCart()
				.Validate_All_Devices_Added_OnCart()
				.Checkout_With_Cart()
				.Add_New_Shipping_Address(newNZShippingAddress)
				.Use_Default_Shipping_Method()
				.Use_Credit_Card_Payment(masterCard3DS1NoFriction)
				.Auto_Renewal_Selection(AutoRenewal,"Yes")
				.Add_New_Billing_Address_CC(billingAddress)
				.Auto_Renewal_validation()
				.Auto_Renewal_Price_Check()
				.Place_Order()
				.Validate_Bundle_Order_Confirmation()
			    .Verify_Order()
			    .Validate_Bundle_Order_Details()
			    .LogOut_Account();				
				
	}		
	
@Test(description="Version_Selection_And_Multi_Avionics_Cart_Validation_JAU_GNX375")
    public void R81_Version_Selection_And_Multi_Avionics_Cart_Validation_JAU_GNX375() {
				
		     home
		    .GoTo_LogIn()
			.Login_With_Credentials_And_Verify_Login(Login.bundleJAU05)
			.Empty_Out_Cart()	
			.Navigate_Buy_Database()
			.Select_Your_Aircraft(bundlesTier1GPS175GNX375)
			.Choose_Fly_Location(alacarteRegions.get("regionNA"))
			.Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageUSA"))
			.Select_Bundles_With_This_Device(bundlesTier1GPS175GNX375.get("devicemodel175"),bundlesTier1GTN7S.get("CoverageUSA"))
			.Validate_Bundle_Coverage_Details_OnCart()
			
		   	.Validate_All_Devices_Added_OnCart()
	    	.Checkout_With_Cart()
	    	.Add_New_Shipping_Address(newNZShippingAddress)
			.Use_Default_Shipping_Method()
			.Use_Credit_Card_Payment(masterCard3DS1NoFriction)
			.Auto_Renewal_Selection(AutoRenewal,"No")
			.Add_New_Billing_Address_CC(addressAustralia)
			.Place_Order()
			.Validate_Bundle_Order_Confirmation()
			.Verify_Order()
			.Validate_Bundle_Order_Details()
			.LogOut_Account();
		
		}
   	
@Test(description="Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_GTN6S_IFD5S")
    public void R82_Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_GTN6S_IFD5S() {
     home
	.GoTo_LogIn()
	.Login_With_Credentials_And_Verify_Login(Login.bundleJUS01)
	.Empty_Out_Cart()	
    .Navigate_Buy_Database()
	.Select_Your_Aircraft(bundlesTier1G1KAIFD500)
    .Choose_Fly_Location(alacarteRegions.get("regionEU"))
	.Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageAmericas"))
   	.Select_Bundles_With_This_Device(bundlesTier1G1KAIFD500.get("devicemodelAIFD5S"),bundlesTier1GTN7S.get("CoverageEuropeIFR"))
   	.Validate_Bundle_Coverage_Details_OnCart()
   	.Navigate_Buy_Database()
    .Select_Your_Aircraft(bundlesTier1GTN6SGTN6S)
    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
    .Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageHeli"))
 	.Select_Bundles_With_This_Device(bundlesTier1GTN6SGTN6S.get("devicemodelGTN6S"),bundlesTier1GTN7S.get("CoverageHeli"))
	 .Validate_Bundle_Coverage_Details_OnCart()
   	.Checkout_With_Cart()
   	.Add_New_Shipping_Address(newNZShippingAddress)
	.Use_Default_Shipping_Method()
	.Use_Credit_Card_Payment(masterCard3DS1NoFriction)
	.Auto_Renewal_Selection(AutoRenewal,"Yes")
	.Add_New_Billing_Address_CC(billingAddress)
	.Auto_Renewal_validation()
	.Auto_Renewal_Price_Check()
	.Place_Order()
	.Validate_Bundle_Order_Confirmation()
	.Verify_Order()
	.Validate_Bundle_Order_Details()
	.LogOut_Account();
	}	
		
@Test(description="Version_Selection_And_Multi_Avionics_Cart_Validation_JAU_GTN6S_PA_SA")
    public void R83_Version_Selection_And_Multi_Avionics_Cart_Validation_JAU_GTN6S_PA_SA() {			
	home
		.GoTo_LogIn()
		.Login_With_Credentials_And_Verify_Login(Login.bundleJAU03)
	    .Empty_Out_Cart()
		.Navigate_Buy_Database()
	    .Select_Your_Aircraft(bundlesTier1GTN6S)
	    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
	    .Validate_VersionSelection(bundlesTier1GTN7S.get(" CoverageCAN"))
	    .Select_Bundles_With_This_Device(bundlesTier1GTN6S.get("devicemodelGTN6S"),bundlesTier1GTN7S.get("CoverageCAN"))
    	.Validate_Bundle_Coverage_Details_OnCart()
    	.Navigate_Buy_Database()
	    .Select_Your_Aircraft(bundlesTier1GTN6SGTN6S)
	    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
	    .Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageCAN"))
    	.Select_Bundles_With_This_Device(bundlesTier1GTN6SGTN6S.get("devicemodelGTN6S"),bundlesTier1GTN7S.get("CoverageCAN"))
		.Validate_Bundle_Coverage_Details_OnCart()
		.Validate_All_Devices_Added_OnCart()
		.Checkout_With_Cart()
		.Add_New_Shipping_Address(newEUGermanyShippingAddress)
		.Use_Default_Shipping_Method()
		.Use_Credit_Card_Payment(masterCard3DS1NoFriction)
		.Auto_Renewal_Selection(AutoRenewal,"Yes")
		.Add_New_Billing_Address_CC(addressAustralia)
		.Auto_Renewal_validation()
		.Auto_Renewal_Price_Check()
		.Place_Order()
		.Validate_Bundle_Order_Confirmation()
		.Verify_Order()
		.Validate_Bundle_Order_Details()
		.LogOut_Account();
		
		}	
			
@Test(description="Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_G3K_GTN6S")
    public void R84_Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_G3K_GTN6S() {
		home
		.GoTo_LogIn()
        .Login_With_Credentials_And_Verify_Login(Login.bundleJUS013)
		.Empty_Out_Cart()
	    .Navigate_Buy_Database()
	    .Select_Your_Aircraft(bundlesTier1G3K175G7SGI275)
	    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
	    .Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageAmericas"))
   	    .Select_Bundles_With_This_Device(bundlesTier1G3K175G7SGI275.get("devicemodelG3K"),bundlesTier1GTN7S.get("CoverageAmericas"))
     	.Validate_Bundle_Coverage_Details_OnCart()
    	.Navigate_Buy_Database()
	    .Select_Your_Aircraft(bundlesTier1G6S7Xi6XiG1k)
	    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
        .Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageCAN"))
   	    .Select_Bundles_With_This_Device(bundlesTier1G6S7Xi6XiG1k.get("devicemodelGTN6S"),bundlesTier1GTN7S.get("CoverageAmericas"))
		.Validate_Bundle_Coverage_Details_OnCart()
		.Checkout_With_Cart()
		.Add_New_Shipping_Address(newEUGermanyShippingAddress)
		.Use_Default_Shipping_Method()
		.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
		.Auto_Renewal_Selection(AutoRenewal,"Yes")
		.Add_New_Billing_Address_CC(billingAddress)
		.Auto_Renewal_validation()
		.Auto_Renewal_Price_Check()
		.Place_Order()
		.Validate_Bundle_Order_Confirmation()
		.Verify_Order()
		.Validate_Bundle_Order_Details()
		.LogOut_Account();
		}

@Test(description="Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_G7Xi_G6XI_G6S")
    public void R85_Version_Selection_And_Multi_Avionics_Cart_Validation_JUS_G7Xi_G6XI_G6S() {
		home
		.GoTo_LogIn()
	    .Login_With_Credentials_And_Verify_Login(Login.bundleJUS014)	   
		.Empty_Out_Cart()
		.Navigate_Buy_Database()
	    .Select_Your_Aircraft(bundlesTier1TXISeries)
	    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
	    .Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageHeli"))
    	.Select_Bundles_With_This_Device(bundlesTier1TXISeries.get("devicemodelGTN6S"),bundlesTier1GTN7S.get("CoverageAmericas"))
    	.Validate_Bundle_Coverage_Details_OnCart()
    	.Navigate_Buy_Database()
	    .Select_Your_Aircraft(bundlesTier1G6S7Xi6XiG1k)
	    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
	    .Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageHeli"))
    	.Select_Bundles_With_This_Device(bundlesTier1G6S7Xi6XiG1k.get("devicemodelG1K"),bundlesTier1GTN7S.get("CoverageAmericas"))
		.Validate_Bundle_Coverage_Details_OnCart()
		.Validate_All_Devices_Added_OnCart()
		.Checkout_With_Cart()
		.Add_New_Shipping_Address(newEUGermanyShippingAddress)
		.Use_Default_Shipping_Method()
		.Use_Credit_Card_Payment(masterCard3DS2NoFriction)
		.Auto_Renewal_Selection(AutoRenewal,"Yes")
		.Add_New_Billing_Address_CC(billingAddress)
		.Auto_Renewal_validation()
		.Auto_Renewal_Price_Check()
		.Place_Order()
		.Validate_Bundle_Order_Confirmation()
		.Verify_Order()
		.Validate_Bundle_Order_Details()
		.LogOut_Account();
	 					}

    @Test(description="Validate_Garmin_4SK_and_Avidyne_Dual_Navigator_Bundle")
	public void R86_Validate_Garmin_4SK_and_Avidyne_Dual_Navigator_Bundle() {
			
			home
			.GoTo_LogIn()
		    .Login_With_Credentials_And_Verify_Login(Login.bundleJUS016)	   
			.Empty_Out_Cart()
			.Goto_OrderHistory()
			.Capture_SubscriptionDetails_OnOrderDetailsPage(amexCard3DS2NoFriction)
			.Goto_MySubscriptions()
			.Validate_PaymentInfo_Renewal_Subscriptions()
			.Verify_AutoPayment_On_Subscriptions()
			.Navigate_Buy_Database()
		    .Select_Your_Aircraft(bundlesTier1GNxi)
		    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
	    	.Select_Bundles_With_This_Device(bundlesTier1GNxi.get("devicemodelG1KN"),bundlesTier1GTN7S.get("CoverageEast"))
	    	.Validate_Bundle_Coverage_Details_OnCart()
	    	.Navigate_Buy_Database()
		    .Select_Your_Aircraft(bundlesTier1G6S7Xi6XiG1k)
		    .Choose_Fly_Location(alacarteRegions.get("regionEU"))
		    .Validate_VersionSelection(bundlesTier1GTN7S.get("CoverageInter"))
	    	.Select_Bundles_With_This_Device(bundlesTier1G6S7Xi6XiG1k.get("devicemodelGXi7"),bundlesTier1GTN7S.get("CoverageInter"))
			.Validate_Bundle_Coverage_Details_OnCart()
			.Navigate_Buy_Database()
			.Select_Your_Aircraft(bundlesTier1IFD5IFD4)
		    .Choose_Fly_Location(alacarteRegions.get("regionNA"))
	    	.Select_Bundles_With_This_Device(bundlesTier1IFD5IFD4.get("devicemodelAIFD5S"),bundlesTier1GTN7S.get("CoverageNAIFR"))
	    	.Validate_Bundle_Coverage_Details_OnCart()
			.Validate_All_Devices_Added_OnCart()
			.Checkout_With_Cart()
			.Add_New_Shipping_Address(newEUGermanyShippingAddress)
			.Use_Default_Shipping_Method()
			.Use_Credit_Card_Payment(amexCard3DS2NoFriction)
			.Auto_Renewal_Selection(AutoRenewal,"Yes")
			.saveCardCheck()
			.Add_New_Billing_Address_CC(billingAddress)
			.Auto_Renewal_validation()
			.Auto_Renewal_Price_Check()
			.Place_Order()
			.Validate_Bundle_Order_Confirmation()
			.Verify_Order()
			.Validate_Bundle_Order_Details()
			.LogOut_Account();
	}


 
	
		


}
