package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC_0001AccountRegistrationTest extends BaseClass {

	@Test(groups= {"regression","master"})
	public void test_reg(){
		
		HomePage hp= new HomePage(driver);
		hp.clickmyaccount();
		hp.clickregister();
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		regpage.setfirstname(randomstring());
		regpage.setlastname(randomstring());
		regpage.setemail(randomalpanumeric());
		regpage.settelephone(randomnumeric());
		String password=randomstring();
		regpage.setpassword(password);
		regpage.setconfirmpassword(password);
		regpage.clicknewesletter();
		regpage.clickagree();
		regpage.clicccontinue();
		
		String targetpage=regpage.getconfirmationmsg();
		
		if(targetpage.equalsIgnoreCase("Your Account Has Been Created!000000000000000000000000000000000000000000")) {
	      Assert.assertTrue(true);		
		}else {
			Assert.fail();
		}
	}
}
