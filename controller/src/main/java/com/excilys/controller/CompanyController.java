package com.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.model.Company;
import com.excilys.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    CompanyService companySevice;
    
    @RequestMapping(value="/getAll" , method=RequestMethod.GET)
    public List<Company> getAll(){
        return companySevice.findAll();
    }
    
    @RequestMapping(value="/find/{id}" , method=RequestMethod.GET)
    public Company findById(@PathVariable Long id){
        return companySevice.findById(id);
    }
    
   
}





