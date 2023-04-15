package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Second Page Object
public class HomePage extends BasePage 
	{
		public HomePage(WebDriver driver) 
			{	
				super(driver);
			}

		// Elements
		//@FindBy(xpath = "//span[text()='My Account']")
		@FindBy(xpath = "//a[@title='My Account']")
		WebElement lnkMyaccount;

		@FindBy(linkText = "Register")
		WebElement lnkRegister;
		
		@FindBy(linkText = "Login")
		WebElement lnkLogin;

		// Action Methods
		public void clickMyAccount() 
			{
				lnkMyaccount.click();
			}

		public void clickRegister() 
			{
				lnkRegister.click();
			}
		
		public void clickLogin()
			{
			    lnkLogin.click();
			}
	}

