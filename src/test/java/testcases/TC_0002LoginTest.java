package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC_0002LoginTest extends BaseClass {
	@Test(groups = { "sanity", "master" })
	public void verify_login() {

		HomePage hp = new HomePage(driver);
		hp.clickmyaccount();
		hp.clicklogin();

		LoginPage lp = new LoginPage(driver);
		lp.setemailaddress(p.getProperty("username"));
		lp.setpassword(p.getProperty("password"));
		lp.clicklogin();

		MyAccountPage acc = new MyAccountPage(driver);
		
		boolean targetpage = acc.ismyaccountexist();

		if (targetpage == true) {
			acc.clickonlogout();
			acc.clickonlogin();
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

}
