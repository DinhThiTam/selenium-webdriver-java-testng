package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sun.nio.ch.SelChImpl;

public class Topic_14_Frame_Iframe {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String likeNumber;
	
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

	//@Test
	public void TC_02_Frame() {
		driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
		sleepInsecond(3);
		driver.findElement(By.xpath("//div[@class='container-fluid offer-space']/preceding-sibling::div[@class='container']/div[@class='pdtb25 text-center']/a[text()='Continue to NetBanking']")).click();
		//(element)[1] day là bọc element lấy theo vị trí index
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.className("input_password")).sendKeys("DinhThiTam");
		driver.findElement(By.xpath("//img[@src='/gif/continue_new1.gif?v=1']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='IPIN (Password)']/parent::td/following-sibling::td//input[@class='input_password']")).isDisplayed());
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("footer")));
		driver.findElement(By.xpath("//p[@class='footer']//a[contains(text(),'Terms and Conditions')]")).click();

	}

	@Test
	public void TC_03_Iframe_Kyna() {
		driver.get("https://kyna.vn/");
		sleepInsecond(3);
		//driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']/iframe")).isDisplayed());
		//likeNumber = driver.findElement(By.xpath("//div[text()='169K lượt thích']")).getText();
		//Assert.assertEquals(likeNumber, "169K lượt thích");
		
		//driver.switchTo().defaultContent();	
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		sleepInsecond(3);
		driver.findElement(By.xpath("//div[@class='button_text']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[@class='container']//input[@placeholder='Nhập tên của bạn']")).sendKeys("Đinh THị Tâm");
		driver.findElement(By.xpath("//div[@class='container']//input[@placeholder='Nhập số điện thoại của bạn']")).sendKeys("0983970447");
		select = new Select(driver.findElement(By.cssSelector("#serviceSelect")));
		select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		driver.findElement(By.xpath("//label[@class='label']/following-sibling::textarea[@name='message']")).sendKeys("Xin chao, toi muon gap ky thuat");
		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Đinh THị Tâm']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='0983970447']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//div[@class='input-group']//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@aria-label='search']//img[@alt='Khóa học trực tuyến']")).click();
		
		List<WebElement> allCourses = driver.findElements(By.cssSelector(".content>h4"));
		for (WebElement course : allCourses) {
			Assert.assertTrue(course.getText().contains("Excel"));
			System.out.println("Khoa hoc: " + course);
		}
		
		
		
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

