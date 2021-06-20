package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea_Exercise {
	WebDriver driver;
	By myAccount = By.className("ico-account");
	By logOut = By.className("ico-logout");
	By firtNameTextbox = By.id("FirstName");
	By lastNameTextbox = By.id("LastName");
	By emailTextbox = By.id("Email");
	By passwordTextbox = By.id("Password");
	
	// Khai bao bien form dang ky
	String firtName, lastName, emailAddress, password;
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		// Init register
		firtName = "Kate";
		lastName = "Muler";
		emailAddress = "katemuler" + generateEmail() ;
		password = "123456";
	}

	@Test
	public void TC_01_Register() {
		clickToElement(By.cssSelector(".ico-register"));
		clickToElement(By.cssSelector("#gender-male"));
		driver.findElement(firtNameTextbox).sendKeys(firtName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		clickToElement(By.id("register-button"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(),"Your registration completed" );
		
		clickToElement(By.cssSelector(".ico-logout"));
		
	}

	@Test
	public void TC_02_Login() {
		clickToElement(By.className("ico-login"));
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);
		clickToElement(By.cssSelector(".button-1.login-button"));
		
		Assert.assertTrue(isElementDisplayed(myAccount));
		Assert.assertTrue(isElementDisplayed(logOut));
	}

	@Test
	public void TC_03_Verify_Info() {
		clickToElement(myAccount);
		Assert.assertEquals(driver.findElement(firtNameTextbox).getAttribute("value"), firtName);
		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), emailAddress);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isElementDisplayed (By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println(by + "is displayed");
			return true;
		}
		else{
		return false;
		}
	}
	
	public void clickToElement (By by) {
		driver.findElement(by).click();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.vn";
	}

	public void sleepInsecond(long timeoutInsecond) {
		try {
			Thread.sleep(timeoutInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
