package avatarAPI;

import org.testng.annotations.Test;

import api.BDIProduct;
import api.CommonAPI;
import api.JEPPProduct;
import base.BaseTest;
import utilities.Api;
import utilities.Utils;
import utilities.Verify;

public class ProductAPI extends BaseTest {
	
	@Test(description= "JEPP Product Items_Landing Zone API - JEPP-200 Error")
	public void tc15_JEPP_ProductItems_LandingZone_JEPP200() {
			JEPPProduct jepp = new JEPPProduct("Product");
			Api productJEPPLandingZone = jepp.postProductItemLandZoneResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(productJEPPLandingZone,200);
			jepp.jeppProditemLandZoneResp(productJEPPLandingZone);
			Verify.verifyResponseTime(productJEPPLandingZone);
		}
	
	@Test(description= "JEPP Product Items_Landing Zone API - Bad Request Response")
	public void tc16_JEPP_ProductItems_LandingZone_BadRequest() {
			JEPPProduct jepp = new JEPPProduct("Product");
			Api productJEPPLandingZone = jepp.postProductItemLandZoneResp(Utils.getPayload("bdiCustLandZoneBadRequest.xml"));
			Verify.verifyStatusCode(productJEPPLandingZone,400);
			Verify.verifyResponseTime(productJEPPLandingZone);
		}
	
	@Test(description= "JEPP Product Items_Common Topic IN API - No new Record Response")
	public void tc17_JEPP_ProductItems_CommonTopicIN_NoNewRecord() {		
			JEPPProduct jepp = new JEPPProduct("Product");
			Api productJEPPCommTopicIN = jepp.postProductCommTopicResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(productJEPPCommTopicIN,200);
			jepp.jeppProditemCommTopicResp(productJEPPCommTopicIN);
			Verify.verifyResponseTime(productJEPPCommTopicIN);
		}
	
	@Test(description= "BDI Product CMIR_Landing Zone API")
	public void tc18_BDI_ProductCMIR_LandingZone() {
			BDIProduct bdi = new BDIProduct("Product");		
			Api productBDILanding = bdi.postProductCMIRLanding(Utils.getPayload("BDICMIRProduct.xml"));
			Verify.verifyStatusCode(productBDILanding,200);
			bdi.bdiProditemCMIRLandingResp(productBDILanding);
			Verify.verifyResponseTime(productBDILanding);
		}
	
	@Test(description= "BDI Product Part Supersession_Landing Zone API")
	public void tc19_BDI_PartSupersession_LandingZone() {
			BDIProduct bdi = new BDIProduct("Product");		
			Api productBDILanding = bdi.postProdPartSupersessionLand(Utils.getPayload("BDIProductPartSupersession.xml"));
			Verify.verifyStatusCode(productBDILanding,200);
			bdi.bdiProdPartSupersessionLandResp(productBDILanding);
			Verify.verifyResponseTime(productBDILanding);
		}

	@Test(description= "BDI Product Part _Landing Zone API")
	public void tc20_BDI_PartProduct_LandingZone() {
			BDIProduct bdi = new BDIProduct("Product");		
			Api productBDILanding = bdi.postProdPartSupersessionLand(Utils.getPayload("BDIProductPart.xml"));
			Verify.verifyStatusCode(productBDILanding,200);
			bdi.bdiProdPartSupersessionLandResp(productBDILanding);
			Verify.verifyResponseTime(productBDILanding);
		}
	
	@Test(description= "BDI Product Sync Part _Landing Zone API")
	public void tc21_BDI_SyncProduct_LandingZone() {
			BDIProduct bdi = new BDIProduct("Product");		
			Api productBDILanding = bdi.postSyncPartLanding(Utils.getPayload("BDIProductSyncPart.xml"));
			Verify.verifyStatusCode(productBDILanding,200);
			bdi.bdiSyncPartLandResp(productBDILanding);
			Verify.verifyResponseTime(productBDILanding);
		}
	
	@Test(description= "BDI Product Items_Common Topic IN API - Valid Response")
	public void tc22_BDI_ProductItems_CommonTopicIN() {		
			BDIProduct bdi = new BDIProduct("Product");
			Api productJEPPCommTopicIN = bdi.postProductCommTopicResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(productJEPPCommTopicIN,200);
			bdi.bdiProductCommTopicResp(productJEPPCommTopicIN);
			Verify.verifyResponseTime(productJEPPCommTopicIN);
		}
	
	@Test(description= "BDI Product Items_Common Topic IN API - No new Record Response")
	public void tc23_BDI_ProductItems_CommonTopicIN_NoNewRecord() {		
			BDIProduct bdi = new BDIProduct("Product");
			Api productJEPPCommTopicIN = bdi.postProductCommTopicResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(productJEPPCommTopicIN,200);
			bdi.bdiProductCommTopicRespNoNewProduct(productJEPPCommTopicIN);
			Verify.verifyResponseTime(productJEPPCommTopicIN);
		}
	
	@Test(description= "JEPP Product Model_ Landing Zone API - Valid Response")
	public void tc24_JEPP_ProductModel_LandingZone() {		
			JEPPProduct jepp = new JEPPProduct("Product");
			Api productJEPPModelLandZone = jepp.postProductModelLandZoneResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(productJEPPModelLandZone,200);
			jepp.jeppProdModelLandZoneResp(productJEPPModelLandZone);
			Verify.verifyResponseTime(productJEPPModelLandZone);
		}
	
	@Test(description= "JEPP Product Model_ Common Topic IN API - Valid Response")
	public void tc25_JEPP_ProductModel_CommonTopicIN() {		
			JEPPProduct jepp = new JEPPProduct("Product");
			Api productJEPPModelCommonTopicIn = jepp.postProductModelCommonTopicINResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(productJEPPModelCommonTopicIn,200);
			jepp.jeppProdModelCommonTopicINResp_WithRecord(productJEPPModelCommonTopicIn);
			Verify.verifyResponseTime(productJEPPModelCommonTopicIn);
		}
	
	@Test(description= "JEPP Product Model_ Common Topic IN API - No new Record Response")
	public void tc26_JEPP_ProductModel_CommonTopicIN_NoNewRecord() {		
			JEPPProduct jepp = new JEPPProduct("Product");
			Api productJEPPModelCommonTopicIn = jepp.postProductModelCommonTopicINResp(Utils.getPayload("emptyPayload.json"));
			Verify.verifyStatusCode(productJEPPModelCommonTopicIn,200);
			jepp.jeppProdModelCommonTopicINResp_NoNewRecord(productJEPPModelCommonTopicIn);
			Verify.verifyResponseTime(productJEPPModelCommonTopicIn);
		}
	
	@Test(description= "BDI Product CDMP API")
	public void tc27_BDI_Product_CDMP_API() {
		CommonAPI common = new CommonAPI("COMMON");
		String payload= "{\"IN_ProductNumber\":\"641742=0H\",\"IN_SourceSystemName\":\"BDI\"}";
		Api bdiProductCDMOUTResp = common.postProductCDMOUTResp(payload);
		Verify.verifyStatusCode(bdiProductCDMOUTResp,200);
		common.bdiProductCDMOUTResp(bdiProductCDMOUTResp);
		Verify.verifyResponseTime(bdiProductCDMOUTResp);
	}
	
	@Test(description= "Product CDMP API InvalidResponse")
	public void tc28_Product_CDMP_API_InvalidResponse() {
		CommonAPI common = new CommonAPI("COMMON");
		String payload= "{\"IN_ProductNumber\":\"641742=0H\",\"IN_SourceSystemName\":\"BDSI\"}";
		Api productCDMOUTBadResponse = common.postProductCDMOUTResp(payload);
		Verify.verifyStatusCode(productCDMOUTBadResponse,200);
		common.productCDMOUTBadResp(productCDMOUTBadResponse);
		Verify.verifyResponseTime(productCDMOUTBadResponse);
	}
	
}
