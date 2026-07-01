package tests.cart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import tests.TestBase;

public class TC17_RmPrdsCrt_h extends TestBase {
	HomePage homeObject;
	CartPage cartObject;
	pages.ProductsPage productsObject;
	
	@Test
	public void rmvPrdsCrt() throws InterruptedException {
		homeObject = new HomePage(driver);
		cartObject = new CartPage(driver);
		productsObject = new pages.ProductsPage(driver);
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Add products to cart
		homeObject.openProductsPage();
		productsObject.add1stPrdCrt();
		Thread.sleep(1000); // Wait for modal to transition
		productsObject.continueShopping();
		
		// 5. Click 'Cart' button
		homeObject.cartBtn.click();
		
		// 6. Verify that cart page is displayed
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		
		// 7. Click 'X' button corresponding to particular product
		cartObject.del1stPrdBtn.click();
		
		// 8. Verify that product is removed from the cart
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(cartObject.emptyCartMessage));
		Assert.assertTrue(cartObject.emptyCartMessage.isDisplayed());
	}
}
