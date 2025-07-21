package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC_0001AccountRegistrationTest extends BaseClass {

	@Test(groups= {"regression","master"})
	public void verify_reg() {
		HomePage hp = new HomePage(driver);
		hp.clickmyaccount();
		hp.clickregister();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setfirstname(randomstring());

		regpage.setlastname(randomstring());
		regpage.setemail(randomalphanumeric());
		regpage.settelephone(randomnumeric());
		String password = randomstring();
		regpage.setpassword(password);
		regpage.setconfirmpassword(password);
		regpage.clicknewsletter();
		regpage.clickonagree();
		regpage.clickoncontinue();

		String targetpage = regpage.getconfirmationmsg();
		if (targetpage.equalsIgnoreCase("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail();
		}
	}
}
