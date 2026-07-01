package tests.authentication;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.RegisterPage;
import tests.TestBase;

public class TC05_RegUsr_n extends TestBase {
	HomePage homeObject;
	RegisterPage registerObject;
	
	@DataProvider(name="RegNegData")
	public Object[][] getRegNgDat() throws IOException{
		return GenExcelLdr.getExcelData("TC05_RegUsr_n.xlsx", 2);
	}
	
	@Test (dataProvider = "RegNegData")
	public void regUsrExstEm(String name, String existingEmail) throws InterruptedException {
		if (name == null || name.isEmpty() || name.equals("DummyData1")) {
			name = "mahmoud QCtester";
			existingEmail = "testermahmoud358@gmail.com"; // Ensure this exists or test fails
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		registerObject = new RegisterPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click on 'Signup / Login' button
		homeObject.openRegisterPage();
		
		// 5. Verify 'New User Signup!' is visible
		Assert.assertEquals(registerObject.registerMessage.getText(), "New User Signup!");
		
		// 6. Enter name and already registered email address
		// 7. Click 'Signup' button
		registerObject.userCanRegister(name, existingEmail);
		
		// 8. Verify error 'Email Address already exist!' is visible
		Assert.assertEquals(registerObject.errorMessage.getText(), "Email Address already exist!");
	}
}
