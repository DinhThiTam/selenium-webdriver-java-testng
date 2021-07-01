package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Frame_Iframe_Window_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Iframe() {
	driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
	//switch to facebook iframe
	//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='fb:page Facebook Social Plugin']")));
	//sleepInsecond(3);
	//String likeNumber = driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText();
	//System.out.println(likeNumber);
	//switch về parent
	//driver.switchTo().defaultContent();
	
	//String postTitle = driver.findElement(By.xpath("//h1[@class='post-title']")).getText();
	//System.out.println(postTitle);
	
	//driver.switchTo().defaultContent();
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
	driver.findElement(By.xpath("//div[contains(text(),'HỌ TÊN')]/parent::div/parent::div/following-sibling::div//input")).sendKeys("Dinh Thi Tam");
	sleepInsecond(3);
	
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
		sleepInsecond(3);
		driver.findElement(By.xpath("//div[@class='container-fluid offer-space']/preceding-sibling::div[@class='container']/div[@class='pdtb25 text-center']/a[text()='Continue to NetBanking']")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.className("input_password")).sendKeys("DinhThiTam");
		driver.findElement(By.xpath("//img[@src='/gif/continue_new1.gif?v=1']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='IPIN (Password)']/parent::td/following-sibling::td//input[@class='input_password']")).isDisplayed());
		driver.switchTo().frame(driver.findElement(By.className("footer.html")));
		driver.findElement(By.xpath("//p[@class='footer']//a[contains(text(),'Terms and Conditions')]"));

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

