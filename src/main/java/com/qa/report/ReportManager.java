package com.qa.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.selenium.ScreenShotTaker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;


public class ReportManager{
	private ExtentReports extentReports;
	private static Map<String, ExtentTest> testMap = new HashMap<String, ExtentTest>();
	public String SuiteName;
	private String reportName=null;
	private String reportConfigFilePath = System.getProperty("user.dir")+"\\extent-config.xml";
	ExtentSparkReporter extentSparkReporter;
	String log;
	static String testName = null;
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
		reportName=System.getProperty("user.dir")+"\\HtmlReport\\"+SuiteName+"_"+new SimpleDateFormat("dd_MM_hhmmss").format(new Date())+".html";
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
	public ExtentTest getExtentTestNode(){
		if(testName!=null)
			return testMap.get(testName);
		return null;
	}
	
	public void testStart(ITestResult result){
		testName = result.getMethod().getQualifiedName();
		testMap.putIfAbsent(testName,extentReports.createTest(testName));
	}
	
	public void testSuccess(ITestResult result) {
		String featureName = result.getMethod().getQualifiedName();
		testMap.get(featureName).pass(result.getTestName());
		testMap.remove(featureName);
		
	}

	public void testFailure(ITestResult result) {
		String featureName =result.getMethod().getQualifiedName();
		testMap.get(featureName).fail(result.getThrowable().getMessage(),takeScreenShot());
		testMap.remove(featureName);
	}

	public void testSkipped(ITestResult result) {
		String featureName = result.getMethod().getQualifiedName();
		ExtentTest e = extentReports.createTest(featureName);
		e.skip("Test Skipped");
	}

	public void onStart(ITestContext context) {
	}

}
