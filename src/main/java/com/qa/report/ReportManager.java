package com.qa.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.selenium.ScreenShotTaker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;


public class ReportManager{
	private ExtentReports extentReports;
	private ExtentTest extentTest;
	private Map<String, ExtentTest> testMap = new HashMap<String, ExtentTest>();
	public String SuiteName=null;
	private String reportName=null;
	private String reportConfigFilePath = System.getProperty("user.dir")+"\\extent-config.xml";
	ExtentSparkReporter extentSparkReporter;
	String log;
	
	/**
	 * initialize ExtentSparkReporter
	 * load extent-config.xml
	 * initialize ExtentReporter and attach ExtentSparkReporter 
	 */
	public ReportManager(){
		if(extentSparkReporter==null && extentReports==null){
			extentReports=new ExtentReports();
		}
	}	
	/**
	 *void 
	 */
	public void writeReport() {
		reportName=System.getProperty("user.dir")+"\\HtmlReport\\"+SuiteName+"_"+new SimpleDateFormat("dd_MM").format(new Date())+".html";
		extentSparkReporter = new ExtentSparkReporter(reportName);
		extentSparkReporter.loadConfig(reportConfigFilePath);
		extentReports.attachReporter(extentSparkReporter);
		extentReports.flush();
	}
	
	/**
	 *MediaEntityModelProvider 
	 *@return
	 *@throws IOException
	 */
	public MediaEntityModelProvider takeScreenShot(){
		try {
			return MediaEntityBuilder.createScreenCaptureFromBase64String(new ScreenShotTaker().takeBase64ScreenShot()).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *void 
	 *@param log
	 */
	public void log(String log){
		this.log=log;
	}
	
	public void testStart(ITestResult result){
		String testName = result.getInstanceName();
		testMap.putIfAbsent(testName,extentReports.createTest(testName));
	}
	
	public void testSuccess(ITestResult result) {
		String featureName = result.getInstanceName();
		testMap.get(featureName).pass(result.getTestName());
		
	}

	public void testFailure(ITestResult result) {
		String featureName = result.getInstanceName();
		testMap.get(featureName).fail(result.getThrowable().getMessage(),takeScreenShot());
		
	}

	public void testSkipped(ITestResult result) {
		String featureName = result.getInstanceName();
		testMap.get(featureName).skip("Test Skipped");
		
	}

//
//	@Override
//	public void onStart(ITestContext context) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//		String featureName = context.
//		scenario = features.get(featureName).createNode(event.getTestCase().getName());
//		scenario.log((Status)scenario.getStatus(),log,base64ScreenCapture());
//		
//	}
}
