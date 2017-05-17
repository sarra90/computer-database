package com.excilys.validations;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.dto.ComputerDto;
import com.excilys.model.ValidatorModel;
 
@Component
public class ComputerFormValidator implements Validator{
    
        
        public boolean supports(Class<?> clazz) {
            return ComputerDto.class.isAssignableFrom(clazz);
        }
     
        public void validate(Object target, Errors errors) 
        {
           ComputerDto computerDto=(ComputerDto)target;
           
           String name = computerDto.getName();
           
           String introduced = computerDto.getIntroduced();
           
           String disconstinued = computerDto.getDisconstinued();
           
           ValidatorModel introducedValidator = DateValidator.isValidDate(introduced);
           ValidatorModel disconstinuedValidator = DateValidator.isValidDate(disconstinued);
           ValidatorModel orderDateValidator = DateValidator.isIntruducedDateBeforeDisconstinuedDate(introduced,
                   disconstinued);

           ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.computerForm.name ");
           if (!introducedValidator.isValid()){
               errors.rejectValue("introduced", "Pattern.computerForm.introduced");
           }
           if(!disconstinuedValidator.isValid()){
               errors.rejectValue("disconstinued", "Pattern.computerForm.disconstinued");
           }
           if(!orderDateValidator.isValid()){
               errors.rejectValue("disconstinued", "Pattern.computerForm.orderDate");
           }
           
        }
        

}
