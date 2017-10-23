package com.guessnumber.datatransfer;

/**
 * Create Data Trasfer Object Error Response
 * @author nikhil
 *
 */
public class ErrorResponse {

    private  String description;
    
    public ErrorResponse(String description){
    	this.description =description;
    }

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
