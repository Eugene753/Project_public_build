package com.datadive.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class GridFactory {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger log;

    public GridFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }


    public WebDriver createDriver() {
        log.info("Connecting to the node with: " + browser);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browser) {
            case "chrome":
                capabilities.setBrowserName("chrome");
                break;

            case "firefox":
                capabilities.setBrowserName("firefox");
                break;

            case "chromeheadless":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless"); // Add headless mode
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setBrowserName("chrome");
                break;

            case "firefoxheadless":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS,firefoxOptions);
                capabilities.setBrowserName("firefox");
                break;

            default:
                capabilities.setBrowserName("chrome");
                break;
        }

        URL url = null;
        try {
            url = new URL("http://localhost:4444");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.set(new RemoteWebDriver(url, capabilities));

        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        return driver.get();
    }
}
