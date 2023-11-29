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
			if(entry.getKey().equals(4)) {
				System.out.print("map value--------------"+entry.getValue());
				usernamemapValue=entry.getValue();
			}
			
			if(entry.getKey().equals(5)) {
				System.out.print("map value--------------"+entry.getValue());
				passWordmapValue=entry.getValue();
			}
			
			if(entry.getKey().equals(2)) {
				System.out.print("map value--------------"+entry.getValue());
				CNum=entry.getValue();
			}
			if(entry.getKey().equals(6)) {
				System.out.print("map value--------------"+entry.getValue());
				expectedShipmentNumber=entry.getValue();
			}if(entry.getKey().equals(3)) {
				System.out.print("map value--------------"+entry.getValue());
				expectedFromCity=entry.getValue();
			}
			if(entry.getKey().equals(6)) {
				System.out.print("map value--------------"+entry.getValue());
				PHConsignmentNr=entry.getValue();
			}
			
			if(entry.getKey().equals(8)) {
				System.out.print("map value--------------"+entry.getValue());
				unitNumber=entry.getValue();
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
		unitInput_txt.sendKeys(CNum);
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
		
		//Select RuteDropdownofTable1 = new Select(driver.findElement(By.id("cbxRpl1")));
		
		
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
	

	public void switchtoLoginWindow() {

		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		    
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
//		ToOPenButton.click();
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
