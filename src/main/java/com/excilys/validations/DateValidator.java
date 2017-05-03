package com.excilys.validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.model.Validator;

public class DateValidator {

	public static Validator isValidDate(String dateToValidate) {

		Validator validator = new Validator();
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(dateToValidate!=null){
		try {
			LocalDate date =  LocalDate.parse(dateToValidate);
			validator.setValid(true);

			validator.setError(null);

		} catch (DateTimeParseException e) {

			validator.setValid(false);

			validator.setError("Date is not valid");

		}
        }else{
        	validator.setValid(false);

			validator.setError("Date is not valid");
        }
		return validator;
	}

	public static Validator isIntruducedDateBeforeDisconstinuedDate(String introduced, String disconstinued) {
		
		Validator validator = new Validator();

		if (DateValidator.isValidDate(introduced).isValid()&& DateValidator.isValidDate(disconstinued).isValid()){
		
			LocalDate dateintroduced = LocalDate.parse(introduced);
			LocalDate datedisconstinued = LocalDate.parse(disconstinued);
			
			if (datedisconstinued.isBefore(dateintroduced)) {
				
				validator.setValid(false);
	
				validator.setError("Discontinued data must be greater than introduced one");
			}
				else {
					validator.setValid(true);
			}
		}
		return validator;
	}
}
