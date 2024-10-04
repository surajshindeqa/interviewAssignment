package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class ProductDetails extends BaseClass {
	
	WebDriver driver;
	
	public ProductDetails(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()=\"Add to cart\"]")
	WebElement button_AddToCart;
	
	@FindBy(xpath="//span[@class=\"VU-ZEz\"]")
	WebElement productName;
	
	@FindBy(xpath="//div[@class=\"Nx9bqj CxhGGd\"]")
	WebElement price;
	
	public void clickAddToCartButton()	{
		button_AddToCart.click();
		
	}
	
	public String getProductName()	{
		return productName.getText();
		
	}
	
	public String getProductPrice()	{
		return price.getText();
		
	}
	

}
