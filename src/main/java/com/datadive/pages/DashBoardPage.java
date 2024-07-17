package com.datadive.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class DashBoardPage extends BasePageObject{

    private By dashboardBoxes = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']");
    private By questionButton = By.xpath("//button[@class='oxd-icon-button']");
    private By dropDownUserButton = By.xpath("//span[@class='oxd-userdropdown-tab']");
    //P elements to get the text
    private By timeToWork = By.xpath("//p[text()='Time at Work']");
    private By myActions = By.xpath("//p[text()='My Actions']");
    private By quickLaunch = By.xpath("//p[text()='Quick Launch']");
    private By buzzLatestPost = By.xpath("//p[text()='Buzz Latest Posts']");
    private By employeesOnLeave = By.xpath("//p[text()='Employees on Leave Today']");
    private By employeeDistrubtBySubUnit = By.xpath("//p[text()='Employee Distribution by Sub Unit']");
    private By empDistribByLocation = By.xpath("//p[text()='Employee Distribution by Location']");
    private By logoutButton = By.xpath("//a[text()='Logout']");

    public DashBoardPage(WebDriver driver, Logger log) {
        super(driver, log);
    }


    public void verifyDashboardDivBoxes(){
        log.info("Verify all div boxes exist in Dashboard page");
        List<WebElement> divBoxes = findAll(dashboardBoxes);
        Assert.assertFalse(divBoxes.isEmpty(), "No boxes are displayed, Dashboard is empty");

        for(WebElement box : divBoxes){
            Assert.assertTrue(box.isDisplayed(), "Dashboard box is not displayed");
        }
    }

    public void clickOnHelpBtnAndSwitchBack(){
        log.info("Clicking on help button to switch to questions and answers");
        click(questionButton);
        switchToWindowWithUrl("https://starterhelp.orangehrm.com/hc/en-us");
    }

    public void switchBackToOriginalTab(){
        log.info("Switching back to original tab");
        switchToWindowWithUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    public void verifyCorrectTextForDivBoxes(){
        log.info("Asserting text for div boxes in Dashboard page");
        List<String> boxesText = new ArrayList<>();
        boxesText.add(getText(timeToWork));
        boxesText.add(getText(myActions));
        boxesText.add(getText(quickLaunch));
        boxesText.add(getText(buzzLatestPost));
        boxesText.add(getText(employeesOnLeave));
        boxesText.add(getText(employeeDistrubtBySubUnit));
        boxesText.add(getText(empDistribByLocation));
        List<String> text = new ArrayList<>();
        text.add("Time at Work");
        text.add("My Actions");
        text.add("Quick Launch");
        text.add("Buzz Latest Posts");
        text.add("Employees on Leave Today");
        text.add("Employee Distribution by Sub Unit");
        text.add("Employee Distribution by Location");

        for(int i = 0; i < boxesText.size(); i++){
            Assert.assertEquals(boxesText.get(i),text.get(i),"Incorrect text");
        }
    }

    public void logOut(){
        log.info("User log out");
        click(dropDownUserButton);
        click(logoutButton);
    }

}
