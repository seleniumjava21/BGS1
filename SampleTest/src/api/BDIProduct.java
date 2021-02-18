package avatar;

import java.lang.reflect.Method;

import utilities.Api;
import utilities.Verify;

public class BDIProduct extends Api {

	public BDIProduct(String username) {
		super(username);
	}

	//Resources
	public final String bdiCMIRLndng = "/BDICMIRLndng";
	public final String bdiProductLndng_Part = "/BDIProductLndng_Part";
	public final String bdiProductLndng_SyncPart = "/BDIProductLndng_SyncPart";
	public final String bdiProductCommonTopic_In = "/BDIProductCommonTopic_In";
	
	
	public Api postProductCMIRLanding(String payload) {	
		return requestXML(Method.POST, bdiCMIRLndng, payload);
	}
	
	public Api postProdPartSupersessionLand(String payload) {
		return requestXML(Method.POST, bdiProductLndng_Part, payload);
	}
	
	public Api postSyncPartLanding(String payload) {
		return requestXML(Method.POST, bdiProductLndng_SyncPart, payload);
	}
	
	public Api postProductCommTopicResp(String payload) {
		return requestJSON(Method.POST, bdiProductCommonTopic_In, payload);
	}
	
	public void bdiProditemCMIRLandingResp(Api response) {
		String outResponseObjPath="BDICMIRLndngResponse.Out_Response";
		String expResponse="Payload loaded to Landing Table";
		Verify.verifyXMLPathContentMatch(response, outResponseObjPath, expResponse, "Out_Response");	
	}

	public void bdiProdPartSupersessionLandResp(Api response) {
		String outResponseObjPath="BDIProductLndng_PartResponse.Out_Response";
		String expResponse="Payload loaded to Landing Table";
		Verify.verifyXMLPathContentMatch(response, outResponseObjPath, expResponse, "Out_Response");
	}

	public void bdiSyncPartLandResp(Api response) {
		String outResponseObjPath="BDIProductLndng_SyncPartResponse.Out_Response";
		String expResponse="Payload loaded to Landing Table";
		Verify.verifyXMLPathContentMatch(response, outResponseObjPath, expResponse, "Out_Response");
	}

	public void bdiProductCommTopicResp(Api response) {
		String outResponse="$.OUT_Response";
		String expectedResponse="BDI Data is Published";
		Verify.verifyJsonPathContentMatch(response, outResponse,expectedResponse);	
	}

	public void bdiProductCommTopicRespNoNewProduct(Api response) {
		String outResponse="$.OUT_Response";
		String expectedResponse="No New Product available for Processing in landing table.";
		Verify.verifyJsonPathContentMatch(response, outResponse,expectedResponse);
	}

	
}	
