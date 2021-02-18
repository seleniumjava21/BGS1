package avatar;

import java.lang.reflect.Method;

import utilities.Api;
import utilities.Utils;
import utilities.Verify;

public class CommonAPI extends Api{
	
	public CommonAPI(String username) {
		super(username);
	}

	//Resources
	public final String customerOutPublish = "/Customer_Out_Publish";
	public final String customerOutSubscribe = "/Customer_Out_Subscribe";
	public final String customerAccountCDMOUT = "/CustomerAccountCDM_OUT";
	public final String productCDMOUT = "/Product_CDMP_API";
	
	
	public Api postCustCommTopicOutPublisherResp(String payload) {
		return requestJSON(Method.POST, customerOutPublish, payload);
	}
	
	public Api postCustCommTopicOutSubscriberResp(String payload) {
		return requestJSON(Method.POST, customerOutSubscribe, payload);
	}
	
	public Api postCustomerAccountCDMOUTResp(String payload) {
		return requestJSON(Method.POST, customerAccountCDMOUT, payload);
	}
	
	public Api postProductCDMOUTResp(String payload) {
		return requestJSON(Method.POST, productCDMOUT, payload);
	}
	
	public void customerOutPublisherValidResp(Api response) {
		String responseObjPath="$.Out_Response";
		String expectedMsg="Data Published Successfully";
		Verify.verifyJsonPathContentMatch(response, responseObjPath,expectedMsg);	
	}

	public void customerOutSubscriberValidResp(Api response) {
		String responseStatusObjPath="$.OUT_Response.responseStatus";
		String statsTotalRows="$.OUT_Response.statsTotalRows";
		String statsSuccessRows="$.OUT_Response.statsTotalRows";
		Verify.verifyJsonPathContentMatch(response, responseStatusObjPath,"SUCCESS");	
		Verify.verifyJsonPathExistence(response, statsTotalRows);
		Verify.verifyJsonPathExistence(response, statsSuccessRows);
	}

	public void customerOutSubscriberFailedResp(Api response) {
		String responseStatusObjPath="$.OUT_Response.responseStatus";
		String responseExtraMessage="$.OUT_Response.responseExtraMessage";
		String responseMessage="There is no pending data for subscription [Customer_Out_Sub] to consume.";
		Verify.verifyJsonPathContentMatch(response, responseStatusObjPath,"FAIL");	
		Verify.verifyJsonPathContentMatch(response, responseExtraMessage,responseMessage);
	}

	public void bdsiCustomerCDMOUTResp(Api response) {
		String customerSiteTable="$.CustomerResponseMsg[:1].CustomerSite";
		String contactPointTable="$.CustomerResponseMsg[:1].ContactPoint";
		String contactBridgeTable="$.CustomerResponseMsg[:1].ContactBridge";
		String contactTypeTable="$.CustomerResponseMsg[:1].ContactType";
		String CustomerSiteTypeTable="$.CustomerResponseMsg[:1].CustomerSiteType";
		String CustomerAccountTable="$.CustomerResponseMsg[:1].CustomerAccount";
		String CarrierTable="$.CustomerResponseMsg[:1].Carrier";
		String ContactTable="$.CustomerResponseMsg[:1].Contact";
		String customerAccountCustomerNumber="$.CustomerResponseMsg[:1].CustomerAccount.CustomerNumber";
		String customerSiteSourceSystem="$.CustomerResponseMsg[:1].CustomerSite[:1].SourceSystemName";
		String customerAccountSourceSystem="$.CustomerResponseMsg[:1].CustomerAccount.SourceSystemName";
		Verify.verifyJsonPathExistence(response, customerSiteTable);
		Verify.verifyJsonPathExistence(response, contactPointTable);
		Verify.verifyJsonPathExistence(response, contactBridgeTable);
		Verify.verifyJsonPathExistence(response, contactTypeTable);
		Verify.verifyJsonPathExistence(response, CustomerSiteTypeTable);
		Verify.verifyJsonPathExistence(response, CustomerAccountTable);
		Verify.verifyJsonPathExistence(response, CarrierTable);
		Verify.verifyJsonPathExistence(response, ContactTable);
		Verify.verifyJsonPathExistence(response, customerAccountCustomerNumber);
		String actualMsg=Utils.readNestedJsonRespArrayObjValue(response, "CustomerResponseMsg", "CustomerAccount", "CustomerNumber");
		Verify.verifyTextContainsAPI(response, actualMsg, "020196", "CustomerNumber");	
		Verify.verifyJsonPathExistence(response, customerSiteSourceSystem);
		String actualMsg1=Utils.readNestedJsonRespMultiArrayObjValue(response, "CustomerResponseMsg", "CustomerSite", "SourceSystemName");
		Verify.verifyTextContainsAPI(response, actualMsg1, "BDSI", "SourceSystemName");
		Verify.verifyJsonPathExistence(response, customerAccountSourceSystem);
		String actualMsg2=Utils.readNestedJsonRespArrayObjValue(response, "CustomerResponseMsg", "CustomerAccount", "SourceSystemName");
		Verify.verifyTextContainsAPI(response, actualMsg2, "BDSI", "SourceSystemName");		
	}
	
	public void jeppCustomerCDMOUTResp(Api response) {
		String customerSiteTable="$.CustomerResponseMsg[:1].CustomerSite";
		String contactPointTable="$.CustomerResponseMsg[:1].ContactPoint";
		String contactBridgeTable="$.CustomerResponseMsg[:1].ContactBridge";
		String contactTypeTable="$.CustomerResponseMsg[:1].ContactType";
		String CustomerSiteTypeTable="$.CustomerResponseMsg[:1].CustomerSiteType";
		String CustomerAccountTable="$.CustomerResponseMsg[:1].CustomerAccount";
		String CarrierTable="$.CustomerResponseMsg[:1].Carrier";
		String ContactTable="$.CustomerResponseMsg[:1].Contact";
		Verify.verifyJsonPathExistence(response, customerSiteTable);
		Verify.verifyJsonPathExistence(response, contactPointTable);
		Verify.verifyJsonPathExistence(response, contactBridgeTable);
		Verify.verifyJsonPathExistence(response, contactTypeTable);
		Verify.verifyJsonPathExistence(response, CustomerSiteTypeTable);
		Verify.verifyJsonPathExistence(response, CustomerAccountTable);
		Verify.verifyJsonPathExistence(response, CarrierTable);
		Verify.verifyJsonPathExistence(response, ContactTable);
	}
	
	public void bdiCustomerCDMOUTResp(Api response) {
		String customerSiteTable="$.CustomerResponseMsg[:1].CustomerSite";
		String contactPointTable="$.CustomerResponseMsg[:1].ContactPoint";
		String contactBridgeTable="$.CustomerResponseMsg[:1].ContactBridge";
		String contactTypeTable="$.CustomerResponseMsg[:1].ContactType";
		String CustomerSiteTypeTable="$.CustomerResponseMsg[:1].CustomerSiteType";
		String CustomerAccountTable="$.CustomerResponseMsg[:1].CustomerAccount";
		String CarrierTable="$.CustomerResponseMsg[:1].Carrier";
		String ContactTable="$.CustomerResponseMsg[:1].Contact";
		Verify.verifyJsonPathExistence(response, customerSiteTable);
		Verify.verifyJsonPathExistence(response, contactPointTable);
		Verify.verifyJsonPathExistence(response, contactBridgeTable);
		Verify.verifyJsonPathExistence(response, contactTypeTable);
		Verify.verifyJsonPathExistence(response, CustomerSiteTypeTable);
		Verify.verifyJsonPathExistence(response, CustomerAccountTable);
		Verify.verifyJsonPathExistence(response, CarrierTable);
		Verify.verifyJsonPathExistence(response, ContactTable);
	}

	public void customerCDMOUTBADResp(Api response) {
		String expectedMsg="CustomerNumber = 020196 and SourceSystemName= BDI Combination doesnot Exist in CDM";
		Verify.verifyJsonPathExistence(response, "Out_Response");
		String actualMsg=Utils.readJsonResStringObjectValue(response, "Out_Response");
		Verify.verifyTextContainsAPI(response, actualMsg, expectedMsg, "Out_Response");	
	}

	public void bdiProductCDMOUTResp(Api response) {
		String productTable="$.ProductModelDetails[:1].Product";
		String productInventoryOrgTable="$.ProductModelDetails[:1].Product[:1].ProductInventoryOrg";
		String productGroupTable="$.ProductModelDetails[:1].Product[:1].ProductGroup";
		String productTypesTable="$.ProductModelDetails[:1].Product[:1].ProductTypes";
		Verify.verifyJsonPathExistence(response, productTable);
		Verify.verifyJsonPathExistence(response, productInventoryOrgTable);
		Verify.verifyJsonPathExistence(response, productGroupTable);
		Verify.verifyJsonPathExistence(response, productTypesTable);
		String actProductNumber=Utils.readNestedJsonRespArrayObjValue(response, "ProductModelDetails", "Product", "ProductNumber");
		Verify.verifyTextContainsAPI(response, actProductNumber, "641742=0H", "ProductNumber");	
		String sourceSystemName=Utils.readNestedJsonRespArrayObjValue(response, "ProductModelDetails", "SourceSystemName", "SourceSystemName");
		Verify.verifyTextContainsAPI(response, sourceSystemName, "BDI", "ProductNumber");	
		String productInventoryOrgSysName=Utils.readNestedJsonRespMultiArrayObjValue(response, "ProductModelDetails", "Product", "ProductInventoryOrg", "SourceSystemName");
		Verify.verifyTextContainsAPI(response, productInventoryOrgSysName, "BDI", "productInventoryOrgSysName");	
		String productGroupSysName=Utils.readNestedJsonRespMultiArrayObjValue(response, "ProductModelDetails", "Product", "ProductGroup", "SourceSystemName");
		Verify.verifyTextContainsAPI(response, productGroupSysName, "BDI", "productGroupSysName");
		String productTypeSysName=Utils.readNestedJsonRespMultiArrayObjValue(response, "ProductModelDetails", "Product", "ProductTypes", "SourceSystemName");
		Verify.verifyTextContainsAPI(response, productTypeSysName, "BDI", "productTypeSysName");
	}

	public void productCDMOUTBadResp(Api response) {
		String expectedMsg="CustomerNumber = 641742=0H and SourceSystemName= BDSI Combination does not Exist in CDM Database.";
		Verify.verifyJsonPathExistence(response, "InfaResponse");
		String actualMsg=Utils.readJsonResStringObjectValue(response, "InfaResponse");
		Verify.verifyTextContainsAPI(response, actualMsg, expectedMsg, "InfaResponse");
		
	}
	

}