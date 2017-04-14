package com.excilys.computer_database.exceptions;

public class DiscontinuedDateException extends RuntimeException {

	@Override
	public String getMessage() {
		
		String message = "Discontinued data must be greater than introduced one";
		
		return message;
	}
}
