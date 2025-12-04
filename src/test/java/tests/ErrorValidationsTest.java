package tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import listeners.RetryTest;
import pages.CartPage;
import pages.ConfirmationPage;
import pages.ProductCatelog;
import pages.checkoutPage;

public class ErrorValidationsTest extends BaseClass{
	
	@Test(groups = {"ErrorHandaling"}, retryAnalyzer = RetryTest.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {		
		landingPage.loginApp("admin001@gmail.com", "@Amin01");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		
		String prodName = "ADIDAS ORIGINAL";
		//Here i directly created object of ProductCatelog in loginApp
		ProductCatelog pc = landingPage.loginApp("admin001@gmail.com", "@Admin01");
		pc.getProductList();
		pc.addProductToCart(prodName);
		
		//Here i directly created object of CartPage in goToCart
		CartPage cp = pc.goToCart();
		boolean match = cp.VerifyProdDisplay("ADIDAS ORIGINALL");
		assertFalse(match);
	}

}
