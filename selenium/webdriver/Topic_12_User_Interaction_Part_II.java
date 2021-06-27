package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_User_Interaction_Part_II {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String jsDragDropPath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";
	String jqueryDragDropPath = projectPath + "/dragAndDrop/jquery_load_helper.js";
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Scroll đến element này trước
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInsecond(3);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
	}

	//
	@Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInsecond(3);
		//trước khi hover mouse
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-paste:not(.context-menu-hover):not(.context-menu-visible)")).isDisplayed());
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		//Click to page menu
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
		driver.switchTo().alert().accept();
		
		
	}

	//@Test
	public void TC_03_DragAnd_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCicrle = driver.findElement(By.cssSelector(".demo-section #draggable"));
		WebElement largeCicrle = driver.findElement(By.cssSelector(".demo-section #droptarget"));
		action.dragAndDrop(smallCicrle, largeCicrle).perform();
		sleepInsecond(3);
		
		Assert.assertEquals(largeCicrle.getText(), "You did great!");
		
		//Css
		//largeCicrle.getCssValue(arg0)
		
	}
	@Test
	public void TC_04_Drag_Drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String columnA = "#column-a";
		String columnB = "#column-b";
		String jsValue = readFile(jsDragDropPath);
		jsValue = jsValue + "$(\"" + columnA + "\").simulateDragDrop({ dropTarget: \"" + columnB + "\"});";
		jsExecutor.executeScript(jsValue);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		
		jsExecutor.executeScript(jsValue);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		jsExecutor.executeScript(jsValue);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		
	}
	
	@Test
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
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

