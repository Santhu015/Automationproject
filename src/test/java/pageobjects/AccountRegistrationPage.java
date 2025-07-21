package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement firstName;
	@FindBy(xpath = "//input[@name='lastname']")
	WebElement lastName;
	@FindBy(xpath = "//input[@name='email']")
	WebElement eMail;
	@FindBy(xpath = "//input[@name='telephone']")
	WebElement telephone;
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	@FindBy(xpath = "//input[@name='confirm']")
	WebElement passwordConfirm;
	@FindBy(xpath = "//label[normalize-space()='Yes']//input[@name='newsletter']")
	WebElement newsletter;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement agree;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continuebtn;
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement confirmationmsg;

	public void setfirstname(String fname) {
		firstName.sendKeys(fname);
	}

	public void setlastname(String lname) {
		lastName.sendKeys(lname);
	}

	public void setemail(String email) {
		eMail.sendKeys(email);
	}

	public void settelephone(String phone) {
		telephone.sendKeys(phone);
	}

	public void setpassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void setconfirmpassword(String pwd) {
		passwordConfirm.sendKeys(pwd);
	}

	public void clicknewsletter() {
		newsletter.click();
	}

	public void clickonagree() {
		agree.click();
	}

	public void clickoncontinue() {
		continuebtn.click();
	}

	public String getconfirmationmsg() {

		try {
			String msgconfiramtion = confirmationmsg.getText();
			return msgconfiramtion;

		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
