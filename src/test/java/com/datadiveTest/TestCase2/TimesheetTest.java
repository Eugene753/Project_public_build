package com.datadiveTest.TestCase2;

import com.datadive.base.TestUtilities;
import com.datadive.pages.LandingPage;
import com.datadive.pages.TimePage;
import com.datadive.pages.WelcomePage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TimesheetTest extends TestUtilities {
    /*
    Test case 2. (Timesheet)
Login into the orageHRMS
Navigate to tab 'time' located on the left side of the app.
In the tab 'time' click on 'Timesheets' and in drop down select 'My timesheet'
If 'No timesheet found' warning is present click on 'Create timesheet'.If timesheet was created but not edited, click on 'edit'
Submit all required fields and click 'Save' button. In 'Project' select 'Apache software foundation' in Activity select 'Future Development' and for 'date' input fields enter dates except for Saturday and Sunday.
After it's 'Saved' click submit and verify that it was submitted and Person name by whom if was performed.
In 'Timesheet' select 'Employee timesheet'.
From the table of Found records select created one and click 'View' and verify that 'Project' and 'Activity' is correct.
Note: if element is keep disappearing from the DOM use ctrl+shift+p
Do the following:
Open the console and navigate to Elements tab
Type command + shift + P (OSX) or control + shift + P (Windows)
Type the word focused
Select Emulate a focused page from the menu
Now clicking around in the console will not close the element.
     */
    @Test
    public void Timesheetfunctionality() throws InterruptedException {
        WelcomePage welcomePage = new WelcomePage(driver, log);
        //Login into the orageHRMS
        welcomePage.openPage();
        takeScreenshot("Welcome_page");
        welcomePage.logIn("Admin","admin123");
        LandingPage landingPage = new LandingPage(driver,log);
        //Navigate to tab 'time' located on the left side of the app.
        landingPage.selectTimeTab();
        takeScreenshot("Time_Page_tab");
        TimePage timePage = new TimePage(driver,log);
        //In the tab 'time' click on 'Timesheets' and in drop down select 'My timesheet'
        timePage.selectMyTimeSheet();
        takeScreenshot("Select_my_timesheet");
        //If 'No timesheet found' warning is present click on 'Create timesheet'.If timesheet was created but not edited, click on 'edit'
        timePage.editMytimeSheet();
        //Submit all required fields and click 'Save' button. In 'Project' select 'Apache software foundation' in Activity select 'Future Development' and for 'date' input fields enter dates except for Saturday and Sunday.
        timePage.enterTimeForTimesheet("A","Feature Development","5");
        takeScreenshot("Fill_out_timesheet");
        //After it's 'Saved' click submit and verify that it was submitted and Person name by whom if was performed.
        List<String> list = new ArrayList<>();
        list.add("Submitted");
        list.add("manda user");
        list.add("2024-25-06");
        timePage.verifyEnteredInformation(list);
        takeScreenshot("Verifying_entered_information_in_timesheet");
        //In 'Timesheet' select 'Employee timesheet'.
        timePage.selectEmployeeTimesheet();
        takeScreenshot("Select_employees_timesheet");
        timePage.clickOnViewButton(1);
        //From the table of Found records select created one and click 'View' and verify that 'Project' and 'Activity' is correct.
        timePage.firstRowOfCreatedTimeSheet("Apache Software Foundation - ASF - Phase 1","Feature Development");
        takeScreenshot("Created_employee_timesheet");
    }

}
