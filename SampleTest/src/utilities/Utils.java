package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.path.json.JsonPath;

public class Utils {
	
	
	private static PrintStream ps;
	public final int COVERAGE_ANNUAL = 364;
	public final int COVERAGE_ONETIME = 27;
	public static  final String apiTestDataFile = System.getProperty("user.dir")
			+ "/TestData/apiTestData.json";
	public static  final String payload = System.getProperty("user.dir")
			+ "/TestData/avatar/";

	private static String getTestData() {
		return System.getProperty("user.dir") + "/TestData/";
	}
	
	public static JsonPath getEnvUrl() {
		JsonPath json = new JsonPath(new File(getTestData()+"env.json"));
		return json;
	}
	
	public static JsonPath getShippingAddres() {
		JsonPath json = new JsonPath(new File(getTestData()+"shippingaddress.json"));
		return json;
	}
	
	public static JsonPath getLogin() {
		JsonPath json = new JsonPath(new File(getTestData()+"login.json"));
		return json;
	}
	
	public static JsonPath getProduct() {
		JsonPath json = new JsonPath(new File(getTestData()+"search.json"));
		return json;
	}

	public static JsonPath getPaymentType() {
		JsonPath json = new JsonPath(new File(getTestData()+"paymenttype.json"));
		return json;
	}
	
	public static JsonPath getRegistration() {
		JsonPath json = new JsonPath(new File(getTestData()+"registration.json"));
		return json;
	}
	
	public static JsonPath getMyAccount() {
		JsonPath json = new JsonPath(new File(getTestData()+"myAccount.json"));
		return json;
	}
	
	public static JsonPath getAircraftDetails() {
		JsonPath json = new JsonPath(new File(getTestData()+"aircraftdetails.json"));
		return json;
	}
	
	public static JsonPath getPayInvoice() {
		JsonPath json = new JsonPath(new File(getTestData()+"payinvoice.json"));
		return json;
	}
	
	public static JsonPath getDashBoardInfo() {
		JsonPath json = new JsonPath(new File(getTestData()+"dashboardInfo.json"));
		return json;
	}
	
	public String getOnlyDigitsFromString(String str) {
		return str.replaceAll("[^0-9]", "").trim();
	}
	public String generateEmail() {
		String defaultText = "@bgs.com";
		String email = "BGS";
		LocalTime time = LocalTime.now();
		String randomint = time.toString().replace(".", "").replace(":", "");
		String randomString = randomint.toString();
		String randomemail = email + randomString;
		return randomemail+defaultText;
	}
	
	public String Generate_Random_Email()
	{
		String newemailID = null;
		String tempEmailID = null;
		String randomEmailID = null;

		String currentTime = getTimestamp();	
		tempEmailID = "tripannual";
		newemailID = tempEmailID + currentTime;
		randomEmailID = newemailID + "@random.com";
		return randomEmailID;
	}
	
	public String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * get current date in the custom pattern
	 */
	public String getCurrentDate(String pattern) {
		return formatDate(new Date(), pattern);
	}
	
	public String getTimestamp() {
		return getCurrentDate("ddHHmms");
	}
	
	public String getLocalTime() {
		LocalTime time = LocalTime.now();
		return time.toString().replace(".", "").replace(":", "");
	}
	
	public String Get_Current_Date()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date).replace("-", " ");
		return date1;
	}
	
	public String Get_Annual_Date_From_Current()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c= Calendar.getInstance();
		c.add(Calendar.DATE, COVERAGE_ANNUAL);
		Date d=c.getTime();
		String annualDate = dateFormat.format(d).replace("-", " ");
		return annualDate;
	}
	
	public String Get_OneTime_Date_From_Current()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c= Calendar.getInstance();
		c.add(Calendar.DATE, COVERAGE_ONETIME);
		Date d=c.getTime();
		String oneTimeDate = dateFormat.format(d).replace("-", " ");
		return oneTimeDate;
	}
	
	public synchronized static String getPropertyValue(String path) {
		Properties prop = null;
		File src = null;
		String osName = System.getProperty("os.name").toLowerCase();
		try {
			prop = new Properties();
			if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") >= 0) {
				src = new File("/apps/BGS/files/creds.properties");
			} else {
				src = new File("C:\\app\\BGS\\files\\creds.properties");
			}

			FileInputStream fin = new FileInputStream(src);
			prop.load(fin);
		} catch (Exception e) {
			throw new CustomException("Unable to access password Properties File " + e);
		}
		return prop.getProperty(path);
	}
	
	
	

	/**
	 * Return PrintStream object
	 */

	public static PrintStream getPrintStream(){
		return ps;
	}

	/**
	 * close printstream object
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static void closePrintStream(){
		ps.close();
	}




	public static PrintStream logPrintStream(String testCaseName)  {
		String resultFolderPath =Reporting.LOGS;
		try {
			Files.createDirectories(Paths.get(resultFolderPath));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String logfilePath = resultFolderPath + File.separator +testCaseName+".txt";	
		File file =  new File(logfilePath);
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ps = new PrintStream(fout);
		return ps;
	}
	
	
	public static String getPayload(String fileName) {
		File file = new File(payload+File.separator+fileName);
		FileInputStream fis = null;
		StringBuilder sb = new StringBuilder();
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String line;
		try {
			while((line = br.readLine()) != null){
				
				sb.append(line);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		return sb.toString();
		
	}
	
	
	public static Map<String, Object> getPathParamObject(){
		return new HashMap<String, Object>();
	}
	
	public static HashMap<String, Object> getQueryParam() {
		return new HashMap<String, Object>();

	}
	
	/*public static Object getPayLoad(String path) {
		File file = new File(GlobalConstants.payload+File.separator+"payloads.json");
		io.restassured.path.json.JsonPath json = new io.restassured.path.json.JsonPath(file);
		return json.getJsonObject(path);
		
		
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String getEndpoint(String url) {
		String[] split = url.split(",");
		String env = System.getProperty("env");
		switch(env) {
		case "QA" : return split[0];
		case "STAGE" : return split[0];
		case "QA2" : return split[0];
		case "DEV" : return split[1];
		case "VAL" : return split[1];
		case "AZDEV": return split[2];
		
		default :
		System.out.println("******* Invalid Environment **********");
		throw new CustomException("Environment not choosen");
		}
	}
	
	
	
	
	public String getAccountThroughAPI(ConcurrentHashMap<String, String> testdata, String acntType){
		Random rand = new Random();
		int rand_int = rand.nextInt(10000);
		String user = getEndpoint( testdata.get("user"));
		String strURL = null;
		String POST_PARAMS = null;
		if (acntType.toUpperCase().equalsIgnoreCase("JUSPERSONAL")) {

			strURL = testdata.get("createPersonalAccountURL");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"

					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"PersonFlag\": \"Y\",\r\n"
					+ "    \"PersonFirstName\": \"John\",\r\n" + "    \"PersonLastName\": \"Doe\",\r\n"
					+ "    \"IssuingCountry\": \"JUS\",\r\n" + "    \"SourceSystem\": \"BDA\",\r\n"
					+ "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int + "\"" + "	 }" + "\n}";
		} else if (acntType.toUpperCase().equalsIgnoreCase("JUSCOMPANY")) {
			strURL = testdata.get("createCompanyAccountURL");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"
					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"CustomerName\": \"TEST AIRLINES\",\r\n"
					+ "    \"PersonFlag\": \"N\",\r\n" + "    \"IssuingCountry\": \"JUS\",\r\n"
					+ "    \"SourceSystem\": \"BDA\",\r\n" + "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int
					+ "\"" + "	 }" + "\n}";
		} else if (acntType.toUpperCase().equalsIgnoreCase("JEUPERSONAL")) {
			strURL = testdata.get("createPersonalAccountURL");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"
					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"PersonFlag\": \"Y\",\r\n"
					+ "    \"PersonFirstName\": \"John\",\r\n" + "    \"PersonLastName\": \"Doe\",\r\n"
					+ "    \"IssuingCountry\": \"JEU\",\r\n" + "    \"SourceSystem\": \"BDA\",\r\n"
					+ "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int + "\"" + "	 }" + "\n}";
		} else if (acntType.toUpperCase().equalsIgnoreCase("JEUCOMPANY")) {
			strURL = testdata.get("createCompanyAccountURL");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"
					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"CustomerName\": \"TEST AIRLINES\",\r\n"
					+ "    \"PersonFlag\": \"N\",\r\n" + "    \"IssuingCountry\": \"JEU\",\r\n"
					+ "    \"SourceSystem\": \"BDA\",\r\n" + "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int
					+ "\"" + "	 }" + "\n}";
		} else if (acntType.toUpperCase().equalsIgnoreCase("JAUPERSONAL")) {
			strURL = testdata.get("createPersonalAccountURL");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"

					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"PersonFlag\": \"Y\",\r\n"
					+ "    \"PersonFirstName\": \"sri\",\r\n" + "    \"PersonLastName\": \"lekha\",\r\n"
					+ "    \"IssuingCountry\": \"JAU\",\r\n" + "    \"SourceSystem\": \"BDA\",\r\n"
					+ "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int + "\"" + "	 }" + "\n}";

		} else if (acntType.toUpperCase().equalsIgnoreCase("JAUCOMPANY")) {
			strURL = testdata.get("createCompanyAccountURL");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"
					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"CustomerName\": \"TEST AIRLINES\",\r\n"
					+ "    \"PersonFlag\": \"N\",\r\n" + "    \"IssuingCountry\": \"JAU\",\r\n"
					+ "    \"SourceSystem\": \"BDA\",\r\n" + "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int
					+ "\"" + "	 }" + "\n}";
		}

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		}

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		String encoding = null;
		try {
			encoding = Base64.getEncoder().encodeToString((user).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		URL obj = null;
		try {
			String endpoint = getEndpoint(strURL);
			obj = new URL(endpoint);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpURLConnection postConnection = null;
		try {
			postConnection = (HttpURLConnection) obj.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			postConnection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		postConnection.setRequestProperty("Authorization", "Basic " + encoding);

		OutputStream os = null;
		try {
			os = postConnection.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.write(POST_PARAMS.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int responseCode = 0;
		try {
			responseCode = postConnection.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (responseCode == 200) { // success HttpURLConnection.HTTP_CREATED
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			try {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String[] Accntnum = response.toString().split("\"CustomerNumber\":\"");
				String[] Accntnum1 = Accntnum[1].split("\"}");
				Double accountNum =(double) Integer.parseInt(Accntnum1[0].toString());
				String account = accountNum.toString();
				String acc = account.substring(0, 7);
				ThreadManager.logger.get().info("Account Number from API :- "+acc);
				return acc;
			} catch (Exception e) {
				ThreadManager.logger.get().fail(MarkupHelper.createLabel("Account Number Creation failed via API", ExtentColor.RED));
				throw new CustomException("POST Account number not successfull",e);
			}
		} else {
			ThreadManager.logger.get().fail(MarkupHelper.createLabel("Account Number Creation failed via API", ExtentColor.RED));
			throw new CustomException("POST Account number not successfull");
			
		}
	}
	
	public float priceExtract(String price) {
		price = price.replaceAll("[^\\d.]", "");
		//price = price.substring(0);
		ThreadManager.logger.get().info("Extracted price is :- "+price);
		return Float.parseFloat(price);
	}
	
	public static String readJsonRespArrayObjValue(Api response, String parentNode, String ChildNode) {
		String data="";
		try {
			JSONObject myResp= new JSONObject(response.getResp().asString());
			JSONArray respArray=(JSONArray) myResp.get(parentNode);
			JSONObject obj=(JSONObject) respArray.get(0);
			data=(String) obj.get(ChildNode);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	

	public static String readJsonResStringObjectValue(Api response, String node) {
		String data="";
		try {
			JSONObject myResp= new JSONObject(response.getResp().asString());
			data=(String) myResp.get(node);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String readJsonFileArrayObjValue(String fileName, String parentNode, String ChildNode) throws ClassNotFoundException {
		
		 JsonPath value = new JsonPath(new File(payload+File.separator+fileName));
		 
		 ConcurrentHashMap<String, Object> obj = (ConcurrentHashMap<String, Object>) value.getObject(parentNode,
					Class.forName("java.util.concurrent.ConcurrentHashMap"));
		  String data=(String) obj.get(ChildNode);
		
		  return data;
		
	}
	
	//To read Nested JSON Array Object Value Ex: parentNode[nestedParentNode[{childNode}]] 
	public static String readNestedJsonRespMultiArrayObjValue(Api response, String parentNode, String nestedParentNode, String childNode) {
		String data="";
		try {
			JSONObject myResp= new JSONObject(response.getResp().asString());
			JSONArray respArray=(JSONArray) myResp.get(parentNode);
			JSONObject obj=(JSONObject) respArray.get(0);
			//Nested JsonArray
			JSONObject myResp1= new JSONObject(obj.toString());
			JSONArray nestedArray=(JSONArray) myResp1.get(nestedParentNode);
			JSONObject obj1=(JSONObject) nestedArray.get(0);
			data=(String) obj1.get(childNode);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	//To read Nested JSON Array Object Value Ex: parentNode[nestedParent:{childNode}] 
	public static String readNestedJsonRespArrayObjValue(Api response, String parentNode, String nestedParentNode, String childNode) {
		String data="";
		try {
			JSONObject myResp= new JSONObject(response.getResp().asString());
			JSONArray respArray=(JSONArray) myResp.get(parentNode);
			JSONObject obj=(JSONObject) respArray.get(0);
			//Nested JsonObject
			JSONObject myResp1= new JSONObject(obj.toString());
			JSONObject obj1=(JSONObject) myResp1.get(nestedParentNode);;
			data=(String) obj1.get(childNode);	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// To read Json Array Object Value with Child Object Number
	public static String readJsonRespArrayObjValueChildObjNum(Api response, String parentNode,int childObject, String ChildNode) {
		String data="";
		try {
			JSONObject myResp= new JSONObject(response.getResp().asString());
			JSONArray respArray=(JSONArray) myResp.get(parentNode);
			JSONObject obj=(JSONObject) respArray.get(childObject);
			data=(String) obj.get(ChildNode);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	//To read Nested JSON Array Object Value Ex: parentNode[nestedParentNode1[nestedParentNode1{childNode}]] 
		public static String readNestedJsonRespMultiArrayObjValue(Api response, String parentNode, String nestedParentNode1,String nestedParentNode2, String childNode) {
			String data="";
			try {
				JSONObject myResp= new JSONObject(response.getResp().asString());
				JSONArray respArray=(JSONArray) myResp.get(parentNode);
				JSONObject obj=(JSONObject) respArray.get(0);
				//Nested JsonArray1
				JSONObject myResp1= new JSONObject(obj.toString());
				JSONArray nestedArray1=(JSONArray) myResp1.get(nestedParentNode1);
				JSONObject obj1=(JSONObject) nestedArray1.get(0);
				//Nested JsonArray2
				JSONObject myResp2= new JSONObject(obj1.toString());
				JSONArray nestedArray2=(JSONArray) myResp2.get(nestedParentNode2);
				JSONObject obj2=(JSONObject) nestedArray2.get(0);
				data=(String) obj2.get(childNode);
			} catch(Exception e) {
				e.printStackTrace();
			}
			return data;
		}
		
}
