package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Get ra window/tab id tại tab đang được active
		String parentTabID= driver.getWindowHandle();
		System.out.println("Parent ID: " + parentTabID);
		//click to GG link => sang tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		//Switch qua google tab
		switchToWindowByID(parentTabID);
		sleepInsecond(3);
		String googleTabID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		
		//switch về parent tab
		switchToWindowByID(googleTabID);
		
		//click to FB link => sang tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		//swit về parent
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInsecond(3);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).isDisplayed());
	}
	

	@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		String parentID= driver.getWindowHandle();
		//click link FB tại footer
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		sleepInsecond(3);
		
		//switch qua FB
		switchToWindowByID("parentID");
		sleepInsecond(3);
		
		//switch qua parent
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='VietnamWorks']")).click();
		
		//switch qua vietnamwork
		switchToWindowByTitle("Tuyển dụng, việc làm, tìm việc làm nhanh mới nhất  | VietnamWorks");
		driver.findElement(By.xpath("//input[@id='search-bar-input']")).sendKeys("Automation Test");
		sleepInsecond(3);
		//switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		//driver.findElement(By.xpath("//div[@class='info']//a[text()='PRIMUS']")).click();
		//switchToWindowByTitle("PRIMUS Homepage");
		
		
		
	}

	@Test
	public void TC_03_() {
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void switchToWindowByID(String windowID) {
		//Get hết ra các id đang có
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Duyệt qua các giá trị trong all windows
		for (String id : allWindowIDs) {
			//Kiểm tra đk nếu như kahcs vơi window id truyền vào thì switch
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				//thoát khỏi vòng lặp
				break;	
			}
		}
	}
	public void switchToWindowByTitle (String pageTitle) {
		//Get hết ra các id đang có
		Set<String> allWindowIDs = driver.getWindowHandles();
		//Duyệt qua các giá trị trong all windows, switch qua window rồi getTitle của window ra, nếu title thực tế bằng với title của window vừa switch thì done, thoát khỏi vòng lặp
		for (String id : allWindowIDs) {	
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(pageTitle)) {
			break;
			}
		}
	}
	public void closeAlltabWithoutParent (String parent) {
		
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

