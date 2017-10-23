package com.guessnumber.exception;

/**
 * Custom Exception Class
 * 
 * @author nikhil
 *
 */
public class DuplicateUserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String exceptionMsg ;
	
	public DuplicateUserException(String exceptionMsg){
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}
