package tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pages.CartPage;
import pages.ConfirmationPage;
import pages.LandingPage;
import pages.OrderPage;
import pages.ProductCatelog;
import pages.checkoutPage;

public class SubmitOrderTest extends BaseClass{
	String prodName = "ZARA COAT 4";
	
	@Test(dataProvider = "getData", groups = "Purchase")
	public void SubmitTest(HashMap<String, String> input) throws InterruptedException, IOException {
		//Here i directly created object of ProductCatelog in loginApp
		ProductCatelog pc = landingPage.loginApp(input.get("email"), input.get("pass"));
		pc.getProductList();
		pc.addProductToCart(input.get("prodName"));
		
		//Here i directly created object of CartPage in goToCart
		CartPage cp = pc.goToCart();
		boolean match = cp.VerifyProdDisplay(input.get("prodName"));
		assertTrue(match);
		
		checkoutPage CheckPage = cp.goToCheckout();
		CheckPage.selectCountry("india");
		ConfirmationPage confirmation = CheckPage.submitOrder();
		String confirmMessege = confirmation.getConfirmationMessege();
		System.out.println(confirmMessege);
		Assert.assertTrue(confirmMessege.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	
	//To verify that "ADIDAS ORIGINAL" are display or not
//	@Test(dependsOnMethods = {"SubmitTest"})
	public void OrderHistoryTest() {
		ProductCatelog productCatelog = landingPage.loginApp("admin001@gmail.com", "@Admin01");
		OrderPage orderPage = productCatelog.goToOrderPage();
		assertTrue(orderPage.VerifyProdDisplay(prodName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getDataFromMap(
				System.getProperty("user.dir") + "\\src\\main\\resources\\PurchaseOrder.json");
		
		return new Object[][]{
			{data.get(0)},
			{data.get(1)}	
		};
		
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "admin001@gmail.com");
//		map.put("pass", "@Admin01");
//		map.put("prodName", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "riyaj01@gmail.com");
//		map1.put("pass", "@Riyaj01");
//		map1.put("prodName", "ZARA COAT 3");
	}
}
