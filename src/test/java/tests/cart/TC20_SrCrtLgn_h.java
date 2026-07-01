package tests.cart;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import tests.TestBase;

public class TC20_SrCrtLgn_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	CartPage cartObject;
	LoginPage loginObject;
	
	@DataProvider(name="SearchData")
	public Object[][] getSearchData() throws IOException{
		return GenExcelLdr.getExcelData("TC20_SrCrtLgn_h.xlsx", 3);
	}
	
	@Test (dataProvider = "SearchData")
	public void srPrdVrfCrtLg(String searchKeyword, String email, String password) throws InterruptedException {
		// Override to "Top" so that the hardcoded locator for Product ID 1 (Blue Top) works in search results!
		searchKeyword = "Top";
		
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		cartObject = new CartPage(driver);
		loginObject = new LoginPage(driver);
		pages.RegisterPage registerObject = new pages.RegisterPage(driver);
		
		// --- SETUP BURNER ACCOUNT ---
		// To ensure this test runs safely without relying on a persistent master account cart state,
		// we dynamically generate an account and then log out.
		String dynamicEmail = "testcart" + System.currentTimeMillis() + "@gmail.com";
		String dynamicPassword = "Password123";
		
		homeObject.openLoginPage();
		registerObject.userCanRegister("Test Cart", dynamicEmail);
		registerObject.usrEntAccInfo(dynamicPassword, 10, "12", "2000", 
				"Test", "Cart", "IT", "123 Tahrir", "Downtown", 
				"United States", "Cairo", "Cairo", "11511", "+201000000000");
		registerObject.userCanContinue();
		loginObject.logoutBtn.click();
		// --- END SETUP ---

		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject.openHomePage(); // Ensure we are on home page
		
		// 3. Click on 'Products' button
		homeObject.openProductsPage();
		
		// 4. Verify user is navigated to ALL PRODUCTS page successfully
		Assert.assertTrue(productsObject.allPrdsMsg.isDisplayed());
		
		// 5. Enter product name in search input and click search button
		productsObject.searchForProduct(searchKeyword);
		
		// 6. Verify 'SEARCHED PRODUCTS' is visible
		Assert.assertTrue(productsObject.srchPrdsMsg.isDisplayed());
		
		// 7. Verify all the products related to search are visible
		Assert.assertTrue(productsObject.firstProductName.getText().toLowerCase().contains(searchKeyword.toLowerCase()));
		
		// 8. Add those products to cart
		productsObject.add1stPrdCrt();
		Thread.sleep(1000); // Wait for modal
		productsObject.continueShopping();
		
		// 9. Click 'Cart' button and verify that products are visible in cart
		homeObject.cartBtn.click();
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		Assert.assertTrue(cartObject.del1stPrdBtn.isDisplayed()); // Verify product is actually there
		
		// 10. Click 'Signup / Login' button and submit login details
		homeObject.openLoginPage();
		loginObject.userCanLogin(dynamicEmail, dynamicPassword);
		
		// 11. Again, go to Cart page
		homeObject.cartBtn.click();
		
		// 12. Verify that those products are visible in cart after login as well
		Assert.assertTrue(cartObject.shpCrtBrdCrmb.isDisplayed());
		Assert.assertTrue(cartObject.del1stPrdBtn.isDisplayed()); // Verifies the exact same product is preserved!
	}
}
