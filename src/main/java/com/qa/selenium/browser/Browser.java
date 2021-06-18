package com.qa.selenium.browser;

import com.qa.selenium.driver.Driver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.fileUtils.ReadConfig;

public class Browser extends SetBrowser
{
    private static String baseURL;
    private static String browserName = null;
    private final String defaultBrowser = "chrome";
    
//    private Browser(){}
    
    public Browser() {
        com.qa.fileUtils.ReadConfig readConfig;
        readConfig = new ReadConfig();
        baseURL=readConfig.getApplicationUrl();
        browserName=readConfig.getBrowser();
        if(browserName == null)
            browserName = defaultBrowser;
        browserSet(browserName);
    }

    public void navigateTo()
    {
        Driver.e_driver.get( baseURL );
    }

    public void navigateTo(String url)
    {
        Driver.e_driver.get( url );
    }
    
    public static EventFiringWebDriver getBrowser(){
    	return Driver.e_driver;
    }
    
    public static void setBrowser(EventFiringWebDriver e_driver){
    	Driver.e_driver=e_driver;
    }
}
