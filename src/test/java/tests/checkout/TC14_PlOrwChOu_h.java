package tests.checkout;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import tests.TestBase;

public class TC14_PlOrwChOu_h extends TestBase {
	HomePage homeObject;
	CartPage cartObject;
	CheckoutPage checkoutObject;
	RegisterPage registerObject;
	
	@DataProvider(name="CheckoutData")
	public Object[][] getCheckoutData() throws IOException{
		return GenExcelLdr.getExcelData("TC14_PlOrwChOu_h.xlsx", 5);
	}
	
	@Test (dataProvider = "CheckoutData")
	public void plOrwChOut(String name, String card, String cvc, String month, String year) throws InterruptedException {
		if (name == null || name.isEmpty() || name.equals("DummyData1")) {
			name = "Test User"; card = "123456789"; cvc = "123"; month = "12"; year = "2025";
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		cartObject = new CartPage(driver);
		checkoutObject = new CheckoutPage(driver);
		registerObject = new RegisterPage(driver);
		pages.ProductsPage productsObject = new pages.ProductsPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");
		
		// 4. Add products to cart
		homeObject.openProductsPage();
		productsObject.add1stPrdCrt();
		Thread.sleep(1000); // Wait for modal
		productsObject.continueShopping();
		
		// 5. Click 'Cart' button
		homeObject.cartBtn.click();
		
		// 6. Verify that cart page is displayed
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		
		// 7. Click Proceed To Checkout
		cartObject.prcToChOutBtn.click();
		
		// 8. Click 'Register / Login' button
		cartObject.regLgnModBtn.click();
		
		// 9. Fill all details in Signup and create account
		registerObject.userCanRegister("Test Checkout", "testcheckout" + System.currentTimeMillis() + "@gmail.com");
		registerObject.usrEntAccInfo("pass", 1, "1", "2000", "Test", "User", "IT", "Add1", "Add2", "India", "State", "City", "123", "123456");
		
		// 10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		Assert.assertTrue(registerObject.successMessage.getText().equalsIgnoreCase("Account Created!"));
		registerObject.userCanContinue();
		
		// 11. Verify ' Logged in as username' at top
		Assert.assertTrue(homeObject.loggedInAsTxt.isDisplayed());
		
		// 12. Click 'Cart' button
		homeObject.cartBtn.click();
		
		// 13. Click 'Proceed To Checkout' button
		cartObject.prcToChOutBtn.click();
		
		// 14. Verify Address Details and Review Your Order
		Assert.assertTrue(checkoutObject.addrDetHdr.isDisplayed());
		Assert.assertTrue(checkoutObject.revOrdHdr.isDisplayed());
		
		// 15. Enter description in comment text area and click 'Place Order'
		checkoutObject.commentTxt.sendKeys("Test order");
		checkoutObject.placeOrderBtn.click();
		
		// 16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		// 17. Click 'Pay and Confirm Order' button
		checkoutObject.entPayDets(name, card, cvc, month, year);
		
		// 18. Verify success message 'Your order has been placed successfully!'
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(checkoutObject.successMessage));
		Assert.assertTrue(checkoutObject.successMessage.isDisplayed());
		
		// 19. Click 'Delete Account' button
		registerObject.usrDelAcc();
		
		// 20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		Assert.assertTrue(registerObject.delSuccMsg.getText().equalsIgnoreCase("Account Deleted!"));
		registerObject.userCanContinue();
	}
}
