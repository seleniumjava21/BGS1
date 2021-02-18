package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class TestMonitor implements  ITestListener {

	
	
	
	public void onTestStart(ITestResult result) {
		
		
		
	}

	public void onTestSuccess(ITestResult result) {
		//afterTest(result);
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test *** failed");
		/*String tcName = result.getName();
		ThreadDriver.testLog.get().createNode("Test Execution Ended :- "+tcName )
						.fail(result.getThrowable());
		ThreadDriver.testLog.remove();*/
		
	}

	public void onTestSkipped(ITestResult result) {
		//afterTest(result);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
				
	}

	public void onFinish(ITestContext context) {
		//
	}
	
	private void afterTest(ITestResult result) {
		String tcName = result.getName();
		ThreadManager.testLog.get().createNode("Test Execution Ended :- "+tcName );
		ThreadManager.testLog.remove();
	}

	
}
