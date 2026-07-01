package tests.cart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import tests.TestBase;

public class TC22_AddCrtRec_h extends TestBase {
	HomePage homeObject;
	CartPage cartObject;
	
	@Test
	public void addCrtRecItms() throws InterruptedException {
		homeObject = new HomePage(driver);
		cartObject = new CartPage(driver);
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		
		// 3. Scroll to bottom of page
		homeObject.scrollToBottom();
		
		// 4. Verify 'RECOMMENDED ITEMS' are visible
		Assert.assertTrue(homeObject.recItmsHdr.isDisplayed());
		
		// 5. Click on 'Add To Cart' on Recommended product
		homeObject.clickWithJS(homeObject.fRecAddCrtBtn);
		Thread.sleep(1500); // wait for modal
		
		// 6. Click on 'View Cart' button
		homeObject.viewCartModalBtn.click();
		
		// 7. Verify that product is displayed in cart page
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		Assert.assertTrue(cartObject.del1stPrdBtn.isDisplayed()); // Verify an item exists in the cart
	}
}
