package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Components;

public class DemoSite extends Components {

	WebDriver driver;
		
	public DemoSite(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver,this);
		}
		
		//path of webelements
		
		 
		 @FindBy(xpath="//div[@class='login_logo']")
		 WebElement logo;
		 
		 @FindBy(xpath="//input[@class='input_error form_input' and @placeholder='Username']")
		 WebElement userInput;
		 
		 @FindBy(xpath="//input[@class='input_error form_input' and @placeholder='Password']")
		 WebElement userPassword;
		 
		 @FindBy(xpath="//input[@class='submit-button btn_action']")
		 WebElement loginButton;
		 
		 @FindBy(xpath="//span[text()='Products']")
		 WebElement products;
		 
		 @FindBy(xpath="//button[@id='react-burger-menu-btn']")
		 WebElement hamburgerButton;
		 
		 @FindBy(xpath="//a[@id='logout_sidebar_link']")
		 WebElement logoutButton;
		 
		 By waitLoogout = By.xpath("//a[@id='logout_sidebar_link']");
		 By waitLogin = By.xpath("//input[@class='submit-button btn_action']");
		 
		 
		 
		 
		 public void goTo(String url) {
			 driver.get(url);
			 
		 }
		 
		
		 
		 public boolean verifyLogoIsPresent() {
			 return logo.isDisplayed();
		 }
		 
		 public String[] verifyLogoCSSProperties() {
			 String[] logoProp = new String[3];

		        // Retrieve CSS properties
		        logoProp[0] = logo.getCssValue("font-family");
		        logoProp[1] = logo.getCssValue("font-size");
		        logoProp[2] = logo.getCssValue("line-height");
		        return logoProp;
			 
		 }
		 
		 public void login(String userName, String password) throws InterruptedException {
			 userInput.click();
			 userInput.sendKeys(userName);
			 Thread.sleep(5);
			 userPassword.click();
			 userPassword.sendKeys(password);
			 Thread.sleep(5);
			 loginButton.click();
			 //boolean alertPresent = checkForAlert();
			 //if(alertPresent) {
				// driver.switchTo().alert().accept();
			// }
			 
			 
		 }
		 
		public boolean verifyProducts() {
			return products.isDisplayed();
		}
		
		public boolean logoutUser() {
			hamburgerButton.click();
			waitForElementToAppear(waitLoogout);
			logoutButton.click();
			waitForElementToAppear(waitLogin);
			if(loginButton.isDisplayed()) {
				return true;
			}
			return false;
			
			
			
		}
		 

	}


