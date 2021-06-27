package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	//@Test
	public void TC_01_Accept_Alert_1() {
		driver.get("http://demo.guru99.com/v4/index.php");
		driver.findElement(By.name("btnLogin")).click();
		//wait cho alert xuat hien + switch
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInsecond(5);
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		alert.accept();
		
		
	}
	//@Test
	public void TC_01_Confirm_Alert_2() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}
	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

	// @Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String fullName = "AutoFC";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(fullName);
		alert.accept();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + fullName);
	}
	//@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");
		//get link 
		String hrefValue = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		passValueToUrl(hrefValue, "admin", "admin");
	}
	
	@Test
	public void TC_06_Authentication_Alert() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void passValueToUrl(String url, String username, String password) {
		String[] hrefValue = url.split("//");
		
		url = hrefValue[0] + "//" + username + ":" + password + "@" + hrefValue[1];
		
		driver.get(url);
		
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

