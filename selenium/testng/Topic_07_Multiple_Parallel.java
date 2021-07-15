package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_07_Multiple_Parallel {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath+ "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else {
			throw new RuntimeException("The browser name is not correct");
		}
		
	
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}
	@Test
	public void TC_01_Register() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");	
		driver.findElement(By.id("firstname")).sendKeys("Automation");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		driver.findElement(By.id("email_address")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}
