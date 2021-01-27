package com.example.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.dto.APIError;

// Global exception handler

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	// bean validation exception
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErrors=ex.getFieldErrors();
		List<String> errors=fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
		APIError apiError=new APIError();
		apiError.setTimestamp(LocalDateTime.now());
		apiError.setStatus(status);
		apiError.setPathUri(request.getDescription(false).substring(4));
		apiError.setErrorMessages(errors);
		return new ResponseEntity<Object>(apiError,headers,status);
	}

	      // custom exception handler
	@ExceptionHandler(value = {OfferNotValidException.class,CurrencyNotValidException.class})
	public ResponseEntity<?> handleOfferNotValidException(Exception ex,ServletWebRequest servletWebRequest){
		APIError apiError=new APIError();
		apiError.setTimestamp(LocalDateTime.now());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setPathUri(servletWebRequest.getDescription(false).substring(4));
		apiError.setErrorMessages(Arrays.asList(ex.getMessage()));
		return new ResponseEntity<>(apiError,new HttpHeaders(),apiError.getStatus());
	}
}
