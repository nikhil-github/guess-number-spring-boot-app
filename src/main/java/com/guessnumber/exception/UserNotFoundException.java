package com.guessnumber.exception;

/**
 * Custome exception handle user not found
 * @author nikhil
 *
 */
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String exceptionMsg ;
	
	public UserNotFoundException(String exceptionMsg){
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}
