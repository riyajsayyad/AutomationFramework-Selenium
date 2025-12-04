package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Reusable;

public class LandingPage extends Reusable{


	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessege;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatelog loginApp(String email, String pass) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		login.click();
		return new ProductCatelog(driver);
	}
	
	public String getErrorMsg() {
		waitForWebElementAppear(errorMessege);
		return errorMessege.getText();
	}
	
	
}
