package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Iterator; 
import java.util.Set;

import com.crm.qa.base.TestBase;

public class SaerchPackageNumberpage extends TestBase {

	public SaerchPackageNumberpage() {
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id = "meny.sendingsinformasjon.sokkolli")
	WebElement SearchPackageNumberLink;
	
	
	public boolean verifySearchPackageNumberLink() throws InterruptedException {
		SearchPackageNumberLink.isDisplayed();
		Thread.sleep(10000);
		return true;
	}
	
	public void clickSearchPackageNumberLink() throws InterruptedException {
		SearchPackageNumberLink.click();
		Thread.sleep(10000);
	}
}
