package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Method {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
	
		driver = new FirefoxDriver();
		
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Web_Element() {
	
	}

	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInsecond(long timeoutInsecond){
		try {
			Thread.sleep(timeoutInsecond *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}

