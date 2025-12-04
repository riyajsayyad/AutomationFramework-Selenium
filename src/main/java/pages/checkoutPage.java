package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Reusable;

public class checkoutPage extends Reusable{

	WebDriver driver;
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "(//button[@type='button'])[2]")
	WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement submitButton;
	
	By result = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);

		a.sendKeys(country, countryName).build().perform();
		waitForElementAppear(result);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submitButton);
		return new ConfirmationPage(driver);
	}
}
