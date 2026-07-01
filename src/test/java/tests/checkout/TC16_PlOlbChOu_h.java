package tests.checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import tests.TestBase;

import java.io.IOException;

import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TC16_PlOlbChOu_h extends TestBase {
	HomePage homeObject;
	LoginPage loginObject;
	CartPage cartObject;
	CheckoutPage checkoutObject;
	RegisterPage registerObject;
	pages.ProductsPage productsObject;

	@org.testng.annotations.DataProvider(name="ChkLoginData")
	public Object[][] getChLgnDat() throws IOException {
		return data.GenExcelLdr.getExcelData("TC16_PlOlbChOu_h.xlsx", 2);
	}

	@Test
	public void plOrdLgnBfCO() throws InterruptedException {
		homeObject = new HomePage(driver);
		loginObject = new LoginPage(driver);
		cartObject = new CartPage(driver);
		checkoutObject = new CheckoutPage(driver);
		registerObject = new RegisterPage(driver);
		productsObject = new pages.ProductsPage(driver);
		
		// --- SETUP BURNER ACCOUNT ---
		// To ensure this test runs reliably without depending on a pre-existing master account 
		// (and to avoid deleting the master account at the end), we dynamically generate an account and then log out.
		String dynamicEmail = "testlogin" + System.currentTimeMillis() + "@gmail.com";
		String dynamicPassword = "Password123";
		
		homeObject.openLoginPage();
		registerObject.userCanRegister("Test Login", dynamicEmail);
		registerObject.usrEntAccInfo(dynamicPassword, 10, "12", "2000", 
				"Test", "Login", "IT", "123 Tahrir", "Downtown", 
				"United States", "Cairo", "Cairo", "11511", "+201000000000");
		registerObject.userCanContinue();
		loginObject.logoutBtn.click();
		// --- END SETUP ---

		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject.openHomePage(); // Ensure we are on home page
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");
		
		// 4. Click 'Signup / Login' button
		homeObject.openLoginPage();
		
		// 5. Fill email, password and click 'Login' button
		loginObject.userCanLogin(dynamicEmail, dynamicPassword);
		
		// 6. Verify 'Logged in as username' at top
		Assert.assertTrue(homeObject.loggedInAsTxt.isDisplayed());
		
		// 7. Add products to cart
		homeObject.openProductsPage();
		productsObject.add1stPrdCrt();
		Thread.sleep(1000); // Wait for modal
		productsObject.continueShopping();
		
		// 8. Click 'Cart' button
		homeObject.cartBtn.click();
		
		// 9. Verify that cart page is displayed
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		
		// 10. Click Proceed To Checkout
		cartObject.prcToChOutBtn.click();
		
		// 11. Verify Address Details and Review Your Order
		Assert.assertTrue(checkoutObject.addrDetHdr.isDisplayed());
		Assert.assertTrue(checkoutObject.revOrdHdr.isDisplayed());
		
		// 12. Enter description in comment text area and click 'Place Order'
		checkoutObject.commentTxt.sendKeys("Test order login before checkout");
		checkoutObject.placeOrderBtn.click();
		
		// 13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		// 14. Click 'Pay and Confirm Order' button
		checkoutObject.entPayDets("Test User", "123456789", "123", "12", "2025");
		
		// 15. Verify success message 'Your order has been placed successfully!'
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(checkoutObject.successMessage));
		Assert.assertTrue(checkoutObject.successMessage.isDisplayed());
		
		// 16. Click 'Delete Account' button
		registerObject.usrDelAcc();
		
		// 17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		Assert.assertTrue(registerObject.delSuccMsg.getText().equalsIgnoreCase("Account Deleted!"));
		registerObject.userCanContinue();
	}
}
