package tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import tests.TestBase;

public class TC25_ScrUpArr_h extends TestBase {
	HomePage homeObject;
	
	@Test
	public void vrfScrUpArr() throws InterruptedException {
		homeObject = new HomePage(driver);
		
		// 1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
		// (Steps 1 & 2 handled by TestBase)
		
		// 3. Verify that home page is visible successfully
		Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)");

		// 4. Scroll down page to bottom
		homeObject.scrollToBottom();
		Thread.sleep(1000); // Give JS time to scroll
		
		// 5. Verify 'SUBSCRIPTION' is visible
		Assert.assertTrue(homeObject.subHdr.isDisplayed());
		
		// 6. Click on arrow at bottom right side to move upward
		homeObject.scrollUpArrowBtn.click();
		Thread.sleep(1000); // Give JS time to scroll up
		
		// 7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
		// Wait for the element to be visible
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(homeObject.fullFledgedText));
		Assert.assertTrue(homeObject.fullFledgedText.isDisplayed());
	}
}
