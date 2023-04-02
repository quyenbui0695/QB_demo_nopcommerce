package com.rei.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Rei_ApplyBasePage_01 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	BasePage bagePage;
	String emailAddress;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		bagePage = new BasePage();
		emailAddress = "mint" + generateFakeNumber() + "@hotmail.com";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://opencart.abstracta.us/");

	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		bagePage.waitforElementClickable(driver, "//span[text()='My Account']");
		bagePage.clickToElement(driver, "//span[text()='My Account']");
		bagePage.clickToElement(driver, "//a[text()='Register']");
		bagePage.waitforElementClickable(driver, "//input[@class='btn btn-primary']");
		bagePage.clickToElement(driver, "//input[@class='btn btn-primary']");
		
		
		 Assert.assertEquals(bagePage.getElementText(driver, "//input[@id='input-firstname']/following-sibling::div"), "First Name must be between 1 and 32 characters!");
		
		
		
	}

	@AfterClass
	public void afterClass() {
	}

}
