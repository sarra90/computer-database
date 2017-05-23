package com.excilys.validations;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String> {

    @Override
    public void initialize(Date annotation) {

    }

    @Override
    public boolean isValid(String dateToValidate, ConstraintValidatorContext arg1) {

        boolean result = false;
        
        if (dateToValidate == null || dateToValidate.isEmpty()) {
            return true;
        }

        else if (dateToValidate.trim().isEmpty()) {
            return false;
        }
        else{
            try {
                LocalDate.parse(dateToValidate);
                result = true;
            } catch (DateTimeParseException e) {
                result = false;
                }
        return result;
        }
    }

}
