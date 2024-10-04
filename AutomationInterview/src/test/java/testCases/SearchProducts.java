package testCases;
import base.BaseClass;
import pageObjects.home;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProducts extends BaseClass{
	
	@Test
	public void verifySortOrder() throws InterruptedException, IOException {
		

		home hm= new home(driver);
		
		String[] userinputs = new String [3];
		userinputs = fetchdata();
		System.out.print(Arrays.toString(userinputs));
		
		hm.enterSearchText(userinputs[0]);
		hm.clickSearchButton();
		hm.clickSortOption(userinputs[1]);
		hm.scrollDown();
		boolean result = hm.checkPriceOrder(userinputs[2]);
		
		Assert.assertEquals(result, true);
	}
	
	

	public String[] fetchdata() throws IOException {

	    String excelFilePath = System.getProperty("user.dir") + "\\dataFiles\\InputData.xlsx";

	    FileInputStream inputFileSteam = new FileInputStream(excelFilePath);

	    try (XSSFWorkbook wb = new XSSFWorkbook(inputFileSteam)) {
	        XSSFSheet sheet = wb.getSheet("Sheet1");

	        int rows = sheet.getLastRowNum();  // Last row index 
	        System.out.print(rows);

	        String[] input = new String [3];

	        XSSFRow row;
	        XSSFCell cell;

	        for (int i = 1; i <= rows-1; i++) {  // Start from 1 (assuming first row is header)
	            row = sheet.getRow(i);


	            if (row != null) {
	                for (int c = 0; c < 3; c++) {

	                    cell = row.getCell(c);
	                    String data = "";

	                    if (cell != null) {  
	                        if (cell.getCellType() == CellType.STRING) {
	                            data = cell.getStringCellValue();
	                        } else if (cell.getCellType() == CellType.NUMERIC) {
	                            data = String.valueOf(cell.getNumericCellValue());
	                            data = data.substring(0, data.length() - 2);   }
	                    }

	                    input[c] = data;  
	                }
	            }
	        }
	        return input;
	    }
	}


}
