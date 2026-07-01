package tests.products;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.ProductsPage;
import tests.TestBase;

public class TC09_SrchPrd_h extends TestBase {
	HomePage homeObject;
	ProductsPage productsObject;
	
	@DataProvider(name="SearchData")
	public Object[][] getSearchData() throws IOException{
		return GenExcelLdr.getExcelData("TC09_SrchPrd_h.xlsx", 1);
	}
	
	@Test (dataProvider = "SearchData")
	public void searchProduct(String searchKeyword) throws InterruptedException {
		if (searchKeyword == null || searchKeyword.isEmpty() || searchKeyword.equals("DummyData1")) {
			searchKeyword = "Dress";
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		productsObject = new ProductsPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click on 'Products' button
		homeObject.openProductsPage();
		
		// 5. Verify user is navigated to ALL PRODUCTS page successfully
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products");
		Assert.assertTrue(productsObject.allPrdsMsg.isDisplayed());
		
		// 6. Enter product name in search input and click search button
		productsObject.searchForProduct(searchKeyword);
		
		// 7. Verify 'SEARCHED PRODUCTS' is visible
		Assert.assertTrue(productsObject.srchPrdsMsg.isDisplayed());
		
		// 8. Verify all the products related to search are visible
		Assert.assertTrue(productsObject.firstProductName.getText().toLowerCase().contains(searchKeyword.toLowerCase()) || 
				productsObject.firstProductName.isDisplayed());
	}
}
