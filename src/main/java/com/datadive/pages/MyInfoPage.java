package com.datadive.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class MyInfoPage extends BasePageObject {
    public MyInfoPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By personalDetailButton = By.xpath("//a[text()='Personal Details']");
    private By contactDetailsButton = By.xpath("//a[text()='Contact Details']");
    private By emergencyContactButton = By.xpath("//a[text()='Emergency Contacts']");
    private By dependentsButton = By.xpath("//a[text()='Dependents']");
    private By immigrationButton = By.xpath("//a[text()='Immigration']");
    private By jobButton = By.xpath("//a[text()='Job']");
    private By salaryButton = By.xpath("//a[text()='Salary']");
    private By reportToButton = By.xpath("//a[text()='Report-to']");
    private By qualificationsButton = By.xpath("//a[text()='Qualifications']");
    private By membershipsButton = By.xpath("//a[text()='Memberships']");
    private By addButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--text']");
    private By inputFields = By.xpath("//input[@class='oxd-input oxd-input--active']");
    private By saveButton = By.xpath("//button[text()=' Save ']");
    private By cancelButton = By.xpath("//button[text()=' Cancel ']");
    private By divBoxWithInformations = By.xpath("//div[@class='oxd-table-cell oxd-padding-cell']");
    private By dropDownRelationshipButton = By.xpath("//div[@class='oxd-select-text--after']");
    private By textInDropDownChild = By.xpath("//span[text()='Child']");
    private By textInDropDownOther = By.xpath("//span[text()='Other']");
    private By DOB = By.xpath("//div[@class='oxd-date-wrapper']");
    private By monthsDOB = By.xpath("//ul[@class='oxd-calendar-dropdown']/li");
    private By yearsDOB = By.xpath("//ul[@class='oxd-calendar-dropdown']/li");
    private By daysDOB = By.xpath("//div[@class='oxd-calendar-dates-grid']/div");
    private By dropDownMonthsButton = By.xpath("//div[@class='oxd-calendar-selector-month-selected']");
    private By dropDownYearsButton = By.xpath("//li[@class='oxd-calendar-selector-year']");
    private By commentSection = By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']");

    public void openEmergencyContactSection(){
        log.info("Opening emergency contacts section");
        waitForVisibilityOf(emergencyContactButton,5);
        click(emergencyContactButton);
    }

    public void openDependentsSection(){
        log.info("Opening Dependents tab");
        waitForVisibilityOf(dependentsButton,5);
        click(dependentsButton);
    }

    public void openQualificationsSection(){
        log.info("Opening qualifications tab");
        waitForVisibilityOf(qualificationsButton,5);
        click(qualificationsButton);
    }

    // Function to click on add button in My info section
    public void clickOnAddButton(int button){
        log.info("Clicking on add button");
        waitForVisibilityOf(addButton,5);
        List<WebElement> elements = driver.findElements(addButton);
        try {
            if (button <= elements.size()) {
                elements.get(button - 1).click();
                return;
            }
        }catch (NotFoundException msg) {
            log.info("Button is not present on the screen: "+msg);
        }
    }

    //Function to submit information into input fields
    public void submitInformation(List<String> text){
        log.info("Submit information into input fields");
        waitForVisibilityOf(inputFields,5);
        List<WebElement> inputFieldElements = driver.findElements(inputFields);
        int count = 0;
            for(int i = 1; i <inputFieldElements.size(); i++) {
                inputFieldElements.get(i).sendKeys(text.get(count));
                count++;
        }
        log.info("Incorrect input field number");
    }

    //Function to get text from input field. Start would be a starting point in array list and cut would cut amount of element in the array from the end
    public List<String> informationFromDivBoxes(){
        log.info("Get information from input boxes");
        List<String> information = new ArrayList<>();
        waitForVisibilityOf(divBoxWithInformations,5);
        List <WebElement> elements = driver.findElements(divBoxWithInformations);
        for (WebElement element : elements) {
            information.add(element.getText());
        }
        return information;
    }

    //Assert information provided in the List<Strings> to the information provided in input fields
    public void assertCorrectInformation(List<String> textToAssert){
        log.info("Asserting text in input fields");
        waitForVisibilityOf(divBoxWithInformations,5);
        List<String> boxes = informationFromDivBoxes();
        for(int i=0;i<textToAssert.size();i++){
            if(boxes.contains(textToAssert.get(i))){
                int index = boxes.indexOf(textToAssert.get(i));
                Assert.assertEquals(boxes.get(index),textToAssert.get(i),"Input text is incorrect verify information in My Info");
            }
        }
    }

    public void handleCalendar(int year, int month, int day, int calendarNumber){
        log.info("Select: "+month+" - "+year+" - "+day);
        waitForVisibilityOf(DOB,5);
        List<WebElement> dob = driver.findElements(DOB);
        dob.get(calendarNumber-1).click();
        waitForVisibilityOf(dropDownYearsButton,5);
        click(dropDownYearsButton);
        waitForVisibilityOf(yearsDOB,5);
        List<WebElement> years = driver.findElements(yearsDOB);
        String str = Integer.toString(year);
        //Less efficient way to look for an element in the array but could be used
        /*for(WebElement text :years) {
            String str1 = text.getText();
            if(str.equals(str1)) {
                text.click();
                break;
            }
        }*/
        //Alternative way to find an element in the Array, more efficient
        if(years.contains(str)){
            int index = years.indexOf(str);
            years.get(index).click();
        }
        waitForVisibilityOf(dropDownMonthsButton,5);
        click(dropDownMonthsButton);
        waitForVisibilityOf(monthsDOB,5);
        List<WebElement> months = driver.findElements(monthsDOB);
        months.get(month - 1).click();
        waitForVisibilityOf(daysDOB,5);
        List<WebElement> days = driver.findElements(daysDOB);
        days.get(day - 1).click();
    }

    //Function to click on Sava button
    public void clickOnSaveButton(){
        log.info("Clicking on save button");
        waitForVisibilityOf(saveButton,5);
        click(saveButton);
    }

    public void selectRelationshipDropDown(String relationship){
        log.info("Select Relationship");
        waitForVisibilityOf(dropDownRelationshipButton,5);
        click(dropDownRelationshipButton);
        if(relationship.equals("Child")){
            waitForVisibilityOf(textInDropDownChild,5);
            click(textInDropDownChild);
        }else {
            waitForVisibilityOf(textInDropDownOther);
            click(textInDropDownChild);
        }
    }

    public void submitInformationInDependent(String text){
        log.info("Submitting information into Dependent tab");
        waitForVisibilityOf(inputFields,5);
        List<WebElement> inputField = driver.findElements(inputFields);
        inputField.get(1).sendKeys(text);
    }

    public void submitInformationInQualifications(String company, String jobTitle){
        log.info("Submitting information for work experience qualifications tab. Company: "+company+". Job title: "+jobTitle);
        waitForVisibilityOf(inputFields,5);
        List<WebElement> inputField = driver.findElements(inputFields);
        inputField.get(1).sendKeys(company);
        inputField.get(2).sendKeys(jobTitle);
    }

    /*Element on the page is working manually but in automation the DOM is not loading it loading
    public void submitComment(String comment) throws InterruptedException {
        log.info("Submitting a comment");
        waitForVisibilityOf(commentSection,20);
        click(commentSection);
        type(comment,commentSection);
    }*/









}
