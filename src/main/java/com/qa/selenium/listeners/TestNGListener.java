package com.qa.selenium.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.report.ReportManager;

public class TestNGListener implements ITestListener{
	ReportManager reportManager = new ReportManager();
	public static String SuiteName = null; 
	@Override
	public void onTestStart(ITestResult result) {
		
		reportManager.testStart(result);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		reportManager.testSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		reportManager.testFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		reportManager.testSkipped(result);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		reportManager.SuiteName=context.getSuite().getName();
	}

	@Override
	public void onFinish(ITestContext context) {
		
		reportManager.writeReport();
	}

}
