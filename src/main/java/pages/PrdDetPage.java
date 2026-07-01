package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrdDetPage extends PageBase {

	public PrdDetPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='product-information']/h2")
	public WebElement productName;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(.,'Category:')]")
	public WebElement productCategory;

	@FindBy(xpath = "//div[@class='product-information']/span/span")
	public WebElement productPrice;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(.,'Availability:')]")
	public WebElement prdAvail;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(.,'Condition:')]")
	public WebElement productCondition;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(.,'Brand:')]")
	public WebElement productBrand;

	@FindBy(id = "name")
	public WebElement reviewNameTxt;

	@FindBy(id = "email")
	public WebElement reviewEmailTxt;

	@FindBy(id = "review")
	public WebElement reviewMessageTxt;

	@FindBy(id = "button-review")
	public WebElement reviewSubmitBtn;

	@FindBy(xpath = "//span[contains(text(),'Thank you for your review.')]")
	public WebElement revSuccMsg;
	
	@FindBy(xpath = "//a[@href='#reviews']")
	public WebElement wrRevHdr;
	
	@FindBy(id = "quantity")
	public WebElement quantityTxt;

	@FindBy(xpath = "//button[contains(@class, 'cart')]")
	public WebElement addToCartBtn;
	
	@FindBy(xpath = "//u[contains(text(), 'View Cart')]")
	public WebElement viewCartModalBtn;

	public void addReview(String name, String email, String review) {
		reviewNameTxt.sendKeys(name);
		reviewEmailTxt.sendKeys(email);
		reviewMessageTxt.sendKeys(review);
		reviewSubmitBtn.click();
	}
}
