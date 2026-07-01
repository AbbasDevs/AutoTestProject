package tests.products;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.PrdDetPage;
import pages.ProductsPage;
import tests.TestBase;

public class TC21_AddRev_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	PrdDetPage prdDetObj;
	
	@DataProvider(name="ReviewData")
	public Object[][] getReviewData() throws IOException{
		return GenExcelLdr.getExcelData("TC21_AddRev_h.xlsx", 3);
	}
	
	@Test (dataProvider = "ReviewData")
	public void addRevOnPrd(String name, String email, String review) throws InterruptedException {
		if (name == null || name.isEmpty() || name.equals("DummyData1")) {
			name = "Test User";
			email = "testuser@gmail.com";
			review = "This is a great product!";
		}
		
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		prdDetObj = new PrdDetPage(driver);
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		
		// 3. Click on 'Products' button
		homeObject.openProductsPage();
		
		// 4. Verify user is navigated to ALL PRODUCTS page successfully
		Assert.assertTrue(productsObject.allPrdsMsg.isDisplayed());
		
		// 5. Click on 'View Product' button
		productsObject.opn1stPrdDets();
		
		// 6. Verify 'Write Your Review' is visible
		Assert.assertTrue(prdDetObj.wrRevHdr.isDisplayed());
		
		// 7. Enter name, email and review
		// 8. Click 'Submit' button
		prdDetObj.addReview(name, email, review);
		
		// 9. Verify success message 'Thank you for your review.'
		Assert.assertTrue(prdDetObj.revSuccMsg.isDisplayed());
	}
}
