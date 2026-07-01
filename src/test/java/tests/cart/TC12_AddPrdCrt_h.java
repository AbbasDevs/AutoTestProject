package tests.cart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;
import tests.TestBase;

public class TC12_AddPrdCrt_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	CartPage cartObject;
	
	@Test
	public void addPrdsCrt() throws InterruptedException {
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		cartObject = new CartPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click 'Products' button
		homeObject.openProductsPage();
		
		// 5. Hover over first product and click 'Add to cart'
		// (Hovering overlay is bypassed naturally using JavaScript click strategy defined in ProductsPage)
		productsObject.add1stPrdCrt();
		
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		
		// 6. Click 'Continue Shopping' button
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(productsObject.cntShpModBtn));
		productsObject.continueShopping();
		
		// 7. Hover over second product and click 'Add to cart'
		productsObject.add2ndPrdCrt();
		
		// 8. Click 'View Cart' button
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(productsObject.viewCartModalBtn));
		productsObject.vwCrtMod();
		
		// 9. Verify both products are added to Cart
		Assert.assertTrue(cartObject.fPrdInCrt.isDisplayed());
		Assert.assertTrue(cartObject.sPrdInCrt.isDisplayed());
		
		// 10. Verify their prices, quantity and total price
		Assert.assertEquals(cartObject.fPrdPrice.getText(), "Rs. 500");
		Assert.assertEquals(cartObject.fPrdQty.getText(), "1");
		Assert.assertEquals(cartObject.fPrdTotPrc.getText(), "Rs. 500");
		
		Assert.assertEquals(cartObject.sPrdPrice.getText(), "Rs. 400");
		Assert.assertEquals(cartObject.sPrdQty.getText(), "1");
		Assert.assertEquals(cartObject.sPrdTotPrc.getText(), "Rs. 400");
	}
}
