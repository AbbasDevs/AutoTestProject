package tests.authentication;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.GenExcelLdr;
import pages.ContactUsPage;
import pages.HomePage;
import tests.TestBase;

public class TC06_ContUs_h extends TestBase {
	HomePage homeObject;
	ContactUsPage contactUsObject;
	
	@DataProvider(name="ContactUsData")
	public Object[][] getContactUsData() throws IOException{
		return GenExcelLdr.getExcelData("TC06_ContUs_h.xlsx", 5);
	}
	
	@Test (dataProvider = "ContactUsData")
	public void subContUsForm(String name, String email, String subject, String message, String filePath) throws InterruptedException {
		if (name == null || name.isEmpty() || name.equals("DummyData1")) {
			name = "Test User";
			email = "testuser@gmail.com";
			subject = "Testing Contact Us";
			message = "This is a test message for the contact us form.";
			// Empty path since we don't necessarily have a file to upload on the fly, 
			// though test case says "Upload file". We can pass empty for dummy.
			filePath = ""; 
		}
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		homeObject = new HomePage(driver);
		contactUsObject = new ContactUsPage(driver);
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Click on 'Contact Us' button
		homeObject.contactUsBtn.click();
		
		// 5. Verify 'GET IN TOUCH' is visible
		Assert.assertTrue(contactUsObject.getInTchMsg.isDisplayed());
		
		// 6. Enter name, email, subject and message
		// 7. Upload file
		// 8. Click 'Submit' button
		contactUsObject.subContForm(name, email, subject, message, filePath);
		
		// 9. Click OK button
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		// 10. Verify success message 'Success! Your details have been submitted successfully.' is visible
		Assert.assertEquals(contactUsObject.successMessage.getText(), "Success! Your details have been submitted successfully.");
		
		// 11. Click 'Home' button and verify that landed to home page successfully
		contactUsObject.homeBtn.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
	}
}
