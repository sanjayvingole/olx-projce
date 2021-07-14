package com.olx.exception;

public class InvalidUserIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message;

	public InvalidUserIdException() {
		this.message = "";
	}

	public InvalidUserIdException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid User Id Please use valid Id " + message;
	}

}
