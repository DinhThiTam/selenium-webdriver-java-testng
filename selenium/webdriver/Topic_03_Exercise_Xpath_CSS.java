package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Exercise_Xpath_CSS {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
		
	}

	@Test
	public void TC_01_EmailPassEmpty() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(3);
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}

	@Test
	public void TC_02_InvalidEmail() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(3);
		driver.findElement(By.id("email")).sendKeys("123426677@124567755");
		sleepInsecond(3);
		driver.findElement(By.id("pass")).sendKeys("123456");
		sleepInsecond(3);
		driver.findElement(By.id("send2")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_InvalidPass() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		sleepInsecond(3);driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_InvalidEmailPass() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(3);
		driver.findElement(By.className("validate-email")).sendKeys("automation@gmail.com");
		sleepInsecond(3);
		driver.findElement(By.className("validate-password")).sendKeys("12345622");
		sleepInsecond(3);
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
	}
	@Test
	public void TC_05_CreatAcc() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.cssSelector("div.account-cart-wrapper > a > span:last-child")).click();
		sleepInsecond(3);
		driver.findElement(By.linkText("My Account")).click();
		sleepInsecond(3);
	
		
	}
	@Test
	public void TC_06_LoginValid() {
		
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

