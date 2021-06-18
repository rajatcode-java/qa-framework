package com.qa.selenium;

import com.qa.logutil.LogStatus;
import com.qa.logutil.LogUtil;

public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public CustomException(String message) {
		super(message);
		this.message = message;
		LogUtil.log(LogStatus.ERROR, message);
	}
	
	public CustomException(String message,Throwable throwable) {
		super(message,throwable);
		this.message = message;
		LogUtil.log(LogStatus.ERROR, message, throwable);
	}
	
	
	@Override
	public String getMessage(){
		return message;
	}
}
