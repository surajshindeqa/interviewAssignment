package base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
public static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		//launch chrome in incognito
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		
		driver.get("https://www.flipkart.com/");
		
		//maximize window
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.close();
		
	}
	
	
}


