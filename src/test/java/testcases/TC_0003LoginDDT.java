package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC_0003LoginDDT extends BaseClass {

	@Test(dataProvider = "Login Data", dataProviderClass = DataProviders.class, groups = { "datadriven", "master" })

	public void verifylogin_DDT(String email, String pwd, String exp) {

		HomePage hp = new HomePage(driver);
		hp.clickmyaccount();
		hp.clicklogin();

		LoginPage lp = new LoginPage(driver);
		lp.setemailaddress(email);
		lp.setpassword(pwd);
		lp.clicklogin();

		MyAccountPage acc = new MyAccountPage(driver);

		boolean targetpage = acc.ismyaccountexist();
		if (exp.equalsIgnoreCase("Valid")) {
			if (targetpage == true) {
				acc.clickonlogout();
				acc.clickonlogin();
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
		if (exp.equalsIgnoreCase("Not Valid")) {
			if (targetpage == true) {
				acc.clickonlogout();
				acc.clickonlogin();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}

	}

}
