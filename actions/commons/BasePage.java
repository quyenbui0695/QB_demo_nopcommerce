package commons;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);

	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
		;
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPrecence(WebDriver driver) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTimeOut);
		return explicitwait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPrecence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPrecence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPrecence(driver).getText();
	}

	public void senkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPrecence(driver).sendKeys(textValue);
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	public List<WebElement> getListElements(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}

	public void senkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(textItem);

	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();

	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();

	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);

		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getAtributeValue(WebDriver driver, String xpathLocator, String atributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(atributeName);

	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);

	}

	public String getHexaColorFromRGBA(String agbaValue) {
		return Color.fromString(agbaValue).asHex();

	}

	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListElements(driver, xpathLocator).size();

	}

	public void checkTheCheckBoxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);

		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckTheCheckBox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);

		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {

		return getWebElement(driver, xpathLocator).isDisplayed();

	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {

		return getWebElement(driver, xpathLocator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {

		return getWebElement(driver, xpathLocator).isEnabled();
	}

	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {

		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	public void switchToDefaultContent(WebDriver driver) {

		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);

		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);

		action.doubleClick(getWebElement(driver, xpathLocator)).perform();
	}

	public void uploadFile(WebDriver driver, String xpathLocator) throws InterruptedException {

		String projectPath = System.getProperty("user.dir");
		String file1 = "1.jpg";
		String file2 = "2.jpg";
		String file3 = "3.jpg";
		String file4 = "4.jpg";

		// đường dẫn upload file

		String uploadFileFolderPath = projectPath + File.separator + "upload_files" + File.separator;
		String path1 = uploadFileFolderPath + file1;
		String path2 = uploadFileFolderPath + file2;
		String path3 = uploadFileFolderPath + file3;
		String path4 = uploadFileFolderPath + file4;
		By uploadfilebutton = getByXpath(xpathLocator);
		driver.findElement(uploadfilebutton).sendKeys(path1 + "\n" + path2 + "\n" + path3 + "\n" + path4);

		Thread.sleep(3000);

		// Click upload element at each file:
		List<WebElement> uploadButton = getListElements(driver, xpathLocator);
		for (WebElement button : uploadButton) {
			button.click();
			;
			Thread.sleep(2000);

		}
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xpathLocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitforElementVisible(WebDriver driver, String xpathLocator) {

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));

	}

	public void waitforAllElementsVisible(WebDriver driver, String xpathLocator) {

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);

		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	public void waitforElementInvisible(WebDriver driver, String xpathLocator) {

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));

	}

	public void waitforAllElementsInvisible(WebDriver driver, String xpathLocator) {

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);

		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, xpathLocator)));
	}

	public void waitforElementClickable(WebDriver driver, String xpathLocator) {

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);

		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private long longTimeOut = 30;
	
	
	
}