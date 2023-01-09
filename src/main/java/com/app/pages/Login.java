package com.app.pages;

 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

 

public class Login {
    WebDriver driver;

    public Login(WebDriver driver){
        this.driver = driver;
    }

    public void enterEmail(String s) {
        driver.findElement(By.id("mobileNumberPass")).sendKeys(s);
    }

    public void enterPassword (String s) {
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(s);
    }

    public void loginButton() throws Exception {
        driver.findElement(By.xpath("//button[@class='btn primary  lg block submitButton']")).click();
        Thread.sleep(32000);
        driver.findElement(By.xpath("//button[@class='btn primary  lg block submitButton']")).click();
        Thread.sleep(3000);

    }




 

}