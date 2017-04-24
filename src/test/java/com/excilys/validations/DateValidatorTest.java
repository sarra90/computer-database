package com.excilys.validations;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateValidatorTest {


	@Test
	public void testDateIsNull(){
		assertFalse(DateValidator.isValidDate(null).getValid());
	}

	@Test
	public void testDayIsInvalid() {
		assertFalse(DateValidator.isValidDate("32/02/2012").getValid());
	}

	@Test
	public void testMonthIsInvalid() {
		assertFalse(DateValidator.isValidDate("31/20/2012").getValid());
	}

	@Test
	public void testYearIsInvalid() {
		assertFalse(DateValidator.isValidDate("31/20/19991").getValid());
	}

	@Test
	public void testDateFormatIsInvalid() {
		assertFalse(DateValidator.isValidDate("2012/02/20").getValid());
	}

	@Test
	public void testDateFeb29_2012() {
		assertTrue(DateValidator.isValidDate("29/02/2012").getValid());
	}

	@Test
	public void testDateFeb29_2011() {
		assertFalse(DateValidator.isValidDate("29/02/2011").getValid());
	}

	@Test
	public void testDateFeb28() {
		assertTrue(DateValidator.isValidDate("28/02/2011").getValid());
	}

	@Test
	public void testDateIsValid_1() {
		assertTrue(DateValidator.isValidDate("31/01/2012").getValid());
	}

	@Test
	public void testDateIsValid_2() {
		assertTrue(DateValidator.isValidDate("30/04/2012").getValid());
	}

	@Test
	public void testDateIsValid_3() {
		assertTrue(DateValidator.isValidDate("31/05/2012").getValid());
	}

}
