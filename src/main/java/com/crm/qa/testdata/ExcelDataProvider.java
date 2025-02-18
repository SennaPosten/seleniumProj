package com.crm.qa.testdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class ExcelDataProvider {

	static File file = null;
	static FileInputStream input = null;
	static Workbook workbook = null;
	static Sheet sheet = null;
	static Row row = null;

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

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		System.out.println(line);
		StringBuffer sb = new StringBuffer(line);
		String line1 =line.concat("Senna");
		System.out.println(line1);
		System.out.println(sb.reverse().toString());
		readexcelData();
		readcsvData();
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
	
	public static void WriteExcel() throws ParserConfigurationException, IOException, SAXException
	{
		//ExcelReportGenerator.

	}
	
	//.generateExcelReport(“MyProject.xls”, “C:\\workspace”);
}
