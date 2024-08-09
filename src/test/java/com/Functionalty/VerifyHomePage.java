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

import junit.framework.Assert;

public class VerifyHomePage extends BaseTest {
	
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
public void verifyHomePage() {
	Assert.assertTrue(demoSite.verifyLogoIsPresent());
}

@Test
public void verifyLogoCSS() {
	String logoProp[] = demoSite.verifyLogoCSSProperties();
	SoftAssert softassert = new SoftAssert();
	
	softassert.assertTrue(logoProp[0].contains("\"DM Mono\""));
	softassert.assertTrue(logoProp[0].contains("\"sans-serif\""));
	softassert.assertEquals(logoProp[1], "24px"); //font size
	softassert.assertEquals(logoProp[2], "48px"); //line-height
	
	softassert.assertAll();
	
}
	
	


@AfterClass
public void TearDown() {
   
        driver.quit();
    
}



}
