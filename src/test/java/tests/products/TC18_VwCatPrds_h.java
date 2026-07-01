package tests.products;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductsPage;
import tests.TestBase;

public class TC18_VwCatPrds_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	
	@Test
	public void vwCatPrds() throws InterruptedException {
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// Verify that categories are visible on left side bar
		Assert.assertTrue(productsObject.categoryHeader.isDisplayed());
		
		// 4. Click on 'Women' category
		productsObject.womenCategory.click();
		
		// 5. Click on any category link under 'Women' category, for example: Dress
		productsObject.wmDrsCat.click();
		
		// 6. Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS' (Using DRESS PRODUCTS as we clicked Dress)
		Assert.assertEquals(productsObject.centerTitle.getText(), "WOMEN - DRESS PRODUCTS");
		
		// 7. On left side bar, click on any sub-category link of 'Men' category
		productsObject.menCategory.click();
		productsObject.menTshrtCat.click();
		
		// 8. Verify that user is navigated to that category page
		Assert.assertEquals(productsObject.centerTitle.getText(), "MEN - TSHIRTS PRODUCTS");
	}
}
