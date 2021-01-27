package com.example.exception;

public class CurrencyNotValidException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public CurrencyNotValidException(String message) {
		super(message);
	}
	
	

}
