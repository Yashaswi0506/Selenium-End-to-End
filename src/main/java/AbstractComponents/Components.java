package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Components {
WebDriver driver;
WebDriverWait wait;
	
	public Components(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	//This functions waits until element is visible
	public void waitForElementToAppear(By element) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public boolean checkForAlert() {
		if(wait.until(ExpectedConditions.alertIsPresent())==null) {
			return false;
		}else {
			return true;
		}
	}
	
	



}
