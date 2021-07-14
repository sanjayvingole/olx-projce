package com.olx.exception;

public class InvalidCategoryIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message;

	public InvalidCategoryIdException() {
		this.message = "";
	}

	public InvalidCategoryIdException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Category Id Please use valid Id " + message;
	}

}
