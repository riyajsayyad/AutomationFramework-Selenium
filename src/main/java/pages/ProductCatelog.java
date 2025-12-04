package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Reusable;

public class ProductCatelog extends Reusable {

	WebDriver driver;

	public ProductCatelog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ngx-spinner")
	WebElement spinner;

	By ProductBy = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementAppear(ProductBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findAny().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String prodName) throws InterruptedException {
		WebElement prod = getProductByName(prodName);
		prod.findElement(addTocart).click();
		waitForElementAppear(toastMessage);
		waitForElementDisappear(spinner);
	}
}
