package com.crm.qa.testdata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	int key1;
	String value ;
public  HashMap<Integer,String> readExcel() throws IOException {
		
	HashMap<Integer,String> Excelvalue=new HashMap<Integer,String>();
	FileInputStream fs = new FileInputStream("C:\\test.xlsx");
	//Creating a workbook
	XSSFWorkbook workbook = new XSSFWorkbook(fs);
	XSSFSheet sheet = workbook.getSheetAt(0);
	Row row = sheet.getRow(0);
	Cell cell = row.getCell(0);

	 
    int col_num = -1;
    	for(int i=0; i < row.getLastCellNum(); i++)
    {
		
    	String cars = cell.getSheet().getRow(0).getCell(i).getRichStringCellValue().toString();
        if(row.getCell(i).getStringCellValue().trim().equals(cars))
           col_num = i;         
    } 

		   for(int i=0;i<=col_num;i++) {
			   //System.err.println("Header size"+strlist.size());
			   	row = sheet.getRow(1);
		        XSSFCell cell1 = (XSSFCell) row.getCell(i);
   		         value = cell1.getStringCellValue();
   		           		        
   		         key1 = cell1.getColumnIndex();	        
   		      System.out.println("index of column and Value of the Excel Cell is - "+ value+"and"+key1);
		        Excelvalue.put(key1, value);  		
		   }
		  
		   for(Map.Entry m : Excelvalue.entrySet()){    
   		    System.out.println(m.getKey()+" "+m.getValue()); 
   		    
   		   } 
		 
		   fs.close();
   
		   return Excelvalue;	
    
 
    	}
		  
}
