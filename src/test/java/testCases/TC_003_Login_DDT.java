package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_Login_DDT extends BaseClass 
	{
		@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
		public void test_loginDDT(String email, String pwd, String exp) //Data provider is returning us 3 values
		{
			    //This method should take data from Data Provider 
			    logger.info("*****  Starting TC_003_Login_DDT  ******");
			
			    //lets keep the whole code in try catch block
			    try
			    {
			    	HomePage hp = new HomePage(driver);
			    	hp.clickMyAccount();
			    	hp.clickLogin();

			    	LoginPage lp = new LoginPage(driver);
			    	lp.setEmail(email);//email whatever we get from DataProvider
			    	lp.setPassword(pwd);
			    	lp.clickLogin();
				
			    	//Validation 
			    	MyAccountPage myaccPage = new MyAccountPage(driver);
			    	boolean targetpage = myaccPage.isMyAccountPageExists();
			
			    	//If Data is Valid : Positive Testing
			    	if(exp.equals("Valid"))
						{
			    			if(targetpage==true)
						 		{
							    	myaccPage.clickLogout(); //Once when the data is valid , we need to click on Logout to login with next credentials
							    	Assert.assertTrue(true); // Once when we logged out , we are making  test pass 
						 		}
			    			else
			    				{
							 		Assert.assertTrue(false); // We are making test to fail 
			    				}
						}
				
			    	//If Data is Invalid : Negative Testing				
			    	if(exp.equals("Invalid"))
			    		{
			    			if(targetpage==true)
			    				{
			    					myaccPage.clickLogout(); 
			    					Assert.assertTrue(false);
			    				}
			    			else
			    				{
			    					Assert.assertTrue(true); 
			    				}
			    		}
			    }
                catch(Exception e)
			    	{
                		Assert.fail();
			    	}
			   	logger.info("*****  Finished TC_003_Login_DDT  ******");
		 }
	}

				

