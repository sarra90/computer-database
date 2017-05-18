package com.excilys.validations;

import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.excilys.dto.ComputerDto;

public class DateValidatorTest1 {

    private LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();

    @Test
    public void testIntroducedDateNotValid() {
        final ComputerDto computerDto = new ComputerDto.Builder().introduced("2002/2015").build();
        System.err.println(computerDto);
        Set<ConstraintViolation<ComputerDto>> violations = validator.validate(computerDto);
        assertFalse(violations.isEmpty());
    }
}
