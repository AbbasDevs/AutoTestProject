package tests.products;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.PrdDetPage;
import pages.ProductsPage;
import tests.TestBase;

public class TC08_VrfPrds_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	PrdDetPage prdDetObj;
	
	@Test
	public void vrfAllPrdsDet() throws InterruptedException {
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		prdDetObj = new PrdDetPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click on 'Products' button
		homeObject.openProductsPage();
		
		// 5. Verify user is navigated to ALL PRODUCTS page successfully
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products");
		Assert.assertTrue(productsObject.allPrdsMsg.isDisplayed());
		
		// 6. The products list is visible
		Assert.assertTrue(productsObject.firstProductName.isDisplayed());
		
		// 7. Click on 'View Product' of first product
		productsObject.opn1stPrdDets();
		
		// 8. User is landed to product detail page
		Assert.assertTrue(driver.getCurrentUrl().contains("product_details"));
		
		// 9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(prdDetObj.prdAvail));
		
		Assert.assertTrue(prdDetObj.productName.isDisplayed());
		Assert.assertTrue(prdDetObj.productCategory.isDisplayed());
		Assert.assertTrue(prdDetObj.productPrice.isDisplayed());
		Assert.assertTrue(prdDetObj.prdAvail.isDisplayed());
		Assert.assertTrue(prdDetObj.productCondition.isDisplayed());
		Assert.assertTrue(prdDetObj.productBrand.isDisplayed());
	}
}
