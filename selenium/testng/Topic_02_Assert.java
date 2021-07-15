package testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class Topic_02_Assert {

		@Test
		public void TC_01() {
			boolean condition = true;
		//TRue
		//Cách 1: import từ tên class > tên hàm (Class name > Method name)
			condition = 3<5;
			System.out.println(condition);
			Assert.assertTrue(condition);
		//Cách 2: static (phải import thư viện import static org.testng.Assert.assertTrue
			assertTrue(condition);
			
		//False
			condition = 3>5;
			System.out.println(condition);
			Assert.assertFalse(condition);
		//Equal
			Assert.assertEquals("Automation", "Auto");
		}

	}


