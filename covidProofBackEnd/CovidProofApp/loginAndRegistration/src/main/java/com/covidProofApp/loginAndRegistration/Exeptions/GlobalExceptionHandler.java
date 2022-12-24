package com.covidProofApp.loginAndRegistration.Exeptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	    @ExceptionHandler(applicantException.class)
		public ResponseEntity<MyError> ErrorResponsehandleException(applicantException ex){
		  return new ResponseEntity<MyError>(new MyError(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
		}
	    
	    @ExceptionHandler(adminExceptions.class)
		public ResponseEntity<MyError> adminResponsehandleException(adminExceptions ex){
	    	return new ResponseEntity<MyError>(new MyError(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
		}
	    
	    @ExceptionHandler(Exception.class)
	 	public ResponseEntity<MyError> allException(Exception ex){
	    	
	    	String mes = "Error";
	    	if(ex.getMessage().contains("ConstraintViolationException"))mes= "Try New Inputs";
	    	
	    	return new ResponseEntity<MyError>(new MyError(mes, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	 	}
}
