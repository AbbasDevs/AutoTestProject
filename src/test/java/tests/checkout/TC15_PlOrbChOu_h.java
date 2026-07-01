package tests.checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.RegisterPage;
import tests.TestBase;

public class TC15_PlOrbChOu_h extends TestBase {
	HomePage homeObject;
	CartPage cartObject;
	CheckoutPage checkoutObject;
	RegisterPage registerObject;
	pages.ProductsPage productsObject;

	@Test
	public void plOrdRegBfCO() throws InterruptedException {
		homeObject = new HomePage(driver);
		cartObject = new CartPage(driver);
		checkoutObject = new CheckoutPage(driver);
		registerObject = new RegisterPage(driver);
		productsObject = new pages.ProductsPage(driver);
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");
		
		// 4. Click 'Signup / Login' button
		homeObject.openLoginPage();
		
		// 5. Fill all details in Signup and create account
		registerObject.userCanRegister("Test Before Checkout", "testbefore" + System.currentTimeMillis() + "@gmail.com");
		registerObject.usrEntAccInfo("pass", 1, "1", "2000", "Test", "User", "IT", "Add1", "Add2", "India", "State", "City", "123", "123456");
		
		// 6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		org.openqa.selenium.support.ui.WebDriverWait waitAcc = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		waitAcc.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(registerObject.successMessage));
		Assert.assertTrue(registerObject.successMessage.getText().equalsIgnoreCase("Account Created!"));
		registerObject.userCanContinue();
		
		// 7. Verify ' Logged in as username' at top
		Assert.assertTrue(homeObject.loggedInAsTxt.isDisplayed());
		
		// 8. Add products to cart
		homeObject.openProductsPage();
		productsObject.add1stPrdCrt();
		Thread.sleep(1000); // Wait for modal
		productsObject.continueShopping();
		
		// 9. Click 'Cart' button
		homeObject.cartBtn.click();
		
		// 10. Verify that cart page is displayed
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		
		// 11. Click Proceed To Checkout
		cartObject.prcToChOutBtn.click();
		
		// 12. Verify Address Details and Review Your Order
		Assert.assertTrue(checkoutObject.addrDetHdr.isDisplayed());
		Assert.assertTrue(checkoutObject.revOrdHdr.isDisplayed());
		
		// 13. Enter description in comment text area and click 'Place Order'
		checkoutObject.commentTxt.sendKeys("Test order before checkout");
		checkoutObject.placeOrderBtn.click();
		
		// 14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		// 15. Click 'Pay and Confirm Order' button
		checkoutObject.entPayDets("Test User", "123456789", "123", "12", "2025");
		
		// 16. Verify success message 'Your order has been placed successfully!'
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(checkoutObject.successMessage));
		Assert.assertTrue(checkoutObject.successMessage.isDisplayed());
		
		// 17. Click 'Delete Account' button
		registerObject.usrDelAcc();
		
		// 18. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		Assert.assertTrue(registerObject.delSuccMsg.getText().equalsIgnoreCase("Account Deleted!"));
		registerObject.userCanContinue();
	}
}
