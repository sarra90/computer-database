package com.excilys.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorDiscontinuedDate implements ConstraintValidator<DisconstinuedDate, String> {

    @Override
    public void initialize(DisconstinuedDate arg0) {
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        // TODO Auto-generated method stub
        return false;
    }

}
