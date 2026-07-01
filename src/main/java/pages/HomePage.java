package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]//a[@href='/login']")
	WebElement lgnRegBtn;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]//a[@href='/']")
	public WebElement homeBtn;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]//a[contains(., 'Logged in as')]")
	public WebElement loggedInAsTxt;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]//a[@href='/contact_us']")
	public WebElement contactUsBtn;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]//a[@href='/products']")
	public WebElement productsBtn;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]//a[@href='/view_cart']")
	public WebElement cartBtn;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]//a[@href='/test_cases']")
	public WebElement testCasesBtn;
	
	@FindBy(xpath = "//h2[text()='Subscription']")
	public WebElement subHdr;
	
	@FindBy(id = "susbscribe_email")
	public WebElement subEmInp;
	
	@FindBy(id = "subscribe")
	public WebElement subscribeBtn;
	
	@FindBy(id = "success-subscribe")
	public WebElement subSuccMsg;
	
	@FindBy(xpath = "//h2[contains(text(), 'RECOMMENDED ITEMS') or contains(text(), 'recommended items')]")
	public WebElement recItmsHdr;
	
	@FindBy(xpath = "(//div[@id='recommended-item-carousel']//a[contains(@class, 'add-to-cart')])[1]")
	public WebElement fRecAddCrtBtn;
	
	@FindBy(xpath = "//u[contains(text(), 'View Cart')]")
	public WebElement viewCartModalBtn;
	
	@FindBy(id = "scrollUp")
	public WebElement scrollUpArrowBtn;
	
	@FindBy(xpath = "//h2[contains(text(), 'Full-Fledged practice website for Automation Engineers')]")
	public WebElement fullFledgedText;
	
	public void openLoginPage() {
		lgnRegBtn.click();
	}
	
	public void openRegisterPage() {
		lgnRegBtn.click();
	}
	
	public void openHomePage() {
		homeBtn.click();
	}
	
	public void openProductsPage() {
		clickWithJS(productsBtn);
	}
	
	public void subscribe(String email) {
		scrollToBottom();
		subEmInp.sendKeys(email);
		subscribeBtn.click();
	}
}
