package com.pageobjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Components;


public class Products extends Components {
	WebDriver driver;
	int index = 0;
	JavascriptExecutor js;
	
	public Products(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		js = (JavascriptExecutor) driver;
	}
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cart;
	
	@FindBy(xpath="//div[2]/button")
	WebElement addToCart;
	
	
	
	public boolean checkCart(int cartVal) {
		cart.isDisplayed();
		if(cartVal == 0) {
			try {
		return driver.findElement(By.xpath("//a[@class='shopping_cart_link']/span")).isDisplayed();
			}catch(org.openqa.selenium.NoSuchElementException e ) {
				return false;
			}
		
		}
		else
			js.executeScript("arguments[0].scrollIntoView(true)",addToCart);
			return driver.findElement(By.xpath("//a[@class='shopping_cart_link']/span[text()="+cartVal+"]")).isDisplayed();
		
	}
	
	
	public String addElement(int index) throws InterruptedException {
		// Assuming 'driver' is already initialized as a WebDriver instance
		WebElement productDetails = driver.findElement(By.xpath("(//div[@class='inventory_item_description'])["+index+"]"));
		
		

		// Now you can use the 'js' object to execute JavaScript
		js.executeScript("arguments[0].scrollIntoView(true)",productDetails);
		// Example: Scroll down by 1000 pixels
		waitForElementToAppear(By.xpath("(//div[@class='inventory_item_description'])["+index+"]"));
		
		productDetails.findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory ']")).click();
		Thread.sleep(5);
		System.out.println(productDetails.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));
		return productDetails.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1);

	}
	
	public String verifyProductAdded(int index) {
		WebElement productDetails = driver.findElement(By.xpath("(.//div[@class='inventory_item_description'])["+index+"]"));
		
		return productDetails.findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory ']")).getText();
	}
	
	

}
