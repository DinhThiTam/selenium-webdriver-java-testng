package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Alway_Run {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
  @BeforeClass
  public void beforeClass() {
	  //Mở trình duyệt
	  //Init driver lên
	  //Nếu như trong before class mà fail thì các testcase bên dưới/after class ko bao giờ chạy. Alway run giúp luôn run after class để đóng trình duyệt
	  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://automationfc.com");
		Assert.assertTrue(false);
  }
  @Test
  public void TC_01() {
	  System.out.println("Run testcase 01");
	  
  }
  @Test
  public void TC_02() {
	  System.out.println("Run testcase 02");  
	  
  }
  @AfterClass (alwaysRun = true)
  public void afterClass() {
	 //Close driver và browser
	  driver.quit();
  }

}
