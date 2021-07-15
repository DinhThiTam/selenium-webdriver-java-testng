package testng;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_09_Dependency {
	 @Test (priority = 1)
	  public void TC_01_Add_New_Customer() {
		 // Assert.assertTrue(false);
		  
	  }
	  @Test (dependsOnMethods = "TC_01_Add_New_Customer")
	  public void TC_02_View_Customer() {
		  System.out.println("Run testcase 02");  
		  
	  }
	  @Test (dependsOnMethods = "TC_02_View_Customer")
	  public void TC_03_Edit_Customer() {
		  System.out.println("Run testcase 02");  
		  
	  }
	  @Test (dependsOnMethods = "TC_03_Edit_Customer")
	  public void TC_04_Delete_Customer() {
		  System.out.println("Run testcase 02");  
		  
	  }
	
}
