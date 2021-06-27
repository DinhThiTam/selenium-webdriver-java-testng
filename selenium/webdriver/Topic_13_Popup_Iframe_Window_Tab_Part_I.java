package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Popup_Iframe_Window_Tab_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_Zingpoll() {
	driver.get("https://www.zingpoll.com/");
	
	By signInPopup = By.cssSelector(".modal_dialog_custom");
	driver.findElement(By.id("Loginform")).click();
	Assert.assertTrue(driver.findElement(signInPopup).isDisplayed());
	driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
	Assert.assertFalse(driver.findElement(signInPopup).isDisplayed());
	
	}

	//@Test
	public void TC_02_Shopee() {
		driver.get("https://shopee.vn/");
		By homePopup = By.cssSelector("img[alt='home_popup_banner']");
		Assert.assertTrue(isElementDisplay(homePopup));
		driver.findElement(By.cssSelector("svg[class='shopee-svg-icon ']>path")).click();
		sleepInsecond(3);
		Assert.assertFalse(isElementDisplay(homePopup));
		
	}

	@Test
	public void TC_03_Testproject_InDom() {
		driver.get("https://blog.testproject.io/");
		if (isElementDisplay(By.cssSelector("#mailch-bg .mailch-wrap"))) {
			driver.findElement(By.cssSelector("#close-mailch>svg")).click();
			sleepInsecond(3);
		}
		driver.findElement(By.cssSelector(".widget_search .search-field ")).sendKeys("Selenium");;
		driver.findElement(By.cssSelector("section .glass")).click();
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isElementDisplayed (By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			return false;
		}
		
	}
	public boolean isElementDisplay (By by) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> elements = driver.findElements(by);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (elements.size()==0) {
			return false;
		} else if (elements.size() >0 && !elements.get(0).isDisplayed())
		{
			return false;
	}else {
		return true;
	}
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
