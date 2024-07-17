package com.datadive.pages;

//import io.qameta.allure.Step;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class WelcomePage extends BasePageObject{

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }


    private String pageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private By usernameInput = By.xpath("//input[@name='username']");
    private By passwordInput = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[text()=' Login ']");
    private By forgotPasswordButton = By.xpath("//p[text()='Forgot your password? ']");
    private By resetPasswordButton = By.xpath("//button[text()=' Reset Password ']");
    private By resetPasswordMessage = By.xpath("//h6[text()='Reset Password link sent successfully']");
    private By loginPageLogo = By.xpath("//div[@class='orangehrm-login-logo']");
    private By invalidCredentialsText = By.xpath("//p[text()='Invalid credentials']");
    private By requiredMessage = By.xpath("//span[text()='Required']");

    /** Open WelcomePage with it's url */
    @Step("Open page url")
    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    @Step("Log in using credentials")
    public void logIn(String username, String password){
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameInput);
        type(password, passwordInput);
        click(loginButton);
    }

    @Step("Log in using incorrect login credentials")
    public void negativeLogIn(String username, String password) {
        log.info("Executing Negative LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameInput);
        type(password, passwordInput);
        click(loginButton);
        waitForVisibilityOf(invalidCredentialsText,5);
    }

    @Step("Verify forgot functionality")
    public void forgotPasswordFunctionality(String userName){
        log.info("Clicking on Forgot password button");
        click(forgotPasswordButton);
        type(userName,usernameInput);
        click(resetPasswordButton);
        log.info("Clicking on Reset password button");
    }

    @Step("Get login button text")
    public String getLoginButtonText(){
        log.info("Get text for login button");
        String loginText = getText(loginButton);
        return loginText;
    }

    @Step("Get text 'Forgot password'")
    public String getForgotPasswordText(){
        log.info("Get text of Forgot password button");
        String forPassText = getText(forgotPasswordButton);
        return forPassText;
    }

    public String getInvalidCredentialText(){
        log.info("Get text for invalid credential");
        String invCredText = getText(invalidCredentialsText);
        return invCredText;
    }

    public String getResetPasswordSuccessfullyText(){
        log.info("Get text for resetting password successfully");
        String passSuccessText = getText(resetPasswordMessage);
        return passSuccessText;
    }

    @Step("Verify logo")
    public void verifyingLogo(){
        log.info("Verifying if logo present on the page");
        waitForVisibilityOf(loginPageLogo,5);
        verifyingElementIsPresent(loginPageLogo,"Logo is not present on the page");
    }

    @Step("Wait for element 'Reset password' to be present")
    public void waitForResetPasswordElement(){
        log.info("Wait for Reset successful password message");
        waitForVisibilityOf(resetPasswordMessage,5);
    }

    @Step("Verifying message is correct for empty case of username and password")
    public void verifyRequiredTextPresent(String password,String username){
        log.info("Verifying message is correct for empty case of username and password");
        log.info("Executing Negative LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameInput);
        type(password, passwordInput);
        click(loginButton);
        List<WebElement> list = new ArrayList<>();
        list = driver.findElements(requiredMessage);
        waitForVisibilityOf(requiredMessage,5);
        for(WebElement text : list){
            Assert.assertEquals(text.getText(),"Required", "Message incorrect or does not exist");
        }
    }


}
