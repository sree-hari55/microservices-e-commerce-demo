package com.example.exception;

public class OfferNotValidException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public OfferNotValidException(String msg) {
		super(msg);
	}

}
