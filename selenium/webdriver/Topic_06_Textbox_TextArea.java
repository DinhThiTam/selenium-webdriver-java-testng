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

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	//Data New member
	String name, dob, city, address, state, pin, phone;
	//Data Edit member
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	//UI
	String emailAdress, loginPageUrl, userID, password,idCustomer;
	By nameTextboxBy = By.name("name");
	By genderTextboxBy = By.name("gender");
	By dobTextboxBy = By.name("dob");
	By addressTextareaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");
	By idCustomerTextboxBy = By.xpath("//td[text()='Customer ID']/following-sibling::td");

	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		
		//Init bieens(gan)
		emailAdress = "dttam" + generateEmail();
		name = "John Lips";
		dob = "1990-01-02";
		city = "California";
		address = "123 PO Box";
		state = "Hawai";
		pin = "867945";
		phone = "0987654321";
		
		//Init edit
		editAddress = "678 Parues";
		editCity = "London";
		editState = "Seat";
		editPin = "456432";
		editPhone = "0986756443";
		editEmail = "dttam";
	
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
	driver.findElement(By.xpath("//a[text()='here']")).click();
	driver.findElement(By.name("emailid")).sendKeys(emailAdress);
	driver.findElement(By.name("btnLogin")).click();
	
	userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	
	}

	@Test
	public void TC_02_Login() {
		//driver.navigate().back();
		//driver.navigate().back();
		//driver.get(loginPageUrl);
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		
		
	}

	@Test
	public void TC_03_Create_New_User() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(dobTextboxBy).sendKeys(dob);
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
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAdress );
		
		idCustomer= driver.findElement(idCustomerTextboxBy).getText();
		
	}
	@Test
	public void TC_04_Edit_User() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(idCustomer);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextboxBy).isEnabled());
		
		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobTextboxBy).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(addressTextareaBy).getText(), address);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), emailAdress);
		
		driver.findElement(addressTextareaBy).clear();
		driver.findElement(addressTextareaBy).sendKeys(editAddress);
		
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys(editPhone);
		
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editAddress);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editAddress );
		
		
		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.net";
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

