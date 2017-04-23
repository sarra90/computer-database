package com.excilys.validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.model.Validator;

public class DateValidator {

	public Validator isValidDate(String dateToValidate){
		
		Validator validator = new Validator();
		
		try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/uuuu");
			LocalDate date = LocalDate.parse(dateToValidate, formatter);
			validator.setValid(true);
		}catch(DateTimeParseException e){
			validator.setValid(false);
			validator.setError("date not valide");
		}
	  return validator;
	}
}
