package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@name='email']")
	WebElement eMailAddress;
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement login;

	public void setemailaddress(String email) {
		eMailAddress.sendKeys(email);
	}

	public void setpassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void clicklogin() {
		login.click();
	}

}
