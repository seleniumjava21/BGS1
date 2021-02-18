package utilities;

import static io.restassured.RestAssured.given;

import java.io.PrintStream;
import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;

public class Api {
	
	protected  String baseURI ="";
	protected  String baseAuthUserName ="";
	protected  String baseAuthPassword ="";
	protected static PrintStream ps;
	protected static ExtentTest test;
	private Response resp;
	private ExtentTest node;
	

	protected Api(String username) {
		String env = System.getProperty("env");
		String testType= System.getProperty("testType");
		setBasicAuthUser(Utils.getEnvUrl().getString(testType+"."+env+"."+"AUTH"));
		ps=Utils.getPrintStream();
		baseURI=Utils.getEnvUrl().getString(testType+"."+env+"."+"URI");
	}
	
	private void setBasicAuthUser(String basicAuth) {
		String[] split=basicAuth.split(":");
		baseAuthUserName=split[0];
		baseAuthPassword=split[1];
	}
	
	
	protected Api(Response resp, ExtentTest node) {
		this.resp = resp;
		this.node = node;
	}

	

	public Response getResp() {
		return resp;
	}

	public ExtentTest getNode() {
		return node;
	}

	
	
	protected  RequestSpecBuilder requestSpecJson() {
		RequestSpecBuilder rsp = new RequestSpecBuilder();
		rsp.setBaseUri(baseURI);
		rsp.setContentType("application/json");
		rsp.setConfig(RestAssuredConfig.config().logConfig(new LogConfig(ps,true)));
		return rsp;
	}

	protected  RequestSpecBuilder requestSpecXML() {
		RequestSpecBuilder rsp = new RequestSpecBuilder();
		rsp.setBaseUri(baseURI);
		rsp.setContentType("application/xml");
		rsp.setConfig(RestAssuredConfig.config().logConfig(new LogConfig(ps,true)));
		return rsp;

	}
	protected  void printlineRequest() {
		ps.println("****** REQUEST *********************************************************************************");
		ps.println();
	}


	protected  void printlineResponse() {
		ps.println();
		ps.println("----------------------RESPONSE----------------------------------------");
		ps.println();
	}
	
	protected Api requestJSON(Method method, String resource,Object reqBody) {
		printlineRequest();
		try {
		Response resp = given()
				.auth().preemptive().basic(baseAuthUserName, baseAuthPassword)
				.relaxedHTTPSValidation("SSL")
				.spec(requestSpecJson().build())
				.body(reqBody)
				.log().all()
				.when()
				.request(method, resource)
				.andReturn();
		printlineResponse();
		resp.then().log().all();
		
		ps.println();
		
		ExtentTest node =  ThreadManager.testLog.get().createNode(method.name()+" - "+resource);
		String code = method.toString() + ":"+baseURI+resource;
		node.info(MarkupHelper.createCodeBlock(code));
		node.info(MarkupHelper.createCodeBlock(resp.getBody().prettyPrint(), CodeLanguage.JSON));
				
		return new Api(resp, node);
		
		}catch(Exception e) {
			ThreadManager.dash.get().setComments(e.toString());
			ThreadManager.logger.get().fail(MarkupHelper.createLabel(e.toString(), ExtentColor.RED));
			ThreadManager.logger.get().fail(e);	
			return new Api(resp, node);
		}	
			
	}

	protected Api requestXML(Method method, String resource,Object reqBody) {
		printlineRequest();		
		try {
		Response resp = given()
				.auth().preemptive().basic(baseAuthUserName, baseAuthPassword)
				.relaxedHTTPSValidation("SSL")
				.spec(requestSpecXML().build())
				.body(reqBody)
				.log().all()
				.when()
				.request(method, resource)
				.andReturn();
		printlineResponse();
		resp.then().log().all();
		
		ps.println();
		
		ExtentTest node =  ThreadManager.testLog.get().createNode(method.name()+" - "+resource);
		String code = method.toString() + ":"+baseURI+resource;
		node.info(MarkupHelper.createCodeBlock(code));
		node.info(MarkupHelper.createCodeBlock(resp.getBody().prettyPrint(), CodeLanguage.XML));
				
		return new Api(resp, node);
		
		}catch(Exception e) {
			ThreadManager.dash.get().setComments(e.toString());
			ThreadManager.logger.get().fail(MarkupHelper.createLabel(e.toString(), ExtentColor.RED));
			ThreadManager.logger.get().fail(e);	
			return new Api(resp, node);
		}	
			
	}

	protected Api requestJSON(Method method, String resource) {
		printlineRequest();
		try {
		Response resp = given()
				.auth().preemptive().basic(baseAuthUserName, baseAuthPassword)
				.relaxedHTTPSValidation("SSL")
				.spec(requestSpecJson().build())
				.log().all()
				.when()
				.request(method, resource)
				.andReturn();
		printlineResponse();
		resp.then().log().all();
		
		ps.println();
		
		ExtentTest node =  ThreadManager.testLog.get().createNode(method.name()+" - "+resource);
		String code = method.toString() + ":"+baseURI+resource;
		node.info(MarkupHelper.createCodeBlock(code));
		node.info(MarkupHelper.createCodeBlock(resp.getBody().prettyPrint(), CodeLanguage.JSON));
				
		return new Api(resp, node);
		
		}catch(Exception e) {
			ThreadManager.dash.get().setComments(e.toString());
			ThreadManager.logger.get().fail(MarkupHelper.createLabel(e.toString(), ExtentColor.RED));
			ThreadManager.logger.get().fail(e);	
			return new Api(resp, node);
		}	
			
	}



}
