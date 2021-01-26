package Demo;

public class SampleException {
	public static void main(String args[]) {
		
		int a,c, b;		
		try {
			
			a=102;
			b= 0;
			c= a/b;
			System.out.println("try block executed");
		}
		
		catch(NumberFormatException e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		
			
		}
		catch(Exception e) {
			System.out.println("the exception is :"+e);
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		
		
	
	}
		
	}
	
		
}	
	
	
	//errors are caused during run time(JVM)
	//when exception occurs they are pushed in to stack-stopping prog abnormally and print error msg on screen
	//stack tracing-poping err msg
	//null pointer
	//Array out of bound
	//number format.
	//multiple catch blocks
	//one super class exception would suffice.
	//finalize is method.
	//final and finally are keywords
	//finally{
	//FINAL CODE TO BE EXECUTED}
	// final-> constant.
	//element not visible
	//element not clickable-- location
	//Stale element
	
	 

