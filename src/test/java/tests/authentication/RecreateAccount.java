package tests.authentication;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.HomePage;
import pages.RegisterPage;
import tests.TestBase;

public class RecreateAccount extends TestBase {
	HomePage homeObject;
	RegisterPage registerObject;
	
	@DataProvider(name="RegisterData")
	public Object[][] getRegisterData() throws IOException{
		// We reuse the exact same Excel sheet from TC01
		return GenExcelLdr.getExcelData("TC01_RegUsr_h.xlsx", 16);
	}
	
	@Test (dataProvider = "RegisterData")
	public void recAccNxtTst(String name, String email, String password, String day, String month, String year,
			String fName, String lName, String company, String addr1, String addr2, String country, 
			String state, String city, String zip, String mobile) throws InterruptedException {
		
		if (name == null || name.isEmpty() || name.equals("DummyData1")) {
			name = "mahmoud QCtester";
			email = "testermahmoud358@gmail.com";
			password = "Password123";
			day = "10"; month = "12"; year = "2000";
			fName = "mahmoud"; lName = "QCtester"; company = "IT";
			addr1 = "123 Tahrir Square"; addr2 = "Downtown"; country = "United States";
			state = "Cairo"; city = "Cairo"; zip = "11511"; mobile = "+201000000000";
		}
		
		homeObject = new HomePage(driver);
		registerObject = new RegisterPage(driver);
		
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		homeObject.openRegisterPage();
		Assert.assertEquals(registerObject.registerMessage.getText(), "New User Signup!");
		
		registerObject.userCanRegister(name, email);
		Assert.assertEquals(registerObject.entAccMsg.getText(), "ENTER ACCOUNT INFORMATION");
		
		registerObject.usrEntAccInfo(password, 10, month, year, 
				fName, lName, company, addr1, addr2, country, state, city, zip, mobile);
				
		Assert.assertTrue(registerObject.successMessage.getText().equalsIgnoreCase("Account Created!"));
		
		registerObject.userCanContinue();
		
		// Intentionally leaving the account created and undeleted so TC02, TC04, and TC05 can pass using the same email!
	}
}
