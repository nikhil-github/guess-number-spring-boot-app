package com.guessnumber.datatransfer;

/**
 * Create Data Transfer Object Response
 * @author nikhil
 *
 */
public class Response {
	
	private String message ;
	
	public Response(){
		
	}
	
	public Response(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
