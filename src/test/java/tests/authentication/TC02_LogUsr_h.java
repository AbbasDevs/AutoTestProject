package tests.authentication;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.LoginPage;
import tests.TestBase;

public class TC02_LogUsr_h extends TestBase {
	LoginPage loginObject;
	HomePage homeObject;
	
	@DataProvider(name="LoginHappyData")
	public Object[][] getLgnHpDat() throws IOException {
		return GenExcelLdr.getExcelData("TC02_LogUsr_h.xlsx", 2);
	}
	
	@Test (dataProvider = "LoginHappyData")
	public void lgnUsrCrrt(String email, String password) throws InterruptedException {
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
		
		// Assert that logout button is enabled/displayed which means login is successful
		Assert.assertTrue(loginObject.logoutBtn.isEnabled());
		
		// 9. Click 'Delete Account' button
		pages.RegisterPage registerObject = new pages.RegisterPage(driver);
		registerObject.usrDelAcc();
		
		// 10. Verify that 'ACCOUNT DELETED!' is visible
		Assert.assertTrue(registerObject.delSuccMsg.getText().equalsIgnoreCase("Account Deleted!"));
		registerObject.userCanContinue();
		
		// --- DYNAMIC RECREATION LOGIC ---
		// Recreate the account immediately so subsequent tests (TC03, TC04, etc.) do not fail
		homeObject.openRegisterPage();
		registerObject.userCanRegister("mahmoud QCtester", "testermahmoud358@gmail.com");
		registerObject.usrEntAccInfo("Password123", 10, "12", "2000", 
				"mahmoud", "QCtester", "IT", "123 Tahrir Square", "Downtown", 
				"United States", "Cairo", "Cairo", "11511", "+201000000000");
		registerObject.userCanContinue();
	}
}
