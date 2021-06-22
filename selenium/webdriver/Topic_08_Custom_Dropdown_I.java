package webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		
		//Ep kieu tuong minh
		jsExecutor = (JavascriptExecutor)driver;
		
		//Ep ngam dinh tu nho den lon thi ko mat du lieu
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
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

	@Test
	public void TC_02_React() {
		
	}

	@Test
	public void TC_03_VueJS() {
		
	}
	
	public void selectItemCustomDropDow(String parentXpath, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		
		//cho tat ca 
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyet qua tung item
		for (WebElement item : allItems) {
			//get text vaf ktra xem co bang item mong muon ko
			//item can chon no hien thi
			if (item.getText().equals(expectedItem)) {
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

