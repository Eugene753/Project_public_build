package com.datadive.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtilities extends BaseTest{

    // STATIC SLEEP
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Todays date in yyyyMMdd format */
    private static String getTodaysDate() {
        return (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    /** Current time in HHmmssSSS */
    private String getSystemTime() {
        return (new SimpleDateFormat("HH-mm-ss-SSS").format(new Date()));
    }

    /** Take screenshot */
    public void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodaysDate()
                + File.separator + testSuiteName
                + File.separator + testName
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*// Alternative method to take screenshots
    public static void takeScreenshot(WebDriver driver, String fileName) {
        // Generate timestamp for unique screenshot file names
        String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());
        // Capture screenshot as file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Copy file to desired location
            FileUtils.copyFile(srcFile, new File("screenshots/" + fileName + "_" + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */

}
