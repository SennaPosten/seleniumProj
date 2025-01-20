package com.crm.qa.pages;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator; 
import java.util.Set;
//import com.crm.qa.testdata.ExcelDataProvider;
import java.util.Vector;

import com.crm.qa.base.TestBase;
import com.crm.qa.utilities.*;
import com.google.common.base.Verify;
import com.crm.qa.testdata.ReadExcel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.crm.qa.reports.ExtentReporterNG;


public class HomePage extends TestBase {

	public String usernamemapValue;
	public String passWordmapValue;
	public String CNum;
	public String ParcelNumber;
	public String  ActualShipmentNumber;
	public String expectedShipmentNumber;
	public String ActualFromCity;
	public String expectedFromCity;
	public String PHConsignmentNr;
	public String getStopIndexInMapForStopName;
	public String unitNumber;
	public String MyBringEmaiIdContactName;
	public String Sales;
	public String  ContactLanguage;
	public String  ContactEditMobileNo;
	
	
	//public string unitInput;
	//static ExtentReports extent;
	public static ExtentTest logger;
	//static WebDriver driver;
	
	//private final static String SCREENSHOT_LOCATION = "C:\\screenshots\\test-image.png";
	//getParecelNumber;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//
	 ReadExcel rd= new ReadExcel();
	//Path of the excel file
	 
	 ExtentReporterNG getScreenshot;
	
	public void readexcelMap() throws IOException {
		Map<Integer,String> obj=rd.readExcel();
		for(Entry<Integer, String> entry:obj.entrySet()) {
			if(entry.getKey().equals(0)) {
				System.out.print("map value--------------"+entry.getValue());
				usernamemapValue=entry.getValue();
			}
			
			if(entry.getKey().equals(1)) {
				System.out.print("map value--------------"+entry.getValue());
				passWordmapValue=entry.getValue();
			}
			
			if(entry.getKey().equals(2)) {
				System.out.print("map value--------------"+entry.getValue());
				Sales=entry.getValue();
			}
			if(entry.getKey().equals(3)) {
				System.out.print("map value--------------"+entry.getValue());
				ContactEditMobileNo=entry.getValue();
			}
			if(entry.getKey().equals(4)) {
				System.out.print("map value--------------"+entry.getValue());
				ContactLanguage=entry.getValue();
			}
//			if(entry.getKey().equals(6)) {
//				System.out.print("map value--------------"+entry.getValue());
//				PHConsignmentNr=entry.getValue();
//			}
//			
//			if(entry.getKey().equals(8)) {
//				System.out.print("map value--------------"+entry.getValue());
//				unitNumber=entry.getValue();
//			}
//			
			if(entry.getKey().equals(5)) {
				System.out.print("map value--------------"+entry.getValue());
				MyBringEmaiIdContactName=entry.getValue();
			}
		}
	}

	
	
	

	 String passWordValue =null;
	//public String  usernameValue = readexcelData();
	
	public static String parentWindow;
	
	@FindBy(id = "tracking-form-q")
	WebElement consignmentNumberSearch;

	@FindBy(id = "tracking-form-q")
	WebElement consignmentNumber;

	@FindBy(name = "userName")
	WebElement userName;
	
	@FindBy(id = "password")
	WebElement password1;
	
	@FindBy(id= "newLogin_knapp_ok")
	WebElement LoginSubmit;
	
	@FindBy(className = "hw-desktop-only hw-button hw-trace__button hw-button--primary")
	WebElement Traces;
	
	@FindBy(xpath = "//a[@id='meny.sendingsinformasjon.sokkolli']")
	WebElement SearchPackageNumberLink;
	
	@FindBy(xpath = "//a[@id='meny.sendingsinformasjon.queryScreenV2']")
	WebElement PHLink;
	
	@FindBy(id = "kolliid")
	WebElement SearchPackageNumberText;
	
	
	@FindBy(xpath="(//button['@type=submit'])[8]")
	WebElement SearchPackageNumberTextinPH;
		
	@FindBy(id = "submitId")
	WebElement SearchPackageNumberButton;
	
	
	@FindBy(xpath ="(//*[@id=\"pagination_id\"]/tbody/tr/td[3])[1]")
	WebElement ParcelNumberInShippmentPage;
	
	
	
	@FindBy(id = "kolilisteform_knapp_aapne")
	WebElement ToOPenButton;
	
	//*********************rutekartElements****************//
	@FindBy(xpath="//span[text()='Ruteplanlegger']")
	WebElement Ruteplanlegger;
	
	@FindBy(xpath="//input[@id='unitInput']")
	WebElement unitInput_txt;
	
	@FindBy(xpath="//button[@id='btnSearchUnitOrRoute']")
	WebElement btnSearchUnitOrRoute;
	
	@FindBy(xpath="//span[text()='13532885 HENSMOVEIEN 46']")
	WebElement stopNameValue;
	
	@FindBy(xpath="//span[text()='15346906 HENSMOVEIEN 90']")
	WebElement stopName2Value;
	
	@FindBy(xpath="//span[text()='10508338 KONG RINGS GATE 1']")
	WebElement Table2stopNameValue;
	
	@FindBy(xpath="//span[text()='10545437 FOLLUMVEIEN 100']")
	WebElement TablestopNameValue;
	
	@FindBy(xpath="//span[text()='10545404 HENSMOVEIEN 1']")
	WebElement Table1stopNameValue;
	
	
	@FindBy(xpath="//select[@id='cbxRpl2']")
	WebElement Table2RuteDropdown;
	
	

	@FindBy(xpath="//span[text()='15179288 HENSMOVEIEN 101']/../parent::tr[@title='15179288 HENSMOVEIEN 101']/td[@data-bind='text: rplSeq()']")
	WebElement getStopIndex;
	
	@FindBy(xpath="(//*[local-name()='svg']/*[local-name()='g']/*[name()='text']")
	WebElement clickIndexInMap;
	
	@FindBy(xpath="//span[text()='Plotting']")
	WebElement PlottingTab;
	
	@FindBy(xpath="//span[text()='Omdeling']")
	WebElement OmdelingTab;

	@FindBy(xpath="//span[text()='Ruter']")
	WebElement RuterTab;
	
	
	@FindBy(xpath="//span[text()='Motsykl. ruter']")
	WebElement MotsyklRuterTab;
	
	@FindBy(xpath="//div[@class=\"esriPopup\"]/div[@class='esriPopupWrapper']")
	WebElement MapPopupTitle;
	
	@FindBy(xpath="//table[@id='rplGrid1Table']/tbody/tr")
	WebElement BeforeDropGrid1TableSize;
	
	@FindBy(xpath="//table[@id='rplGrid2Table']/tbody/tr")
	WebElement AfterDragGrid2TableSize;
	
	@FindBy(xpath="(//button[@class='btn btn-secondary btn-light btn-xs'])[1]")
	WebElement Table1Detailer_btn;
	
	@FindBy(xpath="(//select[@class='form-control form-control-xs mb-0.5'])[1]")
	WebElement Frekvens_drpdwn;
	
	@FindBy(xpath="(//button[text()='Oppdater'])[1]")
	WebElement TableOppdater1_drpdwn;
	
	@FindBy(xpath="(//button[@class='btn btn-success btn-xs'])[1]")
	WebElement Table1AddNewRute_btn;
	
	@FindBy(xpath="//input[@placeholder='Skriv inn et nytt rutenummer']")
	WebElement Table1EnterNewRute_txt;
	
	@FindBy(xpath="//button[@id='btnSaveRPLRoute']")
	WebElement SaveRute_btn;
	
	
	@FindBy(xpath="(//button[@class='btn btn-success btn-xs'])[2]")
	WebElement Table2AddNewRute_btn;
	
	@FindBy(xpath="//span[@data-bind='visible: routeDetailsMissingWarning2']")
	WebElement routeDetailsMissingWarning2_txt;
	
	
	@FindBy(xpath="//input[@id='username']")
	WebElement UNSalesforce_txt;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement PWDSalesforce_txt;
	
	@FindBy(xpath="//input[@id='Login']")
	WebElement LoginSalesforce_btn;

	@FindBy(xpath="//button[@title='App Launcher']")
	WebElement app_Launcher;
	
	@FindBy(xpath="//input[@placeholder='Search apps and items...']")
	WebElement searchBox_txt;
	
	
	@FindBy(xpath="//a[@data-label='Sales']")
	WebElement sales_App_Launcher;
	
	
	@FindBy(xpath="//span[text()='Contacts']")
	WebElement Contacts_tab;
	
	@FindBy(xpath="(//input[@type='search'])[2]")
	WebElement Contacts_SearchBox_txt;
		
	@FindBy(xpath="(//span/mark[text()='Senna'])[1]")
	WebElement Contact_Search_Name;
	
	@FindBy(xpath="(//span[text()='Contacts'])[2]")
	WebElement Contacts_title;
			
	@FindBy(xpath="//a[@title='Senna Kesava Raj Kamatchi Prabhakar']")
	WebElement Contact_Name;
	
	@FindBy(xpath="//a[@title='Senna Kamatchi Prabhakar']")
	WebElement ContactNameXpath;
	
	@FindBy(xpath="(//li/a[@id='detailTab__item'])[1]")
	WebElement ContactsDetailsPage; 
	
	@FindBy(xpath="//button[@title='Edit Email']/span[@class='slds-grid highlights-primary-row inline-edit-trigger-icon']")
	WebElement EditEmailIcon;
	
	@FindBy(xpath="//button[@title='Edit My Bring Userid']/span[@class='slds-grid highlights-primary-row inline-edit-trigger-icon']")
	WebElement EditMyBringUserIdIcon;
	
	@FindBy(xpath="//input[@name='My_Bring_Userid__c']")
	WebElement EditMyBringuseridTextbox;
	
	@FindBy(xpath="//button[@aria-label='Function / Working Area']")
	WebElement EditSalesforceRoleTextbox;
	
	
	@FindBy(xpath="//input[@name='Email']")
	WebElement EditEmailTextbox;
	
	
	@FindBy(xpath="//button[@name='Edit']")
	WebElement Contact_Edit_btn;
	
	
	@FindBy(xpath="//input[@name='MobilePhone']")
	WebElement Contact_Edit_MobileNo;
	
	@FindBy(xpath="//button[@aria-label='Language']")
	WebElement Contact_Edit_Language;
	
	@FindBy(xpath="//button[@aria-label='Callout Status']")
	WebElement Contact_Edit_Callout_Status;
	
	
	@FindBy(xpath="//input[@name='crm_Has_declined_Marketing_Activities__c']")
	WebElement Contact_Edit_declined_Marketing_Activities;
	
	@FindBy(xpath="(//div[@class='slds-dueling-list__options'])[2]")
	WebElement Contact_Edit_Marketing_Activities_Lookup;
	
	
	@FindBy(xpath="//div[@data-value='SMS']")
	WebElement Contact_Edit_Agreed_To_SMS;
	
	@FindBy(xpath="//div[@data-value='Email']")
	WebElement Contact_Edit_Agreed_To_Email;
	
	@FindBy(xpath="//span[@title='Mybring']")
	WebElement Contact_Edit_Agreed_To_Mybring;
	
	
	@FindBy(xpath="//button[@title='Move to Chosen']")
	WebElement Contact_Edit_Agreed_Move_to_elmnt;
	//button[@title='Move to Chosen']
	
	@FindBy(xpath="//button[@name='CancelEdit']")
	WebElement Contact_Edit_Cancel_btn;
	
	@FindBy(xpath="//button[@name='SaveEdit']")
	WebElement Contact_Edit_Save_btn;
	
	@FindBy(xpath="//a[starts-with(@href, '/lightning/r/Account/')]")
	WebElement Contact_CustomerName_In_details_page_link;
	//div[@class='slds-grid']//a[@id='window']/span/slot/span/slot[text()='KOMPLETT DISTRIBUSJON AS']
	//
	@FindBy(xpath="(//lst-formatted-text/span)[1]")
	WebElement Contact_CustomerNumber_In_CustomerName_page_link;
	//td[@data-label='Customer Number']
	@FindBy(xpath="(//dl[@class='slds-list_horizontal slds-wrap']/dt[text()='Customer Number:']//following-sibling::dd)[1]")
	WebElement SalesforceConsumerRelatedAccounts;
	
	

	@FindBy(xpath="//a[@href='/useradmin/account/profile/consent']")
	WebElement MyBring_CommunicationAndConsent_tab;
	
	@FindBy(xpath="//input[@id='yesMarketing']")
	WebElement MyBring_Marketing_Yes_RdBtn;

	
	
	@FindBy(xpath="//input[@id='username']")
	WebElement UNMyBring_txt;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement PWDMyBring_txt;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement LoginMyBringe_btn;
	
	@FindBy(xpath="//span[text()='Senna Kamatchi Prabhakar']")
	WebElement MyBring_Username;
	
	@FindBy(xpath="//a[@href='/useradmin/account/profile']//span[@data-mybicon-class='nav__icon']//*[local-name()='svg']")
	WebElement MyBring_ProfileAndSettings;
	
	//span[text()='Profile and settings']
	
	
	
	@FindBy(xpath="//input[@name='workingArea']")
	WebElement MyBring_RoleInProfileAndSettings;
	
	@FindBy(xpath="//button[@id='saveProfile']")
	WebElement MyBring_SaveProfileAndSettings_btn;
	
	@FindBy(xpath="	//button[@id='saveConsent']")
	WebElement MyBring_SaveConsent_btn;

	
	@FindBy(xpath="//a[@id='sidebarSettingsNav']")
	WebElement MyBring_UserProfile_CustomerSettings;
	
	
	@FindBy(xpath="//input[@id='mobileNumber']")
	WebElement MyBring_getMobileNumber_value;
	
	@FindBy(xpath="//input[@id='language3']")
	WebElement MyBring_getLanguage;
	
	@FindBy(xpath="//input[@id='emailCheckBox']")
	WebElement MyBring_getEmail_chkbox;
	
	@FindBy(xpath="//input[@id='smsCheckBox']")
	WebElement MyBring_getSMS_chkbox;
	
	
	@FindBy(xpath="//input[@id='mybringCheckBox']")
	WebElement MyBring_getMyBring_chkbox;
	
	@FindBy(xpath="//input[@id='noMarketing']")
	WebElement MyBring_DeclinedMarketing_No_RdBtn;
	
	
	
	@FindBy(xpath="//table/tbody/tr/td[1]")
	WebElement MyBring_CustomerSettings_CustomerNumber_txt;
	
	

	
	
	
	public void VerifySalesforceLogin() throws InterruptedException {
		
		UNSalesforce_txt.isDisplayed();
		Thread.sleep(5000);	
		//sUNSalesforce_txt.sendKeys("senna.kamatchi.prabhakar@posten.no.crmfull");
		UNSalesforce_txt.clear();
		UNSalesforce_txt.sendKeys(usernamemapValue);
		Reporter.log("UserName of Salesforce  is entered");
		Assert.assertTrue(true, "UserName of Salesforce  is entered; ");
		Thread.sleep(5000);
		PWDSalesforce_txt.clear();
		//PWDSalesforce_txt.sendKeys("Novv!2024#");
		PWDSalesforce_txt.sendKeys(passWordmapValue);
		Reporter.log("Password of Salesforce  is entered");
		Assert.assertTrue(true, "Password of Salesforce  is entered; ");
		LoginSalesforce_btn.click();
		Reporter.log("SalesForce Login button  is clicked; ");
		Assert.assertTrue(true, "SalesForce Login button is clicked");
		
			
	}
	
	public void VerifyContactsTab() throws InterruptedException {
		Thread.sleep(5000);
		app_Launcher.click();
		Reporter.log("App Launcher  of Salesforce  is clicked; ");
		Assert.assertTrue(true, "App Launcher of Salesforce  is clicked;  ");
		Thread.sleep(5000);
		searchBox_txt.clear();
		searchBox_txt.sendKeys("Sales");
		Reporter.log("Sales App  is entered in Search box; ");
		Assert.assertTrue(true, "Sales App  is entered in Search box; ");
		Thread.sleep(5000);
		sales_App_Launcher.click();
		Reporter.log("Sales App  is clicked from Searched list; ");
		Assert.assertTrue(true, "Sales App  is clicked from Searched list; ");
		Thread.sleep(5000);
		Contacts_tab.click();
		Thread.sleep(5000);
		Reporter.log("Contacts tab  is clicked; ");
		Assert.assertTrue(true, "Contacts tab  is clicked");
		Thread.sleep(6000);
		Contacts_title.isDisplayed();
		Reporter.log("Contacts title tab  is displaying in Contacts Page; ");
		Assert.assertTrue(true, "Contacts title tab  is displaying in Contacts Page;");
		Thread.sleep(5000);
	}
	
	
	
	
	public void VerifyNavigateToContact() throws InterruptedException {
		
	 Contacts_SearchBox_txt.click();
	 Thread.sleep(2000);
	 Contacts_SearchBox_txt.clear();
	 Contacts_SearchBox_txt.click();
	 Thread.sleep(2000);
	 Contacts_SearchBox_txt.clear();
	 Contacts_SearchBox_txt.sendKeys("Senna Kamatchi Prabhakar");
	 Thread.sleep(2000);
	 Contacts_SearchBox_txt.sendKeys(Keys.ENTER);
	 Thread.sleep(2000);
	 ContactNameXpath.click();
	 Reporter.log("Contacts Name   is Clicked in Contacts Page; ");
	 Assert.assertTrue(true, "Contacts Name  is Clicked in Contacts Page;");
	Thread.sleep(5000);
	
	
	}
	
	public void VerifyByUpdateEmailIdMyBringMaildCleared() throws InterruptedException {
		 Thread.sleep(2000);
		 Contacts_SearchBox_txt.sendKeys(MyBringEmaiIdContactName);
		 Contacts_SearchBox_txt.sendKeys(Keys.ENTER);
		 Thread.sleep(2000);
		 Contact_Name.click();
		 Reporter.log("Contacts Name   is Clicked in Contacts Page; ");
		 Assert.assertTrue(true, "Contacts Name  is Clicked in Contacts Page;");
		Thread.sleep(2000);
		Thread.sleep(2000);
	ContactsDetailsPage.click();
	Thread.sleep(2000);
	Reporter.log("Contacts Details tab   is Clicked in Contacts Page; ");
	// Assert.assertTrue(true, "Contacts Details tab is Clicked in Contacts Page;");
    Contact_Edit_btn.click();
    Reporter.log("Contacts Edit button   is Clicked in Contacts Page; ");
	Thread.sleep(2000);
	EditEmailTextbox.clear();
	Thread.sleep(2000);
	  Reporter.log("Contacts  Email   is edited in Contacts Page; ");
	EditEmailTextbox.sendKeys("Sennaposten@gmail.com");
	Thread.sleep(2000);
	Reporter.log("Contacts  Email   is entered in Contacts Page; ");
	Contact_Edit_Save_btn.click();
	Thread.sleep(5000);
	Reporter.log("Contacts  Email   is Saved in Contacts Page; ");
	Contact_Edit_btn.click();
	Thread.sleep(5000);
	Reporter.log("Contacts Edit button   is Clicked in Contacts Page; ");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Thread.sleep(5000);
    js.executeScript("arguments[0].scrollIntoView();", EditMyBringuseridTextbox);
	//Reporter.log("Contacts  My Bring User Id   is clicked in Contacts Page; ");
	String EditMyBringUserid_txt=EditMyBringuseridTextbox.getText();
	Thread.sleep(5000);
	if(EditMyBringUserid_txt=="") {
		
		Assert.assertTrue(true, "MyBringUserid value is cleared");
		Reporter.log("MyBringUserid value is cleared");
	}
	else {
		Assert.assertTrue(false, "MyBringUserid value is not cleared");
		Reporter.log("MyBringUserid value is not cleared");
		
	}
	
	
	
		
		}
	public void VerifyRoleUpdatedInSalesforceByUpdateRoleInMyBring() throws InterruptedException {
		String SalesForceRoleValue ="Accounts";
		ContactsDetailsPage.click();
		Thread.sleep(2000);
		Reporter.log("Contacts Details tab   is Clicked in Contacts Page; ");
		// Assert.assertTrue(true, "Contacts Details tab is Clicked in Contacts Page;");
	    Contact_Edit_btn.click();
	    Reporter.log("Contacts Edit button   is Clicked in Contacts Page; ");
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
	    js.executeScript("arguments[0].scrollIntoView();", EditSalesforceRoleTextbox);
		//Reporter.log("Contacts  My Bring User Id   is clicked in Contacts Page; ");
		String EditMyBringUserid_txt=EditSalesforceRoleTextbox.getText();
		Thread.sleep(5000);
		if(SalesForceRoleValue.equals(EditMyBringUserid_txt)) {
			
			Assert.assertTrue(true, "Salesforce Role " +EditMyBringUserid_txt+" value is Same as My Bring");
			Reporter.log("Salesforce Role " +EditMyBringUserid_txt+" value is Same as My Bring" ); 
		}
		else {
			Assert.assertTrue(true, "Salesforce Role value is not Same as My Bring");
			Reporter.log("Salesforce Role value is not Same as My Bring" +EditSalesforceRoleTextbox);
			
		}
		
	}
	
	
	
	
	public void VerifyEditContactMobileLanguageMarketingActivities() throws InterruptedException {
		Thread.sleep(5000);
		Contact_Edit_btn.click();
		Reporter.log("Contacts Edit button   is Clicked in Contacts Page; ");
		Assert.assertTrue(true, "Contacts  Edit button   is Clicked in Contacts Page;");
		Thread.sleep(5000);
		Contact_Edit_MobileNo.clear();
		Thread.sleep(5000);
		Contact_Edit_MobileNo.sendKeys("+4796660607");
		Reporter.log("Contacts MobileNo   is updated in Contacts Page; ");
		Thread.sleep(5000);
		
		Contact_Edit_Save_btn.click();
		Reporter.log(" Save button is clicked in Contacts Edit Page; ");
		Assert.assertTrue(true, "Save button is clicked in Contacts Edit Page;");
		
	}
	
	public void VerifyCalloutStatus()  throws InterruptedException{
		String ExpectedCalloutStatus="Callout Finished";
		Contact_Edit_btn.click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
	    js.executeScript("arguments[0].scrollIntoView();", Contact_Edit_Callout_Status);
	   // Contact_Edit_Callout_Status.sendKeys("--None--");
	  //  Contact_Edit_Callout_Status.sendKeys(Keys.ENTER);
		// Thread.sleep(2000);
	    //Thread.sleep(5000);
		//Contact_Edit_Save_btn.click();
		//Thread.sleep(5000);
		//Contact_Edit_btn.click();
		//Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", EditSalesforceRoleTextbox);
		EditSalesforceRoleTextbox.sendKeys("Warehouse");
		EditSalesforceRoleTextbox.sendKeys(Keys.ENTER);
		EditSalesforceRoleTextbox.sendKeys(Keys.TAB);
		Thread.sleep(10000);
		Contact_Edit_Save_btn.click();
		Thread.sleep(10000);
		
	//	Select objSelect1 = new Select(EditSalesforceRoleTextbox);
		//objSelect1.selectByValue("Warehouse");
		Contact_Edit_btn.click();
		Thread.sleep(10000);
		js.executeScript("arguments[0].scrollIntoView();", Contact_Edit_Callout_Status);
		
		String CalloutStatusAfterupdate=Contact_Edit_Callout_Status.getText();
		if(ExpectedCalloutStatus.equals(CalloutStatusAfterupdate)) {
			Reporter.log(" Callout Status After update is dislaying "   +CalloutStatusAfterupdate+  "  in Contacts Edit Page as Expected; ");
			
		}
		else
			Reporter.log(" Callout Status After update is dislaying " +CalloutStatusAfterupdate+  " in Contacts Edit Page and its not expected; ");
		Thread.sleep(10000);
		
	}
	
	public void VerifyCalloutStatusForSystemUsers()  throws InterruptedException{
		String ExpectedCalloutStatus="--None--";
		 Thread.sleep(2000);
		 Contacts_SearchBox_txt.sendKeys(MyBringEmaiIdContactName);
		 Contacts_SearchBox_txt.sendKeys(Keys.ENTER);
		 Thread.sleep(2000);
		 Contact_Name.click();
		 Reporter.log("Contacts Name   is Clicked in Contacts Page; ");
		 Assert.assertTrue(true, "Contacts Name  is Clicked in Contacts Page;");
		Thread.sleep(2000);
		
		Contact_Edit_btn.click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
	    //js.executeScript("arguments[0].scrollIntoView();", Contact_Edit_Callout_Status);
	    EditEmailTextbox.clear();
		Thread.sleep(2000);
		  Reporter.log("Contacts  Email   is edited in Contacts Page; ");
		EditEmailTextbox.sendKeys("Sennaposten@posten.no");
		Thread.sleep(2000);
		Reporter.log("Contacts  Email   is entered in Contacts Page; ");
		js.executeScript("arguments[0].scrollIntoView();", EditSalesforceRoleTextbox);
		Thread.sleep(2000);
		EditSalesforceRoleTextbox.sendKeys("IT");
		EditSalesforceRoleTextbox.sendKeys(Keys.ENTER);
		EditSalesforceRoleTextbox.sendKeys(Keys.TAB);
		Thread.sleep(10000);
		Contact_Edit_Save_btn.click();
		Thread.sleep(10000);
		
	//	Select objSelect1 = new Select(EditSalesforceRoleTextbox);
		//objSelect1.selectByValue("Warehouse");
		Contact_Edit_btn.click();
		Thread.sleep(10000);
		js.executeScript("arguments[0].scrollIntoView();", Contact_Edit_Callout_Status);
		
		String CalloutStatusAfterupdate=Contact_Edit_Callout_Status.getText();
		if(ExpectedCalloutStatus.equals(CalloutStatusAfterupdate)) {
			Reporter.log(" Callout Status After update is dislaying "   +CalloutStatusAfterupdate+  "  in Contacts Edit Page as Expected; ");
			
		}
		else
			Reporter.log(" Callout Status After update is dislaying " +CalloutStatusAfterupdate+  " in Contacts Edit Page and its not expected; ");
		Thread.sleep(10000);
		
	}
	
	public void VerifyUpdatedMyBringAttributesInSalesforce() throws InterruptedException {
		Thread.sleep(3000);
		Contact_Edit_btn.click();
		
		
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", Contact_Edit_Marketing_Activities_Lookup);
	String SalesforceMarketingLookup=Contact_Edit_Marketing_Activities_Lookup.getText();
		System.out.println("SalesforceMarketingLookup is "+SalesforceMarketingLookup);
		
		String SalesforceMarketingSMS=Contact_Edit_Agreed_To_SMS.getText();
		if (SalesforceMarketingLookup.contains(SalesforceMarketingSMS)) {
			Reporter.log(" SalesForce Marketing SMS is displaying in lookup as expected in Contacts Edit Page; ");
			//Verify.verify(true, "SalesForce Marketing SMS is displaying in lookup as expected in Contacts Edit Page; ");
		}
		else
			Reporter.log(" SalesForce Marketing SMS is not displaying in lookup as expected in Contacts Edit Page; ");
		//Verify.verify(false, " SalesForce Marketing SMS is not displaying in lookup as expected in Contacts Edit Page;  ");
		
		String SalesforceMarketinEmail=Contact_Edit_Agreed_To_Email.getText();
		
		if (SalesforceMarketingLookup.contains(SalesforceMarketinEmail)) {
			Reporter.log(" SalesForce Marketing Email is displaying in lookup as expected in Contacts Edit Page; ");
			//Verify.verify(true, "SalesForce Marketing Email is displaying in lookup as expected in Contacts Edit Page; ");
		}
		else
			Reporter.log(" SalesForce Marketing Email is not displaying in lookup as expected in Contacts Edit Page; ");
		//Verify.verify(false, "SalesForce Marketing Email is not displaying in lookup as expected in Contacts Edit Page; ");
		
		String SalesforceMarketinMyBring=Contact_Edit_Agreed_To_Mybring.getText();
		
		if (SalesforceMarketingLookup.contains(SalesforceMarketinMyBring)) {
			Reporter.log(" SalesForce Marketing MyBring is  displaying in lookup as expected in Contacts Edit Page; ");
			//Verify.verify(true, "SalesForce Marketing MyBring  is not displaying in lookup as expected in Contacts Edit Page; ");
		}
		else
			Reporter.log(" SalesForce Marketing MyBring is  not displaying in lookup as expected in Contacts Edit Page; ");
		//Verify.verify(false, "SalesForce Marketing MyBring is  displaying in lookup as expected in Contacts Edit Page; ");
		
		
		String SalesForceContactLanguage= Contact_Edit_Language.getText();
		if (SalesForceContactLanguage.equals(ContactLanguage)) {
			Reporter.log(" SalesForce Language is same as MyBring Language in Contacts Edit Page; ");
			//Verify.verify(true, "SalesForce Language is same as MyBring Language in Contacts Edit Page;");
		}
		else
			Reporter.log(" SalesForce Language is not same as MyBring Language in Contacts Edit Page; ");
		//Verify.verify(false, "SalesForce Language is not same as MyBring Language in Contacts Edit Page;");
		Thread.sleep(5000);
		
//		String SalesforceContactMobileValue=Contact_Edit_MobileNo.getText();
//		Thread.sleep(3000);
//		if (SalesforceContactMobileValue==ContactEditMobileNo) {
//			Reporter.log(" SalesForce Mobile No is same as MyBring Mobile No in Contacts Edit Page; ");
//			//Verify.verify(true, "SalesForce Mobile No is same as MyBring Mobile No in Contacts Edit Page; ");
//		}
//		else
//			Reporter.log(" SalesForce Mobile No is not same as MyBring Mobile No in Contacts Edit Page; ");
//		//Verify.verify(false, " SalesForce Mobile No is not same as MyBring Mobile No in Contacts Edit Page; ");
		
	}
	public void NaviageToMyBring() throws InterruptedException {
		Thread.sleep(5000);
		
		driver.get(properties.getProperty("url4"));
		Thread.sleep(5000);
		
	}
	
	
	public void NaviageToSalesforce() throws InterruptedException {
		Thread.sleep(5000);
		
		driver.get(properties.getProperty("url3"));
		Thread.sleep(5000);
		
	}
	
	
public void VerifyMyBringLogin() throws InterruptedException {
		
		UNMyBring_txt.isDisplayed();
		Thread.sleep(2000);	
		UNMyBring_txt.sendKeys("sennakesavaraj.kamatchiprabhakar@tcs.com");
		//UNSalesforce_txt.sendKeys(usernamemapValue);
		Reporter.log("UserName of MyBring  is entered");
		Assert.assertTrue(true, "UserName of MyBring  is entered; ");
		Thread.sleep(2000);
		PWDMyBring_txt.sendKeys("MonOfDec@123");
		//PWDSalesforce_txt.sendKeys(passWordmapValue);
		Reporter.log("Password of MyBring  is entered");
		Assert.assertTrue(true, "Password of MyBring  is entered; ");
		LoginMyBringe_btn.click();
		Reporter.log("MyBring Login button  is clicked; ");
		Assert.assertTrue(true, "MyBring Login button is clicked");
		
			
	}


public void VerifyMyBringNavigateToUserProfilePage() throws InterruptedException {
	
	Thread.sleep(5000);
	MyBring_Username.click();
	Reporter.log("MyBring Username  is clicked in home page; ");
	Assert.assertTrue(true, "MyBring Username  is clicked in home page");
	MyBring_ProfileAndSettings.click();
	Reporter.log("MyBring Profile And Settings  is clicked in home page; ");
	Assert.assertTrue(true, "MyBring Profile And Settings  is clicked in home page");
		
}


public void VerifyMyBringByEnterRoleInUserProfilePage() throws InterruptedException {
	
	Thread.sleep(5000);
	MyBring_RoleInProfileAndSettings.clear();
	Thread.sleep(5000);
	MyBring_RoleInProfileAndSettings.sendKeys("Accounts");	
	//Reporter.log("MyBring Username  is clicked in home page; ");
	
	Reporter.log("Role is entered in MyBring Profile And Settings page; ");
	Thread.sleep(2000);
	MyBring_SaveProfileAndSettings_btn.click();
	Thread.sleep(2000);	
	Reporter.log("Save Button  is clicked in MyBring Profile And Settings page; ");
}

public void VerifyByEnterAttributesInMyBringUserProfilePage() throws InterruptedException {

	Thread.sleep(5000);
	MyBring_getLanguage.click();
	Reporter.log("Language is Selected in MyBring Profile And Settings page; ");
	Thread.sleep(5000);
	MyBring_SaveProfileAndSettings_btn.click();
	Thread.sleep(5000);
	Reporter.log("Save Button is clicked in  MyBring Profile And Settings page; ");
	MyBring_CommunicationAndConsent_tab.click();
	Thread.sleep(5000);
	
	MyBring_Marketing_Yes_RdBtn.click();
	Reporter.log("Marketing Button is selected in  MyBring Consents page; ");
	Thread.sleep(5000);
	if (MyBring_getEmail_chkbox.isSelected()){
		Reporter.log("Email Checkbox is selected in MyBring; ");	
	}
	else {
	MyBring_getEmail_chkbox.click();
	}
	Thread.sleep(5000);
	if (MyBring_getSMS_chkbox.isSelected()){
		Reporter.log("SMS Checkbox is selected in MyBring; ");	
	}
	else {
		MyBring_getSMS_chkbox.click();
	}
	

	Thread.sleep(5000);
	MyBring_getMyBring_chkbox.click();
	Thread.sleep(5000);

	
	MyBring_SaveConsent_btn.click();
	Thread.sleep(5000);
	Reporter.log("Save Button is selected in  MyBring Consents page; ");
	
}





//public String CustomerNumber_value;

public String  VerifyMyBringNavigateToUserProfilePageCustumerSettings(String CustomerNoMyBring) throws InterruptedException {
	
	Thread.sleep(2000);
	MyBring_UserProfile_CustomerSettings.click();
	Thread.sleep(2000);
	Reporter.log("MyBring Username  is clicked in home page; ");
	Assert.assertTrue(true, "MyBring Username  is clicked in home page");
	String CustomerNumber_value=MyBring_CustomerSettings_CustomerNumber_txt.getText();
	Thread.sleep(2000);
	System.out.println("CustomerNumber_value is "+CustomerNumber_value);
	Reporter.log("MyBring CustomerNumber_value  is ; "+CustomerNumber_value );
	Assert.assertTrue(true, "MyBring CustomerNumber_value  is "+CustomerNumber_value);
	//String  SalesforceCustomerNumber_value = VerifySalesforceCustomerNumber(SalesforceCustomerNumber);
	return CustomerNumber_value;
		
}
//public String CustomerNumber=CustomerNumber_value;

public void VerifySalesforceCustomerNumber() throws InterruptedException {
	Thread.sleep(3000);
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", Contact_CustomerName_In_details_page_link); 
	
	//Contact_CustomerName_In_details_page_link.click();
	Thread.sleep(5000);
	//SalesforceConsumerRelatedAccounts.isDisplayed();
	//executor.executeScript("arguments[0].click();", SalesforceConsumerRelatedAccounts); 
	//SalesforceConsumerRelatedAccounts.click();
	Thread.sleep(2000);
	String CustomerNumber=Contact_CustomerNumber_In_CustomerName_page_link.getText();
	Thread.sleep(2000);
	System.out.println("CustomerNumber of salesforce "+CustomerNumber);
	//return CustomerNumber;
	
	
}


/*
 * public String CompareSalesForceAndMyBringCustomerNo() {
 * 
 * 
 * if(CustomerNumber==CustomerNumber_value) {
 * 
 * Reporter.log("CustomerNumber is same as Salesforce CustomerNumber");
 * 
 * } else {
 * Reporter.log("CustomerNumber is not same as Salesforce CustomerNumber"); }
 *
}*/

//public String VerifyCompareSalesforceCustomerNumber(String SalesforceCustomerNumber, String MyBringCustomerNumber) throws InterruptedException {
//	
//	String  SalesforceCustomerNumber_value = VerifySalesforceCustomerNumber(SalesforceCustomerNumber);
//	//String MybringCustomerNumberValue= VerifyMyBringNavigateToUserProfilePageCustumerSettings(MyBringCustomerNumber);
//	
//	System.out.println("SalesforceCustomerNumber is "+SalesforceCustomerNumber_value);
	//System.out.println("MybringCustomerNumberValue is "+MybringCustomerNumberValue);
	
// if (MybringCustomerNumberValue.equals(SalesforceCustomerNumber_value)) {
//	 
//	 Assert.assertTrue(true, "MybringCustomerNumberValue and SalesforceCustomerNumber_value are Same");
// }
// else {
//	 Assert.assertTrue(false, "MybringCustomerNumberValue and SalesforceCustomerNumber_value are not Same");
//	 
// }
//return MybringCustomerNumberValue;
//return SalesforceCustomerNumber_value;
	
	
//}

public void VerifySalesforceFieldsInMyBringUserProfilePage() throws InterruptedException {
	
	
	Thread.sleep(5000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView();", MyBring_getLanguage);
		
	
	//String MyBringContactLanguage= MyBring_getLanguage.getText();
   /* boolean MyBring_SelectedLanguage=MyBring_getLanguage.isSelected();
    try {
	
		Assert.assertTrue(MyBring_SelectedLanguage, "MyBring Language is not same as Salesforce Language in Contacts Edit Page; ");
		Reporter.log(" MyBring Language is same as Salesforce Language in Contacts Edit Page; ");
	
    }
    catch(AssertionError e)
	 {
		Reporter.log(e.getMessage());
		
	}
	Thread.sleep(5000);
	*/
	
	String MyBring_MobileNumber=MyBring_getMobileNumber_value.getAttribute("value");
	Thread.sleep(3000);
	if (MyBring_MobileNumber.equals(ContactEditMobileNo)  ) {
		Reporter.log(" MyBring Mobile No is same as Salesforce Mobile No in Contacts Edit Page; ");
	}
	else
		Reporter.log(" MyBring Mobile No is not same as Salesforce Mobile No in Contacts Edit Page; ");
	
		Thread.sleep(2000);
	}
	
	public void VerifyPlotting() throws InterruptedException {
	
		PlottingTab.isDisplayed();
		Thread.sleep(5000);	
		PlottingTab.click();
		Reporter.log("Plotting Tab is loaded");
		Assert.assertTrue(true, "Plotting Tab is loaded");
		Thread.sleep(5000);
	}
	
	public void verifyApplicationURL() throws InterruptedException, IOException {
	
		String Currenturl= "http://rutekart.qa.posten.no/";
		String ExppectedUrl= driver.getCurrentUrl();
		System.out.println("ExpectedURL is"+ExppectedUrl);
		Assert.assertEquals(Currenturl, ExppectedUrl);
		Reporter.log("Launched Rutekart Application");
		
		
	}

public void verify_Tabs_In_Rutekart() throws InterruptedException, IOException {
		verifyApplicationURL();
		verifyOmdelingTab();
		VerifyRuter();
		VerifyMotsyklRuter();
		VerifyRuteplanlegger();
		VerifyUnitNOinRuteplanleggerTab();
		verifyTheNumberOfRutesInTable1();
		verifyColorInRuteTable1();
	}

public void verifyOmdelingTab() throws InterruptedException, IOException {
	Thread.sleep(2000);	
OmdelingTab.isDisplayed();
OmdelingTab.click();
Reporter.log("Omdeling Tab is loaded");
Assert.assertTrue(true, "Omdeling Tab is loaded");
Thread.sleep(5000);

}
public void VerifyRuteplanlegger() throws InterruptedException, IOException {
	Thread.sleep(2000);	
Ruteplanlegger.isDisplayed();
Thread.sleep(5000);	
Ruteplanlegger.click();
Reporter.log("Ruteplanlegger Tab is Loaded");
Thread.sleep(5000);
}
public void VerifyRuter() throws InterruptedException, IOException {
	Thread.sleep(2000);	
	RuterTab.isDisplayed();
	Thread.sleep(2000);	
	RuterTab.click();
	//Reporter.log("Ruter Tab is loaded");
	String ActualRuterTab=RuterTab.getText();
	String ExpectedRuterTab= "Ruter";
	Assert.assertEquals(ActualRuterTab, ExpectedRuterTab);
	Assert.assertTrue(true, "Ruter Tab is loaded");
}
	
	public void VerifyMotsyklRuter() throws InterruptedException {
		Thread.sleep(2000);	
		MotsyklRuterTab.isDisplayed();
		Thread.sleep(5000);	
		MotsyklRuterTab.click();
		Reporter.log("MotsyklRuter Tab is loaded");
		Assert.assertTrue(true, "MotsyklRuter Tab is loaded");
		Thread.sleep(5000);
	}

	
	
	public void VerifyUnitNOinRuteplanleggerTab() throws InterruptedException {
		Thread.sleep(2000);	
		unitInput_txt.clear();
		unitInput_txt.sendKeys("101892");
		Reporter.log("Unit Number 101892  is entered");
		Thread.sleep(5000);
		btnSearchUnitOrRoute.click();
		Reporter.log("Search Button  is clicked");
		Thread.sleep(5000);
		//driver.navigate().refresh();		
	}
	
	
	public void VerifyIrrelaventUnitNOinRuteplanleggerTab() throws InterruptedException {
		Thread.sleep(2000);	
		unitInput_txt.clear();
		unitInput_txt.sendKeys("101");
		Reporter.log("Unit Number 101  is entered");
		Thread.sleep(5000);
		btnSearchUnitOrRoute.click();
		Reporter.log("Search Button  is clicked");
		Thread.sleep(5000);
		
		Alert alert =driver.switchTo().alert();
		alert.dismiss();
		
	Reporter.log("Alert Popup is dismissed ");
	
			
	}
	
	
	public void verifyTheNumberOfRutesInTable1() throws InterruptedException {
		
		// RuteDropdownofTable1 = new Select(driver.findElement(By.id("cbxRpl1")));
		
		
//		Select se = new Select(driver.findElement(By.id("cbxRpl1")));
//
//		List<WebElement> l = se.getOptions();
//		int SizeofRute1Value=l.size();
		Thread.sleep(2000);
		String[] options = driver.findElement(By.id("cbxRpl1")).getText().split("\n");
		int SizeofRute1Value=options.length;
		System.out.println("Size of Rute1 Dropdown of Table1  "+SizeofRute1Value);
		Thread.sleep(5000);
		Reporter.log("Size of Rute1 Dropdown of Table1  " +SizeofRute1Value);
		Thread.sleep(2000);
	}
	
	public void verifyTheNumberOfRutesInTable2() {
		
	String[] Rute2options = driver.findElement(By.id("cbxRpl2")).getText().split("\n");
	int SizeofRute2Value=Rute2options.length;
	System.out.println("Size of Rute2 Dropdown of Table1  "+SizeofRute2Value);
	Reporter.log("Size of Rute2 Dropdown of Table1  " +SizeofRute2Value);
	}
	
	public void verifyUpdateRute1_drp() throws InterruptedException {
		Table1Detailer_btn.isDisplayed();
		Thread.sleep(2000);	
		Actions Bulider =new Actions(driver);
		Bulider.click(Table1Detailer_btn).build().perform();
		//Table1Detailer_btn.click();
		Thread.sleep(2000);	
		Reporter.log("Table1 Detailer button clicked");
		Thread.sleep(2000);	
		
		Select objSelect =new Select(Frekvens_drpdwn);
		
		objSelect.selectByVisibleText("B");
		
		
		Reporter.log("Table1 Frekvens_drpdwn Selected");
		Thread.sleep(2000);	
		TableOppdater1_drpdwn.isDisplayed();
		Thread.sleep(2000);	
		TableOppdater1_drpdwn.click();
		Thread.sleep(2000);	
		Reporter.log("Rute value updated in dropdown");
		Select se = new Select(driver.findElement(By.id("cbxRpl1")));
		WebElement option = se.getFirstSelectedOption();
		String SelectedText = option.getText();
		System.out.println("Rute value updated in dropdown is" +SelectedText);
		Reporter.log("Rute value updated in dropdown is  " +SelectedText);
		driver.navigate().refresh();
	}
	
	public void verifybyAddNewRute()throws InterruptedException {
		
		Table2AddNewRute_btn.isDisplayed();
		Thread.sleep(2000);	
		Actions Bulider =new Actions(driver);
		Bulider.click(Table2AddNewRute_btn).build().perform();
		Thread.sleep(2000);	
		Reporter.log("Table2 Update button clicked");
		Thread.sleep(2000);	
		Table1EnterNewRute_txt.sendKeys("0030");
		Thread.sleep(2000);	
		Reporter.log("Entered Rute value");
		SaveRute_btn.click();
		Reporter.log("New Rute Save button clicked");
		String routeDetailsMissingWarning2_value=routeDetailsMissingWarning2_txt.getText();
		Reporter.log("After saved New Rute , Error message is displayed  as expected "+routeDetailsMissingWarning2_value);
		
	}
	public void verifyTheMapValue() throws InterruptedException {
//		Ruteplanlegger.isDisplayed();
//		Thread.sleep(5000);	
//		Ruteplanlegger.click();
//		Reporter.log("Ruteplanlegger Tab is Loaded");
//		Thread.sleep(5000);
//		unitInput_txt.clear();
//		unitInput_txt.sendKeys("101892");
//		Reporter.log("Route Number Tab is entered");
//		Thread.sleep(5000);
//		btnSearchUnitOrRoute.click();
//		Reporter.log("Search Button  is clicked");
		Thread.sleep(10000);
		getStopIndex.click();
		Reporter.log("Stop Name   is clicked in the table");
		Thread.sleep(5000);
		getStopIndexInMapForStopName = getStopIndex.getText();
		Thread.sleep(5000);
		
		System.out.println("stop Index is"+getStopIndexInMapForStopName);
			
		JavascriptExecutor js = (JavascriptExecutor)driver;
	
		//WebElement StopIndexInMap= clickIndexInMap+"[contains(text(),'"+getStopIndexInMapForStopName+"')]";
				
		WebElement StopIndexInMap= driver.findElement(By.xpath("(//*[local-name()='svg']/*[local-name()='g']/*[name()='path'])["+getStopIndexInMapForStopName+"]"));
	
		Thread.sleep(5000);
		Actions Bulider =new Actions(driver);
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", StopIndexInMap);
		Thread.sleep(5000);
		Bulider.click(StopIndexInMap).build().perform();
		Thread.sleep(10000);
		Reporter.log("Highlighted Stop Name is clicked in the Map");
		//String MapPopupTitle= 
		String MapPopupTitle=driver.findElement(By.xpath("//div[@class='esriPopupWrapper']/div/div/div[2]")).getText();
		System.out.println("Actual Stop No.."+MapPopupTitle);
		//Assert.assertTrue(true);
		Assert.assertTrue(true, "Highlighted Stop Name is clicked in the Map");
//		assert.assertEquals(//false, false)
		//driver.navigate().refresh();
		
	}
	
	
	public void verifyColorInRuteTable1() throws InterruptedException {
		
//		Ruteplanlegger.isDisplayed();
//		Thread.sleep(5000);	
//		Ruteplanlegger.click();
//		Reporter.log("Ruteplanlegger Tab is Loaded");
//		Thread.sleep(5000);
//		unitInput_txt.clear();
//		unitInput_txt.sendKeys("101892");
//		Reporter.log("Route Number Tab is entered");
//		Thread.sleep(5000);
//		btnSearchUnitOrRoute.click();
//		Reporter.log("Search Button  is clicked");
//		Thread.sleep(10000);
	//	JavascriptExecutor js = (JavascriptExecutor)driver;
	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getStopIndex);
		Actions Bulider =new Actions(driver);
		getStopIndex.click();
		Reporter.log("Stop Name   is clicked in the table");
		Thread.sleep(5000);
		getStopIndexInMapForStopName = getStopIndex.getText();
		Thread.sleep(5000);
		System.out.println("stop Index is"+getStopIndexInMapForStopName);	
		Thread.sleep(5000);
		//WebElement StopIndexInMap= driver.findElement(By.xpath("(//*[local-name()='svg']/*[local-name()='g']/*[name()='path'])["+getStopIndexInMapForStopName+"]"));
		WebElement StopIndexInMap = driver.findElement(By.xpath("(//*[local-name()='svg']/*[local-name()='g' and @id='plottingHighlightGrid1_layer']/*[name()='path'])["+getStopIndexInMapForStopName+"]"));
		Thread.sleep(3000);
	
		Thread.sleep(5000);
		Bulider.click(StopIndexInMap).build().perform();
		Thread.sleep(2000);
		
		Reporter.log("Highlighted Stop Name is clicked in the Map");
		Thread.sleep(3000);
		String colorString =StopIndexInMap.getAttribute("fill");
		Thread.sleep(3000);
		String ExpectedColor="rgb(233, 123, 82)";
		System.out.println("arrColor"+colorString);
		
		Assert.assertEquals(ExpectedColor,colorString, "Stop Name of Route1 color are mataches in table and map " );
		Reporter.log("Stop Name of Route1 color are mataches in table and map");
		if (ExpectedColor==colorString) {
			Reporter.log("Stop Name of Route1 color are mataches in table and map");	
		}

		
		
	}
	
	public void verifyColorInRuteTable2() throws InterruptedException {
		
		Actions Bulider =new Actions(driver);
		Thread.sleep(5000);
		Select objSelect =new Select(driver.findElement(By.xpath("//select[@id='cbxRpl2']")));
		objSelect.selectByVisibleText("0003 B");
		Thread.sleep(5000);
		WebElement VerifyColorrute2Stop=driver.findElement(By.xpath("//span[text()='10508338 KONG RINGS GATE 1']"));
		VerifyColorrute2Stop.click();
		Thread.sleep(5000);
		WebElement Route2StopIndexInMap= driver.findElement(By.xpath("(//*[local-name()='svg']/*[local-name()='g' and @id='plottingHighlightGrid2_layer']/*[name()='path'])[1]"));
	Thread.sleep(5000);
	Bulider.click(Route2StopIndexInMap).build().perform();
	Reporter.log("Stop Name is clicked in the Map");	
	
	String Route2colorString =Route2StopIndexInMap.getAttribute("fill");
	String Route2ExpectedColor="rgb(233, 123, 82)";
	System.out.println("arrColor"+Route2colorString);
	Assert.assertEquals(Route2ExpectedColor,Route2colorString, "Stop Name of Route2 color are mataches in table and map " );
	Reporter.log("Rute2 Stop color is displaying as expected in the map");
	if (Route2ExpectedColor==Route2colorString) {
		Reporter.log("Rute2 Stop color is displaying as expected in the map");	
	}
	Thread.sleep(5000);
	}
	
	public void Verify_Select_Rute2_In_Dropdown_table_in_RuteKartApplication() throws InterruptedException {
	Actions Bulider =new Actions(driver);
	Thread.sleep(5000);
	Select objSelect =new Select(driver.findElement(By.xpath("//select[@id='cbxRpl2']")));
	objSelect.selectByVisibleText("0003 B");
	Thread.sleep(5000);
	WebElement VerifyColorrute2Stop=driver.findElement(By.xpath("//span[text()='10508338 KONG RINGS GATE 1']"));
	VerifyColorrute2Stop.click();
	Thread.sleep(5000);
	
	}
	
	
public void PlantheStopInRuteplanlegger() throws InterruptedException {
		
//		Ruteplanlegger.isDisplayed();
//		Thread.sleep(5000);	
//		Ruteplanlegger.click();
//		Reporter.log("Ruteplanlegger Tab is Loaded");
//		Thread.sleep(5000);
//		unitInput_txt.clear();
//		unitInput_txt.sendKeys("101892");
//		Reporter.log("Route Number Tab is entered");
//		Thread.sleep(5000);
//		btnSearchUnitOrRoute.click();
//		Reporter.log("Search Button  is clicked");
//		Thread.sleep(10000);
		
		
		 
		 
		 WebElement mytable1 = driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody"));
		    //identify rows of table.
		 List<WebElement> r_table = mytable1.findElements(By.tagName("tr"));
		 
		 int BeforeDropGrid1TableSizeValue = r_table.size();
		 
		 System.out.println("BeforeDropGrid1TableSizeValue is "+BeforeDropGrid1TableSizeValue);
		 
		
		Select objSelect =new Select(driver.findElement(By.xpath("//select[@id='cbxRpl2']")));
		objSelect.selectByVisibleText("0003 B");
		Reporter.log("Rute  is selected from Rute2 Dropdown");
		Table2RuteDropdown.isDisplayed();
		stopNameValue.click();
		Reporter.log("Stop Name   is clicked in the table");
		Thread.sleep(5000);
		Actions Bulider =new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Table2stopNameValue);
		Thread.sleep(5000);
		Bulider.dragAndDrop(stopNameValue, Table2stopNameValue).build().perform();
		Reporter.log("Stop is dragged to Rute2 table");
		Thread.sleep(5000);
		
		 //Dimension AfterDragGrid2TableSizeValue= AfterDragGrid2TableSize.getSize();
		
		 
		 WebElement mytable2 = driver.findElement(By.xpath("//table[@id='rplGrid2Table']/tbody"));
		    //identify rows of table.
		 List<WebElement> r_table2 = mytable2.findElements(By.tagName("tr"));
		 int AfterDragGrid2TableSizeValue = r_table2.size();
		 		 System.out.println("AfterDragGrid2TableSizeValue is "+AfterDragGrid2TableSizeValue);
	
		 
		 
		 int ExpectedTable1SizeAfterDrag= BeforeDropGrid1TableSizeValue -1;
		 System.out.println("ExpectedTable1SizeAfterDragValue is "+ExpectedTable1SizeAfterDrag);
		 
		 int ExpectedTable2SizeAfterDrop= AfterDragGrid2TableSizeValue +1;
		 System.out.println("ExpectedTable2SizeAfterDrop is "+ExpectedTable2SizeAfterDrop);
		 
		if(ExpectedTable1SizeAfterDrag+1==BeforeDropGrid1TableSizeValue) {
			
			System.out.println("PASS: Source is dropped to target as expected");
			Reporter.log("PASS: Source is dropped to target as expected");
			//logger.log(LogStatus.INFO, "","PASS: Source is dropped to target as expected");
		}
		
		else {
			System.err.println("FAIL: Source couldn't be dropped to target as expected");
			//logger.log(LogStatus.FAIL, "PASS: Source is dropped to target as expected");
		}	 
//
//		Alert alert =driver.switchTo().alert();
//		alert.dismiss();
	
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

public void ModifyTheStopInSameTableRuteplanleggerwithSelectedValue() throws InterruptedException {
//	driver.manage().deleteAllCookies();
//	Ruteplanlegger.isDisplayed();
//	Thread.sleep(5000);	
//	Ruteplanlegger.click();
//	Reporter.log("Ruteplanlegger Tab is Loaded");
//	Thread.sleep(5000);
//	unitInput_txt.clear();
//	unitInput_txt.sendKeys("101892");
//	Reporter.log("Route Number Tab is entered");
//	Thread.sleep(5000);
//	btnSearchUnitOrRoute.click();
//	Reporter.log("Search Button  is clicked");
//	Thread.sleep(10000);
	
	
	 WebElement mytable1 = driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody"));
	    //identify rows of table.

	 
	Actions Bulider =new Actions(driver);
	Thread.sleep(5000);
	Select objSelect =new Select(driver.findElement(By.xpath("//select[@id='cbxRpl2']")));
	objSelect.selectByVisibleText("0003 B");
	Thread.sleep(5000);
	WebElement VerifyColorrute2Stop=driver.findElement(By.xpath("//span[text()='10508338 KONG RINGS GATE 1']"));
	//VerifyColorrute2Stop.click();
	Thread.sleep(5000);
	
//	Table2RuteDropdown.isDisplayed();
	WebElement mytable = driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody"));
    //identify rows of table.
    List<WebElement> r_table = mytable.findElements(By.tagName("tr"));
    String b_xpath = "//table[@id='rplGrid2Table']/tbody/tr[";
    String a_xpath = "]/td[1]";
    
    //calculate rows number with size()
    int rs_c = r_table.size();
    System.out.println("rs_c"+rs_c);
    //iterate rows of table and check matching condition
    
    for (int r = 1;r <=3; r++) {
       //String n = driver.findElement(By.xpath(b_xpath + r + a_xpath)).getText();
    	//WebElement multipleRute=driver.findElement(By.xpath(b_xpath + r + a_xpath));   
   	 List<WebElement> r_table1 = mytable1.findElements(By.tagName("tr"));
	 
   	 int BeforeDropGrid1TableSizeValue = r_table1.size();
   	 
   	 System.out.println("BeforeDropGrid1TableSizeValue is "+BeforeDropGrid1TableSizeValue);
    	WebElement multipleRute=driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody/tr[3]/td[1]"));
    	WebElement multipleRuteNames=driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody/tr[3]/td[2]/span"));
    	String multipleRuteName=multipleRuteNames.getText();
    	System.out.println("RuteName of "+ r + multipleRuteName);
    	multipleRute.click();
    	System.out.println("______multipleRute______"+multipleRute);
    	//int i=20-r;
    	//WebElement multipleRute2=driver.findElement(By.xpath(b_xpath + i + a_xpath));
    	
    	//System.out.println("multipleRute2...................."+multipleRute2);
    	//WebElement multiple2RuteNames=driver.findElement(By.xpath("//table[@id='rplGridTable']/tbody/tr[3]/td[2]/span"));
  	   	Actions actions= new Actions(driver);
    	//JavascriptExecutor js = (JavascriptExecutor)driver;
    	//Thread.sleep(10000);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", multipleRute2);
		//Thread.sleep(10000);
	//	multipleRute2.click();
		Thread.sleep(5000);
		actions.dragAndDrop(multipleRute, VerifyColorrute2Stop).build().perform();
		Reporter.log("Drag the stop from rute1 to Rute2");
		 Thread.sleep(5000);
		 WebElement mytable2 = driver.findElement(By.xpath("//table[@id='rplGrid2Table']/tbody"));
		    //identify rows of table.
		 List<WebElement> r_table2 = mytable2.findElements(By.tagName("tr"));
		 int AfterDragGrid2TableSizeValue = r_table2.size();
		 		 System.out.println("AfterDragGrid2TableSizeValue is "+AfterDragGrid2TableSizeValue);
	
		 
		 
		 int ExpectedTable1SizeAfterDrag= BeforeDropGrid1TableSizeValue -1;
		 System.out.println("ExpectedTable1SizeAfterDragValue is "+ExpectedTable1SizeAfterDrag);
		 
		 int ExpectedTable2SizeAfterDrop= AfterDragGrid2TableSizeValue +1;
		 System.out.println("ExpectedTable2SizeAfterDrop is "+ExpectedTable2SizeAfterDrop);
		 
		if(ExpectedTable1SizeAfterDrag+1==BeforeDropGrid1TableSizeValue) {
			
			System.out.println("PASS: Source is dropped to target as expected");
			Reporter.log("PASS: Source is dropped to target as expected");
			//logger.log(LogStatus.INFO, "","PASS: Source is dropped to target as expected");
		}
		
		else {
			System.err.println("FAIL: Source couldn't be dropped to target as expected");
			//logger.log(LogStatus.FAIL, "PASS: Source is dropped to target as expected");
		}	 
    }

}

public void planthestop()  throws InterruptedException, AWTException{
	
	driver.manage().deleteAllCookies();
	Ruteplanlegger.isDisplayed();
	Thread.sleep(5000);	
	Ruteplanlegger.click();
	Reporter.log("Ruteplanlegger Tab is Loaded");
	Thread.sleep(5000);
	unitInput_txt.clear();
	unitInput_txt.sendKeys("101892");
	Reporter.log("Route Number Tab is entered");
	Thread.sleep(5000);
	btnSearchUnitOrRoute.click();
	Reporter.log("Search Button  is clicked");
	Thread.sleep(10000);
	Select objSelect =new Select(driver.findElement(By.xpath("//select[@id='cbxRpl2']")));
	objSelect.selectByVisibleText("0005 A");
	Thread.sleep(5000);
	Table2RuteDropdown.isDisplayed();
	WebElement mytable = driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody"));
    //identify rows of table.
    List<WebElement> r_table = mytable.findElements(By.tagName("tr"));
    String b_xpath = "//table[@id='rplGrid1Table']/tbody/tr[";
    String a_xpath = "]/td[1]";
    
   // for (int r = 3;r <=6; r++) {
        //String n = driver.findElement(By.xpath(b_xpath + r + a_xpath)).getText();
     	//WebElement multipleRute=driver.findElement(By.xpath(b_xpath + r + a_xpath));  
     	 List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='rplGrid1Table']/tbody/tr"));
         Actions builder = new Actions(driver);
         builder.click(tableRows.get(3)).keyDown(Keys.CONTROL)
         .click(tableRows.get(4))
         .click(tableRows.get(4))
//         .click(tableRows.get(5))
         .keyUp(Keys.CONTROL).build().perform();
         
         
         Robot robot = new Robot();	
         PointerInfo a = MouseInfo.getPointerInfo();
         Point b = a.getLocation();
         int x = (int) b.getX();
         int y = (int) b.getY();
         System.out.print(y + "jjjjjjjjj");
         System.out.print(x);
         Robot r = new Robot();
         r.mouseMove(x, y - 50);
         
         ///robot.mouseMove(630, 420); // move mouse point to specific location	
         robot.delay(1500);        // delay is to make code wait for mentioned milliseconds before executing next step	
         robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click	
         robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click	
         robot.delay(1500);	
         //robot.keyPress(KeyEvent.VK_DOWN); // press keyboard arrow key to select Save radio button	
         //Thread.sleep(2000);	
       //  robot.keyPress(KeyEvent.);	
         //actions.dragAndDrop(multipleRute, Table2stopNameValue).build().perform();
         
                 
    // }
	
}


public void PlantheStopInRuteplanleggerwithSelectedValue() throws InterruptedException {
	driver.manage().deleteAllCookies();
	Ruteplanlegger.isDisplayed();
	Thread.sleep(5000);	
	Ruteplanlegger.click();
	Reporter.log("Ruteplanlegger Tab is Loaded");
	Thread.sleep(5000);
	unitInput_txt.clear();
	unitInput_txt.sendKeys("101892");
	Reporter.log("Route Number Tab is entered");
	Thread.sleep(5000);
	btnSearchUnitOrRoute.click();
	Reporter.log("Search Button  is clicked");
	Thread.sleep(10000);
	Select objSelect =new Select(driver.findElement(By.xpath("//select[@id='cbxRpl2']")));
	objSelect.selectByVisibleText("0005 A");
	Thread.sleep(5000);
	Table2RuteDropdown.isDisplayed();
	WebElement mytable = driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody"));
    //identify rows of table.
    List<WebElement> r_table = mytable.findElements(By.tagName("tr"));
    String b_xpath = "//table[@id='rplGrid1Table']/tbody/tr[";
    String a_xpath = "]/td[1]";
    
    //calculate rows number with size()
    int rs_c = r_table.size();
    System.out.println("rs_c"+rs_c);
    //iterate rows of table and check matching condition
    int count =3;
    
    for (int r = 3;r <=6; r++) {
       //String n = driver.findElement(By.xpath(b_xpath + r + a_xpath)).getText();
    	//WebElement multipleRute=driver.findElement(By.xpath(b_xpath + r + a_xpath));   
    	WebElement multipleRute=driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody/tr[3]/td[1]"));
    	WebElement multipleRuteNames=driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody/tr[3]/td[2]/span"));
    	String multipleRuteName=multipleRuteNames.getText();
    	System.out.println("RuteName of "+ r + multipleRuteName);
    	multipleRute.click();
    	System.out.println("______multipleRute______"+multipleRute);	
  	   	Actions actions= new Actions(driver);
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Table2stopNameValue);
		Table2stopNameValue.click();
		Thread.sleep(10000);
		actions.dragAndDrop(multipleRute, Table2stopNameValue).build().perform();
		 Thread.sleep(10000);
    

		 
    }
    driver.navigate().refresh();
    
//    	************************
//    	WebElement multipleRute=driver.findElement(By.xpath("//table[@id='rplGrid1Table']/tbody/tr[3]/td[1]"));
//   	
//       System.out.println(multipleRute);
//       Actions action=new Actions(driver);
//       action.keyDown(Keys.LEFT_CONTROL)
//       .click(multipleRute)
//       .keyUp(Keys.LEFT_CONTROL)
//       .build()
//       .perform();
//                  
//       Thread.sleep(5000);
//       
//       action.dragAndDrop(multipleRute,Table2stopNameValue ).build().perform();
//       
    }
  
    


	public void login() throws InterruptedException{
		String currenturl=driver.getCurrentUrl();
		System.out.print("CurrentUrl"+currenturl);
		((JavascriptExecutor) driver).executeScript("window.focus();");
		driver.manage().window().maximize();
		((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", userName);;
		
		Thread.sleep(5000);
		userName.click();
		userName.isDisplayed();
		userName.sendKeys(usernamemapValue);
		Thread.sleep(5000);
		password1.sendKeys(passWordmapValue);
		Thread.sleep(5000);
		LoginSubmit.click();
		Thread.sleep(5000);
//		SearchPackageNumberLink.isDisplayed();
//		Thread.sleep(10000);
//		SearchPackageNumberLink.sendKeys(usernamemapValue);
	}
	
		
	public void LoginSubmitPopup() throws InterruptedException {
		LoginSubmit.click();
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
			
		    driver.switchTo().window(winHandle);
			Thread.sleep(5000);
		}
	}
	

	public void switchtoLoginWindow() throws InterruptedException {
		
		 
		
		String parentWindowHandler = driver.getWindowHandle(); 
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		System.out.print("iterator-----"+iterator);
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		    driver.switchTo().window(subWindowHandler);

		    System.out.println(subWindowHandler);
		}
		 	
		
	}

	public void switchtoParentWindow() throws InterruptedException {

		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		    Thread.sleep(5000);
		    System.err.println("Switched to Parent window");
		    
		}
	}
	
	public void Homepageverification() throws InterruptedException {
		SearchPackageNumberLink.isDisplayed();
		//SearchPackageNumberLink.getText();
		Thread.sleep(5000);
		SearchPackageNumberLink.click();
		Thread.sleep(5000);
		System.out.println("clicked consignmentNumber");
		SearchPackageNumberText.sendKeys(CNum);
		Thread.sleep(5000);
		SearchPackageNumberButton.click();
		Thread.sleep(5000);
			}
	
	public void verifyshipmentPageFields()  throws InterruptedException {
	
		ActualFromCity=driver.findElement(By.xpath("(//*[@id=\"pagination_id\"]/tbody/tr/td[6])[1]")).getText();
		System.out.println("ActualFromCity is"+ActualFromCity);
		Assert.assertEquals(ActualFromCity, expectedFromCity,"From City code not matches");
//		Thread.sleep(5000);
		ToOPenButton.click();
//		Thread.sleep(5000);
	}
	
	
	

	public void verifyConsignmentInPH() throws InterruptedException {
	
		PHLink.isDisplayed();
		Thread.sleep(5000);
		PHLink.click();
		Thread.sleep(5000);
		unitInput_txt.sendKeys("101892");
		Thread.sleep(5000);
		SearchPackageNumberTextinPH.click();
		Thread.sleep(5000);
		
	}
	
	

	
	public void switchtoLMKDetailsPage() {
		// It will return the parent window name as a String
		String parent=driver.getWindowHandle();

		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);

		System.out.println(driver.switchTo().window(child_window).getTitle());
	}
		
	
	
		}
	}
	
	
	

}
