package com.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Components;

public class Cart extends Components{
	WebDriver driver;
	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cartButton;
	
	@FindBy(xpath="//div[@class='header_secondary_container']/span")
	WebElement cartDes;
	
	@FindBys(@FindBy(xpath="//div[@class='cart_item']"))
	List<WebElement> cartItems;
	
	@FindBy(xpath="//button[@name='checkout']")
	WebElement checkOut;
	
	@FindBy(xpath="//div[@class='checkout_info']")
	WebElement checkOutPage;
	
	
	By yourCart = By.xpath("//div[@class='header_secondary_container']/span");
	By checkOutPageInfo = By.xpath("//div[@class='checkout_info']");
	
	
	
	public String verifyCartPage() {
		cartButton.click();
		waitForElementToAppear(yourCart);
		return cartDes.getText();
		
		
	}
	
	public int getCartCount() {
		return cartItems.size();
		
	}
	
	public boolean checkoutItems() {
		checkOut.click();
		waitForElementToAppear(checkOutPageInfo);
		if(checkOutPage.isDisplayed()) {
			return true;
		}
		return false;
	}

}
