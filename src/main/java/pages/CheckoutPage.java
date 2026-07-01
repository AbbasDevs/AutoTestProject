package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends PageBase {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[contains(text(), 'Address Details')]")
	public WebElement addrDetHdr;

	@FindBy(xpath = "//h2[contains(text(), 'Review Your Order')]")
	public WebElement revOrdHdr;

	@FindBy(name = "message")
	public WebElement commentTxt;

	@FindBy(xpath = "//a[contains(text(), 'Place Order')]")
	public WebElement placeOrderBtn;

	@FindBy(name = "name_on_card")
	public WebElement nameOnCardTxt;

	@FindBy(name = "card_number")
	public WebElement cardNumberTxt;

	@FindBy(name = "cvc")
	public WebElement cvcTxt;

	@FindBy(name = "expiry_month")
	public WebElement expiryMonthTxt;

	@FindBy(name = "expiry_year")
	public WebElement expiryYearTxt;

	@FindBy(id = "submit")
	public WebElement payCnfOrdBtn;

	@FindBy(xpath = "//p[contains(text(), 'Congratulations! Your order has been confirmed!')]")
	public WebElement successMessage;

	@FindBy(xpath = "//a[contains(text(), 'Download Invoice')]")
	public WebElement dwnInvBtn;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[@class='addr_fname address_lastname']")
	public WebElement delAddrNm;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[@class='addr_fname address_lastname']")
	public WebElement invAddrNm;
	
	@FindBy(xpath = "//ul[@id='address_delivery']")
	public WebElement delAddrCnt;

	@FindBy(xpath = "//ul[@id='address_invoice']")
	public WebElement invAddrCnt;
	
	public void entPayDets(String name, String card, String cvc, String month, String year) {
		nameOnCardTxt.sendKeys(name);
		cardNumberTxt.sendKeys(card);
		cvcTxt.sendKeys(cvc);
		expiryMonthTxt.sendKeys(month);
		expiryYearTxt.sendKeys(year);
		payCnfOrdBtn.click();
	}
}
