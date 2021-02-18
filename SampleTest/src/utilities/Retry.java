package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	 
    private int count = 1;
    private static int maxTry = Integer.parseInt(System.getProperty("retry"));
 
    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            System.out.println("Retrying " + result.getName() + " again and the count is " + (count+1));
            count++;
            return true;
        }
        return false;
    }
 
}
