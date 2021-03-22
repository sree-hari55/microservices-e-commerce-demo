package com.example.fixedassets.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.fixedassets.dto.APIError;

/**
 * @author srihari.g
 *
 */

@RestControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> feildError=ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
		APIError apiError=new APIError();
		apiError.setTimeStamp(LocalDateTime.now());
		apiError.setStatus(status);
		apiError.setErrors(feildError);
		apiError.setPathUri(request.getDescription(false).substring(4));
		return new ResponseEntity<Object>(apiError,headers,status);
	}

	@ExceptionHandler({AssetNotFoundException.class,Exception.class})
	protected ResponseEntity<Object> CustomExceptionHandler(Exception ex, WebRequest request) {
		APIError apiError=new APIError();
		apiError.setTimeStamp(LocalDateTime.now());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setErrors(Arrays.asList(ex.getMessage()));
		apiError.setPathUri(request.getDescription(false).substring(4));
		return new ResponseEntity<Object>(apiError,apiError.getStatus());
	}
}
