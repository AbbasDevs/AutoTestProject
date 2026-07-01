package tests.cart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.PrdDetPage;
import pages.ProductsPage;
import tests.TestBase;

public class TC13_VrfQtyCrt_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	PrdDetPage prdDetObj;
	CartPage cartObject;
	
	@Test
	public void vrfPrdQtyCrt() throws InterruptedException {
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		prdDetObj = new PrdDetPage(driver);
		cartObject = new CartPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click 'View Product' for any product on home page
		// Products list is also on home page, but we mapped it in ProductsPage.
		productsObject.opn1stPrdDets();
		
		// 5. Verify product detail is opened
		Assert.assertTrue(driver.getCurrentUrl().contains("product_details"));
		
		// 6. Increase quantity to 4
		prdDetObj.quantityTxt.clear();
		prdDetObj.quantityTxt.sendKeys("4");
		
		// 7. Click 'Add to cart' button
		prdDetObj.addToCartBtn.click();
		
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		
		// 8. Click 'View Cart' button
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(prdDetObj.viewCartModalBtn));
		prdDetObj.viewCartModalBtn.click();
		
		// 9. Verify that product is displayed in cart page with exact quantity
		Assert.assertTrue(cartObject.fPrdInCrt.isDisplayed());
		Assert.assertEquals(cartObject.fPrdQty.getText(), "4");
	}
}
