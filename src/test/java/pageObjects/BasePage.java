package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//First Page Object
public class BasePage //We can extend the same BasePage class in all the pageObjects Class instead of creating the constructor again and again everytime
	{
		WebDriver driver;
	    
		public BasePage(WebDriver driver)
	    	{
				this.driver=driver;
				PageFactory.initElements(driver,this);
	    	}
	}

