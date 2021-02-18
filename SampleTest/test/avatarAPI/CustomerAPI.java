package avatarAPI;


import org.testng.annotations.Test;

import api.BDICustomer;
import api.CommonAPI;
import api.JEPPCustomer;
import base.BaseTest;
import utilities.Api;
import utilities.Utils;
import utilities.Verify;


public class CustomerAPI extends BaseTest {
	
	@Test(description= "JEPP Customer_ Landing Zone API - Valid Response")
	public void tc01_JEPP_Customer_LandingZone_ValidResponse() throws ClassNotFoundException  {
		JEPPCustomer jepp = new JEPPCustomer("Jepp");
		String customerNumber=Utils.readJsonFileArrayObjValue("jeppCustomerLandingZone.json", "In_QueryCustomer.Customer_Jeppesen[0]", "CustomerNumber");
		Api customerJEPPLandingZone = jepp.postCustomerLandZoneResp(Utils.getPayload("jeppCustomerLandingZone.json"));
		Verify.verifyStatusCode(customerJEPPLandingZone,200);
		jepp.jeppCustLandZoneResp(customerJEPPLandingZone,Utils.getPayload("jeppCustomerLandingZone.json"),customerNumber);
		Verify.verifyResponseTime(customerJEPPLandingZone);
	}
		
	@Test(description= "JEPP Customer_ Landing Zone API - Empty Customernumber Response")
	public void tc02_JEPP_Customer_LandingZone_Empty_Customernumber_Response() {
			JEPPCustomer jepp = new JEPPCustomer("Jepp");
			Api customerJEPPLandingZone = jepp.postCustomerLandZoneResp(Utils.getPayload("jeppCustomerLandingZoneError100.json"));
			Verify.verifyStatusCode(customerJEPPLandingZone,200);
			Verify.verifyStatusCode(customerJEPPLandingZone,200);
			jepp.jeppCustLandzoneRespErrJEPP100(customerJEPPLandingZone);
			Verify.verifyResponseTime(customerJEPPLandingZone);
		}

	@Test(description= "JEPP Customer_ Common Topic IN API - No New CustomerNumber Response")
	public void tc03_JEPP_Customer_CommonTopicIN_NoNewCustomerResponse() {
			JEPPCustomer jepp = new JEPPCustomer("Jepp");
			Api customerJEPPCommTopicErr1 = jepp.postCustomerCommTopicINResp(Utils.getPayload("emptyPayload.json"));
			Api customerJEPPCommTopicErr = jepp.postCustomerCommTopicINResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(customerJEPPCommTopicErr,200);
			jepp.jeppCommonTopicINResp(customerJEPPCommTopicErr);
			Verify.verifyResponseTime(customerJEPPCommTopicErr);
		}

	@Test(description= "BDI Customer_ Landing Zone  API - Valid Response")
	public void tc04_BDI_Customer_LandingZone_ValidResponse() {
			BDICustomer bdi = new BDICustomer("BDI");
			//Initial Test on Common Topic API to clear the data
			Api customerbdiCommonTopic = bdi.postCustomerCommTopicINResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(customerbdiCommonTopic,200);
			Api customerBDILandTopic = bdi.postCustomerLandZoneResp(Utils.getPayload("bdiCustomerLandingZone.xml"));
			Verify.verifyStatusCode(customerBDILandTopic,200);
			bdi.bdiCustLandzoneResp(customerBDILandTopic);
			Verify.verifyResponseTime(customerBDILandTopic);
		}
	
	@Test(description= "BDI Customer_ Common Topic IN API - Valid Response")
	public void tc05_BDI_Customer_CommonTopicIN_ValidResponse() {
			BDICustomer bdi = new BDICustomer("BDI");
			Api customerBDICommonTopic = bdi.postCustomerCommTopicINResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(customerBDICommonTopic,200);
			bdi.bdiCustCommonTopicINResp(customerBDICommonTopic);
			Verify.verifyResponseTime(customerBDICommonTopic);
		}
	
	@Test(description= "BDI Customer_ Common Topic IN API - No New CustomerNumber Response")
	public void tc06_BDI_Customer_CommonTopicIN_NoNewCustomerResponse() {
			BDICustomer bdi = new BDICustomer("BDI");
			Api customerBDICommonTopic = bdi.postCustomerCommTopicINResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(customerBDICommonTopic,200);
			bdi.bdiCustLandzoneRespErr2(customerBDICommonTopic);
			Verify.verifyResponseTime(customerBDICommonTopic);
		}
	
	@Test(description= "BDI Customer_ Landing Zone API - Bad Request Response")
	public void tc07_BDI_Customer_LandingZone_BadRequest() {
			BDICustomer bdi = new BDICustomer("BDI");
			Api customerBDILandTopic = bdi.postCustomerLandZoneResp(Utils.getPayload("bdiCustLandZoneBadRequest.xml"));
			Verify.verifyStatusCode(customerBDILandTopic,400);
			Verify.verifyResponseTime(customerBDILandTopic);
		}
	
	@Test(description= "BDSI Customer_ CDMP API - Valid Response")
	public void tc08_BDSI_Customer_CDMP_API_ValidResponse() {
			CommonAPI common = new CommonAPI("COMMON");
			String payload= "{\"CustomerNumber\":\"020196\",\"SourceSystem\":\"BDSI\"}";
			Api customerCDMOUTResp = common.postCustomerAccountCDMOUTResp(payload);
			Verify.verifyStatusCode(customerCDMOUTResp,200);
			common.bdsiCustomerCDMOUTResp(customerCDMOUTResp);
			Verify.verifyResponseTime(customerCDMOUTResp);
		}
	
	@Test(description= "Jepp_Customer_CDMP_API-Valid Response")
	public void tc09_Jepp_Customer_CDMP_API_ValidResponse() {
			CommonAPI common = new CommonAPI("COMMON");
			String payload= "{\"CustomerNumber\":\"127907\",\"SourceSystem\":\"JEPP\"}";
			Api customerCDMOUTResp = common.postCustomerAccountCDMOUTResp(payload);
			Verify.verifyStatusCode(customerCDMOUTResp,200);
			common.jeppCustomerCDMOUTResp(customerCDMOUTResp);
			Verify.verifyResponseTime(customerCDMOUTResp);
		}
	
	@Test(description= "BDI_Customer_CDMP_API-Valid Response")
	public void tc10_BDI_Customer_CDMP_API_ValidResponse() {
			CommonAPI common = new CommonAPI("COMMON");
			String payload= "{\"CustomerNumber\":\"0010299986\",\"SourceSystem\":\"BDI\"}";
			Api customerCDMOUTResp = common.postCustomerAccountCDMOUTResp(payload);
			Verify.verifyStatusCode(customerCDMOUTResp,200);
			common.bdiCustomerCDMOUTResp(customerCDMOUTResp);
			Verify.verifyResponseTime(customerCDMOUTResp);
		}
	
	@Test(description= "Customer_CDMP_API-BAD Response")
	public void tc11_Customer_CDMP_API_BADResponse() {
			CommonAPI common = new CommonAPI("COMMON");
			String payload= "{\"CustomerNumber\":\"020196\",\"SourceSystem\":\"BDI\"}";
			Api customerCDMOUTResp = common.postCustomerAccountCDMOUTResp(payload);
			Verify.verifyStatusCode(customerCDMOUTResp,200);
			common.customerCDMOUTBADResp(customerCDMOUTResp);
			Verify.verifyResponseTime(customerCDMOUTResp);
		}
	
	@Test(description= "Customer_Out_Publisher - Valid Response")
	public void tc12_Customer_Out_Publisher_ValidResonse() {
			CommonAPI common = new CommonAPI("COMMON");
			Api customerOutSubscribe = common.postCustCommTopicOutPublisherResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(customerOutSubscribe,200);
			common.customerOutPublisherValidResp(customerOutSubscribe);
			Verify.verifyResponseTime(customerOutSubscribe);
		}
	
	@Test(description= "Customer_Out_Subscriber - Valid Response")
	public void tc13_Customer_Out_Subscriber_ValidResonse() {
			CommonAPI common = new CommonAPI("COMMON");
			Api customerOutSubscribe = common.postCustCommTopicOutSubscriberResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(customerOutSubscribe,200);
			//common.CustomerOutSubscriberValidResp(customerOutSubscribe);
			Verify.verifyResponseTime(customerOutSubscribe);
		}
	
	//Dev team is working on this to connect to Hybris
	//@Test(description= "Customer_Out_Subscriber - NoPendingData Response")
	public void tc14_Customer_Out_Subscriber_NoPendingData() {
			CommonAPI common = new CommonAPI("COMMON");
			Api customerOutSubscribe = common.postCustCommTopicOutSubscriberResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(customerOutSubscribe,200);
			common.customerOutSubscriberFailedResp(customerOutSubscribe);
			Verify.verifyResponseTime(customerOutSubscribe);
		}
	


}
