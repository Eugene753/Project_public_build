package com.datadive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.Assert;
import io.qameta.allure.Step;

public class LandingPage extends BasePageObject{

    public LandingPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By searchInputField = By.xpath("//input[@placeholder='Search']");
    private By adminButton = By.xpath("//span[text()='Admin']");
    private By PIMButton = By.xpath("//span[text()='PIM']");
    private By leaveButton = By.xpath("//span[text()='Leave']");
    private By timeButton = By.xpath("//span[text()='Time']");
    private By recruitmentButton = By.xpath("//span[text()='Recruitment']");
    private By myInfoButton = By.xpath("//span[text()='My Info']");
    private By performanceButton = By.xpath("//span[text()='Performance']");
    private By dashboardPageButton = By.xpath("//span[text()='Dashboard']");
    private By directoryButton = By.xpath("//span[text()='Directory']");
    private By maintenanceButton = By.xpath("//span[text()='Maintenance']");
    private By claimButton = By.xpath("//span[text()='Claim']");
    private By buzzButton = By.xpath("//span[text()='Buzz']");

    public void search(String find){
        type(find,searchInputField);
    }

    @Step("Testing is Dashboard is present")
    public void checkDashBoardIsPresent(){
        log.info("Check that landing page for website is Dashboard");
        waitForVisibilityOf(dashboardPageButton,5);
        verifyingElementIsPresent(dashboardPageButton, "Dashboard button is not present");
        Assert.assertEquals(getText(dashboardPageButton),"Dashboard", "Text is incorrect");
    }

    @Step("Waiting for Dashboard button to be present")
    public void waitForDashboardButton(){
        log.info("Waiting for visibility of Dashboard button");
        waitForVisibilityOf(dashboardPageButton,5);
    }

    @Step("Select Time tab")
    public void selectTimeTab(){
        log.info("Selecting Time tab from the menu");
        waitForVisibilityOf(timeButton,5);
        click(timeButton);
    }

    @Step("Select My info tab")
    public void selectMyInfoTab(){
        log.info("Select time tab from the menu");
        waitForVisibilityOf(myInfoButton,5);
        click(myInfoButton);
    }

}
