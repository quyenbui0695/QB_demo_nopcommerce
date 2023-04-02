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
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class PageObject_01 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	BasePage bagePage;
	String emailAddress;
	HomePageObject homePageObject;
	RegisterPageObject registerPageObject;



	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		bagePage = new BasePage();
		emailAddress = "mint" + generateFakeNumber() + "@hotmail.com";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePageObject = new HomePageObject(driver);
		registerPageObject = new RegisterPageObject(driver);

	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		homePageObject.clickToRegisterLink();
		registerPageObject.clickToRegisterButton();
		Assert.assertEquals(registerPageObject.getErrorTextOfFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPageObject.getErrorTextOfLasttNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPageObject.getErrorTextOfEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPageObject.getErrorTextOfPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPageObject.getErrorTextOfConfirmPasswordTextbox(), "Password is required.");
		
	}

	@AfterClass
	public void afterClass() {
	}

}
