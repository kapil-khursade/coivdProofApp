package com.covidProodApp.Applicant.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.covidProodApp.Admin.Exceptions.MyError;

@ControllerAdvice
public class GlobalExceptionHnadler {

	 @ExceptionHandler(applicantExceptions.class)
		public ResponseEntity<MyError> ErrorResponsehandleException(applicantExceptions ex){
		  return new ResponseEntity<MyError>(new MyError(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
		}
}
