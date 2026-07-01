package tests.subscription;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import tests.TestBase;

public class TC10_SubHome_h extends TestBase {
	HomePage homeObject;
	
	@DataProvider(name="SubscriptionData")
	public Object[][] getSubDat() throws IOException{
		return GenExcelLdr.getExcelData("TC10_SubHome_h.xlsx", 1);
	}
	
	@Test (dataProvider = "SubscriptionData")
	public void vrfSubHomePg(String email) throws InterruptedException {
		if (email == null || email.isEmpty() || email.equals("DummyData1")) {
			email = "test@test.com";
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Scroll down to footer
		// (Scrolling handled inside subscribe method)
		
		// 5. Verify text 'SUBSCRIPTION'
		Assert.assertTrue(homeObject.subHdr.isDisplayed());
		
		// 6. Enter email address in input and click arrow button
		homeObject.subscribe(email);
		
		// 7. Verify success message 'You have been successfully subscribed!' is visible
		Assert.assertEquals(homeObject.subSuccMsg.getText(), "You have been successfully subscribed!");
	}
}
