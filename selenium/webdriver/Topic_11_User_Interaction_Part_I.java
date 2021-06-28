package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Hover_1() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");

	action.moveToElement(driver.findElement(By.id("age"))).perform();
	
	sleepInsecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	
	}

	//@Test
	public void TC_02_Hover_2() {
		driver.get("http://www.myntra.com/");
		sleepInsecond(3);
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Home & Living']"))).perform();
		sleepInsecond(3);
		action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home Gift Sets']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).isDisplayed());
		
	}
	
	@Test
	public void TC_03_Hover_3() {
		driver.get("https://hn.telio.vn/");
		//Hover hien thi sub-menu
		action.moveToElement(driver.findElement(By.xpath("//div[@class='row']//a[@class='menu-link']/span[contains(text(),'Đồ ăn vặt')]"))).perform();
		sleepInsecond(3);
		
		//Verify tung sub-menu
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@class='menu-link']//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a[text()='Bắp rang bơ']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@class='menu-link']//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a[text()='Đồ ăn vặt']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@class='menu-link']//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a[text()='Ô mai']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@class='menu-link']//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a[text()='Hoa quả sấy']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@class='menu-link']//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a[text()='Snack']")).isDisplayed());
		
		//Verify 5 sub-menu
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='row']//a[@class='menu-link']//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a")).size(), 5);
		
		
		//System.out.println("Elemets: " + driver.findElements(By.xpath("//div[@class='row']//a[@class='menu-link']//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a")).size());
		//action.moveToElement(driver.findElement(By.xpath("//div[@class='row']//a[@href='https://hn.telio.vn/do-an-vat/bap-rang-bo']")));
		//Assert.assertTrue(((WebElement) elements).isDisplayed());
		//	action.perform();
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@href='https://hn.telio.vn/do-an-vat/bap-rang-bo']")).isDisplayed());
		
		
		
		//action.click(driver.findElement(By.xpath("//div[@class='row']//a[@href='https://hn.telio.vn/do-an-vat/bap-rang-bo']"))).perform();
		
		
		//Verify
		//String inputValue = (String) jsExecutor.executeScript("return document.querySelector(//span[contains(text(),'Đồ ăn vặt')]/parent::a[@class='menu-link']/following-sibling::ul[@class='groupmenu-drop']//li/a");
		//System.out.println("Elemets: " + inputValue);
		//Assert.assertEquals(inputValue, "Bắp rang bơ");
		//Assert.assertEquals(driver.findElement(By.xpath("//div[@class='row']//a[@href='https://hn.telio.vn/do-an-vat/bap-rang-bo']")).getText(), "Bắp rang bơ");
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@href='https://hn.telio.vn/do-an-vat/Ô mai']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//a[@href='https://hn.telio.vn/do-an-vat/hoa-qua-say']")).isDisplayed());
		
	}

	//@Test
	public void TC_04_ClickAndHover_1() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectange = driver.findElements(By.cssSelector(".ui-selectee"));
		action.clickAndHold(rectange.get(0)) //click vào ô số 1 (chưa nhả chuột)
		.moveToElement(rectange.get(3))//di chuột đến ô số 4
		.release()//nhả chuột trái ra
		.perform(); //thực thi
		sleepInsecond(3);
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(), 4);
		
	}
	//@Test
	public void TC_05_ClickAndHover_2() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectange = driver.findElements(By.cssSelector(".ui-selectee"));
		//Nhấn phím Ctrl xuống (chưa nhả ra)
		if (osName.toLowerCase().contains("mac os")) {
			action.keyDown(Keys.COMMAND).perform();	
		} else {
			action.keyDown(Keys.CONTROL).perform();
		}
		action.click(rectange.get(0)).click(rectange.get(4)).click(rectange.get(8)).click(rectange.get(11)).perform();
		
		//Nhả phím Ctrl 
		if (osName.toLowerCase().contains("mac os")) {
				action.keyUp(Keys.COMMAND).perform();	
			} else {
				action.keyUp(Keys.CONTROL).perform();
			}
		sleepInsecond(3);
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(), 4);
		
		
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

