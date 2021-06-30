package webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		
		//Ep kieu tuong minh
		jsExecutor = (JavascriptExecutor)driver;
		
		//Ep ngam dinh tu nho den lon thi ko mat du lieu
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemCustomDropDow("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "5");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		
		selectItemCustomDropDow("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "15");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		
		selectItemCustomDropDow("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "3");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='3']")).isDisplayed());
	}

	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");	
		selectItemCustomDropDow("//div[@id='root']//div[@role='alert']","//div[contains(@class,visible.menu)]/div[@class='item']/span", "Christian");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemCustomDropDow("//div[@class='btn-group']/li/span[@class='caret']", "//div[@class='btn-group']//ul[@class='dropdown-menu']//a", "First Option");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		
		
	}
	
	@Test
	public void TC_04_Angula() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		sleepInsecond(3);
		//selectItemCustomDropDow("//div[@id='local']//span[@role='listbox']/span", "//li[@class='e-list-item']", "Basketball");
		//sleepInsecond(3);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInsecond(3);
		//cách làm bang javascrip
		String inputValue = (String) jsExecutor.executeScript("return document.querySelector('div[id='local']>span[role='listbox']/span').value");
		Assert.assertEquals(inputValue, "Basketball");
	 
		//Assert.assertEquals(driver.findElement(By.xpath("//select[@id='games_hidden']/option[text() = 'Basketball']")), "Basketball");
		
				//driver.findElement(By.xpath("//div[@id='local']//span[@role='listbox']/span']")).sendKeys("Basketball");
			//	driver.findElement(By.xpath("//div[@id='local']//span[@role='listbox']/span")).sendKeys(Keys.TAB);
				
				//Verify
		
				
	}
	//@Test
	public void TC_05_VerifyToElementDisable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sleepInsecond(3);
		driver.findElement(By.cssSelector("#default-place input")).sendKeys("Audi");
		sleepInsecond(3);
		driver.findElement(By.cssSelector("#default-place input")).sendKeys(Keys.TAB);
		
		//Verify
		String inputValue = (String) jsExecutor.executeScript("return document.querySelector('#default-place input').value");
		Assert.assertEquals(inputValue, "Audi");
		
		driver.findElement(By.cssSelector("#default-place input")).clear();
		driver.findElement(By.cssSelector("#default-place input")).sendKeys("BMW");
		sleepInsecond(3);
		driver.findElement(By.cssSelector("#default-place input")).sendKeys(Keys.TAB);
		//Verify
		inputValue = (String) jsExecutor.executeScript("return document.querySelector('#default-place input').value");
		Assert.assertEquals(inputValue, "BMW");
	}
	
	public void selectItemCustomDropDow(String parentXpath, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		
		//cho tat ca 
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyet qua tung item
		for (WebElement item : allItems) {
			//get text vaf ktra xem co bang item mong muon ko
			//item can chon no hien thi
			if (item.getText().trim().equals(expectedItem)) {
				if(item.isDisplayed()) {
					item.click();
				}else {
					//scoll den item do > click item
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					sleepInsecond(1);
					item.click();
					break;
				}
			}
			
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

