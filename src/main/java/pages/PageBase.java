package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
	protected WebDriver driver;
	
	public PageBase(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver , this);
	}
	
	public void clickWithJS(org.openqa.selenium.WebElement element) {
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void scrollToBottom() {
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollToTop() {
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
	}
}
