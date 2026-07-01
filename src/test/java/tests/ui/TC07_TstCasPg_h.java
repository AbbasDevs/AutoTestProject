package tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import tests.TestBase;

public class TC07_TstCasPg_h extends TestBase {
	HomePage homeObject;
	
	@Test
	public void vrfTsCasPg() throws InterruptedException {
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click on 'Test Cases' button
		homeObject.testCasesBtn.click();
		
		// 5. Verify user is navigated to test cases page successfully
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/test_cases");
	}
}
