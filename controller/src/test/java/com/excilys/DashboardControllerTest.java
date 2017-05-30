package com.excilys;

import static org.junit.Assert.assertEquals;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DashboardControllerTest {

    private WebDriver driver;

    @Before(value = "")
    public void beforeMethod() {

        System.setProperty("webdriver.chrome.driver", "/home/excilys/workspace/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/computerdatabase/dashboard");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNumberOfComputers() {

        System.out.println(driver.findElement(By.id("homeTitle")).getText());
        assertEquals(driver.findElement(By.id("homeTitle")).getText(), "574");

    }
}
