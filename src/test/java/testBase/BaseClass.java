package testBase;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver; // Make it public so that its accessible 
	
	public Logger logger; //declare the variable "logger" for logging
	
	public ResourceBundle rb;
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String br) // we are doing this only once 
		{
		    rb = ResourceBundle.getBundle("config"); //To Load config.properties file
		
		    //Initiate
		    logger = LogManager.getLogger(this.getClass());//LogManager --> Pre-defined class , getLogger() --> Static method 
		    // We should pass the current class name --> this.getClass()
		
		    ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		    
		    chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors"); //We need to use this if we are using latest version of chrome with Selenium 4.6.0 
		
		    //WebDriverManager.chromedriver().setup();
			
		    if(br.equals("chrome"))
		     {
		    	driver= new ChromeDriver(chromeOptions);
		     }
		    
		    else if(br.equals("edge"))
		     {
		        driver = new EdgeDriver();	//We can use EdgeOptions if we want 
		     }
		    
		    else
		     {
		       driver = new FirefoxDriver();	
		     }
		    
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//10 sec's
			
			driver.get(rb.getString("appURL"));
		}
	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown()
		{
			driver.quit();
		}
	
	public String randomString() //These are not TestNG methods , These are Java Methods
	    {
		     String generatedString1 = RandomStringUtils.randomAlphabetic(5) ;
		     return(generatedString1);
	    }
	
	// The generated number needs to be stored in the String format 
	// because while passing the telephone number into application, 
	// we should pass by converting it into string only because sendKeys will send only the string
	public String randomNumber() //These are not TestNG methods, These are Java Methods
    {
	     String generatedString2 = RandomStringUtils.randomNumeric(10);//It will generate 10 digits  
	     return(generatedString2);
    }
	
	//The generated number needs to be stored in the String format because while passing the telephone number into application, 
   //we should pass by converting it into string only because sendKeys will send only the string
	public String randomAlphaNumberic() //These are not TestNG methods , These are Java Methods
	  {
		     String str = RandomStringUtils.randomAlphabetic(10) ;  
		     String num = RandomStringUtils.randomNumeric(10) ; 
		     //To return both (like abc123)
		     return (str+"@"+num);//we can pass one special character like this 
	  }
	
	public String captureScreen(String tname) throws IOException 
	  {
		//The screenshot should be saved with TimeStamp
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
   	  }
}




