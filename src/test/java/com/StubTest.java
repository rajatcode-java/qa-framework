package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qa.selenium.browser.Browser;
import com.qa.selenium.driver.Driver;

public class StubTest {

	Browser browser = new Browser();
	
	@Test
	public void stubtest(){
		browser.navigateTo();
		WebElement w = Driver.driver.findElement(By.id("l"));
	}
	
}
