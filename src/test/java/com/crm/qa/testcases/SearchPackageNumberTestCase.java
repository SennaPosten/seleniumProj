package com.crm.qa.testcases;
import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.SaerchPackageNumberpage;
import com.google.common.base.Verify;

public class SearchPackageNumberTestCase {

	
	SearchPackageNumberTestCase searchPackageNumber;

	public SearchPackageNumberTestCase() {
		super();
	}
	

	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		//initializaton();
		searchPackageNumber = new SearchPackageNumberTestCase();
	}
	
	@Test(groups = { "Simple", "UI" })
	public boolean TC003_VerifyHeaderContent() {
		Assert.assertTrue(searchPackageNumber.TC003_VerifyHeaderContent(), "Search Consingmen");
		return true;
	}
	 @Test(groups = { "Functionality" }) public void
	  TC001_clickOk() throws Throwable {
		 searchPackageNumber.clickSearchPackageNumberLink();
	 }


	private void clickSearchPackageNumberLink() {
		// TODO Auto-generated method stub
		
	}
}
