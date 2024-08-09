package com.Functionalty;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.TestComponents.BaseTest;
import com.pageobjects.DemoSite;
import org.testng.Assert;

public class MyAccount extends BaseTest {
    DemoSite demoSite;
    
    @BeforeClass
    public void landingPage() {
        try {
            demoSite = launchSite();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @Test
    public void loginUser() throws InterruptedException  {
    	System.out.println(prop.getProperty("user"));
    	demoSite.login(prop.getProperty("user"), prop.getProperty("password"));
    	Thread.sleep(5);
    	Assert.assertTrue(demoSite.verifyProducts());
    }
    
    @Test
    public void logout() {
    	boolean isLoginButton= demoSite.logoutUser();
    	Assert.assertTrue(isLoginButton);
    }
    
    @AfterClass
    public void TearDown() {
       
            driver.quit();
        
    }
    
    
}
