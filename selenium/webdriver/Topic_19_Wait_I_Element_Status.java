package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Wait_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Visible_Display() {
		driver.get("https://www.facebook.com/");
		
		//Wait cho element hien thi trong vong 15s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		
		//Verify cho 1 element hieern thi
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
		
	}

	@Test
	public void TC_02_InVisible_UnDisplay() {
		driver.get("https://www.facebook.com/");
		//Wait
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		//Action
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//Không có trên UI nhưng vẫn có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		//Không có trên UI và không có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		
		
	}

	@Test
	public void TC_03_Present() {
		driver.get("https://www.facebook.com/");
		//Hiển thị trên UI và có trong DOM -> pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//Form đăng ký lên
		//Ko có trên UI nhưng vẫn có trong DOM -> Pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		//Không hiển thị trên UI và không có trong DOM -> Fail
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		
	}
	@Test
	public void TC_04_Staleness() {
		//Đang ở trang home
		driver.get("https://www.facebook.com/");
		//Không hiển thị trên UI và không có trong DOM -> Fail
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//Hiện form và lưu element để sử dụng sau khi close
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		//Đang hiển thị trên UI và có trong DOM
		WebElement registerForm = driver.findElement(By.xpath("//form[@id='reg']"));
		//Không hiển trên UI và có trong DOM
		WebElement confirmEmail = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		//Click Close
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		//Tại thời điểm này mong muốn nó ko còn trong DOM nữa
		//Wait register form staleness
		explicitWait.until(ExpectedConditions.stalenessOf(registerForm));
		//Wait confirm email form staleness
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

	}

