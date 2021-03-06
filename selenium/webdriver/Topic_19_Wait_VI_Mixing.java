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

	//@Test
	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://www.facebook.com/");
		showDataTimeNow("Start explicit: ");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		showDataTimeNow("End explicit: ");
		showDataTimeNow("Start implicit: ");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dttam.cntt@gmail.com");
		showDataTimeNow("End implicit: ");
	}
	//@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Chờ hết timeout của implicit
		//cứ nửa s tìm lại 1 lần
		//Hết timeout thì đánh fail và throw exception : No such element
		driver.get("https://www.facebook.com/");
		showDataTimeNow("Start implicit: ");
		try {
			driver.findElement(By.xpath("//input[@id='abc']")).sendKeys("dttam.cntt@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDataTimeNow("End implicit: ");
	}

	//@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		showDataTimeNow("Start explicit: ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='abc']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDataTimeNow("End explicit: ");
		showDataTimeNow("Start implicit: ");
		try {
			driver.findElement(By.xpath("//input[@id='abc']")).sendKeys("dttam.cntt@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDataTimeNow("End implicit: ");
	}
	
	@Test
	public void TC_04_Element_Not_Found_Explicit_Param_By() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		showDataTimeNow("Start explicit: ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='abc']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	public void TC_05_Element_Not_Found_Explicit_Param_WebElement() {
		
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		showDataTimeNow("Start explicit: ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='abc']"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void showDataTimeNow(String status) {
		Date date = new Date();
		System.out.println( "------------------" + status + "---------------" + date.toString());
		
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

