package com.excilys.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.annotation.HttpConstraint;
import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorDiscontinuedDate.class)
public @interface DisconstinuedDate {
    String message() default "Discontinued data must be greater than introduced one";

}
