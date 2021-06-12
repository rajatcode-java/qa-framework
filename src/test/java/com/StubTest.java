package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qa.logutil.LogStatus;
import com.qa.logutil.LogUtil;
import com.qa.selenium.browser.Browser;
import com.qa.selenium.driver.Driver;

public class StubTest {

	Browser browser = new Browser();
	
	@Test
	public void stubtest(){
		browser.navigateTo();
		int i=999999999;
		while(i-->0)
		LogUtil.log(LogStatus.INFO,"HIIIIIIII");
		WebElement w = Driver.driver.findElement(By.id("l"));
	}
	
}
