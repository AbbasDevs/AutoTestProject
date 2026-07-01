package tests.authentication;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.LoginPage;
import tests.TestBase;

public class TC04_LgOutUsr_h extends TestBase {
	LoginPage loginObject;
	HomePage homeObject;
	
	@DataProvider(name="LogoutHappyData")
	public Object[][] getLgtHpDat() throws IOException {
		return GenExcelLdr.getExcelData("TC04_LgOutUsr_h.xlsx", 2);
	}
	
	@Test (dataProvider = "LogoutHappyData")
	public void logoutUser(String email, String password) throws InterruptedException {
		if (email == null || email.isEmpty() || email.equals("DummyData1")) {
			email = "testermahmoud358@gmail.com";
			password = "Password123";
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		loginObject = new LoginPage(driver);
		homeObject = new HomePage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");
				
		// 4. Click on 'Signup / Login' button
		homeObject.openLoginPage();
		
		// 5. Verify 'Login to your account' is visible
		Assert.assertEquals(loginObject.loginMessage.getText(), "Login to your account");

		// 6. Enter correct email address and password
		// 7. Click 'login' button
		loginObject.userCanLogin(email, password);
		
		// 8. Verify that 'Logged in as username' is visible
		Assert.assertTrue(homeObject.loggedInAsTxt.isDisplayed());
		Assert.assertTrue(loginObject.logoutBtn.isEnabled());
		
		// 9. Click 'Logout' button
		loginObject.userCanlogout();
		
		// 10. Verify that user is navigated to login page
		Assert.assertEquals(loginObject.loginMessage.getText(), "Login to your account");
	}
}
