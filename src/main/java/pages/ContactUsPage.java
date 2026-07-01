package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends PageBase {

	public ContactUsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[contains(text(), 'Get In Touch')]")
	public WebElement getInTchMsg;

	@FindBy(name = "name")
	WebElement nameTxt;

	@FindBy(name = "email")
	WebElement emailTxt;

	@FindBy(name = "subject")
	WebElement subjectTxt;

	@FindBy(id = "message")
	WebElement messageTxt;

	@FindBy(name = "upload_file")
	public WebElement uploadFileBtn;

	@FindBy(name = "submit")
	WebElement submitBtn;

	@FindBy(css = "div.status.alert.alert-success")
	public WebElement successMessage;

	@FindBy(xpath = "//a[contains(text(), 'Home')]")
	public WebElement homeBtn;

	public void subContForm(String name, String email, String subject, String message, String filePath) {
		nameTxt.sendKeys(name);
		emailTxt.sendKeys(email);
		subjectTxt.sendKeys(subject);
		messageTxt.sendKeys(message);
		
		if(filePath != null && !filePath.isEmpty()) {
			uploadFileBtn.sendKeys(filePath);
		}
		
		submitBtn.click();
	}
}
