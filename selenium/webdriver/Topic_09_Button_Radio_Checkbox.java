package webdriver;

import java.awt.Checkbox;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	boolean status;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		
		Assert.assertFalse(status);
		System.out.println("Button status = " + status);
		
		driver.findElement(By.id("login_username")).sendKeys("0983970888");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertTrue(status);
		
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		//remove attribute javascript selenium
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInsecond(3);
		
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertTrue(status);
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		//System.out.println("Text: " + driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		driver.navigate().refresh();
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInsecond(3);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInsecond(3);
		
	}
	

	//@Test
	public void TC_02_Radio_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		//click vào checkbox de chon
		checkToCheckboxorRadioButton(rearSideCheckbox);
		sleepInsecond(3);		
		//Verify checkbox is selected
		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
		
		//Click để bỏ chọn
		uncheckToCheckbox(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"));
		sleepInsecond(3);		
		//Verify 
		Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
		
		//Radio
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		By oneDotFourRadio = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		checkToCheckboxorRadioButton(By.xpath("//input[@id='engine1']"));
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(oneDotFourRadio).isSelected());
	
	}

	//@Test
	public void TC_03___Checkbox_SelectAll() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//select toàn bộ checkboxes
		List<WebElement> checkboxes = driver.findElements(By.cssSelector(".form-checkbox"));
		for (WebElement checkbox : checkboxes) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				sleepInsecond(3);
			}
			
		}
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
			
		}
	}
	//@Test
	public void TC_04__Radio_Checkbox_Custom() {
		By winterRadio = By.xpath("//input[@value='Winter']");
		driver.get("https://material.angular.io/components/radio/examples");
		clickToElementByJS(winterRadio);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());
	}
	@Test
	public void TC_05__Radio_Checkbox_Custom() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']/div[contains(@class,'exportInnerBox')]")).isDisplayed());
		driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']/div[contains(@class,'exportInnerBox')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']/div[contains(@class,'exportInnerBox')]")).isDisplayed());
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void clickToElementByJS (By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
		
		
	}
	public void checkToCheckboxorRadioButton(By by) {
		WebElement checkbox = driver.findElement(by);
				if (!checkbox.isSelected()) {
					checkbox.click();
				}
	}
	
	public void uncheckToCheckbox(By by) {
		WebElement checkbox = driver.findElement(by);
				if (checkbox.isSelected()) {
					checkbox.click();
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

