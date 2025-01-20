package com.crm.qa.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hpsf.Date;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
//import com.aventstack.extentreports.ExtentReports;
import org.testng.log4testng.Logger;

//import org.testng.log4testng.Logger;
import org.testng.ITestResult;

//import com.crm.qa.testcases.chromedriver;
import com.crm.qa.utilities.SeleniumActions;
import com.crm.qa.utilities.TestUtils;
import com.crm.qa.utilities.WebDriverListener;
//import com.crm.qa.reports.ExtentReporterNG;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
//import com.aventstack.extentreports.ExtentTest.

public class TestBase {

	protected static WebDriver driver;
	protected static Properties properties;
	protected static SeleniumActions sele_Actions;
	protected static WebDriverListener eventListener;
	protected static EventFiringWebDriver e_driver;
	protected static ChromeOptions chromeOptions;
	

	static File file = null;
	static FileInputStream input = null;
	static Workbook workbook = null;
	static Sheet sheet = null;
	static Row row = null;
//	private ITestResult result;
//	private ExtentTest extentTest;
//	private ExtentReports extent;

	protected static Logger log;
	public static ITestResult result;
	public static ExtentReports extent; 
	public  static ExtentTest extentTest;
	 
	
	
	
	public TestBase() {

		try {
			properties = new Properties();
			
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/crm/qa/config/config.properties");
					//System.getProperty("user.dir") + "/src/main/resources/test.xlsx");
			
			properties.load(ip);
			
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("io exception");

		}

	}

	protected static void initializaton() {
		String browserName = properties.getProperty("browser");
		driver = getDriver(browserName);
		//log.info(browserName + " is configured");

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebDriverListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.get(properties.getProperty("url3"));

		sele_Actions = new SeleniumActions();

	}

	private static WebDriver getDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", TestUtils.WORKSAPCE_PATH + "//src//drivers//chromedriver.exe");
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			return new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("FF")) {
			///System.setProperty("webdriver.gecko.driver", "C://Users//Desktop//geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", TestUtils.WORKSAPCE_PATH + "//drivers//geckodriver.exe");
			return new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", TestUtils.WORKSAPCE_PATH + "//drivers//IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
		return null;
	}

	public void tearDownMain() throws InterruptedException {
		driver.manage().deleteAllCookies();
		driver.close();
		Thread.sleep(5000);
	}
	
	
	public   void startTest()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"ExtentReportResults.html",true);
		extentTest = extent.startTest("ExtentDemo");
	}
	
	
	public void setExtend() {
	
		extent = new ExtentReports(System.getProperty("user.dir") +"\\test-output\\NewExtentReport.html", true);
		//test =extent.startTest("installapp");
		Map<String, String> info = new HashMap<String, String>(); 
		info.put("host name", "POS-S1C05-3-329");
		info.put("user name", "Po30911"); 
		info.put("Environment", "QA");
		extent.addSystemInfo(info);	
	
	}
	 
	public static void FormatResult() { 
		System.out.println("-------------format result---------------");
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Failed test case is ::" + result.getName());
			extentTest.log(LogStatus.FAIL, "Failed test case is ::" +result.getThrowable());
			TestUtils.takeScreenShot(driver);
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(TestUtils.SCREENSHOT_PATH)); 
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Skipped test case is ::" + result.getName());
			}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Passed testc case is ::" + result.getName());
			}
		extent.endTest(extentTest);
		extent.flush();

	}
	
	 public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
	                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}
	 
	//Method for adding logs passed from test cases
	 public void reportLog(String message) {    
		 extentTest.log(LogStatus.INFO, message);//For extentTest HTML report

	}
	 public  String SCREENSHOT_LOCATION = "C:\\Screenshots\\test-image.png";
	 public  void  captureScreenshot() throws IOException, InterruptedException {
	//	 	extentTest = extent.startTest("capturescreenshot");
//			System.setProperty("webdriver.gecko.driver", TestUtils.WORKSAPCE_PATH + "//drivers//geckodriver.exe");
//			driver =new FirefoxDriver();
			
		 File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File screenshotLocation = new File(SCREENSHOT_LOCATION);
			FileUtils.copyFile(screenshot, screenshotLocation);
			Thread.sleep(2000);
			InputStream is = new FileInputStream(screenshotLocation);
			byte[] imageBytes = IOUtils.toByteArray(is);
			Thread.sleep(2000);
			String base64 = Base64.getEncoder().encodeToString(imageBytes);
			
			//extentTest.addScreenCapture(SCREENSHOT_LOCATION);
			//extentTest.log(LogStatus.INFO, "Snapshot below: " + extentTest.addBase64ScreenShot(base64));
			//extentTest.log(LogStatus.INFO, "Snapshot below: " + extentTest.addScreenCapture(SCREENSHOT_LOCATION));			
			//extentTest.log(Status.FAIL, extentTest.addBase64ScreenShot(SCREENSHOT_LOCATION));
			//extent.endTest(extentTest);
			//extent.flush();
						
		}
		

	 public static String getScreenshot( String screenshotName) throws Exception {
         //below line is just to append the date format with the screenshot name to avoid duplicate names		
         String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
         //after execution, you could see a folder "FailedTestsScreenshots" under src folder
	String destination = System.getProperty("user.dir") + "/Screenshot/"+screenshotName+dateName+".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
         //Returns the captured file path
	return destination;
}
	
	public static String readexcelData() {
		try {
			file = new File("C:/Users/PO30911/Downloads/Selenium-POM-TestNG-Maven-master/Selenium-POM-TestNG-Maven-master/src/main/resources/test.xlxs");
		//	
		//"/src/main/java/com/crm/qa/config/config.properties");
			String fp= file.getAbsolutePath();
			System.err.println(fp);
			input = new FileInputStream(file);
			String filepath=file.getAbsolutePath();
			System.err.println("filepath"+filepath);
			workbook = new XSSFWorkbook(input);
			sheet = workbook.getSheet("test1");
			
			int totalRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for (int i = 0; i < totalRows; i++) {
				row = sheet.getRow(i);
				System.out.println();
				for (int j = 0; j < row.getLastCellNum(); j++) {
					try {
						System.out.print(row.getCell(j).toString() + "||");
					} catch (NullPointerException e) {
						System.out.print("");
					}
				}
			}
			input.close();
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
	private static void readcsvData() throws IOException {
		String line = "";
		List<String> list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("\\test .csv"));
		while ((line = br.readLine()) != null) {
			list.add(line);
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}
	

		 
	
 }
