package com.excilys.validations;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateValidatorTest {

    @Test
    public void testDateIsNull() {
        assertFalse(DateValidator1.isValidDate(null).isValid());
    }

    @Test
    public void testDayIsInvalid() {
        assertFalse(DateValidator1.isValidDate("2012-02-32").isValid());
    }

    @Test
    public void testMonthIsInvalid() {
        assertFalse(DateValidator1.isValidDate("2012-20-31").isValid());
    }

    @Test
    public void testYearIsInvalid() {
        assertFalse(DateValidator1.isValidDate("19991-20-31").isValid());
    }

    @Test
    public void testDateFormatIsInvalid() {
        assertFalse(DateValidator1.isValidDate("2012-20-02").isValid());
    }

    @Test
    public void testDateFeb29_2012() {
        assertTrue(DateValidator1.isValidDate("2012-02-29").isValid());
    }

    @Test
    public void testDateFeb28() {
        assertTrue(DateValidator1.isValidDate("2011-02-28").isValid());
    }

    @Test
    public void testDateIsValid_1() {
        assertTrue(DateValidator1.isValidDate("2012-01-31").isValid());

    }

    @Test
    public void testDateIsValid_2() {
        assertTrue(DateValidator1.isValidDate("2012-04-30").isValid());
    }

    @Test
    public void testDateIsValid_3() {
        assertTrue(DateValidator1.isValidDate("2012-05-31").isValid());
    }

    public void testDateWithABC() {
        assertFalse(DateValidator1.isValidDate("12/d").isValid());
    }

}
