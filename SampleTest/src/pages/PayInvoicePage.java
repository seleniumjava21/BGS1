package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.ThreadManager;

public class PayInvoicePage extends BasePage {
	public PayInvoicePage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		String simpleName = this.getClass().getSimpleName();
		if (!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			verify.pageTitle("Guest");

		}
	}

	String InvoiceNumber;
	String acctNo;
	String customerEmail;
	String invamt;
	String currency;
	private String payInvoiceLnk = "PAY INVOICE";
	By invoice_status = null;
	By color_ele = null;
	String myInvoicesClosed1 = "(//table[contains(@class,'table bg-white my-invoice-table open')]//tbody//tr)";
	String myInvoicesClosed2 = "//td[2]//span[1]";
	String InvoicePDFSite = "https://ebs-aventx-usw3-test.s3.amazonaws.com/";
	
		
	//Xpaths added for DABM-1621
	@FindBy(xpath = "(//div[contains(@id,'header')]//a[contains(@href,'/my-account/invoices')])[1]")
	private WebElement LNKHdrPayInvoice;
	
	@FindBy(xpath = "//a[contains(@href,'/my-account/invoices?invoiceStatus=OP')]")
	private WebElement LNKOpenInvoiceTab;
	
	@FindBy(xpath = "//a[contains(@href,'/my-account/invoices?sortOrder=desc&invoiceStatus=CL')]")
	private WebElement LNKClosedInvoiceTab;
	
	@FindBy(xpath = "//h3[contains(@class,'emptyMessage')]")
	private WebElement LBLEmptyClosedInvoices;
	
	@FindBy(xpath = "(//table[contains(@class,'table bg-white my-invoice-table open')]//tbody//td[2])[1]")
	private WebElement ClosedInvoicesStatus1;

	@FindBy(xpath = "(//table[contains(@class,'table bg-white my-invoice-table open')]//tbody//td[1])[1]")
	private WebElement ClosedInvoicesNumber1;

	@FindBy(xpath = "(//a[contains(@class,'btn-primary') and contains(@href,'/my-account/invoicesdetails?invoiceNumber')])[1]")
	private WebElement LNKPayOpenInvoice;
	

	
	@FindBy(xpath = "//h2[contains(text(),'Pay Invoice')]")
	private WebElement payinvoiceheader;
	@FindBy(id = "accountNumber")
	private WebElement accountnumber;
	@FindBy(id = "invoiceNumber")
	private WebElement invoicenumber;
	@FindBy(id = "invoice")
	private WebElement invoicenumberLoggedIn;
	@FindBy(xpath = "//a[@class='hint-text']")
	private WebElement invoiceexamplelink;
	@FindBy(xpath = "//button[@class='btn btn-primary btn-block']")
	private WebElement loginandpaybtn;
	@FindBy(xpath = "//button[@class='btn btn-info btn-block']")
	private WebElement paywithoutloginbtn;
	@FindBy(xpath = "//button[@id='retrieve']")
	private WebElement retrievebtn;
	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailguest;
	@FindBy(xpath = "//button[@class='btn btn-info btn-block']")
	private WebElement payInvoiceUnAuthButton;
	@FindBy(xpath = "//input[@id='signature']")
	private WebElement signature;

	@FindBy(xpath = "//input[@id='termsAgreement']")
	private WebElement termsAgreement;

	@FindBy(xpath = "//button[@id='payInvoiceButton']")
	private WebElement payInvoiceButton;

	@FindBy(xpath = "//div[@id='order-confirmation']")
	private WebElement payInvoiceConfirm;

	@FindBy(xpath = "//div[@id='notice']")
	private WebElement errorMsg;

	@FindBy(id = "j_username")
	private WebElement txt_username;
	@FindBy(xpath = "//button[@class='btn btn-primary btn-block login-btn']")
	private WebElement btn_login;
	@FindBy(id = "j_password")
	private WebElement txt_password;

	@FindBy(xpath = "//a[@title='My Invoices']")
	private WebElement MyInvoices;
	@FindBy(xpath = "//table[@class='table bg-white my-invoice-table open']/thead/tr/th")
	private List<WebElement> myInvHeaderName;
	@FindBy(xpath = "//a[contains(text(),'Closed')]")
	private WebElement ClosedMyInvoices;

	@FindBy(xpath = "//table[contains(@class,'table bg-white my-invoice-table open')]//tbody//td[2]")
	private List<WebElement> MyInvoiceStatus;

	@FindBy(xpath = "//table[contains(@class,'table bg-white my-invoice-table open')]//tbody//td[4]")
	private List<WebElement> duedateInvoicelist;

	@FindBy(xpath = "//i[@class='material-icons arrow_icon']")
	private WebElement ClosedMyInvoices_Link;

	@FindBy(xpath = "//div[@class='col-md-12 p-0 clearboth']")
	private WebElement ClosedMyInvoices_TextDisplayed;

	@FindBy(xpath = "//i[@class='material-icons arrow_icon active']")
	private WebElement ClosedMyInvoices_ActiveLink;

	@FindBy(xpath = "//table[contains(@class,'table bg-white my-invoice-table open')]//tbody//td[3]")
	private List<WebElement> amountInvoicelist;

	@FindBy(xpath = "//div[contains(@class,'pagination-bar bottom')]//ul[contains(@class,'pagination')]//li")
	private List<WebElement> myInvoicesPaginationlist;

	@FindBy(xpath = "//table[contains(@class,'table bg-white my-invoice-table open')]//tbody//td[1]")
	private List<WebElement> MyInvoiceslist;

	@FindBy(xpath = "(//li[(@class='pagination-prev')]//i[(@class='material-icons arrow-prev')])[2]")
	private WebElement historyPaginationPrev;

	@FindBy(xpath = "(//li[(@class='pagination-next')]//i[(@class='material-icons arrow-next')])[2]")
	private WebElement historyPaginationNext;

	@FindBy(xpath = "//i[@class='material-icons arrow-prev']")
	private WebElement myInvoicesPaginationPrev;

	@FindBy(xpath = "//tr[1]//td[5]//a[1]")
	private WebElement MyInvoicesPayButton;

	@FindBy(xpath = "//tr[1]//td[1]")
	private WebElement firstInvoiceNo_Closed;
	@FindBy(xpath = "//strong[contains(text(),'Invoice #')]")
	private WebElement ViewedInvoiceNo_Closed;
	@FindBy(xpath = "//div[@class='col-md-12 pb-2']//div[@class='pb-1']")
	private WebElement ViewedInvoiceStatus_Closed;
	String ClosedViewStatus = "//div[@class='col-md-12 pb-2']//div[@class='pb-1']";
	@FindBy(xpath = "//td[contains(text(),'Shipping Charges')]")
	private WebElement LBLInvoiceShipping;
	@FindBy(xpath = "//td[contains(text(),'Subtotal')]")
	private WebElement LBLInvoiceSubTotal;
	@FindBy(xpath = "//div[contains(@class,'pb-2')][contains(text(),'Tax')]")
	private WebElement LBLInvoiceTaxAmt;
	@FindBy(xpath = "//td[contains(text(),'Total in')]")
	private WebElement LBLTotalInvoiceAmount;

	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//span[contains(text(),'Total in')]")
	private WebElement LBLTotalBalanceAmount;
	
	@FindBy(xpath = "//div[contains(@class,'d-show-desktop')]//h4[contains(text(),'Terms & Conditions')]")
	private WebElement LBLPayInvoiceTnC;
	
	@FindBy(xpath = "//th[contains(text(),'Item number')]")
	private WebElement LBLInvoiceItemNo;
	@FindBy(xpath = "//th[contains(text(),'Service description')]")
	private WebElement LBLServiceDesc;
	@FindBy(xpath = "//th[contains(@class,'mb-2 text-center')]")
	private WebElement LBLInvoiceItemQty;
	@FindBy(xpath = "//th[contains(@class,'mb-2 text-right')]")
	private WebElement LBLItemAmount;
	
	private ArrayList<String> countryWithoutStates = new ArrayList<String>(Arrays.asList("Germany", "France", "New Zealand", "Brazil"));
	private ArrayList<String> countryWithTax = new ArrayList<String>(Arrays.asList("Brazil"));

	
	public PayInvoicePage MoveToClosedInvoiceTab()
	{
		if (verify.isElementDisplayed(LNKClosedInvoiceTab, "Closed Invoices Tab")) {
			action.clickWithClickableWait(LNKClosedInvoiceTab, "Click on Closed Invoices Tab");
		}
		return this;
	}
	
	public PayInvoicePage Validate_PayInvoiceScreen() {
		verify.isElementDisplayed(payinvoiceheader, "Pay Invoice Header");
		verify.isElementDisplayed(accountnumber, "Account Number");
		verify.isElementDisplayed(invoicenumber, "Invoice Number");
		verify.isElementDisplayed(invoiceexamplelink, "Invoice example link");
		verify.isElementDisplayed(loginandpaybtn, "Login and Pay Button");
		verify.isElementDisplayed(paywithoutloginbtn, "Pay Without Login Button");
		return new PayInvoicePage();

	}

	public PayInvoicePage MyInvoices_ValidateHeaders_Open() {
		for (WebElement ele : myInvHeaderName) {
			String headerName = action.getText(ele, "status");
			if (headerName.contains("Invoice") || headerName.contains("Status") || headerName.contains("Amount")
					|| headerName.contains("Due Date") || headerName.contains("Action")) {
				action.logInfo(headerName + "is expected");
			} else {
				action.logFailure(headerName + " is not displayed");
			}
		}

		return this;

	}

	public PayInvoicePage MyInvoices_ValidateHeaders_Closed() {
		action.clickWithClickableWait(ClosedMyInvoices, "Closed InvoicesList");
		for (WebElement ele : myInvHeaderName) {
			String headerName = action.getText(ele, "status");
			if (headerName.contains("Invoice") || headerName.contains("Status") || headerName.contains("Amount")
					|| headerName.contains("Due Date") || headerName.contains("Action")) {
				action.logInfo(headerName + "is expected");
			} else {
				action.logFailure(headerName + " is not displayed");
			}
		}

		return this;

	}
 
	 //This method needs rework,not to be referred
	public PayInvoicePage verify_myInvoices_status_closed() {
		String MyInvoicestatus = null;
		for (int i = 1; i <= MyInvoiceStatus.size(); i++) {
			invoice_status = By.xpath(myInvoicesClosed1 + "[" + i + "]" + myInvoicesClosed2);
			MyInvoicestatus = action.getTextWithoutLogging(invoice_status);
			if (MyInvoicestatus.equals("Paid")) {
				action.logInfo("Status is Paid on Closed Invoices list");
			} else {
				action.logFailure("Status is not correct on Closed Invoices list");
			}
		}

		return this;
	}

	//This method needs rework,not to be referred
	public PayInvoicePage verify_myInvoices_status_open_list() {

		String MyInvoicestatus = null;
		for (int i = 1; i <= MyInvoiceStatus.size(); i++) {

			invoice_status = By.xpath(myInvoicesClosed1 + "[" + i + "]" + myInvoicesClosed2);
			MyInvoicestatus = action.getTextWithoutLogging(invoice_status);
			color_ele = By.xpath(myInvoicesClosed1 + "[" + i + "]" + myInvoicesClosed2);
			String Color_displayed = ThreadManager.getInstance().getDriver().findElement(color_ele)
					.getCssValue("color");
			String hex = Color.fromString(Color_displayed).asHex();
			if (hex.equals("#d43259")) {
				action.logFailure("Invoices are overdue, the Status is X days overdue and is in red font.");
			} else if (hex.equals("#f7b23f")) {
				action.logInfo("Invoices are due soon, the Status is Due in X days and is in yellow font.");
			}

			if (!MyInvoicestatus.equals("Paid")) {
				action.logInfo("Status in MyInvoiceslist is Open");
			} else {
				action.logFailure("Status is Paid on Open Invoices list and need to reported");
			}
		}

		return this;

	}

	//This method needs rework,not to be referred
	public PayInvoicePage verify_myinvoices_sort_amount() {
		try {
			action.clickWithClickableWait(ClosedMyInvoices_Link, "Closed Link");
		} catch (Exception e) {
			String textDisplayed = action.getTextWithoutLogging(ClosedMyInvoices_TextDisplayed);
			ThreadManager.logger.get().info("You don't have any closed invoices.");
			if (textDisplayed.compareToIgnoreCase("You don't have any closed invoices.") > 0) {
				action.clickWithClickableWait(ClosedMyInvoices_ActiveLink, "No Closed Invoices");
			}
		}
		for (int i = 0; i < amountInvoicelist.size(); i++) {
			String next = amountInvoicelist.get(i).getText();
			String prev = null;
			ThreadManager.logger.get().info("Amount is: " + next);
			if (prev != null && (prev.compareTo(next) > 0)) {
				action.logFailure("Closed Invoices are not sorted with Amount");
			}
			prev = next;
		}

		return this;
	}

	//This method needs rework,not to be referred
	public PayInvoicePage verify_myinvoices_sort_duedate() {
		for (int i = 0; i < duedateInvoicelist.size(); i++) {
			String next = duedateInvoicelist.get(i).getText();
			String prev = null;
			ThreadManager.logger.get().info("Due Date is: " + next);
			if (prev != null && (prev.compareTo(next) > 0)) {
				action.logFailure("Closed Invoices are not sorted with DueDate");
			}
			prev = next;
		}

		return this;
	}

	//This method needs rework,not to be referred
	public PayInvoicePage verify_View_Invoices_Closed_Status() {
		action.clickWithClickableWait(ClosedMyInvoices_Link, "Closed Link");
		String first_invNoClosed = action.getTextWithoutLogging(firstInvoiceNo_Closed);
		try {
			action.clickLink("VIEW");
			action.setCustomExplicitWait(10);
			String viewed_invNoClosed_str = action.getTextWithoutLogging(ViewedInvoiceNo_Closed);
			String[] arrOfStr = viewed_invNoClosed_str.split("#");
			String viewed_invNoClosed = arrOfStr[1];
			if (viewed_invNoClosed.equals(first_invNoClosed)) {
				action.logInfo("Invoice Details has the same invoice Number from MyInvoices .");
			}
			String viewed_invStatus = action.getTextWithoutLogging(ViewedInvoiceStatus_Closed);
			String Color_displayed = ThreadManager.getInstance().getDriver().findElement(By.xpath(ClosedViewStatus))
					.getCssValue("color");
			String hex = Color.fromString(Color_displayed).asHex();

			if (hex.equals("#333333")) {
				action.logInfo("Invoices are Paid, with Status in Green font.");
			}

			String item_number = action.getText(LBLInvoiceItemNo, "item number");
			String service_description = action.getText(LBLServiceDesc, "service description");
			String Qty = action.getText(LBLInvoiceItemQty, "qty");
			String amount_in_currency = action.getText(LBLItemAmount, "amount");
			if (item_number.contains("Item number") || service_description.contains("Service description")
					|| Qty.contains("Qty") || amount_in_currency.contains("Amount in")) {
				action.logInfo("4 columns-ItemNumber,ServiceDescription,Qty,AmountnCurrency to show the product details:is expected");
			} else {
			action.logFailure("4 columns-ItemNumber,ServiceDescription,Qty,AmountnCurrency are NOT displayed");
			}

			String shipping_Charges = action.getText(LBLInvoiceShipping, "shipping Charges");
			String sub_total = action.getText(LBLInvoiceSubTotal, "sub total");
			String tax = action.getText(LBLInvoiceTaxAmt, "tax");
			String total_amount = action.getText(LBLTotalInvoiceAmount, "total_amount");

			if (shipping_Charges.contains("Shipping Charges") || sub_total.contains("Subtotal") || tax.contains("Tax")
					|| total_amount.contains("Total in")) {
				action.logInfo("below the product details, the payment details include Shipping charges,Subtotal,Tax,Total in CURRENCY ");
			} else {
            action.logFailure("payment details are not displayed properly");
			}
		} catch (Exception e) {
			action.logInfo("Sorry, we’re not able to display more detail about this invoice. Please contact us for help. ");
		}
		return this;
	}
	
	
	//This method needs rework,not to be referred
	public PayInvoicePage verify_Pagination_MyInvoices() {

		int pagination_total = (myInvoicesPaginationlist.size()) - 2;
		if (pagination_total <= 0) {
                action.logInfo("Pagination is not avialable & expected 10 invoices per page followed with pagination >10 invoices");
		}

		for (int i = 1; i <= pagination_total; i++) {
			if (MyInvoiceslist.size() >= 10) {
				action.scrollToEndofPage();
			} else {
                  action.logInfo("Pagination is not avialable & expected 10 invoices per page followed with pagination >10 invoices");
				break;
			}
			if (action.isElementExistsIgnore(historyPaginationNext)) {
				action.scrollToEndofPage();
				action.clickWithClickableWait(historyPaginationNext, "Pagination Next");
			} else {
						action.logInfo("Pagination is not avialable & expected 10 invoices per page followed with pagination >10 invoices");
				break;
			}
			action.logInfo("Pagination Next for MyInvoice is working as expected");
		}

		for (int j = pagination_total; j >= 1; j--) {

			if (MyInvoiceslist.size() >= 10) {
				action.scrollToEndofPage();
			}

			if (action.isElementExistsIgnore(myInvoicesPaginationPrev)) {
				action.scrollToEndofPage();
				action.clickWithClickableWait(myInvoicesPaginationPrev, "Pagination Prev");
			} else {
			action.logInfo(	"Pagination is not avialable & expected 10 invoices per page followed with pagination >10 invoices");
				break;
			}
			action.logInfo("Pagination Previous for MyInvoice is working as expected ");

		}

		return this;
	}

	 
	public PayInvoiceDetailsPage MyInvoices_Pay_button() {
		action.clickWithClickableWait(LNKPayOpenInvoice, "Pay button on Open invoices");
		return new PayInvoiceDetailsPage();

	}
	
	public LoginPage Unauthenticated_PayInvoice_Enter_Details(ConcurrentHashMap<String, Object> payinvoice) {
			@SuppressWarnings("unchecked")
			Map<String, String> data = (Map<String, String>) payinvoice.get(System.getProperty("env"));
	
		action.inputText(accountnumber, "Pay Account Number", data.get("AccountNumber"));
		action.inputText(invoicenumber, "invoiceNumber", data.get("PayInvoiceNumber") );
		action.clickWithClickableWait(loginandpaybtn, "Login and pay Button");
		return new LoginPage();
	}
	
	public PayInvoiceDetailsPage Guest_PayInvoice_Enter_Details(ConcurrentHashMap<String, Object> payinvoice) {
		@SuppressWarnings("unchecked")
		Map<String, String> data = (Map<String, String>) payinvoice.get(System.getProperty("env"));

	action.inputText(accountnumber, "Pay Account Number", data.get("GuestAccountNumber"));
	action.inputText(invoicenumber, "invoiceNumber", data.get("GuestPayInvoiceNumber") );
	action.clickWithClickableWait(paywithoutloginbtn, "Pay Without Logging In Button");
	return new PayInvoiceDetailsPage();
}
	
	public LoginPage Unauthenticated_ClosedInvoice_Enter_Details(ConcurrentHashMap<String, Object> payinvoice) {
		@SuppressWarnings("unchecked")
		Map<String, String> data = (Map<String, String>) payinvoice.get(System.getProperty("env"));
		
		action.inputText(accountnumber, "Pay Account Number",  data.get("ClosedInvoiceAccNum"));
		action.inputText(invoicenumber, "invoiceNumber",  data.get("ClosedInvoiceNumber"));
		action.clickWithClickableWait(loginandpaybtn, "Login and pay Button");
		return new LoginPage();
	}

	
	
	public HomePage Goto_HomePage() {
		action.clickLink("Shop");
		return new HomePage();
	}

}
