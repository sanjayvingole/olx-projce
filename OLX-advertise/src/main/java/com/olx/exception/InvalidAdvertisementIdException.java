package com.olx.exception;

public class InvalidAdvertisementIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message;

	public InvalidAdvertisementIdException() {
		this.message = "";
	}

	public InvalidAdvertisementIdException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Advertisement Id Please use valid Id " + message;
	}

}
