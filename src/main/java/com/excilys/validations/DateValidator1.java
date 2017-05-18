package com.excilys.validations;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.excilys.model.ValidatorModel;

public class DateValidator1 {

    public static ValidatorModel isValidDate(String dateToValidate) {

        ValidatorModel validator = new ValidatorModel();

        if (dateToValidate != null) {
            try {
                LocalDate date = LocalDate.parse(dateToValidate);
                validator.setValid(true);

                validator.setError(null);

            } catch (DateTimeParseException e) {

                validator.setValid(false);

                validator.setError("Date is not valid");

            }
        } else {
            validator.setValid(false);

            validator.setError("Date is not valid");
        }
        return validator;
    }

    public static ValidatorModel isIntruducedDateBeforeDisconstinuedDate(String introduced, String disconstinued) {

        ValidatorModel validator = new ValidatorModel();

        if (DateValidator1.isValidDate(introduced).isValid() && DateValidator1.isValidDate(disconstinued).isValid()) {

            LocalDate dateintroduced = LocalDate.parse(introduced);
            LocalDate datedisconstinued = LocalDate.parse(disconstinued);

            if (datedisconstinued.isBefore(dateintroduced)) {

                validator.setValid(false);

                validator.setError("Discontinued data must be greater than introduced one");
            } else {
                validator.setValid(true);
            }
        }
        return validator;
    }
}
