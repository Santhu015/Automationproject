package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[@title='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement register;
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement login;

	public void clickmyaccount() {
		myAccount.click();
	}

	public void clickregister() {
		register.click();
	}

	public void clicklogin() {
		login.click();
	}
}
