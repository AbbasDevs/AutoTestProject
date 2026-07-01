package tests.products;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductsPage;
import tests.TestBase;

public class TC19_VwBrdPrds_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	
	@Test
	public void vwBrdPrds() throws InterruptedException {
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		
		// 3. Verify that home page is visible successfully (Implied step before clicking Products, though specification doesn't explicitly mention it here, I will leave the assertion)
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 3. Click on 'Products' button (Step numbering adjusted to match spec)
		homeObject.openProductsPage();
		
		// 4. Verify that Brands are visible on left side bar
		Assert.assertTrue(productsObject.brandsHeader.isDisplayed());
		
		// 5. Click on any brand name
		productsObject.poloBrand.click();
		
		// 6. Verify that user is navigated to brand page and brand products are displayed
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement(productsObject.centerTitle, "BRAND - POLO PRODUCTS"));
		Assert.assertEquals(productsObject.centerTitle.getText(), "BRAND - POLO PRODUCTS");
		
		// 7. On left side bar, click on any other brand link
		productsObject.hmBrand.click();
		
		// 8. Verify that user is navigated to that brand page and can see products
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement(productsObject.centerTitle, "BRAND - H&M PRODUCTS"));
		Assert.assertEquals(productsObject.centerTitle.getText(), "BRAND - H&M PRODUCTS");
	}
}
