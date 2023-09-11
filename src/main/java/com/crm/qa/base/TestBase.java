package com.crm.qa.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
//import org.testng.log4testng.Logger;
import org.testng.ITestResult;

import com.crm.qa.utilities.SeleniumActions;
import com.crm.qa.utilities.TestUtils;
import com.crm.qa.utilities.WebDriverListener;
import com.crm.qa.reports.ExtentReporterNG;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



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
	private ITestResult result;
	private ExtentTest extentTest;
	private ExtentReports extent;

	//protected static Logger log;

	/*
	 * protected ITestResult result; protected ExtentReports extent; protected
	 * ExtentTest extentTest;
	 */
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

		driver.get(properties.getProperty("url"));

		sele_Actions = new SeleniumActions();

	}

	private static WebDriver getDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", TestUtils.WORKSAPCE_PATH + "//src//drivers//chromedriver.exe");
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			return new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("FF")) {
			//System.setProperty("webdriver.gecko.driver", "C://Users//Desktop//geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", TestUtils.WORKSAPCE_PATH + "//drivers//geckodriver.exe");
			return new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", TestUtils.WORKSAPCE_PATH + "//drivers//IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
		return null;
	}

	public void tearDownMain() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	
	
	public void setExtend() {
		extent = new ExtentReports(System.getProperty("user.dir") +"//test-output//Extent.html", true);
		Map<String, String> info = new HashMap<String, String>(); 
		info.put("host name", "POS-S1C05-3-329");
		info.put("user name", "PO30911"); 
		info.put("Environment", "QA");
		extent.addSystemInfo(info);
		
		
	}
	 
	public void FormatResult() { 
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
			sheet = workbook.getSheet("test");
			
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
