package com.crm.qa.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import com.crm.qa.base.TestBase;

public class SeleniumActions extends TestBase {

	public void selectbyText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void selectbyValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public static boolean clickElement(WebDriver driver, By locator) {
	try {
		WebElement element= driver.findElement(locator);
		element.click();
		return true;
		
	}	catch(Exception e){
		System.out.println("An unexpected erroroccured while clicking element: "+locator);
		
		e.printStackTrace();
	}
	return false;
	}


}
