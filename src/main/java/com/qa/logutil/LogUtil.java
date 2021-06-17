package com.qa.logutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.report.ReportManager;


public class LogUtil {
	static Logger log = null;
	static ReportManager reportManager = new ReportManager();
	
	@SuppressWarnings("static-access")
	public static void log(LogStatus LogStatus, String msg){
		getClassLog();
		addExtentLog(LogStatus,msg);
		if(LogStatus.equals(LogStatus.INFO)){
			log.info(msg);
		}
		else if(LogStatus.equals(LogStatus.DEBUG)){
			log.debug(msg);
		}
		else if(LogStatus.equals(LogStatus.TRACE)){
			log.trace(msg);
		}
		else if(LogStatus.equals(LogStatus.ERROR)){
			log.error(msg);
		}
		else if(LogStatus.equals(LogStatus.WARN)){
			log.warn(msg);
		}
		
	}
	
	@SuppressWarnings("static-access")
	public static void log(LogStatus LogStatus, String msg, Throwable throwable){
		getClassLog();
		addExtentLog(LogStatus,msg,throwable);
		if(LogStatus.equals(LogStatus.INFO)){
			log.info(msg,throwable);
		}
		else if(LogStatus.equals(LogStatus.DEBUG)){
			log.debug(msg,throwable);
		}
		else if(LogStatus.equals(LogStatus.TRACE)){
			log.trace(msg,throwable);
		}
		else if(LogStatus.equals(LogStatus.ERROR)){
			log.error(msg,throwable);
		}
		else if(LogStatus.equals(LogStatus.WARN)){
			log.warn(msg,throwable);
		}
		
	}
	
	
	private static void getClassLog(){
		 try {
				log = LoggerFactory.getLogger(Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
			}
	}
	
	private static void addExtentLog(LogStatus logStatus,String msg){
		try{
			switch(logStatus){
				case INFO:
					reportManager.getExtentTestNode().log(Status.INFO,msg);
					break;
				case DEBUG:
					reportManager.getExtentTestNode().log(Status.DEBUG,msg);
					break;
				case WARN:
					reportManager.getExtentTestNode().log(Status.WARNING,msg);
					break;
				case ERROR:
					reportManager.getExtentTestNode().log(Status.ERROR,msg);
					break;
				default:
					reportManager.getExtentTestNode().log(Status.INFO,msg);	
			}
		}
		catch(NullPointerException e){
			//do nothing
			//extent test has not been created and pre test config log not recorded in Extent Reports
		}
	}	
	
	private static void addExtentLog(LogStatus logStatus,String msg,Throwable t){
		try{
			switch(logStatus){
				case INFO:
					reportManager.getExtentTestNode().log(Status.INFO,t);
					break;
				case DEBUG:
					reportManager.getExtentTestNode().log(Status.DEBUG,t);
					break;
				case WARN:
					reportManager.getExtentTestNode().log(Status.WARNING,t);
					break;
				case ERROR:
					reportManager.getExtentTestNode().log(Status.ERROR,t);
					break;
				default:
					reportManager.getExtentTestNode().log(Status.INFO,t);	
			}
		}
		catch(NullPointerException e){
			//do nothing
			//extent test has not been created and pre test config log not recorded in Extent Reports
		}
	}
}
