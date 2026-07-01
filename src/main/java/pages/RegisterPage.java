package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends PageBase{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name="name")
	WebElement nameTxt;
	
	@FindBy(name="email")
	List<WebElement> emailsTxt;
	
	@FindBy(css="#form > div > div > div:nth-child(3) > div > form > button")
	WebElement signUpBtn;
	
	@FindBy(id="id_gender1")
	WebElement genderRadioBtn;
	
	@FindBy(id="password")
	WebElement passwordTxt;
	
	@FindBy(id="days")
	WebElement daysList;
	
	@FindBy(id="months")
	WebElement monthsList;
	
	@FindBy(id="years")
	WebElement yearsList;
	
	@FindBy(id="newsletter")
	WebElement newsLtrChk;
	
	@FindBy(id="optin")
	WebElement spcOffrChk;
	
	@FindBy(id="first_name")
	WebElement firstNameTxt;
	
	@FindBy(id="last_name")
	WebElement lastNameTxt;
	
	@FindBy(id="company")
	WebElement companyTxt;
	
	@FindBy(id="address1")
	WebElement address1Txt;
	
	@FindBy(id="address2")
	WebElement address2Txt;
	
	@FindBy(id="country")
	WebElement countryList;
	
	@FindBy(id="state")
	WebElement stateTxt;
	
	@FindBy(id="city")
	WebElement cityTxt;
	
	@FindBy(id="zipcode")
	WebElement zipCodeTxt;
	
	@FindBy(id="mobile_number")
	WebElement mobileNumberTxt;
	
	@FindBy(className = "btn")
	WebElement createAccountBtn;
	
	@FindBy(linkText = "Continue")
	WebElement continueBtn;
		
	@FindBy(linkText = "Delete Account")
	public WebElement deleteAccountBtn;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[3]/div/h2")
	public WebElement registerMessage;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div/div/h2/b")
	public WebElement entAccMsg;
	
	@FindBy(css="#form > div > div > div > h2 > b")
	public WebElement successMessage;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div/h2/b")
	public WebElement delSuccMsg;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[3]/div/form/p")
	public WebElement errorMessage;
	
	public void userCanRegister(String name,String email) {
		nameTxt.sendKeys(name);
		emailsTxt.get(1).sendKeys(email);
		signUpBtn.click();
	}
	
	public void usrEntAccInfo(String password,int days,
	String month,String year,String firstName,String lastName,
	String company,String address1,String address2,String country,
	String state,String city,String zipCode,String mobileNumber) {
	
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(genderRadioBtn));
		genderRadioBtn.click();
		passwordTxt.sendKeys(password);
		
		Select makeDaysList = new Select(daysList);
		makeDaysList.selectByIndex(days);
		
		Select makeMonthList = new Select(monthsList);
		makeMonthList.selectByValue(month);
		
		Select makeYearsList = new Select(yearsList);
		makeYearsList.selectByVisibleText(year);
		
		newsLtrChk.click();
		spcOffrChk.click();
		
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.sendKeys(lastName);
		companyTxt.sendKeys(company);
		
		address1Txt.sendKeys(address1);
		address2Txt.sendKeys(address2);
		
		Select makeCountryList = new Select(countryList);
		makeCountryList.selectByVisibleText(country);
		
		stateTxt.sendKeys(state);
		cityTxt.sendKeys(city);
		
		zipCodeTxt.sendKeys(zipCode);
		mobileNumberTxt.sendKeys(mobileNumber);
		
		createAccountBtn.click();
	}
	
	public void usrEntAccInfo(String password,String firstName,String lastName,
			String address1,String country,String state,
			String city,String zipCode,String mobileNumber) {
				passwordTxt.sendKeys(password);
				
				firstNameTxt.sendKeys(firstName);
				lastNameTxt.sendKeys(lastName);
				
				address1Txt.sendKeys(address1);
				
				Select makeCountryList = new Select(countryList);
				makeCountryList.selectByVisibleText(country);
				
				stateTxt.sendKeys(state);
				cityTxt.sendKeys(city);
				
				zipCodeTxt.sendKeys(zipCode);
				mobileNumberTxt.sendKeys(mobileNumber);
				
				createAccountBtn.click();
			}
	
	public void userCanContinue() {
		continueBtn.click();
	}
	
	public void usrDelAcc() {
		deleteAccountBtn.click();
	}
}
