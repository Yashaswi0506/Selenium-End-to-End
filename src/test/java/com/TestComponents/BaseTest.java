package com.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.pageobjects.DemoSite;
import com.pageobjects.Products;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public Properties prop;
	public WebDriver driver;
	public DemoSite demoSite;
	public Products product;

	public  WebDriver initializeDriver() throws IOException {
		// TODO Auto-generated method stub
		 prop = new Properties();
		 FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/GlobalComponents/Config.properties");
		 prop.load(fis);
		 String browserName = prop.getProperty("browser");
			//String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
			
			if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");

			driver = new ChromeDriver(options);
			
			//driver = new ChromeDriver();
			
			}
			else if (browserName.equals("firefox")) {
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver();
	        } 
			else if (browserName.equals("edge")) {
	            WebDriverManager.edgedriver().setup();
	            driver = new EdgeDriver();
	        } 
			else {
	            System.out.println("Invalid browser name specified.");
	            System.exit(1);
	        }
			
			//define implicit wait and timeouts 
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.manage().window().maximize();
			return driver;
		
		

	}
	
	public DemoSite launchSite() throws IOException{
		driver = initializeDriver();
		demoSite = new DemoSite(driver);
		System.out.println(prop.getProperty("url"));
		demoSite.goTo(prop.getProperty("url"));
		return demoSite;
	}

	public Products instantiateProducts() {
		return new Products(driver);
	}
}
