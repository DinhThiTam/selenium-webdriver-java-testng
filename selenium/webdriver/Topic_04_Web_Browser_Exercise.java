package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Exercise {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Verify_Url() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@class='button']")).click();
		sleepInsecond(5);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_Title() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//a[@class='button']")).click();
		sleepInsecond(10);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}

	@Test
	public void TC_03_Navigation() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(5);
		driver.findElement(By.xpath("//a[@class='button']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		
	}
	@Test
	public void TC_04_Navigation_PageSouceCode() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String currentPageSouce = driver.getPageSource();
		sleepInsecond(10);
		Assert.assertTrue(currentPageSouce.contains("Login or Create an Account"));
		sleepInsecond(10);
		driver.findElement(By.xpath("//a[@class='button']")).click();
		sleepInsecond(10);
		currentPageSouce = driver.getPageSource();
		Assert.assertTrue(currentPageSouce.contains("Create an Account"));
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

