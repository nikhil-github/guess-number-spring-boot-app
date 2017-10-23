package com.guessnumber.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.guessnumber.datatransfer.ErrorResponse;

/**
 * Translates the exception Springs controller advise
 * @author nikhil
 *
 */
@ControllerAdvice
public class ExceptionConverter {

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ResponseEntity<ErrorResponse> processDuplicateMessage(DuplicateUserException ex) {
    	ErrorResponse error =  
    			new ErrorResponse(ex.getExceptionMsg());
        return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorResponse> processNoUserFoundMessage(UserNotFoundException ex) {
    	ErrorResponse error =  
    			new ErrorResponse(ex.getExceptionMsg());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> processRuntimeException(Exception ex) throws Exception {
    	ErrorResponse error =  
    			new ErrorResponse("An error occured during gaming - restart again");
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

	
}
