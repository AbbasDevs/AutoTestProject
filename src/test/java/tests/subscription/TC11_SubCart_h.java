package tests.subscription;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.CartPage;
import pages.HomePage;
import tests.TestBase;

public class TC11_SubCart_h extends TestBase {
	HomePage homeObject;
	CartPage cartObject;
	
	@DataProvider(name="SubscriptionData")
	public Object[][] getSubDat() throws IOException{
		return GenExcelLdr.getExcelData("TC11_SubCart_h.xlsx", 1);
	}
	
	@Test (dataProvider = "SubscriptionData")
	public void vrfSubCrtPg(String email) throws InterruptedException {
		if (email == null || email.isEmpty() || email.equals("DummyData1")) {
			email = "test@test.com";
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		cartObject = new CartPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click 'Cart' button
		homeObject.cartBtn.click();
		
		// 5. Scroll down to footer
		// (Scrolling is handled automatically inside the subscribe method)
		
		// 6. Verify text 'SUBSCRIPTION'
		Assert.assertTrue(homeObject.subHdr.isDisplayed());
		
		// 7. Enter email address in input and click arrow button
		homeObject.subscribe(email);
		
		// 8. Verify success message 'You have been successfully subscribed!' is visible
		Assert.assertEquals(homeObject.subSuccMsg.getText(), "You have been successfully subscribed!");
	}
}
