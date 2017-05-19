package com.excilys.validations;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.excilys.dto.ComputerDto;

@ContextConfiguration(classes = {DateValidatorTest1.Config.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DateValidatorTest1 {

    @Configuration
    public static class Config {
        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }
        @Bean
        public Validator validator(){
            Validator validator = new  LocalValidatorFactoryBean();
            return validator ;
            
        }

    }
    private static final String CORRECT_DATE = "02/05/2015";
    private static final String INCORRECT_DATE = "02/06";
 
    @Autowired
    private static Validator validator;
    
    @BeforeClass
    public static void setup() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) factory.getValidator();
    }


    @Autowired
    private ComputerDto computerDto;
    

    @Before
    public void before() {
        computerDto = new ComputerDto.Builder().build();
    }
    @Test
    public void testEmailExistsCorrect() {
        computerDto.setIntroduced(CORRECT_DATE);
 /*
        Set<ConstraintViolation<ComputerDto>> violations = validator
                .validate(computerDto);
        Assert.assertEquals(0, violations.size());*/
    }
}

