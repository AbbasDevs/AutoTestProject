package tests.checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import tests.TestBase;

public class TC23_VrfAddChO_h extends TestBase {
	HomePage homeObject;
	LoginPage loginObject;
	RegisterPage registerObject;
	ProductsPage productsObject;
	CartPage cartObject;
	CheckoutPage checkoutObject;
	
	@Test
	public void vrfAddChO() throws InterruptedException {
		homeObject = new HomePage(driver);
		loginObject = new LoginPage(driver);
		registerObject = new RegisterPage(driver);
		productsObject = new ProductsPage(driver);
		cartObject = new CartPage(driver);
		checkoutObject = new CheckoutPage(driver);
		
		String name = "Test CheckoutUser";
		String email = "testcheckout" + System.currentTimeMillis() + "@gmail.com";
		String password = "Password123";
		String fName = "Mahmoud";
		String lName = "Abbas";
		String company = "Company";
		String address = "123 Test Street";
		String address2 = "Apt 4";
		String state = "Cairo";
		String city = "Cairo";
		String zipcode = "11511";
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject.openHomePage();
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");
		
		// 4. Click 'Signup / Login' button
		homeObject.openLoginPage();
		
		// 5. Fill all details in Signup and create account
		registerObject.userCanRegister(name, email);
		registerObject.usrEntAccInfo(password, 10, "12", "2000", 
				fName, lName, company, address, address2, 
				"United States", state, city, zipcode, "+201000000000");
		
		// 6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		Assert.assertTrue(registerObject.successMessage.getText().equalsIgnoreCase("Account Created!"));
		registerObject.userCanContinue();
		
		// 7. Verify ' Logged in as username' at top
		Assert.assertTrue(homeObject.loggedInAsTxt.getText().contains(name));
		
		// 8. Add products to cart
		homeObject.openProductsPage();
		productsObject.add1stPrdCrt();
		Thread.sleep(1000); // wait for modal
		productsObject.continueShopping();
		
		// 9. Click 'Cart' button
		homeObject.cartBtn.click();
		
		// 10. Verify that cart page is displayed
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		
		// 11. Click Proceed To Checkout
		cartObject.prcToChOutBtn.click();
		
		// 12. Verify that the delivery address is same address filled at the time registration of account
		String delAddrTxt = checkoutObject.delAddrCnt.getText();
		Assert.assertTrue(delAddrTxt.contains(fName + " " + lName));
		Assert.assertTrue(delAddrTxt.contains(company));
		Assert.assertTrue(delAddrTxt.contains(address));
		Assert.assertTrue(delAddrTxt.contains(address2));
		Assert.assertTrue(delAddrTxt.contains(city + " " + state + " " + zipcode));
		
		// 13. Verify that the billing address is same address filled at the time registration of account
		String invAddrTxt = checkoutObject.invAddrCnt.getText();
		Assert.assertTrue(invAddrTxt.contains(fName + " " + lName));
		Assert.assertTrue(invAddrTxt.contains(company));
		Assert.assertTrue(invAddrTxt.contains(address));
		Assert.assertTrue(invAddrTxt.contains(address2));
		Assert.assertTrue(invAddrTxt.contains(city + " " + state + " " + zipcode));
		
		// 14. Click 'Delete Account' button
		registerObject.usrDelAcc();
		
		// 15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		Assert.assertTrue(registerObject.delSuccMsg.getText().equalsIgnoreCase("Account Deleted!"));
		registerObject.userCanContinue();
	}
}
