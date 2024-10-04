package pageObjects;

import java.io.Console;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class home extends BaseClass {
	
	WebDriver driver;

	public home(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Home-search page webelements
	
	@FindBy(xpath="//input[@title=\"Search for Products, Brands and More\"]")
	WebElement searchbox; 
	
	@FindBy(xpath="//button[@type=\"submit\"]")
	WebElement searchButton; 
	
	
	public void enterSearchText(String searchtext) {
		searchbox.sendKeys(searchtext);
	}
	
	public void clickSearchButton()	{
		searchButton.click();
		
	}
	
	public void clickSortOption(String sort) throws InterruptedException	{
		
		String newxpath="//div[text()='" +sort + "']";

		driver.findElement(By.xpath(newxpath)).click();	
		
		Thread.sleep(5000);
	}
	
	public void scrollDown() throws InterruptedException	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(5000);
	}
	
	public boolean checkPriceOrder(String pageno) throws InterruptedException {
		
		int pagenm= Integer.parseInt(pageno);
		int p=1;
		
		boolean results=true;
		
		for(int i=0;i<pagenm;i++)
		{
			if(i!=0) {
						Thread.sleep(5000);
						String pagenumber ="(//a[@class=\"cn++Ap\"])["+String.valueOf(i)+"]";
						
						WebElement java = driver.findElement(By.xpath(pagenumber));
						
						JavascriptExecutor js = (JavascriptExecutor)driver;
				        js.executeScript("arguments[0].click();", java);
				        Thread.sleep(5000);
				        js.executeScript("window.scrollBy(0,1000)");
				        p=1;
			}
			
		
			while(p < 40) {
				
			String priceproductone= "(//div[@class=\"Nx9bqj\"])["+String.valueOf(p) +"]";
			String priceproducttwo= "(//div[@class=\"Nx9bqj\"])["+String.valueOf(p+1) +"]";
			
				if(Integer.parseInt(driver.findElement(By.xpath(priceproductone)).getText().substring(1))<=Integer.parseInt(driver.findElement(By.xpath(priceproducttwo)).getText().substring(1)) ) {
					
				}
				else {
					
					System.out.println(p);
					
					System.out.println(driver.findElement(By.xpath(priceproductone)).getText());
					System.out.println(driver.findElement(By.xpath(priceproducttwo)).getText());
					
					results=false;
					
			        break;
					
				}
				p++;
			}
			
			
		}
	
		return results;
		
	}
	
	
	
	public void viewProduct(String productNum) {
		
		String product= "(//div[@class=\"wvIX4U\"])["+productNum +"]";
		
		driver.findElement(By.xpath(product)).click();
		
	}
	

}
