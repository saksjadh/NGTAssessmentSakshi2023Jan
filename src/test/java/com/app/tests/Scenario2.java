package com.app.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.app.pages.Login;

//import pageFactory.loginPage;

public class Scenario2 {
	WebDriver driver=null;
	Login login = new Login(driver);
	
	@BeforeMethod(alwaysRun=true)
	public void setup() {
		String rootFolder = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",rootFolder+"\\src\\test\\resources\\chromedriver.exe");
	    driver = new ChromeDriver();	
	}
	
  @Test
  public void login() throws Exception {
	  Login login = new Login(driver);
	  Properties propObj=new Properties();
	  String rootFolder = System.getProperty("user.dir"); 
      propObj.load(new FileInputStream(rootFolder+ "//src//test//resources//login.properties"));
      driver.get(propObj.getProperty("LoginUrl"));
      Thread.sleep(1000);
      login.enterEmail(propObj.getProperty("Email"));
      login.enterPassword(propObj.getProperty("Pass"));
      Thread.sleep(1000);
      String expectedTitle="Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra";
      String actualTitle = driver.getTitle();
//      System.out.println(actualTitle);
    
      Assert.assertEquals(actualTitle, expectedTitle,"Home page not loaded");
      Thread.sleep(2000);
      WebElement prof = driver.findElement(By.xpath("//span[.=\"Profile\"]"));
      
      if(prof.isDisplayed()) {
    	  System.out.println("You are Successfully Logged in");
      }

    
  }
  @AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}


}
