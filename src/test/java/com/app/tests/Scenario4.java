package com.app.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Scenario4 {
	
	WebDriver driver=null;
	
	@BeforeMethod(alwaysRun=true)
	public void setup() {
		String rootFolder = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",rootFolder+"\\src\\test\\resources\\chromedriver.exe");
	    driver = new ChromeDriver();	
	}
	
  @Test
  public void f() throws FileNotFoundException, IOException, InterruptedException {
      driver.manage().window().maximize();

	  Properties propObj=new Properties();
	  String rootFolder = System.getProperty("user.dir");
      propObj.load(new FileInputStream(rootFolder+ "//src//test//resources//login.properties"));
      driver.get(propObj.getProperty("LoginUrl"));
      Thread.sleep(1000);
      
      driver.findElement(By.xpath("//input[@id=\"mobileNumberPass\"]")).sendKeys(propObj.getProperty("Email"));
      driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(propObj.getProperty("Pass"));
      Thread.sleep(1000);
      driver.findElement(By.xpath("//button[.=\"LOGIN\"]")).click();
      Thread.sleep(35000);
      driver.findElement(By.xpath("//button[.=\"LOGIN\"]")).click();
      Thread.sleep(5000);
      driver.findElement(By.xpath("//a[.='Men']")).click();
      
      WebElement Bag = driver.findElement(By.xpath("//span[.=\"Bag\"]"));
      
      String num = driver.findElement(By.xpath("//span[@data-reactid=\"904\"]")).getText();
//      System.out.println(num);
      if (num == "") {
    	  driver.findElement(By.xpath("//input[@class=\"desktop-searchBar\"]")).sendKeys("towel");
          Thread.sleep(1000);
          driver.findElement(By.xpath("//input[@class=\"desktop-searchBar\"]")).sendKeys(Keys.ENTER);
          
          driver.findElement(By.xpath("(//h3[.=\"Jockey\"])[1]")).click() ;
          Thread.sleep(3000);
          String currentHandle = driver.getWindowHandle();
          for (String handle : driver.getWindowHandles()) {
              if (!handle.equalsIgnoreCase(currentHandle)) {
                  driver.switchTo().window(handle);
              }
          }

          Thread.sleep(2000);
          //WebElement addToBagButton= driver.findElement(By.cssSelector("div.pdp-add-to-bag"));
          WebElement addToBagButton= driver.findElement(By.cssSelector("div.pdp-add-to-bag"));
          addToBagButton.click();
          int number = Integer.parseInt(num);
          if ( number==1) {
        	  System.out.println("You have added item in your cart");
          }
      }
      else {
    	  String expectedNumber = "6";
    	  String actualNumber= num;
    	  Assert.assertEquals(expectedNumber, actualNumber,"The number of items dont match");

    	  System.out.println("You have " +num+ " items in your cart");
      }
  }
  @AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}

}
