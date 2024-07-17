package com.datadiveTest.TestCase1;

import com.datadive.base.CsvDataProvider;
import com.datadive.base.TestUtilities;
import com.datadive.pages.DashBoardPage;
import com.datadive.pages.LandingPage;
import com.datadive.pages.WelcomePage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginAndLandingPage extends TestUtilities {
    /*
    Test case 1.(login page)
Navigate to login page
Verify logo is present on login page
Verify Login text is correct
Enter login and password and click login button to check that login functionality works correctly
When logged in confirmed that user landed on dashboard page.
Confirm that 6 div boxes are present on the screen with the correct text 'Time at work', 'My Actions', 'Quick Launch', 'Buzz Latest Posts', 'Employees on Leave Today', 'Employee Distribution by Sub Unit', 'Employee Distribution by Location'.
Verify that 'Question' button is present on the screen in top right corner
Click on question button to verify Q&A window opened in another tab.
Confirm 'Forgot your password' text is correct and functionality is working
     */

    @Test
    @Description("Testing Successful log in and Dashboard functionality")
    public void SuccessLogin(){
        WelcomePage welcomePage = new WelcomePage(driver, log);

        welcomePage.openPage();
        //Verifying that logo is present on the screen
        welcomePage.verifyingLogo();
        takeScreenshot("Login Page");
        //Verifying that Login text is present and correct
        Assert.assertEquals(welcomePage.getLoginButtonText(),"Login", "Login Text is incorrect");
        welcomePage.logIn("Admin","admin123");
        LandingPage landingPage = new LandingPage(driver,log);
        //Login to the page and taking screenshot
        landingPage.waitForDashboardButton();
        takeScreenshot("Dashboard Landing page ");
        // Checking that Dashboard button is present and it's text is correct
        landingPage.checkDashBoardIsPresent();
        DashBoardPage dashBoardPage = new DashBoardPage(driver,log);
        //Confirm that 6 div boxes are present on the screen with the correct text 'Time at work', 'My Actions',
        // 'Quick Launch', 'Buzz Latest Posts', 'Employees on Leave Today', 'Employee Distribution by Sub Unit', 'Employee Distribution by Location'.
        dashBoardPage.verifyDashboardDivBoxes();
        dashBoardPage.verifyCorrectTextForDivBoxes();
        //Verify that 'Question' button is present on the screen in top right corner
        dashBoardPage.clickOnHelpBtnAndSwitchBack();
        takeScreenshot("Questions and Answer Tab");
        //Click on question button to verify Q&A window opened in another tab.
        dashBoardPage.switchBackToOriginalTab();
        takeScreenshot("Switching back to Dashboard tab");
        //Confirm 'Forgot your password' text is correct and functionality is working
        dashBoardPage.logOut();
        Assert.assertEquals(welcomePage.getForgotPasswordText(),"Forgot your password?", "Text is incorrect 'forgot password'");
        welcomePage.forgotPasswordFunctionality("John Doe");
        welcomePage.waitForResetPasswordElement();
        takeScreenshot("Password is reset");

    }

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProvider.class)
    public void negativeLogin(Map<String, String> data){
        WelcomePage welcomePage = new WelcomePage(driver,log);
        welcomePage.openPage();
        if(data.get("username").equals(" ") && data.get("password").equals(" ")){
            welcomePage.verifyRequiredTextPresent(data.get("username"),data.get("password"));
            takeScreenshot("For empty credentials we print 'Require'");
        }else{
            welcomePage.negativeLogIn(data.get("username"),data.get("password"));
            Assert.assertEquals(data.get("expectedMessage"),"Invalid credentials", "Incorrect message");
            takeScreenshot("Incorrect credentials message");
        }
    }
}
