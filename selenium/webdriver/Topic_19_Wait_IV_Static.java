package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Wait_IV_Static {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By starButton = By.cssSelector("#start>button");
	By loadingIcon = By.cssSelector("div#loading");
	By helloworldText = By.xpath("//h4[text()='Hello World!']");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		
	}

	@Test
	public void TC_01_Less() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(starButton).click();
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}
	@Test
	public void TC_02_Enough() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(starButton).click();
		sleepInsecond(6);
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}

	@Test
	public void TC_03_Less() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//String projectPath = System.getProperty("user.dir");driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(starButton).click();
		sleepInsecond(10);
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
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

