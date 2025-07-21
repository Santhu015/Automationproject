package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC_0003LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = { "datadriven", "master" })
	public void verify_loginDDT(String email, String password, String exp) {
		HomePage hp = new HomePage(driver);
		hp.clickmyaccount();
		hp.clicklogin();

		LoginPage lp = new LoginPage(driver);
		lp.setemailaddress(email);
		lp.setpassword(password);
		lp.clicklogin();

		MyAccountPage mcc = new MyAccountPage(driver);
		boolean targetpage = mcc.ismyaccountexist();

		if (exp.equalsIgnoreCase("Valid")) {
			if (targetpage == true) {
				mcc.clicklogout();
				mcc.clicklogin();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("Not Valid")) {
			if (targetpage == true) {
				mcc.clicklogout();
				mcc.clicklogin();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
	}

}
