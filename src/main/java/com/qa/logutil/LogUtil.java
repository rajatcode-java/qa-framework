package com.qa.logutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qa.selenium.listeners.TestNGListener;


public class LogUtil {
	static{System.setProperty("logback.configurationFile", System.getProperty("user.dir")+"//logback.xml");}
	static{System.setProperty("LOG_NAME", TestNGListener.SuiteName);}
	static Logger log = null;
	
	public LogUtil(){
		
	}
	public static void log(LogStatus LogStatus, String msg){
		getClassLog();
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
	
	public static void log(LogStatus LogStatus, String msg, Throwable throwable){
		getClassLog();
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
			 StackTraceElement[] f = Thread.currentThread().getStackTrace();
				log = LoggerFactory.getLogger(Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
			}
	}
}
