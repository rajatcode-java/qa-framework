package com.qa.selenium;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.qa.logutil.LogStatus;
import com.qa.logutil.LogUtil;
import com.qa.selenium.listeners.TestNGListener;

public class DataProviderClass {

	@DataProvider(name = "dataProvider")
    public static Object[][] dataProviderMethod() 
    {
		String dataFilePath = System.getProperty("user.dir")+("//src//test//resources//data//")+TestNGListener.ClassName+".proerties";
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(dataFilePath));
		} catch (Exception e) {
			LogUtil.log(LogStatus.ERROR, "Data File for test class "+TestNGListener.ClassName+" Not Found!!");
		}
		HashMap<String, String> dataMap = new HashMap<String,String>();
		
		Iterator<Object> iterator = properties.keySet().iterator();
		Object[][] obj = new Object[1][1];
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			dataMap.put(key, properties.getProperty(key));
		}	
		obj[0][0]=dataMap;
		return obj;
    }
}
