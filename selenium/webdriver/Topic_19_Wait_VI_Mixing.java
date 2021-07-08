package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Wait_VI_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 100);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://www.facebook.com/");
		showDataTimeNow("Start explicit: ");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
	}
	@Test
	public void TC_02_Element_Not_Found_Implicit() {
		
	}

	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
	}
	
	@Test
	public void TC_04_UploadFile() {
		
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void showDataTimeNow(String status) {
		Date date = new Date();
		System.out.println( "------------------" + status + ":" + "---------------" + date.toString());
		
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

