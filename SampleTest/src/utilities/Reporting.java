package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLEngineResult.Status;

import org.openqa.selenium.By;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.path.json.JsonPath;

public class Reporting {

	public static final String LOGS = System.getProperty("user.dir")+File.separator+"ExtentReports"+File.separator+"Screenshots";
	protected ExtentTest logger = ThreadManager.logger.get();
	protected static ExtentHtmlReporter htmlreport;
	protected static ExtentReports report;
	private static String password;
	private static String suite_name;
	private static String threadCount;
	protected String URL ;
	protected String baseURI ;
	long implicitWait = Long.parseLong(System.getProperty("time"));
	
	protected String tempVariable;
	protected static ConcurrentHashMap<String, Summary> dashInfo;
	protected static Instant start;
	protected static Instant end;
	public static CopyOnWriteArrayList<String> pool;
	
	protected void startReporting() {
				start = Instant.now();
				String dir = "user.dir";
				clearScreenshots();
				File extentReportPath = new File(System.getProperty(dir) + "//ExtentReports//extentReport.html");
				if (!extentReportPath.exists()) {
					try {
						extentReportPath.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				htmlreport = new ExtentHtmlReporter(extentReportPath);
				htmlreport.loadXMLConfig(System.getProperty(dir) + "//extent-config.xml");
				htmlreport.config().setTheme(Theme.STANDARD);
				htmlreport.config().setDocumentTitle("BGS MarketPlace Report");
				htmlreport.config().setEncoding("utf-8");
				htmlreport.config().setReportName("BGS MarketPlace Automation Report");
				htmlreport.config().setCSS("css-string");
				htmlreport.config().setJS("js-string");
				report = new ExtentReports();
				report.setSystemInfo("Current Location", System.getProperty(dir));
				report.setSystemInfo("Browser", System.getProperty("browser"));
				report.attachReporter(htmlreport);	

				//dashboard		
				dashInfo = new ConcurrentHashMap<String, Summary>();
				password = Utils.getPropertyValue("password");
	}
	
	
	public synchronized static String getPassword() {
		return password;
	}
	
	
	protected void endReport(String suiteName, int tcount) {
		report.flush();
		end = Instant.now();
		suite_name = suiteName.substring(0,1).toUpperCase() + suiteName.substring(1).toLowerCase();;
		threadCount = Integer.toString(tcount);
		generateDashBoard();
	}


	

	protected void startTest(Method result, ITestResult res, String env ) {
		String str = result.getDeclaringClass().getName().toString().substring(6);
		String testType= System.getProperty("testType");
		String tcName = result.getName();
		Summary sum = dashInfo.get(str+tcName);
		if(null==sum) {
			sum = new Summary();
		}else {			
			sum.setRetry(2);
			System.out.println(str+tcName +":::"+sum.getRetry());
		}
		 
		ThreadManager.dash.set(sum);
		ExtentTest logger = report.createTest(tcName);
		ExtentTest node = logger.createNode("Test Execution started :- " + tcName);
		System.out.println("Test Started :-"+tcName+ " - "+ Thread.currentThread().getId());
		ThreadManager.dash.get().setTestCase(tcName);
		
		if(testType.contains("API") ) {
			Utils.logPrintStream(tcName);
		
		} else {

			ThreadManager.getInstance().getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			ThreadManager.getInstance().getDriver().manage().window().maximize();
			node.info("Browser navigated to :- " + env);
			System.out.println("Browser navigated to :- " + env+ " - "+ Thread.currentThread().getId());
			node.info("Test Description:- "+res.getMethod().getDescription());
			System.out.println("Test Description:- "+res.getMethod().getDescription()+ " - "+ Thread.currentThread().getId());
			ThreadManager.getInstance().getDriver().get(env);
			
		}
		
		ThreadManager.testLog.set(logger);
		ThreadManager.testLog.get().assignCategory(str);
		ThreadManager.logger.set(null);
	}


	protected void endTest(Method res, ITestResult result) {
		String softAssertResult = ThreadManager.testLog.get().getStatus().toString(); 
		Throwable throwable = result.getThrowable();
		String tcName = result.getName();
		String testType= System.getProperty("testType");
		System.out.println("Test Ended :-"+tcName + " - "+ Thread.currentThread().getId());
		ThreadManager.dash.get().setDescription(result.getMethod().getDescription());
	
		if((testType.contains("API"))) {		
			ThreadManager.testLog.get().createNode("Detailed Logs").log(Status.INFO, new ScreenshotLink("Screenshots"+File.separator+tcName+".txt", "logs"));
			if(result.getStatus()==ITestResult.SUCCESS && softAssertResult.equals("pass")) {	
				ThreadManager.testLog.get().createNode("Test Execution Ended :- "+tcName,"Test Case PASSED" );		
				ThreadManager.dash.get().setResult("PASSED");
				System.out.println("Test Passed :- "+tcName + " - "+ Thread.currentThread().getId());
			}else {
				ThreadManager.testLog.get().createNode("Test Execution FAILED :- "+tcName, "Test Case FAILED" )
				.fail("Soft Assertion Failed");
				ThreadManager.dash.get().setResult("FAILED");
			}
			
		}else {
				PageAction act = new PageAction();
				if(result.getStatus()==ITestResult.SUCCESS && softAssertResult.equals("pass")) {	
				ThreadManager.testLog.get().createNode("Test Execution Ended :- "+tcName,"Test Case PASSED" );		
				ThreadManager.dash.get().setResult("PASSED");
				System.out.println("Test Passed :- "+tcName + " - "+ Thread.currentThread().getId());
			}else if(null==throwable){
				ThreadManager.testLog.get().createNode("Test Execution FAILED :- "+tcName, "Test Case FAILED" )
				.fail("Soft Assertion Failed");
				ThreadManager.dash.get().setResult("FAILED");
				System.out.println("Test Failed :- "+tcName + " - "+ Thread.currentThread().getId());
			}else {
				ThreadManager.testLog.get().createNode("Test Execution FAILED :- "+tcName, "Test Case FAILED" )
				.fail(throwable);	
				act.takeScreenshot();
				ThreadManager.dash.get().setResult("FAILED");
				System.out.println("Test Failed :- "+tcName + " - "+ Thread.currentThread().getId());
			}
			ThreadManager.getInstance().removeDriver();
		}
		
		ThreadManager.logger.remove();
		ThreadManager.testLog.remove();		
		String str = res.getDeclaringClass().getName().toString().substring(6);
		dashInfo.put(str+tcName, ThreadManager.dash.get());

	}

	private void clearScreenshots() {
		try {
			File dir = new File("./ExtentReports/Screenshots/");
			for (File file : dir.listFiles()) {
				if (!file.isDirectory()) {
					if (file.getName().equals("DoNotRemove.txt")) {
						continue;
					}
					file.delete();
				}

			}

		} catch (Exception e) {
			e.getMessage();
		}
	}


	private void generateDashBoard() {
		
		int total = dashInfo.size();
		int pass = 0;
		int fail = 0;
		int retry= 0;
		String bn="";
	
		String totalTime = getExecutionDuration(start, end);
		
		String currentDate = new Utils().getCurrentDate("dd-MMM-YYYY  hh:mm:ss a zzz");
		
		for(Map.Entry<String, Summary> e : dashInfo.entrySet()) {
			Summary s = e.getValue();
			
			System.out.println("Value :"+s.getResult());
			if(s.getResult().contentEquals("FAILED")) {
				fail++;
				retry++;
			}else if(s.getResult().contentEquals("PASSED")) {
				pass++;
				if(s.getRetry()==2) {
					retry++;
				}
			}
			
		}
		
		int i=1;
		File path = new File(System.getProperty("user.dir") + "//ExtentReports//dashboard.html");
		String env = System.getProperty("env");
		String browser = System.getProperty("browser");
		String testType= System.getProperty("testType");
		String header;
		
		if(testType.contains("API")) {
		 bn =System.getProperty("testType")+" Test";
		 baseURI=Utils.getEnvUrl().getString(System.getProperty("testType")+"."+System.getProperty("env")+"."+"URI");
		 header = "<HTML> <HEAD> <meta charset='UTF-8'> <title>BGS DASHBOARD</title> <style type='text/css'>body { background-color: white; font-family: Verdana, Geneva, sans-serif; text-align: center; border: 1px solid black;} div { color:blue;font-size:0.8em;} small { font-size: 0.7em; } table { border: 1px solid black; border-collapse: collapse; border-spacing: 0px; width: 100%; margin-left: auto; margin-right: auto; } tr.heading { border:2px solid black; background-color: blue; color: #ffffff; font-size: 1em; font-weight: bold; } tr.subsection { cursor: pointer; } "
					+ "td { font-size:1em; border:2px solid black; padding: 0px; text-align: center; word-wrap: break-word; max-width: 450px } th { padding: 2px; text-align: left; word-break: break-all; max-width: 450px } td.justified { text-align: justify; } </style> </Head> <body> <table id='header'> <thead> <tr class='heading'> <th style='font-family:Copperplate Gothic Bold; text-align:left;font-size:1.2em;background-color:white;color:blue'> <Strong>"+currentDate+"</Strong> </th> <th style='font-family:Verdana;font-size:1.3em;text-align:center;background-color:white;color:blue'>BGS Marketplace automation summary report<br/>"+ suite_name + "  test - "+env+"</font></th> <th style='font-family:Copperplate Gothic Bold; text-align:right;font-size:1.2em;background-color:white;color:blue'><Strong>Total Execution Time - "+totalTime+"</Strong> </th> </tr> </thead> </table> <table> <tr style='font-family:Verdana;font-size:1.0em;background-color:black;color:white'> <td style = 'background-color:#f3e3c0;color:black'> <strong>Total Test Cases : "+total+"</strong> </td> <td style = 'background-color:#b6f8c2;color:black'> <strong>Total Passed : "+pass+"</strong> </td> <td style = 'background-color:#ff3333;font-family:Arial Black;color:black'> <strong>Total Failed : "+fail+"</strong> </td></tr><tr style='font-family:Verdana;font-size:1.0em;background-color:#f8f9ed;color:black'><td style = ''><strong>URL : "+baseURI+"</td><td style = 'color:#0505fd'><strong>Build : "+bn+"</td><td style = ''><strong>Number of Parallel Tests : "+threadCount+"</td></tr></table> <table id='main'> <tr style= 'font-family:Verdana;text-align:center;font-size:0.9em;color:white;background-color :#000000;'> <table> <tr style= 'font-family:Verdana;text-align:center;font-size:1em;color:black;background-color :#d8e0fa;'> <th style = 'text-align:Center;width:1%;border-right:1px solid black;border-left:1px solid black'>#</th> <th style = 'text-align:Center;width:9%;border-right:1px solid black;border-left:1px solid black'>Test Case</th> <th style = 'text-align:Center;width:13%;border-right:1px solid black;border-left:1px solid black'>Description</th> <th style = 'text-align:Center;width:8%;border-right:1px solid black;border-left:1px solid black'>Comments</th> <th style = 'text-align:Center;width:4%;border-right:1px solid black;border-left:1px solid black'>Result</th> </tr> </table> </td> </tr> <table>";
		 
		}else {
			bn = getBuildNo();
			 header = "<HTML> <HEAD> <meta charset='UTF-8'> <title>BGS DASHBOARD</title> <style type='text/css'>body { background-color: white; font-family: Verdana, Geneva, sans-serif; text-align: center; border: 1px solid black;} div { color:blue;font-size:0.8em;} small { font-size: 0.7em; } table { border: 1px solid black; border-collapse: collapse; border-spacing: 0px; width: 100%; margin-left: auto; margin-right: auto; } tr.heading { border:2px solid black; background-color: blue; color: #ffffff; font-size: 1em; font-weight: bold; } tr.subsection { cursor: pointer; } "
					+ "td { font-size:1em; border:2px solid black; padding: 0px; text-align: center; word-wrap: break-word; max-width: 450px } th { padding: 2px; text-align: left; word-break: break-all; max-width: 450px } td.justified { text-align: justify; } </style> </Head> <body> <table id='header'> <thead> <tr class='heading'> <th style='font-family:Copperplate Gothic Bold; text-align:left;font-size:1.2em;background-color:white;color:blue'> <Strong>"+currentDate+"</Strong> </th> <th style='font-family:Verdana;font-size:1.3em;text-align:center;background-color:white;color:blue'>BGS Marketplace automation summary report<br/>"+ suite_name + "  test - "+env+"</font></th> <th style='font-family:Copperplate Gothic Bold; text-align:right;font-size:1.2em;background-color:white;color:blue'><Strong>Total Execution Time - "+totalTime+"</Strong> </th> </tr> </thead> </table> <table> <tr style='font-family:Verdana;font-size:1.0em;background-color:black;color:white'> <td style = 'background-color:#f3e3c0;color:black'> <strong>Total Test Cases : "+total+"</strong> </td> <td style = 'background-color:#b6f8c2;color:black'> <strong>Total Passed : "+pass+"</strong> </td> <td style = 'background-color:#ff3333;font-family:Arial Black;color:black'> <strong>Total Failed : "+fail+"</strong> </td><td style = 'background-color:#c4faf8;font-family:Arial Black;color:black'><strong>Total Re-Executed : "+retry+"</strong></td> </tr><tr style='font-family:Verdana;font-size:1.0em;background-color:#f8f9ed;color:black'><td style = ''><strong>URL : "+URL+"</td><td style = 'color:#0505fd'><strong>Build : "+bn+"</td><td style = ''><strong>Number of Parallel Tests : "+threadCount+"</td><td style = ''><strong>Browser : "+browser+"</td></tr></table> <table id='main'> <tr style= 'font-family:Verdana;text-align:center;font-size:0.9em;color:white;background-color :#000000;'> <table> <tr style= 'font-family:Verdana;text-align:center;font-size:1em;color:black;background-color :#d8e0fa;'> <th style = 'text-align:Center;width:1%;border-right:1px solid black;border-left:1px solid black'>#</th> <th style = 'text-align:Center;width:9%;border-right:1px solid black;border-left:1px solid black'>Test Case</th> <th style = 'text-align:Center;width:13%;border-right:1px solid black;border-left:1px solid black'>Description</th> <th style = 'text-align:Center;width:8%;border-right:1px solid black;border-left:1px solid black'>Comments</th> <th style = 'text-align:Center;width:4%;border-right:1px solid black;border-left:1px solid black'>Result</th> </tr> </table> </td> </tr> <table>";
		}	
			
		
		String footer ="</table> </td> </table> </body> </HTML>";
		BufferedWriter writer = null;
		JsonPath jsonObj = Utils.getDashBoardInfo();
		
		try {
			writer = new BufferedWriter(new FileWriter(path, false));
		} catch (IOException e1) {
			System.out.println("Unable to create dasboard");
			e1.printStackTrace();
		}
		
	
	    try {
			writer.append(header);
			for(Map.Entry<String, Summary> e : dashInfo.entrySet())			
			{
				Summary s = e.getValue();
				
				if(s.getResult().contentEquals("FAILED")) {	
					//String customErrorInfo = ;
						String failed ="<tr style='font-family: Arial, Helvetica, sans-serif;font-size:0.9em;color:red;background-color:white'> <th style = 'border: 1px Solid Black;text-align:center;width:1%'>"+i+"</th> <th style = 'border: 1px Solid Black;text-align:left;width:9%'>"+s.getTestCase()+"</th> <th style = 'border: 1px Solid Black;text-align:left;width:13%'>"+s.getDescription()+"</th> <th style = 'border: 1px Solid Black;text-align:center;width:8%'>"+s.getComments()+"</th> <th style = 'border: 1px Solid Black;text-align:center;width:4%'>FAILED<div>"+getCustomErrorInfo(jsonObj, s.getTestCase(), s.getComments())+"</div></th></tr>";
						writer.append(failed);				
				}else if(s.getResult().contentEquals("PASSED")) {
					if(s.getRetry()==2) {
						String passed="<tr style='font-family: Arial, Helvetica, sans-serif;font-size:0.9em;color:black;background-color:#cefac4'> <th style = 'border: 1px Solid Black;text-align:center;width:1%'>"+i+"</th> <th style = 'border: 1px Solid Black;text-align:left;width:9%'>"+s.getTestCase()+"</th> <th style = 'border: 1px Solid Black;text-align:left;width:13%'>"+s.getDescription()+"</th> <th style = 'border: 1px Solid Black;text-align:center;width:8%'>"+s.getComments()+"</th> <th style = 'border: 1px Solid Black;text-align:center;width:4%'>PASSED</th></tr>";
						writer.append(passed);
					}else {
						String passed="<tr style='font-family: Arial, Helvetica, sans-serif;font-size:0.9em;color:black;background-color:white'> <th style = 'border: 1px Solid Black;text-align:center;width:1%'>"+i+"</th> <th style = 'border: 1px Solid Black;text-align:left;width:9%'>"+s.getTestCase()+"</th> <th style = 'border: 1px Solid Black;text-align:left;width:13%'>"+s.getDescription()+"</th> <th style = 'border: 1px Solid Black;text-align:center;width:8%'>"+s.getComments()+"</th> <th style = 'border: 1px Solid Black;text-align:center;width:4%'>PASSED</th></tr>";
						writer.append(passed);
					}
				}
				i++;
			}
			
		} catch (IOException e) {
			System.out.println("Unable to create dasboard");
			e.printStackTrace();
		}
	     
	    try {
	    	writer.append(footer);
			writer.close();
		} catch (IOException e) {
			System.out.println("Unable to create dasboard");
			e.printStackTrace();
		}		

	}
	
	
	private String getCustomErrorInfo(JsonPath json, String testcase, String comments) {
		Map<Object, Object> map = json.getMap(testcase);
		if(map != null) {
			if(map.get("error").equals(comments)) {
				return (String) map.get("dashBoardInfo");
			}
		}
		
		return "";
	}


	private String getExecutionDuration(Instant startTime, Instant endTime) {
		Duration timeElapsed = Duration.between(startTime, endTime);		
		long hours = timeElapsed.toHours();
		int minutes = (int) ((timeElapsed.getSeconds() % (60*60)) / 60);
		int seconds = (int) (timeElapsed.getSeconds() % 60);	
		String hr = Long.toString(hours);
		String min = Integer.toString(minutes);
		String sec = Integer.toString(seconds);
		if(minutes<10) {
			min = "0"+minutes;
		}
		if(hours<10) {
			hr="0"+hours;
		}
		if(seconds<10) {
			sec="0"+seconds;
		}
		String totalTime = hr+":"+min+":"+sec;
		return totalTime;
	}


	
	private String getBuildNo() {
		String buildNo = "Unable to get Build Number";
		try {
			String url =Utils.getEnvUrl().getString(System.getProperty("env"))+"release";
			ThreadManager.getInstance().getDriver().get(url);
			System.out.println("Launching release info page :- "+ url);
			ThreadManager.getInstance().getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//ThreadManager.getInstance().getDriver().manage().window().maximize();
			//buildNo = ThreadManager.getInstance().getDriver().findElement(By.xpath("//td[contains(text(),'bgs-release')]")).getText();
			//Get the Build Number irrespective of bgs-dev-release-xxx Or bgs-release-xx. 
			//Mostly this happens when the deployment is either from Dev OR Release branch
			buildNo = ThreadManager.getInstance().getDriver().findElement(By.cssSelector("body:nth-child(2) table.tbl tbody:nth-child(1) tr:nth-child(7) > td.builddata:nth-child(2)")).getText();
			ThreadManager.getInstance().removeDriverWithoutLog();
			if(buildNo.isEmpty()) {
				buildNo = "Build No is Empty";
			}
		} catch (Exception e) {
			System.out.println("Error in getting buildNo");
			System.out.println(e);
			e.printStackTrace();
			ThreadManager.getInstance().removeDriverWithoutLog();
		}
		
		return buildNo;
	}

}







