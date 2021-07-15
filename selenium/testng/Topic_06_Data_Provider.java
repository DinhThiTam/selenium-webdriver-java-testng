package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_06_Data_Provider {
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
	@Test(dataProvider = "user_pass")
	public void TC_01_Register (String username, String password) throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");	
		driver.findElement(By.id("firstname")).sendKeys("Automation");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		driver.findElement(By.id("email_address")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	
	@Test(dataProvider = "user_pass")
	public void TC_02_Login (String username, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");	
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");		
	}
	@DataProvider (name = "user_pass")
	public Object[][] UserAndPassworData() {
		return new Object[][] {
			{"automationfc_01@gmail.com", "111111"},
			{"automationfc_02@gmail.com", "111111"},
			{"automationfc_03@gmail.com", "111111"}};
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}
