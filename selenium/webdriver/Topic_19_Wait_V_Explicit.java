package webdriver;

import java.util.Date;

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

public class Topic_19_Wait_V_Explicit {
	WebDriver driver;
	WebDriverWait explicit;
	String projectPath = System.getProperty("user.dir");
	String firstFileName = "01.jpg";
	String firstFilePath = projectPath + "\\UploadFile\\" + firstFileName;
	
	String secondFileName = "02.jpg";
	String secondFilePath = projectPath + "\\UploadFile\\" + secondFileName;
	
	By starButton = By.cssSelector("#start>button");
	By loadingIcon = By.cssSelector("div#loading");
	By helloworldText = By.xpath("//h4[text()='Hello World!']");
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		explicit = new WebDriverWait(driver, 100);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(starButton).click();
		//Chờ cho hello worl được hiển thị (visible)
		explicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}
	//@Test
	public void TC_02_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(starButton).click();
		//chờ cho loading biến mất (invisible)
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}

	//@Test
	public void TC_03_Ajax() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		//Click vào today
		explicit.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='20']")));
		driver.findElement(By.xpath("//a[text()='20']")).click();
		
		//Wait cho ajax loading biến mất
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]//div[@class='raDiv']")));
		
		//Verify ngày đã được chọn
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='20']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='20']")).isDisplayed());
		
		//Verify today đc update lên "Selected Dates"
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText().trim(), "Tuesday, July 20, 2021");
	}
	
	@Test
	public void TC_04_UploadFile() {
		driver.get("https://gofile.io/uploadFiles");
		//sleepInsecond(10);
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@class='animation__wobble']")));
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-sm-auto']/button")));
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstFilePath + "\n" + secondFilePath);
		//Wait cho choose server invisible
		System.out.println("Start server icon: " + getDataTimeNow());
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowUploadProgress-selectServer")));
		System.out.println("End server icon: " + getDataTimeNow());
		
		//Wait cho file loaded seccess
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'text-truncate') and text()='"+firstFileName+"']")));
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'text-truncate') and text()='"+secondFileName+"']")));
		System.out.println("End server icon: " + getDataTimeNow());
		//Wait progess bar icon invisible
		explicit.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@role='progessbar']"))));
		
		//Wait cho success message display
		explicit.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//h5[text()='Your files have been successfully uploaded']"))));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		//Wait cho button show file clickable
		explicit.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='rowUploadSuccess-showFiles']")));
		driver.findElement(By.xpath("//button[@id='rowUploadSuccess-showFiles']")).click();
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" +firstFileName+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" +secondFileName+ "']")).isDisplayed());
		
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String getDataTimeNow() {
		Date date = new Date();
		return date.toString();
		
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

