package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_UploadFile_Senkey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String googleFileName = "Google.jpg";
	String googleFilePath = projectPath + "\\UploadFile\\" + googleFileName;
	
	String facebookFileName = "Facebook.jpg";
	String facebookFilePath = projectPath + "\\UploadFile\\" + facebookFileName;
	
	String AmazonFileName = "Amazon.png";
	String AmazonFilePath = projectPath + "\\UploadFile\\" + AmazonFileName;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		//System.setProperty("webdriver.edge.driver", projectPath+ "/browserDrivers/msedgedriver.exe");
		//driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_UploadLoad_Single() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +googleFileName+ "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("table .start")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" +googleFileName+ "']")).isDisplayed());
	
		
		
		
		
	}

	@Test
	public void TC_02_UploadLoada_Multile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath + "\n" + AmazonFilePath + "\n" + facebookFilePath);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +googleFileName+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +AmazonFileName+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +facebookFileName+ "']")).isDisplayed());
		
		List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table .start"));
		for (WebElement startButton : startUploadButtons) {
			startButton.click();
			sleepInsecond(1);
			
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" +googleFileName+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" +AmazonFileName+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" +facebookFileName+ "']")).isDisplayed());
	}

	@Test
	public void TC_03_() {
		
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

