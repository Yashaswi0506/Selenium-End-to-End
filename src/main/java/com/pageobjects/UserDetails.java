package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Components;

public class UserDetails extends Components {
	WebDriver driver;

	public UserDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
		// TODO Auto-generated constructor stub
		
		@FindBy(xpath="//input[@id = 'first-name']")
		WebElement fnameInput;
		
		@FindBy(xpath="//input[@id = 'last-name']")
		WebElement lastInput;
		
		@FindBy(xpath="//input[@id = 'postal-code']")
		WebElement codeInput;
		
		@FindBy(xpath="//input[@id='continue']")
		WebElement continueButton;
		
		@FindBy(xpath="//div[@class='error-message-container error']")
		WebElement error;
		
		@FindBy(xpath="//div[@class='header_secondary_container']/span[text()='Checkout: Overview']")
		WebElement checkOutOverview;
		
		By waitFOrCheckOut = By.xpath("//div[@class='header_secondary_container']/span[text()='Checkout: Overview']");
		
		public boolean submitEmptyDetails() throws InterruptedException {
			continueButton.click();
			Thread.sleep(5);
			return error.isDisplayed();
		}
		
		public boolean submitUserDetails(String fname, String lname, String zip) throws InterruptedException {
			fnameInput.click();
			fnameInput.sendKeys(fname);
			Thread.sleep(2);
			lastInput.click();
			lastInput.sendKeys(lname);
			Thread.sleep(2);
			codeInput.click();
			codeInput.sendKeys(zip);
			Thread.sleep(2);
			continueButton.click();
			Thread.sleep(5);
			waitForElementToAppear(waitFOrCheckOut);
			return checkOutOverview.isDisplayed();
			
		}
		
		
		
	

	

}
