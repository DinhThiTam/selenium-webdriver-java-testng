package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Upload_AutoIT_Robor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String googleFileName = "Google.jpg";
	String googleFilePath = projectPath + "\\UploadFile\\" + googleFileName;
	
	String facebookFileName = "Facebook.jpg";
	String facebookFilePath = projectPath + "\\UploadFile\\" + facebookFileName;
	
	String AmazonFileName = "Amazon.png";
	String AmazonFilePath = projectPath + "\\UploadFile\\" + AmazonFileName;
	
	String firefoxOneTimeAutoIT = projectPath+ "\\autoIT\\firefoxUploadOneTime.exe";
	String firefoxMultipleTimeAutoIT = projectPath+ "\\autoIT\\firefoxUploadMultiple.exe";
	
	String chromeOneTimeAutoIT = projectPath+ "\\autoIT\\firefoxUploadOneTime.exe";
	String chromeMultipleTimeAutoIT = projectPath+ "\\autoIT\\firefoxUploadMultiple.exe";
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		//driver = new ChromeDriver();
		System.setProperty("webdriver.edge.driver", projectPath+ "/browserDrivers/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_AutoIT_Single() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Bat Open File Dialog => click button
		driver.findElement(By.cssSelector(".btn-success")).click();
		Runtime.getRuntime().exec(new String[] {firefoxOneTimeAutoIT,googleFilePath});
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +googleFileName+ "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("table .start")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" +googleFileName+ "']")).isDisplayed());	
		
	}

	//@Test
	public void TC_02_AutoIT_Multile() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();
		//Runtime.getRuntime().exec(new String[] {firefoxMultipleTimeAutoIT,googleFilePath, facebookFilePath, AmazonFilePath});
		if (driver.toString().contains("chrome")|| driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] {chromeMultipleTimeAutoIT,googleFilePath, facebookFilePath, AmazonFilePath});	
		} else if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {firefoxMultipleTimeAutoIT,googleFilePath, facebookFilePath, AmazonFilePath});
		}
		
		
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
	public void TC_03_Java_Robot() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Bat Open File Dialog => click button
		driver.findElement(By.cssSelector(".btn-success")).click();
		//Runtime.getRuntime().exec(new String[] {firefoxOneTimeAutoIT,googleFilePath});
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +googleFileName+ "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("table .start")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" +googleFileName+ "']")).isDisplayed());	
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void uploadFileByRobot (String filePath) {
		
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

