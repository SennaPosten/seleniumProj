package com.crm.qa.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
	//getParecelNumber;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	//
	 ReadExcel rd= new ReadExcel();
	//Path of the excel file
	 
	
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
			if(entry.getKey().equals(7)) {
				System.out.print("map value--------------"+entry.getValue());
				PHConsignmentNr=entry.getValue();
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
		SearchPackageNumberTextinPH.sendKeys(PHConsignmentNr);
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
