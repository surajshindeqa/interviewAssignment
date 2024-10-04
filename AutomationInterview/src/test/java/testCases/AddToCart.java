package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import pageObjects.Cart;
import pageObjects.ProductDetails;
import pageObjects.home;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pageObjects.home;

public class AddToCart  extends BaseClass{
	
	
	@Test
	public void Validate_Add_to_Cart() throws InterruptedException, IOException {
		

		String ProductName1,ProductName2 ;
		String Price1, Price2;
		String totaItemlPrice;
		String[] userinputs = new String [2];
		
		home hm= new home(driver);
		
		ProductDetails pd= new ProductDetails(driver);
		
		Cart cr=new Cart(driver);
		
		
		userinputs = fetchdata();
		System.out.print(Arrays.toString(userinputs));
		
		hm.enterSearchText(userinputs[0]);
		hm.clickSearchButton();
		hm.clickSortOption(userinputs[1]);
		String parent=driver.getWindowHandle();
		hm.viewProduct("2");
		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    
		ProductName1=pd.getProductName();
		Price1= pd.getProductPrice().substring(1);
		
		pd.clickAddToCartButton();
		Thread.sleep(5000);
		
		driver.switchTo().window(parent);
		Thread.sleep(4000);
		
		hm.viewProduct("3");
		Thread.sleep(5000);
		
		ProductName2=pd.getProductName();
		Price2= pd.getProductPrice().substring(1);
		
		pd.clickAddToCartButton();
		Thread.sleep(5000);
		
		totaItemlPrice= cr.getTotalPrice().substring(1);
		
		Assert.assertEquals(Integer.parseInt(totaItemlPrice), Integer.parseInt(Price1)  + Integer.parseInt(Price2) );
	}
	
	
	
	

	public String[] fetchdata() throws IOException {

	    String excelFilePath = System.getProperty("user.dir") + "\\dataFiles\\InputData2.xlsx";

	    FileInputStream inputFileSteam = new FileInputStream(excelFilePath);

	    try (XSSFWorkbook wb = new XSSFWorkbook(inputFileSteam)) {
	        XSSFSheet sheet = wb.getSheet("Sheet1");

	        int rows = sheet.getLastRowNum();  // Last row index 
	        System.out.print(rows);

	        String[] input = new String [2];

	        XSSFRow row;
	        XSSFCell cell;

	        for (int i = 1; i <= rows-1; i++) {  // Start from 1 (assuming first row is header)
	            row = sheet.getRow(i);


	            if (row != null) {
	                for (int c = 0; c < 2; c++) {

	                    cell = row.getCell(c);
	                    String data = "";

	                    if (cell != null) {  
	                        if (cell.getCellType() == CellType.STRING) {
	                            data = cell.getStringCellValue();
	                        } 
	                    }

	                    input[c] = data;  
	                }
	            }
	        }
	        return input;
	    }
	}

}
