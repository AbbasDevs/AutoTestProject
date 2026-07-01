package tests.authentication;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import tests.TestBase;

public class CleanupAccount extends TestBase {
	
	@Test
	public void clnBefSuite() throws InterruptedException {
		HomePage homeObject = new HomePage(driver);
		pages.LoginPage loginObject = new pages.LoginPage(driver);
		pages.RegisterPage registerObject = new pages.RegisterPage(driver);
		
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		homeObject.openLoginPage();
		loginObject.userCanLogin("testermahmoud358@gmail.com", "Password123");
		
		if (driver.getCurrentUrl().equals("https://automationexercise.com/")) {
			registerObject.usrDelAcc();
			registerObject.userCanContinue();
		}
	}
}
