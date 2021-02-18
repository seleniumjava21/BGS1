package avatar;

import java.lang.reflect.Method;

import utilities.Api;
import utilities.Utils;
import utilities.Verify;

public class JEPPProduct extends Api {

	public JEPPProduct(String username) {
		super(username);
	}
	
	//Resources
	public final String jeppProductItemDetailsLanding = "/JeppProductItemDetailsLanding";
	public final String jeppProductItemCommonTopic_In = "/JeppProductItemCommonTopic_In";
	public final String jeppProductModelLanding = "/Jepp_PrdMdlDtl_Publish_Lndgn";
	public final String jeppProductModelCommonTopicIN = "/Jepp_PrdMdlDtl_CommonProduct_IN_Publisher_Main";
	
	
	
	public Api postProductItemLandZoneResp(String payload) {
		return requestJSON(Method.POST, jeppProductItemDetailsLanding, payload);
	}

	public Api postProductCommTopicResp(String payload) {
		return requestJSON(Method.POST, jeppProductItemCommonTopic_In, payload);
	}
	
	public Api postProductModelLandZoneResp(String payload) {
		return requestJSON(Method.POST, jeppProductModelLanding, payload);
	}
	
	public Api postProductModelCommonTopicINResp(String payload) {
		return requestJSON(Method.POST, jeppProductModelCommonTopicIN, payload);
	}

	public void jeppProditemLandZoneResp(Api response) {
		String errTextObjPath="$.Out_Response[:1].ResponseHeader.Status.FaultMessage.Text";
		String errCodeObjPath="$.Out_Response[:1].ResponseHeader.Status.FaultMessage.Code";
		Verify.verifyJsonPathExistence(response, errTextObjPath);
		String expErrorText="No Item Data Exist in ERP for the parameters you entered.";
		String actErrorText=Utils.readJsonRespArrayObjValue(response, "Out_Response", "ResponseHeader.Status.FaultMessage.Text");
		Verify.verifyTextContainsAPI(response, actErrorText, expErrorText, "Error Text");
		Verify.verifyJsonPathExistence(response, errCodeObjPath);
		String expErrorCode="JEP-200";
		String actErrorCode=Utils.readJsonRespArrayObjValue(response, "Out_Response", "ResponseHeader.Status.FaultMessage.Code");
		Verify.verifyTextContainsAPI(response, actErrorCode, expErrorCode, "Error Code");	
	}

	public void jeppProditemCommTopicResp(Api response) {
		String totalRecordPath="$.Response[:1].TotalRecords";
		String responseMessagePath="$.Response[:1].ResponseMessage";
		Verify.verifyJsonPathExistence(response, totalRecordPath);
		String totalNoOfRecords="0";
		String actNoOfRecords=Utils.readJsonRespArrayObjValue(response, "Response", "TotalRecords");
		Verify.verifyTextContainsAPI(response, actNoOfRecords, totalNoOfRecords, "Total Number Of Records");
		Verify.verifyJsonPathExistence(response, responseMessagePath);
		String expResponseMsg="No new product records are available for processing in landing table";
		String actResponseMsg=Utils.readJsonRespArrayObjValue(response, "Response", "ResponseMessage");
		Verify.verifyTextContainsAPI(response, actResponseMsg, expResponseMsg, "Response Message");	
	}
	
	public void jeppProdModelLandZoneResp(Api response) {
		String WithDeltaNotificationObjPath="$.Output[:7].Notification_AviationTrainingProducts";
		String ReasonTextObjPath="$.Output[:1].Reason";
		Verify.verifyJsonPathExistence(response, ReasonTextObjPath);
		String expTextWithoutDelta="No Model Data Exist in ERP for the parameters you entered.";
		String actTextWithoutDelta=Utils.readJsonRespArrayObjValue(response, "Output", "Reason");
		Verify.verifyJsonPathExistence(response, WithDeltaNotificationObjPath);
		Verify.verifyTextContainsAPI(response, actTextWithoutDelta, expTextWithoutDelta, "Without Delta Text");
		String expTextWithDelta="ProductNo#10026017 Successfully Processed Aviation Training Products. ";
		String actTextWithDelta=Utils.readJsonRespArrayObjValueChildObjNum(response, "Output",6, "Notification_AviationTrainingProducts");
		Verify.verifyTextContainsAPI(response, actTextWithDelta, expTextWithDelta, "With Delta Text");	
	}
	
	public void jeppProdModelCommonTopicINResp_WithRecord(Api response) {
		String WithResponseObjPath="$.InfaResponse.Response";
		Verify.verifyJsonPathExistence(response, WithResponseObjPath);
		String expText="Subprocess triggered below productNo#, Please check Final status in Infa log or Product Landing table.";
		String actText=Utils.readJsonRespArrayObjValue(response, "InfaResponse", "Response");
		Verify.verifyTextContainsAPI(response, actText, expText, "Response");
		String ProductNo="$.InfaResponse.ProductNo.Jepp-PrdMdlPublisher-PO.ProductNumber";
		Verify.verifyJsonPathExistence(response, ProductNo);
		String expNo="10026017";
		String actNo=Utils.readNestedJsonRespMultiArrayObjValue(response, "ProductNo", "Jepp-PrdMdlPublisher-PO", "ProductNumber");
		Verify.verifyTextContainsAPI(response, actNo, expNo, "ProductNumber");	
	}
	
	public void jeppProdModelCommonTopicINResp_NoNewRecord(Api response) {
		String WithResponseObjPath="$.InfaResponse.DBResponse";
		String expText="No New Delta record available for processing.";
		Verify.verifyJsonPathContentMatch(response, WithResponseObjPath,expText);
	}
	


}
