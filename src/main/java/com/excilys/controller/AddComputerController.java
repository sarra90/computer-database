package com.excilys.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.ComputerDto;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validations.ComputerFormValidator;

@Controller
public class AddComputerController {

    @Autowired 
    ComputerService computerService;
    
    @Autowired
    CompanyService companyService;
    @Autowired
    ComputerFormValidator computerFormVAlidator;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(computerFormVAlidator);
    }
    
    @RequestMapping(value = "/addcomputer" , method = RequestMethod.GET )
    public String addCOmputer(Model model){
        
        model.addAttribute("list",companyService.findAll());
        return "addComputer";
    }
    @ModelAttribute("computerDto")
    public ComputerDto createComputerDto() {
        ComputerDto computerDto = new ComputerDto.Builder().build();
      return computerDto;
    }
    
    @RequestMapping(value="/addcomputer", method = RequestMethod.POST)
    public String saveCountry(@ModelAttribute("computerDto") @Valid ComputerDto computerDto, BindingResult result, Model model) {

      model.addAttribute("newComputerDto", computerDto);
      Computer computer = MapperComputer.convertToComputer(computerDto);
      computerService.create(computer);
      return "redirect:/addcomputer";
    }   
}
