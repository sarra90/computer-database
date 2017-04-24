package com.excilys.validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.model.Validator;

public class DateValidator {

	public static Validator isValidDate(String dateToValidate) {

		Validator validator = new Validator();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
		try {
			LocalDate date =  LocalDate.parse(dateToValidate, formatter);

			validator.setValid(true);

			validator.setError(null);

		} catch (DateTimeParseException e) {

			validator.setValid(false);

			validator.setError("date not valide");

			System.out.println(validator.getError() + " " + validator.getValid());
		}
		return validator;
	}

	public static Validator isIntruducedDateBeforeDisconstinuedDate(String introduced, String disconstinued) {

		LocalDate dateintroduced = LocalDate.parse(introduced);
		
		LocalDate datedisconstinued = LocalDate.parse(disconstinued);
		
		Validator validator = new Validator();
		
		if (datedisconstinued.isBefore(dateintroduced)) {
			
			validator.setValid(false);

			validator.setError("Discontinued data must be greater than introduced one");
		}
		else{
			validator.setValid(true);
		}
		return validator;
	}
}
