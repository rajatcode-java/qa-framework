package com.qa.selenium.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.logutil.LogStatus;
import com.qa.logutil.LogUtil;
import com.qa.report.ReportManager;

public class TestNGListener implements ITestListener{
	ReportManager reportManager=new ReportManager();
	public static String SuiteName = null;
	
	@Override
	public void onTestStart(ITestResult result) {
		LogUtil.log(LogStatus.INFO,"Executing test method: "+result.getMethod().getQualifiedName());
		reportManager.testStart(result);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LogUtil.log(LogStatus.INFO, "Test Passed: "+result.getMethod().getQualifiedName());
		reportManager.testSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LogUtil.log(LogStatus.ERROR, "Test Failed: "+result.getMethod().getQualifiedName());
		LogUtil.log(LogStatus.ERROR, "Error Trace: "+result.getThrowable());
		reportManager.testFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		reportManager.testSkipped(result);
		LogUtil.log(LogStatus.WARN, "Test Skipped: "+result.getMethod().getQualifiedName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		LogUtil.log(LogStatus.INFO, "Starting execution of test suite: "+context.getSuite().getName());
		SuiteName=context.getSuite().getName();
		reportManager.SuiteName=context.getSuite().getName();
		reportManager.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		LogUtil.log(LogStatus.INFO, "Suite execution completed: "+context.getSuite().getName());
		reportManager.writeReport();
	}

}
