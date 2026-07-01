package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends PageBase {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//li[@class='active']")
	public WebElement shpCrtBrdCrmb;

	@FindBy(xpath = "//tr[@id='product-1']")
	public WebElement fPrdInCrt;

	@FindBy(xpath = "//tr[@id='product-2']")
	public WebElement sPrdInCrt;

	@FindBy(xpath = "//tr[@id='product-1']//td[@class='cart_price']/p")
	public WebElement fPrdPrice;

	@FindBy(xpath = "//tr[@id='product-1']//button[@class='disabled']")
	public WebElement fPrdQty;

	@FindBy(xpath = "//tr[@id='product-1']//p[@class='cart_total_price']")
	public WebElement fPrdTotPrc;

	@FindBy(xpath = "//tr[@id='product-2']//td[@class='cart_price']/p")
	public WebElement sPrdPrice;

	@FindBy(xpath = "//tr[@id='product-2']//button[@class='disabled']")
	public WebElement sPrdQty;

	@FindBy(xpath = "//tr[@id='product-2']//p[@class='cart_total_price']")
	public WebElement sPrdTotPrc;

	@FindBy(xpath = "//a[@data-product-id='1']")
	public WebElement del1stPrdBtn;

	@FindBy(id = "empty_cart")
	public WebElement emptyCartMessage;

	@FindBy(xpath = "//a[contains(text(), 'Proceed To Checkout')]")
	public WebElement prcToChOutBtn;

	@FindBy(xpath = "//u[contains(text(), 'Register / Login')]")
	public WebElement regLgnModBtn;
}
