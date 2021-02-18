package avatar;

import java.io.File;
import java.lang.reflect.Method;

import io.restassured.path.xml.XmlPath;
import utilities.Api;
import utilities.Utils;
import utilities.Verify;


public class BDICustomer extends Api {
	
	public BDICustomer(String username) {
		super(username);
	}
	
	//Resources
	private final String bdiCustomerPublishLndgn = "/BDICustomerUpdate";
	private final String bdiCustomerCommonTopic ="/BDI_CommonCustomer_IN_Publisher";
	
	public Api postCustomerLandZoneResp(String payload) {
		return requestXML(Method.POST, bdiCustomerPublishLndgn, payload);
	}

	public Api postCustomerCommTopicINResp(String payload) {
		return requestJSON(Method.POST, bdiCustomerCommonTopic, payload);
	}

	public void bdiCustLandzoneResp(Api response) {
			String responseObjPath="BDICustomerUpdateResponse.Out_Response.Envelope.Body.XML_Payload";
			String payloadResp="Payload for above customer loaded to Landing Table successfully";
			Verify.verifyXMLPathContentMatch(response, responseObjPath, payloadResp, "Resonse");
	}

	public void bdiCustCommonTopicINResp(Api response) {
		String customerNumObjPath="$.InfaResponse[:1].CustomerNumber";
		String responseObjPath="$.InfaResponse[:1].Response";
		XmlPath xml = new XmlPath(new File(Utils.payload+"bdiCustomerLandingZone.xml"));
		String reqCustNumber=xml.getString("ns0:MT_ZOTC4050I_CustMastertoODS_IN.PCUSTOMER_UpdateStatement.PCUSTOMER.access.CUSTOMER");
		String expCustNuber=Verify.getJsonPathValue(response, customerNumObjPath);
		Verify.verifyTextContainsAPI(response, reqCustNumber, expCustNuber, "Customer Number");
		Verify.verifyJsonPathExistence(response, responseObjPath);
		Verify.verifyJsonContentNotNull(response, responseObjPath,"Response");
	}
	
	public void bdiCustLandzoneRespErr2(Api response) {
		String responseObjPath="$.InfaResponse[:1].Response";
		String expectedMsg="No New CustomerNumber available for Processing in landing table";
		Verify.verifyJsonPathExistence(response, responseObjPath);
		String actualMsg=Utils.readJsonRespArrayObjValue(response, "InfaResponse", "Response");
		Verify.verifyTextContainsAPI(response, expectedMsg, actualMsg, "Response");
	}

	

	

}
