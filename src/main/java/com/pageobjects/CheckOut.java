package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Components;

public class CheckOut extends Components {
	WebDriver driver;
	public CheckOut(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='summary_subtotal_label']")
	WebElement itemTotal;
	
	@FindBy(xpath="//div[@data-test='tax-label']")
	WebElement itemTax;
	
	@FindBy(xpath="//div[@data-test='total-label']")
	WebElement total;
	
	@FindBy(xpath="//button[@name='finish']")
	WebElement finishButton;
	
	@FindBy(xpath="//h2[@class='complete-header']")
	WebElement orderPlacedMessage;
	
	By img = By.xpath("//img[@class='pony_express']");
	
	
	
	public float verifyPrice() throws InterruptedException {
		System.out.println("Hello");
		System.out.println("VerifyPrice"+itemTotal.getText());
		Thread.sleep(5);
		return Float.parseFloat(itemTotal.getText().split(" ")[2].substring(1));
	}
	
	public float addTaxes() {
		return Float.parseFloat(itemTax.getText().split(" ")[1].substring(1));
		
	}
	
	public float verifyPriceTaxes() {
		System.out.println(total.getText());
		return Float.parseFloat(total.getText().split(" ")[1].substring(1));
	}
	
	public String verifyOrderPlaced() {
		finishButton.click();
		waitForElementToAppear(img);
		return orderPlacedMessage.getText();
		
	}

}
