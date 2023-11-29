package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.reports.ExtentReporterNG;

import com.google.common.base.Verify;
import com.crm.qa.testdata.ReadExcel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PostenHomePageTestCase extends TestBase {

	HomePage homepage;
	
	
	
	public PostenHomePageTestCase() {
		super();
	}

//
//	 @BeforeTest (alwaysRun = true)
//	 public void setExtentReport() { 
//		 setExtend();
//		 }
	 
	
	
	
	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		initializaton();
		homepage = new HomePage();
	}
	
	
	

	  @Test(groups = { "ReadExcel" }) 
	  public void TC001_readExcel() throws IOException, InterruptedException {
		  homepage.readexcelMap();
		  //SaerchPackageNumberpage.
//		  homepage.switchtoLoginWindow(); 
//		  homepage.login();
//		  homepage.switchtoParentWindow();
//		  homepage.Homepageverification();
//		  homepage.verifyshipmentPageFields();
//		  
	 
}


	  @Test(groups = { "Functionality" }) 
	  public void TC002_login() throws IOException, InterruptedException {
		  homepage.switchtoLoginWindow(); 
		  homepage.login();
		  homepage.switchtoParentWindow();
	  
	 
}

	  @Test(groups = { "Functionality" }) 
	  public void TC003_Homepageverification() throws IOException, InterruptedException {
	
		  homepage.Homepageverification();
		
		  
	 
}

	  

	  @Test(groups = { "Functionality" }) 
	  public void TC004_verifyshipmentPage() throws IOException, InterruptedException {

		  homepage.verifyshipmentPageFields();
		  
	 
}
	

	  @Test(groups = { "Functionality" }) 
	  public void TC005_verifyConsignmentInPHn() throws IOException, InterruptedException {
	
		  homepage.verifyConsignmentInPH();
		
		  
	 
}
	  
	  

	  
//	  @Test(groups = { "Functionality" }) 
//	  public void TC002_SwitchtoLoginWindow() {
//	  homepage.switchtoLoginWindow(); 
//	 
//  }
	 
	
//	  @Test(groups = { "Functionality" })
//	  public void TC002_enterUsername(String usernamemapvalue) throws Throwable {
//	  homepage.enterUsername(usernamemapvalue);
//	   }
//
//		
//	 @Test(groups = { "Functionality" }) public void
//	  TC003_enterpassword() throws Throwable {
//	  homepage.enterPassword(properties.getProperty("PW"));
//	   }
//	 
//	 
//	 @Test(groups = { "Functionality" }) public void
//	  TC004_clickOk() throws Throwable {
//	  homepage.LoginSubmitPopup();
//	  Thread.sleep(10000);
//	 }
	 
	 
//		@Test(groups = { "Simple", "UI" })
//		public void TC003_VerifyHeaderContent() {
//		homepage.getPageHeader();
//		Assert.assertEquals(homepage.getPageHeader(), "Logistikkmotor");
//		}
		
//	 @Test(groups = { "Functionality" }) public void
//	  TC004_clickConsignmentNumberPage() throws Throwable {
//	  homepage.clickSearchPackageNumberLink();
//	  Thread.sleep(10000);
//	 }
	 
	 
		/*
		 * @Test(groups = { "Functionality" }) public void TC004_clickOk() throws
		 * Throwable { homepage.LoginSubmitPopup(); }
		 */
	 
		/*
		 * @Test(groups = { "Simple", "UI" }) public boolean TC003_VerifyHeaderContent()
		 * throws InterruptedException {
		 * Assert.assertTrue(homepage.verifySearchPackageNumberLink(),
		 * "Search Consingmen"); return true; }
		 */
		/*
		 * @Test(groups = { "Functionality" }) public void TC001_clickOk() throws
		 * Throwable { homepage.clickSearchPackageNumberLink(); }
		 */


	
		  @AfterSuite(alwaysRun = true) public void tearDown() {
		 FormatResult();
		  tearDownMain(); }
		 

}
