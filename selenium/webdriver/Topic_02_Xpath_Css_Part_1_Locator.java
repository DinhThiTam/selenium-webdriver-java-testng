package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_1_Locator {
	WebDriver driver;

	@BeforeClass
	
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
	driver.findElement(By.id("FirstName")).sendKeys("Tam");
	sleepInsecond(3);
	driver.findElement(By.id("gender-male")).click();
	sleepInsecond(3);
	}

	@Test
	public void TC_02_Classname() {
		driver.navigate().refresh();
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
		sleepInsecond(3);
		driver.findElement(By.className("search-box-button")).click();
		sleepInsecond(3);
	}

	@Test
	public void TC_03_Name() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		sleepInsecond(3);
		driver.findElement(By.name("Email")).sendKeys("dttam.cntt@gmail.com");
		sleepInsecond(3);
		driver.findElement(By.name("Newsletter")).click();
		sleepInsecond(3);
		
	}
	@Test
	public void TC_04_Tagname() {
		System.out.println("Sum link: " + driver.findElements(By.tagName("a")).size());
		
	}
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Login")).click();
		sleepInsecond(3);
	}
	@Test
	public void TC_06_PartialLinkText() {
		driver.findElement(By.partialLinkText("Recently viewed products")).click();
		sleepInsecond(3);
		
		driver.findElement(By.partialLinkText("viewed products")).click();
		sleepInsecond(3);
		
		driver.findElement(By.partialLinkText("Recently viewed")).click();
		sleepInsecond(3);
		
	}
	
	@Test
	public void TC_07_CSS() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");	
		
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Name");
		sleepInsecond(3);
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("MAcbook");
		sleepInsecond(3);
		driver.findElement(By.cssSelector("Email")).sendKeys("tam@gmail.com");
		sleepInsecond(3);
		driver.findElement(By.cssSelector("a[href*='login']")).click();
		sleepInsecond(3);
	}	
	
	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");	
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		sleepInsecond(3);
		driver.findElement(By.xpath("//input[contains(@class,'search-box-text')]")).sendKeys("Macbook");
		sleepInsecond(3);
		driver.findElement(By.xpath("//input[@name-'Email'")).sendKeys("AutoFC@gmail.com");
		sleepInsecond(3);
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
