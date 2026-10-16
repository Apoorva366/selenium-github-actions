package com.seleniumproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        File file = new File("webpage/login.html");
        driver.get(file.toURI().toString());
    }

    @Test
    public void validLoginTest() {
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.tagName("button")).click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(
            message.getText(),
            "Login Successful"
        );
    }

    @Test
    public void invalidLoginTest() {
        driver.findElement(By.id("username")).sendKeys("hello");
        driver.findElement(By.id("password")).sendKeys("111");
        driver.findElement(By.tagName("button")).click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(
            message.getText(),
            "Invalid Username or Password"
        );
    }

    @Test
    public void backgroundColorTest() {
        WebElement loginBox = driver.findElement(By.id("loginBox"));

        String color = loginBox.getCssValue("background-color");

        Assert.assertEquals(color, "rgba(173, 216, 230, 1)");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
