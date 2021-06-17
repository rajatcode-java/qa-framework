package com.qa.selenium.browser;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.selenium.driver.Driver;
import com.qa.selenium.listeners.WebDriverListener;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.qa.fileUtils.ReadConfig;


public class SetBrowser {
    ReadConfig readConfig=new ReadConfig();
    WebDriverListener driverListener = new WebDriverListener();
    static{System.setProperty("logback.configurationFile", System.getProperty("user.dir")+"//logback.xml");}
    //what browsers are we interested in implementing
    @SuppressWarnings("unused")
	public void browserSet(String browser ) {
        switch (browser) {        //check our browser
            case "Firefox": {
            	FirefoxOptions firefoxOptions = new FirefoxOptions();
            	WebDriverManager.firefoxdriver().setup();
                Driver.driver = new FirefoxDriver();
                Driver.e_driver=new EventFiringWebDriver(Driver.driver);
                Driver.e_driver.register(driverListener);
                break;
            }
            case "Chrome": {
            	ChromeOptions options = new ChromeOptions();
            	WebDriverManager.chromedriver().setup();
            	options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--enable-logging"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                options.addArguments("--silent");
                options.addArguments("--headless");

                options.setBinary(WebDriverManager.chromedriver().getBinaryPath());
                Driver.driver = new ChromeDriver();
                Driver.e_driver=new EventFiringWebDriver(Driver.driver);
                Driver.e_driver.register(driverListener);
                Driver.driver.manage().window().maximize();
                break;
            }
            case "InternetExplorer": {
            	WebDriverManager.iedriver().setup();
                Driver.driver = new InternetExplorerDriver();
                Driver.e_driver=new EventFiringWebDriver(Driver.driver);
                Driver.e_driver.register(driverListener);
                break;
            }
            case "Safari": {
                Driver.driver = new SafariDriver();
                Driver.e_driver=new EventFiringWebDriver(Driver.driver);
                Driver.e_driver.register(driverListener);
                break;
            }
           
            
        }
    }
}
