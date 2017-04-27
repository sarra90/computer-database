package com.excilys.controllers;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class DashboardControllerTest {

	private WebDriver driver;
	
	@Before
	public void beforeMethod(){
		

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
	public void testNumberOfComputers(){

		System.out.println(driver.findElement(By.id("homeTitle")).getText());
		Assert.assertEquals(driver.findElement(By.id("homeTitle")).getText(), "574");
		
	}
}
