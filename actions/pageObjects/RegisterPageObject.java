package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

	}

	public String getErrorTextOfFirstNameTextbox() {
		return getElementText(driver, RegisterPageUI.ERROR_TEXT_EMPTY_FIRSTNAME);
	}

	public String getErrorTextOfLasttNameTextbox() {
		return getElementText(driver, RegisterPageUI.ERROR_TEXT_EMPTY_LASTNAME);
	}

	public String getErrorTextOfEmailTextbox() {
		// TODO Auto-generated method stub
		return getElementText(driver, RegisterPageUI.ERROR_TEXT_EMPTY_EMAIL);
	}

	public String getErrorTextOfPasswordTextbox() {
		// TODO Auto-generated method stub
		return getElementText(driver, RegisterPageUI.ERROR_TEXT_EMPTY_PASSWORD);
	}

	public String getErrorTextOfConfirmPasswordTextbox() {
		// TODO Auto-generated method stub
		return getElementText(driver, RegisterPageUI.ERROR_TEXT_EMPTY_CONFIRM_PASSWORD);
	}

}
