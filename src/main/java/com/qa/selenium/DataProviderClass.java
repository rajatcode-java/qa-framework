package com.qa.selenium;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.qa.fileUtils.ReadConfig;
import com.qa.logutil.LogStatus;
import com.qa.logutil.LogUtil;

public class DataProviderClass {
	ReadConfig readConfig = new ReadConfig();
    String dataFilePath=readConfig.getDataFilePath();
	@DataProvider(name = "dataProvider")
    public Object[][] dataProviderMethod(Class<?> className) throws CustomException {
		String dataFilePath = this.dataFilePath+"//"+className.getSimpleName()+".properties";
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(dataFilePath));
		} catch (Exception e) {
			LogUtil.log(LogStatus.ERROR, "Data File for test class "+className+".properties Not Found!!");
			throw new CustomException("Data File for test class "+className+".properties Not Found!!");
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
