package com.tests;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qa.logutil.LogStatus;
import com.qa.logutil.LogUtil;
import com.qa.selenium.DataProviderClass;
import com.qa.selenium.browser.Browser;
import com.qa.selenium.driver.Driver;

public class StubTest {

	Browser browser = new Browser();
	
	@Test(dataProvider ="dataProvider",dataProviderClass =DataProviderClass.class)
	public void stubtest(HashMap<String, String> map){
		String u = map.get("username");
		browser.navigateTo();
		int i=9;
		while(i-->0)
		LogUtil.log(LogStatus.ERROR,"HIIIIIIII");
		WebElement w = Driver.driver.findElement(By.id("l"));
	}
	@Test(dataProvider ="dataProvider",dataProviderClass =DataProviderClass.class)
	public void stubtest1(HashMap<String, String> map){
		String u = map.get("username");
		browser.navigateTo();
		int i=9;
		while(i-->0)
		LogUtil.log(LogStatus.WARN,"HIIIIIIII");
		WebElement w = Driver.driver.findElement(By.id("l"));
	}
	
}
