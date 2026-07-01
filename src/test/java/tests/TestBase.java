package tests;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	protected WebDriver driver;
	String baseURL = "https://automationexercise.com/";
  
	@BeforeMethod
	public void openWebsite() {
		ChromeOptions options = new ChromeOptions();
		// Block Google Ads at the DNS level without needing any extensions!
		options.addArguments("--host-resolver-rules=MAP *googleads.g.doubleclick.net 127.0.0.1, MAP *pagead2.googlesyndication.com 127.0.0.1, MAP *tpc.googlesyndication.com 127.0.0.1, MAP *ad.doubleclick.net 127.0.0.1");
		options.addArguments("--remote-allow-origins=*");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void closeWebsite() {
		driver.quit();
	}

}
