
package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Wait_VII_Fluent {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	FluentWait<WebElement> fluentElement;
	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		
	}

	//@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countdownTimer = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
		fluentElement = new FluentWait<WebElement>(countdownTimer);
		fluentElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement countdown) {
				boolean status = countdown.getText().endsWith("00");
				System.out.println("Text = " + countdown.getText() + "----------" + status );
				return status;				
			}				
		});
	}
	//@Test
	public void TC_02_() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		clickToElement(By.xpath("//div[@id='start']/button"));
		Assert.assertTrue(isElementDisplay(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		Assert.assertEquals(getElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}
	@Test
	public void TC_02_Icon_Loading_Success() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='total']//span")).getText(), "3 month(s)");
		
		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']")).click();
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("Peter Mac");
	
		driver.findElement(By.id("searchBtn")).click();
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		Assert.assertEquals(driver.findElement(By.xpath("//table[@id='resultTable']//tr/td[3]//a")).getText(), "Peter Mac");
		
		
	}
	//Hàm này dùng để getText
	public WebElement getElement (By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
				
			}
		});
		return element;
	}
	public void clickToElement (By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
				
			}
		});
		element.click();
	}
	public boolean isElementDisplay (By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(NoSuchElementException.class);
		Boolean status = wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver element) {
				return driver.findElement(locator).isDisplayed();
				
			}
		});
		return status;
	}
	public boolean isJQueryLoadedSuccess (WebDriver driver) {
		explicitWait = new WebDriverWait(driver, timeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply (WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery !=null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	long timeout = 15;
	long polling = 1;
	public void showDataTimeNow(String status) {
		Date date = new Date();
		System.out.println( "------------------" + status + "---------------" + date.toString());
		
	}
	
}
