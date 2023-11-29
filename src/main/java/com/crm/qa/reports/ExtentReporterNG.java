package com.crm.qa.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import com.relevantcodes.extentreports.LogStatus;
import com.crm.qa.base.TestBase;
import com.crm.qa.utilities.TestUtils;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.crm.qa.util.TestUtil;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.
//import com.aventstack.extentreports.MediaEntityModelProvider;



public class ExtentReporterNG implements IReporter {
	private ExtentReports extent;
	public static  WebDriver driver;
	//public static WebDriver driver = new FirefoxDriver();

	  
	static ExtentTest logger;
	//private final static String SCREENSHOT_LOCATION = "C:\\Screenshots\\test-image.png";
	//private final static String SCREENSHOT_LOCATION =System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
	
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
					//test.addScreenCapture(SCREENSHOT_LOCATION);
					
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}
				

				extent.endTest(test);
			}
		}
	}
	
	 public  String SCREENSHOT_LOCATIONs = "C:\\Screenshots\\test-image.png";
	 public  void  captureScreenshots() throws IOException, InterruptedException {
		 	
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File screenshotLocation = new File(SCREENSHOT_LOCATION);
			FileUtils.copyFile(screenshot, screenshotLocation);
			Thread.sleep(2000);
			InputStream is = new FileInputStream(screenshotLocation);
			byte[] imageBytes = IOUtils.toByteArray(is);
			Thread.sleep(2000);
			String base64 = Base64.getEncoder().encodeToString(imageBytes);
			//extentTest.log(LogStatus.INFO, "Snapshot below: " + extentTest.addBase64ScreenShot(base64));
			logger.log(LogStatus.INFO, "Snapshot below: " + logger.addScreenCapture(SCREENSHOT_LOCATIONs));			
			//extentTest.log(Status.FAIL, extentTest.addBase64ScreenShot(SCREENSHOT_LOCATION));
			
		}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	
	
	private   String SCREENSHOT_LOCATION = "C:\\screenshots\\test-image.png";
	public  void  captureScreenshot() throws IOException, InterruptedException {
//		System.setProperty("webdriver.gecko.driver", TestUtils.WORKSAPCE_PATH + "//drivers//geckodriver.exe");
//		driver =new FirefoxDriver();
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File screenshotLocation = new File(SCREENSHOT_LOCATION);
		FileUtils.copyFile(screenshot, screenshotLocation);
		Thread.sleep(2000);
		InputStream is = new FileInputStream(screenshotLocation);
		byte[] imageBytes = IOUtils.toByteArray(is);
		Thread.sleep(2000);
		String base64 = Base64.getEncoder().encodeToString(imageBytes);
		logger.log(LogStatus.INFO, "Snapshot below: " + logger.addBase64ScreenShot("data:image/png;base64," + base64));

//		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//	 
//	    TakesScreenshot ts = (TakesScreenshot)driver;
//	    File source = ts.getScreenshotAs(OutputType.FILE);
//	    String destination = System.getProperty("user.dir") + "/Screenshots/" +  dateName
//	            + ".png";
//	    System.out.println("destination"+destination);
//	    File finalDestination = new File(destination);
//	    FileHandler.copy(source, finalDestination);
//	    return destination;
	   
	}
	

	
	
	
//	public String captureScreen() throws IOException {
//		MediaModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build();
//		test.fail("details", mediaModel);	
//	}
//	
//	
//	public String getcurrentdateandtime(){
//		String str = null;
//		try{
//		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
//		Date date = new Date();
//		str= dateFormat.format(date);
//		str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
//		}
//		catch(Exception e){
//
//		}
//		return str;
//		}
		
	

	
	
	
}
