package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.PageAction;
import utilities.ThreadManager;

public class ReportPage extends BasePage {

	PageAction actions;
	
	public ReportPage() {
		PageFactory.initElements(ThreadManager.getInstance().getDriver(), this);
		actions = new PageAction();
		String simpleName = this.getClass().getSimpleName();
		if(!(ThreadManager.logger.get().getModel().getName().contentEquals(simpleName))) {
			ExtentTest node = ThreadManager.testLog.get().createNode(simpleName);
			ThreadManager.logger.set(node);
			node.info(MarkupHelper.createLabel("Page Title :-"+ThreadManager.getInstance().getDriver().getTitle(), ExtentColor.GREY));
		}
	}
	
	@FindBy(how = How.ID, using = "invoice-select")
	private WebElement selectInvoice;
	
	@FindBy(how = How.ID, using = "shipToSelect")
	private WebElement selectBackOrders;
	
	@FindBy(how=How.XPATH,using="//img[@class='ui-datepicker-trigger']")
	private List<WebElement> calendarIcon;
	
	@FindBy(how=How.XPATH, using="//div[contains(@class,'fakeSelect noborder fullBox background-color basefont light')]")
	private WebElement monthBtn;
	
	@FindBy(how=How.XPATH,using="//input[@id='run-report']")
	private WebElement runReport;
	
	@FindBy(how=How.ID,using="ui-datepicker-div")
	private WebElement monthpanel;
	
	
	private void chooseMonth(String month) {
		actions.clickWithClickableWait(monthBtn, "month dropddown");
		String monthXpath = "//div[contains(text(),'"+month.toString()+"')]";
		actions.clickWithClickableWait(By.xpath(monthXpath), "month :"+month);
	}
	
	private void chooseDate() {
		actions.clickWithClickableWait(calendarIcon.get(0), "Start date Icon");
		chooseMonth("Aug");
		actions.clickLink("1");
		actions.waitForElementInvisibility(monthpanel);
		actions.waitForElementDisplayed(calendarIcon.get(1),  "End date Icon").click();
		chooseMonth("Aug");
		actions.clickLink("5");
	}
	
	private void run_Invoices_Report(String item) {
		actions.clickLink("Invoices");
		actions.selectByVisibleText(selectInvoice, item, "invoice type drop down box");
		chooseDate();
		actions.clickWithClickableWait(runReport, "Report button");	
	}
	
	public ReportPage run_Shipment_Report() {
		actions.clickLink("Shipments");
		actions.selectByIndex(selectBackOrders, 1, "Shipment select drop down box");
		actions.clickWithClickableWait(runReport, "Report button");	
		return this;
	}
	
	public ReportPage run_Open_Invoice_Report() {
		run_Invoices_Report("Open Invoices");
		return this;
	}
	
	public ReportPage run_Closed_Paid_Invoices_Report() {
		run_Invoices_Report("Closed / Paid Invoices");
		return this;
	}
	
	public ReportPage run_BackOrder_Report() {
		actions.clickLink("Backorders");
		actions.selectByIndex(selectBackOrders, 1, "Backorder select dropdown");
		actions.clickWithClickableWait(runReport, "Report button");
		return this;
	}
	
	public ReportPage run_FutureOrder_Report() {
		actions.clickLink("Future Orders");
		actions.selectByIndex(selectBackOrders, 1, "Future Orders select dropdown");
		actions.clickWithClickableWait(runReport, "Report button");
		return this;
	}
	
}
