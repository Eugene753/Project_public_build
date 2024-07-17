package com.datadive.base;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;
    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    @Parameters({ "browser", "environment" })
    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, @Optional("chrome") String browser,@Optional("local") String environment, ITestContext ctx) {
        // Alternative we can do this
        // log = LogManager.getLogger(ctx.getCurrentXmlTest().getSuite().getName());
        String testName = ctx.getCurrentXmlTest().getName();
        log = LoggerFactory.getLogger(testName);

        switch (environment){
            case "local":
                driver = new BrowserDriverFactory(browser,log).createDriver();
                break;
            case "grid":
                driver = new GridFactory(browser, log).createDriver();
                break;
            default:
                driver = new BrowserDriverFactory(browser, log).createDriver();
                break;
        }

        driver.manage().window().maximize();

        this.testSuiteName = ctx.getSuite().getName();
        // this.testName = ctx.getCurrentXmlTest().getName(); And this would look like this
        this.testName = testName;
        this.testMethodName = method.getName();
        log.info(testSuiteName);
        log.info(testName);
        log.info(testMethodName);
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void tearDown() {
        log.info("Close Browser");
        driver.quit();
    }


}
