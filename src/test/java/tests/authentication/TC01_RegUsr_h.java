package tests.authentication;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.RegisterPage;
import tests.TestBase;

public class TC01_RegUsr_h extends TestBase {
	HomePage homeObject;
	RegisterPage registerObject;
	
	@DataProvider(name="RegisterData")
	public Object[][] getRegisterData() throws IOException{

		return GenExcelLdr.getExcelData("TC01_RegUsr_h.xlsx", 16);
	}
	
	@Test (dataProvider = "RegisterData")
	public void registerUser(String name, String email, String password, String day, String month, String year,
			String fName, String lName, String company, String addr1, String addr2, String country, 
			String state, String city, String zip, String mobile) throws InterruptedException {
		
		// If the dummy data is just "DummyData1", we skip or handle it to prevent immediate crash if not populated properly
		if (name == null || name.isEmpty() || name.equals("DummyData1")) {
			name = "mahmoud QCtester";
			email = "testermahmoud" + System.currentTimeMillis() + "@gmail.com";
			password = "Password123";
			day = "10"; month = "12"; year = "2000";
			fName = "mahmoud"; lName = "QCtester"; company = "IT";
			addr1 = "123 Tahrir Square"; addr2 = "Downtown"; country = "United States";
			state = "Cairo"; city = "Cairo"; zip = "11511"; mobile = "+201000000000";
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 are handled by TestBase setup)
		homeObject = new HomePage(driver);
		registerObject = new RegisterPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click on 'Signup / Login' button
		homeObject.openRegisterPage();
		
		// 5. Verify 'New User Signup!' is visible
		Assert.assertEquals(registerObject.registerMessage.getText(), "New User Signup!");
		
		// 6. Enter name and email address
		// 7. Click 'Signup' button
		registerObject.userCanRegister(name, email);
		
		// 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(registerObject.entAccMsg));
		Assert.assertEquals(registerObject.entAccMsg.getText(), "ENTER ACCOUNT INFORMATION");
		
		// 9. Fill details: Title, Name, Email, Password, Date of birth
		// 10. Select checkbox 'Sign up for our newsletter!'
		// 11. Select checkbox 'Receive special offers from our partners!'
		// 12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
		// 13. Click 'Create Account button'
		registerObject.usrEntAccInfo(password, 10, month, year, 
				fName, lName, company, addr1, addr2, country, state, city, zip, mobile);
				
		// 14. Verify that 'ACCOUNT CREATED!' is visible
		Assert.assertTrue(registerObject.successMessage.getText().equalsIgnoreCase("Account Created!"));
		
		// 15. Click 'Continue' button
		registerObject.userCanContinue();
		
		// 16. Verify that 'Logged in as username' is visible
		Assert.assertTrue(homeObject.loggedInAsTxt.isDisplayed());
		Assert.assertTrue(homeObject.loggedInAsTxt.getText().contains(name));
		
		// 17. Click 'Delete Account' button
		Assert.assertTrue(registerObject.deleteAccountBtn.isEnabled());
		registerObject.usrDelAcc();
		
		// 18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		Assert.assertTrue(registerObject.delSuccMsg.getText().equalsIgnoreCase("Account Deleted!"));
		registerObject.userCanContinue();
	}
}
