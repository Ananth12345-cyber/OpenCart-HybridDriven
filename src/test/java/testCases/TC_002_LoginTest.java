package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
public class TC_002_LoginTest extends BaseClass
	{
	   @Test(groups = {"Sanity","Master"})
	   public void test_login()
			{
				try 
				{ 
		        logger.info("*************** Starting TC_002_LoginTest *****************");
				
				//1st Page
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				logger.info("*************** Clicked on MyAccont *****************");
				
				hp.clickLogin();
				logger.info("*************** Clicked on Login Link *****************");
				
				//2nd Page
				LoginPage lp = new LoginPage(driver);
				logger.info("Providing Login Details");
				lp.setEmail(rb.getString("email"));//we can access the email value from config.properties file using Resource Bundle variable 
				lp.setPassword(rb.getString("password"));//we can access the pwd value from config.properties file using Resource Bundle variable 
				lp.clickLogin();
				logger.info("*************** Clicked on Login Button *****************");
				
				//3rd Page 
				MyAccountPage myAcc= new MyAccountPage(driver);
				boolean targetpage = myAcc.isMyAccountPageExists();//when we call this method, it returns boolean value i.e., either True or False 
				Assert.assertEquals(targetpage, true, "Invalid Login Data");
				}
				
				catch(Exception e)
				{
					Assert.fail();
				}
				 logger.info("*************** Finished TC_002_LoginTest *****************");
			}
	}	




