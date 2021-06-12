package com.Selenium.browser;

import com.Selenium.driver.Driver;
import com.fileUtils.ReadConfig;

public class Browser
{
    private static String baseURL;
    private static String browserName = null;
    private final String defaultBrowser = "chrome";
    public Browser() throws Exception {
        ReadConfig readConfig;
        readConfig = new ReadConfig();
        baseURL=readConfig.getApplicationUrl();
        browserName=readConfig.getBrowser();
        if(browserName == null)
            browserName = defaultBrowser;

        SetBrowser setBrowser = new SetBrowser();
        setBrowser.browserSet(browserName);
    }

    public void navigateTo()
    {
        Driver.e_driver.get( baseURL );
    }

    public void navigateTo(String url)
    {
        Driver.e_driver.get( url );
    }

}
