package com.qa.selenium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.selenium.driver.Driver;


public class ScreenShotTaker{
	private static String filePath = System.getProperty("user.dir")+"//screenshot";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY_mm_dd_HHmmss");
	
	public static String takeBase64ScreenShot(){
		if(Driver.driver==null){
			return null;
		}
		else{
			return ((TakesScreenshot)Driver.driver).getScreenshotAs(OutputType.BASE64);
		}
	}
	
	public static void takeScreenShot(){
		String callerClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		File srcFile=((TakesScreenshot)Driver.driver).getScreenshotAs(OutputType.FILE);
		try {
			String destFileName = filePath+"//"+callerClassName+"_"+simpleDateFormat;
			FileUtils.copyFile(srcFile, new File(destFileName));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot");
			e.printStackTrace();
		}
	}
}