package com.datadiveTest.TestCase3;

import com.datadive.base.TestUtilities;
import com.datadive.pages.LandingPage;
import com.datadive.pages.MyInfoPage;
import com.datadive.pages.WelcomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MyInfo extends TestUtilities {

    /*
    Test Case 3. (verify add functionality in section 'My info', subsection 'Emergency Contacts', 'Dependents', 'Qualifications')
Login into the orangeHRMS
Click on 'My info' on the left side menu.
In 'My info' click on 'Emergency Contacts'.
In 'Emergency Contacts' click on 'Add' button next to 'Assigned Emergency Contacts' and fill out all related fields (Name, Relationship, Home Telepone, Mobile, Work Telephone)
Verify that emergency contact was added.
In 'Emergency Contacts' click on 'add' button next to 'Attachments' and upload attachment
Click on 'Dependents', after click on 'add' button next to 'Assigned Dependents'
Filled out all the fields related to 'Add Dependent' and click 'Save' button
Verify that dependant was added
Click on 'Qualifications', after click on 'add' button next to 'Work Experience'
Fill out all the fields related to 'Work Experience' and click 'save' button.
Verify that experience was added.
     */


    @Test
    public void MyInfoPageFunctionality() throws InterruptedException {
        // Login into the orangeHRMS
        WelcomePage welcomePage = new WelcomePage(driver,log);
        welcomePage.openPage();
        takeScreenshot("Login_Page");
        welcomePage.logIn("Admin","admin123");
        LandingPage landingPage = new LandingPage(driver,log);
        takeScreenshot("Landing_page");
        //Click on 'My info' on the left side menu.
        landingPage.selectMyInfoTab();
        takeScreenshot("MyInfo_tab");
        //In 'Emergency Contacts' click on 'Add' button next to 'Assigned Emergency Contacts' and fill out all related fields (Name, Relationship, Home Telepone, Mobile, Work Telephone)
        MyInfoPage myInfoPage = new MyInfoPage(driver,log);
        myInfoPage.openEmergencyContactSection();
        takeScreenshot("Emergency_section");
        myInfoPage.clickOnAddButton(1);
        List<String> text = new ArrayList<>();
        text.add("John");
        text.add("Friend");
        text.add("777-888-9999");
        text.add("345-345-3455");
        text.add("787-787-7878");
        myInfoPage.submitInformation(text);
        takeScreenshot("Adding_information_to_emergency_section");
        myInfoPage.clickOnSaveButton();
        takeScreenshot("Submitted_information");
        //Verify that emergency contact was added.
        myInfoPage.assertCorrectInformation(text);
        //Click on 'Dependents', after click on 'add' button next to 'Assigned Dependents'
        myInfoPage.openDependentsSection();
        takeScreenshot("Dependent_section");
        myInfoPage.clickOnAddButton(1);
        myInfoPage.submitInformationInDependent("Sonny");
        myInfoPage.selectRelationshipDropDown("Child");
        myInfoPage.handleCalendar(2002,2,25,1);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        takeScreenshot("Adding_information_to_dependent_section");
        myInfoPage.clickOnSaveButton();
        takeScreenshot("Submitting_information_to_dependent_section");
        List<String> list = new ArrayList<>();
        list.add("Sonny");
        list.add("Child");
        list.add("2024-14-06");
        //Verify that dependant was added
        myInfoPage.assertCorrectInformation(list);
        //Click on 'Qualifications', after click on 'add' button next to 'Work Experience'
        myInfoPage.openQualificationsSection();
        takeScreenshot("Qualification_section");
        myInfoPage.clickOnAddButton(1);
        //Fill out all the fields related to 'Work Experience' and click 'save' button.
        myInfoPage.submitInformationInQualifications("Shield","QA");
        myInfoPage.handleCalendar(2023,3,5,1);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        myInfoPage.handleCalendar(2024,5,20,2);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        takeScreenshot("Adding_information_to_qualification_section");
        //myInfoPage.submitComment("Great work");
        myInfoPage.clickOnSaveButton();
        takeScreenshot("Submitting_information_to_qualification_section");
        //Verify that experience was added.
        List<String> qualifications = new ArrayList<>();
        qualifications.add("Shield");
        qualifications.add("QA");
        qualifications.add("2023-03-05");
        qualifications.add("2024-05-20");
        myInfoPage.assertCorrectInformation(qualifications);
    }
}
