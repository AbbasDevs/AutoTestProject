package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends PageBase {

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[contains(text(), 'All Products')]")
	public WebElement allPrdsMsg;

	@FindBy(id = "search_product")
	public WebElement searchInput;

	@FindBy(id = "submit_search")
	public WebElement searchBtn;

	@FindBy(xpath = "//h2[contains(text(), 'Searched Products')]")
	public WebElement srchPrdsMsg;

	@FindBy(xpath = "//div[@class='productinfo text-center']/p")
	public WebElement firstProductName;

	@FindBy(xpath = "(//ul[@class='nav nav-pills nav-justified']//a)[1]")
	public WebElement fPrdVwPrdBtn;

	@FindBy(xpath = "//h2[contains(text(), 'Category')]")
	public WebElement categoryHeader;
	
	@FindBy(xpath = "//a[@href='#Women']")
	public WebElement womenCategory;

	@FindBy(xpath = "//a[@href='/category_products/1']")
	public WebElement wmDrsCat;
	
	@FindBy(xpath = "//a[@href='#Men']")
	public WebElement menCategory;

	@FindBy(xpath = "//a[@href='/category_products/3']")
	public WebElement menTshrtCat;
	
	@FindBy(xpath = "//h2[contains(text(), 'Brands')]")
	public WebElement brandsHeader;
	
	@FindBy(xpath = "//a[@href='/brand_products/Polo']")
	public WebElement poloBrand;
	
	@FindBy(xpath = "//a[@href='/brand_products/H&M']")
	public WebElement hmBrand;
	
	@FindBy(xpath = "//h2[@class='title text-center']")
	public WebElement centerTitle; // Used for "WOMEN - DRESS PRODUCTS" etc.
	
	@FindBy(xpath = "//a[@data-product-id='1']")
	public WebElement fPrdAddCrtBtn;
	
	@FindBy(xpath = "//button[contains(text(), 'Continue Shopping')]")
	public WebElement cntShpModBtn;

	@FindBy(xpath = "//a[@data-product-id='2']")
	public WebElement sPrdAddCrtBtn;
	
	@FindBy(xpath = "//u[contains(text(), 'View Cart')]")
	public WebElement viewCartModalBtn;

	public void searchForProduct(String productName) {
		searchInput.clear();
		searchInput.sendKeys(productName);
		searchBtn.click();
	}
	
	public void opn1stPrdDets() {
		clickWithJS(fPrdVwPrdBtn);
	}
	
	public void add1stPrdCrt() {
		clickWithJS(fPrdAddCrtBtn);
	}
	
	public void add2ndPrdCrt() {
		clickWithJS(sPrdAddCrtBtn);
	}
	
	public void continueShopping() {
		cntShpModBtn.click();
	}
	
	public void vwCrtMod() {
		viewCartModalBtn.click();
	}
}
