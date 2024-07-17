package com.datadive.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    /** Open page with given URL */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /** Find element using given locator */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /** Find all elements using given locator */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Duration timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(condition);
    }

    /**
     * Wait for given number of seconds for element with given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                int timeout = (timeOutInSeconds.length > 0 && timeOutInSeconds[0] != null) ? timeOutInSeconds[0] : 30;
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), Duration.ofSeconds(timeout));
                break;
            } catch (StaleElementReferenceException e) {
                // Optional: Add logging or handling for the exception
            }
            attempts++;
        }
    }

    /** Click on element with given locator when its visible */
    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    /** Type given text into element with given locator */
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 5);
        log.info("Clear input field");
        find(locator).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        log.info("Type in input field");
        find(locator).sendKeys(text);

    }

    public String getText(By locator){
        waitForVisibilityOf(locator, 5);
        // Find the element using the provided locator
        WebElement element = driver.findElement(locator);
        // Return the text of the element
        return element.getText();
    }

    // pass expected text and locator
    // command will get text actual text from the locator
    // TO DO: may remove this command
    public void assertText(String expectedText, By locator) {
        String actualText = getText(locator);
        // Assert that the text is as expected
        Assert.assertEquals(actualText, expectedText, "Text does not match!");
    }

    /** Get title of current page */
    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public void switchToWindowWithUrl(String expectedUrl) {
        // Switching to new window
        String firstWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();

        for (String allWindow : allWindows) {
            String windowHandle = allWindow.toString();
            if (!windowHandle.equals(firstWindow)) {
                driver.switchTo().window(windowHandle);
                if (getCurrentPageUrl().equals(expectedUrl)) {
                    log.info("Switched to expected tab "+expectedUrl);
                    return;
                }
            }
        }
        log.info("Expected tab not found switch back to original window "+firstWindow);
        // If the window with the expected URL is not found, switch back to the original window
        driver.switchTo().window(firstWindow);
    }


    public void verifyingElementIsPresent(By locator, String message){
        log.info("Verifying if element is present on the page");
        List<WebElement> elements = driver.findElements(locator);
        Assert.assertTrue(elements.size() > 0, message);
    }

    public boolean ifElementIsPresent(By locator){
        List<WebElement> elements = driver.findElements(locator);
        return elements.size() != 0? true : false;
    }

    public void selectDropDown(By locator, String text){
        WebElement webElement = driver.findElement(locator);
        Select dropdown = new Select(webElement);
        dropdown.selectByVisibleText(text);
    }



}
