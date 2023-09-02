package com.ecommerce.exception;

@SuppressWarnings("serial")
public class AddressException extends RuntimeException{
	
	public AddressException() {
		
	}
	
	public AddressException(String message) {
		super(message);
	}
}
