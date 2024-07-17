package com.datadive.pages;

import org.openqa.selenium.*;

import org.slf4j.Logger;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class TimePage extends BasePageObject {

    public TimePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By timesheetsDropDown = By.xpath("//span[text()='Timesheets ']");
    private By myTimeSheetButton = By.xpath("//a[text()='My Timesheets']");
    private By employeeTimesheet = By.xpath("//a[text()='Employee Timesheets']");
    private By noTimesheetMessage = By.xpath("//p[text()='No Timesheets Found']");
    private By editButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost']");
    private By dateFields = By.xpath("//input[@class='oxd-input oxd-input--active']");
    private By projectInputField = By.xpath("//input[@placeholder='Type for hints...']");
    private By activityInputField = By.xpath("//div[@class='oxd-select-wrapper']");
    private By saveButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    private By createTimesheetButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    private By futureDevelopmentButton = By.xpath("//span[text()='Feature Development']");
    private By submitButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    private By divBoxOfPerformedAction = By.xpath("//div[@class='oxd-table-cell oxd-padding-cell']/div");
    private By timeSheetTable = By.xpath("//table[@class='orangehrm-timesheet-table']/tbody/tr");
    private By allFoundRecordInformation = By.xpath("//div[@class='oxd-table-cell oxd-padding-cell']");
    private By projectApacheButton = By.xpath("//span[text()='Apache Software Foundation - ASF - Phase 1']");
    private By editTimesheetTitle = By.xpath("//div[@class='orangehrm-timesheet-header--title']");
    private By viewButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--text oxd-table-cell-action-space']");

    //select my timesheet in time tab
    public void selectMyTimeSheet(){
        log.info("Select my timesheet");
        waitForVisibilityOf(timesheetsDropDown,5);
        click(timesheetsDropDown);
        waitForVisibilityOf(myTimeSheetButton,5);
        click(myTimeSheetButton);
    }
    //Select employees timesheet in time tab
    public void selectEmployeeTimesheet(){
        log.info("Select employee timesheet");
        waitForVisibilityOf(timesheetsDropDown,5);
        click(timesheetsDropDown);
        waitForVisibilityOf(employeeTimesheet,5);
        click(employeeTimesheet);
    }
    //Edit timesheet if no timesheet exist create one
    public void editMytimeSheet() {
        log.info("Edit timesheet");
        if(ifElementIsPresent(noTimesheetMessage)){
            waitForVisibilityOf(createTimesheetButton,5);
            click(createTimesheetButton);
            waitForVisibilityOf(editButton,5);
            click(editButton);
        }else{
            waitForVisibilityOf(editButton,5);
            click(editButton);
        }
    }
    //Entering required fields to create timesheet
    public void enterTimeForTimesheet(String project,String activity, String time) {
        log.info("Create timesheet");
        if(ifElementIsPresent(projectApacheButton)){
            if(ifElementIsPresent(submitButton)){
                click(submitButton);
            }
            return;
        }
        log.info("Enter project: "+project+", activity: "+activity+" and time:"+time);
        waitForVisibilityOf(projectInputField,5);
        type(project,projectInputField);
        click(editTimesheetTitle);
        type(project,projectInputField);
        click(projectApacheButton);
        click(activityInputField);
        click(futureDevelopmentButton);
        List<WebElement> elements = driver.findElements(dateFields);
        for(int i = 1; i < elements.size()-2; i++){
           elements.get(i).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
           elements.get(i).sendKeys(time);
        }
        waitForVisibilityOf(saveButton,5);
        click(saveButton);
        if(ifElementIsPresent(submitButton)){
            waitForVisibilityOf(submitButton,5);
            click(submitButton);
        }
    }

    //Verifying that timesheet information which we just created is correct.Pass a list of things which we want to verify
    public void verifyEnteredInformation(List<String> list){
        log.info("Verifying correct information in timesheet");
        List<WebElement> elements = driver.findElements(divBoxOfPerformedAction);
        for(int i=0;i<elements.size()-1;i++) {
            Assert.assertEquals(elements.get(i).getText(),list.get(i),"Timesheet is incorrect");
            log.info("Expected: "+list.get(i)+" Actual: "+(elements.get(i).getText()));
        }
    }

    //Verifying That project and activity is correct in timesheet
    public List<WebElement> firstRowOfCreatedTimeSheet(String expectedTextProject,String expectedTextActivity) throws InterruptedException {
        log.info("Get first row of created timesheet");
        waitForVisibilityOf(timeSheetTable,10);
        List<WebElement> rows = driver.findElements(timeSheetTable);
        List<WebElement> tableBoxes = new ArrayList<>();
        String text="";
        for(int i=0;i<rows.size()-1;i++){
                tableBoxes = driver.findElements(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody/tr["+(i+1)+"]/td"));
                Assert.assertEquals(tableBoxes.get(0).getText(),expectedTextProject, "Text in the timesheet is incorrect");
                Assert.assertEquals(tableBoxes.get(1).getText(),expectedTextActivity, "Text in the timesheet is incorrect");
        }
        return tableBoxes;
    }

    //Get date and time of the timesheet when it was created
    public List<String> getRecordDateAndTime(){
        log.info("Get record date and time");
        List<WebElement> record = driver.findElements(allFoundRecordInformation);
        List<String> recordText = new ArrayList<>();
        for(int i=1; i<record.size(); i=+3 ){
            recordText.add(record.get(i).getText());
        }
        return recordText;
    }

    // Assert date of the time list when it was created
    public void assertRecordList(String date){
        log.info("Asserting date when timesheet was created");
        for(String element : getRecordDateAndTime()){
            if(date.equals(element)){
                Assert.assertEquals(element,date,"No records found");
            }
        }
    }

    public void clickOnViewButton(int numberButton) throws InterruptedException {
        log.info("Clicking on view button");
        waitForVisibilityOf(viewButton,5);
        List<WebElement> elements = driver.findElements(viewButton);
        if (numberButton<elements.size()) {
            elements.get(numberButton - 1).click();
        }
    }

}
