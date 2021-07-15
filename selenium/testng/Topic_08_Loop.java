package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_08_Loop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.id("email");
	By passwordTextbox = By.id("pass");
	By loginButton = By.id("send2");
	

	@BeforeClass
	public void beforeClass() {
	
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}
	@Test(invocationCount = 3)
	public void TC_01_Register () {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");	
		driver.findElement(By.id("firstname")).sendKeys("Automation");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		
		String emailAddress = generateEmailAddress();
		System.out.println("Email address= " + emailAddress);
		driver.findElement(By.id("email_address")).sendKeys(generateEmailAddress());
		
		String password = String.valueOf(generatePassword());
		System.out.println("Password = " + password);
		
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	
	public String generateEmailAddress() {
		Random rand = new Random();
		return "automationfc" + rand.nextInt(99999) + "@fc.com";
	}
	public int generatePassword() {
		Random rand = new Random();
		return rand.nextInt(999999999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}
