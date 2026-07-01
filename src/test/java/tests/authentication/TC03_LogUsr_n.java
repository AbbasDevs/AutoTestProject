package tests.authentication;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.LoginPage;
import tests.TestBase;

public class TC03_LogUsr_n extends TestBase {
	LoginPage loginObject;
	HomePage homeObject;
	
	@DataProvider(name="LogNegData")
	public Object[][] getLgnNgDat() throws IOException {
		return GenExcelLdr.getExcelData("TC03_LogUsr_n.xlsx", 2);
	}
	
	@Test (dataProvider = "LogNegData")
	public void lgnUsrIncrrt(String email, String password) throws InterruptedException {
		if (email == null || email.isEmpty() || email.equals("DummyData1")) {
			email = "invalid_user@gmail.com";
			password = "wrongpassword";
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 are handled by TestBase setup)
		loginObject = new LoginPage(driver);
		homeObject = new HomePage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");
				
		// 4. Click on 'Signup / Login' button
		homeObject.openLoginPage();
		
		// 5. Verify 'Login to your account' is visible
		Assert.assertEquals(loginObject.loginMessage.getText(), "Login to your account");

		// 6. Enter incorrect email address and password
		// 7. Click 'login' button
		loginObject.userCanLogin(email, password);
		
		// 8. Verify error 'Your email or password is incorrect!' is visible
		Assert.assertEquals(loginObject.errorMessage.getText(), "Your email or password is incorrect!");
	}
}
