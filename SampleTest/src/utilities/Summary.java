package utilities;

public class Summary {
	private String testCase;
	private String description;
	private String result;
	private String comments;
	private int retry;
	
	public Summary() {
		this.testCase = "NA";
		this.description="NA";
		this.result="NA";
		this.comments="";
		this.retry=1;
	}
	
	public String getTestCase() {
		return testCase;
	}
	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getRetry() {
		return retry;
	}
	public void setRetry(int retry) {
		this.retry = retry;
	}
	
	
}
