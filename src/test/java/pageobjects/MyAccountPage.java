package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myaccount;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Login']")
	WebElement login;

	public boolean ismyaccountexist() {
		return myaccount.isDisplayed();
	}

	public void clickonlogout() {
		logout.click();
	}

	public void clickonlogin() {
		login.click();
	}

}
