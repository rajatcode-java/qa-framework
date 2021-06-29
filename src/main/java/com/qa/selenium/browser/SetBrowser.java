package com.qa.selenium.browser;


import java.io.IOException;
import java.util.Collections;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.selenium.driver.Driver;
import com.qa.selenium.listeners.WebDriverListener;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.qa.fileUtils.ReadConfig;
import com.qa.logutil.LogStatus;
import com.qa.logutil.LogUtil;


public class SetBrowser extends Driver{
    ReadConfig readConfig=new ReadConfig();
    WebDriverListener driverListener = new WebDriverListener();
    static{System.setProperty("logback.configurationFile", System.getProperty("user.dir")+"//logback.xml");}
    //what browsers are we interested in implementing
    @SuppressWarnings({ "unused", "deprecation" })
	public void browserSet(String browser ) {
        switch (browser) {        //check our browser
            case "Firefox": {
            	FirefoxOptions firefoxOptions = new FirefoxOptions();
            	WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                e_driver=new EventFiringWebDriver(Driver.driver);
                e_driver.register(driverListener);
                break;
            }
            case "Chrome": {

                String commandClose = "taskkill /f /im chrome.exe";
                String commandCloseDriver = "taskkill /f /im chromedriver.exe";
            	 try {
 					Runtime.getRuntime().exec(commandClose);
 					Runtime.getRuntime().exec(commandCloseDriver);
 					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 					LogUtil.log(LogStatus.INFO, "Cleanning existing chrome process");
 				} catch (IOException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
            	ChromeOptions options = new ChromeOptions();
            	WebDriverManager.chromedriver().setup();
            	options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
				options.addArguments("disable-notifications");
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--enable-logging"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                options.addArguments("--silent");
//                options.addArguments("--headless");
                options.addArguments("--ignore-ssl-errors=yes");
				options.addArguments("--ignore-certificate-errors");
				
				DesiredCapabilities chromeCap = DesiredCapabilities.chrome();
				chromeCap.setJavascriptEnabled(true);
				chromeCap.setCapability(ChromeOptions.CAPABILITY, options);
				chromeCap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                options.setBinary(WebDriverManager.chromedriver().getBinaryPath());
                driver = new ChromeDriver(chromeCap);
                e_driver=new EventFiringWebDriver(Driver.driver);
                e_driver.register(driverListener);
                e_driver.manage().window().maximize();
                break;
            }
            case "InternetExplorer": {
            	WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                e_driver=new EventFiringWebDriver(Driver.driver);
                e_driver.register(driverListener);
                break;
            }
            case "Safari": {
                driver = new SafariDriver();
                e_driver=new EventFiringWebDriver(Driver.driver);
                e_driver.register(driverListener);
                break;
            }
           
            
        }
    }
}
