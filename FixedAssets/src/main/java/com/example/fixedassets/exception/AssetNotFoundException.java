package com.example.fixedassets.exception;

public class AssetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AssetNotFoundException(String msg) {
		super(msg);
	}
}
