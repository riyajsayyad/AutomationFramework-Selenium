package pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Reusable;

public class CartPage extends Reusable{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	public boolean VerifyProdDisplay(String prodName) {
		boolean matches = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(prodName));
		return matches;
	}
	
	public checkoutPage goToCheckout() {
		checkoutButton.click();
		return new checkoutPage(driver);
	}
}
