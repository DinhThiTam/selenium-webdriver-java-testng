package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Wait_II_Find_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com/");
		//1 - Có duy nhất 1 element -> không cần chờ hêt timeout của impicit
		//Tương tác lên Element luôn
		driver.findElement(By.cssSelector("#email")).sendKeys("automationfc.vn@gmail.com");
		
		//2 - Ko có element nào hết
		//Cần phải chờ hết timeout của impicit
		//Trong time chờ cứ mỗi nửa s sẽ tìm lại 1 lần
		//Khi nào chờ hết timeout của implicit thì sẽ đánh fail testcase và throw exception: NoSuchElementException
		//driver.findElement(By.id("address")).sendKeys("Viet Nam");
		
		//3- Nhiều hơn 1 element (>=2)
		//Không cần chờ hết timeout của implicit
		//Nó sẽ lấy cái element đầu tiên để tương tác, ko quan tâm có bao nhiêu matching node
	}

	@Test
	public void TC_02_FindElements() {
		driver.navigate().refresh();
		//1 - Có duy nhất 1 element -> không cần chờ hêt timeout của impicit
		//Tương tác lên Element luôn
		driver.findElements(By.id("email")).get(0).sendKeys("automationfc.vn@gmail.com");
		System.out.println(driver.findElements(By.id("email")).size());
		//2 - Ko có element nào hết -> Cần test 1 element không xuất hiện trên UI và ko có trong DOM
		//Cần phải chờ hết timeout của impicit
		//Trong time chờ cứ mỗi nửa s sẽ tìm lại 1 lần
		//Không đánh fail testcase nhưng trả về 1 list empty (rỗng/không có phần tử (webelement)] nào 
		//Chuyển qua step tiếp theo
		System.out.println(driver.findElements(By.id("address")).size());
		//3 - Nhiều hơn 1 element (>=2)
		//Không cần chờ hết timeout của implicit
		//Lưu hết tất cả các element vào trong list (radio, textbox...)
		List<WebElement>  footerLinks = driver.findElements(By.cssSelector("ul.pageFooterLinkList a"));
		System.out.println(footerLinks.size());
		for (WebElement link : footerLinks) {
			System.out.println(link.getText());
		}
		}
		

	@Test
	public void TC_03_() {
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	}

