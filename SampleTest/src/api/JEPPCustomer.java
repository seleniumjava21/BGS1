package avatar;

import java.lang.reflect.Method;

import utilities.Api;
import utilities.Utils;
import utilities.Verify;

public class JEPPCustomer extends Api{
	
	public JEPPCustomer(String username) {
		super(username);
	}
	
	//Resources
	public final String jeppCustomerPublishLndgn = "/Jepp_Customer_Publish_Lndgn";
	public final String jeppCustomerCommonTopic = "/Jepp_CommonCustomer_IN_Publisher_Multiple";
	
	
	public Api postCustomerLandZoneResp(String payload) {
		return requestJSON(Method.POST, jeppCustomerPublishLndgn, payload);
	}

	public Api postCustomerCommTopicINResp(String payload) {
		return requestJSON(Method.POST, jeppCustomerCommonTopic,payload);
	}
	
	public void jeppCustLandZoneResp(Api response, String requestBody, String reqCustNumber) {
			String customerNumObjPath="$.LandingTblInfaResponse[:1].CustomerNumber";
			String responseObjPath="$.LandingTblInfaResponse[:1].Response";
			Verify.verifyJsonPathExistence(response, customerNumObjPath);
			String expCustNuber=Utils.readJsonRespArrayObjValue(response, "LandingTblInfaResponse", "CustomerNumber");
			Verify.verifyTextContainsAPI(response, reqCustNumber, expCustNuber, "Customer Number");
			Verify.verifyJsonPathExistence(response, responseObjPath);
			String reqResponse=Utils.readJsonRespArrayObjValue(response, "LandingTblInfaResponse", "Response");
			String expResponse="Below CustomerNumber Successfully processed.";
			Verify.verifyTextContainsAPI(response, reqResponse, expResponse, "Response");	
	}

	public void jeppCustLandzoneRespErrJEPP100(Api response) {
		String responseObjPath="$.LandingTblInfaResponse[:1].Response";
		Verify.verifyJsonPathExistence(response, responseObjPath);
		String reqResponse=Utils.readJsonRespArrayObjValue(response, "LandingTblInfaResponse", "Response");
		String expResponse="Empty Customernumber can't be proccessed.Please provide correct CustomerNo.";
		Verify.verifyTextContainsAPI(response, reqResponse, expResponse, "Response");
	}

	public void jeppCommonTopicINResp(Api response) {				
		String responseObjPath="$.CustomerTopicINPubInfaResponse[:1].Response";
		String expectedMsg="No New CustomerNumber available for Processing in landing table.";
		Verify.verifyJsonPathExistence(response, responseObjPath);
		String actualMsg=Utils.readJsonRespArrayObjValue(response, "CustomerTopicINPubInfaResponse", "Response");
		Verify.verifyTextContainsAPI(response, expectedMsg, actualMsg, "Response");
	}

	
}
