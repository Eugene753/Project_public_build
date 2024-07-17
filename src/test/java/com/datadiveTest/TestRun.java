package com.datadiveTest;

import com.datadive.base.BaseTest;
import com.datadive.base.BrowserDriverFactory;
import com.datadive.base.TestUtilities;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;


public class TestRun extends TestUtilities {



    @Test
    public synchronized void firstTryTestRunChrome(){

        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        takeScreenshot("Test run Chrome browser");
        //Check in what thread running this instance
        System.out.println("Running firstTryTestRunChrome in thread: " + Thread.currentThread().getId());
    }

    @Test
    public void firstTryTestRunFirefox(){

        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        takeScreenshot("Test run firefox browser");
        //Check in what thread running this instance
        System.out.println("Running firstTryTestRunChrome in thread: " + Thread.currentThread().getId());

    }

    @Test
    public synchronized void firstTryTestRunChromeHeadless(){

        log.info("Test 1 2 3");
        log.info("Message");

        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        takeScreenshot("Test run Chrome headless");
        //Check in what thread running this instance
        System.out.println("Running firstTryTestRunChrome in thread: " + Thread.currentThread().getId());

    }

    @Test
    public void firstTryTestRunFirefoxHeadless(){

        log.info("Test 1 2 3");

        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        takeScreenshot("Test run firefox headless");
        //Check in what thread running this instance
        System.out.println("Running firstTryTestRunChrome in thread: " + Thread.currentThread().getId());

    }


}
