package com.Functionalty;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.TestComponents.BaseTest;
import com.pageobjects.Cart;
import com.pageobjects.CheckOut;
import com.pageobjects.DemoSite;
import com.pageobjects.Products;
import com.pageobjects.UserDetails;

public class EndToEnd extends BaseTest{
float cartTotal = 0.0f;
DemoSite demoSite;
Products product;
int cartValue = 0;
Cart mycart;
UserDetails details;
CheckOut checkout;


    
    @BeforeClass
    public void landingPage() {
        try {
            demoSite = launchSite();
            product = instantiateProducts();
            mycart = new Cart(driver);
            details = new UserDetails(driver);
            checkout = new CheckOut(driver);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
@Test(priority = 1)
    
    public void loginUser() throws InterruptedException  {
    	System.out.println(prop.getProperty("user"));
    	demoSite.login(prop.getProperty("user"), prop.getProperty("password"));
    	Thread.sleep(5);
    	Assert.assertTrue(demoSite.verifyProducts());
    }

@Test(priority = 2)
public void verifyCartEmpty() {
	System.out.println(product.checkCart(cartValue));
	Assert.assertFalse(product.checkCart(cartValue));
}

@Test(priority = 3)
public void AddElementsToTheCart() throws NumberFormatException, InterruptedException {
	
	cartTotal+= Float.parseFloat(product.addElement(6));
	System.out.println(cartTotal);
	Assert.assertEquals(product.verifyProductAdded(6), "Remove");
	Assert.assertTrue(product.checkCart(1));
	cartTotal+= Float.parseFloat(product.addElement(2));
	System.out.println(cartTotal);
	Assert.assertEquals(product.verifyProductAdded(2), "Remove");
	Assert.assertTrue(product.checkCart(2));
	
	
}

@Test(priority = 4)
public void checkOut(){
	Assert.assertEquals(mycart.verifyCartPage(), "Your Cart");
	Assert.assertEquals(mycart.getCartCount(), 2);
	Assert.assertTrue(mycart.checkoutItems());
	
}
@Parameters({"fname", "lname","zip"})
@Test(priority = 5)
public void fillForm(String fname, String lname,String zip) throws InterruptedException {
	Assert.assertTrue(details.submitEmptyDetails());
	Assert.assertTrue(details.submitUserDetails(fname, lname, zip));
	
	
	
}

    
    
    
    @Test(priority = 6)
    public void checOutCart() throws InterruptedException{
    	Assert.assertEquals(checkout.verifyPrice(), cartTotal);
    	cartTotal += checkout.addTaxes();
    	Assert.assertEquals(checkout.verifyPriceTaxes(), cartTotal);
    	Assert.assertEquals(checkout.verifyOrderPlaced(), "Thank you for your order!");
    	
    }
    
    
    @AfterClass
    public void TearDown() {
       
            driver.quit();
        
    }
    
   
    
    
    
    
	
	
	

}
