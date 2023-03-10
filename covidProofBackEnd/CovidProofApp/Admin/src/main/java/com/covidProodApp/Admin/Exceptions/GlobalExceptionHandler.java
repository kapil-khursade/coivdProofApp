package com.covidProodApp.Admin.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler {

	 @ExceptionHandler(vaccineDetailException.class)
		public ResponseEntity<MyError> ErrorResponsehandleException(vaccineDetailException ex){
		  return new ResponseEntity<MyError>(new MyError(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
		}
	 
	 @ExceptionHandler(centerException.class)
		public ResponseEntity<MyError> ErrorResponsehandleException(centerException ex){
		  return new ResponseEntity<MyError>(new MyError(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
		}
}
