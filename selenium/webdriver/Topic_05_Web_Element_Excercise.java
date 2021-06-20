package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.AbstractDocument.LeafElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Excercise {
	WebDriver driver;
	String firstName, lastName, emailAdress, password, fullname;
	By emailTextbox = By.id("mail");
	By educationTextArea = By.id("edu");
	By eunder18Radio = By.id("under_18");
	By javaCheckbox = By.id("java");

	By passwordTextbox = By.id("password");
	By disableCheckbox = By.id("check-disbaled");
	By disableButton = By.id("button-disabled");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "luu";
		lastName = "khanh";
		fullname = firstName + " " + lastName;
		emailAdress = "luukhanh" + generateEmail();
		password = "123456";
	}

	@Test
	public void TC_01_Create_New_Account() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAdress);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText(),
				"Thank you for registering with Main Website Store.");
		sleepInsecond(5);
		Assert.assertTrue(driver.findElement(
				By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"
						+ fullname + "')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(
				By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"
						+ emailAdress + "')]"))
				.isDisplayed());
		// dùng hàm gettex
		String contacInformation = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println(contacInformation);
		Assert.assertTrue(contacInformation.contains(fullname));
		Assert.assertTrue(contacInformation.contains(emailAdress));
		driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}

	@Test
	public void TC_02_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.cssSelector("#email")).sendKeys(emailAdress);
		sleepInsecond(5);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		driver.findElement(By.cssSelector("#send2")).click();
		// Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My
		// Dashboard'")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(),"Hello, " + fullname + "!");
		Assert.assertTrue(driver.findElement(
				By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"
						+ fullname + "')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(
				By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"
						+ emailAdress + "')]"))
				.isDisplayed());

	}

	// @Test
	public void TC_03_Displayed_Newbie() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(By.cssSelector("#mail")).isDisplayed()) {
			driver.findElement(By.cssSelector("#mail")).sendKeys("AutomationFC");
			System.out.println("Mail textbox is displayed");
		} else {
			System.out.println("Mail textbox is not displayed");
		}
		if (driver.findElement(By.cssSelector("#under_18")).isDisplayed()) {
			driver.findElement(By.cssSelector("#under_18")).click();
			System.out.println("Age radiobutton is displayed");
		} else {
			System.out.println("Age radiobutton is not displayed");
		}
		if (driver.findElement(By.cssSelector("#edu")).isDisplayed()) {
			driver.findElement(By.cssSelector("#edu")).sendKeys("AutomationFC");
			System.out.println("Edu TextArea is displayed");
		} else {
			System.out.println("Edu TextArea is not displayed");
		}

	}

	@Test
	public void TC_03_Displayed_Function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By emailTextbox = By.id("mail");
		By educationTextArea = By.id("edu");
		By eunder18Radio = By.id("under_18");
		if (isElementDisplayed(emailTextbox)) {
			senkeyToElement(emailTextbox, "AutoamationFC");
		}
		if (isElementDisplayed(educationTextArea)) {
			senkeyToElement(educationTextArea, "AutomationFC");
		}
		if (isElementDisplayed(eunder18Radio)) {
			clickToElement(eunder18Radio);
		}

	}

	@Test
	public void TC_04_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(eunder18Radio);
		clickToElement(javaCheckbox);
		Assert.assertTrue(isElementDisplayed(eunder18Radio));
		Assert.assertTrue(isElementSelected(javaCheckbox));

		clickToElement(javaCheckbox);
		Assert.assertTrue(isElementDisplayed(eunder18Radio));
		Assert.assertFalse(isElementSelected(javaCheckbox));

	}

	@Test
	public void TC_05_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnable(emailTextbox));
		Assert.assertTrue(isElementEnable(educationTextArea));
		Assert.assertTrue(isElementEnable(eunder18Radio));
		Assert.assertTrue(isElementEnable(javaCheckbox));

		Assert.assertFalse(isElementEnable(passwordTextbox));
		Assert.assertFalse(isElementEnable(disableCheckbox));
		Assert.assertFalse(isElementEnable(disableButton));
	}

	@Test
	public void TC_06_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		By passwordTextbox = By.cssSelector("#new_password");
		By signUpButton = By.cssSelector("#create-account");
		By upperCaseCompleted = By.cssSelector(".uppercase-char.completed");
		By lowerCaseCompleted = By.cssSelector(".lowercase-char.completed");
		By numberCaseCompleted = By.cssSelector(".number-char.completed");
		By specialCaseCompleted = By.cssSelector(".special-char.completed");
		By greatThan8CharCaseCompleted = By.cssSelector("li[class='8-char completed']");

		driver.findElement(By.id("email")).sendKeys("dttam@hotmail.net");
		driver.findElement(By.id("new_username")).sendKeys("dttam");

		driver.findElement(passwordTextbox).sendKeys("AUTOMATION");
		sleepInsecond(2);
		Assert.assertTrue(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementEnable(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automation");
		sleepInsecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnable(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123456");
		sleepInsecond(2);
		Assert.assertTrue(isElementDisplayed(numberCaseCompleted));
		Assert.assertFalse(isElementEnable(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("#$%^&^");
		sleepInsecond(2);
		Assert.assertTrue(isElementDisplayed(specialCaseCompleted));
		Assert.assertFalse(isElementEnable(signUpButton));
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Automation123***");
		sleepInsecond(2);
		Assert.assertFalse(driver.findElement(lowerCaseCompleted).isDisplayed());
		Assert.assertFalse(driver.findElement(upperCaseCompleted).isDisplayed());
		Assert.assertFalse(driver.findElement(specialCaseCompleted).isDisplayed());
		Assert.assertFalse(driver.findElement(numberCaseCompleted).isDisplayed());
		Assert.assertFalse(driver.findElement(greatThan8CharCaseCompleted).isDisplayed());
		
		Assert.assertTrue(isElementEnable(signUpButton));
		driver.findElement(By.id("marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
		
		
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println(by + "is displayed");
			return true;
		} else {
			System.out.println(by + "is not displayed");
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println(by + "is Selected");
			return true;

		} else {
			System.out.println(by + "is not Selected");
			return false;
		}
	}

	public boolean isElementEnable(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println(by + "Enable");
			return true;

		} else {
			System.out.println(by + "is disable");
			return false;
		}
	}

	public void senkeyToElement(By by, String value) {
		driver.findElements(by).clear();
		driver.findElement(by).sendKeys(value);
	}

	public void clickToElement(By by) {
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
