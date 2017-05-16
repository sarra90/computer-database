package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.ComputerDto;
import com.excilys.mapper.MapperComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class AddComputerController {

    @Autowired 
    ComputerService computerService;
    
    @Autowired
    CompanyService companyService;
    
    @RequestMapping(value = "/addcomputer" , method = RequestMethod.GET )
    public String addCOmputer(Model model){
        
        model.addAttribute("list",companyService.findAll());
        return "addComputer";
    }
    
    public String addComputer(@ModelAttribute("computer") ComputerDto computerDto,BindingResult result,Model model){
        
        computerService.create(MapperComputer.convertToComputer(computerDto));
        return "addComputer";
    }
}
