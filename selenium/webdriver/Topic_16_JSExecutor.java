package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.xml.internal.txw2.Document;

public class Topic_16_JSExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_liveGuru() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		System.out.println("doman: " + liveGuruDomain);
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		String liveGuruURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruURL, "http://live.demoguru99.com/");
		
		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div//button[@title='Add to Cart']");
		
		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		
		scrollToElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		
		clickToElementByJS("//button[@title='Subscribe']");
		
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		Assert.assertEquals((String) executeForBrowser("return document.domain"), "demo.guru99.com");	
	}

	//@Test
	public void TC_02_Validate() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInsecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='fname']", "Automation FC");
		sleepInsecond(3);
		
	
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='pass']", "123456");
		
	
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='em']", "123");
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInsecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please include an '@' in the email address. '123' is missing an '@'.");
		sendkeyToElementByJS("//input[@id='em']", "123@1234");

		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");
		sendkeyToElementByJS("//input[@id='em']", "123@1#df");
		
	}

	//@Test
	public void TC_03_New_Custom() {
		driver.get("http://demo.guru99.com/v4/");
		String name, dob, city, address, state, pin, phone;
		//Data Edit member
		String editCity, editState, editPhone;
		//UI
		String emailAdress, loginPageUrl, userID, password;
		By nameTextboxBy = By.name("name");
		By dobTextboxBy = By.name("dob");
		By addressTextareaBy = By.name("addr");
		By cityTextboxBy = By.name("city");
		By stateTextboxBy = By.name("state");
		By pinTextboxBy = By.name("pinno");
		By phoneTextboxBy = By.name("telephoneno");
		By emailTextboxBy = By.name("emailid");
		By passwordTextboxBy = By.name("password");
		emailAdress = "dttam" + generateEmail();
		name = "John Lips";
		dob = "0102-12-09";
		city = "California";
		address = "123 PO Box";
		state = "Hawai";
		pin = "867945";
		phone = "0987654321";
		
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAdress);
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		
		removeAttributeInDOM("//input[@name='dob']", "type");
		driver.findElement(addressTextareaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(emailAdress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement((By.name("sub"))).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		//Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		System.out.println("dob: " + dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAdress );
	}
	
	@Test
	public void TC_04_Register() {
		driver.get("http://live.demoguru99.com/");
		String emailAdress = "dttam" + generateEmail();
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		clickToElementByJS("//a[@title='Create an Account']");
		sendkeyToElementByJS("//input[@id='firstname']", "AutomationFC");
		sendkeyToElementByJS("//input[@id='lastname']", "FC");
		sleepInsecond(3);
		sendkeyToElementByJS("//input[@id='email_address']", emailAdress);
		sendkeyToElementByJS("//input[@id='password']", "123456");
		sendkeyToElementByJS("//input[@id='confirmation']", "123456");
		clickToElementByJS("//span[text()='Register']");
		sleepInsecond(3);
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));
		clickToElementByJS("//a[text()='Log Out']");
		navigateToUrlByJS("http://live.demoguru99.com/index.php/customer/account/logoutSuccess/");
		sleepInsecond(3);
		Assert.assertEquals((String) executeForBrowser("return document.title"), "Magento Commerce");
	
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.net";
	}
	//domail, url
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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

