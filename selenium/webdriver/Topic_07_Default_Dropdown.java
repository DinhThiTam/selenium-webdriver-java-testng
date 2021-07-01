package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	JavascriptExecutor jsExecutor;
	By myAccount = By.className("ico-account");
	By logOut = By.className("ico-logout");
	By firtNameTextbox = By.id("FirstName");
	By lastNameTextbox = By.id("LastName");
	By emailTextbox = By.id("Email");
	By passwordTextbox = By.id("Password");
	
	// Khai bao bien form dang ky
	String firtName, lastName, emailAddress, password, day, month, year;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();;
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		// Init register
		firtName = "Kate";
		lastName = "Muler";
		emailAddress = "katemuler" + generateEmail() ;
		password = "123456";
		day = "10";
		month = "August";
		year = "1990";
	}

	@Test
	public void TC_01_Register() {
		clickToElement(By.cssSelector(".ico-register"));
		clickToElement(By.cssSelector("#gender-male"));
		driver.findElement(firtNameTextbox).sendKeys(firtName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		clickByJS(By.id("register-button"));
		sleepInsecond(3 );
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
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
	
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void clickByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();",driver.findElement(by));
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
