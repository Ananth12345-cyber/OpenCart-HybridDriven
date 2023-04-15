package testCases;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

//This is our first base Test Case 
public class TC_001_AccountRegistrationTest extends BaseClass 
	{
		@Test(groups = {"Regression","Master"})
		public void test_account_Registration() //This is the actual test
			{
		        logger.debug("Application Logs...........");
			    logger.info("**********Starting TC_001_AccountRegistrationTest*********************");
			
			    //To handle all types of exception , In case if anything goes wrong
				
			    try {
			      //Step : 1 :  
			      HomePage hp = new HomePage(driver);
				  hp.clickMyAccount(); 
				  logger.info("*****************Clicked on My Account Link***********");
				  
				  hp.clickRegister();	 
				  logger.info("*****************Clicked on Register Link***********");
				  
			   	  //Step : 2 :
				  AccountRegistrationPage regispage = new AccountRegistrationPage(driver);
				 
				  logger.info("*****************Providing Customer Data***************");
				  
			      regispage.setFirstName(randomString().toUpperCase());//If we want the randomly generated string into Uppercase 
			   	
				  regispage.setLastName(randomString().toUpperCase());
				
				  regispage.setEmail(randomString()+"@gmail.com");
	            
				  regispage.setTelephone(randomNumber());
	            
				  //feed the same values for both password and confirm password fields
				  String password = randomAlphaNumberic();
				  regispage.setPassword(password);
	              regispage.setConfirmPassword(password);
	            
				  regispage.setPrivacyPolicy();         
	              regispage.clickContinue();
	              logger.info("*****************Clicked on Continue***************");
	              
	              //compare the expected message with actual message using Assertions
	              logger.info("*****************Validating Expected Message***************");
	              String confirmationMsg = regispage.getConfirmationMsg();
	              Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!","Test Failed");
			 }
			 catch(Exception e)
			   {
				 logger.error("Test Failed");
				 Assert.fail();
			   }
			 logger.info("**********Finished TC_001_AccountRegistrationTest*********************");
	   }
	}





