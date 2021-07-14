package com.olx.exception;

public class InvalidStatusIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message;

	public InvalidStatusIdException() {
		this.message = "";
	}

	public InvalidStatusIdException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Status Id Please use valid Id " + message;
	}

}
