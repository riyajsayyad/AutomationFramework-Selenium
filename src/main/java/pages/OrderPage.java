package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Reusable;

public class OrderPage extends Reusable {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	public boolean VerifyProdDisplay(String prodName) {
		boolean matches = productNames.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(prodName));
		return matches;
	}
}
