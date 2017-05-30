package com.excilys.exceptions;

public class DiscontinuedDateException extends Exception {

    @Override
    public String getMessage() {

        String message = "Discontinued data must be greater than introduced one";

        return message;
    }
}
